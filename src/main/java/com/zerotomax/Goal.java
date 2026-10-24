package com.zerotomax;

import java.util.Map;
import net.runelite.api.Skill;

/**
 * Something the planner can recommend: a quest or an achievement diary.
 * Both have the same kinds of requirements, so they are checked the same way.
 */
interface Goal
{
	String getName();

	Map<Skill, Integer> getSkillRequirements();

	/** Prerequisite quest id -> true if it must be finished, false if only started. */
	Map<String, Boolean> getQuestRequirements();

	int getQuestPoints();

	int getCombatLevel();
}
