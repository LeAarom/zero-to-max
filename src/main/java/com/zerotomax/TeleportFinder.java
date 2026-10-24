package com.zerotomax;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import lombok.Getter;
import net.runelite.api.Client;
import net.runelite.api.Item;
import net.runelite.api.ItemContainer;
import net.runelite.api.QuestState;
import net.runelite.api.Skill;
import net.runelite.api.coords.WorldPoint;
import net.runelite.api.gameval.InventoryID;
import net.runelite.api.gameval.ItemID;
import net.runelite.api.gameval.VarbitID;

/**
 * Picks the best way to get close to a destination: a teleport spell you can cast, a teleport
 * item you own (inventory, worn, or seen in your bank), or a fairy ring. Also points out a
 * teleport item you don't have yet if it would land much closer.
 * Call on the client thread.
 */
final class TeleportFinder
{
	enum Kind
	{
		SPELL,
		ITEM,
		FAIRY_RING
	}

	@Getter
	static final class Teleport
	{
		private final Kind kind;
		private final String name;
		private final WorldPoint landing;
		private final int[] itemIds;
		private final Map<Skill, Integer> skills = new LinkedHashMap<>();
		private final List<String> quests = new ArrayList<>();
		private final String varbits;
		private final String varps;
		private final String note;
		/** Spellbook the spell is on (SPELLBOOK varbit value), or -1. */
		private final int spellbook;

		Teleport(Kind kind, String name, int x, int y, int plane, String items, String skillReqs, String questReqs,
			String varbits, String varps, String note)
		{
			this.kind = kind;
			this.name = name;
			this.landing = new WorldPoint(x, y, plane);
			this.itemIds = items.isEmpty() ? new int[0] : java.util.Arrays.stream(items.split(",")).mapToInt(Integer::parseInt).toArray();
			for (String s : skillReqs.split(","))
			{
				if (!s.isEmpty())
				{
					String[] kv = s.split(":");
					try
					{
						skills.put(Skill.valueOf(kv[0]), Integer.parseInt(kv[1]));
					}
					catch (IllegalArgumentException ignored)
					{
						// unknown skill name: ignore the requirement
					}
				}
			}
			for (String q : questReqs.split(","))
			{
				if (!q.isEmpty())
				{
					quests.add(q);
				}
			}
			int book = -1;
			StringBuilder other = new StringBuilder();
			for (String tok : varbits.split(";"))
			{
				if (tok.startsWith(VarbitID.SPELLBOOK + "="))
				{
					book = Integer.parseInt(tok.substring(tok.indexOf('=') + 1));
				}
				else if (!tok.isEmpty())
				{
					other.append(other.length() > 0 ? ";" : "").append(tok);
				}
			}
			this.spellbook = book;
			this.varbits = other.toString();
			this.varps = varps;
			this.note = note;
		}
	}

	private static final String[] SPELLBOOKS = {"Standard", "Ancient", "Lunar", "Arceuus"};
	/** Extra "tiles" for a fairy ring, since you need to reach a ring first. */
	private static final int FAIRY_RING_PENALTY = 15;
	/** Only suggest a teleport if it saves at least this many tiles over walking. */
	private static final int MIN_SAVING = 20;

	private final List<Teleport> teleports;
	private final Set<Integer> teleportItemIds = new HashSet<>();
	private final Set<Integer> inventory = new HashSet<>();
	private final Set<Integer> worn = new HashSet<>();
	private final Set<Integer> bank = new HashSet<>();

	TeleportFinder(List<Teleport> teleports)
	{
		this.teleports = teleports;
		for (Teleport t : teleports)
		{
			for (int id : t.getItemIds())
			{
				teleportItemIds.add(id);
			}
		}
	}

	/** Refresh inventory and worn items from the client. */
	void refreshCarried(Client client)
	{
		fill(inventory, client.getItemContainer(InventoryID.INV));
		fill(worn, client.getItemContainer(InventoryID.WORN));
	}

