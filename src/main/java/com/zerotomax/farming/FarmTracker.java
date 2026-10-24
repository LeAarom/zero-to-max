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
		HERB("Herb run"),
		TREE("Tree run"),
		FRUIT_TREE("Fruit tree run");

		@Getter
		private final String label;

		RunType(String label)
		{
			this.label = label;
		}
	}

	enum Status
	{
		READY,
		EMPTY,
		GROWING,
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

		Patch(RunType type, String name, int regionId, int varbit, PatchImplementation implementation,
			int x, int y, String teleport, int farmingLevel, String quest, int diaryVarbit)
		{
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
			"Pendant of ates or fairy ring", 0, "CHILDREN_OF_THE_SUN", -1)
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

	private static final int[] BIRDHOUSE_VARPS = {
		VarPlayerID.BIRDHOUSE_TRANSMIT_A, VarPlayerID.BIRDHOUSE_TRANSMIT_B,
		VarPlayerID.BIRDHOUSE_TRANSMIT_C, VarPlayerID.BIRDHOUSE_TRANSMIT_D};
	private static final long BIRDHOUSE_SECONDS = 50 * 60;

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
		long now = Instant.now().getEpochSecond();
		long earliest = Long.MAX_VALUE;
		boolean anyKnown = false;
		for (Patch p : PATCHES)
		{
			if (p.type != type || !isUnlocked(p, questDone))
			{
				continue;
			}
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
		if (!anyKnown)
		{
			return "Not tracked yet";
		}
		if (earliest == Long.MAX_VALUE)
		{
			return "Nothing planted";
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
		List<Patch> todo = new ArrayList<>();
		int unlocked = 0;
		for (Patch p : PATCHES)
		{
			if (p.type != type || !isUnlocked(p, questDone))
			{
				continue;
			}
			unlocked++;
			Status s = status(p, new long[1]);
			if (s != Status.GROWING)
			{
				todo.add(p);
			}
		}
		if (todo.isEmpty())
		{
			return null;
		}

		Patch next = todo.get(0);
		Status s = status(next, new long[1]);
		String what = whatToDo(type, s);
		String progress = (unlocked - todo.size() + 1) + " of " + unlocked + " patches";
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
			case TREE:
			case FRUIT_TREE:
			{
				Object[] sapling = bestRow(type == RunType.TREE ? TREE_SAPLINGS : FRUIT_SAPLINGS, level);
				String plant = sapling == null
					? "You need a higher Farming level for this sapling."
					: "plant a " + sapling[0] + " sapling and pay the gardener " + sapling[2] + " to protect it.";
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

	/** What to bring for a run, based on Farming level and unlocked patches. */
	public List<String> checklist(RunType type, Predicate<String> questDone)
	{
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
			{
				Object[] sapling = bestRow(type == RunType.TREE ? TREE_SAPLINGS : FRUIT_SAPLINGS, level);
				if (sapling == null)
				{
					out.add("Your Farming level is too low for any " + (type == RunType.TREE ? "tree" : "fruit tree") + " sapling yet");
					return out;
				}
				out.add(patches + " x " + sapling[0] + " saplings");
				out.add(patches + " x payment: " + sapling[2]);
				out.add(patches + " x compost (ultracompost or a bottomless bucket)");
				out.add("Spade, rake, coins for the gardeners");
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
