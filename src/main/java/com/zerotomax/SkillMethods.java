package com.zerotomax;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import lombok.Getter;
import net.runelite.api.Skill;
import net.runelite.api.coords.WorldPoint;

/**
 * How to train each skill at each level, per training style.
 *
 * GENERATED from the OSRS Wiki training guides (oldschool.runescape.wiki, CC BY-NC-SA 3.0),
 * summarised in our own words. XP rates are only filled in where the wiki states them (0 = unknown).
 * Methods have no GPS location yet.
 */
final class SkillMethods
{
	private SkillMethods()
	{
	}

	enum Style
	{
		FAST,
		AFK,
		MINIGAME
	}

	@Getter
	static final class Option
	{
		private final int minLevel;
		private final String methodName;
		private final String instruction;
		private final String teleportGuide;
		private final WorldPoint destination;
		/** 0 = unknown. */
		private final int xpPerHour;
		/** 0 = unknown. */
		private final double xpPerAction;
		private final String actionUnitName;

		Option(int minLevel, String methodName, String instruction, String teleportGuide,
			WorldPoint destination, int xpPerHour, double xpPerAction, String actionUnitName)
		{
			this.minLevel = minLevel;
			this.methodName = methodName;
			this.instruction = instruction;
			this.teleportGuide = teleportGuide;
			this.destination = destination;
			this.xpPerHour = xpPerHour;
			this.xpPerAction = xpPerAction;
			this.actionUnitName = actionUnitName;
		}
	}

	private static final Map<Skill, Map<Style, List<Option>>> TABLE = new EnumMap<>(Skill.class);