	/** Remember which teleport items are in the bank. Returns the ids as text to save. */
	String updateBank(ItemContainer container)
	{
		fill(bank, container);
		StringBuilder sb = new StringBuilder();
		for (int id : bank)
		{
			sb.append(sb.length() > 0 ? "," : "").append(id);
		}
		return sb.toString();
	}

	/** Load bank teleport items saved from an earlier session. */
	void loadBank(String saved)
	{
		bank.clear();
		if (saved == null || saved.isEmpty())
		{
			return;
		}
		for (String s : saved.split(","))
		{
			try
			{
				bank.add(Integer.parseInt(s));
			}
			catch (NumberFormatException ignored)
			{
				// skip bad entries
			}
		}
	}

	/**
	 * A one-line suggestion for getting to dest, or null if walking is as good.
	 *
	 * @param questState state of a quest, by RuneLite Quest enum name
	 */
	String suggest(Client client, WorldPoint dest, Function<String, QuestState> questState)
	{
		WorldPoint player = client.getLocalPlayer().getWorldLocation();
		int walk = distance(player, dest);
		int currentBook = client.getVarbitValue(VarbitID.SPELLBOOK);
		boolean fairyRings = canUseFairyRings(client, questState);

		Teleport best = null;
		int bestScore = Integer.MAX_VALUE;
		Teleport betterUnowned = null;
		int unownedScore = Integer.MAX_VALUE;
		Teleport otherBook = null;
		int otherBookScore = Integer.MAX_VALUE;

		for (Teleport t : teleports)
		{
			if (!requirementsMet(client, t, questState))
			{
				continue;
			}
			int score = distance(t.getLanding(), dest);
			switch (t.getKind())
			{
				case SPELL:
					if (t.getSpellbook() != -1 && t.getSpellbook() != currentBook)
					{
						if (score < otherBookScore)
						{
							otherBookScore = score;
							otherBook = t;
						}
						continue;
					}
					break;
				case ITEM:
					if (!owns(t))
					{
						if (isBuyable(t) && score < unownedScore)
						{
							unownedScore = score;
							betterUnowned = t;
						}
						continue;
					}
					break;
				case FAIRY_RING:
					if (!fairyRings)
					{
						continue;
					}
					score += FAIRY_RING_PENALTY;
					break;
			}
			if (score < bestScore)
			{
				bestScore = score;
				best = t;
			}
		}

		StringBuilder out = new StringBuilder();
		if (best != null && bestScore + MIN_SAVING < walk)
		{
			out.append(describe(best)).append(" (lands ").append(distance(best.getLanding(), dest)).append(" tiles away)");
		}
		if (betterUnowned != null && unownedScore + MIN_SAVING < Math.min(walk, bestScore))
		{
			String item = baseName(betterUnowned);
			out.append(out.length() > 0 ? ". Faster: buy " : "Buy ").append(article(item)).append(' ').append(item)
				.append(" at the GE (");
			if (!placeName(betterUnowned).equals(item))
			{
				out.append(placeName(betterUnowned)).append(", ");
			}
			out.append("lands ").append(unownedScore).append(" tiles away)");
		}
		if (out.length() == 0 && otherBook != null && otherBookScore + MIN_SAVING < walk && otherBook.getSpellbook() < SPELLBOOKS.length)
		{
			out.append(otherBook.getName()).append(" on the ").append(SPELLBOOKS[otherBook.getSpellbook()])
				.append(" spellbook (lands ").append(otherBookScore).append(" tiles away)");
		}
		return out.length() == 0 ? null : out.toString();
	}

	private boolean requirementsMet(Client client, Teleport t, Function<String, QuestState> questState)
	{
		for (Map.Entry<Skill, Integer> e : t.getSkills().entrySet())
		{
			if (client.getRealSkillLevel(e.getKey()) < e.getValue())
			{
				return false;
			}
		}
		for (String q : t.getQuests())
		{
			if (questState.apply(q) != QuestState.FINISHED)
			{
				return false;
			}
		}
		return checkVars(t.getVarbits(), client, true) && checkVars(t.getVarps(), client, false);
	}

