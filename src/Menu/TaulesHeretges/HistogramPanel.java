package Menu.TaulesHeretges;
import java.awt.*;
import java.util.List;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.*;


/**
 * extret de -> https://stackoverflow.com/questions/29708147/custom-graph-java-swing
 */
public class HistogramPanel extends JPanel {
    private int histogramHeight = 200;
    private int barWidth = 50;
    private int barGap = 10;

    private JPanel barPanel;
    private JPanel labelPanel;

    private List<Bar> bars = new ArrayList<Bar>();

    public HistogramPanel()
    {
        setBorder( new EmptyBorder(10, 10, 10, 10) );
        setLayout( new BorderLayout() );

        barPanel = new JPanel( new GridLayout(1, 0, barGap, 0) );
        Border outer = new MatteBorder(1, 1, 1, 1, Color.BLACK);
        Border inner = new EmptyBorder(10, 10, 0, 10);
        Border compound = new CompoundBorder(outer, inner);
        barPanel.setBorder( compound );

        labelPanel = new JPanel( new GridLayout(1, 0, barGap, 0) );
        labelPanel.setBorder( new EmptyBorder(5, 10, 0, 10) );

        add(barPanel, BorderLayout.CENTER);
        add(labelPanel, BorderLayout.PAGE_END);
    }

    public void addHistogramColumn(String label, int value, Color color)
    {
        Bar bar = new Bar(label, value, color);
        bars.add( bar );
    }

    public void layoutHistogram()
    {
        barPanel.removeAll();
        labelPanel.removeAll();

        int maxValue = 0;

        for (Bar bar: bars)
            maxValue = Math.max(maxValue, bar.getValue());

        for (Bar bar: bars)
        {
            JLabel label;
            String sLabel = bar.getValue() + "";

            if (bar.getValue() == 0) {
                label = new JLabel("");
            } else {
                label = new JLabel(sLabel);
            }

            label.setHorizontalTextPosition(JLabel.CENTER);
            label.setHorizontalAlignment(JLabel.CENTER);
            label.setVerticalTextPosition(JLabel.TOP);
            label.setVerticalAlignment(JLabel.BOTTOM);
            int barHeight = (bar.getValue() * histogramHeight) / maxValue;
            Icon icon = new ColorIcon(bar.getColor(), barWidth, barHeight);
            label.setIcon( icon );
            barPanel.add( label );

            JLabel barLabel = new JLabel( bar.getLabel() );
            barLabel.setHorizontalAlignment(JLabel.CENTER);
            labelPanel.add( barLabel );
        }
    }

    private class Bar
    {
        private String label;
        private int value;
        private Color color;

        public Bar(String label, int value, Color color)
        {
            this.label = label;
            this.value = value;
            this.color = color;
        }

        public String getLabel()
        {
            return label;
        }

        public int getValue()
        {
            return value;
        }

        public Color getColor()
        {
            return color;
        }
    }

    private class ColorIcon implements Icon
    {
        private int shadow = 3;

        private Color color;
        private int width;
        private int height;

        public ColorIcon(Color color, int width, int height)
        {
            this.color = color;
            this.width = width;
            this.height = height;
        }

        public int getIconWidth()
        {
            return width;
        }

        public int getIconHeight()
        {
            return height;
        }

        public void paintIcon(Component c, Graphics g, int x, int y)
        {
            g.setColor(color);
            g.fillRect(x, y, width - shadow, height);
            g.setColor(Color.GRAY);
            g.fillRect(x + width - shadow, y + shadow, shadow, height - shadow);
        }
    }

    public void addHistogramColumn(String msg, int value, Color color, HistogramPanel panel) {
        panel.addHistogramColumn(msg, value, color);
    }

    public void ShowGUI(HistogramPanel panel) {
        panel.layoutHistogram();
        JFrame frame = new JFrame("Histogram Window");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 600);

        JPanel generalPanel = new JPanel(new BorderLayout());

        JInternalFrame internalFrame1 = new JInternalFrame("Histogram Legend", true, true, true, true);
        internalFrame1.setSize(300, 200);
        internalFrame1.setVisible(true);

        // Crea los JLabel con diferentes colores
        JLabel label1 = new JLabel("KING");
        label1.setForeground(Color.RED);
        label1.setOpaque(true);

        JLabel label2 = new JLabel("Label 2");
        label2.setForeground(Color.GREEN);
        label2.setOpaque(true);

        JLabel label3 = new JLabel("Label 3");
        label3.setForeground(Color.BLUE);
        label3.setOpaque(true);

        // Configura el layout del contenido del JInternalFrame
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        internalFrame1.setContentPane(contentPanel);

        // Agrega los JLabel al contenido del JInternalFrame
        contentPanel.add(label1);
        contentPanel.add(label2);
        contentPanel.add(label3);

        generalPanel.add(internalFrame1);

        JInternalFrame internalFrame2 = new JInternalFrame("Histogram", true, true, true, true);
        internalFrame2.setSize(1200, 600);
        internalFrame2.setLocation(320, 0);
        internalFrame2.setVisible(true);
        internalFrame2.add(panel);
        generalPanel.add(internalFrame2);

        frame.getContentPane().add(generalPanel, BorderLayout.CENTER);
        frame.setVisible(true);
    }
}