	static
	{
		add(Skill.AGILITY, Style.AFK, 1, "Draynor Village Rooftop Course", "Run low-attention rooftop laps in Draynor Village; can be combined with High Level Alchemy.", 9000, 0.0, "laps");
		add(Skill.AGILITY, Style.AFK, 20, "Brimhaven spikes (single-click)", "Use the very low intensity single-click floor spikes method in the Brimhaven Agility Arena. (Needs: 200 coins entry fee)", 36000, 0.0, "actions");
		add(Skill.AGILITY, Style.AFK, 60, "Seers' Village Rooftop Course", "Run Seers' Village rooftop laps; faster with the Seers' Camelot teleport.", 40000, 0.0, "laps");
		add(Skill.AGILITY, Style.AFK, 75, "Prifddinas Agility Course", "Run the less click-intensive Prifddinas Agility Course. (Needs: Song of the Elves)", 60000, 0.0, "laps");
		add(Skill.AGILITY, Style.AFK, 90, "Ardougne Rooftop Course", "Run Ardougne rooftop laps for high XP and marks of grace.", 66000, 0.0, "laps");
		add(Skill.AGILITY, Style.FAST, 1, "Draynor Village Rooftop Course", "Run laps of the Draynor Village rooftop course, collecting marks of grace.", 9000, 0.0, "laps");
		add(Skill.AGILITY, Style.FAST, 20, "Brimhaven Arena floor spikes", "Tag the floor spikes trap in the Brimhaven Agility Arena on Karamja; bring food. (Needs: 200 coins entry fee)", 30000, 0.0, "actions");
		add(Skill.AGILITY, Style.FAST, 40, "Brimhaven Arena pillars + spikes", "Tag ticket pillars while using the floor spikes trap in the Brimhaven Agility Arena. (Needs: 200 coins entry fee; level 40 for all obstacles)", 45000, 0.0, "actions");
		add(Skill.AGILITY, Style.FAST, 52, "Wilderness Agility Course", "Run laps of the Wilderness Agility Course; beware player killers. (Needs: 52 Agility to enter (boostable from 47 with summer pie))", 50000, 0.0, "laps");
		add(Skill.AGILITY, Style.FAST, 62, "Hallowed Sepulchre (floors 1-2)", "Complete Hallowed Sepulchre floors in Darkmeyer for large floor XP drops. (Needs: Sins of the Father)", 56300, 925.0, "floor 2 completions");
		add(Skill.AGILITY, Style.FAST, 87, "Hallowed Sepulchre (floors 1-5)", "Run all five Hallowed Sepulchre floors for the best Agility XP in the game. (Needs: Sins of the Father; 87 Agility for floor 5)", 98500, 5725.0, "floor 5 completions");
		add(Skill.AGILITY, Style.MINIGAME, 20, "Brimhaven Agility Arena (spikes)", "Tag the floor spikes trap in the Brimhaven Agility Arena. (Needs: 200 coins entry fee)", 30000, 0.0, "actions");
		add(Skill.AGILITY, Style.MINIGAME, 40, "Brimhaven Agility Arena (pillars)", "Tag pillars and the floor spikes in the Brimhaven Agility Arena. (Needs: 200 coins entry fee)", 45000, 0.0, "actions");
		add(Skill.AGILITY, Style.MINIGAME, 62, "Hallowed Sepulchre (floors 1-2)", "Complete Hallowed Sepulchre floors 1 and 2 in Darkmeyer. (Needs: Sins of the Father)", 56300, 925.0, "floor 2 completions");
		add(Skill.AGILITY, Style.MINIGAME, 77, "Hallowed Sepulchre (floors 1-4)", "Complete Hallowed Sepulchre floors 1 to 4 in Darkmeyer. (Needs: Sins of the Father; 77 Agility for floor 4)", 79700, 2875.0, "floor 4 completions");
		add(Skill.AGILITY, Style.MINIGAME, 87, "Hallowed Sepulchre (floors 1-5)", "Complete all five Hallowed Sepulchre floors in Darkmeyer. (Needs: Sins of the Father; 87 Agility for floor 5)", 98500, 5725.0, "floor 5 completions");
		add(Skill.ATTACK, Style.AFK, 1, "Questing", "Complete the early melee XP-reward quests from the wiki quest list, granting 61,775 Attack XP (to about 45 Attack). (Needs: Quests on the wiki early melee list)", 0, 0.0, "actions");
		add(Skill.ATTACK, Style.AFK, 30, "Gemstone Crab", "Attack the Gemstone Crab in the Tlati Rainforest and re-find it each time it burrows (about every 10 minutes). (Needs: Children of the Sun)", 0, 3.5, "damage");
		add(Skill.ATTACK, Style.AFK, 60, "Nightmare Zone (absorptions)", "Run normal rumble in Nightmare Zone north of Yanille with absorption potions, allowing about 20 minute breaks. (Needs: At least 5 of the quests Nightmare Zone uses)", 0, 0.0, "actions");
		add(Skill.ATTACK, Style.AFK, 75, "Maniacal monkeys (auto-retaliate)", "Stand among maniacal monkeys in Kruk's Dungeon with Hallowfell on auto-retaliate for low-intensity XP. (Needs: 75 Attack (Hallowfell), Monkey Madness II)", 0, 0.0, "actions");
		add(Skill.ATTACK, Style.FAST, 1, "Questing", "Complete the early melee XP-reward quests from the wiki quest list, granting 61,775 Attack XP (to about 45 Attack). (Needs: Quests on the wiki early melee list (e.g. Waterfall Quest))", 0, 0.0, "actions");
		add(Skill.ATTACK, Style.FAST, 30, "Gemstone Crab", "Fight the Gemstone Crab in the Tlati Rainforest mines; it has infinite HP and moves to another mine every 10 minutes. (Needs: Children of the Sun)", 0, 3.5, "damage");
		add(Skill.ATTACK, Style.FAST, 55, "Sulphur Naguas", "Kill Sulphur Naguas in Neypotzli with Protect from Melee, using glacial temotli or sulphur blades and the Blood Moon set if owned. (Needs: Partial Perilous Moons, 48 Slayer, 55 Attack, 43 Prayer)", 75000, 0.0, "actions");
		add(Skill.ATTACK, Style.FAST, 75, "Maniacal monkeys (Hallowfell)", "Stack and kill maniacal monkeys in north-west Kruk's Dungeon on Ape Atoll with Hallowfell and strength gear. (Needs: 75 Attack (Hallowfell), Monkey Madness II)", 200000, 0.0, "actions");
		add(Skill.ATTACK, Style.MINIGAME, 60, "Nightmare Zone", "Play Nightmare Zone north of Yanille on normal rumble with absorption potions and overloads or super combats. (Needs: At least 5 of the quests Nightmare Zone uses)", 0, 0.0, "actions");
		add(Skill.CONSTRUCTION, Style.AFK, 1, "Wooden hull parts (workbench)", "Make wooden hull parts at the Shipwrights' workbench in Deepfin Point; relaxed pace. (Needs: Pandemonium)", 72500, 72.5, "hull parts");
		add(Skill.CONSTRUCTION, Style.AFK, 8, "Oak hull parts (workbench)", "Make oak hull parts at the Shipwrights' workbench in Deepfin Point; relaxed pace. (Needs: Pandemonium)", 150000, 150.0, "hull parts");
		add(Skill.CONSTRUCTION, Style.AFK, 23, "Teak hull parts (workbench)", "Make teak hull parts at the Shipwrights' workbench in Deepfin Point; relaxed pace. (Needs: Pandemonium)", 225000, 225.0, "hull parts");
		add(Skill.CONSTRUCTION, Style.AFK, 41, "Mahogany hull parts (workbench)", "Make mahogany hull parts at the Shipwrights' workbench in Deepfin Point; relaxed pace. (Needs: Pandemonium)", 350000, 350.0, "hull parts");
		add(Skill.CONSTRUCTION, Style.AFK, 53, "Camphor hull parts (workbench)", "Make camphor hull parts at the Shipwrights' workbench in Deepfin Point; relaxed pace. (Needs: Pandemonium)", 400000, 400.0, "hull parts");
		add(Skill.CONSTRUCTION, Style.AFK, 77, "Ironwood hull parts (workbench)", "Make ironwood hull parts at the Shipwrights' workbench in Deepfin Point; relaxed pace. (Needs: Pandemonium)", 437500, 437.5, "hull parts");
		add(Skill.CONSTRUCTION, Style.FAST, 1, "Crude wooden chairs", "Build crude wooden chairs in your Parlour using planks and steel nails. (Needs: Saw, hammer, planks, steel nails)", 0, 58.0, "chairs");
		add(Skill.CONSTRUCTION, Style.FAST, 9, "Wooden larders", "Build wooden larders in your Kitchen using planks and steel nails. (Needs: Saw, hammer, planks, steel nails)", 0, 228.0, "larders");
		add(Skill.CONSTRUCTION, Style.FAST, 16, "Crafting table 1", "Build crafting tables in your Workshop using 4 oak planks each. (Needs: Saw, hammer, oak planks)", 0, 240.0, "tables");
		add(Skill.CONSTRUCTION, Style.FAST, 33, "Oak larders", "Build and remove oak larders in your Kitchen using 8 oak planks each. (Needs: Saw, hammer, oak planks)", 0, 480.0, "larders");
		add(Skill.CONSTRUCTION, Style.FAST, 52, "Mahogany tables", "Build mahogany tables in your Dining room using 6 mahogany planks each. (Needs: Saw, hammer, mahogany planks)", 900000, 840.0, "tables");
		add(Skill.CONSTRUCTION, Style.FAST, 77, "Gnome benches", "Build gnome benches in a Superior garden using 6 mahogany planks each. (Needs: Saw, hammer, mahogany planks)", 1100000, 840.0, "benches");
		add(Skill.CONSTRUCTION, Style.MINIGAME, 1, "Mahogany Homes (Beginner)", "Do Beginner Mahogany Homes contracts in the four cities; a plank sack helps. (Needs: Player-owned house; teleports to four cities; charged Xeric's talisman)", 35000, 865.01, "contracts");
		add(Skill.CONSTRUCTION, Style.MINIGAME, 20, "Mahogany Homes (Novice)", "Do Novice Mahogany Homes contracts in the four cities; a plank sack helps. (Needs: Player-owned house; teleports to four cities; charged Xeric's talisman)", 80000, 1887.44, "contracts");
		add(Skill.CONSTRUCTION, Style.MINIGAME, 50, "Mahogany Homes (Adept)", "Do Adept Mahogany Homes contracts in the four cities; a plank sack helps. (Needs: Player-owned house; teleports to four cities; charged Xeric's talisman)", 130000, 3275.58, "contracts");
		add(Skill.CONSTRUCTION, Style.MINIGAME, 70, "Mahogany Homes (Expert)", "Do Expert Mahogany Homes contracts in the four cities; a plank sack helps. (Needs: Player-owned house; teleports to four cities; charged Xeric's talisman)", 185000, 4373.37, "contracts");
		add(Skill.COOKING, Style.AFK, 1, "Cook low-level fish", "Cook the best raw fish you can (sardine, herring, trout, salmon) on a range.", 0, 0.0, "fish");
		add(Skill.COOKING, Style.AFK, 30, "Cooked karambwan", "Cook karambwan normally at the Hosidius kitchen range (Rogues' Den after 96). (Needs: Tai Bwo Wannai Trio; easy Kourend & Kebos Diary for Hosidius)", 141000, 0.0, "karambwan");
		add(Skill.COOKING, Style.AFK, 80, "Sharks", "Cook raw sharks at the Hosidius kitchen range wearing cooking gauntlets. (Needs: Cooking gauntlets (Family Crest); easy Kourend & Kebos Diary)", 275000, 210.0, "sharks");
		add(Skill.COOKING, Style.AFK, 84, "Anglerfish", "Cook raw anglerfish at the Hosidius kitchen range wearing cooking gauntlets. (Needs: Cooking gauntlets (Family Crest); easy Kourend & Kebos Diary (elite recommended))", 305000, 230.0, "anglerfish");
		add(Skill.COOKING, Style.FAST, 1, "1-tick poison karambwan", "1-tick cook raw karambwan into poison karambwan at a fire or range.", 0, 0.0, "karambwan");
		add(Skill.COOKING, Style.FAST, 30, "1-tick cooked karambwan", "1-tick cook karambwan at a range next to a bank, e.g. Rogues' Den or Myths' Guild. (Needs: Tai Bwo Wannai Trio)", 464000, 0.0, "karambwan");
		add(Skill.CRAFTING, Style.AFK, 1, "Glassblowing: beer glasses", "Use a glassblowing pipe on molten glass to make beer glasses (candle lanterns at 4, oil lamps at 12). (Needs: Glassblowing pipe)", 30625, 17.5, "items made");
		add(Skill.CRAFTING, Style.AFK, 33, "Glassblowing: vials", "Use a glassblowing pipe on molten glass to make vials (fishbowls at 42). (Needs: Glassblowing pipe)", 61250, 35.0, "items made");
		add(Skill.CRAFTING, Style.AFK, 46, "Glassblowing: unpowered orbs", "Use a glassblowing pipe on molten glass to make unpowered orbs. (Needs: Glassblowing pipe)", 91875, 52.5, "items made");
		add(Skill.CRAFTING, Style.AFK, 49, "Glassblowing: lantern lenses", "Use a glassblowing pipe on molten glass to make lantern lenses. (Needs: Glassblowing pipe)", 96250, 55.0, "items made");
		add(Skill.CRAFTING, Style.AFK, 87, "Glassblowing: light orbs", "Use a glassblowing pipe on molten glass to make empty light orbs. (Needs: Glassblowing pipe)", 122500, 70.0, "items made");
		add(Skill.CRAFTING, Style.FAST, 1, "Leather items", "Craft the highest leather item you can (gloves, boots, cowl, vambraces, body, chaps) using leather, a needle and thread.", 0, 13.8, "items made");
		add(Skill.CRAFTING, Style.FAST, 20, "Cutting gems", "Cut uncut gems with a chisel, using the best gem you can (sapphire, emerald, ruby, diamond, dragonstone).", 139000, 50.0, "gems cut");
		add(Skill.CRAFTING, Style.FAST, 54, "Battlestaves", "Attach orbs to battlestaves (water 54, earth 58, fire 62, air 66); Zaff sells noted battlestaves to Varrock Diary completers. (Needs: Varrock Diary optional for Zaff battlestaves)", 245000, 100.0, "battlestaves made");
		add(Skill.CRAFTING, Style.FAST, 63, "Green d'hide bodies", "Make green d'hide bodies with needle and thread (switch to blue d'hide bodies at 71). (Needs: Costume needle (Death on the Isle) optional)", 313410, 186.0, "bodies made");
		add(Skill.CRAFTING, Style.FAST, 77, "Red d'hide bodies", "Make red d'hide bodies with needle and thread or a costume needle. (Needs: Costume needle (Death on the Isle) optional)", 394290, 234.0, "bodies made");
		add(Skill.CRAFTING, Style.FAST, 84, "Black d'hide bodies", "Make black d'hide bodies with needle and thread or a costume needle. (Needs: Costume needle (Death on the Isle) optional)", 434730, 258.0, "bodies made");
		add(Skill.DEFENCE, Style.AFK, 1, "Questing", "Complete the early melee XP-reward quests from the wiki quest list, granting 15,300 Defence XP (to about 31 Defence). (Needs: Quests on the wiki early melee list)", 0, 0.0, "actions");
		add(Skill.DEFENCE, Style.AFK, 30, "Gemstone Crab", "Attack the Gemstone Crab in the Tlati Rainforest and re-find it each time it burrows (about every 10 minutes). (Needs: Children of the Sun)", 0, 3.5, "damage");
		add(Skill.DEFENCE, Style.AFK, 60, "Nightmare Zone (absorptions)", "Run normal rumble in Nightmare Zone north of Yanille with absorption potions, allowing about 20 minute breaks. (Needs: At least 5 of the quests Nightmare Zone uses)", 0, 0.0, "actions");
		add(Skill.DEFENCE, Style.AFK, 75, "Maniacal monkeys (auto-retaliate)", "Stand among maniacal monkeys in Kruk's Dungeon with Hallowfell on auto-retaliate for low-intensity XP. (Needs: 75 Attack (Hallowfell), Monkey Madness II)", 0, 0.0, "actions");
		add(Skill.DEFENCE, Style.FAST, 1, "Questing", "Complete the early melee XP-reward quests from the wiki quest list, granting 15,300 Defence XP (to about 31 Defence). (Needs: Quests on the wiki early melee list (e.g. Waterfall Quest))", 0, 0.0, "actions");
		add(Skill.DEFENCE, Style.FAST, 30, "Gemstone Crab", "Fight the Gemstone Crab in the Tlati Rainforest mines; it has infinite HP and moves to another mine every 10 minutes. (Needs: Children of the Sun)", 0, 3.5, "damage");
		add(Skill.DEFENCE, Style.FAST, 55, "Sulphur Naguas", "Kill Sulphur Naguas in Neypotzli with Protect from Melee, using glacial temotli or sulphur blades and the Blood Moon set if owned. (Needs: Partial Perilous Moons, 48 Slayer, 55 Attack, 43 Prayer)", 75000, 0.0, "actions");
		add(Skill.DEFENCE, Style.FAST, 75, "Maniacal monkeys (Hallowfell)", "Stack and kill maniacal monkeys in north-west Kruk's Dungeon on Ape Atoll with Hallowfell and strength gear. (Needs: 75 Attack (Hallowfell), Monkey Madness II)", 200000, 0.0, "actions");
		add(Skill.DEFENCE, Style.FAST, 76, "Chin maniacal monkeys (Long fuse)", "Throw chinchompas on Long fuse at maniacal monkeys in Kruk's Dungeon; the wiki says this gives more Defence XP/hr than melee. (Needs: Monkey Madness II (Chapter II started), chinchompas)", 0, 0.0, "actions");
		add(Skill.DEFENCE, Style.MINIGAME, 60, "Nightmare Zone", "Play Nightmare Zone north of Yanille on normal rumble with absorption potions and overloads or super combats. (Needs: At least 5 of the quests Nightmare Zone uses)", 0, 0.0, "actions");
		add(Skill.FARMING, Style.AFK, 1, "Compost bins", "Make supercompost or ultracompost in compost bins for steady low-level XP.", 0, 8.5, "buckets");
		add(Skill.FARMING, Style.AFK, 15, "Passive tree runs", "Do a tree and fruit tree run whenever trees finish growing; XP comes while you do other things.", 0, 0.0, "actions");
		add(Skill.FARMING, Style.AFK, 47, "Herb and allotment runs", "Do herb runs every 80 minutes and plant watermelons in allotments (snape grass at 61).", 0, 54.5, "harvests");
		add(Skill.FARMING, Style.FAST, 1, "Bagged plants", "Buy bagged plants from the Garden Supplier and plant them in your player-owned house garden.", 0, 31.0, "plants");
		add(Skill.FARMING, Style.FAST, 15, "Tree runs", "Plant the best tree sapling in every tree patch each run (oak 15, willow 30, maple 45, yew 60).", 0, 481.3, "trees");
		add(Skill.FARMING, Style.FAST, 27, "Tree + fruit tree runs", "Add fruit tree patches to tree runs (apple 27, banana 33, orange 39, curry 42, pineapple 51, papaya 57, palm 68).", 0, 1272.5, "fruit trees");
		add(Skill.FARMING, Style.FAST, 35, "Add hardwood trees", "Add teak hardwood trees on Fossil Island to runs (mahogany at 55).", 0, 7325.0, "hardwood trees");
		add(Skill.FARMING, Style.FAST, 75, "Magic tree runs", "Plant magic trees in tree patches, plus calquat at 72 and palm or better fruit trees.", 0, 13913.8, "trees");
		add(Skill.FARMING, Style.FAST, 85, "Full tree run", "Run magic trees, dragonfruit trees, calquat and celastrus (add mahogany and redwood at 90).", 2400000, 0.0, "actions");
		add(Skill.FARMING, Style.MINIGAME, 34, "Tithe Farm", "Plant, water and harvest Tithe Farm seeds for XP and rewards like the seed box and gricoller's can. (Needs: 34 Farming)", 0, 0.0, "actions");
		add(Skill.FARMING, Style.MINIGAME, 74, "Tithe Farm (high level)", "Play Tithe Farm with the highest-level seeds for the best XP rates.", 90000, 0.0, "actions");
		add(Skill.FIREMAKING, Style.AFK, 1, "Forester's campfire (logs)", "Add normal logs to a Forester's campfire next to a bank, e.g. the Grand Exchange.", 26600, 40.0, "logs");
		add(Skill.FIREMAKING, Style.AFK, 15, "Forester's campfire (oak)", "Add oak logs to a Forester's campfire next to a bank.", 39900, 60.0, "logs");
		add(Skill.FIREMAKING, Style.AFK, 45, "Forester's campfire (maple)", "Add maple logs to a Forester's campfire next to a bank.", 89775, 135.0, "logs");
		add(Skill.FIREMAKING, Style.AFK, 60, "Forester's campfire (yew)", "Add yew logs to a Forester's campfire next to a bank.", 134662, 202.5, "logs");
		add(Skill.FIREMAKING, Style.AFK, 75, "Forester's campfire (magic)", "Add magic logs to a Forester's campfire next to a bank.", 202027, 303.8, "logs");
		add(Skill.FIREMAKING, Style.AFK, 90, "Forester's campfire (redwood)", "Add redwood logs to a Forester's campfire next to a bank.", 232750, 350.0, "logs");
		add(Skill.FIREMAKING, Style.FAST, 1, "Blue logs with firelighter", "Burn logs lit with a blue firelighter in lines at the east Grand Exchange. (Needs: Blue firelighters; tinderbox)", 74250, 50.0, "logs");
		add(Skill.FIREMAKING, Style.FAST, 35, "Teak logs", "Burn teak logs in lines at the east Grand Exchange. (Needs: Tinderbox)", 138000, 105.0, "logs");
		add(Skill.FIREMAKING, Style.FAST, 50, "Mahogany logs", "Burn mahogany logs in lines at the east Grand Exchange. (Needs: Tinderbox)", 233887, 157.5, "logs");
		add(Skill.FIREMAKING, Style.FAST, 60, "Yew logs", "Burn yew logs in lines at the east Grand Exchange. (Needs: Tinderbox)", 300712, 202.5, "logs");
		add(Skill.FIREMAKING, Style.FAST, 75, "Magic logs", "Burn magic logs in lines at the east Grand Exchange. (Needs: Tinderbox)", 451143, 303.8, "logs");
		add(Skill.FIREMAKING, Style.FAST, 90, "Redwood logs", "Burn redwood logs in lines at the east Grand Exchange. (Needs: Tinderbox)", 519750, 350.0, "logs");
		add(Skill.FIREMAKING, Style.MINIGAME, 50, "Wintertodt", "Subdue the Wintertodt at the Wintertodt camp by chopping, fletching and burning roots in the braziers. (Needs: 50 Firemaking; warm clothing and food; pyromancer outfit optional)", 161000, 0.0, "actions");
		add(Skill.FISHING, Style.AFK, 20, "AFK fly fishing", "Fly fish trout and salmon at a river spot, e.g. south-east of Prifddinas, banking or dropping. (Needs: Fly fishing rod; feathers)", 13000, 0.0, "fish");
		add(Skill.FISHING, Style.AFK, 58, "AFK barbarian fishing", "Barbarian fish leaping fish at Otto's Grotto or Mount Quidamortem, dropping fish. (Needs: Partial Barbarian Training; 30 Agility and Strength (45 for sturgeon); barbarian rod; feathers)", 37000, 0.0, "fish");
		add(Skill.FISHING, Style.FAST, 1, "Quests", "Complete Sea Slug, Fishing Contest and Freeing Pirate Pete for 10,612 Fishing xp (to level 27). (Needs: Quest requirements for each quest)", 0, 0.0, "actions");
		add(Skill.FISHING, Style.FAST, 20, "3-tick fly fishing", "3-tick fly fish trout and salmon, e.g. south-east of the Prifddinas southern gate. (Needs: Fly fishing rod; feathers; tick manipulation)", 23000, 0.0, "fish");
		add(Skill.FISHING, Style.FAST, 58, "3-tick barbarian fishing", "3-tick barbarian fish leaping fish at Otto's Grotto using cut-eat with a knife. (Needs: Partial Barbarian Training; 30 Agility and Strength (45 for sturgeon); barbarian rod; feathers; knife)", 68000, 0.0, "fish");
		add(Skill.FISHING, Style.FAST, 71, "2-tick harpoon swordfish/tuna", "2-tick harpoon swordfish and tuna on the Port Piscarilius shore using lured rats. (Needs: Harpoon (crystal recommended); shortbow with no ammo; auto retaliate)", 78400, 0.0, "fish");
		add(Skill.FISHING, Style.FAST, 78, "Leechfin fishing", "Fish leechfin with a big fishing net in the Vampyrium via the blood portal at Castle Drakan. (Needs: The Blood Moon Rises; big fishing net; angler's outfit assumed)", 109000, 0.0, "fish");
		add(Skill.FISHING, Style.MINIGAME, 35, "Tempoross", "Fish and fight the Tempoross spirit at the Ruins of Unkah with a harpoon (mass or solo). (Needs: 35 Fishing; harpoon)", 30000, 0.0, "actions");
		add(Skill.FISHING, Style.MINIGAME, 43, "Aerial fishing", "Aerial fish with a cormorant on Lake Molch (fairy ring DJR) for Fishing and Hunter xp. (Needs: 43 Fishing; 35 Hunter)", 27600, 0.0, "fish");
		add(Skill.FLETCHING, Style.AFK, 10, "Darts (zero-time)", "Attach feathers to dart tips while doing other activities, upgrading metal as you level (rune at 81, dragon at 95).", 0, 1.8, "darts made");
		add(Skill.FLETCHING, Style.AFK, 20, "Vale Totems: oak shortbow (u)", "Fletch oak shortbows (u) at the Vale Totems with a log basket for a full 8-totem loop; a fletching knife is faster. (Needs: Vale Totems miniquest; log basket for full loops)", 34240, 0.0, "actions");
		add(Skill.FLETCHING, Style.AFK, 35, "Vale Totems: willow shortbow (u)", "Fletch willow shortbows (u) at the Vale Totems, using 25 logs for a 5-totem partial loop. (Needs: Vale Totems miniquest)", 66130, 0.0, "actions");
		add(Skill.FLETCHING, Style.AFK, 50, "Vale Totems: maple shortbow (u)", "Fletch maple shortbows (u) at the Vale Totems (maple longbows (u) at 55); a fletching knife is faster. (Needs: Vale Totems miniquest)", 105800, 0.0, "actions");
		add(Skill.FLETCHING, Style.FAST, 1, "Bronze arrows", "Attach bronze arrowtips to headless arrows (make headless arrows from shafts and feathers first if needed).", 58500, 1.3, "arrows made");
		add(Skill.FLETCHING, Style.FAST, 15, "Iron arrows", "Attach iron arrowtips to headless arrows.", 112500, 2.5, "arrows made");
		add(Skill.FLETCHING, Style.FAST, 30, "Steel arrows", "Attach steel arrowtips to headless arrows (mithril arrows at 45).", 225000, 5.0, "arrows made");
		add(Skill.FLETCHING, Style.FAST, 52, "Broad arrows", "Attach broad arrowheads to headless arrows. (Needs: Broader fletching Slayer reward)", 450000, 10.0, "arrows made");
		add(Skill.FLETCHING, Style.FAST, 75, "Rune arrows", "Attach rune arrowtips to headless arrows (amethyst arrows at 82).", 562500, 12.5, "arrows made");
		add(Skill.FLETCHING, Style.FAST, 90, "Dragon arrows", "Attach dragon arrowtips to headless arrows.", 675000, 15.0, "arrows made");
		add(Skill.HERBLORE, Style.AFK, 3, "Cleaning grimy herbs", "Clean grimy herbs from the bank, a slow but profitable method. (Needs: Druidic Ritual)", 0, 0.0, "herbs cleaned");
		add(Skill.HERBLORE, Style.AFK, 81, "Mastering Mixology (passive)", "Play Mastering Mixology in Aldarin at a relaxed pace without speedups. (Needs: Children of the Sun; 60 Herblore)", 45000, 0.0, "actions");
		add(Skill.HERBLORE, Style.FAST, 3, "Attack potions", "Add eyes of newt to guam potions (unf); move to antipoison at 5 and strength potions at 12. (Needs: Druidic Ritual)", 62500, 25.0, "potions made");
		add(Skill.HERBLORE, Style.FAST, 38, "Prayer potions", "Add snape grass to ranarr potions (unf). (Needs: Druidic Ritual)", 218750, 87.5, "potions made");
		add(Skill.HERBLORE, Style.FAST, 55, "Super strength potions", "Add limpwurt roots to kwuarm potions (unf). (Needs: Druidic Ritual)", 312500, 125.0, "potions made");
		add(Skill.HERBLORE, Style.FAST, 63, "Super restore potions", "Add red spiders' eggs to snapdragon potions (unf). (Needs: Druidic Ritual)", 356000, 142.5, "potions made");
		add(Skill.HERBLORE, Style.FAST, 72, "Ranging potions", "Add wine of zamorak to dwarf weed potions (unf). (Needs: Druidic Ritual)", 406250, 162.5, "potions made");
		add(Skill.HERBLORE, Style.FAST, 81, "Saradomin brews", "Add crushed bird's nests to toadflax potions (unf); ancient brews at 85 give more XP per hour. (Needs: Druidic Ritual)", 450000, 180.0, "potions made");
		add(Skill.HERBLORE, Style.MINIGAME, 60, "Mastering Mixology", "Make mox, lye and aga pastes, mix potion bases and fill orders in Aldarin for resin and XP. (Needs: Children of the Sun; 60 Herblore)", 0, 0.0, "actions");
		add(Skill.HERBLORE, Style.MINIGAME, 81, "Mastering Mixology (all recipes)", "Play Mastering Mixology in Aldarin with all recipes unlocked, using all speedups. (Needs: Children of the Sun)", 70000, 0.0, "actions");
		add(Skill.HITPOINTS, Style.AFK, 1, "Gemstone Crab", "Fight the Gemstone Crab, an idle method that only needs re-engaging every 10 minutes. (Needs: Children of the Sun)", 0, 0.0, "actions");
		add(Skill.HITPOINTS, Style.AFK, 60, "Nightmare Zone", "Fight Nightmare Zone normal rumble with absorption potions, the most AFK combat training. (Needs: 5 Nightmare Zone quests; coin fee)", 0, 0.0, "actions");
		add(Skill.HITPOINTS, Style.AFK, 80, "Venator bow monkeys", "Range maniacal monkeys with a venator bow; low involvement and camp indefinitely. (Needs: Venator bow; amethyst arrows; Monkey Madness II chapter II; 80 Ranged)", 0, 0.0, "actions");
		add(Skill.HITPOINTS, Style.FAST, 1, "Witch's House quest", "Complete Witch's House (doable with rings of recoil) for a one-off Hitpoints XP reward.", 0, 6325.0, "quest");
		add(Skill.HITPOINTS, Style.FAST, 10, "Cannon ogres", "Cannon and range ogres east of Castle Wars; Hitpoints XP comes from damage dealt. (Needs: Dwarf Cannon)", 0, 0.0, "actions");
		add(Skill.HITPOINTS, Style.FAST, 45, "Chin maniacal monkeys", "Throw chinchompas at maniacal monkeys in Kruk's Dungeon; Hitpoints XP comes with Ranged XP. (Needs: Monkey Madness II chapter II started; 45 Ranged)", 0, 0.0, "actions");
		add(Skill.HITPOINTS, Style.FAST, 70, "Burst/barrage maniacal monkeys", "Ice Burst or Barrage maniacal monkeys on Ape Atoll for high Magic and Hitpoints XP. (Needs: 70+ Magic; Desert Treasure I; Monkey Madness II)", 0, 0.0, "actions");
		add(Skill.HITPOINTS, Style.MINIGAME, 60, "Nightmare Zone", "Fight bosses in Nightmare Zone normal rumble with absorption potions. (Needs: 5 Nightmare Zone quests; coin fee)", 0, 0.0, "actions");
		add(Skill.HITPOINTS, Style.MINIGAME, 70, "Pest Control", "Play Pest Control and spend points on Hitpoints XP. (Needs: Combat level 100+ recommended for veteran boat)", 0, 0.0, "games");
		add(Skill.HUNTER, Style.AFK, 5, "Bird house runs", "Build and fill bird houses on Fossil Island every 50 minutes, upgrading the log type as you level. (Needs: Bone Voyage; clockwork, log, hammer, chisel, seeds)", 20160, 112.0, "bird houses");
		add(Skill.HUNTER, Style.AFK, 44, "Drift net fishing", "Chase fish shoals into drift nets underwater on Fossil Island (trains Fishing too). (Needs: 44 Hunter, 47 Fishing, Bone Voyage, numulites for Ceto)", 60145, 52.3, "shoals");
		add(Skill.HUNTER, Style.AFK, 74, "Magic bird house runs", "Run magic bird houses on Fossil Island every 50 minutes (redwood bird houses at 89). (Needs: Bone Voyage)", 174420, 969.0, "bird houses");
		add(Skill.HUNTER, Style.FAST, 9, "Feldip weasels", "Catch Feldip weasels with a noose wand in the Feldip Hunter area. (Needs: Noose wand)", 13000, 0.0, "catches");
		add(Skill.HUNTER, Style.FAST, 21, "Red crabs", "Trap red crabs at the Pandemonium using two traps and fish offcuts. (Needs: Partial Pandemonium; 10 Construction)", 31000, 0.0, "catches");
		add(Skill.HUNTER, Style.FAST, 43, "Spotted kebbits (falconry)", "Hunt spotted kebbits with a rented gyr falcon at the Piscatoris falconry area. (Needs: 500 coins falcon rental; empty weapon, shield and hand slots)", 60000, 0.0, "catches");
		add(Skill.HUNTER, Style.FAST, 49, "Razor-backed kebbits", "Track razor-backed kebbits with a noose wand in the Piscatoris Hunter area. (Needs: Noose wand; stamina potions recommended)", 130000, 348.5, "catches");
		add(Skill.HUNTER, Style.FAST, 73, "Black chinchompas", "Box trap black chinchompas in the Wilderness Hunter area (Wilderness level 32-36). (Needs: Partial Eagles' Peak; Wilderness risk)", 145000, 0.0, "catches");
		add(Skill.MAGIC, Style.AFK, 1, "Splashing", "Autocast a combat spell with at least -64 Magic attack bonus on auto-retaliate, interacting every 20 minutes. (Needs: Gear totalling -64 Magic attack or lower, autocast staff)", 6600, 0.0, "actions");
		add(Skill.MAGIC, Style.AFK, 55, "High Level Alchemy", "Cast High Level Alchemy on profitable or cheap items, alone or while doing other activities. (Needs: 55 Magic, nature runes, items to alch)", 78000, 0.0, "actions");
		add(Skill.MAGIC, Style.AFK, 65, "Bake Pie", "Cast Bake Pie on uncooked pies on the Lunar spellbook. (Needs: Lunar Diplomacy, uncooked pies)", 113000, 0.0, "actions");
		add(Skill.MAGIC, Style.AFK, 80, "String Jewellery", "Cast String Jewellery on unstrung amulets on the Lunar spellbook, using a mud battlestaff. (Needs: Lunar Diplomacy (Lunar spellbook), unstrung amulets)", 150000, 0.0, "actions");
		add(Skill.MAGIC, Style.FAST, 4, "Enchant crossbow bolts", "Cast Enchant Crossbow Bolt on gem-tipped bolts (opal first), which can be done while running; can be very expensive. (Needs: Gem-tipped bolts and runes; Magic level for each bolt type)", 49500, 0.0, "actions");
		add(Skill.MAGIC, Style.FAST, 43, "Superheat Item", "Cast Superheat Item on ores for Magic plus passive Smithing XP. (Needs: 43 Magic, ores, nature and fire runes)", 97000, 53.0, "casts");
		add(Skill.MAGIC, Style.FAST, 62, "Smoke Burst maniacal monkeys", "Burst stacked maniacal monkeys in Kruk's Dungeon with Ancient Magicks. (Needs: Desert Treasure I, Monkey Madness II (Chapter II started))", 0, 0.0, "actions");
		add(Skill.MAGIC, Style.FAST, 70, "Ice Burst maniacal monkeys", "Cast Ice Burst on stacked maniacal monkeys in Kruk's Dungeon on Ape Atoll. (Needs: Desert Treasure I, Monkey Madness II (Chapter II started))", 310000, 0.0, "actions");
		add(Skill.MAGIC, Style.FAST, 94, "Ice Barrage maniacal monkeys", "Cast Ice Barrage on stacked maniacal monkeys in Kruk's Dungeon on Ape Atoll. (Needs: Desert Treasure I, Monkey Madness II (Chapter II started))", 400000, 0.0, "actions");
		add(Skill.MAGIC, Style.MINIGAME, 7, "Mage Training Arena", "Do the Enchanting and Alchemist rooms in the Mage Training Arena for more XP than normal enchanting or alching plus pizazz points.", 0, 0.0, "actions");
		add(Skill.MAGIC, Style.MINIGAME, 20, "Arceuus Library", "Find and deliver books in the Arceuus Library in Great Kourend for Magic XP, ideally with the Kourend Library plugin.", 0, 0.0, "actions");
		add(Skill.MINING, Style.AFK, 10, "Crashed stars", "Mine crashed shooting stars of a suitable tier wherever they land. (Needs: Shooting Stars activity)", 0, 0.0, "stardust");
		add(Skill.MINING, Style.AFK, 48, "Rubium rocks", "Mine rubium rocks in the Charred Dungeon on Charred Island. (Needs: 60 Sailing; rope; dragonfire protection recommended)", 39000, 0.0, "ores");
		add(Skill.MINING, Style.AFK, 60, "Lead rocks (Deepfin Mine)", "Mine lead rocks in Deepfin Mine; rates reach up to 90-100k xp/h with mining enhancers. (Needs: 67 Sailing; Superior or Expert mining gloves)", 0, 0.0, "ores");
		add(Skill.MINING, Style.AFK, 92, "Amethyst", "Mine amethyst crystals in the Mining Guild for slow but very AFK xp. (Needs: 92 Mining; Expert mining gloves and Varrock armour 4 recommended)", 20000, 0.0, "ores");
		add(Skill.MINING, Style.FAST, 1, "Copper and tin ore", "Mine copper and tin rocks anywhere, e.g. Lumbridge Swamp, until level 15.", 0, 0.0, "ores");
		add(Skill.MINING, Style.FAST, 15, "Iron ore (powermine)", "Powermine iron ore at a triangle of iron rocks, dropping ores; Mining Guild from 60. (Needs: Varrock armour 1 recommended)", 45000, 0.0, "ores");
		add(Skill.MINING, Style.FAST, 45, "3-tick granite", "3-tick mine granite at the Desert Quarry (or Cape Conch mine) using tick manipulation. (Needs: Tick manipulation; Varrock armour 2+; uncharged celestial ring recommended)", 87000, 0.0, "ores");
		add(Skill.MINING, Style.MINIGAME, 30, "Motherlode Mine", "Mine pay-dirt veins at Motherlode Mine and clean it in the water wheel for ores. (Needs: 30 Mining; prospector kit recommended)", 0, 0.0, "pay-dirt");
		add(Skill.MINING, Style.MINIGAME, 70, "Volcanic Mine", "Mine lava boulders in a team at Volcanic Mine on Fossil Island (world 323). (Needs: Bone Voyage; 50 Mining (70 recommended); numulites entry fee; food)", 64200, 0.0, "actions");
		add(Skill.MINING, Style.MINIGAME, 75, "Blast Mine", "Blast hard rock walls with dynamite at the Blast Mine in Lovakengj. (Needs: Dynamite, chisel, tinderbox; ring of endurance recommended)", 65000, 0.0, "actions");
		add(Skill.PRAYER, Style.AFK, 1, "Questing", "Complete The Restless Ghost, Priest in Peril, Recruitment Drive and Holy Grail for 14,531 Prayer XP (level 1 to 30). (Needs: Listed quests)", 0, 0.0, "actions");
		add(Skill.PRAYER, Style.AFK, 30, "Cam Torum calcified rocks", "Mine calcified rocks in the Cam Torum mine for bone shards to bank Prayer XP at a relatively AFK pace. (Needs: Access to Cam Torum (Varlamore))", 11000, 0.0, "actions");
		add(Skill.PRAYER, Style.FAST, 1, "Questing", "Complete The Restless Ghost, Priest in Peril, Recruitment Drive and Holy Grail for 14,531 Prayer XP (level 1 to 30). (Needs: Listed quests; Spirits of the Elid adds 8,000 XP)", 0, 0.0, "actions");
		add(Skill.PRAYER, Style.FAST, 30, "Libation bowl (Teomat)", "Offer blessed bone shards at the libation bowl in the Teomat using jugs of blessed wine (sunfire wine is faster). (Needs: 30 Prayer, blessed bone shards, blessed wine)", 670000, 0.0, "actions");
		add(Skill.PRAYER, Style.FAST, 48, "Libation bowl (Teomat)", "Offer blessed bone shards at the libation bowl in the Teomat with blessed or blessed sunfire wine. (Needs: Blessed bone shards, blessed wine)", 923000, 0.0, "actions");
		add(Skill.PRAYER, Style.FAST, 62, "Libation bowl (Teomat)", "Offer blessed bone shards at the libation bowl in the Teomat with blessed or blessed sunfire wine. (Needs: Blessed bone shards, blessed wine)", 1050000, 0.0, "actions");
		add(Skill.RANGED, Style.AFK, 1, "Gemstone Crab", "Range the Gemstone Crab in the Tlati Rainforest mines, re-finding it when it moves every 10 minutes. (Needs: Children of the Sun)", 0, 3.5, "damage");
		add(Skill.RANGED, Style.AFK, 70, "Nightmare Zone (absorptions)", "Range in Nightmare Zone with absorptions and super ranging for about 20 minutes per interaction (up to 140k-150k XP/hr at 99). (Needs: Nightmare Zone quests; Venator bow or magic shortbow (i) or adamant darts)", 0, 0.0, "actions");
		add(Skill.RANGED, Style.AFK, 80, "Venator bow at monkeys", "Use a Venator bow with amethyst or adamant arrows on maniacal monkeys in Kruk's Dungeon for lower-effort XP. (Needs: Venator bow, Monkey Madness II (Chapter II started))", 140000, 0.0, "actions");
		add(Skill.RANGED, Style.FAST, 1, "Questing", "Complete Shadow of the Storm (reward on Ranged), Death to the Dorgeshuun and Horror from the Deep for 16,662.5 XP (level 1 to 32). (Needs: Listed quests; Temple of Ikov adds 10,500 XP more)", 0, 0.0, "actions");
		add(Skill.RANGED, Style.FAST, 32, "Cannon Castle Wars ogres", "Set up a dwarf multicannon at the ogres east of Castle Wars and range them alongside it. (Needs: Dwarf Cannon)", 40000, 0.0, "actions");
		add(Skill.RANGED, Style.FAST, 45, "Chin maniacal monkeys", "Throw grey chinchompas at stacked maniacal monkeys in Kruk's Dungeon on Ape Atoll. (Needs: Monkey Madness II (Chapter II started), chinchompas)", 290000, 0.0, "actions");
		add(Skill.RANGED, Style.FAST, 65, "Red chins at maniacal monkeys", "Throw red or black chinchompas at stacked maniacal monkeys in Kruk's Dungeon with the best ranged gear. (Needs: Monkey Madness II (Chapter II started), red or black chinchompas)", 540000, 0.0, "actions");
		add(Skill.RANGED, Style.MINIGAME, 70, "Pest Control", "Play Pest Control (veteran boat at 100 combat) and spend commendation points on Ranged XP. (Needs: Combat level 40/70/100 for novice/intermediate/veteran boats)", 76600, 0.0, "actions");
		add(Skill.RANGED, Style.MINIGAME, 70, "Nightmare Zone", "Range in Nightmare Zone north of Yanille with absorptions and super ranging potions. (Needs: At least 5 of the quests Nightmare Zone uses)", 0, 0.0, "actions");
		add(Skill.RUNECRAFT, Style.AFK, 1, "Ourania Altar (ZMI)", "Craft runes at the Ourania Altar; a lower-effort alternative to lava runes. (Needs: Lunar Diplomacy; Ourania Teleport unlocked; 71 Magic strongly recommended)", 20423, 0.0, "actions");
		add(Skill.RUNECRAFT, Style.AFK, 50, "Ourania Altar (ZMI)", "Craft runes at the Ourania Altar; daeyalt essence increases XP. (Needs: Lunar Diplomacy; Ourania Teleport unlocked; 71 Magic strongly recommended)", 42077, 0.0, "actions");
		add(Skill.RUNECRAFT, Style.AFK, 75, "Ourania Altar (ZMI)", "Craft runes at the Ourania Altar; daeyalt essence increases XP. (Needs: Lunar Diplomacy; Ourania Teleport unlocked; 71 Magic strongly recommended)", 57270, 0.0, "actions");
		add(Skill.RUNECRAFT, Style.AFK, 85, "Ourania Altar (ZMI)", "Craft runes at the Ourania Altar; daeyalt essence increases XP. (Needs: Lunar Diplomacy; Ourania Teleport unlocked; 71 Magic strongly recommended)", 72526, 0.0, "actions");
		add(Skill.RUNECRAFT, Style.FAST, 1, "Talisman tiaras", "Craft talisman tiaras at the altars; elemental tiaras give the best rates.", 36300, 0.0, "tiaras");
		add(Skill.RUNECRAFT, Style.FAST, 23, "Lava runes", "Craft lava runes at the Fire Altar using earth runes and a binding necklace. (Needs: Fire Altar access; earth runes; binding necklace; fastest setup needs 82 Magic and Lunar Diplomacy)", 43200, 0.0, "actions");
		add(Skill.RUNECRAFT, Style.FAST, 50, "Lava runes (large pouch)", "Craft lava runes at the Fire Altar using medium and large essence pouches. (Needs: Fire Altar access; earth runes; binding necklace; fastest setup needs 82 Magic and Lunar Diplomacy; essence pouches)", 61300, 0.0, "actions");
		add(Skill.RUNECRAFT, Style.FAST, 75, "Lava runes (giant pouch)", "Craft lava runes at the Fire Altar using all essence pouches or the colossal pouch. (Needs: Fire Altar access; earth runes; binding necklace; fastest setup needs 82 Magic and Lunar Diplomacy; essence pouches)", 77600, 0.0, "actions");
		add(Skill.RUNECRAFT, Style.FAST, 85, "Lava runes (colossal pouch)", "Craft lava runes at the Fire Altar with a colossal pouch and Raiments of the Eye. (Needs: Fire Altar access; earth runes; binding necklace; fastest setup needs 82 Magic and Lunar Diplomacy; colossal pouch)", 100800, 0.0, "actions");
		add(Skill.RUNECRAFT, Style.MINIGAME, 27, "Guardians of the Rift", "Play Guardians of the Rift; each game gives Runecraft level x45 XP if 300+ energy gained. (Needs: Temple of the Eye; 27 Runecraft)", 0, 0.0, "games");
		add(Skill.RUNECRAFT, Style.MINIGAME, 40, "Guardians of the Rift", "Play Guardians of the Rift for XP plus rewards like the colossal pouch. (Needs: Temple of the Eye)", 25000, 0.0, "games");
		add(Skill.RUNECRAFT, Style.MINIGAME, 50, "Guardians of the Rift", "Play Guardians of the Rift for XP plus rewards like the colossal pouch. (Needs: Temple of the Eye)", 40000, 0.0, "games");
		add(Skill.RUNECRAFT, Style.MINIGAME, 75, "Guardians of the Rift", "Play Guardians of the Rift for XP plus rewards like Raiments of the Eye. (Needs: Temple of the Eye)", 50000, 0.0, "games");
		add(Skill.RUNECRAFT, Style.MINIGAME, 85, "Guardians of the Rift", "Play Guardians of the Rift in small coordinated teams. (Needs: Temple of the Eye)", 65000, 0.0, "games");
		add(Skill.SAILING, Style.AFK, 15, "Small shipwreck salvaging", "Salvage small shipwrecks with a salvaging hook; crewmates can help. (Needs: Salvaging hook)", 2000, 0.0, "actions");
		add(Skill.SAILING, Style.AFK, 35, "Barracuda shipwreck salvaging", "Salvage barracuda shipwrecks, using two hooks if you can. (Needs: Salvaging hook)", 9500, 0.0, "actions");
		add(Skill.SAILING, Style.AFK, 53, "Large shipwreck salvaging", "Salvage large shipwrecks, using two hooks if you can. (Needs: Salvaging hook)", 18000, 0.0, "actions");
		add(Skill.SAILING, Style.AFK, 64, "Pirate shipwreck salvaging", "Salvage pirate shipwrecks, using two hooks if you can. (Needs: Salvaging hook)", 35000, 0.0, "actions");
		add(Skill.SAILING, Style.AFK, 73, "Mercenary shipwreck salvaging", "Salvage mercenary shipwrecks, using two hooks if you can. (Needs: Salvaging hook)", 55000, 0.0, "actions");
		add(Skill.SAILING, Style.AFK, 87, "Merchant shipwreck salvaging", "Salvage merchant shipwrecks, using two hooks if you can. (Needs: Salvaging hook)", 70000, 0.0, "actions");
		add(Skill.SAILING, Style.FAST, 1, "Courier tasks and sail trimming", "Take courier tasks from the Port Sarim and Pandemonium notice boards and trim sails during wind gusts. (Needs: Pandemonium)", 0, 10.5, "sail trims");
		add(Skill.SAILING, Style.FAST, 30, "Barracuda Trials: Tempor Tantrum", "Run The Tempor Tantrum course in the Barracuda Trials. (Needs: 30 Sailing)", 19342, 634.0, "trials");
		add(Skill.SAILING, Style.FAST, 55, "Barracuda Trials: Jubbly Jive", "Run The Jubbly Jive course in the Barracuda Trials. (Needs: 55 Sailing)", 64746, 2392.0, "trials");
		add(Skill.SAILING, Style.FAST, 72, "Barracuda Trials: Gwenith Glide", "Run The Gwenith Glide course in the Barracuda Trials. (Needs: 72 Sailing)", 113538, 4100.0, "trials");
		add(Skill.SAILING, Style.FAST, 76, "Lunar Isle courier route", "Do courier deliveries between Lunar Isle and nearby ports like Aldarin and Red Rock. (Needs: 76 Sailing; The Red Reef for Red Rock tasks)", 130000, 0.0, "actions");
		add(Skill.SAILING, Style.MINIGAME, 30, "Barracuda Trials: Tempor Tantrum", "Run The Tempor Tantrum course in the Barracuda Trials. (Needs: 30 Sailing)", 19342, 634.0, "trials");
		add(Skill.SAILING, Style.MINIGAME, 55, "Barracuda Trials: Jubbly Jive", "Run The Jubbly Jive course in the Barracuda Trials. (Needs: 55 Sailing)", 64746, 2392.0, "trials");
		add(Skill.SAILING, Style.MINIGAME, 72, "Barracuda Trials: Gwenith Glide", "Run The Gwenith Glide course in the Barracuda Trials. (Needs: 72 Sailing)", 113538, 4100.0, "trials");
		add(Skill.SLAYER, Style.AFK, 1, "Cannon tasks with melee/ranged", "Do cannonable tasks with a dwarf multicannon plus ranged or melee from your best master. (Needs: Dwarf Cannon)", 0, 0.0, "tasks");
		add(Skill.SLAYER, Style.AFK, 50, "Nieve/Duradel cannon tasks", "Take Nieve or Duradel tasks, skipping bad ones with points, and cannon where possible. (Needs: 85 combat (Nieve) or 100 combat + Shilo Village (Duradel); Dwarf Cannon)", 0, 0.0, "tasks");
		add(Skill.SLAYER, Style.FAST, 1, "Highest available master", "Take tasks from the highest Slayer master your combat allows (Turael, Mazchna, Vannaka, Chaeldar, Konar, Nieve). (Needs: Mazchna 20 combat; Vannaka 40; Chaeldar 70 + Lost City; Konar 75; Nieve 85)", 0, 0.0, "tasks");
		add(Skill.SLAYER, Style.FAST, 50, "Duradel + barrage/cannon", "Take Duradel tasks; barrage stacked monsters (e.g. Catacombs of Kourend) or cannon small monsters. (Needs: 100 combat; Shilo Village; Ancient Magicks for barrage; Dwarf Cannon)", 0, 0.0, "tasks");
		add(Skill.SMITHING, Style.AFK, 4, "Bronze dart tips", "Smith bronze bars into dart tips at an anvil; requires little attention. (Needs: The Tourist Trap)", 13250, 0.0, "bars");
		add(Skill.SMITHING, Style.AFK, 34, "Steel dart tips", "Smith steel bars into dart tips at an anvil. (Needs: The Tourist Trap)", 39750, 0.0, "bars");
		add(Skill.SMITHING, Style.AFK, 54, "Mithril dart tips", "Smith mithril bars into dart tips at an anvil. (Needs: The Tourist Trap)", 53000, 0.0, "bars");
		add(Skill.SMITHING, Style.AFK, 74, "Adamant dart tips", "Smith adamantite bars into dart tips at an anvil. (Needs: The Tourist Trap)", 66250, 0.0, "bars");
		add(Skill.SMITHING, Style.AFK, 89, "Rune dart tips", "Smith runite bars into dart tips at an anvil. (Needs: The Tourist Trap)", 79500, 0.0, "bars");
		add(Skill.SMITHING, Style.FAST, 1, "Anvil smithing (Varrock)", "Smith the best bronze, iron then steel items (warhammers, platebodies) at the Varrock west anvil. (Needs: Bronze, iron and steel bars; hammer)", 13900, 12.5, "items");
		add(Skill.SMITHING, Style.FAST, 40, "Blast Furnace gold bars", "Smelt gold ore at the Blast Furnace in Keldagrim wearing goldsmith gauntlets and ice gloves. (Needs: Family Crest (goldsmith gauntlets); ice gloves; The Giant Dwarf; Blast Furnace fee)", 380000, 56.2, "gold ore");
		add(Skill.SMITHING, Style.MINIGAME, 15, "Giants' Foundry bronze/iron", "Make swords at Giants' Foundry in Kourend using a bronze and iron bar mix. (Needs: Sleeping Giants)", 0, 0.0, "actions");
		add(Skill.SMITHING, Style.MINIGAME, 30, "Giants' Foundry iron/steel", "Make swords at Giants' Foundry using an iron and steel bar mix. (Needs: Sleeping Giants)", 0, 0.0, "actions");
		add(Skill.SMITHING, Style.MINIGAME, 50, "Giants' Foundry steel/mithril", "Make swords at Giants' Foundry using a steel and mithril bar mix. (Needs: Sleeping Giants)", 0, 0.0, "actions");
		add(Skill.SMITHING, Style.MINIGAME, 70, "Giants' Foundry mithril/addy", "Make swords at Giants' Foundry using a mithril and adamantite bar mix. (Needs: Sleeping Giants)", 0, 0.0, "actions");
		add(Skill.SMITHING, Style.MINIGAME, 85, "Giants' Foundry addy/rune", "Make swords at Giants' Foundry using an adamantite and runite bar mix. (Needs: Sleeping Giants)", 0, 0.0, "actions");
		add(Skill.STRENGTH, Style.AFK, 1, "Questing", "Complete the early melee XP-reward quests from the wiki quest list, granting 20,375 Strength XP (to about 34 Strength). (Needs: Quests on the wiki early melee list)", 0, 0.0, "actions");
		add(Skill.STRENGTH, Style.AFK, 30, "Gemstone Crab", "Attack the Gemstone Crab in the Tlati Rainforest and re-find it each time it burrows (about every 10 minutes). (Needs: Children of the Sun)", 0, 3.5, "damage");
		add(Skill.STRENGTH, Style.AFK, 60, "Nightmare Zone (absorptions)", "Run normal rumble in Nightmare Zone north of Yanille with absorption potions, allowing about 20 minute breaks. (Needs: At least 5 of the quests Nightmare Zone uses)", 0, 0.0, "actions");
		add(Skill.STRENGTH, Style.AFK, 75, "Maniacal monkeys (auto-retaliate)", "Stand among maniacal monkeys in Kruk's Dungeon with Hallowfell on auto-retaliate for low-intensity XP. (Needs: 75 Attack (Hallowfell), Monkey Madness II)", 0, 0.0, "actions");
		add(Skill.STRENGTH, Style.FAST, 1, "Questing", "Complete the early melee XP-reward quests from the wiki quest list, granting 20,375 Strength XP (to about 34 Strength). (Needs: Quests on the wiki early melee list (e.g. Waterfall Quest))", 0, 0.0, "actions");
		add(Skill.STRENGTH, Style.FAST, 30, "Gemstone Crab", "Fight the Gemstone Crab in the Tlati Rainforest mines; it has infinite HP and moves to another mine every 10 minutes. (Needs: Children of the Sun)", 0, 3.5, "damage");
		add(Skill.STRENGTH, Style.FAST, 55, "Sulphur Naguas", "Kill Sulphur Naguas in Neypotzli with Protect from Melee, using glacial temotli or sulphur blades and the Blood Moon set if owned. (Needs: Partial Perilous Moons, 48 Slayer, 55 Attack, 43 Prayer)", 75000, 0.0, "actions");
		add(Skill.STRENGTH, Style.FAST, 75, "Maniacal monkeys (Hallowfell)", "Stack and kill maniacal monkeys in north-west Kruk's Dungeon on Ape Atoll with Hallowfell and strength gear. (Needs: 75 Attack (Hallowfell), Monkey Madness II)", 200000, 0.0, "actions");
		add(Skill.STRENGTH, Style.MINIGAME, 60, "Nightmare Zone", "Play Nightmare Zone north of Yanille on normal rumble with absorption potions and overloads or super combats. (Needs: At least 5 of the quests Nightmare Zone uses)", 0, 0.0, "actions");
		add(Skill.THIEVING, Style.AFK, 1, "Pickpocket men/women", "Low-risk pickpocketing of men or women for the first few levels.", 0, 0.0, "pickpockets");
		add(Skill.THIEVING, Style.AFK, 50, "Stealing valuables (Varlamore)", "Steal valuables in Varlamore with minimal effort; world hop for higher rates. (Needs: Children of the Sun)", 65000, 45.0, "steals");
		add(Skill.THIEVING, Style.AFK, 55, "Knights of Ardougne", "Lure a knight and pickpocket repeatedly without moving the mouse. (Needs: Medium Ardougne Diary recommended)", 86000, 0.0, "pickpockets");
		add(Skill.THIEVING, Style.AFK, 78, "Dorgesh-Kaan rich chests", "Picklock rich chests in Dorgesh-Kaan, banking or world hopping; low effort. (Needs: Death to the Dorgeshuun; lockpick)", 190000, 0.0, "chests");
		add(Skill.THIEVING, Style.FAST, 1, "Pickpocket men/women", "Pickpocket men or women (about 49 successes to reach level 5).", 0, 0.0, "pickpockets");
		add(Skill.THIEVING, Style.FAST, 5, "Bakery stall (East Ardougne)", "Steal from the East Ardougne bakery stall, standing under the baker to avoid guards.", 19200, 0.0, "steals");
		add(Skill.THIEVING, Style.FAST, 25, "Fruit stalls (Hosidius)", "Steal from the fruit stalls in Hosidius, using two stalls for best rates.", 34200, 0.0, "steals");
		add(Skill.THIEVING, Style.FAST, 45, "Blackjacking (Pollnivneach)", "Knock out and pickpocket bandits in Pollnivneach with a blackjack. (Needs: Partial The Feud; any blackjack)", 86000, 0.0, "pickpockets");
		add(Skill.THIEVING, Style.FAST, 49, "Stealing artefacts (Piscarilius)", "Steal artefacts in Port Piscarilius, luring guards away; bring a lockpick. (Needs: Lockpick)", 150000, 0.0, "heists");
		add(Skill.THIEVING, Style.FAST, 65, "Blackjacking (Pollnivneach)", "Knock out and pickpocket bandits in Pollnivneach with a blackjack; brews for best rates. (Needs: Partial The Feud; any blackjack)", 230000, 0.0, "pickpockets");
		add(Skill.THIEVING, Style.MINIGAME, 1, "Sorceress's Garden (Winter)", "Pick sq'irks in the Winter garden and turn the juice in to Osman. (Needs: Pestle and mortar; beer glass)", 0, 350.0, "glasses of juice");
		add(Skill.THIEVING, Style.MINIGAME, 25, "Sorceress's Garden (Spring)", "Pick sq'irks in the Spring garden and turn the juice in to Osman. (Needs: Pestle and mortar; beer glass)", 0, 1350.0, "glasses of juice");
		add(Skill.THIEVING, Style.MINIGAME, 45, "Sorceress's Garden (Autumn)", "Pick sq'irks in the Autumn garden and turn the juice in to Osman. (Needs: Pestle and mortar; beer glass)", 0, 2350.0, "glasses of juice");
		add(Skill.THIEVING, Style.MINIGAME, 65, "Sorceress's Garden (Summer)", "Pick sq'irks in the Summer garden and turn the juice in to Osman. (Needs: Pestle and mortar; beer glass)", 140000, 3000.0, "glasses of juice");
		add(Skill.THIEVING, Style.MINIGAME, 81, "Pyramid Plunder", "Loot urns and chests in Pyramid Plunder in Sophanem. (Needs: Access to Sophanem)", 190000, 0.0, "actions");
		add(Skill.THIEVING, Style.MINIGAME, 91, "Pyramid Plunder", "Loot urns and chests in Pyramid Plunder in Sophanem. (Needs: Access to Sophanem)", 270000, 0.0, "actions");
		add(Skill.WOODCUTTING, Style.AFK, 1, "Regular trees", "Chop regular trees almost anywhere until level 15.", 0, 25.0, "logs");
		add(Skill.WOODCUTTING, Style.AFK, 15, "Oak trees", "Chop oak trees and drop or bank the logs until level 35.", 0, 37.5, "logs");
		add(Skill.WOODCUTTING, Style.AFK, 35, "Teak trees (no tick manip)", "Chop teak trees normally, dropping logs, with a forestry kit for events.", 34000, 85.0, "logs");
		add(Skill.WOODCUTTING, Style.AFK, 62, "Blisterwood tree", "Chop the blisterwood tree in Darkmeyer; it gives an audible cue when it stops. (Needs: Partial Sins of the Father)", 0, 76.0, "logs");
		add(Skill.WOODCUTTING, Style.AFK, 65, "Sulliuscep", "Chop sulliuscep caps in the Tar Swamp on Fossil Island (world 321). (Needs: Bone Voyage)", 82700, 0.0, "chops");
		add(Skill.WOODCUTTING, Style.AFK, 77, "Bloodwood trees", "Chop bloodwood trees in the Vampyrium, keeping three trees going at once. (Needs: The Blood Moon Rises)", 130000, 0.0, "logs");
		add(Skill.WOODCUTTING, Style.FAST, 1, "Regular trees", "Chop regular trees almost anywhere until level 15.", 0, 25.0, "logs");
		add(Skill.WOODCUTTING, Style.FAST, 15, "Oak trees", "Chop oak trees and drop or bank the logs until level 35.", 0, 37.5, "logs");
		add(Skill.WOODCUTTING, Style.FAST, 35, "1.5-tick teaks", "1.5-tick teak trees on Fossil Island using tick manipulation. (Needs: Bone Voyage; tick manipulation)", 93000, 85.0, "logs");
		add(Skill.WOODCUTTING, Style.MINIGAME, 1, "Forestry events", "Chop trees on a Forestry world (e.g. 444) and join Forestry events as they spawn. (Needs: Forestry kit recommended)", 0, 0.0, "actions");
	}

