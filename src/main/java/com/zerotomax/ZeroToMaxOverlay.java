package com.zerotomax;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import javax.inject.Inject;
import net.runelite.api.Client;
import net.runelite.api.GameState;
import net.runelite.client.ui.overlay.OverlayPanel;
import net.runelite.client.ui.overlay.OverlayPosition;
import net.runelite.client.ui.overlay.components.LineComponent;
import net.runelite.client.ui.overlay.components.TitleComponent;

public class ZeroToMaxOverlay extends OverlayPanel
{
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

		panelComponent.getChildren().add(TitleComponent.builder()
			.text("Zero-to-Max Guide")
			.color(Color.GREEN)
			.build());

		addLine("Account:", plugin.getAccountStatusText(), null);
		addLine("Step:", plugin.getCurrentStepTitle(), Color.YELLOW);

		String method = plugin.getActiveMethodName();
		if (method != null && !method.isEmpty())
		{
			addLine("Style [" + config.cycleStyleHotkey() + "]:", config.trainingStyle() + " - " + method, Color.MAGENTA);
		}

		addLine("Teleport:", plugin.getGpsStatusText(), plugin.getGpsColor());
		addLine("Action:", plugin.getCurrentStepInstruction(), null);

		String progress = plugin.getProgressText();
		if (progress != null && !progress.isEmpty())
		{
			addLine("Progress:", progress, Color.ORANGE);
		}

		String farm = plugin.getFarmAlertText();
		if (farm != null && !farm.isEmpty())
		{
			addLine("Farming:", farm, Color.GREEN);
		}

		panelComponent.setPreferredSize(new Dimension(300, 0));
		return super.render(graphics);
	}

	private void addLine(String left, String right, Color rightColor)
	{
		LineComponent.LineComponentBuilder b = LineComponent.builder()
			.left(left)
			.right(right == null ? "-" : right);
		if (rightColor != null)
		{
			b.rightColor(rightColor);
		}
		panelComponent.getChildren().add(b.build());
	}
}
