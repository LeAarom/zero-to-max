package com.zerotomax.farming;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;
import lombok.Getter;
import net.runelite.api.Client;
import net.runelite.api.Skill;
import net.runelite.api.coords.WorldPoint;
import net.runelite.api.gameval.VarPlayerID;
import net.runelite.api.gameval.VarbitID;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.plugins.timetracking.TimeTrackingConfig;
import net.runelite.client.plugins.timetracking.farming.CropState;
import net.runelite.client.plugins.timetracking.farming.Produce;

/**
 * Farm runs: patch states and timers (read from RuneLite's Time Tracking plugin, which must be on),
 * the next patch to visit on a run, and what to bring.
 *
 * Patch locations, unlocks, teleports and protection payments come from Quest Helper's herb and tree
 * run helpers (https://github.com/Zoinkwiz/quest-helper, BSD 2-Clause). The growth prediction is
 * adapted from Quest Helper's FarmingHandler. Seed and sapling levels are from the OSRS Wiki
 * (Farming/Level up table).
 */
public final class FarmTracker
{
	public enum RunType
	{
		HERB("Herb run", "Herbs"),
		TREE("Tree run", "Trees"),
		FRUIT_TREE("Fruit tree run", "Fruit trees"),
		HARDWOOD("Hardwood run", "Hardwood"),
		CALQUAT("Calquat run", "Calquat"),
		SPIRIT_TREE("Spirit tree run", "Spirit trees"),
		BIRDHOUSE("Birdhouse run", "Birdhouses");

		@Getter
		private final String label;
		/** Short name for the timer list. */
		@Getter
		private final String shortName;

		RunType(String label, String shortName)
		{
			this.label = label;
			this.shortName = shortName;
		}
	}

	enum Status
	{
		READY,
		EMPTY,
		GROWING,
		/** A spirit tree that is fully grown and already checked: leave it standing. */
		GROWN,
		DISEASED,
		DEAD,
		UNKNOWN
	}

	/** One patch on a run. */
	static final class Patch
	{
		final RunType type;
		final String name;
		final int regionId;
		final int varbit;
		final PatchImplementation implementation;
		final WorldPoint location;
		final String teleport;
		final int farmingLevel;
		/** Quest that must be finished to reach the patch, or null. */
		final String quest;
		/** Diary reward varbit that must be set, or -1. */
		final int diaryVarbit;
		/** Another skill needed to reach the patch (for example Sailing), or null. */
		final Skill otherSkill;
		final int otherLevel;

		Patch(RunType type, String name, int regionId, int varbit, PatchImplementation implementation,
			int x, int y, String teleport, int farmingLevel, String quest, int diaryVarbit)
		{
			this(type, name, regionId, varbit, implementation, x, y, teleport, farmingLevel, quest, diaryVarbit, null, 0);
		}

		Patch(RunType type, String name, int regionId, int varbit, PatchImplementation implementation,
			int x, int y, String teleport, int farmingLevel, String quest, int diaryVarbit, Skill otherSkill, int otherLevel)
		{
			this.otherSkill = otherSkill;
			this.otherLevel = otherLevel;
			this.type = type;
			this.name = name;
			this.regionId = regionId;
			this.varbit = varbit;
			this.implementation = implementation;
			this.location = new WorldPoint(x, y, 0);
			this.teleport = teleport;
			this.farmingLevel = farmingLevel;
			this.quest = quest;
			this.diaryVarbit = diaryVarbit;
		}
	}

	/** The next thing to do on a run. */
	@Getter
	public static final class FarmStep
	{
		private final String title;
		private final String instruction;
		private final String teleport;
		private final WorldPoint destination;
		private final String progress;

		FarmStep(String title, String instruction, String teleport, WorldPoint destination, String progress)
		{
			this.title = title;
			this.instruction = instruction;
			this.teleport = teleport;
			this.destination = destination;
			this.progress = progress;
		}
	}

