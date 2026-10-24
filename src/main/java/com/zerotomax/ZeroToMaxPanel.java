package com.zerotomax;

import com.zerotomax.farming.FarmTracker;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.List;
import java.util.function.Consumer;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import net.runelite.client.ui.ColorScheme;
import net.runelite.client.ui.PluginPanel;

@Singleton
public class ZeroToMaxPanel extends PluginPanel
{
	private final ZeroToMaxConfig config;

	private final JLabel currentTitleLabel = new JLabel("Loading...");
	private final JLabel activeMethodLabel = new JLabel();
	private final JLabel instructionLabel = new JLabel();
	private final JLabel teleportLabel = new JLabel();
	private final JLabel progressLabel = new JLabel();
	private final JLabel herbTimerLabel = new JLabel("Herbs: -");
	private final JLabel treeTimerLabel = new JLabel("Trees: -");
	private final JLabel fruitTimerLabel = new JLabel("Fruit trees: -");
	private final JLabel birdhouseTimerLabel = new JLabel("Birdhouses: -");
	private final JLabel activeRunLabel = new JLabel();
	private final JLabel checklistTitleLabel = new JLabel();
	private final JLabel checklistLabel = new JLabel();
	private final JLabel roadmapLabel = new JLabel();
	private final JLabel slayerLabel = new JLabel();

	/** Set by the plugin. Called on the Swing thread with the run to start, or null to stop. */
	private volatile Consumer<FarmTracker.RunType> runListener;

	@Inject
	ZeroToMaxPanel(ZeroToMaxConfig config)
	{
		super();
		this.config = config;

		setLayout(new BorderLayout());
		setBorder(new EmptyBorder(10, 10, 10, 10));

		JPanel container = new JPanel();
		container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
		container.setBackground(ColorScheme.DARK_GRAY_COLOR);

		JLabel header = new JLabel("Zero-to-Max Guide");
		header.setForeground(Color.GREEN);
		header.setFont(header.getFont().deriveFont(15f));
		header.setBorder(new EmptyBorder(0, 0, 8, 0));
		container.add(header);

		JPanel activeBox = createSection("CURRENT OBJECTIVE");
		currentTitleLabel.setForeground(Color.YELLOW);
		activeMethodLabel.setForeground(Color.MAGENTA);
		instructionLabel.setForeground(Color.WHITE);
		teleportLabel.setForeground(Color.CYAN);
		progressLabel.setForeground(Color.ORANGE);
		activeBox.add(currentTitleLabel);
		activeBox.add(activeMethodLabel);
		activeBox.add(instructionLabel);
		activeBox.add(teleportLabel);
		activeBox.add(progressLabel);
		container.add(activeBox);
		container.add(spacer());

		JPanel timerBox = createSection("RUN TIMERS (from Time Tracking)");
		for (JLabel l : new JLabel[]{herbTimerLabel, treeTimerLabel, fruitTimerLabel, birdhouseTimerLabel})
		{
			l.setForeground(Color.CYAN);
			timerBox.add(l);
		}
		container.add(timerBox);
		container.add(spacer());

		JPanel runBox = createSection("FARM RUN GPS");
		JPanel buttons = new JPanel(new GridLayout(3, 2, 4, 4));
		buttons.setOpaque(false);
		buttons.add(runButton("Herb run", FarmTracker.RunType.HERB));
		buttons.add(runButton("Tree run", FarmTracker.RunType.TREE));
		buttons.add(runButton("Fruit trees", FarmTracker.RunType.FRUIT_TREE));
		buttons.add(runButton("Birdhouses", FarmTracker.RunType.BIRDHOUSE));
		buttons.add(runButton("Stop run", null));
		buttons.setAlignmentX(LEFT_ALIGNMENT);
		runBox.add(buttons);
		activeRunLabel.setForeground(Color.YELLOW);
		runBox.add(activeRunLabel);
		checklistTitleLabel.setForeground(Color.GREEN);
		runBox.add(checklistTitleLabel);
		checklistLabel.setForeground(Color.LIGHT_GRAY);
		runBox.add(checklistLabel);
		container.add(runBox);
		container.add(spacer());

		JPanel slayerBox = createSection("SLAYER TASK");
		slayerLabel.setForeground(Color.LIGHT_GRAY);
		slayerBox.add(slayerLabel);
		container.add(slayerBox);
		container.add(spacer());

		JPanel roadmapBox = createSection("ROADMAP");
		roadmapLabel.setForeground(Color.LIGHT_GRAY);
		roadmapBox.add(roadmapLabel);
		container.add(roadmapBox);

		add(container, BorderLayout.NORTH);
	}