	/** "id=value;id>value;id<value" against varbits (or varps). */
	private static boolean checkVars(String spec, Client client, boolean varbit)
	{
		if (spec == null || spec.isEmpty())
		{
			return true;
		}
		for (String tok : spec.split(";"))
		{
			int op = Math.max(tok.indexOf('='), Math.max(tok.indexOf('>'), tok.indexOf('<')));
			if (op <= 0)
			{
				continue;
			}
			int id = Integer.parseInt(tok.substring(0, op));
			int want = Integer.parseInt(tok.substring(op + 1));
			int have = varbit ? client.getVarbitValue(id) : client.getVarpValue(id);
			char c = tok.charAt(op);
			if ((c == '=' && have != want) || (c == '>' && have <= want) || (c == '<' && have >= want))
			{
				return false;
			}
		}
		return true;
	}

	private boolean canUseFairyRings(Client client, Function<String, QuestState> questState)
	{
		if (questState.apply("FAIRYTALE_II__CURE_A_QUEEN") == QuestState.NOT_STARTED)
		{
			return false;
		}
		boolean staff = has(ItemID.DRAMEN_STAFF) || has(ItemID.LUNAR_MOONCLAN_LIMINAL_STAFF);
		return staff || client.getVarbitValue(VarbitID.LUMBRIDGE_ELITE_REWARD) >= 1;
	}

	private boolean owns(Teleport t)
	{
		for (int id : t.getItemIds())
		{
			if (has(id))
			{
				return true;
			}
		}
		return false;
	}

	private boolean has(int id)
	{
		return inventory.contains(id) || worn.contains(id) || bank.contains(id);
	}

	private void fill(Set<Integer> set, ItemContainer container)
	{
		if (container == null)
		{
			return;
		}
		set.clear();
		for (Item item : container.getItems())
		{
			if (teleportItemIds.contains(item.getId()) || item.getId() == ItemID.DRAMEN_STAFF
				|| item.getId() == ItemID.LUNAR_MOONCLAN_LIMINAL_STAFF)
			{
				set.add(item.getId());
			}
		}
	}

	private static String describe(Teleport t)
	{
		switch (t.getKind())
		{
			case SPELL:
				return "Cast " + t.getName() + (t.getNote().isEmpty() ? "" : " [" + t.getNote() + "]");
			case FAIRY_RING:
				return t.getName();
			default:
				return "Use " + baseName(t) + " -> " + placeName(t);
		}
	}

	/**
	 * Common teleport jewellery, tablets and scrolls that can be bought at the Grand Exchange.
	 * Quest and diary rewards (cloaks, seed pods, etc.) are left out of "buy" suggestions.
	 */
	private static boolean isBuyable(Teleport t)
	{
		String n = baseName(t).toLowerCase();
		return n.contains("necklace") || n.contains("ring of dueling") || n.contains("amulet of glory")
			|| n.contains("combat bracelet") || n.contains("ring of wealth") || n.contains("pendant")
			|| n.contains("burning amulet") || n.contains("slayer ring") || n.contains("tablet")
			|| n.contains("teleport scroll") || n.endsWith(" teleport");
	}

	private static String article(String word)
	{
		return "aeiouAEIOU".indexOf(word.charAt(0)) >= 0 ? "an" : "a";
	}

	/** "Games necklace: Burthorpe" -> "Games necklace". */
	private static String baseName(Teleport t)
	{
		int i = t.getName().indexOf(':');
		return i > 0 ? t.getName().substring(0, i).trim() : t.getName();
	}

	/** "Games necklace: Burthorpe" -> "Burthorpe". */
	private static String placeName(Teleport t)
	{
		int i = t.getName().indexOf(':');
		return i > 0 ? t.getName().substring(i + 1).trim() : t.getName();
	}

	/** Tiles between two points; different planes count a little extra. */
	private static int distance(WorldPoint a, WorldPoint b)
	{
		int d = Math.max(Math.abs(a.getX() - b.getX()), Math.abs(a.getY() - b.getY()));
		return a.getPlane() == b.getPlane() ? d : d + 5;
	}
}