	// Route order follows Quest Helper's runs.
	private static final List<Patch> PATCHES = Collections.unmodifiableList(Arrays.asList(
		// Herbs
		new Patch(RunType.HERB, "Falador", 12083, VarbitID.FARMING_TRANSMIT_D, PatchImplementation.HERB, 3058, 3311,
			"Explorers' ring 2+ or Falador teleport", 0, null, -1),
		new Patch(RunType.HERB, "Ardougne", 10548, VarbitID.FARMING_TRANSMIT_D, PatchImplementation.HERB, 2670, 3374,
			"Ardougne cloak 2+ or Ardougne teleport", 0, null, -1),
		new Patch(RunType.HERB, "Catherby", 11062, VarbitID.FARMING_TRANSMIT_D, PatchImplementation.HERB, 2813, 3463,
			"Catherby teleport or Camelot teleport", 0, null, -1),
		new Patch(RunType.HERB, "Morytania", 14391, VarbitID.FARMING_TRANSMIT_D, PatchImplementation.HERB, 3605, 3529,
			"Ectophial", 0, "PRIEST_IN_PERIL", -1),
		new Patch(RunType.HERB, "Hosidius", 6967, VarbitID.FARMING_TRANSMIT_D, PatchImplementation.HERB, 1738, 3550,
			"Xeric's talisman", 0, null, -1),
		new Patch(RunType.HERB, "Troll Stronghold", 11321, VarbitID.FARMING_TRANSMIT_A, PatchImplementation.HERB, 2826, 3694,
			"Stony basalt or Trollheim teleport", 0, "MY_ARMS_BIG_ADVENTURE", -1),
		new Patch(RunType.HERB, "Weiss", 11325, VarbitID.FARMING_TRANSMIT_A, PatchImplementation.HERB, 2848, 3934,
			"Icy basalt", 0, "MAKING_FRIENDS_WITH_MY_ARM", -1),
		new Patch(RunType.HERB, "Farming Guild", 4922, VarbitID.FARMING_TRANSMIT_E, PatchImplementation.HERB, 1238, 3726,
			"Skills necklace (Farming Guild)", 65, null, -1),
		new Patch(RunType.HERB, "Harmony", 15148, VarbitID.FARMING_TRANSMIT_B, PatchImplementation.HERB, 3789, 2837,
			"Harmony teleport tablet", 0, null, VarbitID.MORYTANIA_ELITE_REWARD),
		new Patch(RunType.HERB, "Varlamore", 6192, VarbitID.FARMING_TRANSMIT_D, PatchImplementation.HERB, 1582, 3094,
			"Quetzal whistle", 0, "CHILDREN_OF_THE_SUN", -1),

		// Trees
		new Patch(RunType.TREE, "Lumbridge", 12594, VarbitID.FARMING_TRANSMIT_A, PatchImplementation.TREE, 3193, 3231,
			"Lumbridge teleport", 0, null, -1),
		new Patch(RunType.TREE, "Falador", 11828, VarbitID.FARMING_TRANSMIT_A, PatchImplementation.TREE, 3004, 3373,
			"Falador teleport or Ring of wealth", 0, null, -1),
		new Patch(RunType.TREE, "Taverley", 11573, VarbitID.FARMING_TRANSMIT_A, PatchImplementation.TREE, 2936, 3438,
			"Falador teleport, then walk to Taverley", 0, null, -1),
		new Patch(RunType.TREE, "Varrock", 12854, VarbitID.FARMING_TRANSMIT_A, PatchImplementation.TREE, 3229, 3459,
			"Varrock teleport", 0, null, -1),
		new Patch(RunType.TREE, "Gnome Stronghold", 9781, VarbitID.FARMING_TRANSMIT_A, PatchImplementation.TREE, 2436, 3415,
			"Spirit tree to the Gnome Stronghold", 0, null, -1),
		new Patch(RunType.TREE, "Farming Guild", 4922, VarbitID.FARMING_TRANSMIT_G, PatchImplementation.TREE, 1232, 3736,
			"Skills necklace (Farming Guild)", 65, null, -1),
		new Patch(RunType.TREE, "Auburnvale", 5427, VarbitID.FARMING_TRANSMIT_A, PatchImplementation.TREE, 1367, 3322,
			"Pendant of ates or fairy ring", 0, "CHILDREN_OF_THE_SUN", -1),

		// Fruit trees
		new Patch(RunType.FRUIT_TREE, "Gnome Stronghold", 9781, VarbitID.FARMING_TRANSMIT_B, PatchImplementation.FRUIT_TREE, 2476, 3446,
			"Spirit tree to the Gnome Stronghold", 0, null, -1),
		new Patch(RunType.FRUIT_TREE, "Tree Gnome Village", 9777, VarbitID.FARMING_TRANSMIT_A, PatchImplementation.FRUIT_TREE, 2490, 3180,
			"Spirit tree to Tree Gnome Village (follow Elkoy to get out)", 0, null, -1),
		new Patch(RunType.FRUIT_TREE, "Brimhaven", 11058, VarbitID.FARMING_TRANSMIT_A, PatchImplementation.FRUIT_TREE, 2765, 3213,
			"Charter ship or boat to Brimhaven", 0, null, -1),
		new Patch(RunType.FRUIT_TREE, "Catherby", 11317, VarbitID.FARMING_TRANSMIT_A, PatchImplementation.FRUIT_TREE, 2860, 3433,
			"Catherby teleport or Camelot teleport", 0, null, -1),
		new Patch(RunType.FRUIT_TREE, "Lletya", 9265, VarbitID.FARMING_TRANSMIT_A, PatchImplementation.FRUIT_TREE, 2347, 3162,
			"Crystal teleport seed", 0, "MOURNINGS_END_PART_I", -1),
		new Patch(RunType.FRUIT_TREE, "Farming Guild", 4922, VarbitID.FARMING_TRANSMIT_K, PatchImplementation.FRUIT_TREE, 1242, 3758,
			"Skills necklace (Farming Guild)", 85, null, -1),
		new Patch(RunType.FRUIT_TREE, "Kastori", 5423, VarbitID.FARMING_TRANSMIT_B, PatchImplementation.FRUIT_TREE, 1350, 3057,
			"Pendant of ates or fairy ring", 0, "CHILDREN_OF_THE_SUN", -1),

		// Hardwood trees (Quest Helper's tree run; RuneLite's FarmingWorld for the patch ids)
		new Patch(RunType.HARDWOOD, "Fossil Island (west)", 14651, VarbitID.FARMING_TRANSMIT_C, PatchImplementation.HARDWOOD_TREE, 3702, 3837,
			"Digsite pendant (Fossil Island), then the magic mushtree to House on the Hill", 35, "BONE_VOYAGE", -1),
		new Patch(RunType.HARDWOOD, "Fossil Island (middle)", 14651, VarbitID.FARMING_TRANSMIT_B, PatchImplementation.HARDWOOD_TREE, 3708, 3833,
			"Next to the west patch", 35, "BONE_VOYAGE", -1),
		new Patch(RunType.HARDWOOD, "Fossil Island (east)", 14651, VarbitID.FARMING_TRANSMIT_A, PatchImplementation.HARDWOOD_TREE, 3715, 3835,
			"Next to the middle patch", 35, "BONE_VOYAGE", -1),
		new Patch(RunType.HARDWOOD, "Avium Savannah", 6702, VarbitID.FARMING_TRANSMIT_A, PatchImplementation.HARDWOOD_TREE, 1687, 2972,
			"Fairy ring AJP or Quetzal whistle", 35, "THE_RIBBITING_TALE_OF_A_LILY_PAD_LABOUR_DISPUTE", -1),
		new Patch(RunType.HARDWOOD, "Anglers' Retreat", 9770, VarbitID.FARMING_TRANSMIT_A, PatchImplementation.HARDWOOD_TREE, 2470, 2704,
			"Sail to Anglers' Retreat", 35, null, -1, Skill.SAILING, 51),

		// Calquat trees
		new Patch(RunType.CALQUAT, "Tai Bwo Wannai", 11056, VarbitID.FARMING_TRANSMIT_A, PatchImplementation.CALQUAT, 2795, 3102,
			"Tai Bwo Wannai teleport, or fairy ring CKR and walk", 72, null, -1),
		new Patch(RunType.CALQUAT, "Kastori", 5423, VarbitID.FARMING_TRANSMIT_A, PatchImplementation.CALQUAT, 1366, 3033,
			"Pendant of ates or fairy ring", 72, "CHILDREN_OF_THE_SUN", -1),
		new Patch(RunType.CALQUAT, "Great Conch", 12581, VarbitID.FARMING_TRANSMIT_C, PatchImplementation.CALQUAT, 3129, 2406,
			"Sail to the Great Conch", 72, "TROUBLED_TORTUGANS", -1),

		// Spirit trees (patch areas from Shortest Path's spirit-tree-patches.tsv)
		new Patch(RunType.SPIRIT_TREE, "Port Sarim", 12082, VarbitID.FARMING_TRANSMIT_A, PatchImplementation.SPIRIT_TREE, 3060, 3258,
			"Amulet of glory (Draynor Village), then walk south to Port Sarim", 83, null, -1),
		new Patch(RunType.SPIRIT_TREE, "Etceteria", 10300, VarbitID.FARMING_TRANSMIT_B, PatchImplementation.SPIRIT_TREE, 2613, 3857,
			"Ring of wealth (Miscellania) or fairy ring CIP", 83, "THRONE_OF_MISCELLANIA", -1),
		new Patch(RunType.SPIRIT_TREE, "Brimhaven", 11058, VarbitID.FARMING_TRANSMIT_B, PatchImplementation.SPIRIT_TREE, 2802, 3203,
			"Charter ship or boat to Brimhaven", 83, null, -1),
		new Patch(RunType.SPIRIT_TREE, "Hosidius", 6967, VarbitID.FARMING_TRANSMIT_F, PatchImplementation.SPIRIT_TREE, 1693, 3542,
			"Xeric's talisman or Hosidius teleport", 83, null, -1),
		new Patch(RunType.SPIRIT_TREE, "Farming Guild", 4922, VarbitID.FARMING_TRANSMIT_A, PatchImplementation.SPIRIT_TREE, 1253, 3750,
			"Skills necklace (Farming Guild)", 85, null, -1)
	));

