package com.zerotomax;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import net.runelite.api.Client;
import net.runelite.api.Experience;
import net.runelite.api.Quest;
import net.runelite.api.QuestState;
import net.runelite.api.Skill;
import net.runelite.api.coords.WorldPoint;
import net.runelite.api.gameval.VarPlayerID;

/**
 * Works out the next thing to do from what the account has already done.
 * Call plan() on the client thread.
 *
 * Order of decisions:
 *  1. Any quest you can start right now (easiest first, quests that unlock others first).
 *  2. Otherwise any achievement diary you can do right now (Easy first).
 *  3. Otherwise the quest or diary closest to being unlocked, and the skill to train for it.
 *  4. Once everything is done, train skills to 99.
 */
final class Planner
{
	private static final int ROADMAP_LENGTH = 12;

	/**
	 * Quests with requirements beyond skills, quests, quest points and combat level
	 * (e.g. Kudos, prayer points, spellbook, sub-quest parts). The plugin can't check these,
	 * so it tells you to look. Taken from Quest Helper's requirement types.
	 */
	private static final Set<String> EXTRA_REQUIREMENTS = new HashSet<>(Arrays.asList(
		"DESERT_TREASURE_I", "DEVIOUS_MINDS", "BONE_VOYAGE", "THE_FREMENNIK_ISLES", "THE_GIANT_DWARF",
		"THE_GREAT_BRAIN_ROBBERY", "MONKEY_MADNESS_II", "MY_ARMS_BIG_ADVENTURE", "RAG_AND_BONE_MAN_II",
		"RATCATCHERS", "RECIPE_FOR_DISASTER", "RUM_DEAL", "A_KINGDOM_DIVIDED", "WHILE_GUTHIX_SLEEPS"));

	private static final String EXTRA_NOTE = " (Has extra requirements this plugin can't check - see the wiki page.)";

	static final class Plan
	{
		String title = "";
		String method = "";
		String instruction = "";
		String teleport = "-";
		String progress = "";
		WorldPoint destination;
		List<String> roadmap = new ArrayList<>();
		String summary = "";
	}

	private final List<QuestInfo> quests = new ArrayList<>();
	private final Map<String, QuestInfo> byId = new HashMap<>();
	private final List<DiaryInfo> diaries;
	/** Quest states, cached because each lookup runs a game script. */
	private final Map<String, QuestState> states = new HashMap<>();
	/** States of other quests (e.g. for teleports), looked up on demand and cleared on refresh. */
	private final Map<String, QuestState> extraStates = new HashMap<>();

	Planner(List<QuestInfo> questList, List<DiaryInfo> diaryList)
	{
		for (QuestInfo q : questList)
		{
			if (q.isKnown())
			{
				quests.add(q);
				byId.put(q.getId(), q);
			}
		}
		this.diaries = diaryList;
	}

