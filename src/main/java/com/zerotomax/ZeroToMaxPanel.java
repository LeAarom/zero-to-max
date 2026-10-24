package com.zerotomax;

import com.zerotomax.farming.FarmTracker;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import net.runelite.client.ui.ColorScheme;
import net.runelite.client.ui.FontManager;
import net.runelite.client.ui.PluginPanel;

/**
 * Side panel. Black and white: white for what matters now, grey for everything else.
 * Every section can be folded away by clicking its title.
 */
@Singleton
public class ZeroToMaxPanel extends PluginPanel
{
	private static final Color BACKGROUND = ColorScheme.DARK_GRAY_COLOR;
	private static final Color CARD = ColorScheme.DARKER_GRAY_COLOR;
	private static final Color LINE = new Color(0x3A, 0x3A, 0x3A);
	private static final Color TEXT = Color.WHITE;
	private static final Color MUTED = new Color(0x9A, 0x9A, 0x9A);
	private static final Color CHIP = new Color(0x2A, 0x2A, 0x2A);
	private static final Color CHIP_HOVER = new Color(0x3C, 0x3C, 0x3C);
	private static final String WHITE_HEX = "#ffffff";
	private static final String MUTED_HEX = "#9a9a9a";
	private static final String DIM_HEX = "#6a6a6a";
	private static final int TEXT_WIDTH = 135;

	private final ZeroToMaxConfig config;

	private final JLabel objectiveLabel = body();
	private final JLabel timersLabel = body();
	private final JLabel runStatusLabel = body();
	private final JLabel checklistLabel = body();
	private final JLabel slayerLabel = body();
	private final JLabel roadmapLabel = body();
	private final Map<FarmTracker.RunType, RunChip> chips = new EnumMap<>(FarmTracker.RunType.class);

	/** Set by the plugin. Called on the Swing thread with the run to start, or null to stop. */
	private volatile Consumer<FarmTracker.RunType> runListener;

	@Inject
	ZeroToMaxPanel(ZeroToMaxConfig config)
	{
		super();
		this.config = config;

		setLayout(new BorderLayout());
		setBackground(BACKGROUND);
		setBorder(new EmptyBorder(10, 10, 10, 10));

		JPanel container = new JPanel();
		container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
		container.setBackground(BACKGROUND);

		container.add(header());
		container.add(gap(10));

		container.add(section("Current objective", objectiveLabel));
		container.add(gap(8));
		container.add(section("Run timers", timersLabel));
		container.add(gap(8));

		JPanel runBody = column();
		JPanel grid = new JPanel(new GridLayout(4, 2, 4, 4));
		grid.setOpaque(false);
		grid.setAlignmentX(Component.LEFT_ALIGNMENT);
		addChip(grid, "Herbs", FarmTracker.RunType.HERB);
		addChip(grid, "Trees", FarmTracker.RunType.TREE);
		addChip(grid, "Fruit trees", FarmTracker.RunType.FRUIT_TREE);
		addChip(grid, "Hardwood", FarmTracker.RunType.HARDWOOD);
		addChip(grid, "Calquat", FarmTracker.RunType.CALQUAT);
		addChip(grid, "Spirit trees", FarmTracker.RunType.SPIRIT_TREE);
		addChip(grid, "Birdhouses", FarmTracker.RunType.BIRDHOUSE);
		grid.add(new RunChip("Stop", null));
		runBody.add(grid);
		runBody.add(gap(8));
		runBody.add(runStatusLabel);
		runBody.add(gap(6));
		runBody.add(checklistLabel);
		container.add(section("Farm run GPS", runBody));
		container.add(gap(8));

		container.add(section("Slayer task", slayerLabel));
		container.add(gap(8));
		container.add(section("Roadmap", roadmapLabel));

		add(container, BorderLayout.NORTH);
	}

	void setRunListener(Consumer<FarmTracker.RunType> listener)
	{
		this.runListener = listener;
	}

	/** Safe to call from any thread. */
	public void update(String title, String method, String instruction, String teleport, String progress)
	{
		StringBuilder sb = open();
		sb.append("<b><font color='").append(WHITE_HEX).append("'>").append(escape(title)).append("</font></b><br/>");
		String style = config.trainingStyle().toString()
			+ (method == null || method.isEmpty() ? "" : " · " + method);
		row(sb, "Style", style);
		row(sb, "Do", instruction);
		row(sb, "Teleport", teleport);
		if (progress != null && !progress.isEmpty())
		{
			row(sb, "Progress", progress);
		}
		String html = close(sb);
		SwingUtilities.invokeLater(() -> objectiveLabel.setText(html));
	}