	// {name, farming level} - OSRS Wiki Farming/Level up table
	private static final Object[][] HERB_SEEDS = {
		{"Guam", 9}, {"Marrentill", 14}, {"Tarromin", 19}, {"Harralander", 26}, {"Ranarr", 32}, {"Toadflax", 38},
		{"Irit", 44}, {"Avantoe", 50}, {"Kwuarm", 56}, {"Snapdragon", 62}, {"Huasca", 65}, {"Cadantine", 67},
		{"Lantadyme", 73}, {"Dwarf weed", 79}, {"Torstol", 85}};
	// {name, farming level, protection payment} - levels from the wiki, payments from Quest Helper
	private static final Object[][] TREE_SAPLINGS = {
		{"Oak", 15, "1 basket of tomatoes"}, {"Willow", 30, "1 basket of apples"}, {"Maple", 45, "1 basket of oranges"},
		{"Yew", 60, "10 cactus spines"}, {"Magic", 75, "25 coconuts"}};
	private static final Object[][] FRUIT_SAPLINGS = {
		{"Apple", 27, "9 sweetcorn"}, {"Banana", 33, "4 baskets of apples"}, {"Orange", 39, "3 baskets of strawberries"},
		{"Curry", 42, "5 baskets of bananas"}, {"Pineapple", 51, "10 watermelons"}, {"Papaya", 57, "10 pineapples"},
		{"Palm", 68, "15 papaya fruit"}, {"Dragonfruit", 81, "15 coconuts"}};
	private static final Object[][] HARDWOOD_SAPLINGS = {
		{"Teak", 35, "15 limpwurt roots"}, {"Mahogany", 55, "25 yanillian hops"}, {"Camphor", 66, "10 white berries"},
		{"Ironwood", 80, "10 curry leaves"}, {"Rosewood", 92, "8 dragonfruit"}};
	private static final Object[][] CALQUAT_SAPLINGS = {{"Calquat", 72, "8 poison ivy berries"}};
	private static final Object[][] SPIRIT_SAPLINGS = {{"Spirit", 83, "5 monkey nuts, 1 monkey bar and 1 ground tooth"}};
	/** How many spirit trees you can have at once, by Farming level (OSRS Wiki). */
	private static final int[][] SPIRIT_TREE_LIMITS = {{83, 1}, {88, 2}, {93, 3}, {96, 4}, {99, 99}};

