package com.zerotomax;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.awt.geom.Path2D;
import javax.inject.Inject;
import net.runelite.api.Client;
import net.runelite.api.GameState;
import net.runelite.api.Perspective;
import net.runelite.api.Point;
import net.runelite.api.coords.LocalPoint;
import net.runelite.api.coords.WorldPoint;
import net.runelite.client.ui.overlay.Overlay;
import net.runelite.client.ui.overlay.OverlayLayer;
import net.runelite.client.ui.overlay.OverlayPosition;
import net.runelite.client.ui.overlay.OverlayUtil;

/**
 * Draws a glowing line to the objective. NOTE: this is a STRAIGHT line, it does
 * not walk around walls. Real pathfinding is the next job.
 */
public class ZeroToMaxWorldOverlay extends Overlay
{
	private final Client client;
	private final ZeroToMaxPlugin plugin;
	private final ZeroToMaxConfig config;

	@Inject
	private ZeroToMaxWorldOverlay(Client client, ZeroToMaxPlugin plugin, ZeroToMaxConfig config)
	{
		this.client = client;
		this.plugin = plugin;
		this.config = config;
		setPosition(OverlayPosition.DYNAMIC);
		setLayer(OverlayLayer.ABOVE_SCENE);
	}

	@Override
	public Dimension render(Graphics2D graphics)
	{
		if (!config.drawGroundPath()
			|| client.getGameState() != GameState.LOGGED_IN
			|| client.getLocalPlayer() == null
			|| client.getTopLevelWorldView().isInstance())
		{
			return null;
		}

		WorldPoint dest = plugin.getActiveDestination();
		if (dest == null)
		{
			return null;
		}

		WorldPoint player = client.getLocalPlayer().getWorldLocation();
		if (player.getPlane() != dest.getPlane())
		{
			return null;
		}

		// Highlight the destination tile
		LocalPoint destLp = LocalPoint.fromWorld(client, dest);
		if (destLp != null)
		{
			Polygon poly = Perspective.getCanvasTilePoly(client, destLp);
			if (poly != null)
			{
				OverlayUtil.renderPolygon(graphics, poly, Color.GREEN);
			}
		}

		int dist = player.distanceTo2D(dest);
		// With Shortest Path on, it draws the walkable route; we only highlight the destination tile.
		if (config.useShortestPath() || dist <= 1 || dist > 100)
		{
			return null;
		}

		// Build the line, breaking it wherever a point is off-scene or off-screen
		Path2D.Double path = new Path2D.Double();
		boolean penDown = false;
		for (int i = 0; i <= dist; i++)
		{
			double t = (double) i / dist;
			WorldPoint wp = new WorldPoint(
				(int) Math.round(player.getX() + t * (dest.getX() - player.getX())),
				(int) Math.round(player.getY() + t * (dest.getY() - player.getY())),
				player.getPlane());

			LocalPoint lp = LocalPoint.fromWorld(client, wp);
			Point pt = lp == null ? null : Perspective.localToCanvas(client, lp, player.getPlane());
			if (pt == null)
			{
				penDown = false;
				continue;
			}

			if (penDown)
			{
				path.lineTo(pt.getX(), pt.getY());
			}
			else
			{
				path.moveTo(pt.getX(), pt.getY());
				penDown = true;
			}
		}

		Stroke oldStroke = graphics.getStroke();
		Object oldAa = graphics.getRenderingHint(RenderingHints.KEY_ANTIALIASING);
		graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		Color base = config.pathColor();
		// soft glow underneath
		graphics.setColor(new Color(base.getRed(), base.getGreen(), base.getBlue(), 60));
		graphics.setStroke(new BasicStroke(9f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
		graphics.draw(path);
		// bright core
		graphics.setColor(base);
		graphics.setStroke(new BasicStroke(3f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
		graphics.draw(path);

		graphics.setStroke(oldStroke);
		if (oldAa != null)
		{
			graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, oldAa);
		}
		return null;
	}
}
