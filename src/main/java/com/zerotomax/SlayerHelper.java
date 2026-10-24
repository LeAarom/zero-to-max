package com.zerotomax;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import net.runelite.api.Client;
import net.runelite.api.gameval.DBTableID;
import net.runelite.api.gameval.VarPlayerID;
import net.runelite.api.gameval.VarbitID;

/**
 * Reads your current Slayer task (the same way RuneLite's Slayer plugin does) and shows the
 * best way to do it from SlayerData. No prayer advice: that's not allowed for Plugin Hub plugins.
 * Call update() on the client thread.
 */
final class SlayerHelper
{
	/** Boss task id, from RuneLite's Slayer plugin ([proc,helper_slayer_current_assignment]). */
	private static final int BOSS_TASK_ID = 98;

	static final class TaskAdvice
	{
		final String task;
		final int slayerLevel;
		final String location;
		final String fastest;
		final String afk;
		final String cannon;
		final String burst;
		final String items;
		final String advice;
		final String adviceNote;

		TaskAdvice(String task, int slayerLevel, String location, String fastest, String afk, String cannon,
			String burst, String items, String advice, String adviceNote)
		{
			this.task = task;
			this.slayerLevel = slayerLevel;
			this.location = location;
			this.fastest = fastest;
			this.afk = afk;
			this.cannon = cannon;
			this.burst = burst;
			this.items = items;
			this.advice = advice;
			this.adviceNote = adviceNote;
		}
	}

	private final Map<String, TaskAdvice> advice = new HashMap<>();
	private final Set<String> bosses = new HashSet<>();

	private int lastTaskId = -1;
	private int lastBossId = -1;
	private int lastAreaId = -1;
	private String taskName;
	private String area;
	private int remaining;

	SlayerHelper(List<TaskAdvice> data, List<String> bossTasks)
	{
		for (TaskAdvice a : data)
		{
			advice.put(a.task.toLowerCase(Locale.ROOT), a);
		}
		for (String b : bossTasks)
		{
			bosses.add(b.toLowerCase(Locale.ROOT));
		}
	}

	/** Re-reads the task. Only does the database lookups when the task changes. */
	void update(Client client)
	{
		remaining = client.getVarpValue(VarPlayerID.SLAYER_COUNT);
		if (remaining <= 0)
		{
			taskName = null;
			area = null;
			lastTaskId = -1;
			return;
		}

		int taskId = client.getVarpValue(VarPlayerID.SLAYER_TARGET);
		int bossId = client.getVarbitValue(VarbitID.SLAYER_TARGET_BOSSID);
		int areaId = client.getVarpValue(VarPlayerID.SLAYER_AREA);
		if (taskId == lastTaskId && bossId == lastBossId && areaId == lastAreaId && taskName != null)
		{
			return;
		}
		lastTaskId = taskId;
		lastBossId = bossId;
		lastAreaId = areaId;

		Integer row = null;
		if (taskId == BOSS_TASK_ID)
		{
			List<Integer> bossRows = client.getDBRowsByValue(DBTableID.SlayerTaskSublist.ID,
				DBTableID.SlayerTaskSublist.COL_TASK_SUBTABLE_ID, 0, bossId);
			if (!bossRows.isEmpty())
			{
				row = (Integer) client.getDBTableField(bossRows.get(0), DBTableID.SlayerTaskSublist.COL_TASK, 0)[0];
			}
		}
		else
		{
			List<Integer> rows = client.getDBRowsByValue(DBTableID.SlayerTask.ID, DBTableID.SlayerTask.COL_ID, 0, taskId);
			if (!rows.isEmpty())
			{
				row = rows.get(0);
			}
		}
		taskName = row == null ? null : (String) client.getDBTableField(row, DBTableID.SlayerTask.COL_NAME_UPPERCASE, 0)[0];

		area = null;
		if (areaId > 0)
		{
			List<Integer> areaRows = client.getDBRowsByValue(DBTableID.SlayerArea.ID, DBTableID.SlayerArea.COL_AREA_ID, 0, areaId);
			if (!areaRows.isEmpty())
			{
				area = (String) client.getDBTableField(areaRows.get(0), DBTableID.SlayerArea.COL_AREA_NAME_IN_HELPER, 0)[0];
			}
		}
	}

	boolean hasTask()
	{
		return taskName != null && remaining > 0;
	}

	/** Short line for the overlay, using the method that fits the training style. */
	String overlayLine(ZeroToMaxConfig.TrainingStyle style)
	{
		if (!hasTask())
		{
			return "";
		}
		String head = remaining + " x " + taskName;
		if (isBoss())
		{
			return head + " (boss task)";
		}
		TaskAdvice a = find();
		if (a == null)
		{
			return head;
		}
		String how = style == ZeroToMaxConfig.TrainingStyle.CHILL_AFK && !a.afk.isEmpty() ? a.afk : a.fastest;
		return head + (how.isEmpty() ? "" : " - " + how);
	}

	/** Lines for the side panel. */
	List<String> panelLines()
	{
		List<String> out = new ArrayList<>();
		if (!hasTask())
		{
			out.add("No task. Get one from a Slayer master.");
			return out;
		}
		out.add(remaining + " x " + taskName + (area != null ? " (in " + area + ")" : ""));
		if (isBoss())
		{
			out.add("Boss task: see the wiki's strategy page for this boss.");
			return out;
		}
		TaskAdvice a = find();
		if (a == null)
		{
			out.add("No advice for this task yet.");
			return out;
		}
		add(out, "Where: ", area != null ? area : a.location);
		add(out, "Fastest: ", a.fastest);
		add(out, "AFK: ", a.afk);
		add(out, "Cannon: ", a.cannon);
		add(out, "Burst/barrage: ", a.burst);
		add(out, "Bring: ", a.items);
		if (!a.advice.isEmpty())
		{
			out.add("Advice: " + a.advice + (a.adviceNote.isEmpty() ? "" : " - " + a.adviceNote));
		}
		return out;
	}

	private boolean isBoss()
	{
		return lastTaskId == BOSS_TASK_ID || (taskName != null && bosses.contains(taskName.toLowerCase(Locale.ROOT)));
	}

	private TaskAdvice find()
	{
		return taskName == null ? null : advice.get(taskName.toLowerCase(Locale.ROOT));
	}

	private static void add(List<String> out, String label, String value)
	{
		if (value != null && !value.isEmpty())
		{
			out.add(label + value);
		}
	}
}