	private static final int[] BIRDHOUSE_VARPS = {
		VarPlayerID.BIRDHOUSE_TRANSMIT_A, VarPlayerID.BIRDHOUSE_TRANSMIT_B,
		VarPlayerID.BIRDHOUSE_TRANSMIT_C, VarPlayerID.BIRDHOUSE_TRANSMIT_D};
	private static final long BIRDHOUSE_SECONDS = 50 * 60;

	// {name, Hunter level, Crafting level, logs} - OSRS Wiki, Bird house trapping
	private static final Object[][] BIRDHOUSES = {
		{"Regular", 5, 5, "logs"}, {"Oak", 14, 15, "oak logs"}, {"Willow", 24, 25, "willow logs"},
		{"Teak", 34, 35, "teak logs"}, {"Maple", 44, 45, "maple logs"}, {"Mahogany", 49, 50, "mahogany logs"},
		{"Yew", 59, 60, "yew logs"}, {"Magic", 74, 75, "magic logs"}, {"Redwood", 89, 90, "redwood logs"}};

	/**
	 * Fossil Island birdhouse spots, in run order. Exact spot tiles aren't available, so the GPS goes
	 * to the magic mushtree next to each pair (mushtree locations from Shortest Path's data).
	 * Varps follow RuneLite's BirdHouseSpace.
	 */
	private static final Object[][] BIRDHOUSE_SPOTS = {
		{"Verdant Valley (north-east)", VarPlayerID.BIRDHOUSE_TRANSMIT_C, 3760, 3758, "Verdant Valley, south of the Museum Camp: the north-east spot"},
		{"Verdant Valley (south-west)", VarPlayerID.BIRDHOUSE_TRANSMIT_D, 3760, 3758, "Verdant Valley: the south-west spot"},
		{"Mushroom Meadow (north)", VarPlayerID.BIRDHOUSE_TRANSMIT_A, 3676, 3871, "Mushroom Meadow: directly north of the magic mushtree"},
		{"Mushroom Meadow (south)", VarPlayerID.BIRDHOUSE_TRANSMIT_B, 3676, 3871, "Mushroom Meadow: by the entrance to the Tar Swamp"}};