	/**
	 * @param refreshQuests  re-read every quest's state from the game. Pass true now and then
	 *                       (and after login), false otherwise to keep this cheap.
	 * @param includeDiaries plan achievement diaries as well as quests
	 */
	Plan plan(Client client, ZeroToMaxConfig.TrainingStyle style, boolean refreshQuests, boolean includeDiaries)
	{
		if (refreshQuests || states.isEmpty())
		{
			states.clear();
			extraStates.clear();
			for (QuestInfo q : quests)
			{
				states.put(q.getId(), q.getState(client));
			}
		}

		Plan plan = new Plan();
		int questPoints = client.getVarpValue(VarPlayerID.QP);
		int combat = client.getLocalPlayer() != null ? client.getLocalPlayer().getCombatLevel() : 0;

		// What's left
		int capeTotal = 0;
		int capeDone = 0;
		Set<QuestInfo> questTodo = new LinkedHashSet<>();
		for (QuestInfo q : quests)
		{
			if (q.isCape())
			{
				capeTotal++;
				if (isDone(q))
				{
					capeDone++;
				}
				else
				{
					collect(q, questTodo);
				}
			}
		}

		List<DiaryInfo> diaryTodo = new ArrayList<>();
		int diariesDone = 0;
		if (includeDiaries)
		{
			for (DiaryInfo d : diaries)
			{
				if (d.isDone(client))
				{
					diariesDone++;
				}
				else
				{
					diaryTodo.add(d);
					// unfinished quests a diary needs become part of the plan too
					for (String pre : d.getQuestRequirements().keySet())
					{
						QuestInfo info = byId.get(pre);
						if (info != null && !isDone(info))
						{
							collect(info, questTodo);
						}
					}
				}
			}
		}

		plan.summary = "Quests " + capeDone + "/" + capeTotal
			+ (includeDiaries ? " | Diaries " + diariesDone + "/" + diaries.size() : "")
			+ " | QP " + questPoints;

		if (questTodo.isEmpty() && diaryTodo.isEmpty())
		{
			planSkills(client, style, plan);
			return plan;
		}

		// How many other to-do quests and diaries each quest unlocks
		Map<String, Integer> unlocks = new HashMap<>();
		for (QuestInfo q : questTodo)
		{
			for (String pre : q.getQuestRequirements().keySet())
			{
				unlocks.merge(pre, 1, Integer::sum);
			}
		}
		for (DiaryInfo d : diaryTodo)
		{
			for (String pre : d.getQuestRequirements().keySet())
			{
				unlocks.merge(pre, 1, Integer::sum);
			}
		}

		List<QuestInfo> availableQuests = new ArrayList<>();
		List<DiaryInfo> availableDiaries = new ArrayList<>();
		List<Goal> blocked = new ArrayList<>();
		for (QuestInfo q : questTodo)
		{
			sort(client, q, questPoints, combat, availableQuests, blocked);
		}
		for (DiaryInfo d : diaryTodo)
		{
			sort(client, d, questPoints, combat, availableDiaries, blocked);
		}

		availableQuests.sort(Comparator
			.comparingInt(QuestInfo::getDifficultyRank)
			.thenComparing((QuestInfo q) -> -unlocks.getOrDefault(q.getId(), 0))
			.thenComparing(QuestInfo::getName));
		availableDiaries.sort(Comparator
			.comparingInt(DiaryInfo::getTierRank)
			.thenComparing(DiaryInfo::getArea));

		if (!availableQuests.isEmpty())
		{
			QuestInfo next = availableQuests.get(0);
			plan.title = next.getName();
			plan.instruction = next.getStartText().isEmpty() ? "Start the quest (start location not in my data)" : next.getStartText();
			if (EXTRA_REQUIREMENTS.contains(next.getId()))
			{
				plan.instruction += EXTRA_NOTE;
			}
			plan.teleport = "No teleport data yet - walk or use your own";
			plan.destination = next.getStart();
			plan.progress = availableQuests.size() + " quests available now, " + questTodo.size() + " left";
			fillRoadmap(plan, availableQuests, availableDiaries);
			return plan;
		}

		if (!availableDiaries.isEmpty())
		{
			DiaryInfo next = availableDiaries.get(0);
			plan.title = next.getName();
			plan.instruction = "Do the " + next.getArea() + " " + next.getTier()
				+ " tasks (Quest Helper lists them under Achievement Diaries), then claim the reward. "
				+ next.getClaimText();
			if (next.isExtraRequirements())
			{
				plan.instruction += EXTRA_NOTE;
			}
			plan.teleport = "No teleport data yet - walk or use your own";
			plan.destination = next.getClaimLocation();
			plan.progress = availableDiaries.size() + " diaries available now, " + diaryTodo.size() + " left";
			fillRoadmap(plan, availableQuests, availableDiaries);
			return plan;
		}

		// Nothing is startable: find the quest or diary that is cheapest to unlock
		Goal best = null;
		long bestCost = Long.MAX_VALUE;
		for (Goal g : blocked)
		{
			long cost = unlockCost(client, g, questPoints, combat);
			if (cost < bestCost)
			{
				bestCost = cost;
				best = g;
			}
		}

		if (best == null)
		{
			plan.title = "Nothing available";
			plan.instruction = "Everything left is locked behind something this plugin can't work out. Check the Quest list in game.";
			return plan;
		}

		Map<Skill, Integer> gaps = skillGaps(client, best);
		if (gaps.isEmpty())
		{
			if (combat < best.getCombatLevel())
			{
				plan.title = "Raise combat level to " + best.getCombatLevel() + " (for " + best.getName() + ")";
				plan.method = "Combat: any combat skill";
				plan.instruction = "Train Attack, Strength, Defence, Hitpoints, Ranged, Magic or Prayer until your combat level is "
					+ best.getCombatLevel() + ".";
				plan.progress = "Combat " + combat + "/" + best.getCombatLevel();
				return plan;
			}
			plan.title = "Need more quest points";
			plan.instruction = best.getName() + " needs " + best.getQuestPoints() + " quest points. You have "
				+ questPoints + ". Finish other quests to earn more.";
			return plan;
		}

		Skill pick = null;
		long pickXp = Long.MAX_VALUE;
		for (Map.Entry<Skill, Integer> gap : gaps.entrySet())
		{
			long xp = xpToLevel(client, gap.getKey(), gap.getValue());
			if (xp < pickXp)
			{
				pickXp = xp;
				pick = gap.getKey();
			}
		}
		trainStep(client, style, plan, pick, gaps.get(pick), "for " + best.getName());

		blocked.sort(Comparator.comparingLong((Goal g) -> unlockCost(client, g, questPoints, combat)));
		for (Goal g : blocked)
		{
			if (plan.roadmap.size() >= ROADMAP_LENGTH)
			{
				break;
			}
			Map<Skill, Integer> g2 = skillGaps(client, g);
			plan.roadmap.add(g.getName() + (g2.isEmpty() ? "" : " (needs " + describeGaps(g2) + ")"));
		}
		return plan;
	}