	private static void add(Skill skill, Style style, int minLevel, String name, String instruction,
		int xpPerHour, double xpPerAction, String unit)
	{
		TABLE.computeIfAbsent(skill, s -> new EnumMap<>(Style.class))
			.computeIfAbsent(style, s -> new ArrayList<>())
			.add(new Option(minLevel, name, instruction, "No teleport data yet", null, xpPerHour, xpPerAction, unit));
	}

	/**
	 * Best method for your current level in the chosen style. Falls back to the fast method when
	 * the style has nothing at your level (e.g. no minigame for that skill).
	 */
	static Option get(Skill skill, int currentLevel, int targetLevel, ZeroToMaxConfig.TrainingStyle trainingStyle)
	{
		Style style = trainingStyle == ZeroToMaxConfig.TrainingStyle.CHILL_AFK ? Style.AFK
			: trainingStyle == ZeroToMaxConfig.TrainingStyle.MINIGAME ? Style.MINIGAME : Style.FAST;

		Map<Style, List<Option>> byStyle = TABLE.get(skill);
		if (byStyle != null)
		{
			Option o = pick(byStyle.get(style), currentLevel);
			if (o == null && style != Style.FAST)
			{
				o = pick(byStyle.get(Style.FAST), currentLevel);
			}
			if (o != null)
			{
				return o;
			}
		}
		return new Option(1, "Pick a method",
			"Train " + skill.getName() + " to " + targetLevel + " (no method data for your level)",
			"No teleport data yet", null, 0, 0, "actions");
	}

	private static Option pick(List<Option> options, int level)
	{
		if (options == null)
		{
			return null;
		}
		Option best = null;
		for (Option o : options)
		{
			if (o.getMinLevel() <= level && (best == null || o.getMinLevel() >= best.getMinLevel()))
			{
				best = o;
			}
		}
		return best;
	}
}