	private final Client client;
	private final ConfigManager configManager;

	public FarmTracker(Client client, ConfigManager configManager)
	{
		this.client = client;
		this.configManager = configManager;
	}

	// ------------------------------------------------------------------ timers

	/** "Ready!", "mm:ss" until the first patch is ready, or a hint if nothing is tracked. */
	public String timerText(RunType type, Predicate<String> questDone)
	{
		if (type == RunType.BIRDHOUSE)
		{
			return birdhouseTimerText();
		}
		long now = Instant.now().getEpochSecond();
		long earliest = Long.MAX_VALUE;
		boolean anyKnown = false;
		boolean anyUnlocked = false;
		for (Patch p : PATCHES)
		{
			if (p.type != type || !isUnlocked(p, questDone))
			{
				continue;
			}
			anyUnlocked = true;
			long[] readyAt = new long[1];
			Status s = status(p, readyAt);
			if (s == Status.UNKNOWN)
			{
				continue;
			}
			anyKnown = true;
			if (s == Status.READY || s == Status.DEAD || s == Status.DISEASED)
			{
				return "Ready!";
			}
			if (s == Status.GROWING)
			{
				earliest = Math.min(earliest, readyAt[0]);
			}
		}
		if (!anyUnlocked)
		{
			return "Locked";
		}
		if (!anyKnown)
		{
			return "Not tracked";
		}
		if (earliest == Long.MAX_VALUE)
		{
			return type == RunType.SPIRIT_TREE ? "Nothing to do" : "Nothing planted";
		}
		return format(earliest - now);
	}

	public String birdhouseTimerText()
	{
		long now = Instant.now().getEpochSecond();
		long latest = 0;
		boolean anySeeded = false;
		for (int varp : BIRDHOUSE_VARPS)
		{
			String stored = configManager.getRSProfileConfiguration(TimeTrackingConfig.CONFIG_GROUP,
				TimeTrackingConfig.BIRD_HOUSE + "." + varp);
			long[] parsed = parse(stored);
			if (parsed == null)
			{
				continue;
			}
			int value = (int) parsed[0];
			if (value > 0 && value % 3 == 0) // seeded, same rule as RuneLite's BirdHouseState
			{
				anySeeded = true;
				latest = Math.max(latest, parsed[1] + BIRDHOUSE_SECONDS);
			}
		}
		if (!anySeeded)
		{
			return "Not set up";
		}
		return latest <= now ? "Ready!" : format(latest - now);
	}

	// ------------------------------------------------------------------ runs

	/** The next patch to do on this run, or null when the run is finished. */
	public FarmStep nextStep(RunType type, Predicate<String> questDone)
	{
		if (type == RunType.BIRDHOUSE)
		{
			return nextBirdhouse(questDone);
		}
		List<Patch> todo = new ArrayList<>();
		int done = 0;
		int planted = 0;
		for (Patch p : PATCHES)
		{
			if (p.type != type || !isUnlocked(p, questDone))
			{
				continue;
			}
			Status s = status(p, new long[1]);
			if (s == Status.GROWING || s == Status.GROWN)
			{
				done++;
			}
			if (s != Status.EMPTY && s != Status.UNKNOWN)
			{
				planted++;
			}
			if (s != Status.GROWING && s != Status.GROWN)
			{
				todo.add(p);
			}
		}
		if (type == RunType.SPIRIT_TREE && planted >= spiritTreeLimit())
		{
			// At the limit: only visit patches that already have a tree.
			todo.removeIf(p ->
			{
				Status s = status(p, new long[1]);
				return s == Status.EMPTY || s == Status.UNKNOWN;
			});
		}
		if (todo.isEmpty())
		{
			return null;
		}

		Patch next = todo.get(0);
		Status s = status(next, new long[1]);
		String what = whatToDo(type, s);
		String progress = (done + 1) + " of " + (done + todo.size()) + " patches";
		return new FarmStep(type.getLabel() + ": " + next.name, what, next.teleport, next.location, progress);
	}

