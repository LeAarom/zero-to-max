package com.zerotomax;

import java.awt.Color;
import java.awt.event.KeyEvent;
import net.runelite.client.config.Alpha;
import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;
import net.runelite.client.config.ConfigSection;
import net.runelite.client.config.Keybind;

@ConfigGroup(ZeroToMaxConfig.GROUP)
public interface ZeroToMaxConfig extends Config
{
	String GROUP = "zerotomax";

	enum TrainingStyle
	{
		FASTEST_XP("Fastest XP"),
		CHILL_AFK("Chill / AFK"),
		MINIGAME("Minigames");

		private final String label;

		TrainingStyle(String label)
		{
			this.label = label;
		}

		@Override
		public String toString()
		{
			return label;
		}
	}

	@ConfigSection(
		name = "GPS & Display",
		description = "Overlay line and training style",
		position = 0
	)
	String displaySection = "displaySection";

	@ConfigItem(
		keyName = "trainingStyle",
		name = "Training Style",
		description = "Fastest XP, chill AFK methods, or minigames",
		section = displaySection,
		position = 1
	)
	default TrainingStyle trainingStyle()
	{
		return TrainingStyle.FASTEST_XP;
	}

	@ConfigItem(
		keyName = "cycleStyleHotkey",
		name = "Cycle Style Hotkey",
		description = "Press to switch between training styles",
		section = displaySection,
		position = 2
	)
	default Keybind cycleStyleHotkey()
	{
		return new Keybind(KeyEvent.VK_F12, 0);
	}

	@ConfigItem(
		keyName = "drawGroundPath",
		name = "Draw GPS Line",
		description = "Draws a line to your current objective",
		section = displaySection,
		position = 3
	)
	default boolean drawGroundPath()
	{
		return true;
	}

	@ConfigItem(
		keyName = "useShortestPath",
		name = "Use Shortest Path for the route",
		description = "Send your destination to the Shortest Path plugin (install it from the Plugin Hub) so it draws a walkable route. Turn off to use the simple straight line instead.",
		section = displaySection,
		position = 5
	)
	default boolean useShortestPath()
	{
		return true;
	}

	@Alpha
	@ConfigItem(
		keyName = "pathColor",
		name = "GPS Line Color",
		description = "Color of the GPS line",
		section = displaySection,
		position = 4
	)
	default Color pathColor()
	{
		return Color.CYAN;
	}

	@ConfigSection(
		name = "Goals",
		description = "What the guide plans for you",
		position = 10
	)
	String goalsSection = "goalsSection";

	@ConfigItem(
		keyName = "includeDiaries",
		name = "Include achievement diaries",
		description = "Plan achievement diaries as well as quests",
		section = goalsSection,
		position = 11
	)
	default boolean includeDiaries()
	{
		return true;
	}

	@ConfigItem(
		keyName = "showSlayer",
		name = "Show Slayer task help",
		description = "Show your current Slayer task and the best way to do it",
		section = goalsSection,
		position = 12
	)
	default boolean showSlayer()
	{
		return true;
	}
}
