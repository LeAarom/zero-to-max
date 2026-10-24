/*
 * The quest data in this file is derived from Quest Helper
 * (https://github.com/Zoinkwiz/quest-helper), used under the licence below.
 *
 * BSD 2-Clause License
 *
 * Copyright (c) 2020, Zoinkwiz
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this
 *    list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package com.zerotomax;

import java.util.ArrayList;
import java.util.List;

/**
 * Quest requirements and start locations for every quest.
 *
 * GENERATED from the open-source Quest Helper plugin (Zoinkwiz, BSD 2-Clause licence,
 * https://github.com/Zoinkwiz/quest-helper). Skill levels, quest prerequisites, quest-point
 * and combat requirements, and the first-step coordinates were read from its source.
 *
 * Format of each row:
 *   questId (the RuneLite Quest enum name), difficulty, counts toward Quest cape,
 *   "SKILL:level,..." , "PREREQ_QUEST,PREREQ_QUEST*..." (a trailing * means only started is needed),
 *   quest points needed, combat level needed, start x, y, plane, start instruction.
 * A start of 0,0,0 means no start location was found (no GPS for that quest).
 * Skill boosts are ignored: your real level is used.
 */
final class QuestData
{
	private QuestData()
	{
	}

	static List<QuestInfo> all()
	{
		List<QuestInfo> list = new ArrayList<>();
		add(list, "ANIMAL_MAGNETISM", "INTERMEDIATE", true, "CRAFTING:19,RANGED:30,SLAYER:18,WOODCUTTING:35", "ERNEST_THE_CHICKEN,PRIEST_IN_PERIL,THE_RESTLESS_GHOST", 0, 0, 3093, 3357, 0, "Talk to Ava to begin the quest.");
		add(list, "ANOTHER_SLICE_OF_HAM", "INTERMEDIATE", true, "ATTACK:15,PRAYER:25", "DEATH_TO_THE_DORGESHUUN,THE_DIG_SITE,THE_GIANT_DWARF", 0, 0, 3209, 3216, 0, "Enter the Lumbridge Castle basement.");
		add(list, "AT_FIRST_LIGHT", "NOVICE", true, "CONSTRUCTION:27,HERBLORE:30,HUNTER:46", "CHILDREN_OF_THE_SUN,EAGLES_PEAK", 0, 0, 1554, 3033, 0, "Talk to Guildmaster Apatura in the Hunter Guild, south-west of Civitas illa Fortis.");
		add(list, "A_KINGDOM_DIVIDED", "EXPERIENCED", true, "AGILITY:54,CRAFTING:38,HERBLORE:50,MAGIC:35,MINING:42,THIEVING:52,WOODCUTTING:52", "TALE_OF_THE_RIGHTEOUS,THE_ASCENT_OF_ARCEUUS,THE_DEPTHS_OF_DESPAIR,THE_FORSAKEN_TOWER,THE_QUEEN_OF_THIEVES,X_MARKS_THE_SPOT", 0, 0, 1664, 3670, 0, "Talk to Martin Holt east of Kourend Castle.");
		add(list, "A_NIGHT_AT_THE_THEATRE", "MASTER", true, "", "A_TASTE_OF_HOPE", 0, 0, 3673, 3223, 0, "Speak with the Mysterious Stranger in Ver Sinhaza.");
		add(list, "A_PORCINE_OF_INTEREST", "NOVICE", true, "", "", 0, 0, 3086, 3251, 0, "Read the notice board in Draynor Village.");
		add(list, "A_RUFF_SITUATION", "NOVICE", true, "CRAFTING:15", "", 0, 0, 3037, 3457, 0, "Talk to Talia at the dog shelter south-west of the Edgeville Monastery.");
		add(list, "A_SOULS_BANE", "INTERMEDIATE", true, "", "", 0, 0, 3307, 3454, 0, "Talk to Launa east of Varrock.");
		add(list, "A_TAIL_OF_TWO_CATS", "INTERMEDIATE", true, "", "ICTHLARINS_LITTLE_HELPER", 0, 0, 2919, 3559, 0, "Talk to Unferth in north east Burthorpe.");
		add(list, "A_TASTE_OF_HOPE", "EXPERIENCED", true, "AGILITY:45,ATTACK:40,CRAFTING:48,HERBLORE:40,SLAYER:38", "DARKNESS_OF_HALLOWVALE", 0, 0, 3668, 3217, 0, "Talk to Garth outside the Theatre of Blood.");
		add(list, "BELOW_ICE_MOUNTAIN", "NOVICE", true, "", "", 16, 0, 3003, 3435, 0, "Talk to Willow outside Falador, south of the Ice Mountain dwarves.");
		add(list, "BENEATH_CURSED_SANDS", "MASTER", true, "AGILITY:62,CRAFTING:55,FIREMAKING:55", "CONTACT", 0, 0, 3311, 2779, 0, "Talk to Jamila in Sophanem to start the quest.");
		add(list, "BETWEEN_A_ROCK", "EXPERIENCED", true, "DEFENCE:30,MINING:40,SMITHING:50", "DWARF_CANNON,FISHING_CONTEST", 0, 0, 2732, 3713, 0, "Travel to the Keldagrim south mines and talk to Dondakan there.");
		add(list, "BIG_CHOMPY_BIRD_HUNTING", "INTERMEDIATE", true, "COOKING:30,FLETCHING:5,RANGED:30", "", 0, 0, 2631, 2982, 0, "Talk to Rantz in the east of Feldip Hills.");
		add(list, "BIOHAZARD", "NOVICE", true, "", "PLAGUE_CITY", 0, 0, 2592, 3336, 0, "Talk to Elena in her house west of the northern Ardougne bank.");
		add(list, "BLACK_KNIGHTS_FORTRESS", "INTERMEDIATE", true, "", "", 12, 0, 2955, 3339, 0, "Speak to Sir Amik Varze on the 2nd floor of Falador Castle.");
		add(list, "BONE_VOYAGE", "INTERMEDIATE", true, "", "THE_DIG_SITE", 0, 0, 3257, 3448, 0, "Talk to Curator Haig in the Varrock Museum.");
		add(list, "CABIN_FEVER", "EXPERIENCED", true, "AGILITY:42,CRAFTING:45,RANGED:40,SMITHING:50", "PIRATES_TREASURE,PRIEST_IN_PERIL,RUM_DEAL", 0, 0, 3678, 3494, 0, "Talk to Bill Teach in Port Phasmatys.");
		add(list, "CHILDREN_OF_THE_SUN", "NOVICE", true, "", "", 0, 0, 3225, 3426, 0, "Talk to Alina east of Varrock Square.");
		add(list, "CLIENT_OF_KOUREND", "NOVICE", true, "", "X_MARKS_THE_SPOT", 0, 0, 1824, 3690, 0, "Talk to Veos on the Port Piscarilius docks. You can travel to him by talking to Veos in Port Sarim.");
		add(list, "CLOCK_TOWER", "NOVICE", true, "", "", 0, 0, 2570, 3245, 0, "Talk to Brother Kojo in the Clock Tower to start the quest.");
		add(list, "COLD_WAR", "INTERMEDIATE", true, "AGILITY:30,CONSTRUCTION:34,CRAFTING:30,HUNTER:10,THIEVING:15", "", 0, 0, 2597, 3266, 0, "Talk to Larry at the Ardougne Zoo.");
		add(list, "CONTACT", "EXPERIENCED", true, "", "ICTHLARINS_LITTLE_HELPER,PRINCE_ALI_RESCUE", 0, 0, 3281, 2772, 0, "Talk to the High Priest in Sophanem.");
		add(list, "COOKS_ASSISTANT", "NOVICE", true, "", "", 0, 0, 3206, 3214, 0, "Give the Cook in Lumbridge Castle's kitchen the required items to finish the quest.");
		add(list, "CRAB_QUEST", "INTERMEDIATE", true, "FISHING:30,SAILING:40", "", 0, 0, 3055, 2639, 0, "Drink from the Bottle on the shore of Dognose Island.");
		add(list, "CREATURE_OF_FENKENSTRAIN", "INTERMEDIATE", true, "CRAFTING:20,THIEVING:25", "PRIEST_IN_PERIL,THE_RESTLESS_GHOST", 0, 0, 3492, 3474, 0, "Head to the Canifis bar and either buy the pickled brain for 50 coins, or telegrab it.");
		add(list, "CURRENT_AFFAIRS", "NOVICE", true, "FISHING:10,SAILING:22", "PANDEMONIUM", 0, 0, 2803, 3430, 0, "Talk to Arhein on the northern part of the Catherby Docks to start the quest.");
		add(list, "DARKNESS_OF_HALLOWVALE", "EXPERIENCED", true, "AGILITY:26,CONSTRUCTION:5,CRAFTING:32,MAGIC:33,MINING:20,STRENGTH:40,THIEVING:22", "IN_AID_OF_THE_MYREQUE", 0, 0, 3491, 3230, 0, "");
		add(list, "DEATH_ON_THE_ISLE", "INTERMEDIATE", true, "AGILITY:32,THIEVING:34", "CHILDREN_OF_THE_SUN", 0, 0, 1414, 2937, 0, "Talk to Patzi at the entrance to Villa Lucens on the island of Aldarin to start the quest.");
		add(list, "DEATH_PLATEAU", "NOVICE", true, "", "", 0, 0, 2896, 3529, 0, "Talk to Denulth in Burthorpe. You can get there with the Burthorpe minigame teleport or a games necklace.");
		add(list, "DEATH_TO_THE_DORGESHUUN", "INTERMEDIATE", true, "AGILITY:23,THIEVING:23", "THE_LOST_TRIBE", 0, 0, 3209, 3216, 0, "Enter the Lumbridge Castle basement.");
		add(list, "DEFENDER_OF_VARROCK", "EXPERIENCED", true, "HUNTER:52,SMITHING:55", "BELOW_ICE_MOUNTAIN,DEMON_SLAYER,FAMILY_CREST,GARDEN_OF_TRANQUILLITY,ROMEO__JULIET,SHIELD_OF_ARRAV,TEMPLE_OF_IKOV,WHAT_LIES_BELOW", 0, 0, 3283, 3501, 0, "Talk to Elias White in the Jolly Boar Inn north-east of Varrock.");
		add(list, "DEMON_SLAYER", "NOVICE", true, "", "", 0, 0, 3204, 3424, 0, "Talk to Aris in her tent in Varrock Square.");
		add(list, "DESERT_TREASURE_I", "MASTER", true, "FIREMAKING:50,MAGIC:50,SLAYER:10,THIEVING:53", "PLAGUE_CITY*,PRIEST_IN_PERIL,TEMPLE_OF_IKOV,THE_DIG_SITE,THE_TOURIST_TRAP,TROLL_STRONGHOLD,WATERFALL_QUEST", 0, 0, 3177, 3043, 0, "WARNING - IMPORTANT NOTE: At any point a Diamond of Azzanadra is in your inventory, a level 95 Stranger may appear and attack you with a poisoned dragon dagger.\\n\\n");
		add(list, "DESERT_TREASURE_II__THE_FALLEN_EMPIRE", "GRANDMASTER", true, "CONSTRUCTION:60,FIREMAKING:75,HERBLORE:62,MAGIC:75,RUNECRAFT:60,THIEVING:70", "BELOW_ICE_MOUNTAIN,DESERT_TREASURE_I,ENAKHRAS_LAMENT,HIS_FAITHFUL_SERVANTS,SECRETS_OF_THE_NORTH,TEMPLE_OF_THE_EYE,THE_GARDEN_OF_DEATH", 0, 0, 3511, 2971, 0, "Attempt to enter the Vault door north-east of Nardah.");
		add(list, "DEVIOUS_MINDS", "EXPERIENCED", true, "FLETCHING:50,RUNECRAFT:50,SMITHING:65", "DORICS_QUEST,ENTER_THE_ABYSS,TROLL_STRONGHOLD,WANTED", 0, 0, 3406, 3494, 0, "Start by talking to the Monk outside the Paterdomus temple, east of Varrock.");
		add(list, "DORICS_QUEST", "NOVICE", true, "", "", 0, 0, 2951, 3451, 0, "Bring Doric north of Falador all the required items. You can mine them all in the Dwarven Mines, or buy them from the Grand Exchange.");
		add(list, "DRAGON_SLAYER_I", "EXPERIENCED", true, "", "", 32, 0, 3190, 3360, 0, "Talk to the Guildmaster in the Champions' Guild, south of Varrock.");
		add(list, "DRAGON_SLAYER_II", "GRANDMASTER", true, "AGILITY:60,CONSTRUCTION:50,CRAFTING:62,HITPOINTS:50,MAGIC:75,MINING:68,SMITHING:70,THIEVING:60", "ANIMAL_MAGNETISM,A_TAIL_OF_TWO_CATS,BONE_VOYAGE,CLIENT_OF_KOUREND,DREAM_MENTOR,GHOSTS_AHOY,LEGENDS_QUEST", 200, 0, 2458, 2869, 0, "Talk to Alec Kincade outside the Myths' Guild.");
		add(list, "DREAM_MENTOR", "MASTER", true, "", "EADGARS_RUSE,LUNAR_DIPLOMACY", 0, 85, 2142, 3944, 0, "Enter the mine in the north east of Lunar Isle.");
		add(list, "DRUIDIC_RITUAL", "NOVICE", true, "", "", 0, 0, 2925, 3486, 0, "Talk to Kaqemeex in the Druids' Circle in Taverley.");
		add(list, "DWARF_CANNON", "NOVICE", true, "", "", 0, 0, 2567, 3460, 0, "Talk to Captain Lawgof near the Coal Truck Mining Site (north of Fishing Guild, West of McGrubor's Wood).");
		add(list, "EADGARS_RUSE", "INTERMEDIATE", true, "HERBLORE:31", "DRUIDIC_RITUAL,TROLL_STRONGHOLD", 0, 0, 2899, 3429, 0, "Talk to Sanfew upstairs in the Taverley herblore store.");
		add(list, "EAGLES_PEAK", "NOVICE", true, "HUNTER:27", "", 0, 0, 2607, 3264, 0, "Speak to Charlie in the Ardougne Zoo.");
		add(list, "ELEMENTAL_WORKSHOP_I", "NOVICE", true, "CRAFTING:20,MINING:20,SMITHING:20", "", 0, 0, 2716, 3482, 0, "Search the bookcase in the house in Seers' Village.");
		add(list, "ELEMENTAL_WORKSHOP_II", "INTERMEDIATE", true, "MAGIC:20,SMITHING:30", "ELEMENTAL_WORKSHOP_I", 0, 0, 3367, 3335, 0, "Search the marked bookcase in the Exam Centre in the Dig Site.");
		add(list, "ENAKHRAS_LAMENT", "EXPERIENCED", true, "CRAFTING:50,FIREMAKING:45,MAGIC:39,PRAYER:43", "", 0, 0, 3190, 2925, 0, "Before you begin, ensure that you have enough prayer points to use");
		add(list, "ENLIGHTENED_JOURNEY", "INTERMEDIATE", true, "CRAFTING:36,FARMING:30,FIREMAKING:20", "", 20, 0, 3047, 3236, 0, "Bank all weapons and armour you have, and go to Port Sarim to get a boat to Entrana.");
		add(list, "ERNEST_THE_CHICKEN", "NOVICE", true, "", "", 0, 0, 3109, 3329, 0, "Talk to Veronica outside of Draynor Manor.");
		add(list, "ETHICALLY_ACQUIRED_ANTIQUITIES", "NOVICE", true, "THIEVING:25", "CHILDREN_OF_THE_SUN,SHIELD_OF_ARRAV", 0, 0, 1721, 3165, 0, "Inspect the empty display case at the ");
		add(list, "FAIRYTALE_II__CURE_A_QUEEN", "EXPERIENCED", true, "FARMING:49,HERBLORE:57,THIEVING:40", "FAIRYTALE_I__GROWING_PAINS", 0, 0, 3078, 3256, 0, "Talk to Martin in the Draynor Market.");
		add(list, "FAIRYTALE_I__GROWING_PAINS", "INTERMEDIATE", true, "", "LOST_CITY,NATURE_SPIRIT", 0, 0, 3078, 3256, 0, "Talk to Martin in the Draynor Market.");
		add(list, "FALLEN_FROM_GRACE", "EXPERIENCED", true, "CRAFTING:60,MINING:53,RUNECRAFT:47,SAILING:62", "PANDEMONIUM", 0, 0, 2575, 2296, 0, "Talk to Ffion to be allowed onto Wyrmscraig island.");
		add(list, "FAMILY_CREST", "EXPERIENCED", true, "CRAFTING:40,MAGIC:59,MINING:40,SMITHING:40", "", 0, 0, 3280, 3402, 0, "Talk to Dimintheis in south east Varrock.");
		add(list, "FIGHT_ARENA", "INTERMEDIATE", true, "", "", 0, 0, 2565, 3199, 0, "Talk to Lady Servil, west-southwest of the Monastery south of Ardougne.");
		add(list, "FISHING_CONTEST", "NOVICE", true, "FISHING:10", "", 0, 0, 2821, 3486, 0, "Talk to Vestri just north of Catherby.");
		add(list, "FORGETTABLE_TALE", "INTERMEDIATE", true, "COOKING:22,FARMING:17", "FISHING_CONTEST,THE_GIANT_DWARF", 0, 0, 3140, 3504, 0, "Travel to Keldagrim.");
		add(list, "GARDEN_OF_TRANQUILLITY", "INTERMEDIATE", true, "FARMING:25", "CREATURE_OF_FENKENSTRAIN", 0, 0, 3230, 3478, 0, "Talk to Ellamaria east of Varrock Castle.");
		add(list, "GERTRUDES_CAT", "NOVICE", true, "", "", 0, 0, 3148, 3413, 0, "Talk to Gertrude west of Varrock to start the quest.");
		add(list, "GETTING_AHEAD", "INTERMEDIATE", true, "CONSTRUCTION:26,CRAFTING:30", "", 0, 0, 1248, 3686, 0, "Talk to Gordon south of the Farming Guild.");
		add(list, "GHOSTS_AHOY", "INTERMEDIATE", true, "AGILITY:25,COOKING:20", "PRIEST_IN_PERIL,THE_RESTLESS_GHOST", 0, 0, 3660, 3508, 0, "Enter Port Phasmatys.");
		add(list, "GOBLIN_DIPLOMACY", "NOVICE", true, "", "", 0, 0, 2954, 3497, 0, "You need three goblin mails, which you can find around the Goblin Village. The first is up the ladder in a crate in the south of the village.");
		add(list, "GRIM_TALES", "MASTER", true, "AGILITY:59,FARMING:45,HERBLORE:52,THIEVING:58,WOODCUTTING:71", "WITCHS_HOUSE", 0, 0, 2892, 3454, 0, "Talk to Sylas in Taverley.");
		add(list, "HAUNTED_MINE", "EXPERIENCED", true, "CRAFTING:35", "PRIEST_IN_PERIL", 0, 0, 3443, 3258, 0, "Talk to the Zealot outside the Abandoned Mine in south west Morytania.");
		add(list, "HAZEEL_CULT", "NOVICE", true, "", "", 0, 0, 2569, 3275, 0, "Talk to Ceril Carnillean in the south west of East Ardougne.");
		add(list, "HEROES_QUEST", "EXPERIENCED", true, "COOKING:53,FISHING:53,HERBLORE:25,MINING:50", "DRAGON_SLAYER_I,LOST_CITY,MERLINS_CRYSTAL,SHIELD_OF_ARRAV", 55, 0, 2904, 3511, 0, "Talk to Achietties outside the Heroes' Guild, south of Burthorpe.");
		add(list, "HOLY_GRAIL", "INTERMEDIATE", true, "ATTACK:20", "MERLINS_CRYSTAL", 0, 0, 2763, 3513, 0, "Talk to King Arthur in Camelot Castle to start the quest.");
		add(list, "HORROR_FROM_THE_DEEP", "INTERMEDIATE", true, "AGILITY:35", "ALFRED_GRIMHANDS_BARCRAWL", 0, 0, 2507, 3634, 0, "Talk to Larrissa outside ");
		add(list, "ICTHLARINS_LITTLE_HELPER", "INTERMEDIATE", true, "", "GERTRUDES_CAT", 0, 0, 3316, 2849, 0, "Talk to the Wanderer west of the Agility Pyramid.");
		add(list, "IMP_CATCHER", "NOVICE", true, "", "", 0, 0, 3103, 3159, 0, "Climb to the top of the Wizards' Tower and talk to Wizard Mizgog.");
		add(list, "IN_AID_OF_THE_MYREQUE", "INTERMEDIATE", true, "CRAFTING:25,MAGIC:7,MINING:15", "IN_SEARCH_OF_THE_MYREQUE", 0, 0, 3495, 3464, 0, "Enter the trapdoor behind the Canifis Pub.");
		add(list, "IN_SEARCH_OF_THE_MYREQUE", "INTERMEDIATE", true, "AGILITY:25", "NATURE_SPIRIT", 0, 0, 3503, 3477, 0, "Talk to Vanstrom Klause in the Canifis pub.");
		add(list, "JUNGLE_POTION", "NOVICE", true, "HERBLORE:3", "DRUIDIC_RITUAL", 0, 0, 2809, 3085, 0, "Talk to Trufitus in Tai Bwo Wannai.");
		add(list, "KINGS_RANSOM", "EXPERIENCED", true, "DEFENCE:65,MAGIC:45", "BLACK_KNIGHTS_FORTRESS,HOLY_GRAIL,MURDER_MYSTERY,ONE_SMALL_FAVOUR", 0, 0, 2741, 3557, 0, "Talk to Gossip, just south of the Sinclair Mansion.");
		add(list, "LAND_OF_THE_GOBLINS", "EXPERIENCED", true, "AGILITY:38,FISHING:40,HERBLORE:48,THIEVING:45", "ANOTHER_SLICE_OF_HAM,FISHING_CONTEST", 0, 0, 3209, 3216, 0, "Enter the Lumbridge Castle basement.");
		add(list, "LEGENDS_QUEST", "MASTER", true, "AGILITY:50,CRAFTING:50,HERBLORE:45,MAGIC:56,MINING:52,PRAYER:42,SMITHING:50,STRENGTH:50,THIEVING:50,WOODCUTTING:50", "FAMILY_CREST,HEROES_QUEST,SHILO_VILLAGE,UNDERGROUND_PASS,WATERFALL_QUEST", 107, 0, 2729, 3348, 0, "Talk to one of the guards outside the Legends' Guild.");
		add(list, "LOST_CITY", "INTERMEDIATE", true, "CRAFTING:31,WOODCUTTING:36", "", 0, 0, 3151, 3207, 0, "Talk to the Warrior south east of Draynor Village.");
		add(list, "LUNAR_DIPLOMACY", "EXPERIENCED", true, "CRAFTING:61,DEFENCE:40,FIREMAKING:49,HERBLORE:5,MAGIC:65,MINING:60,WOODCUTTING:55", "LOST_CITY,RUNE_MYSTERIES,SHILO_VILLAGE,THE_FREMENNIK_TRIALS", 0, 0, 2620, 3693, 0, "Talk to Lokar Searunner on Rellekka's docks.");
		add(list, "MAKING_FRIENDS_WITH_MY_ARM", "MASTER", true, "AGILITY:68,CONSTRUCTION:35,FIREMAKING:66,MINING:72", "COLD_WAR,MY_ARMS_BIG_ADVENTURE,ROMEO__JULIET,SWAN_SONG", 0, 0, 2839, 3690, 0, "Talk to Burntmeat in the Troll Stronghold's kitchen.");
		add(list, "MAKING_HISTORY", "INTERMEDIATE", true, "", "PRIEST_IN_PERIL,THE_RESTLESS_GHOST*", 0, 0, 2436, 3346, 0, "Talk to Jorral at the outpost south of the Tree Gnome Stronghold. You can teleport there with a Necklace");
		add(list, "MEAT_AND_GREET", "EXPERIENCED", true, "", "CHILDREN_OF_THE_SUN", 0, 0, 1753, 3074, 0, "Talk to Emelio in Outer Fortis at the south-eastern entrance of Civitas illa Fortis to start the quest.");
		add(list, "MERLINS_CRYSTAL", "INTERMEDIATE", true, "", "", 0, 0, 2763, 3513, 0, "Talk to King Arthur in Camelot Castle to start.");
		add(list, "MISTHALIN_MYSTERY", "NOVICE", true, "", "", 0, 0, 3237, 3155, 0, "Talk to Abigale in the south east corner of Lumbridge Swamp.");
		add(list, "MONKEY_MADNESS_I", "MASTER", true, "", "THE_GRAND_TREE,TREE_GNOME_VILLAGE", 0, 0, 2465, 3496, 0, "Talk to King Narnode Shareen in the Tree Gnome Stronghold.");
		add(list, "MONKEY_MADNESS_II", "GRANDMASTER", true, "AGILITY:55,CRAFTING:70,FIREMAKING:60,HUNTER:60,SLAYER:69,THIEVING:55", "ENLIGHTENED_JOURNEY,THE_EYES_OF_GLOUPHRIE,TROLL_STRONGHOLD,WATCHTOWER", 0, 0, 2465, 3496, 0, "Talk to King Narnode Shareen in the Tree Gnome Stronghold.");
		add(list, "MONKS_FRIEND", "NOVICE", true, "", "", 0, 0, 2607, 3211, 0, "Talk to Brother Omad in the monastery south of East Ardougne.");
		add(list, "MOUNTAIN_DAUGHTER", "INTERMEDIATE", true, "AGILITY:20", "", 0, 0, 2766, 3667, 0, "Use your rope on the boulder outside the Mountain Camp east of Rellekka.");
		add(list, "MOURNINGS_END_PART_I", "MASTER", true, "RANGED:60,THIEVING:50", "BIG_CHOMPY_BIRD_HUNTING,ROVING_ELVES,SHEEP_HERDER", 0, 0, 2207, 3159, 0, "Talk to Islwyn in Isafdar. If he's not at the marked location, try hopping worlds to find him here.");
		add(list, "MOURNINGS_END_PART_II", "MASTER", true, "", "MOURNINGS_END_PART_I", 0, 0, 2354, 3170, 0, "Talk to Arianwyn in Lletya.");
		add(list, "MURDER_MYSTERY", "NOVICE", true, "", "", 0, 0, 2741, 3561, 0, "Talk to the Guard in the Sinclair Manor north of Camelot.");
		add(list, "MY_ARMS_BIG_ADVENTURE", "EXPERIENCED", true, "FARMING:29,WOODCUTTING:10", "EADGARS_RUSE,JUNGLE_POTION,THE_FEUD", 0, 0, 2839, 3690, 0, "Enter the Troll Stronghold.");
		add(list, "NATURE_SPIRIT", "INTERMEDIATE", true, "", "PRIEST_IN_PERIL,THE_RESTLESS_GHOST", 0, 0, 3405, 3507, 0, "Talk to Drezel under the Paterdomus Temple.");
		add(list, "OBSERVATORY_QUEST", "INTERMEDIATE", true, "", "", 0, 0, 2442, 3186, 0, "Talk to the Observatory professor north of Castle Wars.");
		add(list, "OLAFS_QUEST", "INTERMEDIATE", true, "FIREMAKING:40,WOODCUTTING:50", "THE_FREMENNIK_TRIALS", 0, 0, 2722, 3727, 0, "Talk to Olaf Hradson north east of Rellekka.");
		add(list, "ONE_SMALL_FAVOUR", "EXPERIENCED", true, "AGILITY:36,CRAFTING:25,HERBLORE:18,SMITHING:30", "DRUIDIC_RITUAL,RUNE_MYSTERIES,SHILO_VILLAGE", 0, 0, 2836, 2983, 0, "Talk to Yanni Salika in Shilo Village. CKR fairy ring or take cart from Brimhaven.");
		add(list, "PANDEMONIUM", "NOVICE", true, "", "", 0, 0, 3025, 3208, 0, "Talk to Will or Anne on the docks of Port Sarim to start the quest.");
		add(list, "PERILOUS_MOONS", "MASTER", true, "CONSTRUCTION:10,FISHING:20,HUNTER:20,RUNECRAFT:20,SLAYER:48", "TWILIGHTS_PROMISE", 0, 0, 1435, 3124, 0, "Talk to Attala, stood south of Ralos' Rise.");
		add(list, "PIRATES_TREASURE", "NOVICE", true, "", "", 0, 0, 3053, 3251, 0, "Talk to Redbeard Frank in Port Sarim.");
		add(list, "PLAGUE_CITY", "NOVICE", true, "", "", 0, 0, 2568, 3333, 0, "Talk to Edmond in the north-west corner of East Ardougne.");
		add(list, "PRIEST_IN_PERIL", "NOVICE", true, "", "", 0, 0, 3222, 3473, 0, "Speak to King Roald in Varrock Castle.");
		add(list, "PRINCE_ALI_RESCUE", "NOVICE", true, "", "", 0, 0, 3298, 3163, 0, "Talk to Hassan in the Al Kharid Palace.");
		add(list, "PRYING_TIMES", "INTERMEDIATE", true, "SAILING:12,SMITHING:30", "PANDEMONIUM,THE_KNIGHTS_SWORD", 0, 0, 3050, 2966, 0, "Talk to 'Squawking' Steve Beanie behind the Pandemonium bar to start the quest. You can travel there via Captain Tobias on Port Sarim docks for 30gp.");
		add(list, "RAG_AND_BONE_MAN_I", "NOVICE", true, "", "", 0, 0, 3362, 3502, 0, "Talk to the Odd Old Man east of Varrock.");
		add(list, "RAG_AND_BONE_MAN_II", "EXPERIENCED", true, "DEFENCE:20,SLAYER:40", "RAG_AND_BONE_MAN_I,SKIPPY_AND_THE_MOGRES", 0, 0, 3362, 3502, 0, "Talk to the Odd Old Man north-east of Varrock.");
		add(list, "RATCATCHERS", "INTERMEDIATE", true, "", "ICTHLARINS_LITTLE_HELPER", 0, 0, 3151, 3413, 0, "Talk to Gertrude west of Varrock.");
		add(list, "RECIPE_FOR_DISASTER", "NOVICE", true, "AGILITY:48,COOKING:70,CRAFTING:42,FIREMAKING:20", "BIG_CHOMPY_BIRD_HUNTING,BIOHAZARD,COOKS_ASSISTANT,DEMON_SLAYER,DESERT_TREASURE_I,FAMILY_CREST,FISHING_CONTEST,GERTRUDES_CAT,GOBLIN_DIPLOMACY,HEROES_QUEST,HORROR_FROM_THE_DEEP,MONKEY_MADNESS_I,MURDER_MYSTERY,NATURE_SPIRIT,RUM_DEAL*,SHADOW_OF_THE_STORM,SHILO_VILLAGE,UNDERGROUND_PASS,WATERFALL_QUEST,WITCHS_HOUSE", 175, 0, 3209, 3215, 0, "Talk to the Lumbridge Cook.");
		add(list, "RECRUITMENT_DRIVE", "NOVICE", true, "", "BLACK_KNIGHTS_FORTRESS,DRUIDIC_RITUAL", 0, 0, 2955, 3339, 0, "Climb up the stairs to the first floor on the Falador Castle.");
		add(list, "REGICIDE", "EXPERIENCED", true, "AGILITY:56,CRAFTING:10", "UNDERGROUND_PASS", 0, 0, 2572, 3296, 0, "Go to the second floor of Ardougne Castle.");
		add(list, "ROMEO__JULIET", "NOVICE", true, "", "", 0, 0, 3211, 3422, 0, "Talk to Romeo in Varrock Square.");
		add(list, "ROVING_ELVES", "EXPERIENCED", true, "AGILITY:56", "REGICIDE,WATERFALL_QUEST", 0, 0, 2207, 3159, 0, "Talk to Islwyn in Isafdar. If he's not at the marked location, try hopping worlds to find him here.");
		add(list, "ROYAL_TROUBLE", "EXPERIENCED", true, "AGILITY:40,SLAYER:40", "THRONE_OF_MISCELLANIA", 0, 0, 2629, 3693, 0, "");
		add(list, "RUM_DEAL", "EXPERIENCED", true, "CRAFTING:42,FARMING:40,FISHING:50,PRAYER:47,SLAYER:42", "PRIEST_IN_PERIL,ZOGRE_FLESH_EATERS", 0, 0, 3680, 3537, 0, "Talk to Pirate Pete north east of the Ectofuntus.");
		add(list, "RUNE_MYSTERIES", "NOVICE", true, "", "", 0, 0, 3205, 3208, 0, "Talk to Duke Horacio on the first floor of Lumbridge castle.");
		add(list, "SCORPION_CATCHER", "INTERMEDIATE", true, "PRAYER:31", "ALFRED_GRIMHANDS_BARCRAWL", 0, 0, 2701, 3408, 0, "Climb to the top of the Sorcerer's Tower south of Seers' Village.");
		add(list, "SCRAMBLED", "INTERMEDIATE", true, "CONSTRUCTION:38,COOKING:36,SMITHING:35", "CHILDREN_OF_THE_SUN", 0, 0, 1247, 3167, 0, "Talk to Alan in Tal Teok Temple, north of Tal Teklan, to start the quest.");
		add(list, "SEA_SLUG", "INTERMEDIATE", true, "FIREMAKING:30", "", 0, 0, 2717, 3303, 0, "Talk to Caroline just north of Witchaven, east of East Ardougne.");
		add(list, "SECRETS_OF_THE_NORTH", "MASTER", true, "AGILITY:69,HUNTER:56,THIEVING:64", "DEVIOUS_MINDS,HAZEEL_CULT,MAKING_FRIENDS_WITH_MY_ARM,THE_GENERALS_SHADOW", 0, 0, 2570, 3276, 0, "Talk to the guard outside of the Carnillean Mansion in East Ardougne.");
		add(list, "SHADES_OF_MORTTON", "INTERMEDIATE", true, "CRAFTING:20,FIREMAKING:5,HERBLORE:15", "PRIEST_IN_PERIL", 0, 0, 3481, 3279, 0, "Search the shelf in the south building of Mort'ton.");
		add(list, "SHADOWS_OF_CUSTODIA", "EXPERIENCED", true, "CONSTRUCTION:41,FISHING:45,HUNTER:36,SLAYER:54", "CHILDREN_OF_THE_SUN", 0, 0, 1396, 3356, 0, "Read the noticeboard in the Auburnvale bar to start the quest.");
		add(list, "SHADOW_OF_THE_STORM", "INTERMEDIATE", true, "CRAFTING:30", "DEMON_SLAYER,THE_GOLEM", 0, 0, 3270, 3159, 0, "Talk to Father Reen outside Al Kharid bank.");
		add(list, "SHEEP_HERDER", "NOVICE", true, "", "", 0, 0, 2615, 3298, 0, "Talk to Councillor Halgrive outside the East Ardougne church.");
		add(list, "SHEEP_SHEARER", "NOVICE", true, "", "", 0, 0, 3190, 3273, 0, "Talk to Fred the Farmer north of Lumbridge to start the quest. Bring 20 balls of wool to autocomplete the quest.");
		add(list, "SHIELD_OF_ARRAV", "NOVICE", true, "", "", 0, 0, 3210, 3494, 0, "Talk to Reldo in the Varrock Castle library.");
		add(list, "SHILO_VILLAGE", "INTERMEDIATE", true, "AGILITY:32,CRAFTING:20", "JUNGLE_POTION", 0, 0, 2884, 2951, 0, "Talk to Mosol Rei east of Shilo Village in south Karamja to receive a wampum belt.");
		add(list, "SINS_OF_THE_FATHER", "MASTER", true, "AGILITY:52,ATTACK:50,CRAFTING:56,FLETCHING:60,MAGIC:49,SLAYER:50,WOODCUTTING:62", "A_TASTE_OF_HOPE,VAMPYRE_SLAYER", 0, 0, 3729, 3318, 0, "Talk to Veliaf in Slepe.");
		add(list, "SLEEPING_GIANTS", "INTERMEDIATE", true, "SMITHING:15", "", 0, 0, 3361, 3147, 0, "Attempt to Strike the Hill Giant at the entrance of the cave to start the quest.");
		add(list, "SONG_OF_THE_ELVES", "GRANDMASTER", true, "AGILITY:70,CONSTRUCTION:70,FARMING:70,HERBLORE:70,HUNTER:70,MINING:70,SMITHING:70,WOODCUTTING:70", "MAKING_HISTORY,MOURNINGS_END_PART_II", 0, 0, 2566, 3337, 0, "Talk to Edmond in East Ardougne.");
		add(list, "SPIRITS_OF_THE_ELID", "INTERMEDIATE", true, "MAGIC:33,MINING:37,RANGED:37,THIEVING:37", "", 0, 0, 3443, 2915, 0, "Speak to Awusah the Mayor in Nardah.");
		add(list, "SWAN_SONG", "MASTER", true, "COOKING:62,CRAFTING:40,FIREMAKING:42,FISHING:62,MAGIC:66,SMITHING:45", "GARDEN_OF_TRANQUILLITY,ONE_SMALL_FAVOUR", 100, 0, 2345, 3651, 0, "Talk to Herman Carnos outside the Fishing Colony.");
		add(list, "TAI_BWO_WANNAI_TRIO", "INTERMEDIATE", true, "AGILITY:15,COOKING:30,FIREMAKING:30,FISHING:5", "JUNGLE_POTION", 0, 0, 2782, 3087, 0, "Talk to Timfraku upstairs in his house in Tai Bwo Wannai.");
		add(list, "TALE_OF_THE_RIGHTEOUS", "INTERMEDIATE", true, "MINING:10,STRENGTH:16", "CLIENT_OF_KOUREND,X_MARKS_THE_SPOT", 0, 0, 1542, 3570, 0, "Talk to Phileas Rimor in Shayzien.");
		add(list, "TEARS_OF_GUTHIX", "INTERMEDIATE", true, "CRAFTING:20,FIREMAKING:49,MINING:20", "", 43, 0, 3169, 3172, 0, "Enter the hole to Lumbridge swamp.");
		add(list, "TEMPLE_OF_IKOV", "INTERMEDIATE", true, "RANGED:40,THIEVING:42", "", 0, 0, 2573, 3321, 0, "Talk to Lucien in the pub north of East Ardougne castle.");
		add(list, "TEMPLE_OF_THE_EYE", "INTERMEDIATE", true, "RUNECRAFT:10", "ENTER_THE_ABYSS", 0, 0, 3285, 3232, 0, "Talk to Wizard Persten east of the Al Kharid gate.");
		add(list, "THE_ASCENT_OF_ARCEUUS", "INTERMEDIATE", true, "HUNTER:12", "CLIENT_OF_KOUREND,X_MARKS_THE_SPOT", 0, 0, 1698, 3742, 0, "Talk to Mori in Arceuus.");
		add(list, "THE_BLOOD_MOON_RISES", "GRANDMASTER", true, "COOKING:72,CRAFTING:64,FLETCHING:70,HERBLORE:64,HUNTER:65,MAGIC:57,MINING:66,SLAYER:74,SMITHING:72,WOODCUTTING:74", "A_NIGHT_AT_THE_THEATRE,SINS_OF_THE_FATHER", 0, 0, 3697, 3184, 0, "Talk to Sarius Guile in the Icyene Graveyard to start the quest.");
		add(list, "THE_CORSAIR_CURSE", "INTERMEDIATE", true, "", "", 0, 0, 3030, 3273, 0, "Talk to Captain Tock north of Port Sarim.");
		add(list, "THE_CURSE_OF_ARRAV", "MASTER", true, "AGILITY:61,MINING:64,RANGED:62,SLAYER:37,STRENGTH:58,THIEVING:62", "DEFENDER_OF_VARROCK,TROLL_ROMANCE", 0, 0, 3505, 3037, 0, "Talk to Elias south of Ruins of Uzer to start the quest.");
		add(list, "THE_DEPTHS_OF_DESPAIR", "INTERMEDIATE", true, "AGILITY:18", "CLIENT_OF_KOUREND,X_MARKS_THE_SPOT", 0, 0, 1782, 3572, 0, "Talk to Lord Kandur Hosidius in the house west of the vinery.");
		add(list, "THE_DIG_SITE", "INTERMEDIATE", true, "AGILITY:10,HERBLORE:10,THIEVING:25", "", 0, 0, 3362, 3337, 0, "Talk to an Examiner in the Exam Centre south east of Varrock.");
		add(list, "THE_EYES_OF_GLOUPHRIE", "INTERMEDIATE", true, "CONSTRUCTION:5,MAGIC:46", "THE_GRAND_TREE", 0, 0, 2404, 3419, 0, "Go talk to Brimstail in his cave in west Tree Gnome Stronghold.");
		add(list, "THE_FEUD", "INTERMEDIATE", true, "THIEVING:30", "", 0, 0, 3304, 3211, 0, "Talk to Ali Morrisane in Al Kharid to start the quest.");
		add(list, "THE_FINAL_DAWN", "MASTER", true, "FLETCHING:52,RUNECRAFT:52,THIEVING:66", "PERILOUS_MOONS,THE_HEART_OF_DARKNESS", 0, 0, 1681, 3168, 0, "Talk to Servius in the Sunrise Palace in Civitas illa ");
		add(list, "THE_FORSAKEN_TOWER", "INTERMEDIATE", true, "", "CLIENT_OF_KOUREND,X_MARKS_THE_SPOT", 0, 0, 1483, 3747, 0, "Talk to Lady Vulcana Lovakengj in the south of Lovakengj.");
		add(list, "THE_FREMENNIK_EXILES", "MASTER", true, "CRAFTING:65,FISHING:60,MINING:60,RUNECRAFT:55,SLAYER:60,SMITHING:60", "HEROES_QUEST,LUNAR_DIPLOMACY,MOUNTAIN_DAUGHTER,THE_FREMENNIK_ISLES", 0, 0, 2662, 3673, 0, "Buy 2 kegs of beer from Thora in Rellekka.  She will be unavailable to trade later in the quest.");
		add(list, "THE_FREMENNIK_ISLES", "EXPERIENCED", true, "AGILITY:40,CONSTRUCTION:20,CRAFTING:46,WOODCUTTING:56", "THE_FREMENNIK_TRIALS", 0, 0, 2644, 3709, 0, "Talk to Mord Gunnars on a pier in north Rellekka.");
		add(list, "THE_FREMENNIK_TRIALS", "INTERMEDIATE", true, "CRAFTING:40,FLETCHING:25,WOODCUTTING:40", "", 0, 0, 2658, 3669, 0, "Talk to Brundt in Rellekka's longhall.");
		add(list, "THE_GARDEN_OF_DEATH", "INTERMEDIATE", true, "FARMING:20", "", 0, 0, 1314, 3470, 0, "Search the tent in the south east of the Kebos Lowlands.");
		add(list, "THE_GIANT_DWARF", "INTERMEDIATE", true, "CRAFTING:12,FIREMAKING:16,MAGIC:33,THIEVING:14", "", 0, 0, 2732, 3713, 0, "Speak to the Dwarven Boatman.");
		add(list, "THE_GOLEM", "INTERMEDIATE", true, "CRAFTING:20,THIEVING:25", "", 0, 0, 3485, 3088, 0, "Talk to the Golem in Uzer to start the quest.");
		add(list, "THE_GRAND_TREE", "INTERMEDIATE", true, "AGILITY:25", "", 0, 0, 2466, 3495, 0, "Talk to King Narnode Shareen in the Grand Tree");
		add(list, "THE_GREAT_BRAIN_ROBBERY", "EXPERIENCED", true, "CONSTRUCTION:30,CRAFTING:16,PRAYER:50", "CABIN_FEVER,CREATURE_OF_FENKENSTRAIN,RUM_DEAL", 0, 0, 3710, 3496, 0, "Cross the gangplank to Bill Teach's ship.");
		add(list, "THE_HAND_IN_THE_SAND", "INTERMEDIATE", true, "CRAFTING:49,THIEVING:17", "", 0, 0, 2551, 3099, 0, "Talk to Bert in west Yanille.");
		add(list, "THE_HEART_OF_DARKNESS", "EXPERIENCED", true, "AGILITY:46,MINING:55,SLAYER:48,THIEVING:48", "TWILIGHTS_PROMISE", 0, 0, 1454, 3173, 0, "Talk to Prince Itzla Arkan at the Teomat. You");
		add(list, "THE_IDES_OF_MILK", "NOVICE", true, "", "", 0, 0, 3171, 3277, 0, "Speak to Cassius by the pond north-west of Lumbridge to start the quest.");
		add(list, "THE_KNIGHTS_SWORD", "INTERMEDIATE", true, "MINING:10", "", 0, 0, 2978, 3341, 0, "Talk to the Squire in Falador Castle's courtyard.");
		add(list, "THE_LOST_TRIBE", "INTERMEDIATE", true, "AGILITY:13,MINING:17,THIEVING:13", "GOBLIN_DIPLOMACY,RUNE_MYSTERIES", 0, 0, 3205, 3208, 0, "Go up to the first floor of Lumbridge Castle.");
		add(list, "THE_PATH_OF_GLOUPHRIE", "EXPERIENCED", true, "AGILITY:45,RANGED:47,SLAYER:56,STRENGTH:60,THIEVING:56", "THE_EYES_OF_GLOUPHRIE,TREE_GNOME_VILLAGE,WATERFALL_QUEST", 0, 0, 0, 0, 0, "");
		add(list, "THE_QUEEN_OF_THIEVES", "INTERMEDIATE", true, "THIEVING:20", "CLIENT_OF_KOUREND,X_MARKS_THE_SPOT", 0, 0, 1796, 3781, 0, "Speak to Tomas Lawry in Port Piscarilius.");
		add(list, "THE_RED_REEF", "EXPERIENCED", true, "SAILING:52,SMITHING:48", "TROUBLED_TORTUGANS", 0, 0, 3186, 2405, 0, "Talk to Elder Raley at his home in The Great Conch to start the quest.");
		add(list, "THE_RESTLESS_GHOST", "NOVICE", true, "", "", 0, 0, 3243, 3206, 0, "Talk to Father Aereck in the Lumbridge Church.");
		add(list, "THE_RIBBITING_TALE_OF_A_LILY_PAD_LABOUR_DISPUTE", "NOVICE", true, "WOODCUTTING:15", "CHILDREN_OF_THE_SUN", 0, 0, 1683, 2973, 0, "Talk to Marcellus near the Locus Oasis south of Civitas illa Fortis. AJP Fairy ring is fastest.");
		add(list, "THE_SLUG_MENACE", "INTERMEDIATE", true, "CRAFTING:30,RUNECRAFT:30,SLAYER:30,THIEVING:30", "SEA_SLUG,WANTED", 0, 0, 2996, 3373, 0, "Talk to Sir Tiffy Cashien in Falador Park.");
		add(list, "THE_TOURIST_TRAP", "INTERMEDIATE", true, "FLETCHING:10,SMITHING:20", "", 0, 0, 3304, 3112, 0, "Talk to Irena south of the Shantay Pass.");
		add(list, "THRONE_OF_MISCELLANIA", "EXPERIENCED", true, "FARMING:10,MINING:30,SMITHING:35,WOODCUTTING:45", "HEROES_QUEST,THE_FREMENNIK_TRIALS", 0, 0, 2629, 3693, 0, "");
		add(list, "TOWER_OF_LIFE", "NOVICE", true, "CONSTRUCTION:10", "", 0, 0, 2637, 3218, 0, "Talk to Effigy outside the Tower of Life.");
		add(list, "TREE_GNOME_VILLAGE", "INTERMEDIATE", true, "", "", 0, 0, 2541, 3170, 0, "Follow the marked path to walk through the maze.");
		add(list, "TRIBAL_TOTEM", "INTERMEDIATE", true, "THIEVING:21", "", 0, 0, 2794, 3182, 0, "Talk to Kangai Mau in the Brimhaven food store.");
		add(list, "TROLL_ROMANCE", "INTERMEDIATE", true, "AGILITY:28", "TROLL_STRONGHOLD", 0, 0, 2839, 3690, 0, "Enter the Troll Stronghold.");
		add(list, "TROLL_STRONGHOLD", "INTERMEDIATE", true, "AGILITY:15", "DEATH_PLATEAU", 0, 0, 2895, 3528, 0, "Talk to Denulth in Burthorpe.");
		add(list, "TROUBLED_TORTUGANS", "EXPERIENCED", true, "CONSTRUCTION:48,CRAFTING:34,HUNTER:45,SAILING:45,SLAYER:51,WOODCUTTING:40", "PANDEMONIUM", 0, 0, 2962, 2605, 0, "Talk to the Injured Tortugan on the small island north-west of The Great Conch to start the quest.");
		add(list, "TWILIGHTS_PROMISE", "INTERMEDIATE", true, "", "CHILDREN_OF_THE_SUN", 0, 0, 3281, 3413, 0, "Talk to Regulus Cento outside Varrock's East Gate to travel to Varlamore.");
		add(list, "UNDERGROUND_PASS", "EXPERIENCED", true, "RANGED:25", "BIOHAZARD", 0, 0, 2572, 3296, 0, "Go to the second floor of Ardougne Castle and talk to King Lathas.");
		add(list, "VAMPYRE_SLAYER", "INTERMEDIATE", true, "", "", 0, 0, 3098, 3268, 0, "Talk to Morgan in the north of Draynor Village.");
		add(list, "WANTED", "INTERMEDIATE", true, "", "ENTER_THE_ABYSS,PRIEST_IN_PERIL,RECRUITMENT_DRIVE,THE_LOST_TRIBE", 32, 0, 2997, 3373, 0, "Talk to Sir Tiffy Cashien in Falador Park.");
		add(list, "WATCHTOWER", "INTERMEDIATE", true, "AGILITY:25,HERBLORE:14,MAGIC:15,MINING:40,THIEVING:15", "", 0, 0, 2548, 3119, 0, "Go to the top floor of the Watchtower north of Yanille and talk to the Watchtower Wizard.");
		add(list, "WATERFALL_QUEST", "INTERMEDIATE", true, "", "", 0, 0, 2521, 3495, 0, "Talk to Almera on top of Baxtorian Falls, south of Barbarian Outpost.");
		add(list, "WHAT_LIES_BELOW", "INTERMEDIATE", true, "MINING:42,RUNECRAFT:35", "RUNE_MYSTERIES", 0, 0, 3266, 3333, 0, "Talk to Rat Burgiss south of Varrock.");
		add(list, "WHILE_GUTHIX_SLEEPS", "GRANDMASTER", true, "AGILITY:66,FARMING:65,HERBLORE:65,HUNTER:62,MAGIC:67,THIEVING:72", "A_TAIL_OF_TWO_CATS,DEFENDER_OF_VARROCK,DREAM_MENTOR,FIGHT_ARENA,NATURE_SPIRIT,TEARS_OF_GUTHIX,TEMPLE_OF_THE_EYE,THE_HAND_IN_THE_SAND,THE_PATH_OF_GLOUPHRIE,WANTED", 180, 0, 2907, 3450, 0, "Talk to Ivy Sophista in Taverley south of the witch's house.");
		add(list, "WITCHS_HOUSE", "INTERMEDIATE", true, "", "", 0, 0, 2928, 3456, 0, "Talk to the Boy in Taverley to start the quest.");
		add(list, "WITCHS_POTION", "NOVICE", true, "", "", 0, 0, 2968, 3205, 0, "Talk to Hetty in Rimmington.");
		add(list, "X_MARKS_THE_SPOT", "NOVICE", true, "", "", 0, 0, 3228, 3242, 0, "Talk to Veos in The Sheared Ram pub in Lumbridge to start the quest.");
		add(list, "ZOGRE_FLESH_EATERS", "INTERMEDIATE", true, "HERBLORE:8,RANGED:30,SMITHING:4", "BIG_CHOMPY_BIRD_HUNTING,JUNGLE_POTION", 0, 0, 2447, 3049, 0, "Talk to Grish south of Castle Wars.");
		add(list, "ALFRED_GRIMHANDS_BARCRAWL", "MINIQUEST", false, "", "", 0, 0, 2544, 3568, 0, "Talk to a barbarian guard outside the Barbarian Agility Course at the Barbarian Outpost.");
		add(list, "CURSE_OF_THE_EMPTY_LORD", "MINIQUEST", false, "", "DESERT_TREASURE_I*,THE_RESTLESS_GHOST*", 0, 0, 2556, 3445, 0, "Talk to the Mysterious Ghost outside Glarial's Tomb.");
		add(list, "ENTER_THE_ABYSS", "MINIQUEST", false, "", "RUNE_MYSTERIES", 0, 0, 3102, 3557, 0, "Talk to the Mage");
		add(list, "HIS_FAITHFUL_SERVANTS", "MINIQUEST", false, "", "PRIEST_IN_PERIL", 0, 0, 3564, 3294, 0, "Talk to the Strange Old Man at Barrows east of Burgh de Rott.");
		add(list, "LAIR_OF_TARN_RAZORLOR", "MINIQUEST", false, "SLAYER:40", "HAUNTED_MINE", 0, 0, 0, 0, 0, "");
		add(list, "MAGE_ARENA_I", "MINIQUEST", false, "MAGIC:60", "", 0, 0, 3090, 3956, 0, "Pull the lever in the");
		add(list, "SKIPPY_AND_THE_MOGRES", "MINIQUEST", false, "COOKING:20", "", 0, 0, 2982, 3194, 0, "Right-click 'sober-up' on Skippy south west of Port Sarim.");
		add(list, "THE_GENERALS_SHADOW", "MINIQUEST", false, "", "CURSE_OF_THE_EMPTY_LORD,FIGHT_ARENA", 0, 0, 2718, 3628, 0, "Talk to General");
		add(list, "LEARNING_THE_ROPES", "NOVICE", true, "", "", 0, 0, 0, 0, 0, ""); // Tutorial Island quest (Oct 2025), not in Quest Helper yet
		return list;
	}

	private static void add(List<QuestInfo> list, String id, String difficulty, boolean cape, String skills,
		String prereqs, int questPoints, int combat, int x, int y, int plane, String startText)
	{
		list.add(new QuestInfo(id, difficulty, cape, skills, prereqs, questPoints, combat, x, y, plane, startText));
	}
}
