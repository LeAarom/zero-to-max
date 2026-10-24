package com.zerotomax;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import javax.inject.Inject;
import net.runelite.api.Client;
import net.runelite.api.GameState;
import net.runelite.client.ui.FontManager;
import net.runelite.client.ui.overlay.OverlayPanel;
import net.runelite.client.ui.overlay.OverlayPosition;
import net.runelite.client.ui.overlay.components.LineComponent;

/**
 * The in-game box. Black and white like the side panel: the step in bold white, what to do in
 * light grey, and the small stuff in dark grey. Text runs left to right so long lines wrap neatly.
 */
public class ZeroToMaxOverlay extends OverlayPanel
{
	private static final Color WHITE = Color.WHITE;
	private static final Color LIGHT = new Color(0xD2, 0xD2, 0xD2);
	private static final Color DIM = new Color(0x8C, 0x8C, 0x8C);
	private static final int WIDTH = 210;

	private final Client client;
	private final ZeroToMaxPlugin plugin;
	private final ZeroToMaxConfig config;

	@Inject
	private ZeroToMaxOverlay(Client client, ZeroToMaxPlugin plugin, ZeroToMaxConfig config)
	{
		super(plugin);
		this.client = client;
		this.plugin = plugin;
		this.config = config;
		setPosition(OverlayPosition.TOP_LEFT);
	}

	@Override
	public Dimension render(Graphics2D graphics)
	{
		if (client.getGameState() != GameState.LOGGED_IN)
		{
			return null;
		}

		text("ZERO TO MAX", DIM, null);
		text(plugin.getCurrentStepTitle(), WHITE, FontManager.getRunescapeBoldFont());
		text(plugin.getCurrentStepInstruction(), LIGHT, null);
		text(plugin.getGpsStatusText(), WHITE, null);

		String method = plugin.getActiveMethodName();
		if (notEmpty(method))
		{
			text(config.trainingStyle() + " · " + method + "  [" + config.cycleStyleHotkey() + "]", DIM, null);
		}

		String progress = plugin.getProgressText();
		if (notEmpty(progress))
		{
			text(progress, DIM, null);
		}

		String slayer = plugin.getSlayerText();
		if (notEmpty(slayer))
		{
			text("Slayer: " + slayer, LIGHT, null);
		}

		String farm = plugin.getFarmAlertText();
		if (notEmpty(farm))
		{
			panelComponent.getChildren().add(LineComponent.builder()
				.left("Farming")
				.leftColor(DIM)
				.right(farm)
				.rightColor(WHITE)
				.build());
		}

		String account = plugin.getAccountStatusText();
		if (notEmpty(account))
		{
			// "Quests 94/184 ... QP 169" on one line, then "Diaries 0/48" under it, so nothing wraps
			String[] parts = account.split(" \\| ");
			if (parts.length >= 2)
			{
				panelComponent.getChildren().add(LineComponent.builder()
					.left(parts[0])
					.leftColor(DIM)
					.right(parts[parts.length - 1])
					.rightColor(DIM)
					.build());
				for (int i = 1; i < parts.length - 1; i++)
				{
					text(parts[i], DIM, null);
				}
			}
			else
			{
				text(account, DIM, null);
			}
		}

		panelComponent.setPreferredSize(new Dimension(WIDTH, 0));
		return super.render(graphics);
	}

	/** One left-aligned line (wraps when long). */
	private void text(String value, Color color, Font font)
	{
		if (!notEmpty(value))
		{
			return;
		}
		LineComponent.LineComponentBuilder b = LineComponent.builder()
			.left(value)
			.leftColor(color);
		if (font != null)
		{
			b.leftFont(font);
		}
		panelComponent.getChildren().add(b.build());
	}

	private static boolean notEmpty(String s)
	{
		return s != null && !s.isEmpty();
	}
}
