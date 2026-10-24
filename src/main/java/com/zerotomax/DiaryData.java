/*
 * The diary data in this file is derived from Quest Helper
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
import net.runelite.api.gameval.VarbitID;

/**
 * Requirements for every achievement diary, and where to claim each reward.
 *
 * GENERATED from Quest Helper's diary helpers. A diary counts as done when its reward
 * varbit is set, i.e. once the reward has been claimed from the diary NPC.
 *
 * Row format: id, area, tier name, tier rank (0 = Easy), reward varbit,
 *   "SKILL:level,...", "PREREQ_QUEST,PREREQ_QUEST*..." (* = only needs to be started),
 *   quest points, combat level, has extra requirements this plugin can't check,
 *   claim NPC x, y, plane, claim instruction.
 */
final class DiaryData
{
	private DiaryData()
	{
	}

	static List<DiaryInfo> all()
	{
		List<DiaryInfo> list = new ArrayList<>();
		add(list, "ARDOUGNE_EASY", "Ardougne", "Easy", 0, VarbitID.ARDOUGNE_EASY_REWARD, "THIEVING:5", "BIOHAZARD,RUNE_MYSTERIES", 0, 0, false, 2574, 3323, 0, "Talk to Two-pints in the Flying Horse Inn at East Ardougne to claim your reward!");
		add(list, "ARDOUGNE_MEDIUM", "Ardougne", "Medium", 1, VarbitID.ARDOUGNE_MEDIUM_REWARD, "AGILITY:39,FARMING:31,FIREMAKING:50,MAGIC:51,RANGED:21,STRENGTH:38,THIEVING:38", "ENLIGHTENED_JOURNEY,SEA_SLUG*,THE_HAND_IN_THE_SAND,TOWER_OF_LIFE,UNDERGROUND_PASS,WATCHTOWER*", 0, 0, false, 2574, 3323, 0, "Talk to Two-pints in the Flying Horse Inn at East Ardougne to claim your reward!");
		add(list, "ARDOUGNE_HARD", "Ardougne", "Hard", 2, VarbitID.ARDOUGNE_HARD_REWARD, "CONSTRUCTION:50,FARMING:70,HUNTER:59,MAGIC:66,RUNECRAFT:65,SMITHING:68,THIEVING:72", "LEGENDS_QUEST,MONKEY_MADNESS_I*,MOURNINGS_END_PART_II,WATCHTOWER", 0, 0, false, 2574, 3323, 0, "Talk to Two-pints in the Flying Horse Inn at East Ardougne to claim your reward!");
		add(list, "ARDOUGNE_ELITE", "Ardougne", "Elite", 3, VarbitID.ARDOUGNE_ELITE_REWARD, "AGILITY:90,COOKING:91,CRAFTING:10,FARMING:85,FISHING:81,FLETCHING:69,MAGIC:94,SMITHING:91,THIEVING:82", "DESERT_TREASURE_I,HAUNTED_MINE", 0, 0, false, 2574, 3323, 0, "Talk to Two-pints in the Flying Horse Inn at East Ardougne to claim your reward!");
		add(list, "DESERT_EASY", "Desert", "Easy", 0, VarbitID.DESERT_EASY_REWARD, "HUNTER:5,THIEVING:21", "", 0, 0, false, 3303, 3124, 0, "Talk to Jarr at the Shantay pass to claim your reward!");
		add(list, "DESERT_MEDIUM", "Desert", "Medium", 1, VarbitID.DESERT_MEDIUM_REWARD, "AGILITY:30,CONSTRUCTION:20,HERBLORE:36,HUNTER:47,SLAYER:22,THIEVING:25,WOODCUTTING:35", "EAGLES_PEAK,ENAKHRAS_LAMENT,SPIRITS_OF_THE_ELID,THE_GOLEM", 0, 0, false, 3303, 3124, 0, "Talk to Jarr at the Shantay pass to claim your reward!");
		add(list, "DESERT_HARD", "Desert", "Hard", 2, VarbitID.DESERT_HARD_REWARD, "AGILITY:70,ATTACK:50,DEFENCE:10,FIREMAKING:60,MAGIC:68,MINING:45,SLAYER:65,SMITHING:68,THIEVING:65", "CONTACT,DESERT_TREASURE_I,DREAM_MENTOR,THE_FEUD", 0, 0, false, 3303, 3124, 0, "Talk to Jarr at the Shantay pass to claim your reward!");
		add(list, "DESERT_ELITE", "Desert", "Elite", 3, VarbitID.DESERT_ELITE_REWARD, "CONSTRUCTION:78,COOKING:85,FLETCHING:95,MAGIC:94,PRAYER:85,THIEVING:91", "DESERT_TREASURE_I,ICTHLARINS_LITTLE_HELPER*,PRIEST_IN_PERIL,THE_TOURIST_TRAP", 0, 0, false, 3303, 3124, 0, "Talk to Jarr at the Shantay pass to claim your reward!");
		add(list, "FALADOR_EASY", "Falador", "Easy", 0, VarbitID.FALADOR_EASY_REWARD, "AGILITY:5,CONSTRUCTION:16,MINING:10,SMITHING:13", "DORICS_QUEST,RUNE_MYSTERIES,THE_KNIGHTS_SWORD", 0, 0, false, 2977, 3346, 0, "Congratulations! Talk to Sir Rebral in the courtyard of The White Knight Castle to claim your reward!");
		add(list, "FALADOR_MEDIUM", "Falador", "Medium", 1, VarbitID.FALADOR_MEDIUM_REWARD, "", "", 0, 0, false, 2977, 3346, 0, "Congratulations! Talk to Sir Rebral in the courtyard of The White Knight Castle to claim your reward!");
		add(list, "FALADOR_HARD", "Falador", "Hard", 2, VarbitID.FALADOR_HARD_REWARD, "AGILITY:50,DEFENCE:50,MINING:60,PRAYER:70,RUNECRAFT:56,SLAYER:72,THIEVING:50", "GRIM_TALES,HEROES_QUEST,THE_SLUG_MENACE", 0, 0, true, 2977, 3346, 0, "Congratulations! Talk to Sir Rebral in the courtyard of The White Knight Castle to claim your reward!");
		add(list, "FALADOR_ELITE", "Falador", "Elite", 3, VarbitID.FALADOR_ELITE_REWARD, "AGILITY:80,FARMING:91,HERBLORE:81,RUNECRAFT:88,WOODCUTTING:75", "WANTED", 0, 0, true, 2977, 3346, 0, "Congratulations! Talk to Sir Rebral in the courtyard of The White Knight Castle to claim your reward!");
		add(list, "FREMENNIK_EASY", "Fremennik", "Easy", 0, VarbitID.FREMENNIK_EASY_REWARD, "CRAFTING:23,FIREMAKING:15,HUNTER:11,MINING:20,SMITHING:20,THIEVING:5,WOODCUTTING:15", "DEATH_PLATEAU,THE_FREMENNIK_TRIALS,THE_GIANT_DWARF*,TROLL_STRONGHOLD", 0, 0, false, 2658, 3627, 0, "Talk to Thorodin south of Rellekka to claim your reward!");
		add(list, "FREMENNIK_MEDIUM", "Fremennik", "Medium", 1, VarbitID.FREMENNIK_MEDIUM_REWARD, "CONSTRUCTION:37,HUNTER:35,MINING:40,PRAYER:43,SLAYER:47,THIEVING:42", "BETWEEN_A_ROCK,EAGLES_PEAK,HORROR_FROM_THE_DEEP,OLAFS_QUEST", 0, 0, false, 2658, 3627, 0, "Talk to Thorodin south of Rellekka to claim your reward!");
		add(list, "FREMENNIK_HARD", "Fremennik", "Hard", 2, VarbitID.FREMENNIK_HARD_REWARD, "HERBLORE:66,HUNTER:55,MAGIC:72,MINING:70,SMITHING:60,THIEVING:75,WOODCUTTING:56", "EADGARS_RUSE,LUNAR_DIPLOMACY,THE_FREMENNIK_ISLES,THE_GIANT_DWARF*,THRONE_OF_MISCELLANIA", 0, 0, true, 2658, 3627, 0, "Talk to Thorodin south of Rellekka to claim your reward!");
		add(list, "FREMENNIK_ELITE", "Fremennik", "Elite", 3, VarbitID.FREMENNIK_ELITE_REWARD, "AGILITY:80,CRAFTING:80,HITPOINTS:70,PRAYER:43,RANGED:70,RUNECRAFT:82,SLAYER:83,STRENGTH:70", "", 0, 0, false, 2658, 3627, 0, "Talk to Thorodin south of Rellekka to claim your reward!");
		add(list, "KANDARIN_EASY", "Kandarin", "Easy", 0, VarbitID.KANDARIN_EASY_REWARD, "AGILITY:20,FARMING:13,FISHING:16", "", 0, 0, false, 2760, 3476, 0, "Talk to the 'Wedge' in front of Camelot Castle to claim your reward!");
		add(list, "KANDARIN_MEDIUM", "Kandarin", "Medium", 1, VarbitID.KANDARIN_MEDIUM_REWARD, "AGILITY:36,COOKING:43,FARMING:26,FISHING:46,FLETCHING:50,HERBLORE:48,MAGIC:45,MINING:30,RANGED:40,STRENGTH:22,THIEVING:47", "ALFRED_GRIMHANDS_BARCRAWL,ELEMENTAL_WORKSHOP_II,WATERFALL_QUEST", 0, 0, false, 2760, 3476, 0, "Talk to the 'Wedge' in front of Camelot Castle to claim your reward!");
		add(list, "KANDARIN_HARD", "Kandarin", "Hard", 2, VarbitID.KANDARIN_HARD_REWARD, "AGILITY:60,CONSTRUCTION:50,DEFENCE:70,FIREMAKING:65,FISHING:70,FLETCHING:70,MAGIC:56,PRAYER:70,SMITHING:75,STRENGTH:50,THIEVING:53,WOODCUTTING:60", "DESERT_TREASURE_I,TAI_BWO_WANNAI_TRIO", 0, 0, true, 2760, 3476, 0, "Talk to the 'Wedge' in front of Camelot Castle to claim your reward!");
		add(list, "KANDARIN_ELITE", "Kandarin", "Elite", 3, VarbitID.KANDARIN_ELITE_REWARD, "AGILITY:60,COOKING:80,CRAFTING:85,FARMING:79,FIREMAKING:85,FISHING:76,HERBLORE:86,MAGIC:87,SMITHING:90", "FAMILY_CREST,LUNAR_DIPLOMACY", 0, 0, false, 2760, 3476, 0, "Talk to the 'Wedge' in front of Camelot Castle to claim your reward!");
		add(list, "KARAMJA_EASY", "Karamja", "Easy", 0, VarbitID.ATJUN_EASY_REWARD, "AGILITY:15,MINING:40", "", 0, 0, false, 2810, 3192, 0, "Talk to Pirate Jackie the Fruit in Brimhaven to claim your reward!");
		add(list, "KARAMJA_MEDIUM", "Karamja", "Medium", 1, VarbitID.ATJUN_MED_REWARD, "AGILITY:12,COOKING:16,FARMING:27,FISHING:65,HUNTER:41,MINING:40,WOODCUTTING:50", "DRAGON_SLAYER_I,SHILO_VILLAGE,TAI_BWO_WANNAI_TRIO,THE_GRAND_TREE", 0, 0, false, 2810, 3192, 0, "Talk to Pirate Jackie the Fruit in Brimhaven to claim your reward!");
		add(list, "KARAMJA_HARD", "Karamja", "Hard", 2, VarbitID.ATJUN_HARD_REWARD, "AGILITY:53,COOKING:50,MINING:52,RANGED:42,RUNECRAFT:44,SLAYER:50,STRENGTH:50,THIEVING:50,WOODCUTTING:15", "TAI_BWO_WANNAI_TRIO", 0, 100, true, 2810, 3192, 0, "Talk to Pirate Jackie the Fruit in Brimhaven to claim your reward!");
		add(list, "KARAMJA_ELITE", "Karamja", "Elite", 3, VarbitID.KARAMJA_ELITE_REWARD, "FARMING:72,HERBLORE:87,RUNECRAFT:91", "", 0, 0, false, 2810, 3192, 0, "Talk to Pirate Jackie the Fruit in Brimhaven to claim your reward!");
		add(list, "KOUREND_EASY", "Kourend & Kebos", "Easy", 0, VarbitID.KOUREND_EASY_REWARD, "CONSTRUCTION:25,FISHING:20,HERBLORE:12,MINING:15,THIEVING:25", "DRUIDIC_RITUAL", 0, 0, false, 1647, 3665, 0, "Talk to Elise in the Kourend castle courtyard to claim your reward!");
		add(list, "KOUREND_MEDIUM", "Kourend & Kebos", "Medium", 1, VarbitID.KOUREND_MEDIUM_REWARD, "AGILITY:49,CRAFTING:30,FARMING:45,FIREMAKING:50,FISHING:43,HUNTER:53,MINING:42,WOODCUTTING:50", "EAGLES_PEAK,FAIRYTALE_II__CURE_A_QUEEN*,TALE_OF_THE_RIGHTEOUS,THE_ASCENT_OF_ARCEUUS,THE_DEPTHS_OF_DESPAIR,THE_FORSAKEN_TOWER,THE_QUEEN_OF_THIEVES", 0, 0, false, 1647, 3665, 0, "Talk to Elise in the Kourend castle courtyard to claim your reward!");
		add(list, "KOUREND_HARD", "Kourend & Kebos", "Hard", 2, VarbitID.KOUREND_HARD_REWARD, "FARMING:74,MAGIC:66,MINING:65,SLAYER:62,SMITHING:70,THIEVING:49,WOODCUTTING:60", "DREAM_MENTOR,THE_FORSAKEN_TOWER", 0, 85, false, 1647, 3665, 0, "Talk to Elise in the Kourend castle courtyard to claim your reward!");
		add(list, "KOUREND_ELITE", "Kourend & Kebos", "Elite", 3, VarbitID.KOUREND_ELITE_REWARD, "COOKING:84,CRAFTING:38,FARMING:85,FISHING:82,FLETCHING:40,MAGIC:90,MINING:38,RUNECRAFT:77,SLAYER:95,WOODCUTTING:90", "", 0, 0, false, 1647, 3665, 0, "Talk to Elise in the Kourend castle courtyard to claim your reward!");
		add(list, "LUMBRIDGE_EASY", "Lumbridge & Draynor", "Easy", 0, VarbitID.LUMBRIDGE_EASY_REWARD, "FIREMAKING:15,FISHING:15,MINING:15,RUNECRAFT:5,SLAYER:7,WOODCUTTING:15", "COOKS_ASSISTANT,RUNE_MYSTERIES", 0, 0, false, 3235, 3213, 0, "Talk to Hatius Cosaintus in Lumbridge to claim your reward!");
		add(list, "LUMBRIDGE_MEDIUM", "Lumbridge & Draynor", "Medium", 1, VarbitID.LUMBRIDGE_MEDIUM_REWARD, "AGILITY:20,CRAFTING:38,FISHING:30,HUNTER:42,MAGIC:31,RANGED:50,RUNECRAFT:23,STRENGTH:19,THIEVING:38,WOODCUTTING:30", "ANIMAL_MAGNETISM", 0, 70, false, 3235, 3213, 0, "Talk to Hatius Cosaintus in Lumbridge to claim your reward!");
		add(list, "LUMBRIDGE_HARD", "Lumbridge & Draynor", "Hard", 2, VarbitID.LUMBRIDGE_HARD_REWARD, "AGILITY:46,CRAFTING:70,FARMING:63,FIREMAKING:65,MAGIC:60,PRAYER:52,RUNECRAFT:59,WOODCUTTING:57", "ANOTHER_SLICE_OF_HAM,LOST_CITY,RECIPE_FOR_DISASTER,TEARS_OF_GUTHIX", 0, 0, false, 3235, 3213, 0, "Talk to Hatius Cosaintus in Lumbridge to claim your reward!");
		add(list, "LUMBRIDGE_ELITE", "Lumbridge & Draynor", "Elite", 3, VarbitID.LUMBRIDGE_ELITE_REWARD, "AGILITY:70,RANGED:70,RUNECRAFT:76,SMITHING:88,STRENGTH:70,THIEVING:78,WOODCUTTING:75", "", 0, 0, true, 3235, 3213, 0, "Talk to Hatius Cosaintus in Lumbridge to claim your reward!");
		add(list, "MORYTANIA_EASY", "Morytania", "Easy", 0, VarbitID.MORYTANIA_EASY_REWARD, "COOKING:12,CRAFTING:15,FARMING:47,SLAYER:15", "NATURE_SPIRIT,TROUBLED_TORTUGANS", 0, 20, true, 3464, 3480, 0, "Talk to Le-Sabre near Canifis to claim your reward!");
		add(list, "MORYTANIA_MEDIUM", "Morytania", "Medium", 1, VarbitID.MORYTANIA_MEDIUM_REWARD, "AGILITY:40,COOKING:40,HERBLORE:22,HUNTER:29,SLAYER:42,SMITHING:35,WOODCUTTING:45", "CABIN_FEVER,DWARF_CANNON,GHOSTS_AHOY,IN_AID_OF_THE_MYREQUE*,LAIR_OF_TARN_RAZORLOR", 0, 0, false, 3464, 3480, 0, "Talk to Le-Sabre near Canifis to claim your reward!");
		add(list, "MORYTANIA_HARD", "Morytania", "Hard", 2, VarbitID.MORYTANIA_HARD_REWARD, "AGILITY:71,CONSTRUCTION:50,DEFENCE:70,FARMING:53,FIREMAKING:50,MAGIC:66,MINING:55,PRAYER:70,SLAYER:58,WOODCUTTING:50", "CABIN_FEVER,DESERT_TREASURE_I,HAUNTED_MINE,IN_AID_OF_THE_MYREQUE,KINGS_RANSOM,NATURE_SPIRIT,THE_GREAT_BRAIN_ROBBERY", 0, 0, true, 3464, 3480, 0, "Talk to Le-Sabre near Canifis to claim your reward!");
		add(list, "MORYTANIA_ELITE", "Morytania", "Elite", 3, VarbitID.MORYTANIA_ELITE_REWARD, "ATTACK:70,CRAFTING:84,DEFENCE:70,FIREMAKING:80,FISHING:96,MAGIC:83,RANGED:70,SLAYER:85,STRENGTH:76", "IN_AID_OF_THE_MYREQUE,LUNAR_DIPLOMACY,SHADES_OF_MORTTON", 0, 0, true, 3464, 3480, 0, "Talk to Le-Sabre near Canifis to claim your reward!");
		add(list, "VARROCK_EASY", "Varrock", "Easy", 0, VarbitID.VARROCK_EASY_REWARD, "AGILITY:13,CRAFTING:8,FISHING:20,MINING:15,RUNECRAFT:9,THIEVING:5", "RUNE_MYSTERIES", 0, 0, false, 3225, 3415, 0, "Talk to Toby in Varrock to claim your reward!");
		add(list, "VARROCK_MEDIUM", "Varrock", "Medium", 1, VarbitID.VARROCK_MEDIUM_REWARD, "AGILITY:30,FARMING:30,FIREMAKING:40,MAGIC:25", "A_SOULS_BANE,ENLIGHTENED_JOURNEY,GARDEN_OF_TRANQUILLITY,GERTRUDES_CAT,THE_DIG_SITE,TREE_GNOME_VILLAGE", 32, 40, false, 3225, 3415, 0, "Talk to Toby in Varrock to claim your reward!");
		add(list, "VARROCK_HARD", "Varrock", "Hard", 2, VarbitID.VARROCK_HARD_REWARD, "AGILITY:51,CONSTRUCTION:50,FARMING:68,FIREMAKING:60,HUNTER:69,MAGIC:54,PRAYER:52,WOODCUTTING:60", "DESERT_TREASURE_I", 0, 0, false, 3225, 3415, 0, "Talk to Toby in Varrock to claim your reward!");
		add(list, "VARROCK_ELITE", "Varrock", "Elite", 3, VarbitID.VARROCK_ELITE_REWARD, "COOKING:95,FLETCHING:81,HERBLORE:90,MAGIC:86,RUNECRAFT:78,SMITHING:89", "DREAM_MENTOR,RUNE_MYSTERIES,THE_TOURIST_TRAP", 0, 0, true, 3225, 3415, 0, "Talk to Toby in Varrock to claim your reward!");
		add(list, "WESTERN_EASY", "Western Provinces", "Easy", 0, VarbitID.WESTERN_EASY_REWARD, "FLETCHING:20,HUNTER:9,MINING:15", "BIG_CHOMPY_BIRD_HUNTING,RUNE_MYSTERIES", 0, 40, false, 2466, 3460, 0, "Talk to the Elder gnome child in Gnome Stronghold to claim your reward!");
		add(list, "WESTERN_MEDIUM", "Western Provinces", "Medium", 1, VarbitID.WESTERN_MEDIUM_REWARD, "AGILITY:37,COOKING:42,FIREMAKING:35,FISHING:46,HUNTER:31,MINING:40,WOODCUTTING:35", "BIG_CHOMPY_BIRD_HUNTING,EAGLES_PEAK,MONKEY_MADNESS_I*,ONE_SMALL_FAVOUR*,THE_EYES_OF_GLOUPHRIE", 0, 70, false, 2466, 3460, 0, "Talk to the Elder gnome child in Gnome Stronghold to claim your reward!");
		add(list, "WESTERN_HARD", "Western Provinces", "Hard", 2, VarbitID.WESTERN_HARD_REWARD, "AGILITY:48,CONSTRUCTION:65,COOKING:62,FARMING:68,FIREMAKING:50,FISHING:62,HUNTER:69,MAGIC:64,MINING:70,RANGED:70,THIEVING:75,WOODCUTTING:50", "MOURNINGS_END_PART_I*,RECIPE_FOR_DISASTER,SWAN_SONG", 0, 100, false, 2466, 3460, 0, "Talk to the Elder gnome child in Gnome Stronghold to claim your reward!");
		add(list, "WESTERN_ELITE", "Western Provinces", "Elite", 3, VarbitID.WESTERN_ELITE_REWARD, "AGILITY:85,FARMING:75,FLETCHING:85,SLAYER:93,THIEVING:85", "BIG_CHOMPY_BIRD_HUNTING,MOURNINGS_END_PART_I*,REGICIDE*", 0, 40, false, 2466, 3460, 0, "Talk to the Elder gnome child in Gnome Stronghold to claim your reward!");
		add(list, "WILDERNESS_EASY", "Wilderness", "Easy", 0, VarbitID.WILDERNESS_EASY_REWARD, "AGILITY:15,MAGIC:21,MINING:15", "ENTER_THE_ABYSS", 0, 0, false, 3121, 3518, 0, "Talk to the Lesser Fanatic in Edgeville to claim your reward!");
		add(list, "WILDERNESS_MEDIUM", "Wilderness", "Medium", 1, VarbitID.WILDERNESS_MEDIUM_REWARD, "AGILITY:52,MAGIC:60,MINING:55,SLAYER:50,SMITHING:50,WOODCUTTING:61", "BETWEEN_A_ROCK*", 0, 0, false, 3121, 3518, 0, "Talk to Lesser Fanatic in Edgeville to claim your reward!");
		add(list, "WILDERNESS_HARD", "Wilderness", "Hard", 2, VarbitID.WILDERNESS_HARD_REWARD, "AGILITY:64,FISHING:53,HUNTER:67,MAGIC:66,SLAYER:68,SMITHING:75", "DEATH_PLATEAU*,MAGE_ARENA_I", 0, 0, false, 3121, 3518, 0, "Talk to Lesser Fanatic in Edgeville to claim your reward!");
		add(list, "WILDERNESS_ELITE", "Wilderness", "Elite", 3, VarbitID.WILDERNESS_ELITE_REWARD, "COOKING:90,FIREMAKING:75,FISHING:85,MAGIC:96,MINING:85,SLAYER:83,SMITHING:90,THIEVING:84,WOODCUTTING:75", "DESERT_TREASURE_I", 0, 0, false, 3121, 3518, 0, "Talk to Lesser Fanatic in Edgeville to claim your reward!");
		return list;
	}

	private static void add(List<DiaryInfo> list, String id, String area, String tier, int tierRank, int varbit,
		String skills, String prereqs, int questPoints, int combat, boolean extraRequirements,
		int x, int y, int plane, String claimText)
	{
		list.add(new DiaryInfo(id, area, tier, tierRank, varbit, skills, prereqs, questPoints, combat,
			extraRequirements, x, y, plane, claimText));
	}
}