	private String whatToDo(RunType type, Status s)
	{
		int level = client.getRealSkillLevel(Skill.FARMING);
		switch (type)
		{
			case HERB:
				switch (s)
				{
					case READY:
						return "Harvest the herbs, then add compost and plant a new seed.";
					case DEAD:
						return "Clear the dead herbs with a spade, then add compost and replant.";
					case DISEASED:
						return "Cure the patch with plant cure, or dig it up and replant.";
					default:
						return "Rake the patch if needed, add compost and plant a seed (" + best(HERB_SEEDS, level) + " is the best you can plant).";
				}
			case SPIRIT_TREE:
				switch (s)
				{
					case READY:
						return "Check the spirit tree's health for XP. Leave it standing: you can travel with it.";
					case DEAD:
						return "Clear the dead tree with a spade, then plant a spirit sapling and pay the gardener "
							+ SPIRIT_SAPLINGS[0][2] + ".";
					case DISEASED:
						return "Prune the diseased tree with secateurs (or pay the gardener), then carry on.";
					default:
						return "Rake the patch if needed, add compost, then plant a spirit sapling and pay the gardener "
							+ SPIRIT_SAPLINGS[0][2] + ". You can have " + limitText() + ".";
				}
			case CALQUAT:
				switch (s)
				{
					case READY:
						return "Check the tree's health (or pick the calquats), then clear it with a spade and plant a calquat sapling. Pay the gardener "
							+ CALQUAT_SAPLINGS[0][2] + " to protect it.";
					case DEAD:
						return "Clear the dead tree with a spade, then plant a calquat sapling and pay the gardener " + CALQUAT_SAPLINGS[0][2] + ".";
					case DISEASED:
						return "Prune the diseased tree with secateurs (or pay the gardener), then carry on.";
					default:
						return "Rake the patch if needed, add compost, then plant a calquat sapling and pay the gardener "
							+ CALQUAT_SAPLINGS[0][2] + " to protect it.";
				}
			case TREE:
			case FRUIT_TREE:
			case HARDWOOD:
			{
				Object[] sapling = bestRow(saplings(type), level);
				String plant = sapling == null
					? "You need a higher Farming level for this sapling."
					: "plant " + article((String) sapling[0]) + " " + sapling[0] + " sapling and pay the gardener " + sapling[2] + " to protect it.";
				switch (s)
				{
					case READY:
						return "Check the tree's health, pay the gardener to remove it, then " + plant;
					case DEAD:
						return "Clear the dead tree with a spade, then " + plant;
					case DISEASED:
						return "Prune the diseased tree with secateurs (or pay the gardener), then carry on.";
					default:
						return "Rake the patch if needed, add compost, then " + plant;
				}
			}
			default:
				return "";
		}
	}

	private FarmStep nextBirdhouse(Predicate<String> questDone)
	{
		if (!questDone.test("BONE_VOYAGE") || client.getRealSkillLevel(Skill.HUNTER) < 5)
		{
			return null;
		}
		long now = Instant.now().getEpochSecond();
		Object[] tier = bestBirdhouse();
		String house = tier == null ? "bird house" : tier[0] + " bird house";
		int done = 0;
		for (Object[] spot : BIRDHOUSE_SPOTS)
		{
			long[] parsed = parse(configManager.getRSProfileConfiguration(TimeTrackingConfig.CONFIG_GROUP,
				TimeTrackingConfig.BIRD_HOUSE + "." + spot[1]));
			String what;
			if (parsed == null || parsed[0] <= 0)
			{
				what = "Build and place a " + house + ", then add 10 seeds.";
			}
			else if (parsed[0] % 3 != 0)
			{
				what = "Add 10 seeds to the bird house.";
			}
			else if (parsed[1] + BIRDHOUSE_SECONDS <= now)
			{
				what = "Dismantle the full bird house, then place a new " + house + " and add 10 seeds.";
			}
			else
			{
				done++;
				continue; // seeded and still filling: done for this run
			}
			WorldPoint where = new WorldPoint((int) spot[2], (int) spot[3], 0);
			return new FarmStep("Birdhouse run: " + spot[0], spot[4] + ". " + what,
				"Digsite pendant (Fossil Island), then the magic mushtrees", where,
				(done + 1) + " of " + BIRDHOUSE_SPOTS.length + " spots");
		}
		return null;
	}

	/** Best bird house for both your Hunter and Crafting level. */
	private Object[] bestBirdhouse()
	{
		int hunter = client.getRealSkillLevel(Skill.HUNTER);
		int crafting = client.getRealSkillLevel(Skill.CRAFTING);
		Object[] best = null;
		for (Object[] row : BIRDHOUSES)
		{
			if ((int) row[1] <= hunter && (int) row[2] <= crafting)
			{
				best = row;
			}
		}
		return best;
	}

