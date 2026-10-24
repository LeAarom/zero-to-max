package com.zerotomax;

import com.google.inject.Provides;
import com.zerotomax.farming.FarmTracker;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.List;
import java.util.function.Predicate;
import javax.inject.Inject;
import lombok.Getter;
import net.runelite.api.Client;
import net.runelite.api.GameState;
import net.runelite.api.coords.WorldPoint;
import net.runelite.api.events.GameStateChanged;
import net.runelite.api.events.GameTick;
import net.runelite.api.events.ItemContainerChanged;
import net.runelite.api.gameval.InventoryID;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.EventBus;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.events.PluginMessage;
import net.runelite.client.input.KeyManager;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.ClientToolbar;
import net.runelite.client.ui.NavigationButton;
import net.runelite.client.ui.overlay.OverlayManager;
import net.runelite.client.util.HotkeyListener;

@PluginDescriptor(
	name = "Zero to Max Guide",
	description = "Works out your next quest or skill goal from your account, all the way to Quest cape and max",
	tags = {"quest", "skill", "max", "guide", "gps", "route", "quest cape"}
)
public class ZeroToMaxPlugin extends Plugin
{
	/** Wait this many ticks after login so quest data has loaded. */
	private static final int LOGIN_SETTLE_TICKS = 5;
	/** Work out the plan this often (game ticks are 0.6s). */
	private static final int REPLAN_EVERY_TICKS = 3;
	/** Re-read all quest states this often (each check runs a game script). */
	private static final int QUEST_REFRESH_EVERY_TICKS = 10;

	@Inject
	private Client client;

	@Inject
	private ZeroToMaxConfig config;

	@Inject
	private ConfigManager configManager;

	@Inject
	private KeyManager keyManager;

	@Inject
	private EventBus eventBus;

	@Inject
	private OverlayManager overlayManager;

	@Inject
	private ClientToolbar clientToolbar;

	@Inject
	private ZeroToMaxOverlay overlay;

	@Inject
	private ZeroToMaxWorldOverlay worldOverlay;

	@Inject
	private ZeroToMaxPanel panel;

	private NavigationButton navButton;
	private Planner planner;
	private FarmTracker farmTracker;
	private TeleportFinder teleportFinder;
	private SlayerHelper slayerHelper;
	private boolean bankLoaded;
	/** Last destination sent to Shortest Path, so we only send changes. */
	private WorldPoint sentToShortestPath;

	/** Farm run the player started from the panel, or null. Set on the Swing thread. */
	private volatile FarmTracker.RunType activeRun;
	private volatile boolean replanNow;
	private volatile String runStatusText = "Pick a run to start the GPS.";

	private int ticksSinceLogin;
	private int ticksSincePlan;
	private int ticksSinceQuestRefresh;
	private ZeroToMaxConfig.TrainingStyle lastStyle;
	private boolean lastIncludeDiaries;

	// Written on the client thread, read by overlays and the panel -> volatile
	@Getter
	private volatile String accountStatusText = "Log in to start";
	@Getter
	private volatile String currentStepTitle = "Analyzing account...";
	@Getter
	private volatile String activeMethodName = "";
	@Getter
	private volatile String currentStepInstruction = "Please wait...";
	@Getter
	private volatile String gpsStatusText = "-";
	@Getter
	private volatile Color gpsColor = Color.CYAN;
	@Getter
	private volatile String progressText = "";
	@Getter
	private volatile WorldPoint activeDestination = null;
	/** e.g. "Herbs ready, Birdhouses ready" - shown on the overlay when no run is active. */
	@Getter
	private volatile String farmAlertText = "";
	/** Current Slayer task and how to do it, for the overlay. Empty when off or no task. */
	@Getter
	private volatile String slayerText = "";

	private final HotkeyListener hotkeyListener = new HotkeyListener(() -> config.cycleStyleHotkey())
	{
		@Override
		public void hotkeyPressed()
		{
			ZeroToMaxConfig.TrainingStyle[] all = ZeroToMaxConfig.TrainingStyle.values();
			ZeroToMaxConfig.TrainingStyle next = all[(config.trainingStyle().ordinal() + 1) % all.length];
			configManager.setConfiguration(ZeroToMaxConfig.GROUP, "trainingStyle", next);
		}
	};

