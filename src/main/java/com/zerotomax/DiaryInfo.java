package com.zerotomax;

import java.util.LinkedHashMap;
import java.util.Map;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Client;
import net.runelite.api.Skill;
import net.runelite.api.coords.WorldPoint;

/** One achievement diary tier, built from the rows in DiaryData. */
@Slf4j
final class DiaryInfo implements Goal
{
	@Getter
	private final String id;
	@Getter
	private final String area;
	@Getter
	private final String tier;
	/** 0 = Easy, 3 = Elite. */
	@Getter
	private final int tierRank;
	private final int rewardVarbit;
	@Getter
	private final Map<Skill, Integer> skillRequirements = new LinkedHashMap<>();
	@Getter
	private final Map<String, Boolean> questRequirements = new LinkedHashMap<>();
	@Getter
	private final int questPoints;
	@Getter
	private final int combatLevel;
	/** True if the diary needs things this plugin can't check (items, Warriors' Guild access...). */
	@Getter
	private final boolean extraRequirements;
	/** Where you claim the reward. */
	@Getter
	private final WorldPoint claimLocation;
	@Getter
	private final String claimText;

	DiaryInfo(String id, String area, String tier, int tierRank, int rewardVarbit, String skills, String prereqs,
		int questPoints, int combatLevel, boolean extraRequirements, int x, int y, int plane, String claimText)
	{
		this.id = id;
		this.area = area;
		this.tier = tier;
		this.tierRank = tierRank;
		this.rewardVarbit = rewardVarbit;
		this.questPoints = questPoints;
		this.combatLevel = combatLevel;
		this.extraRequirements = extraRequirements;
		this.claimLocation = new WorldPoint(x, y, plane);
		this.claimText = claimText;

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
				log.warn("Unknown skill in diary data: {}", part);
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
	}

	@Override
	public String getName()
	{
		return area + " " + tier + " Diary";
	}

	/** Done once the reward has been claimed. Read on the client thread. */
	boolean isDone(Client client)
	{
		return client.getVarbitValue(rewardVarbit) >= 1;
	}
}
