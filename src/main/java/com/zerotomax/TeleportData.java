/*
 * The teleport data in this file is derived from the Shortest Path plugin
 * (https://github.com/Skretzo/shortest-path), used under the licence below.
 *
 * BSD 2-Clause License
 *
 * Copyright (c) Skretzo and Shortest Path contributors
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
 * Every teleport spell, teleport item and fairy ring, with where it lands and what it needs.
 * GENERATED from Shortest Path's transport files (teleportation_spells, teleportation_items,
 * fairy_rings, teleportation_boxes, spirit_trees, teleportation_portals_poh). Row: kind, name, landing x, y, plane, item ids (any one), skill requirements,
 * quest requirements (RuneLite Quest names), varbit and varplayer requirements, note.
 */
final class TeleportData
{
	private TeleportData()
	{
	}

	static List<TeleportFinder.Teleport> all()
	{
		List<TeleportFinder.Teleport> list = new ArrayList<>();
		part0(list);
		part1(list);
		part2(list);
		part3(list);
		return list;
	}

	private static void part0(List<TeleportFinder.Teleport> list)
	{
		add(list, TeleportFinder.Kind.SPELL, "Varrock Teleport", 3213, 3424, 0, "", "MAGIC:25", "", "4070=0", "", "3 air rune + 1 fire rune + 1 law rune");
		add(list, TeleportFinder.Kind.SPELL, "Varrock Teleport: Grand Exchange", 3165, 3479, 0, "", "MAGIC:25", "", "4070=0;4480=1", "", "3 air rune + 1 fire rune + 1 law rune");
		add(list, TeleportFinder.Kind.SPELL, "Lumbridge Teleport", 3221, 3218, 0, "", "MAGIC:31", "", "4070=0", "", "3 air rune + 1 earth rune + 1 law rune");
		add(list, TeleportFinder.Kind.SPELL, "Falador Teleport", 2964, 3378, 0, "", "MAGIC:37", "", "4070=0", "", "3 air rune + 1 water rune + 1 law rune");
		add(list, TeleportFinder.Kind.SPELL, "Teleport to House", 1858, 7051, 0, "", "MAGIC:40", "", "4070=0;4744=0", "", "1 air rune + 1 earth rune + 1 law rune");
		add(list, TeleportFinder.Kind.SPELL, "Teleport to House (Inside)", 1858, 7051, 0, "", "MAGIC:40", "", "4070=0;4744=1", "", "1 air rune + 1 earth rune + 1 law rune");
		add(list, TeleportFinder.Kind.SPELL, "Teleport to House", 2953, 3224, 0, "", "MAGIC:40", "", "4070=0;2187=1;4744=1", "", "1 air rune + 1 earth rune + 1 law rune");
		add(list, TeleportFinder.Kind.SPELL, "Teleport to House (Outside)", 2953, 3224, 0, "", "MAGIC:40", "", "4070=0;2187=1;4744=0", "", "1 air rune + 1 earth rune + 1 law rune");
		add(list, TeleportFinder.Kind.SPELL, "Teleport to House", 2893, 3465, 0, "", "MAGIC:40", "", "4070=0;2187=2;4744=1", "", "1 air rune + 1 earth rune + 1 law rune");
		add(list, TeleportFinder.Kind.SPELL, "Teleport to House (Outside)", 2893, 3465, 0, "", "MAGIC:40", "", "4070=0;2187=2;4744=0", "", "1 air rune + 1 earth rune + 1 law rune");
		add(list, TeleportFinder.Kind.SPELL, "Teleport to House", 3340, 3003, 0, "", "MAGIC:40", "", "4070=0;2187=3;4744=1", "", "1 air rune + 1 earth rune + 1 law rune");
		add(list, TeleportFinder.Kind.SPELL, "Teleport to House (Outside)", 3340, 3003, 0, "", "MAGIC:40", "", "4070=0;2187=3;4744=0", "", "1 air rune + 1 earth rune + 1 law rune");
		add(list, TeleportFinder.Kind.SPELL, "Teleport to House", 2670, 3631, 0, "", "MAGIC:40", "", "4070=0;2187=4;4744=1", "", "1 air rune + 1 earth rune + 1 law rune");
		add(list, TeleportFinder.Kind.SPELL, "Teleport to House (Outside)", 2670, 3631, 0, "", "MAGIC:40", "", "4070=0;2187=4;4744=0", "", "1 air rune + 1 earth rune + 1 law rune");
		add(list, TeleportFinder.Kind.SPELL, "Teleport to House", 2757, 3178, 0, "", "MAGIC:40", "", "4070=0;2187=5;4744=1", "", "1 air rune + 1 earth rune + 1 law rune");
		add(list, TeleportFinder.Kind.SPELL, "Teleport to House (Outside)", 2757, 3178, 0, "", "MAGIC:40", "", "4070=0;2187=5;4744=0", "", "1 air rune + 1 earth rune + 1 law rune");
		add(list, TeleportFinder.Kind.SPELL, "Teleport to House", 2544, 3096, 0, "", "MAGIC:40", "", "4070=0;2187=6;4744=1", "", "1 air rune + 1 earth rune + 1 law rune");
		add(list, TeleportFinder.Kind.SPELL, "Teleport to House (Outside)", 2544, 3096, 0, "", "MAGIC:40", "", "4070=0;2187=6;4744=0", "", "1 air rune + 1 earth rune + 1 law rune");
		add(list, TeleportFinder.Kind.SPELL, "Teleport to House", 1743, 3517, 0, "", "MAGIC:40", "", "4070=0;2187=8;4744=1", "", "1 air rune + 1 earth rune + 1 law rune");
		add(list, TeleportFinder.Kind.SPELL, "Teleport to House (Outside)", 1743, 3517, 0, "", "MAGIC:40", "", "4070=0;2187=8;4744=0", "", "1 air rune + 1 earth rune + 1 law rune");
		add(list, TeleportFinder.Kind.SPELL, "Teleport to House", 3239, 6076, 0, "", "MAGIC:40", "", "4070=0;2187=9;4744=1", "", "1 air rune + 1 earth rune + 1 law rune");
		add(list, TeleportFinder.Kind.SPELL, "Teleport to House (Outside)", 3239, 6076, 0, "", "MAGIC:40", "", "4070=0;2187=9;4744=0", "", "1 air rune + 1 earth rune + 1 law rune");
		add(list, TeleportFinder.Kind.SPELL, "Teleport to House", 1422, 2963, 0, "", "MAGIC:40", "", "4070=0;2187=13;4744=1", "", "1 air rune + 1 earth rune + 1 law rune");
		add(list, TeleportFinder.Kind.SPELL, "Teleport to House (Outside)", 1422, 2963, 0, "", "MAGIC:40", "", "4070=0;2187=13;4744=0", "", "1 air rune + 1 earth rune + 1 law rune");
		add(list, TeleportFinder.Kind.SPELL, "Camelot Teleport", 2757, 3479, 0, "", "MAGIC:45", "", "4070=0", "", "5 air rune + 1 law rune");
		add(list, TeleportFinder.Kind.SPELL, "Camelot Teleport: Seers'", 2726, 3487, 0, "", "MAGIC:45", "", "4070=0;4477=1", "", "5 air rune + 1 law rune");
		add(list, TeleportFinder.Kind.SPELL, "Kourend Castle Teleport", 1641, 3673, 0, "", "MAGIC:48", "CLIENT_OF_KOUREND", "4070=0", "", "1 fire rune + 1 water rune + 2 law rune");
		add(list, TeleportFinder.Kind.SPELL, "Ardougne Teleport", 2662, 3305, 0, "", "MAGIC:51", "PLAGUE_CITY", "4070=0", "165=30", "2 water rune + 2 law rune");
		add(list, TeleportFinder.Kind.SPELL, "Civitas illa Fortis Teleport", 1681, 3136, 0, "", "MAGIC:54", "TWILIGHTS_PROMISE", "4070=0", "", "1 earth rune + 1 fire rune + 2 law rune");
		add(list, TeleportFinder.Kind.SPELL, "Watchtower Teleport", 2546, 3112, 0, "", "MAGIC:58", "WATCHTOWER", "4070=0", "212=14", "2 earth rune + 2 law rune");
		add(list, TeleportFinder.Kind.SPELL, "Watchtower Teleport: Yanille", 2584, 3097, 0, "", "MAGIC:58", "WATCHTOWER", "4070=0;4460=1", "212=14", "2 earth rune + 2 law rune");
		add(list, TeleportFinder.Kind.SPELL, "Trollheim Teleport", 2890, 3679, 0, "", "MAGIC:61", "EADGARS_RUSE", "4070=0", "", "2 fire rune + 2 law rune");
		add(list, TeleportFinder.Kind.SPELL, "Ape Atoll Teleport", 2797, 2798, 1, "", "MAGIC:64", "RECIPE_FOR_DISASTER__KING_AWOWOGEI", "4070=0", "", "2 fire rune + 2 water rune + 2 law rune + 1 banana");
		add(list, TeleportFinder.Kind.SPELL, "Paddewwa Teleport", 3097, 9881, 0, "", "MAGIC:54", "DESERT_TREASURE_I", "4070=1", "", "1 air rune + 1 fire rune + 2 law rune");
		add(list, TeleportFinder.Kind.SPELL, "Senntisten Teleport", 3320, 3337, 0, "", "MAGIC:60", "DESERT_TREASURE_I", "4070=1", "", "2 law rune + 1 soul rune");
		add(list, TeleportFinder.Kind.SPELL, "Kharyrll Teleport", 3494, 3473, 0, "", "MAGIC:66", "DESERT_TREASURE_I", "4070=1", "", "2 law rune + 1 blood rune");
		add(list, TeleportFinder.Kind.SPELL, "Lassar Teleport", 3004, 3470, 0, "", "MAGIC:72", "DESERT_TREASURE_I", "4070=1", "", "2 law rune + 4 water rune");
		add(list, TeleportFinder.Kind.SPELL, "Dareeyak Teleport", 2968, 3696, 0, "", "MAGIC:78", "DESERT_TREASURE_I", "4070=1", "", "2 air rune + 3 fire rune + 2 law rune");
		add(list, TeleportFinder.Kind.SPELL, "Carrallanger Teleport", 3158, 3666, 0, "", "MAGIC:84", "DESERT_TREASURE_I", "4070=1", "", "2 law rune + 2 soul rune");
		add(list, TeleportFinder.Kind.SPELL, "Annakarl Teleport", 3288, 3888, 0, "", "MAGIC:90", "DESERT_TREASURE_I", "4070=1", "", "2 law rune + 2 blood rune");
		add(list, TeleportFinder.Kind.SPELL, "Ghorrock Teleport", 2976, 3875, 0, "", "MAGIC:96", "DESERT_TREASURE_I", "4070=1", "", "2 law rune + 8 water rune");
		add(list, TeleportFinder.Kind.SPELL, "Moonclan Teleport", 2113, 3915, 0, "", "MAGIC:69", "LUNAR_DIPLOMACY", "4070=2", "", "2 earth rune + 2 astral rune + 1 law rune");
		add(list, TeleportFinder.Kind.SPELL, "Ourania Teleport", 2468, 3246, 0, "", "MAGIC:71", "LUNAR_DIPLOMACY", "4070=2", "", "6 earth rune + 2 astral rune + 1 law rune");
		add(list, TeleportFinder.Kind.SPELL, "Waterbirth Teleport", 2546, 3755, 0, "", "MAGIC:72", "LUNAR_DIPLOMACY", "4070=2", "", "1 water rune + 2 astral rune + 1 law rune");
		add(list, TeleportFinder.Kind.SPELL, "Barbarian Teleport", 2543, 3569, 0, "", "MAGIC:75", "LUNAR_DIPLOMACY", "4070=2", "", "3 fire rune + 2 astral rune + 2 law rune");
		add(list, TeleportFinder.Kind.SPELL, "Khazard Teleport", 2636, 3167, 0, "", "MAGIC:78", "LUNAR_DIPLOMACY", "4070=2", "", "4 water rune + 2 astral rune + 2 law rune");
		add(list, TeleportFinder.Kind.SPELL, "Fishing Guild Teleport", 2613, 3391, 0, "", "MAGIC:85", "LUNAR_DIPLOMACY", "4070=2", "", "10 water rune + 3 astral rune + 3 law rune");
		add(list, TeleportFinder.Kind.SPELL, "Catherby Teleport", 2801, 3449, 0, "", "MAGIC:87", "LUNAR_DIPLOMACY", "4070=2", "", "10 water rune + 3 astral rune + 3 law rune");
		add(list, TeleportFinder.Kind.SPELL, "Ice Plateau Teleport", 2975, 3938, 0, "", "MAGIC:89", "LUNAR_DIPLOMACY", "4070=2", "", "8 water rune + 3 astral rune + 3 law rune");
		add(list, TeleportFinder.Kind.SPELL, "Arceuus Library Teleport", 1632, 3837, 0, "", "MAGIC:6", "", "4070=3", "", "2 earth rune + 1 law rune");
		add(list, TeleportFinder.Kind.SPELL, "Draynor Manor Teleport", 3109, 3351, 0, "", "MAGIC:17", "", "4070=3", "", "1 earth rune + 1 water rune + 1 law rune");
		add(list, TeleportFinder.Kind.SPELL, "Battlefront Teleport", 1347, 3740, 0, "", "MAGIC:23", "", "4070=3", "", "1 earth rune + 1 fire rune + 1 law rune");
		add(list, TeleportFinder.Kind.SPELL, "Mind Altar Teleport", 2979, 3510, 0, "", "MAGIC:28", "", "4070=3", "", "1 law rune + 2 mind rune");
		add(list, TeleportFinder.Kind.SPELL, "Respawn Teleport", 3221, 3218, 0, "", "MAGIC:34", "", "4070=3;668=0;3910=0;621=0;10528=0;12310=0;9805=0", "", "1 law rune + 1 soul rune");
		add(list, TeleportFinder.Kind.SPELL, "Respawn Teleport", 2964, 3378, 0, "", "MAGIC:34", "", "4070=3;668=1", "", "1 law rune + 1 soul rune");
		add(list, TeleportFinder.Kind.SPELL, "Respawn Teleport", 2757, 3479, 0, "", "MAGIC:34", "", "4070=3;3910=1", "", "1 law rune + 1 soul rune");
		add(list, TeleportFinder.Kind.SPELL, "Respawn Teleport", 3095, 3469, 0, "", "MAGIC:34", "", "4070=3;621=1", "", "1 law rune + 1 soul rune");
		add(list, TeleportFinder.Kind.SPELL, "Respawn Teleport", 3265, 6077, 0, "", "MAGIC:34", "", "4070=3;668=0;3910=0;621=0;10528=0;12310=0;9805=0", "", "1 law rune + 1 soul rune");
		add(list, TeleportFinder.Kind.SPELL, "Respawn Teleport", 3151, 3636, 0, "", "MAGIC:34", "", "4070=3;10528=1", "", "1 law rune + 1 soul rune");
		add(list, TeleportFinder.Kind.SPELL, "Respawn Teleport", 1630, 3674, 0, "", "MAGIC:34", "", "4070=3;12310=1", "", "1 law rune + 1 soul rune");
		add(list, TeleportFinder.Kind.SPELL, "Respawn Teleport", 1682, 3135, 0, "", "MAGIC:34", "", "4070=3;9805=1", "", "1 law rune + 1 soul rune");
		add(list, TeleportFinder.Kind.SPELL, "Salve Graveyard Teleport", 3433, 3459, 0, "", "MAGIC:40", "PRIEST_IN_PERIL", "4070=3", "", "1 law rune + 2 soul rune");
		add(list, TeleportFinder.Kind.SPELL, "Fenkenstrain's Castle Teleport", 3548, 3529, 0, "", "MAGIC:48", "PRIEST_IN_PERIL", "4070=3", "", "1 earth rune + 1 law rune + 1 soul rune");
		add(list, TeleportFinder.Kind.SPELL, "West Ardougne Teleport", 2500, 3290, 0, "", "MAGIC:61", "BIOHAZARD", "4070=3", "", "2 law rune + 2 soul rune");
		add(list, TeleportFinder.Kind.SPELL, "Harmony Island Teleport", 3797, 2866, 0, "", "MAGIC:65", "THE_GREAT_BRAIN_ROBBERY", "4070=3", "", "1 law rune + 1 nature rune + 1 soul rune");
		add(list, TeleportFinder.Kind.SPELL, "Cemetery Teleport", 2981, 3762, 0, "", "MAGIC:71", "", "4070=3", "", "1 blood rune + 1 law rune + 1 soul rune");
		add(list, TeleportFinder.Kind.SPELL, "Barrows Teleport", 3564, 3315, 0, "", "MAGIC:83", "", "4070=3", "", "1 blood rune + 2 law rune + 2 soul rune");
		add(list, TeleportFinder.Kind.SPELL, "Ape Atoll Teleport", 2771, 9102, 0, "", "MAGIC:90", "MONKEY_MADNESS_I", "4070=3", "", "2 blood rune + 2 law rune + 2 soul rune");
		add(list, TeleportFinder.Kind.ITEM, "Ardougne cloak: Kandarin Monastery", 2607, 3221, 0, "13121,13122,13123,13124,20760", "", "", "4458=1", "", "");
		add(list, TeleportFinder.Kind.ITEM, "Ardougne cloak: Ardougne Farm", 2665, 3374, 0, "13122", "", "", "4459=1;6069<3", "", "consumable");
		add(list, TeleportFinder.Kind.ITEM, "Ardougne cloak: Ardougne Farm", 2665, 3374, 0, "13123", "", "", "4460=1;6069<5", "", "consumable");
		add(list, TeleportFinder.Kind.ITEM, "Ardougne cloak: Ardougne Farm", 2665, 3374, 0, "13124,20760", "", "", "4461=1", "", "");
		add(list, TeleportFinder.Kind.ITEM, "Karamja gloves: Gem Mine", 2840, 9387, 0, "11140,13103", "", "", "3611=2", "", "");
		add(list, TeleportFinder.Kind.ITEM, "Karamja gloves 4: Slayer Master", 2869, 2982, 1, "13103", "", "", "4566=1", "", "");
		add(list, TeleportFinder.Kind.ITEM, "Desert amulet 3: Teleport", 3420, 2917, 0, "13134,13135", "", "", "4485=1;4558=0", "", "consumable");
		add(list, TeleportFinder.Kind.ITEM, "Desert amulet 4: Nardah", 3426, 2927, 0, "13136", "", "", "4486=1", "", "");
		add(list, TeleportFinder.Kind.ITEM, "Desert amulet 4: Kalphite cave", 3322, 3122, 0, "13136", "", "", "4486=1", "", "");
		add(list, TeleportFinder.Kind.ITEM, "Morytania legs: Slime Pit", 3683, 9888, 0, "13112", "", "", "4487=1;4542<2", "", "consumable");
		add(list, TeleportFinder.Kind.ITEM, "Morytania legs: Slime Pit", 3683, 9888, 0, "13113,13114", "", "", "4488=1;4542<5", "", "consumable");
		add(list, TeleportFinder.Kind.ITEM, "Morytania legs: Slime Pit", 3683, 9888, 0, "13115", "", "", "4490=1", "", "");
		add(list, TeleportFinder.Kind.ITEM, "Morytania legs: Burgh de Rott", 3482, 3230, 0, "13114,13115", "", "", "4489=1", "", "");
		add(list, TeleportFinder.Kind.ITEM, "Fremennik sea boots: Teleport", 2643, 3675, 0, "13129", "", "", "4491=1;5005=0", "", "consumable");
		add(list, TeleportFinder.Kind.ITEM, "Fremennik sea boots: Teleport", 2643, 3675, 0, "13130", "", "", "4491=1;5005<3", "", "consumable");
		add(list, TeleportFinder.Kind.ITEM, "Fremennik sea boots: Teleport", 2643, 3675, 0, "13131", "", "", "4491=1;5005<5", "", "consumable");
		add(list, TeleportFinder.Kind.ITEM, "Fremennik sea boots: Teleport", 2643, 3675, 0, "13132", "", "", "4494=1", "", "");
		add(list, TeleportFinder.Kind.ITEM, "Kandarin headgear 3: Teleport", 2730, 3420, 0, "13139", "", "", "4477=1;4561=0", "", "consumable");
		add(list, TeleportFinder.Kind.ITEM, "Kandarin headgear 4: Teleport", 2730, 3420, 0, "13140", "", "", "4478=1", "", "");
		add(list, TeleportFinder.Kind.ITEM, "Wilderness sword 3: Teleport", 3377, 3890, 0, "13110", "", "", "4468=1;4541=0", "", "consumable");
		add(list, TeleportFinder.Kind.ITEM, "Wilderness sword 4: Teleport", 3377, 3890, 0, "13111", "", "", "4469=1", "", "");
		add(list, TeleportFinder.Kind.ITEM, "Western banner 3: Teleport", 2332, 3681, 0, "13143", "", "", "4473=1;4564=0", "", "consumable");
		add(list, TeleportFinder.Kind.ITEM, "Western banner 4: Teleport", 2332, 3681, 0, "13144", "", "", "4474=1", "", "");
		add(list, TeleportFinder.Kind.ITEM, "Explorer's ring 2: Teleport", 3052, 3291, 0, "13126", "", "", "4496=1;4552<3", "", "consumable");
		add(list, TeleportFinder.Kind.ITEM, "Explorer's ring: Teleport", 3052, 3291, 0, "13127,13128", "", "", "4497=1", "", "");
		add(list, TeleportFinder.Kind.ITEM, "Rada's blessing: Kourend Woodland", 1554, 3461, 0, "22941", "", "", "7925=1;7937<3", "", "consumable");
		add(list, TeleportFinder.Kind.ITEM, "Rada's blessing: Kourend Woodland", 1554, 3461, 0, "22943", "", "", "7926=1;7937<5", "", "consumable");
		add(list, TeleportFinder.Kind.ITEM, "Rada's blessing: Kourend Woodland", 1554, 3461, 0, "22945,22947", "", "", "7927=1", "", "");
		add(list, TeleportFinder.Kind.ITEM, "Rada's blessing: Mount Karuulm", 1310, 3796, 0, "22945", "", "", "7927=1;7938<3", "", "consumable");
		add(list, TeleportFinder.Kind.ITEM, "Rada's blessing: Mount Karuulm", 1310, 3796, 0, "22947", "", "", "7928=1", "", "");
		add(list, TeleportFinder.Kind.ITEM, "Games necklace: Barbarian Outpost", 2520, 3571, 0, "3853,3855,3857,3859,3861,3863,3865,3867", "", "", "", "", "consumable");
		add(list, TeleportFinder.Kind.ITEM, "Games necklace: Burthorpe", 2899, 3553, 0, "3853,3855,3857,3859,3861,3863,3865,3867", "", "", "", "", "consumable");
		add(list, TeleportFinder.Kind.ITEM, "Games necklace: Corporeal Beast", 2964, 4382, 2, "3853,3855,3857,3859,3861,3863,3865,3867", "", "", "", "", "consumable");
		add(list, TeleportFinder.Kind.ITEM, "Games necklace: Tears of Guthix", 3245, 9500, 2, "3853,3855,3857,3859,3861,3863,3865,3867", "", "", "451>1", "", "consumable");
		add(list, TeleportFinder.Kind.ITEM, "Games necklace: Wintertodt", 1631, 3940, 0, "3853,3855,3857,3859,3861,3863,3865,3867", "", "", "5619>0", "", "consumable");
		add(list, TeleportFinder.Kind.ITEM, "Ring of dueling: Emir's Arena", 3315, 3235, 0, "2566,2564,2562,2560,2558,2556,2554,2552", "", "", "", "", "consumable");
		add(list, TeleportFinder.Kind.ITEM, "Ring of dueling: Castle Wars", 2440, 3090, 0, "2566,2564,2562,2560,2558,2556,2554,2552", "", "", "", "", "consumable");
		add(list, TeleportFinder.Kind.ITEM, "Ring of dueling: Ferox Enclave", 3151, 3635, 0, "2566,2564,2562,2560,2558,2556,2554,2552", "", "", "", "", "consumable");
		add(list, TeleportFinder.Kind.ITEM, "Ring of dueling: Fortis Colosseum", 1793, 3107, 0, "2566,2564,2562,2560,2558,2556,2554,2552", "", "", "11410>8", "", "consumable");
		add(list, TeleportFinder.Kind.ITEM, "Combat bracelet: Warriors' Guild", 2882, 3547, 0, "11118,11120,11122,11124,11972,11974", "", "", "", "", "consumable");
		add(list, TeleportFinder.Kind.ITEM, "Combat bracelet: Champions' Guild", 3192, 3368, 0, "11118,11120,11122,11124,11972,11974", "", "", "", "", "consumable");
		add(list, TeleportFinder.Kind.ITEM, "Combat bracelet: Monastery", 3052, 3490, 0, "11118,11120,11122,11124,11972,11974", "", "", "", "", "consumable");
		add(list, TeleportFinder.Kind.ITEM, "Combat bracelet: Ranging Guild", 2653, 3439, 0, "11118,11120,11122,11124,11972,11974", "", "", "", "", "consumable");
		add(list, TeleportFinder.Kind.ITEM, "Skills necklace: Fishing Guild", 2611, 3390, 0, "11105,11107,11109,11111,11968,11970", "", "", "", "", "consumable");
		add(list, TeleportFinder.Kind.ITEM, "Skills necklace: Mining Guild", 3049, 9763, 0, "11105,11107,11109,11111,11968,11970", "", "", "", "", "consumable");
		add(list, TeleportFinder.Kind.ITEM, "Skills necklace: Crafting Guild", 2933, 3295, 0, "11105,11107,11109,11111,11968,11970", "", "", "", "", "consumable");
		add(list, TeleportFinder.Kind.ITEM, "Skills necklace: Cooking Guild", 3144, 3438, 0, "11105,11107,11109,11111,11968,11970", "", "", "", "", "consumable");
		add(list, TeleportFinder.Kind.ITEM, "Skills necklace: Woodcutting Guild", 1662, 3505, 0, "11105,11107,11109,11111,11968,11970", "", "", "", "", "consumable");
		add(list, TeleportFinder.Kind.ITEM, "Skills necklace: Farming Guild", 1248, 3719, 0, "11105,11107,11109,11111,11968,11970", "", "", "", "", "consumable");
		add(list, TeleportFinder.Kind.ITEM, "Skills necklace: Farming Guild", 1248, 3725, 0, "11105,11107,11109,11111,11968,11970", "FARMING:45", "", "", "", "consumable");
		add(list, TeleportFinder.Kind.ITEM, "Amulet of glory: Edgeville", 3087, 3496, 0, "1706,1708,1710,1712,11976,11978,10360,10358,10356,10354,11966,11964", "", "", "", "", "consumable");
		add(list, TeleportFinder.Kind.ITEM, "Amulet of glory: Karamja", 2918, 3176, 0, "1706,1708,1710,1712,11976,11978,10360,10358,10356,10354,11966,11964", "", "", "", "", "consumable");
		add(list, TeleportFinder.Kind.ITEM, "Amulet of glory: Draynor Village", 3105, 3251, 0, "1706,1708,1710,1712,11976,11978,10360,10358,10356,10354,11966,11964", "", "", "", "", "consumable");
		add(list, TeleportFinder.Kind.ITEM, "Amulet of glory: Al Kharid", 3293, 3163, 0, "1706,1708,1710,1712,11976,11978,10360,10358,10356,10354,11966,11964", "", "", "", "", "consumable");
		add(list, TeleportFinder.Kind.ITEM, "Amulet of glory: Edgeville", 3087, 3496, 0, "19707", "", "", "", "", "");
		add(list, TeleportFinder.Kind.ITEM, "Amulet of glory: Karamja", 2918, 3176, 0, "19707", "", "", "", "", "");
		add(list, TeleportFinder.Kind.ITEM, "Amulet of glory: Draynor Village", 3105, 3251, 0, "19707", "", "", "", "", "");
		add(list, TeleportFinder.Kind.ITEM, "Amulet of glory: Al Kharid", 3293, 3163, 0, "19707", "", "", "", "", "");
		add(list, TeleportFinder.Kind.ITEM, "Ring of wealth: Miscellania", 2534, 3862, 0, "11988,11986,11984,11982,11980,20790,20789,20788,20787,20786", "", "THRONE_OF_MISCELLANIA", "", "359=100", "consumable");
		add(list, TeleportFinder.Kind.ITEM, "Ring of wealth: Grand Exchange", 3163, 3478, 0, "11988,11986,11984,11982,11980,20790,20789,20788,20787,20786", "", "", "", "", "consumable");
		add(list, TeleportFinder.Kind.ITEM, "Ring of wealth: Falador Park", 2995, 3375, 0, "11988,11986,11984,11982,11980,20790,20789,20788,20787,20786", "", "", "", "", "consumable");
		add(list, TeleportFinder.Kind.ITEM, "Ring of wealth: Dondakan", 2824, 10168, 0, "11988,11986,11984,11982,11980,20790,20789,20788,20787,20786", "", "BETWEEN_A_ROCK", "299=10", "", "consumable");
		add(list, TeleportFinder.Kind.ITEM, "Slayer ring: Stronghold", 2431, 3422, 0, "11866,11867,11868,11869,11870,11871,11872,11873", "", "", "", "", "consumable");
		add(list, TeleportFinder.Kind.ITEM, "Slayer ring: Slayer Tower", 3421, 3537, 0, "11866,11867,11868,11869,11870,11871,11872,11873", "", "PRIEST_IN_PERIL", "", "", "consumable");
		add(list, TeleportFinder.Kind.ITEM, "Slayer ring: Fremennik", 2802, 9999, 0, "11866,11867,11868,11869,11870,11871,11872,11873", "", "", "", "", "consumable");
		add(list, TeleportFinder.Kind.ITEM, "Slayer ring: Tarn's Lair", 3185, 4601, 0, "11866,11867,11868,11869,11870,11871,11872,11873", "", "HAUNTED_MINE", "", "", "consumable");
		add(list, TeleportFinder.Kind.ITEM, "Slayer ring: Dark Beasts", 2028, 4636, 0, "11866,11867,11868,11869,11870,11871,11872,11873", "", "MOURNINGS_END_PART_II", "", "", "consumable");
		add(list, TeleportFinder.Kind.ITEM, "Slayer ring: Stronghold", 2431, 3422, 0, "21268", "", "", "", "", "");
		add(list, TeleportFinder.Kind.ITEM, "Slayer ring: Slayer Tower", 3421, 3537, 0, "21268", "", "PRIEST_IN_PERIL", "", "", "");
		add(list, TeleportFinder.Kind.ITEM, "Slayer ring: Fremennik", 2802, 9999, 0, "21268", "", "", "", "", "");
		add(list, TeleportFinder.Kind.ITEM, "Slayer ring: Tarn's Lair", 3185, 4601, 0, "21268", "", "HAUNTED_MINE", "", "", "");
		add(list, TeleportFinder.Kind.ITEM, "Slayer ring: Dark Beasts", 2028, 4636, 0, "21268", "", "MOURNINGS_END_PART_II", "", "", "");
		add(list, TeleportFinder.Kind.ITEM, "Digsite pendant: Digsite", 3341, 3445, 0, "11190,11191,11192,11193,11194", "", "", "", "", "consumable");
		add(list, TeleportFinder.Kind.ITEM, "Digsite pendant: Fossil Island", 3763, 3869, 1, "11190,11191,11192,11193,11194", "", "BONE_VOYAGE", "", "", "consumable");
		add(list, TeleportFinder.Kind.ITEM, "Digsite pendant: Lithkren", 3549, 10456, 0, "11190,11191,11192,11193,11194", "", "DRAGON_SLAYER_II", "", "", "consumable");
		add(list, TeleportFinder.Kind.ITEM, "Necklace of passage: Wizards' Tower", 3114, 3179, 0, "21146,21149,21151,21153,21155", "", "", "", "", "consumable");
		add(list, TeleportFinder.Kind.ITEM, "Necklace of passage: The Outpost", 2430, 3349, 0, "21146,21149,21151,21153,21155", "", "", "", "", "consumable");
		add(list, TeleportFinder.Kind.ITEM, "Necklace of passage: Eagle's Eyrie", 3405, 3158, 0, "21146,21149,21151,21153,21155", "", "", "", "", "consumable");
		add(list, TeleportFinder.Kind.ITEM, "Necklace of passage: Wyrmscraig", 2591, 2221, 0, "21146,21149,21151,21153,21155", "", "FALLEN_FROM_GRACE", "", "", "consumable");
		add(list, TeleportFinder.Kind.ITEM, "Burning amulet: Chaos Temple", 3234, 3634, 0, "21166,21169,21171,21173,21175", "", "", "", "", "consumable");
		add(list, TeleportFinder.Kind.ITEM, "Burning amulet: Bandit Camp", 3038, 3651, 0, "21166,21169,21171,21173,21175", "", "", "", "", "consumable");
		add(list, TeleportFinder.Kind.ITEM, "Burning amulet: Lava Maze", 3028, 3842, 0, "21166,21169,21171,21173,21175", "", "", "", "", "consumable");
	}

