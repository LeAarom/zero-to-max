package com.zerotomax;

import java.util.LinkedHashMap;
import java.util.Map;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Client;
import net.runelite.api.Quest;
import net.runelite.api.QuestState;
import net.runelite.api.Skill;
import net.runelite.api.coords.WorldPoint;

/**
 * One quest: what it needs, where it starts. Built from the rows in QuestData.
 * The quest is looked up by name at runtime, so a quest that your RuneLite version
 * doesn't know about is skipped instead of stopping the plugin from compiling.
 */
@Slf4j
final class QuestInfo implements Goal
{
	@Getter
	private final String id;
	@Getter
	private final boolean cape;
	/** 0 = easiest. */
	@Getter
	private final int difficultyRank;
	@Getter
	private final Map<Skill, Integer> skillRequirements = new LinkedHashMap<>();
	/** Prerequisite quest id -> true if it must be finished, false if only started. */
	@Getter
	private final Map<String, Boolean> questRequirements = new LinkedHashMap<>();
	@Getter
	private final int questPoints;
	@Getter
	private final int combatLevel;
	/** Null when no start location is known. */
	@Getter
	private final WorldPoint start;
	@Getter
	private final String startText;

	private final Quest quest;

	QuestInfo(String id, String difficulty, boolean cape, String skills, String prereqs,
		int questPoints, int combatLevel, int x, int y, int plane, String startText)
	{
		this.id = id;
		this.cape = cape;
		this.difficultyRank = rank(difficulty);
		this.questPoints = questPoints;
		this.combatLevel = combatLevel;
		this.start = (x == 0 && y == 0) ? null : new WorldPoint(x, y, plane);
		this.startText = startText;

		for (String part : skills.split(","))
		{
			if (part.isEmpty())
			{
				continue;
			}
			String[] kv = part.split(":");
			try
			{
				skillRequirements.put(Skill.valueOf(kv[0]), Integer.parseInt(kv[1]));
			}
			catch (IllegalArgumentException e)
			{
				log.warn("Unknown skill in quest data: {}", part);
			}
		}

		for (String part : prereqs.split(","))
		{
			if (part.isEmpty())
			{
				continue;
			}
			boolean mustFinish = !part.endsWith("*");
			questRequirements.put(mustFinish ? part : part.substring(0, part.length() - 1), mustFinish);
		}

		Quest found = null;
		try
		{
			found = Quest.valueOf(id);
		}
		catch (IllegalArgumentException e)
		{
			log.warn("Quest {} is not known to this RuneLite version, skipping it", id);
		}
		this.quest = found;
	}

	/** False if this RuneLite version doesn't have the quest. */
	boolean isKnown()
	{
		return quest != null;
	}

	@Override
	public String getName()
	{
		return quest != null ? quest.getName() : id;
	}

	QuestState getState(Client client)
	{
		return quest != null ? quest.getState(client) : QuestState.NOT_STARTED;
	}

	private static int rank(String difficulty)
	{
		switch (difficulty)
		{
			case "NOVICE":
				return 0;
			case "INTERMEDIATE":
				return 1;
			case "EXPERIENCED":
				return 2;
			case "MASTER":
				return 3;
			case "GRANDMASTER":
				return 4;
			default:
				return 1; // miniquests that other quests need
		}
	}
}
