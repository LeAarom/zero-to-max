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

	/** A skill to focus on, or OFF to let the guide pick. Names match net.runelite.api.Skill. */
	enum FocusSkill
	{
		OFF("Off (guide picks)"),
		ATTACK("Attack"),
		STRENGTH("Strength"),
		DEFENCE("Defence"),
		RANGED("Ranged"),
		PRAYER("Prayer"),
		MAGIC("Magic"),
		RUNECRAFT("Runecraft"),
		CONSTRUCTION("Construction"),
		HITPOINTS("Hitpoints"),
		AGILITY("Agility"),
		HERBLORE("Herblore"),
		THIEVING("Thieving"),
		CRAFTING("Crafting"),
		FLETCHING("Fletching"),
		SLAYER("Slayer"),
		HUNTER("Hunter"),
		MINING("Mining"),
		SMITHING("Smithing"),
		FISHING("Fishing"),
		COOKING("Cooking"),
		FIREMAKING("Firemaking"),
		WOODCUTTING("Woodcutting"),
		FARMING("Farming"),
		SAILING("Sailing");

		private final String label;

		FocusSkill(String label)
		{
			this.label = label;
		}

		@Override
		public String toString()
		{
			return label;
		}
	}

	enum JewelleryBox
	{
		NONE("None"),
		BASIC("Basic"),
		FANCY("Fancy"),
		ORNATE("Ornate");

		private final String label;

		JewelleryBox(String label)
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
		name = "Your house (POH)",
		description = "What your house has, so teleport suggestions can use it",
		position = 20
	)
	String houseSection = "houseSection";

	@ConfigItem(
		keyName = "jewelleryBox",
		name = "Jewellery box",
		description = "The jewellery box in your house",
		section = houseSection,
		position = 21
	)
	default JewelleryBox jewelleryBox()
	{
		return JewelleryBox.NONE;
	}

	@ConfigItem(
		keyName = "houseSpiritTree",
		name = "Spirit tree in house",
		description = "Your house has a spirit tree",
		section = houseSection,
		position = 22
	)
	default boolean houseSpiritTree()
	{
		return false;
	}

	@ConfigItem(
		keyName = "houseFairyRing",
		name = "Fairy ring in house",
		description = "Your house has a fairy ring",
		section = houseSection,
		position = 23
	)
	default boolean houseFairyRing()
	{
		return false;
	}

	@ConfigItem(
		keyName = "useMaxHouses",
		name = "Suggest max houses (W330)",
		description = "Suggest public max houses on world 330 (join one from the house advertisement board next to any house portal) when their portals, jewellery box or spirit tree get you closer",
		section = houseSection,
		position = 24
	)
	default boolean useMaxHouses()
	{
		return true;
	}

	@ConfigSection(
		name = "Goals",
		description = "What the guide plans for you",
		position = 10
	)
	String goalsSection = "goalsSection";

	@ConfigItem(
		keyName = "focusSkill",
		name = "Train this skill",
		description = "Pick a skill and the guide shows the best way to train it to 99, using your training style. Overrides quests and diaries until you set it back to Off.",
		section = goalsSection,
		position = 9
	)
	default FocusSkill focusSkill()
	{
		return FocusSkill.OFF;
	}

	@ConfigItem(
		keyName = "skillsOnly",
		name = "Skills only (no quests)",
		description = "Skip quests and diaries and just plan your skills to 99. Farm runs, timers, teleports and Slayer help still work.",
		section = goalsSection,
		position = 10
	)
	default boolean skillsOnly()
	{
		return false;
	}

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