	public void setSlayer(List<String> lines)
	{
		StringBuilder sb = open();
		boolean first = true;
		for (String line : lines)
		{
			int colon = line.indexOf(": ");
			if (first)
			{
				sb.append("<b><font color='").append(WHITE_HEX).append("'>").append(escape(line)).append("</font></b><br/>");
				first = false;
			}
			else if (colon > 0 && colon < 16)
			{
				row(sb, line.substring(0, colon), line.substring(colon + 2));
			}
			else
			{
				sb.append(escape(line)).append("<br/>");
			}
		}
		String html = close(sb);
		SwingUtilities.invokeLater(() -> slayerLabel.setText(html));
	}

	public void setRoadmap(List<String> steps)
	{
		StringBuilder sb = open();
		int i = 1;
		for (String step : steps)
		{
			String color = i == 1 ? WHITE_HEX : MUTED_HEX;
			sb.append("<font color='").append(DIM_HEX).append("'>").append(i).append("&nbsp;&nbsp;</font>")
				.append("<font color='").append(color).append("'>").append(escape(step)).append("</font><br/>");
			i++;
		}
		String html = close(sb);
		SwingUtilities.invokeLater(() -> roadmapLabel.setText(html));
	}

	/** Safe to call from any thread. Timers are "Name: value" lines. */
	public void setFarming(FarmTracker.RunType active, List<String> timers, String runStatus,
		String checklistTitle, List<String> checklist)
	{
		StringBuilder t = new StringBuilder("<html><table width='" + (TEXT_WIDTH + 20) + "' cellpadding='1' cellspacing='0'>");
		for (String timer : timers)
		{
			int colon = timer.indexOf(": ");
			String name = colon > 0 ? timer.substring(0, colon) : timer;
			String value = colon > 0 ? timer.substring(colon + 2) : "";
			boolean ready = value.startsWith("Ready");
			t.append("<tr><td><font color='").append(ready ? WHITE_HEX : MUTED_HEX).append("'>").append(escape(name))
				.append("</font></td><td align='right'>")
				.append(ready ? "<b><font color='" + WHITE_HEX + "'>Ready</font></b>"
					: "<font color='" + DIM_HEX + "'>" + escape(value) + "</font>")
				.append("</td></tr>");
		}
		String timersHtml = t.append("</table></html>").toString();

		StringBuilder s = open();
		s.append("<font color='").append(WHITE_HEX).append("'>").append(escape(runStatus)).append("</font>");
		String statusHtml = close(s);

		StringBuilder c = open();
		c.append("<font color='").append(DIM_HEX).append("'>").append(escape(checklistTitle).toUpperCase()).append("</font><br/>");
		for (String item : checklist)
		{
			if (item.endsWith(":"))
			{
				c.append("<br/><font color='").append(DIM_HEX).append("'>").append(escape(item)).append("</font><br/>");
			}
			else
			{
				c.append("<font color='").append(DIM_HEX).append("'>–&nbsp;</font>").append(escape(item)).append("<br/>");
			}
		}
		String checklistHtml = close(c);

		SwingUtilities.invokeLater(() ->
		{
			timersLabel.setText(timersHtml);
			runStatusLabel.setText(statusHtml);
			runStatusLabel.setVisible(runStatus != null && !runStatus.isEmpty());
			checklistLabel.setText(checklistHtml);
			for (Map.Entry<FarmTracker.RunType, RunChip> e : chips.entrySet())
			{
				e.getValue().setSelected(e.getKey() == active);
			}
		});
	}

	// ------------------------------------------------------------------ building blocks

	private JPanel header()
	{
		JPanel p = column();
		JLabel title = new JLabel("ZERO TO MAX");
		title.setFont(FontManager.getRunescapeBoldFont());
		title.setForeground(TEXT);
		JLabel sub = new JLabel("Quest cape · diaries · max");
		sub.setFont(FontManager.getRunescapeSmallFont());
		sub.setForeground(MUTED);
		p.add(title);
		p.add(sub);
		p.add(gap(8));
		JPanel line = new JPanel();
		line.setBackground(LINE);
		line.setAlignmentX(Component.LEFT_ALIGNMENT);
		line.setMaximumSize(new Dimension(Integer.MAX_VALUE, 1));
		line.setPreferredSize(new Dimension(0, 1));
		p.add(line);
		return p;
	}