	@Provides
	ZeroToMaxConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(ZeroToMaxConfig.class);
	}

	@Override
	protected void startUp()
	{
		planner = new Planner(QuestData.all(), DiaryData.all());
		farmTracker = new FarmTracker(client, configManager);
		teleportFinder = new TeleportFinder(TeleportData.all());
		slayerHelper = new SlayerHelper(SlayerData.all(), SlayerData.bosses());
		bankLoaded = false;
		activeRun = null;
		panel.setRunListener(type ->
		{
			activeRun = type;
			runStatusText = type == null ? "Run stopped." : type.getLabel() + " started - follow the line.";
			replanNow = true;
		});
		ticksSinceLogin = 0;
		ticksSincePlan = REPLAN_EVERY_TICKS;
		ticksSinceQuestRefresh = QUEST_REFRESH_EVERY_TICKS;
		lastStyle = null;

		overlayManager.add(overlay);
		overlayManager.add(worldOverlay);
		keyManager.registerKeyListener(hotkeyListener);

		BufferedImage icon = new BufferedImage(24, 24, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = icon.createGraphics();
		g.setColor(Color.GREEN);
		g.fillOval(4, 4, 16, 16);
		g.dispose();

		navButton = NavigationButton.builder()
			.tooltip("Zero to Max Guide")
			.icon(icon)
			.priority(5)
			.panel(panel)
			.build();
		clientToolbar.addNavigation(navButton);
	}

	@Override
	protected void shutDown()
	{
		overlayManager.remove(overlay);
		overlayManager.remove(worldOverlay);
		keyManager.unregisterKeyListener(hotkeyListener);
		panel.setRunListener(null);
		activeRun = null;
		clearShortestPath();
		if (navButton != null)
		{
			clientToolbar.removeNavigation(navButton);
			navButton = null;
		}
		activeDestination = null;
	}

	@Subscribe
	public void onGameStateChanged(GameStateChanged event)
	{
		GameState state = event.getGameState();
		if (state == GameState.LOGIN_SCREEN || state == GameState.LOGGING_IN || state == GameState.HOPPING)
		{
			ticksSinceLogin = 0;
			ticksSincePlan = REPLAN_EVERY_TICKS;
			ticksSinceQuestRefresh = QUEST_REFRESH_EVERY_TICKS;
			bankLoaded = false;
		}
	}

	@Subscribe
	public void onItemContainerChanged(ItemContainerChanged event)
	{
		if (event.getContainerId() == InventoryID.BANK && teleportFinder != null)
		{
			String saved = teleportFinder.updateBank(event.getItemContainer());
			configManager.setRSProfileConfiguration(ZeroToMaxConfig.GROUP, "bankTeleportItems", saved);
		}
	}

	@Subscribe
	public void onGameTick(GameTick event)
	{
		if (client.getGameState() != GameState.LOGGED_IN || client.getLocalPlayer() == null)
		{
			return;
		}

		if (ticksSinceLogin < LOGIN_SETTLE_TICKS)
		{
			ticksSinceLogin++;
			currentStepTitle = "Reading your account...";
			return;
		}

		if (!bankLoaded)
		{
			teleportFinder.loadBank(configManager.getRSProfileConfiguration(ZeroToMaxConfig.GROUP, "bankTeleportItems"));
			bankLoaded = true;
		}

		ZeroToMaxConfig.TrainingStyle style = config.trainingStyle();
		boolean includeDiaries = config.includeDiaries();
		ticksSincePlan++;
		ticksSinceQuestRefresh++;
		if (!replanNow && ticksSincePlan < REPLAN_EVERY_TICKS && style == lastStyle && includeDiaries == lastIncludeDiaries)
		{
			return;
		}
		ticksSincePlan = 0;
		replanNow = false;
		lastStyle = style;
		lastIncludeDiaries = includeDiaries;

		boolean refreshQuests = ticksSinceQuestRefresh >= QUEST_REFRESH_EVERY_TICKS;
		if (refreshQuests)
		{
			ticksSinceQuestRefresh = 0;
		}
		Planner.Plan plan = planner.plan(client, style, refreshQuests, includeDiaries);

		accountStatusText = plan.summary;
		Predicate<String> questDone = planner::isQuestFinished;
		teleportFinder.refreshCarried(client);
		teleportFinder.setHouse(config.jewelleryBox().ordinal(), config.houseSpiritTree(), config.houseFairyRing(), config.useMaxHouses());

		FarmTracker.RunType run = activeRun;
		FarmTracker.FarmStep farmStep = run == null ? null : farmTracker.nextStep(run, questDone);
		if (run != null && farmStep == null)
		{
			runStatusText = run.getLabel() + " complete! Check the timers for when to come back.";
			activeRun = null;
			run = null;
		}

		if (farmStep != null)
		{
			currentStepTitle = farmStep.getTitle();
			activeMethodName = "";
			currentStepInstruction = farmStep.getInstruction();
			progressText = farmStep.getProgress();
			updateGps(farmStep.getDestination(), farmStep.getTeleport());
		}
		else
		{
			currentStepTitle = plan.title;
			activeMethodName = plan.method;
			currentStepInstruction = plan.instruction;
			progressText = plan.progress;
			updateGps(plan.destination, plan.teleport);
		}

		updateFarming(run, questDone);
		updateSlayer(style);
		panel.update(currentStepTitle, activeMethodName, currentStepInstruction, gpsStatusText, progressText);
		panel.setRoadmap(plan.roadmap);
	}

	private void updateSlayer(ZeroToMaxConfig.TrainingStyle style)
	{
		if (!config.showSlayer())
		{
			slayerText = "";
			panel.setSlayer(java.util.Collections.singletonList("Turned off in settings."));
			return;
		}
		slayerHelper.update(client);
		slayerText = slayerHelper.overlayLine(style);
		panel.setSlayer(slayerHelper.panelLines());
	}

	private void updateFarming(FarmTracker.RunType run, Predicate<String> questDone)
	{
		String herb = farmTracker.timerText(FarmTracker.RunType.HERB, questDone);
		String tree = farmTracker.timerText(FarmTracker.RunType.TREE, questDone);
		String fruit = farmTracker.timerText(FarmTracker.RunType.FRUIT_TREE, questDone);
		String birds = farmTracker.birdhouseTimerText();

		List<String> ready = new ArrayList<>();
		if (herb.startsWith("Ready"))
		{
			ready.add("Herbs");
		}
		if (tree.startsWith("Ready"))
		{
			ready.add("Trees");
		}
		if (fruit.startsWith("Ready"))
		{
			ready.add("Fruit trees");
		}
		if (birds.startsWith("Ready"))
		{
			ready.add("Birdhouses");
		}
		farmAlertText = run != null || ready.isEmpty() ? "" : String.join(", ", ready) + " ready";

		FarmTracker.RunType shown = run != null ? run : FarmTracker.RunType.HERB;
		panel.setFarming(herb, tree, fruit, birds, runStatusText,
			"What to bring: " + shown.getLabel(), farmTracker.checklist(shown, questDone));
	}

	private void updateGps(WorldPoint dest, String teleGuide)
	{
		activeDestination = dest;
		sendToShortestPath(dest);

		if (dest == null)
		{
			gpsStatusText = teleGuide;
			gpsColor = Color.CYAN;
			return;
		}

		if (client.getTopLevelWorldView().isInstance())
		{
			gpsStatusText = "In an instance (e.g. your POH)";
			gpsColor = Color.GREEN;
			return;
		}

		int distance = client.getLocalPlayer().getWorldLocation().distanceTo2D(dest);
		if (distance > 55)
		{
			String suggestion = teleportFinder.suggest(client, dest, id -> planner.questState(client, id));
			gpsStatusText = suggestion != null ? suggestion : teleGuide;
			gpsColor = Color.CYAN;
		}
		else if (distance > 8)
		{
			gpsStatusText = "Follow the line (" + distance + " tiles)";
			gpsColor = Color.ORANGE;
		}
		else
		{
			gpsStatusText = "You've arrived";
			gpsColor = Color.GREEN;
		}
	}

	/**
	 * Shortest Path (Plugin Hub) draws a walkable route when sent a target through a PluginMessage.
	 * If it isn't installed the message is simply ignored.
	 */
	private void sendToShortestPath(WorldPoint dest)
	{
		if (!config.useShortestPath())
		{
			clearShortestPath();
			return;
		}
		if (Objects.equals(dest, sentToShortestPath))
		{
			return;
		}
		if (dest == null)
		{
			clearShortestPath();
			return;
		}
		Map<String, Object> data = new HashMap<>();
		data.put("target", dest);
		eventBus.post(new PluginMessage("shortestpath", "path", data));
		sentToShortestPath = dest;
	}

	private void clearShortestPath()
	{
		if (sentToShortestPath != null)
		{
			eventBus.post(new PluginMessage("shortestpath", "clear", new HashMap<>()));
			sentToShortestPath = null;
		}
	}
}