	private static void part1(List<TeleportFinder.Teleport> list)
	{
		add(list, TeleportFinder.Kind.ITEM, "Teleport to House tablet: Inside the house", 1858, 7051, 0, "8013", "", "", "4744=0", "", "consumable");
		add(list, TeleportFinder.Kind.ITEM, "Teleport to House tablet (Inside): Inside the house", 1858, 7051, 0, "8013", "", "", "4744=1", "", "consumable");
		add(list, TeleportFinder.Kind.ITEM, "Teleport to House tablet: Outside the house", 2953, 3224, 0, "8013", "", "", "2187=1;4744=1", "", "consumable");
		add(list, TeleportFinder.Kind.ITEM, "Teleport to House tablet (Outside): Outside the house", 2953, 3224, 0, "8013", "", "", "2187=1;4744=0", "", "consumable");
		add(list, TeleportFinder.Kind.ITEM, "Teleport to House tablet: Outside the house", 2893, 3465, 0, "8013", "", "", "2187=2;4744=1", "", "consumable");
		add(list, TeleportFinder.Kind.ITEM, "Teleport to House tablet (Outside): Outside the house", 2893, 3465, 0, "8013", "", "", "2187=2;4744=0", "", "consumable");
		add(list, TeleportFinder.Kind.ITEM, "Teleport to House tablet: Outside the house", 3340, 3003, 0, "8013", "", "", "2187=3;4744=1", "", "consumable");
		add(list, TeleportFinder.Kind.ITEM, "Teleport to House tablet (Outside): Outside the house", 3340, 3003, 0, "8013", "", "", "2187=3;4744=0", "", "consumable");
		add(list, TeleportFinder.Kind.ITEM, "Teleport to House tablet: Outside the house", 2670, 3631, 0, "8013", "", "", "2187=4;4744=1", "", "consumable");
		add(list, TeleportFinder.Kind.ITEM, "Teleport to House tablet (Outside): Outside the house", 2670, 3631, 0, "8013", "", "", "2187=4;4744=0", "", "consumable");
		add(list, TeleportFinder.Kind.ITEM, "Teleport to House tablet: Outside the house", 2757, 3178, 0, "8013", "", "", "2187=5;4744=1", "", "consumable");
		add(list, TeleportFinder.Kind.ITEM, "Teleport to House tablet (Outside): Outside the house", 2757, 3178, 0, "8013", "", "", "2187=5;4744=0", "", "consumable");
		add(list, TeleportFinder.Kind.ITEM, "Teleport to House tablet: Outside the house", 2544, 3096, 0, "8013", "", "", "2187=6;4744=1", "", "consumable");
		add(list, TeleportFinder.Kind.ITEM, "Teleport to House tablet (Outside): Outside the house", 2544, 3096, 0, "8013", "", "", "2187=6;4744=0", "", "consumable");
		add(list, TeleportFinder.Kind.ITEM, "Teleport to House tablet: Outside the house", 1743, 3517, 0, "8013", "", "", "2187=8;4744=1", "", "consumable");
		add(list, TeleportFinder.Kind.ITEM, "Teleport to House tablet (Outside): Outside the house", 1743, 3517, 0, "8013", "", "", "2187=8;4744=0", "", "consumable");
		add(list, TeleportFinder.Kind.ITEM, "Teleport to House tablet: Outside the house", 3239, 6076, 0, "8013", "", "", "2187=9;4744=1", "", "consumable");
		add(list, TeleportFinder.Kind.ITEM, "Teleport to House tablet (Outside): Outside the house", 3239, 6076, 0, "8013", "", "", "2187=9;4744=0", "", "consumable");
		add(list, TeleportFinder.Kind.ITEM, "Teleport to House tablet: Outside the house", 1422, 2963, 0, "8013", "", "", "2187=13;4744=1", "", "consumable");
		add(list, TeleportFinder.Kind.ITEM, "Teleport to House tablet (Outside): Outside the house", 1422, 2963, 0, "8013", "", "", "2187=13;4744=0", "", "consumable");
		add(list, TeleportFinder.Kind.ITEM, "Varrock tablet", 3213, 3424, 0, "8007", "", "", "", "", "consumable");
		add(list, TeleportFinder.Kind.ITEM, "Varrock tablet: GE", 3165, 3479, 0, "8007", "", "", "4480=1", "", "consumable");
		add(list, TeleportFinder.Kind.ITEM, "Falador tablet", 2964, 3378, 0, "8009", "", "", "", "", "consumable");
		add(list, TeleportFinder.Kind.ITEM, "Lumbridge tablet", 3221, 3218, 0, "8008", "", "", "", "", "consumable");
		add(list, TeleportFinder.Kind.ITEM, "Camelot tablet", 2757, 3479, 0, "8010", "", "", "", "", "consumable");
		add(list, TeleportFinder.Kind.ITEM, "Ardougne tablet", 2662, 3305, 0, "8011", "", "PLAGUE_CITY", "", "", "consumable");
		add(list, TeleportFinder.Kind.ITEM, "Watchtower tablet", 2546, 3112, 0, "8012", "", "WATCHTOWER", "", "", "consumable");
		add(list, TeleportFinder.Kind.ITEM, "Watchtower tablet: Yanille", 2584, 3097, 0, "8012", "", "WATCHTOWER", "4460=1", "", "consumable");
		add(list, TeleportFinder.Kind.ITEM, "Rimmington tablet", 2953, 3224, 0, "11741", "", "", "", "", "consumable");
		add(list, TeleportFinder.Kind.ITEM, "Taverley tablet", 2893, 3465, 0, "11742", "", "", "", "", "consumable");
		add(list, TeleportFinder.Kind.ITEM, "Pollnivneach tablet", 3340, 3004, 0, "11743", "", "", "", "", "consumable");
		add(list, TeleportFinder.Kind.ITEM, "Rellekka tablet", 2670, 3631, 0, "11744", "", "", "", "", "consumable");
		add(list, TeleportFinder.Kind.ITEM, "Brimhaven tablet", 2757, 3179, 0, "11745", "", "", "", "", "consumable");
		add(list, TeleportFinder.Kind.ITEM, "Yanille tablet", 2544, 3096, 0, "11746", "", "", "", "", "consumable");
		add(list, TeleportFinder.Kind.ITEM, "Trollheim tablet", 2890, 3679, 0, "11747", "", "", "", "", "consumable");
		add(list, TeleportFinder.Kind.ITEM, "Hosidius tablet", 1743, 3517, 0, "19651", "", "", "", "", "consumable");
		add(list, TeleportFinder.Kind.ITEM, "Prifddinas tablet", 3239, 6077, 0, "23771", "", "", "", "", "consumable");
		add(list, TeleportFinder.Kind.ITEM, "Aldarin tablet", 1422, 2964, 0, "30149", "", "", "", "", "consumable");
		add(list, TeleportFinder.Kind.ITEM, "Paddewwa tablet", 3097, 9881, 0, "12781", "", "DESERT_TREASURE_I", "", "", "consumable");
		add(list, TeleportFinder.Kind.ITEM, "Senntisten tablet", 3320, 3337, 0, "12782", "", "DESERT_TREASURE_I", "", "", "consumable");
		add(list, TeleportFinder.Kind.ITEM, "Kharyrll tablet", 3494, 3473, 0, "12779", "", "DESERT_TREASURE_I", "", "", "consumable");
		add(list, TeleportFinder.Kind.ITEM, "Lassar tablet", 3004, 3470, 0, "12780", "", "DESERT_TREASURE_I", "", "", "consumable");
		add(list, TeleportFinder.Kind.ITEM, "Dareeyak tablet", 2968, 3696, 0, "12777", "", "DESERT_TREASURE_I", "", "", "consumable");
		add(list, TeleportFinder.Kind.ITEM, "Carrallanger tablet", 3158, 3666, 0, "12776", "", "DESERT_TREASURE_I", "", "", "consumable");
		add(list, TeleportFinder.Kind.ITEM, "Annakarl tablet", 3288, 3888, 0, "12775", "", "DESERT_TREASURE_I", "", "", "consumable");
		add(list, TeleportFinder.Kind.ITEM, "Ghorrock tablet", 2976, 3875, 0, "12778", "", "DESERT_TREASURE_I", "", "", "consumable");
		add(list, TeleportFinder.Kind.ITEM, "Moonclan tablet", 2113, 3915, 0, "24949", "", "LUNAR_DIPLOMACY", "", "", "consumable");
		add(list, TeleportFinder.Kind.ITEM, "Ourania tablet", 2468, 3246, 0, "24951", "", "LUNAR_DIPLOMACY", "", "", "consumable");
		add(list, TeleportFinder.Kind.ITEM, "Waterbirth tablet", 2546, 3755, 0, "24953", "", "", "", "", "consumable");
		add(list, TeleportFinder.Kind.ITEM, "Barbarian tablet", 2543, 3569, 0, "24955", "", "", "", "", "consumable");
		add(list, TeleportFinder.Kind.ITEM, "Khazard tablet", 2636, 3167, 0, "24957", "", "", "", "", "consumable");
		add(list, TeleportFinder.Kind.ITEM, "Fishing guild tablet", 2613, 3391, 0, "24959", "", "", "", "", "consumable");
		add(list, TeleportFinder.Kind.ITEM, "Catherby tablet", 2801, 3449, 0, "24961", "", "", "", "", "consumable");
		add(list, TeleportFinder.Kind.ITEM, "Ice plateau tablet", 2975, 3938, 0, "24963", "", "", "", "", "consumable");
		add(list, TeleportFinder.Kind.ITEM, "Arceuus Library tablet", 1632, 3837, 0, "19613", "", "", "", "", "consumable");
		add(list, TeleportFinder.Kind.ITEM, "Draynor Manor tablet", 3109, 3351, 0, "19615", "", "", "", "", "consumable");
		add(list, TeleportFinder.Kind.ITEM, "Battlefront tablet", 1347, 3740, 0, "22949", "", "", "", "", "consumable");
		add(list, TeleportFinder.Kind.ITEM, "Mind Altar tablet", 2979, 3510, 0, "19617", "", "", "", "", "consumable");
		add(list, TeleportFinder.Kind.ITEM, "Salve Graveyard tablet", 3433, 3461, 0, "19619", "", "PRIEST_IN_PERIL", "", "", "consumable");
		add(list, TeleportFinder.Kind.ITEM, "Fenkenstrain's Castle tablet", 3548, 3529, 0, "19621", "", "PRIEST_IN_PERIL", "", "", "consumable");
		add(list, TeleportFinder.Kind.ITEM, "West Ardougne tablet", 2500, 3290, 0, "19623", "", "BIOHAZARD", "", "", "consumable");
		add(list, TeleportFinder.Kind.ITEM, "Harmony Island tablet", 3797, 2866, 0, "19625", "", "THE_GREAT_BRAIN_ROBBERY", "", "", "consumable");
		add(list, TeleportFinder.Kind.ITEM, "Cemetery tablet", 2981, 3764, 0, "19627", "", "", "", "", "consumable");
		add(list, TeleportFinder.Kind.ITEM, "Barrows tablet", 3564, 3315, 0, "19629", "", "PRIEST_IN_PERIL", "", "", "consumable");
		add(list, TeleportFinder.Kind.ITEM, "Ape Atoll tablet", 2771, 9102, 0, "19631", "", "MONKEY_MADNESS_I", "", "", "consumable");
		add(list, TeleportFinder.Kind.ITEM, "Volcanic Mine tablet", 3811, 3813, 0, "21541", "", "", "", "", "consumable");
		add(list, TeleportFinder.Kind.ITEM, "Wilderness Crabs tablet", 3352, 3782, 0, "24251", "", "", "", "", "consumable");
		add(list, TeleportFinder.Kind.ITEM, "Nardah teleport", 3419, 2917, 0, "12402", "", "", "", "", "consumable");
		add(list, TeleportFinder.Kind.ITEM, "Master scroll book: Nardah teleport", 3419, 2917, 0, "21389", "", "", "5672>0", "", "consumable");
		add(list, TeleportFinder.Kind.ITEM, "Digsite teleport", 3325, 3412, 0, "12403", "", "", "", "", "consumable");
		add(list, TeleportFinder.Kind.ITEM, "Master scroll book: Digsite teleport", 3325, 3412, 0, "21389", "", "", "5673>0", "", "consumable");
		add(list, TeleportFinder.Kind.ITEM, "Feldip hills teleport", 2541, 2925, 0, "12404", "", "", "", "", "consumable");
		add(list, TeleportFinder.Kind.ITEM, "Master scroll book: Feldip hills teleport", 2541, 2925, 0, "21389", "", "", "5674>0", "", "consumable");
		add(list, TeleportFinder.Kind.ITEM, "Lunar isle teleport", 2094, 3913, 0, "12405", "", "LUNAR_DIPLOMACY", "", "", "consumable");
		add(list, TeleportFinder.Kind.ITEM, "Master scroll book: Lunar isle teleport", 2094, 3913, 0, "21389", "", "LUNAR_DIPLOMACY", "5675>0", "", "consumable");
		add(list, TeleportFinder.Kind.ITEM, "Mort'ton teleport", 3488, 3288, 0, "12406", "", "PRIEST_IN_PERIL", "", "", "consumable");
		add(list, TeleportFinder.Kind.ITEM, "Master scroll book: Mort'ton teleport", 3488, 3288, 0, "21389", "", "PRIEST_IN_PERIL", "5676>0", "", "consumable");
		add(list, TeleportFinder.Kind.ITEM, "Pest control teleport", 2658, 2659, 0, "12407", "", "", "", "", "consumable");
		add(list, TeleportFinder.Kind.ITEM, "Master scroll book: Pest control teleport", 2658, 2659, 0, "21389", "", "", "5677>0", "", "consumable");
		add(list, TeleportFinder.Kind.ITEM, "Piscatoris teleport", 2339, 3649, 0, "12408", "", "", "", "", "consumable");
		add(list, TeleportFinder.Kind.ITEM, "Master scroll book: Piscatoris teleport", 2339, 3649, 0, "21389", "", "", "5678>0", "", "consumable");
		add(list, TeleportFinder.Kind.ITEM, "Tai bwo wannai teleport", 2789, 3066, 0, "12409", "", "TAI_BWO_WANNAI_TRIO", "", "", "consumable");
		add(list, TeleportFinder.Kind.ITEM, "Master scroll book: Tai bwo wannai teleport", 2789, 3066, 0, "21389", "", "TAI_BWO_WANNAI_TRIO", "5679>0", "", "consumable");
		add(list, TeleportFinder.Kind.ITEM, "Iorwerth camp teleport", 2194, 3258, 0, "12410", "", "REGICIDE", "", "", "consumable");
		add(list, TeleportFinder.Kind.ITEM, "Master scroll book: Iorwerth camp teleport", 2194, 3258, 0, "21389", "", "REGICIDE", "5680>0", "", "consumable");
		add(list, TeleportFinder.Kind.ITEM, "Mos le'harmless teleport", 3701, 2996, 0, "12411", "", "CABIN_FEVER", "", "", "consumable");
		add(list, TeleportFinder.Kind.ITEM, "Master scroll book: Mos le'harmless teleport", 3701, 2996, 0, "21389", "", "CABIN_FEVER", "5681>0", "", "consumable");
		add(list, TeleportFinder.Kind.ITEM, "Lumberyard teleport", 3302, 3487, 0, "12642", "", "", "", "", "consumable");
		add(list, TeleportFinder.Kind.ITEM, "Master scroll book: Lumberyard teleport", 3302, 3487, 0, "21389", "", "", "5682>0", "", "consumable");
		add(list, TeleportFinder.Kind.ITEM, "Zul-andra teleport", 2196, 3056, 0, "12938", "", "REGICIDE", "", "", "consumable");
		add(list, TeleportFinder.Kind.ITEM, "Master scroll book: Zul-andra teleport", 2196, 3056, 0, "21389", "", "REGICIDE", "5683>0", "", "consumable");
		add(list, TeleportFinder.Kind.ITEM, "Key master teleport", 1310, 1250, 0, "13249", "", "", "", "", "consumable");
		add(list, TeleportFinder.Kind.ITEM, "Master scroll book: Key master teleport", 1310, 1250, 0, "21389", "", "", "5684>0", "", "consumable");
		add(list, TeleportFinder.Kind.ITEM, "Revenant cave teleport", 3128, 3832, 0, "21802", "", "", "", "", "consumable");
		add(list, TeleportFinder.Kind.ITEM, "Master scroll book: Revenant cave teleport", 3128, 3832, 0, "21389", "", "", "6056>0", "", "consumable");
		add(list, TeleportFinder.Kind.ITEM, "Watson teleport", 1645, 3580, 0, "23387", "", "", "", "", "consumable");
		add(list, TeleportFinder.Kind.ITEM, "Master scroll book: Watson teleport", 1645, 3580, 0, "21389", "", "", "8253>0", "", "consumable");
		add(list, TeleportFinder.Kind.ITEM, "Crafting cape: Teleport", 2931, 3286, 0, "9780,9781", "CRAFTING:99", "", "", "", "");
		add(list, TeleportFinder.Kind.ITEM, "Construction cape: Tele to POH", 1858, 7051, 0, "9789,9790", "CONSTRUCTION:99", "", "4744=0", "", "");
		add(list, TeleportFinder.Kind.ITEM, "Construction cape: Rimmington", 2954, 3224, 0, "9789,9790", "CONSTRUCTION:99", "", "2187=1;4744=1", "", "");
		add(list, TeleportFinder.Kind.ITEM, "Construction cape: Taverley", 2894, 3465, 0, "9789,9790", "CONSTRUCTION:99", "", "2187=2;4744=1", "", "");
		add(list, TeleportFinder.Kind.ITEM, "Construction cape: Pollnivneach", 3340, 3004, 0, "9789,9790", "CONSTRUCTION:99", "", "2187=3;4744=1", "", "");
		add(list, TeleportFinder.Kind.ITEM, "Construction cape: Rellekka", 2670, 3632, 0, "9789,9790", "CONSTRUCTION:99", "", "2187=4;4744=1", "", "");
		add(list, TeleportFinder.Kind.ITEM, "Construction cape: Brimhaven", 2758, 3178, 0, "9789,9790", "CONSTRUCTION:99", "", "2187=5;4744=1", "", "");
		add(list, TeleportFinder.Kind.ITEM, "Construction cape: Yanille", 2544, 3095, 0, "9789,9790", "CONSTRUCTION:99", "", "2187=6;4744=1", "", "");
		add(list, TeleportFinder.Kind.ITEM, "Construction cape: Hosidius", 1744, 3517, 0, "9789,9790", "CONSTRUCTION:99", "", "2187=8;4744=1", "", "");
		add(list, TeleportFinder.Kind.ITEM, "Construction cape: Prifddinas", 3239, 6076, 0, "9789,9790", "CONSTRUCTION:99", "", "2187=9;4744=1", "", "");
		add(list, TeleportFinder.Kind.ITEM, "Construction cape: Aldarin", 1422, 2965, 0, "9789,9790", "CONSTRUCTION:99", "", "2187=13;4744=1", "", "");
		add(list, TeleportFinder.Kind.ITEM, "Construction cape: Rimmington", 2954, 3224, 0, "9789,9790", "CONSTRUCTION:99", "", "", "", "");
		add(list, TeleportFinder.Kind.ITEM, "Construction cape: Taverley", 2894, 3465, 0, "9789,9790", "CONSTRUCTION:99", "", "", "", "");
		add(list, TeleportFinder.Kind.ITEM, "Construction cape: Pollnivneach", 3340, 3004, 0, "9789,9790", "CONSTRUCTION:99", "", "", "", "");
		add(list, TeleportFinder.Kind.ITEM, "Construction cape: Hosidius", 1744, 3517, 0, "9789,9790", "CONSTRUCTION:99", "", "", "", "");
		add(list, TeleportFinder.Kind.ITEM, "Construction cape: Rellekka", 2670, 3632, 0, "9789,9790", "CONSTRUCTION:99", "", "", "", "");
		add(list, TeleportFinder.Kind.ITEM, "Construction cape: Brimhaven", 2758, 3178, 0, "9789,9790", "CONSTRUCTION:99", "", "", "", "");
		add(list, TeleportFinder.Kind.ITEM, "Construction cape: Yanille", 2544, 3095, 0, "9789,9790", "CONSTRUCTION:99", "", "", "", "");
		add(list, TeleportFinder.Kind.ITEM, "Construction cape: Prifddinas", 3239, 6076, 0, "9789,9790", "CONSTRUCTION:99", "", "", "", "");
		add(list, TeleportFinder.Kind.ITEM, "Construction cape: Aldarin", 1422, 2965, 0, "9789,9790", "CONSTRUCTION:99", "", "", "", "");
		add(list, TeleportFinder.Kind.ITEM, "Fishing cape: Fishing Guild", 2604, 3401, 0, "9798,9799", "FISHING:99", "", "", "", "");
		add(list, TeleportFinder.Kind.ITEM, "Fishing cape: Otto's Grotto", 2504, 3484, 0, "9798,9799", "FISHING:99", "", "", "", "");
		add(list, TeleportFinder.Kind.ITEM, "Farming cape: Farming Guild", 1248, 3725, 0, "9810,9811", "FARMING:99", "", "", "", "");
		add(list, TeleportFinder.Kind.ITEM, "Hunter cape: Feldip Hills", 2556, 2917, 0, "9948,9949", "HUNTER:99", "", "4819<5", "", "consumable");
		add(list, TeleportFinder.Kind.ITEM, "Hunter cape: Black chinchompa", 3144, 3772, 0, "9948,9949", "HUNTER:99", "", "4819<5", "", "consumable");
		add(list, TeleportFinder.Kind.ITEM, "Hunter cape: Hunter Guild", 1558, 3046, 0, "9948,9949", "HUNTER:99", "", "", "", "");
		add(list, TeleportFinder.Kind.ITEM, "Strength cape: Warriors' Guild", 2865, 3546, 0, "9750,9751", "STRENGTH:99", "", "", "", "");
		add(list, TeleportFinder.Kind.ITEM, "Music cape: Falo the Bard", 2689, 3547, 0, "13221,13222", "", "", "", "", "");
		add(list, TeleportFinder.Kind.ITEM, "Achievement diary cape: 1. Two-pints", 2574, 3323, 0, "13069,19476", "", "", "4461=1;4465=1;4469=1;4474=1;4478=1;4482=1;4486=1;4490=1;4494=1;4498=1;4566=1", "", "");
		add(list, TeleportFinder.Kind.ITEM, "Achievement diary cape: 2. Jarr", 3302, 3122, 0, "13069,19476", "", "", "4461=1;4465=1;4469=1;4474=1;4478=1;4482=1;4486=1;4490=1;4494=1;4498=1;4566=1", "", "");
		add(list, TeleportFinder.Kind.ITEM, "Achievement diary cape: 3. Sir Rebral", 2978, 3347, 0, "13069,19476", "", "", "4461=1;4465=1;4469=1;4474=1;4478=1;4482=1;4486=1;4490=1;4494=1;4498=1;4566=1", "", "");
		add(list, TeleportFinder.Kind.ITEM, "Achievement diary cape: 4. Thorodin", 2659, 3627, 0, "13069,19476", "", "", "4461=1;4465=1;4469=1;4474=1;4478=1;4482=1;4486=1;4490=1;4494=1;4498=1;4566=1", "", "");
		add(list, TeleportFinder.Kind.ITEM, "Achievement diary cape: 5. Flax keeper", 2744, 3443, 0, "13069,19476", "", "", "4461=1;4465=1;4469=1;4474=1;4478=1;4482=1;4486=1;4490=1;4494=1;4498=1;4566=1", "", "");
		add(list, TeleportFinder.Kind.ITEM, "Achievement diary cape: 6. Pirate Jackie the Fruit", 2808, 3192, 0, "13069,19476", "", "", "4461=1;4465=1;4469=1;4474=1;4478=1;4482=1;4486=1;4490=1;4494=1;4498=1;4566=1", "", "");
		add(list, TeleportFinder.Kind.ITEM, "Achievement diary cape: 7. Kaleb Paramaya", 2862, 2997, 0, "13069,19476", "", "", "4461=1;4465=1;4469=1;4474=1;4478=1;4482=1;4486=1;4490=1;4494=1;4498=1;4566=1", "", "");
		add(list, TeleportFinder.Kind.ITEM, "Achievement diary cape: 8. Jungle forester", 2795, 2945, 0, "13069,19476", "", "", "4461=1;4465=1;4469=1;4474=1;4478=1;4482=1;4486=1;4490=1;4494=1;4498=1;4566=1", "", "");
		add(list, TeleportFinder.Kind.ITEM, "Achievement diary cape: 9. TzHaar-Mej", 2455, 5133, 0, "13069,19476", "", "", "4461=1;4465=1;4469=1;4474=1;4478=1;4482=1;4486=1;4490=1;4494=1;4498=1;4566=1", "", "");
		add(list, TeleportFinder.Kind.ITEM, "Achievement diary cape: A. Elise", 1648, 3665, 0, "13069,19476", "", "", "4461=1;4465=1;4469=1;4474=1;4478=1;4482=1;4486=1;4490=1;4494=1;4498=1;4566=1", "", "");
		add(list, TeleportFinder.Kind.ITEM, "Achievement diary cape: B. Hatius Cosaintus", 3234, 3214, 0, "13069,19476", "", "", "4461=1;4465=1;4469=1;4474=1;4478=1;4482=1;4486=1;4490=1;4494=1;4498=1;4566=1", "", "");
		add(list, TeleportFinder.Kind.ITEM, "Achievement diary cape: C. Le-sabr?", 3465, 3478, 0, "13069,19476", "", "", "4461=1;4465=1;4469=1;4474=1;4478=1;4482=1;4486=1;4490=1;4494=1;4498=1;4566=1", "", "");
		add(list, TeleportFinder.Kind.ITEM, "Achievement diary cape: D. Toby", 3225, 3415, 0, "13069,19476", "", "", "4461=1;4465=1;4469=1;4474=1;4478=1;4482=1;4486=1;4490=1;4494=1;4498=1;4566=1", "", "");
		add(list, TeleportFinder.Kind.ITEM, "Achievement diary cape: E. Lesser Fanatic", 3121, 3516, 0, "13069,19476", "", "", "4461=1;4465=1;4469=1;4474=1;4478=1;4482=1;4486=1;4490=1;4494=1;4498=1;4566=1", "", "");
		add(list, TeleportFinder.Kind.ITEM, "Achievement diary cape: F. Elder Gnome child", 2467, 3459, 0, "13069,19476", "", "", "4461=1;4465=1;4469=1;4474=1;4478=1;4482=1;4486=1;4490=1;4494=1;4498=1;4566=1", "", "");
		add(list, TeleportFinder.Kind.ITEM, "Achievement diary cape: G. Twiggy O'Korn", 3096, 3227, 0, "13069,19476", "", "", "4461=1;4465=1;4469=1;4474=1;4478=1;4482=1;4486=1;4490=1;4494=1;4498=1;4566=1", "", "");
		add(list, TeleportFinder.Kind.ITEM, "Ectophial", 3660, 3522, 0, "4251,4252", "", "", "", "", "");
		add(list, TeleportFinder.Kind.ITEM, "Teleport crystal: Lletya", 2330, 3171, 0, "6099,6100,6101,6102,13102", "", "", "", "", "consumable");
		add(list, TeleportFinder.Kind.ITEM, "Teleport crystal: Prifddinas", 3264, 6066, 0, "6099,6100,6101,6102,13102", "", "SONG_OF_THE_ELVES", "", "", "consumable");
		add(list, TeleportFinder.Kind.ITEM, "Eternal teleport crystal: Lletya", 2330, 3171, 0, "23946", "", "", "", "", "");
		add(list, TeleportFinder.Kind.ITEM, "Eternal teleport crystal: Prifddinas", 3264, 6066, 0, "23946", "", "SONG_OF_THE_ELVES", "", "", "");
		add(list, TeleportFinder.Kind.ITEM, "Royal seed pod: Grand Tree", 2465, 3495, 0, "19564", "", "", "", "", "");
		add(list, TeleportFinder.Kind.ITEM, "Grand seed pod: Grand Tree", 2465, 3495, 0, "9469", "", "", "", "", "consumable");
		add(list, TeleportFinder.Kind.ITEM, "Enchanted lyre: Rellekka", 2662, 3644, 0, "3691,6125,6126,6127,13079", "", "", "", "", "consumable");
		add(list, TeleportFinder.Kind.ITEM, "Enchanted lyre: Waterbirth Island", 2551, 3755, 0, "3691,6125,6126,6127,13079", "", "", "4493=1", "", "consumable");
	}