	/** A card with a title you can click to fold the body away. */
	private JPanel section(String title, Component content)
	{
		JPanel card = new JPanel(new BorderLayout());
		card.setBackground(CARD);
		card.setAlignmentX(Component.LEFT_ALIGNMENT);
		card.setBorder(BorderFactory.createCompoundBorder(
			BorderFactory.createMatteBorder(1, 1, 1, 1, LINE),
			new EmptyBorder(8, 9, 9, 9)));

		JPanel head = new JPanel(new BorderLayout());
		head.setOpaque(false);
		head.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		JLabel name = new JLabel(title.toUpperCase());
		name.setFont(FontManager.getRunescapeSmallFont());
		name.setForeground(MUTED);
		JLabel arrow = new JLabel("−");
		arrow.setForeground(MUTED);
		head.add(name, BorderLayout.WEST);
		head.add(arrow, BorderLayout.EAST);

		JPanel bodyWrap = column();
		bodyWrap.setBorder(new EmptyBorder(6, 0, 0, 0));
		if (content instanceof JLabel)
		{
			((JLabel) content).setAlignmentX(Component.LEFT_ALIGNMENT);
		}
		bodyWrap.add(content);

		head.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				boolean show = !bodyWrap.isVisible();
				bodyWrap.setVisible(show);
				arrow.setText(show ? "−" : "+");
				card.revalidate();
			}

			@Override
			public void mouseEntered(MouseEvent e)
			{
				name.setForeground(TEXT);
				arrow.setForeground(TEXT);
			}

			@Override
			public void mouseExited(MouseEvent e)
			{
				name.setForeground(MUTED);
				arrow.setForeground(MUTED);
			}
		});

		card.add(head, BorderLayout.NORTH);
		card.add(bodyWrap, BorderLayout.CENTER);
		return card;
	}

	private void addChip(JPanel grid, String text, FarmTracker.RunType type)
	{
		RunChip chip = new RunChip(text, type);
		chips.put(type, chip);
		grid.add(chip);
	}

	/** Flat black-and-white button. White when its run is active. */
	private final class RunChip extends JLabel
	{
		private boolean selected;
		private boolean hover;

		RunChip(String text, FarmTracker.RunType type)
		{
			super(text, SwingConstants.CENTER);
			setOpaque(true);
			setFont(FontManager.getRunescapeSmallFont());
			setBorder(new EmptyBorder(5, 2, 5, 2));
			setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			paintState();
			addMouseListener(new MouseAdapter()
			{
				@Override
				public void mouseClicked(MouseEvent e)
				{
					Consumer<FarmTracker.RunType> l = runListener;
					if (l != null)
					{
						l.accept(type);
					}
					for (Map.Entry<FarmTracker.RunType, RunChip> c : chips.entrySet())
					{
						c.getValue().setSelected(c.getKey() == type);
					}
				}

				@Override
				public void mouseEntered(MouseEvent e)
				{
					hover = true;
					paintState();
				}

				@Override
				public void mouseExited(MouseEvent e)
				{
					hover = false;
					paintState();
				}
			});
		}

		void setSelected(boolean selected)
		{
			if (this.selected != selected)
			{
				this.selected = selected;
				paintState();
			}
		}

		private void paintState()
		{
			setBackground(selected ? TEXT : hover ? CHIP_HOVER : CHIP);
			setForeground(selected ? Color.BLACK : hover ? TEXT : MUTED);
		}
	}

	private static JLabel body()
	{
		JLabel l = new JLabel();
		l.setForeground(MUTED);
		l.setAlignmentX(Component.LEFT_ALIGNMENT);
		return l;
	}

	private static JPanel column()
	{
		JPanel p = new JPanel();
		p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
		p.setOpaque(false);
		p.setAlignmentX(Component.LEFT_ALIGNMENT);
		return p;
	}

	private static JPanel gap(int height)
	{
		JPanel s = new JPanel();
		s.setOpaque(false);
		s.setAlignmentX(Component.LEFT_ALIGNMENT);
		s.setPreferredSize(new Dimension(0, height));
		s.setMaximumSize(new Dimension(Integer.MAX_VALUE, height));
		return s;
	}

	private static StringBuilder open()
	{
		return new StringBuilder("<html><div style='width:" + TEXT_WIDTH + "px'>");
	}

	private static String close(StringBuilder sb)
	{
		return sb.append("</div></html>").toString();
	}

	/** Grey label, white value. */
	private static void row(StringBuilder sb, String label, String value)
	{
		sb.append("<font color='").append(DIM_HEX).append("'>").append(escape(label)).append("</font>&nbsp;&nbsp;")
			.append("<font color='").append(WHITE_HEX).append("'>").append(escape(value)).append("</font><br/>");
	}

	private static String escape(String s)
	{
		return s == null ? "-" : s.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;");
	}
}