	/** What to bring for a run, based on Farming level and unlocked patches. */
	public List<String> checklist(RunType type, Predicate<String> questDone)
	{
		if (type == RunType.BIRDHOUSE)
		{
			List<String> out = new ArrayList<>();
			if (!questDone.test("BONE_VOYAGE"))
			{
				out.add("Finish Bone Voyage first to reach Fossil Island");
				return out;
			}
			Object[] tier = bestBirdhouse();
			if (tier == null)
			{
				out.add("You need 5 Hunter and 5 Crafting for bird houses");
				return out;
			}
			out.add("4 x " + tier[0] + " bird houses, or 4 x " + tier[3] + " + 4 clockwork + hammer + chisel to build them");
			out.add("40 low-level seeds (10 each) or 20 high-level seeds: hop, herb, flower, bush or allotment");
			out.add("Digsite pendant (Fossil Island)");
			out.add("Spots: 2 in Verdant Valley, 2 in Mushroom Meadow (use the magic mushtrees)");
			return out;
		}
		int level = client.getRealSkillLevel(Skill.FARMING);
		List<String> teleports = new ArrayList<>();
		int patches = 0;
		for (Patch p : PATCHES)
		{
			if (p.type == type && isUnlocked(p, questDone))
			{
				patches++;
				teleports.add(p.name + ": " + p.teleport);
			}
		}

		List<String> out = new ArrayList<>();
		switch (type)
		{
			case HERB:
				out.add(patches + " x " + best(HERB_SEEDS, level) + " seeds (best for your level, any herb works)");
				out.add(patches + " x ultracompost (best: lowers the chance your herbs die)");
				out.add("Spade, rake, seed dibber");
				out.add(questDone.test("FAIRYTALE_I__GROWING_PAINS") ? "Magic secateurs (more herbs)" : "Secateurs");
				out.add("Plant cure (optional, for diseased herbs)");
				out.add("No payment: gardeners can't protect herbs. Disease-free patches you have: " + diseaseFree(questDone));
				break;
			case TREE:
			case FRUIT_TREE:
			case HARDWOOD:
			case CALQUAT:
			case SPIRIT_TREE:
			{
				Object[] sapling = bestRow(saplings(type), level);
				if (sapling == null)
				{
					out.add("Locked: needs " + saplings(type)[0][1] + " Farming");
					return out;
				}
				if (patches == 0)
				{
					out.add("Locked: no patches unlocked yet (they need quests or Sailing)");
					return out;
				}
				int count = type == RunType.SPIRIT_TREE ? Math.min(patches, spiritTreeLimit()) : patches;
				out.add(count + " x " + sapling[0] + " saplings");
				out.add(count + " x payment: " + sapling[2]);
				out.add(count + " x compost (ultracompost or a bottomless bucket)");
				out.add("Spade, rake, secateurs, coins for the gardeners");
				if (type == RunType.HARDWOOD && (int) sapling[1] > 55)
				{
					out.add("If a patch won't take " + sapling[0] + ", plant mahogany or teak there");
				}
				if (type == RunType.SPIRIT_TREE)
				{
					out.add("You can have " + limitText() + " (more at 88, 93, 96 and 99 Farming)");
				}
				break;
			}
			default:
				break;
		}
		out.add("Teleports:");
		out.addAll(teleports);
		return out;
	}

	/** Herb patches that can't get diseased (OSRS Wiki, Herb patch). */
	private String diseaseFree(Predicate<String> questDone)
	{
		List<String> names = new ArrayList<>();
		if (questDone.test("MY_ARMS_BIG_ADVENTURE"))
		{
			names.add("Troll Stronghold");
		}
		if (questDone.test("MAKING_FRIENDS_WITH_MY_ARM"))
		{
			names.add("Weiss");
		}
		if (client.getVarbitValue(VarbitID.MORYTANIA_ELITE_REWARD) >= 1)
		{
			names.add("Harmony");
		}
		if (client.getVarbitValue(VarbitID.KOUREND_EASY_REWARD) >= 1)
		{
			names.add("Hosidius");
		}
		return names.isEmpty() ? "none yet" : String.join(", ", names);
	}

	// ------------------------------------------------------------------ patch state

	private boolean isUnlocked(Patch p, Predicate<String> questDone)
	{
		if (client.getRealSkillLevel(Skill.FARMING) < p.farmingLevel)
		{
			return false;
		}
		if (p.quest != null && !questDone.test(p.quest))
		{
			return false;
		}
		if (p.otherSkill != null && client.getRealSkillLevel(p.otherSkill) < p.otherLevel)
		{
			return false;
		}
		return p.diaryVarbit == -1 || client.getVarbitValue(p.diaryVarbit) >= 1;
	}