	private static void part2(List<TeleportFinder.Teleport> list)
	{
		add(list, TeleportFinder.Kind.ITEM, "Enchanted lyre: Jatizso", 2409, 3809, 0, "3691,6125,6126,6127,13079", "", "", "4494=1", "", "consumable");
		add(list, TeleportFinder.Kind.ITEM, "Enchanted lyre: Neitiznot", 2336, 3802, 0, "3691,6125,6126,6127,13079", "", "", "4494=1", "", "consumable");
		add(list, TeleportFinder.Kind.ITEM, "Enchanted lyre (i): Rellekka", 2662, 3644, 0, "23458", "", "", "", "", "");
		add(list, TeleportFinder.Kind.ITEM, "Enchanted lyre (i): Waterbirth Island", 2551, 3755, 0, "23458", "", "", "4493=1", "", "");
		add(list, TeleportFinder.Kind.ITEM, "Enchanted lyre (i): Jatizso", 2409, 3809, 0, "23458", "", "", "4494=1", "", "");
		add(list, TeleportFinder.Kind.ITEM, "Enchanted lyre (i): Neitiznot", 2336, 3802, 0, "23458", "", "", "4494=1", "", "");
		add(list, TeleportFinder.Kind.ITEM, "Camulet: Enakhra's Temple", 3105, 9315, 2, "6707", "", "", "", "", "");
		add(list, TeleportFinder.Kind.ITEM, "Camulet: Bandit Camp Quarry", 3191, 2924, 0, "6707", "", "", "4485=1", "", "");
		add(list, TeleportFinder.Kind.ITEM, "Kharedst's memoirs: Lunch by the Lancalliums", 1714, 3611, 0, "21760", "", "", "6028=1;6035>0", "", "consumable");
		add(list, TeleportFinder.Kind.ITEM, "Kharedst's memoirs: The Fisher's Flute", 1803, 3748, 0, "21760", "", "", "6038=1;6035>0", "", "consumable");
		add(list, TeleportFinder.Kind.ITEM, "Kharedst's memoirs: History and Hearsay", 1478, 3576, 0, "21760", "", "", "6359=1;6035>0", "", "consumable");
		add(list, TeleportFinder.Kind.ITEM, "Kharedst's memoirs: Jewelry of Jubilation", 1544, 3761, 0, "21760", "", "", "7801=1;6035>0", "", "consumable");
		add(list, TeleportFinder.Kind.ITEM, "Kharedst's memoirs: A Dark Disposition", 1681, 3746, 0, "21760", "", "", "7857=1;6035>0", "", "consumable");
		add(list, TeleportFinder.Kind.ITEM, "Book of the dead: Lunch by the Lancalliums", 1714, 3611, 0, "25818", "", "", "6028=1;6035>0", "", "consumable");
		add(list, TeleportFinder.Kind.ITEM, "Book of the dead: The Fisher's Flute", 1803, 3748, 0, "25818", "", "", "6038=1;6035>0", "", "consumable");
		add(list, TeleportFinder.Kind.ITEM, "Book of the dead: History and Hearsay", 1478, 3576, 0, "25818", "", "", "6359=1;6035>0", "", "consumable");
		add(list, TeleportFinder.Kind.ITEM, "Book of the dead: Jewelry of Jubilation", 1544, 3761, 0, "25818", "", "", "7801=1;6035>0", "", "consumable");
		add(list, TeleportFinder.Kind.ITEM, "Book of the dead: A Dark Disposition", 1681, 3746, 0, "25818", "", "", "7857=1;6035>0", "", "consumable");
		add(list, TeleportFinder.Kind.ITEM, "Drakan's medallion: Ver Sinhaza", 3649, 3230, 0, "22400", "", "", "", "", "");
		add(list, TeleportFinder.Kind.ITEM, "Drakan's medallion: Darkmeyer", 3592, 3337, 0, "22400", "", "SINS_OF_THE_FATHER", "", "", "");
		add(list, TeleportFinder.Kind.ITEM, "Drakan's medallion: Slepe", 3808, 9700, 0, "22400", "", "", "", "", "");
		add(list, TeleportFinder.Kind.ITEM, "Mythical cape", 2457, 2850, 0, "21913,22114,24855", "", "", "", "", "");
		add(list, TeleportFinder.Kind.ITEM, "Stony basalt", 2845, 3694, 0, "22601", "", "", "6528=207", "", "consumable");
		add(list, TeleportFinder.Kind.ITEM, "Stony basalt: Roof", 2837, 3695, 0, "22601", "AGILITY:73", "", "6528=207;4493=1", "", "consumable");
		add(list, TeleportFinder.Kind.ITEM, "Icy basalt", 2846, 3938, 0, "22599", "", "", "6528=207", "", "consumable");
		add(list, TeleportFinder.Kind.ITEM, "Pharaoh's sceptre: Jalsavrah", 1934, 4428, 3, "26948", "", "", "", "", "consumable");
		add(list, TeleportFinder.Kind.ITEM, "Pharaoh's sceptre: Jaleustrophos", 3341, 2827, 0, "26948", "", "", "", "", "consumable");
		add(list, TeleportFinder.Kind.ITEM, "Pharaoh's sceptre: Jaldraocht", 3232, 2897, 0, "26948", "", "", "", "", "consumable");
		add(list, TeleportFinder.Kind.ITEM, "Pharaoh's sceptre: Jaltevas", 3313, 2718, 0, "26948", "", "", "13839=1", "", "consumable");
		add(list, TeleportFinder.Kind.ITEM, "Skull sceptre", 3081, 3421, 0, "9013,21276", "", "", "", "", "consumable");
		add(list, TeleportFinder.Kind.ITEM, "Xeric's talisman: 1. Xeric's Lookout", 1579, 3530, 0, "13393", "", "", "", "", "consumable");
		add(list, TeleportFinder.Kind.ITEM, "Xeric's talisman: 2. Xeric's Glade", 1752, 3566, 0, "13393", "", "", "", "", "consumable");
		add(list, TeleportFinder.Kind.ITEM, "Xeric's talisman: 3. Xeric's Inferno", 1504, 3815, 0, "13393", "", "", "", "", "consumable");
		add(list, TeleportFinder.Kind.ITEM, "Xeric's talisman: 4. Xeric's Heart", 1644, 3673, 0, "13393", "", "", "", "", "consumable");
		add(list, TeleportFinder.Kind.ITEM, "Chronicle", 3200, 3355, 0, "13660", "", "", "", "", "consumable");
		add(list, TeleportFinder.Kind.ITEM, "Amulet of the eye", 3615, 9461, 0, "26914", "", "", "", "", "");
		add(list, TeleportFinder.Kind.ITEM, "Ring of the elements: Air", 2981, 3276, 0, "26818", "", "", "", "", "consumable");
		add(list, TeleportFinder.Kind.ITEM, "Ring of the elements: Water", 3170, 3155, 0, "26818", "", "", "", "", "consumable");
		add(list, TeleportFinder.Kind.ITEM, "Ring of the elements: Earth", 3288, 3468, 0, "26818", "", "", "", "", "consumable");
		add(list, TeleportFinder.Kind.ITEM, "Ring of the elements: Fire", 3314, 3279, 0, "26818", "", "", "", "", "consumable");
		add(list, TeleportFinder.Kind.ITEM, "Hallowed crystal shard", 2400, 5982, 0, "24709", "", "", "", "", "consumable");
		add(list, TeleportFinder.Kind.ITEM, "Ring of shadows: Ancient Vault", 3296, 6436, 0, "28327", "", "", "", "", "consumable");
		add(list, TeleportFinder.Kind.ITEM, "Ring of shadows: Ghorrock Dungeon", 2912, 10336, 0, "28327", "", "", "", "", "consumable");
		add(list, TeleportFinder.Kind.ITEM, "Ring of shadows: The Scar", 2019, 6434, 0, "28327", "", "", "", "", "consumable");
		add(list, TeleportFinder.Kind.ITEM, "Ring of shadows: Lassar Undercity", 2588, 6435, 0, "28327", "", "", "", "", "consumable");
		add(list, TeleportFinder.Kind.ITEM, "Ring of shadows: The Stranglewood", 1175, 3424, 0, "28327", "", "", "", "", "consumable");
		add(list, TeleportFinder.Kind.ITEM, "Pendant of ates: 1. The Darkfrost", 1491, 3283, 0, "29893", "", "", "11175=1", "", "consumable");
		add(list, TeleportFinder.Kind.ITEM, "Pendant of ates: 2. Twilight Temple", 1668, 3223, 0, "29893", "", "", "11176=1", "", "consumable");
		add(list, TeleportFinder.Kind.ITEM, "Pendant of ates: 3. Ralos' Rise", 1459, 3138, 0, "29893", "", "", "11177=1", "", "consumable");
		add(list, TeleportFinder.Kind.ITEM, "Pendant of ates: 4. North Aldarin", 1426, 2995, 0, "29893", "", "", "11178=1", "", "consumable");
		add(list, TeleportFinder.Kind.ITEM, "Pendant of ates: 5. Kastori", 1366, 3086, 0, "29893", "", "", "16752=1", "", "consumable");
		add(list, TeleportFinder.Kind.ITEM, "Pendant of ates: 6. Nemus Retreat", 1364, 3277, 0, "29893", "", "", "16757=1", "", "consumable");
		add(list, TeleportFinder.Kind.ITEM, "Giantsoul amulet: 1. Bryophyta", 3174, 9898, 0, "30638", "", "", "", "", "consumable");
		add(list, TeleportFinder.Kind.ITEM, "Giantsoul amulet: 2. Obor", 3096, 9833, 0, "30638", "", "", "", "", "consumable");
		add(list, TeleportFinder.Kind.ITEM, "Giantsoul amulet: 3. Branda and Eldric", 2952, 9574, 0, "30638", "", "", "", "", "consumable");
		add(list, TeleportFinder.Kind.ITEM, "Sailors' amulet: The Pandemonium", 3058, 2975, 0, "32399", "", "", "", "", "consumable");
		add(list, TeleportFinder.Kind.ITEM, "Sailors' amulet: Port Roberts", 1889, 3292, 0, "32399", "", "", "19540=1", "", "consumable");
		add(list, TeleportFinder.Kind.ITEM, "Sailors' amulet: Red Rock", 2797, 2521, 0, "32399", "", "", "19541=1", "", "consumable");
		add(list, TeleportFinder.Kind.ITEM, "Sailors' amulet: Deepfin Point", 1943, 2757, 0, "32399", "", "", "19543=1", "", "consumable");
		add(list, TeleportFinder.Kind.ITEM, "Cowbell amulet", 3259, 3277, 0, "33104", "", "", "", "", "consumable");
		add(list, TeleportFinder.Kind.ITEM, "Dorgesh-kaan sphere", 2703, 5350, 0, "10972", "", "DEATH_TO_THE_DORGESHUUN", "", "", "consumable");
		add(list, TeleportFinder.Kind.ITEM, "Goblin village sphere", 2956, 3505, 0, "11060", "", "ANOTHER_SLICE_OF_HAM", "", "", "consumable");
		add(list, TeleportFinder.Kind.ITEM, "Plain of Mud sphere", 2588, 9840, 0, "26577", "", "LAND_OF_THE_GOBLINS", "", "", "consumable");
		add(list, TeleportFinder.Kind.FAIRY_RING, "Fairy ring AIQ", 2996, 3114, 0, "", "", "", "", "", "");
		add(list, TeleportFinder.Kind.FAIRY_RING, "Fairy ring AIR", 2700, 3247, 0, "", "", "", "", "", "");
		add(list, TeleportFinder.Kind.FAIRY_RING, "Fairy ring AIS", 1429, 3324, 0, "", "", "", "", "", "");
		add(list, TeleportFinder.Kind.FAIRY_RING, "Fairy ring AIR", 2328, 4426, 0, "", "", "", "", "", "");
		add(list, TeleportFinder.Kind.FAIRY_RING, "Fairy ring AJP", 1651, 3010, 0, "", "", "CHILDREN_OF_THE_SUN", "", "", "");
		add(list, TeleportFinder.Kind.FAIRY_RING, "Fairy ring AJQ", 2735, 5221, 0, "", "", "DEATH_TO_THE_DORGESHUUN", "", "", "");
		add(list, TeleportFinder.Kind.FAIRY_RING, "Fairy ring AJR", 2780, 3613, 0, "", "", "", "", "", "");
		add(list, TeleportFinder.Kind.FAIRY_RING, "Fairy ring AJS", 2500, 3896, 0, "", "", "", "", "", "");
		add(list, TeleportFinder.Kind.FAIRY_RING, "Fairy ring AKP", 3284, 2706, 0, "", "", "", "", "", "");
		add(list, TeleportFinder.Kind.FAIRY_RING, "Fairy ring AKQ", 2319, 3619, 0, "", "", "", "", "", "");
		add(list, TeleportFinder.Kind.FAIRY_RING, "Fairy ring AKR", 1826, 3540, 0, "", "", "", "", "", "");
		add(list, TeleportFinder.Kind.FAIRY_RING, "Fairy ring AKS", 2571, 2956, 0, "", "", "", "", "", "");
		add(list, TeleportFinder.Kind.FAIRY_RING, "Fairy ring ALP", 2503, 3636, 0, "", "", "", "", "", "");
		add(list, TeleportFinder.Kind.FAIRY_RING, "Fairy ring ALQ", 3597, 3495, 0, "", "", "", "", "", "");
		add(list, TeleportFinder.Kind.FAIRY_RING, "Fairy ring ALR", 3059, 4875, 0, "", "", "", "", "", "");
		add(list, TeleportFinder.Kind.FAIRY_RING, "Fairy ring ALS", 2644, 3495, 0, "", "", "", "", "", "");
		add(list, TeleportFinder.Kind.FAIRY_RING, "Fairy ring BIP", 3410, 3324, 0, "", "", "", "", "", "");
		add(list, TeleportFinder.Kind.FAIRY_RING, "Fairy ring BIQ", 3251, 3095, 0, "", "", "", "", "", "");
		add(list, TeleportFinder.Kind.FAIRY_RING, "Fairy ring BIS", 2635, 3266, 0, "", "", "", "", "", "");
		add(list, TeleportFinder.Kind.FAIRY_RING, "Fairy ring BJP", 2264, 2976, 0, "", "", "", "", "", "");
		add(list, TeleportFinder.Kind.FAIRY_RING, "Fairy ring BJR", 2650, 4730, 0, "", "", "HOLY_GRAIL", "", "", "");
		add(list, TeleportFinder.Kind.FAIRY_RING, "Fairy ring BJS", 2150, 3070, 0, "", "", "REGICIDE", "", "", "");
		add(list, TeleportFinder.Kind.FAIRY_RING, "Fairy ring BKP", 2385, 3035, 0, "", "", "", "", "", "");
		add(list, TeleportFinder.Kind.FAIRY_RING, "Fairy ring BKQ", 3041, 4532, 0, "", "", "", "", "", "");
		add(list, TeleportFinder.Kind.FAIRY_RING, "Fairy ring BKR", 3469, 3431, 0, "", "", "", "", "", "");
		add(list, TeleportFinder.Kind.FAIRY_RING, "Fairy ring BKS", 2412, 4434, 0, "", "", "", "", "", "");
		add(list, TeleportFinder.Kind.FAIRY_RING, "Fairy ring BLP", 2437, 5126, 0, "", "", "", "", "", "");
		add(list, TeleportFinder.Kind.FAIRY_RING, "Fairy ring BLQ", 3572, 4372, 0, "", "", "", "13599>49", "", "");
		add(list, TeleportFinder.Kind.FAIRY_RING, "Fairy ring BLR", 2740, 3351, 0, "", "", "", "", "", "");
		add(list, TeleportFinder.Kind.FAIRY_RING, "Fairy ring BLS", 1295, 3493, 0, "", "", "", "", "", "");
		add(list, TeleportFinder.Kind.FAIRY_RING, "Fairy ring CIP", 2513, 3884, 0, "", "", "THE_FREMENNIK_TRIALS", "", "", "");
		add(list, TeleportFinder.Kind.FAIRY_RING, "Fairy ring CIQ", 2528, 3127, 0, "", "", "", "", "", "");
		add(list, TeleportFinder.Kind.FAIRY_RING, "Fairy ring CIR", 1302, 3762, 0, "", "", "", "", "", "");
		add(list, TeleportFinder.Kind.FAIRY_RING, "Fairy ring CIS", 1639, 3868, 0, "", "", "", "", "", "");
		add(list, TeleportFinder.Kind.FAIRY_RING, "Fairy ring CJQ", 3178, 2447, 0, "", "", "TROUBLED_TORTUGANS", "", "", "");
		add(list, TeleportFinder.Kind.FAIRY_RING, "Fairy ring CJR", 2705, 3576, 0, "", "", "", "", "", "");
		add(list, TeleportFinder.Kind.FAIRY_RING, "Fairy ring CKP", 2075, 4848, 0, "", "", "", "", "", "");
		add(list, TeleportFinder.Kind.FAIRY_RING, "Fairy ring CKQ", 1359, 2941, 0, "", "", "CHILDREN_OF_THE_SUN", "", "", "");
		add(list, TeleportFinder.Kind.FAIRY_RING, "Fairy ring CKR", 2801, 3003, 0, "", "", "", "", "", "");
		add(list, TeleportFinder.Kind.FAIRY_RING, "Fairy ring CKS", 3447, 3470, 0, "", "", "", "", "", "");
		add(list, TeleportFinder.Kind.FAIRY_RING, "Fairy ring CLP", 3082, 3206, 0, "", "", "", "", "", "");
		add(list, TeleportFinder.Kind.FAIRY_RING, "Fairy ring CLR", 2740, 2738, 0, "", "", "MONKEY_MADNESS_I", "", "", "");
		add(list, TeleportFinder.Kind.FAIRY_RING, "Fairy ring CLS", 2682, 3081, 0, "", "", "", "", "", "");
		add(list, TeleportFinder.Kind.FAIRY_RING, "Fairy ring DIP", 3037, 4763, 0, "", "", "", "", "", "");
		add(list, TeleportFinder.Kind.FAIRY_RING, "Fairy ring DIQ", 1858, 7051, 0, "", "", "", "", "", "");
		add(list, TeleportFinder.Kind.FAIRY_RING, "Fairy ring DIR", 3038, 5348, 0, "", "", "", "", "", "");
		add(list, TeleportFinder.Kind.FAIRY_RING, "Fairy ring DIS", 3108, 3149, 0, "", "", "", "", "", "");
		add(list, TeleportFinder.Kind.FAIRY_RING, "Fairy ring DJP", 2658, 3230, 0, "", "", "", "", "", "");
		add(list, TeleportFinder.Kind.FAIRY_RING, "Fairy ring DJR", 1455, 3658, 0, "", "", "", "", "", "");
		add(list, TeleportFinder.Kind.FAIRY_RING, "Fairy ring DKP", 2900, 3111, 0, "", "", "", "", "", "");
		add(list, TeleportFinder.Kind.FAIRY_RING, "Fairy ring DKR", 3129, 3496, 0, "", "", "", "", "", "");
		add(list, TeleportFinder.Kind.FAIRY_RING, "Fairy ring DKS", 2744, 3719, 0, "", "", "", "", "", "");
		add(list, TeleportFinder.Kind.FAIRY_RING, "Fairy ring DLQ", 3423, 3016, 0, "", "", "", "", "", "");
		add(list, TeleportFinder.Kind.FAIRY_RING, "Fairy ring DLR", 2213, 3099, 0, "", "", "", "", "", "");
		add(list, TeleportFinder.Kind.FAIRY_RING, "Fairy ring DLS", 3447, 9824, 0, "", "", "IN_SEARCH_OF_THE_MYREQUE", "", "", "");
		add(list, TeleportFinder.Kind.POH_BOX, "Jewellery box: Emir's Arena", 3315, 3235, 0, "", "", "", "", "", "1");
		add(list, TeleportFinder.Kind.POH_BOX, "Jewellery box: Castle Wars", 2440, 3090, 0, "", "", "", "", "", "1");
		add(list, TeleportFinder.Kind.POH_BOX, "Jewellery box: Ferox Enclave", 3151, 3635, 0, "", "", "", "", "", "1");
		add(list, TeleportFinder.Kind.POH_BOX, "Jewellery box: Fortis Colosseum", 1793, 3107, 0, "", "", "", "", "", "1");
		add(list, TeleportFinder.Kind.POH_BOX, "Jewellery box: Burthorpe", 2899, 3553, 0, "", "", "", "", "", "1");
		add(list, TeleportFinder.Kind.POH_BOX, "Jewellery box: Barbarian Outpost", 2520, 3571, 0, "", "", "", "", "", "1");
		add(list, TeleportFinder.Kind.POH_BOX, "Jewellery box: Chasm of Tears", 3245, 9500, 2, "", "", "", "", "", "1");
		add(list, TeleportFinder.Kind.POH_BOX, "Jewellery box: Corporeal Beast", 2964, 4382, 2, "", "", "", "", "", "1");
		add(list, TeleportFinder.Kind.POH_BOX, "Jewellery box: Wintertodt Camp", 1631, 3940, 0, "", "", "", "", "", "1");
		add(list, TeleportFinder.Kind.POH_BOX, "Jewellery box: Warriors' Guild", 2882, 3547, 0, "", "", "", "", "", "2");
		add(list, TeleportFinder.Kind.POH_BOX, "Jewellery box: Champions' Guild", 3192, 3368, 0, "", "", "", "", "", "2");
		add(list, TeleportFinder.Kind.POH_BOX, "Jewellery box: Monastery", 3052, 3490, 0, "", "", "", "", "", "2");
		add(list, TeleportFinder.Kind.POH_BOX, "Jewellery box: Ranging Guild", 2653, 3439, 0, "", "", "", "", "", "2");
		add(list, TeleportFinder.Kind.POH_BOX, "Jewellery box: Fishing Guild", 2611, 3390, 0, "", "", "", "", "", "2");
		add(list, TeleportFinder.Kind.POH_BOX, "Jewellery box: Mining Guild", 3049, 9763, 0, "", "", "", "", "", "2");
		add(list, TeleportFinder.Kind.POH_BOX, "Jewellery box: Crafting Guild", 2933, 3295, 0, "", "", "", "", "", "2");
		add(list, TeleportFinder.Kind.POH_BOX, "Jewellery box: Cooking Guild", 3144, 3438, 0, "", "", "", "", "", "2");
		add(list, TeleportFinder.Kind.POH_BOX, "Jewellery box: Woodcutting Guild", 1662, 3505, 0, "", "", "", "", "", "2");
		add(list, TeleportFinder.Kind.POH_BOX, "Jewellery box: Farming Guild", 1248, 3719, 0, "", "", "", "", "", "2");
		add(list, TeleportFinder.Kind.POH_BOX, "Jewellery box: Miscellania", 2534, 3862, 0, "", "", "", "", "", "3");
		add(list, TeleportFinder.Kind.POH_BOX, "Jewellery box: Grand Exchange", 3163, 3478, 0, "", "", "", "", "", "3");
		add(list, TeleportFinder.Kind.POH_BOX, "Jewellery box: Falador Park", 2995, 3375, 0, "", "", "", "", "", "3");
		add(list, TeleportFinder.Kind.POH_BOX, "Jewellery box: Dondakan's Rock", 2824, 10168, 0, "", "", "", "", "", "3");
		add(list, TeleportFinder.Kind.POH_BOX, "Jewellery box: Edgeville", 3087, 3496, 0, "", "", "", "", "", "3");
		add(list, TeleportFinder.Kind.POH_BOX, "Jewellery box: Karamja", 2918, 3176, 0, "", "", "", "", "", "3");
		add(list, TeleportFinder.Kind.POH_BOX, "Jewellery box: Draynor Village", 3105, 3251, 0, "", "", "", "", "", "3");
		add(list, TeleportFinder.Kind.POH_BOX, "Jewellery box: Al Kharid", 3293, 3163, 0, "", "", "", "", "", "3");
		add(list, TeleportFinder.Kind.POH_SPIRIT_TREE, "Spirit tree: Tree Gnome Village", 2542, 3170, 0, "", "", "TREE_GNOME_VILLAGE", "", "", "");
		add(list, TeleportFinder.Kind.POH_SPIRIT_TREE, "Spirit tree: Gnome Stronghold", 2461, 3444, 0, "", "", "TREE_GNOME_VILLAGE", "", "", "");
		add(list, TeleportFinder.Kind.POH_SPIRIT_TREE, "Spirit tree: Battlefield of Khazard", 2555, 3259, 0, "", "", "TREE_GNOME_VILLAGE", "", "", "");
		add(list, TeleportFinder.Kind.POH_SPIRIT_TREE, "Spirit tree: Grand Exchange", 3185, 3508, 0, "", "", "TREE_GNOME_VILLAGE", "", "", "");
		add(list, TeleportFinder.Kind.POH_SPIRIT_TREE, "Spirit tree: Feldip Hills", 2488, 2850, 0, "", "", "TREE_GNOME_VILLAGE", "", "", "");
	}