	void setRunListener(Consumer<FarmTracker.RunType> listener)
	{
		this.runListener = listener;
	}

	/** Safe to call from any thread. */
	public void update(String title, String method, String instruction, String teleport, String progress)
	{
		SwingUtilities.invokeLater(() ->
		{
			currentTitleLabel.setText(wrap(title));
			activeMethodLabel.setText(wrap("Style: " + config.trainingStyle()
				+ (method == null || method.isEmpty() ? "" : " | " + method)));
			instructionLabel.setText(wrap("Action: " + instruction));
			teleportLabel.setText(wrap("Teleport: " + teleport));
			progressLabel.setText(wrap(progress == null || progress.isEmpty() ? "" : "Progress: " + progress));
		});
	}

	public void setSlayer(List<String> lines)
	{
		SwingUtilities.invokeLater(() -> slayerLabel.setText(wrapLines(lines)));
	}

	public void setRoadmap(List<String> steps)
	{
		SwingUtilities.invokeLater(() -> roadmapLabel.setText(wrapList(steps)));
	}

	/** Safe to call from any thread. */
	public void setFarming(String herb, String tree, String fruit, String birdhouse,
		String activeRun, String checklistTitle, List<String> checklist)
	{
		SwingUtilities.invokeLater(() ->
		{
			herbTimerLabel.setText("Herbs: " + herb);
			treeTimerLabel.setText("Trees: " + tree);
			fruitTimerLabel.setText("Fruit trees: " + fruit);
			birdhouseTimerLabel.setText("Birdhouses: " + birdhouse);
			activeRunLabel.setText(wrap(activeRun));
			checklistTitleLabel.setText(wrap(checklistTitle));
			checklistLabel.setText(wrapList(checklist));
		});
	}

	private JButton runButton(String text, FarmTracker.RunType type)
	{
		JButton b = new JButton(text);
		b.addActionListener(e ->
		{
			Consumer<FarmTracker.RunType> l = runListener;
			if (l != null)
			{
				l.accept(type);
			}
		});
		return b;
	}

	private static String wrap(String text)
	{
		return "<html><div style='width:160px'>" + escape(text) + "</div></html>";
	}

	private static String wrapList(List<String> items)
	{
		StringBuilder sb = new StringBuilder("<html><div style='width:160px'>");
		int i = 1;
		for (String item : items)
		{
			sb.append(i++).append(". ").append(escape(item)).append("<br/>");
		}
		return sb.append("</div></html>").toString();
	}

	private static String wrapLines(List<String> lines)
	{
		StringBuilder sb = new StringBuilder("<html><div style='width:160px'>");
		for (String line : lines)
		{
			sb.append(escape(line)).append("<br/>");
		}
		return sb.append("</div></html>").toString();
	}

	private static String escape(String s)
	{
		return s == null ? "-" : s.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;");
	}

	private JPanel createSection(String title)
	{
		JPanel box = new JPanel();
		box.setLayout(new BoxLayout(box, BoxLayout.Y_AXIS));
		box.setBackground(ColorScheme.DARKER_GRAY_COLOR);
		box.setBorder(new EmptyBorder(8, 8, 8, 8));
		JLabel titleLbl = new JLabel(title);
		titleLbl.setForeground(Color.GREEN);
		box.add(titleLbl);
		return box;
	}

	private JPanel spacer()
	{
		JPanel s = new JPanel();
		s.setOpaque(false);
		s.setPreferredSize(new Dimension(0, 10));
		return s;
	}
}