	/** Puts a goal in the available list, the blocked list (only skills/QP/combat missing), or neither. */
	private <T extends Goal> void sort(Client client, T goal, int questPoints, int combat, List<T> available, List<Goal> blocked)
	{
		if (!prereqQuestsMet(goal))
		{
			return; // a prerequisite quest will be planned first
		}
		if (skillGaps(client, goal).isEmpty() && questPoints >= goal.getQuestPoints() && combat >= goal.getCombatLevel())
		{
			available.add(goal);
		}
		else
		{
			blocked.add(goal);
		}
	}

	private static void fillRoadmap(Plan plan, List<QuestInfo> availableQuests, List<DiaryInfo> availableDiaries)
	{
		for (QuestInfo q : availableQuests)
		{
			if (plan.roadmap.size() >= ROADMAP_LENGTH)
			{
				return;
			}
			plan.roadmap.add(q.getName());
		}
		for (DiaryInfo d : availableDiaries)
		{
			if (plan.roadmap.size() >= ROADMAP_LENGTH)
			{
				return;
			}
			plan.roadmap.add(d.getName());
		}
	}

	private void planSkills(Client client, ZeroToMaxConfig.TrainingStyle style, Plan plan)
	{
		Skill lowest = null;
		int lowestLevel = 100;
		List<String> remaining = new ArrayList<>();
		for (Skill s : Skill.values())
		{
			int lvl = client.getRealSkillLevel(s);
			if (lvl < 99)
			{
				remaining.add(s.getName() + " " + lvl + "/99");
				if (lvl < lowestLevel)
				{
					lowestLevel = lvl;
					lowest = s;
				}
			}
		}

		if (lowest == null)
		{
			plan.title = "MAXED OUT!";
			plan.instruction = "Everything is done and every skill is 99.";
			plan.teleport = "Done";
			return;
		}

		trainStep(client, style, plan, lowest, 99, "to max");
		plan.roadmap.addAll(remaining.subList(0, Math.min(ROADMAP_LENGTH, remaining.size())));
	}

	private void trainStep(Client client, ZeroToMaxConfig.TrainingStyle style, Plan plan,
		Skill skill, int target, String reason)
	{
		int level = client.getRealSkillLevel(skill);
		SkillMethods.Option opt = SkillMethods.get(skill, level, target, style);
		long xpLeft = xpToLevel(client, skill, target);

		plan.title = "Train " + skill.getName() + " to " + target + " (" + reason + ")";
		plan.method = skill.getName() + ": " + opt.getMethodName();
		plan.instruction = opt.getInstruction();
		plan.teleport = opt.getTeleportGuide();
		plan.destination = opt.getDestination();

		String progress = level + "/" + target + " (" + String.format("%,d", xpLeft) + " XP left";
		if (opt.getXpPerAction() > 0)
		{
			long actions = (long) Math.ceil(xpLeft / opt.getXpPerAction());
			progress += ", ~" + String.format("%,d", actions) + " " + opt.getActionUnitName();
		}
		if (opt.getXpPerHour() > 0)
		{
			double hours = (double) xpLeft / opt.getXpPerHour();
			progress += String.format(", ~%.1fh at %,dk xp/hr", hours, opt.getXpPerHour() / 1000);
		}
		plan.progress = progress + ")";
	}