	private static void part3(List<TeleportFinder.Teleport> list)
	{
		add(list, TeleportFinder.Kind.POH_SPIRIT_TREE, "Spirit tree: Prifddinas", 3274, 6123, 0, "", "", "TREE_GNOME_VILLAGE,SONG_OF_THE_ELVES", "", "", "");
		add(list, TeleportFinder.Kind.POH_SPIRIT_TREE, "Spirit tree: Port Sarim", 3058, 3257, 0, "", "", "TREE_GNOME_VILLAGE", "", "", "");
		add(list, TeleportFinder.Kind.POH_SPIRIT_TREE, "Spirit tree: Etceteria", 2613, 3855, 0, "", "", "TREE_GNOME_VILLAGE", "", "", "");
		add(list, TeleportFinder.Kind.POH_SPIRIT_TREE, "Spirit tree: Brimhaven", 2800, 3203, 0, "", "", "TREE_GNOME_VILLAGE", "", "", "");
		add(list, TeleportFinder.Kind.POH_SPIRIT_TREE, "Spirit tree: Hosidius", 1693, 3540, 0, "", "", "TREE_GNOME_VILLAGE", "", "", "");
		add(list, TeleportFinder.Kind.POH_SPIRIT_TREE, "Spirit tree: Farming Guild", 1251, 3750, 0, "", "", "TREE_GNOME_VILLAGE", "", "", "");
		add(list, TeleportFinder.Kind.POH_SPIRIT_TREE, "Spirit tree: Poison Waste", 2339, 3109, 0, "", "", "THE_PATH_OF_GLOUPHRIE", "", "", "");
		add(list, TeleportFinder.Kind.POH_SPIRIT_TREE, "Spirit tree: Laguna Aurorae", 1202, 2785, 0, "", "", "PANDEMONIUM", "", "", "");
		add(list, TeleportFinder.Kind.POH_PORTAL, "Ape Atoll Dungeon Portal", 2771, 9102, 0, "", "", "", "", "", "");
		add(list, TeleportFinder.Kind.POH_PORTAL, "Annakarl Portal", 3288, 3888, 0, "", "", "", "", "", "");
		add(list, TeleportFinder.Kind.POH_PORTAL, "Ardougne Portal", 2662, 3305, 0, "", "", "", "", "", "");
		add(list, TeleportFinder.Kind.POH_PORTAL, "Arceuus Library Portal", 1632, 3837, 0, "", "", "", "", "", "");
		add(list, TeleportFinder.Kind.POH_PORTAL, "Barbarian Outpost Portal", 2543, 3569, 0, "", "", "", "", "", "");
		add(list, TeleportFinder.Kind.POH_PORTAL, "Barrows Portal", 3564, 3315, 0, "", "", "", "", "", "");
		add(list, TeleportFinder.Kind.POH_PORTAL, "Battlefront Portal", 1347, 3740, 0, "", "", "", "", "", "");
		add(list, TeleportFinder.Kind.POH_PORTAL, "Camelot Portal", 2757, 3479, 0, "", "", "", "", "", "");
		add(list, TeleportFinder.Kind.POH_PORTAL, "Seers' Village Portal", 2726, 3487, 0, "", "", "", "", "", "");
		add(list, TeleportFinder.Kind.POH_PORTAL, "Carrallanger Portal", 3158, 3666, 0, "", "", "", "", "", "");
		add(list, TeleportFinder.Kind.POH_PORTAL, "Catherby Portal", 2801, 3449, 0, "", "", "", "", "", "");
		add(list, TeleportFinder.Kind.POH_PORTAL, "Civitas illa Fortis Portal", 1681, 3133, 0, "", "", "", "", "", "");
		add(list, TeleportFinder.Kind.POH_PORTAL, "Cemetery Portal", 2981, 3764, 0, "", "", "", "", "", "");
		add(list, TeleportFinder.Kind.POH_PORTAL, "Dareeyak Portal", 2968, 3696, 0, "", "", "", "", "", "");
		add(list, TeleportFinder.Kind.POH_PORTAL, "Draynor Manor Portal", 3109, 3351, 0, "", "", "", "", "", "");
		add(list, TeleportFinder.Kind.POH_PORTAL, "Falador Portal", 2964, 3378, 0, "", "", "", "", "", "");
		add(list, TeleportFinder.Kind.POH_PORTAL, "Fenkenstrain's Castle Portal", 3548, 3529, 0, "", "", "", "", "", "");
		add(list, TeleportFinder.Kind.POH_PORTAL, "Fishing Guild Portal", 2613, 3391, 0, "", "", "", "", "", "");
		add(list, TeleportFinder.Kind.POH_PORTAL, "Ghorrock Portal", 2976, 3872, 0, "", "", "", "", "", "");
		add(list, TeleportFinder.Kind.POH_PORTAL, "Grand Exchange Portal", 3165, 3479, 0, "", "", "", "", "", "");
		add(list, TeleportFinder.Kind.POH_PORTAL, "Harmony Island Portal", 3797, 2866, 0, "", "", "", "", "", "");
		add(list, TeleportFinder.Kind.POH_PORTAL, "Ice Plateau Portal", 2975, 3938, 0, "", "", "", "", "", "");
		add(list, TeleportFinder.Kind.POH_PORTAL, "Kharyrll Portal", 3494, 3473, 0, "", "", "", "", "", "");
		add(list, TeleportFinder.Kind.POH_PORTAL, "Kourend Portal", 1641, 3673, 0, "", "", "", "", "", "");
		add(list, TeleportFinder.Kind.POH_PORTAL, "Lassar Portal", 3004, 3470, 0, "", "", "", "", "", "");
		add(list, TeleportFinder.Kind.POH_PORTAL, "Lumbridge Portal", 3221, 3218, 0, "", "", "", "", "", "");
		add(list, TeleportFinder.Kind.POH_PORTAL, "Lunar Isle Portal", 2113, 3915, 0, "", "", "", "", "", "");
		add(list, TeleportFinder.Kind.POH_PORTAL, "Marim Portal", 2797, 2798, 1, "", "", "", "", "", "");
		add(list, TeleportFinder.Kind.POH_PORTAL, "Mind Altar Portal", 2979, 3510, 0, "", "", "", "", "", "");
		add(list, TeleportFinder.Kind.POH_PORTAL, "Ourania Portal", 2468, 3246, 0, "", "", "", "", "", "");
		add(list, TeleportFinder.Kind.POH_PORTAL, "Paddewwa Portal", 3097, 9881, 0, "", "", "", "", "", "");
		add(list, TeleportFinder.Kind.POH_PORTAL, "Port Khazard Portal", 2636, 3167, 0, "", "", "", "", "", "");
		add(list, TeleportFinder.Kind.POH_PORTAL, "Salve Graveyard Portal", 3433, 3461, 0, "", "", "", "", "", "");
		add(list, TeleportFinder.Kind.POH_PORTAL, "Senntisten Portal", 3320, 3337, 0, "", "", "", "", "", "");
		add(list, TeleportFinder.Kind.POH_PORTAL, "Trollheim Portal", 2890, 3679, 0, "", "", "", "", "", "");
		add(list, TeleportFinder.Kind.POH_PORTAL, "Troll Stronghold Portal", 2845, 3694, 0, "", "", "", "", "", "");
		add(list, TeleportFinder.Kind.POH_PORTAL, "Varrock Portal", 3213, 3424, 0, "", "", "", "", "", "");
		add(list, TeleportFinder.Kind.POH_PORTAL, "Waterbirth Island Portal", 2546, 3755, 0, "", "", "", "", "", "");
		add(list, TeleportFinder.Kind.POH_PORTAL, "Weiss Portal", 2846, 3938, 0, "", "", "", "", "", "");
		add(list, TeleportFinder.Kind.POH_PORTAL, "West Ardougne Portal", 2500, 3290, 0, "", "", "", "", "", "");
		add(list, TeleportFinder.Kind.POH_PORTAL, "Watchtower Portal", 2546, 3114, 0, "", "", "", "", "", "");
		add(list, TeleportFinder.Kind.POH_PORTAL, "Yanille Portal", 2584, 3097, 0, "", "", "", "", "", "");
	}

	private static void add(List<TeleportFinder.Teleport> list, TeleportFinder.Kind kind, String name, int x, int y, int plane,
		String items, String skills, String quests, String varbits, String varps, String note)
	{
		list.add(new TeleportFinder.Teleport(kind, name, x, y, plane, items, skills, quests, varbits, varps, note));
	}
}