	/** Reads what Time Tracking stored for a patch. readyAt[0] is set when GROWING. */
	private Status status(Patch p, long[] readyAt)
	{
		String stored = configManager.getRSProfileConfiguration(TimeTrackingConfig.CONFIG_GROUP, p.regionId + "." + p.varbit);
		long[] parsed = parse(stored);
		if (parsed == null)
		{
			return Status.UNKNOWN;
		}

		if (p.implementation == PatchImplementation.SPIRIT_TREE && parsed[0] == 20)
		{
			return Status.GROWN; // fully grown and already checked
		}
		PatchState state = p.implementation.forVarbitValue((int) parsed[0]);
		if (state == null)
		{
			return Status.UNKNOWN;
		}
		CropState crop = state.getCropState();
		if (crop == CropState.EMPTY || state.getProduce() == Produce.WEEDS)
		{
			return Status.EMPTY;
		}
		if (crop == CropState.HARVESTABLE)
		{
			return Status.READY;
		}
		if (crop == CropState.DEAD)
		{
			return Status.DEAD;
		}
		if (crop == CropState.DISEASED)
		{
			return Status.DISEASED;
		}
		if (crop != CropState.GROWING)
		{
			return Status.UNKNOWN;
		}

		int tickRate = state.getTickRate();
		if (tickRate <= 0 || parsed[1] <= 0)
		{
			return Status.GROWING;
		}
		long tickTime = tickTime(tickRate, 0, parsed[1]);
		long done = tickTime(tickRate, state.getStages() - 1 - state.getStage(), tickTime);
		if (Instant.now().getEpochSecond() >= done)
		{
			return Status.READY;
		}
		readyAt[0] = done;
		return Status.GROWING;
	}

	/** Same farming-tick maths as Time Tracking (adapted from Quest Helper's FarmingHandler). */
	private long tickTime(int tickRate, int ticks, long requestedTime)
	{
		Integer precision = configManager.getRSProfileConfiguration(TimeTrackingConfig.CONFIG_GROUP,
			TimeTrackingConfig.FARM_TICK_OFFSET_PRECISION, int.class);
		Integer offset = configManager.getRSProfileConfiguration(TimeTrackingConfig.CONFIG_GROUP,
			TimeTrackingConfig.FARM_TICK_OFFSET, int.class);

		long offsetSeconds = 0;
		if (precision != null && offset != null && (precision >= tickRate || precision >= 40))
		{
			offsetSeconds = (offset % tickRate) * 60L;
		}
		long now = requestedTime + offsetSeconds;
		long currentTick = now - (now % (tickRate * 60L));
		long goalTick = currentTick + ((long) ticks * tickRate * 60);
		return goalTick - offsetSeconds;
	}

	/** "value:unixTime" -> {value, unixTime}, or null. */
	private static long[] parse(String stored)
	{
		if (stored == null)
		{
			return null;
		}
		String[] parts = stored.split(":");
		if (parts.length != 2)
		{
			return null;
		}
		try
		{
			return new long[]{Long.parseLong(parts[0]), Long.parseLong(parts[1])};
		}
		catch (NumberFormatException e)
		{
			return null;
		}
	}

	private static Object[][] saplings(RunType type)
	{
		switch (type)
		{
			case TREE:
				return TREE_SAPLINGS;
			case FRUIT_TREE:
				return FRUIT_SAPLINGS;
			case HARDWOOD:
				return HARDWOOD_SAPLINGS;
			case CALQUAT:
				return CALQUAT_SAPLINGS;
			case SPIRIT_TREE:
				return SPIRIT_SAPLINGS;
			default:
				return HERB_SEEDS;
		}
	}

	private static String article(String word)
	{
		return "AEIOU".indexOf(Character.toUpperCase(word.charAt(0))) >= 0 ? "an" : "a";
	}

	private int spiritTreeLimit()
	{
		int level = client.getRealSkillLevel(Skill.FARMING);
		int limit = 0;
		for (int[] row : SPIRIT_TREE_LIMITS)
		{
			if (level >= row[0])
			{
				limit = row[1];
			}
		}
		return limit;
	}

	private String limitText()
	{
		int limit = spiritTreeLimit();
		return limit >= 99 ? "as many spirit trees as you like" : limit + " spirit tree" + (limit == 1 ? "" : "s") + " at once";
	}

	private static String best(Object[][] table, int level)
	{
		Object[] row = bestRow(table, level);
		return row == null ? "no" : (String) row[0];
	}

	private static Object[] bestRow(Object[][] table, int level)
	{
		Object[] best = null;
		for (Object[] row : table)
		{
			if ((int) row[1] <= level)
			{
				best = row;
			}
		}
		return best;
	}

	private static String format(long seconds)
	{
		if (seconds <= 0)
		{
			return "Ready!";
		}
		long h = seconds / 3600;
		long m = (seconds % 3600) / 60;
		long s = seconds % 60;
		return h > 0 ? String.format("%dh %02dm", h, m) : String.format("%02d:%02d", m, s);
	}
}