	/** Adds q and every unfinished quest it needs. */
	private void collect(QuestInfo q, Set<QuestInfo> out)
	{
		if (!out.add(q))
		{
			return;
		}
		for (String pre : q.getQuestRequirements().keySet())
		{
			QuestInfo info = byId.get(pre);
			if (info != null && !isDone(info))
			{
				collect(info, out);
			}
		}
	}

	private boolean prereqQuestsMet(Goal g)
	{
		for (Map.Entry<String, Boolean> e : g.getQuestRequirements().entrySet())
		{
			QuestInfo pre = byId.get(e.getKey());
			if (pre == null)
			{
				continue; // unknown to this RuneLite version, don't block on it
			}
			QuestState state = stateOf(pre);
			boolean ok = e.getValue() ? state == QuestState.FINISHED : state != QuestState.NOT_STARTED;
			if (!ok)
			{
				return false;
			}
		}
		return true;
	}

	/** Skills below what the goal needs, with the level needed. */
	private static Map<Skill, Integer> skillGaps(Client client, Goal g)
	{
		Map<Skill, Integer> gaps = new LinkedHashMap<>();
		for (Map.Entry<Skill, Integer> e : g.getSkillRequirements().entrySet())
		{
			if (client.getRealSkillLevel(e.getKey()) < e.getValue())
			{
				gaps.put(e.getKey(), e.getValue());
			}
		}
		return gaps;
	}

	/**
	 * Rough effort to unlock a goal, used to pick what to work towards. Skill gaps cost their XP,
	 * a combat gap costs a flat amount per level, and a quest-point gap can't be trained for
	 * (only earned by other quests), so it ranks last.
	 */
	private static long unlockCost(Client client, Goal g, int questPoints, int combat)
	{
		long cost = 0;
		for (Map.Entry<Skill, Integer> gap : skillGaps(client, g).entrySet())
		{
			cost += xpToLevel(client, gap.getKey(), gap.getValue());
		}
		cost += Math.max(0, g.getCombatLevel() - combat) * 100_000L;
		if (questPoints < g.getQuestPoints())
		{
			cost += 1_000_000_000_000L;
		}
		return cost;
	}

	private static long xpToLevel(Client client, Skill skill, int level)
	{
		return Math.max(0, Experience.getXpForLevel(level) - client.getSkillExperience(skill));
	}

	/** State of any quest by RuneLite Quest name. Cached; call on the client thread. */
	QuestState questState(Client client, String questId)
	{
		QuestInfo q = byId.get(questId);
		if (q != null)
		{
			return stateOf(q);
		}
		return extraStates.computeIfAbsent(questId, id ->
		{
			try
			{
				return Quest.valueOf(id).getState(client);
			}
			catch (IllegalArgumentException e)
			{
				return QuestState.NOT_STARTED;
			}
		});
	}

	/** True if the quest is finished (cached state; false for quests this plugin doesn't know). */
	boolean isQuestFinished(String questId)
	{
		QuestInfo q = byId.get(questId);
		return q != null && isDone(q);
	}

	private boolean isDone(QuestInfo q)
	{
		return stateOf(q) == QuestState.FINISHED;
	}

	private QuestState stateOf(QuestInfo q)
	{
		return states.getOrDefault(q.getId(), QuestState.NOT_STARTED);
	}

	private static String describeGaps(Map<Skill, Integer> gaps)
	{
		StringBuilder sb = new StringBuilder();
		for (Map.Entry<Skill, Integer> e : gaps.entrySet())
		{
			if (sb.length() > 0)
			{
				sb.append(", ");
			}
			sb.append(e.getValue()).append(' ').append(e.getKey().getName());
		}
		return sb.toString();
	}
}
