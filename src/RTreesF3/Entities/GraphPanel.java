package RTreesF3.Entities;

import java.awt.*;
import java.util.Random;
import javax.swing.*;

public class GraphPanel extends JPanel {
    private int width = 1440;
    private int height = 900;
    private int padding = 25;
    private int labelPadding = 20;
    private Color lineColor = new Color(44, 102, 230, 180);
    private Color pointColor = new Color(100, 100, 100, 180);
    private Color gridColor = new Color(200, 200, 200, 200);
    private static final Stroke GRAPH_STROKE = new BasicStroke(2f);
    private int pointWidth = 4;
    private int numDivisions = 30;
    private double minX;
    private double maxX;
    private double minY;
    private double maxY;
    private RTreesF3.Entities.Rectangle[] rectangles;
    private RTreesF3.Entities.Point[] points;
    private int yDifference;
    private int xDifference;
    private int startX;
    private int startY;
    public GraphPanel(double minX, double maxX, double minY, double maxY, RTreesF3.Entities.Rectangle[] rectangles, Point[] points) {
        this.minX = minX;
        this.maxX = maxX;
        this.minY = minY;
        this.maxY = maxY;
        this.points = points;
        this.rectangles = rectangles;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Calculate scales for x and y axes
        double xScale = ((double) getWidth() - 2 * padding - labelPadding) / (maxX - minX);
        double yScale = ((double) getHeight() - 2 * padding - labelPadding) / (maxY - minY);

        // Calculate the width of the widest y-axis label
        g2.setFont(new Font("Arial", Font.PLAIN, 12));
        FontMetrics fontMetrics = g2.getFontMetrics();
        int yAxisLabelWidth = fontMetrics.stringWidth(String.format("%.5f", maxY));

        // Adjust the padding to accommodate the widest y-axis label
        padding = Math.max(padding, yAxisLabelWidth + 10);

        // Calculate the bottom-left point (x, y) where the grid starts
        startX = padding + labelPadding;
        startY = getHeight() - padding - labelPadding;

        // Draw the background grid
        g2.setColor(gridColor);

        // Draw the horizontal lines of the x axis.
        for (int i = 0; i < numDivisions + 1; i++) {
            int y = startY - i * (getHeight() - 2 * padding - labelPadding) / numDivisions;
            g2.drawLine(startX, y, getWidth() - padding, y);
        }

        // Draw the vertical lines of the y axis.
        for (int i = 0; i < numDivisions + 1; i++) {
            int x = startX + i * (getWidth() - 2 * padding - labelPadding) / numDivisions;
            g2.drawLine(x, padding, x, startY);
        }

        xDifference = (getWidth() - 2 * padding - labelPadding) / numDivisions;
        yDifference = (getHeight() - 2 * padding - labelPadding) / numDivisions;

        // Draw the x and y axes
        g2.setColor(Color.BLACK);
        g2.drawLine(startX, startY, startX, padding);
        g2.drawLine(startX, startY, getWidth() - padding, startY);

        // Draw axis labels and tick marks
        int xAxisLabelHeight = fontMetrics.getHeight() + 10;

        // X-axis labels and tick marks
        for (int i = 0; i <= numDivisions; i++) {
            int x = startX + i * (getWidth() - 2 * padding - labelPadding) / numDivisions;
            String xLabel = String.format("%.5f", minX + (i * (maxX - minX) / numDivisions));
            int labelWidth = fontMetrics.stringWidth(xLabel);

            // Girar el gráfico 90 grados en sentido antihorario
            g2.rotate(-Math.PI / 2, x, startY + xAxisLabelHeight + labelWidth / 2);

            // Dibujar el texto vertical
            g2.drawString(xLabel, x - 15, startY + xAxisLabelHeight + 5 + labelWidth / 2);

            // Restaurar la transformación original
            g2.rotate(Math.PI / 2, x, startY + xAxisLabelHeight + labelWidth / 2);

            g2.drawLine(x, startY, x, startY + 5);
        }

        // Y-axis labels and tick marks
        for (int i = 0; i <= numDivisions; i++) {
            int y = startY - i * (getHeight() - 2 * padding - labelPadding) / numDivisions;
            String yLabel = String.format("%.5f", minY + (i * (maxY - minY) / numDivisions));
            g2.drawString(yLabel, startX - yAxisLabelWidth - 12, y + fontMetrics.getHeight() / 2 - 2);
            g2.drawLine(startX, y, startX - 5, y);
        }

        // Bold font
        Font boldFont = new Font("Arial", Font.BOLD, 12);
        g2.setFont(boldFont);

        // Draw x-axis label
        g2.drawString("LONGITUDE", getWidth() / 2, startY + xAxisLabelHeight + 52);

        // Draw y-axis label
        g2.rotate(-Math.PI / 2, startX - yAxisLabelWidth - 12, getHeight() / 2);
        g2.drawString("LATITUDE", startX - yAxisLabelWidth, getHeight() / 2 - 5);
        g2.rotate(Math.PI / 2, startX - yAxisLabelWidth - 12, getHeight() / 2);

        // Draw rectangles
        g2.setColor(Color.RED);

        for (Rectangle rectangle : rectangles) {
            Random random = new Random();
            int red = random.nextInt(256);   // Valor de rojo entre 0 y 255
            int green = random.nextInt(256); // Valor de verde entre 0 y 255
            int blue = random.nextInt(256);  // Valor de azul entre 0 y 255
            g2.setColor(new Color(red, green, blue));
            // Calculate the normalized coordinates within the bounded area

            int x1 = (int) (startX +  (((rectangle.minPoint.x - minX) * xDifference) / ((maxX - minX) / numDivisions)));
            int y1 = (int) (startY -  (((rectangle.minPoint.y - minY) * yDifference) / ((maxY - minY) / numDivisions)));
            int x2 = (int) (startX +  (((rectangle.maxPoint.x - minX) * xDifference) / ((maxX - minX) / numDivisions)));
            int y2 = (int) (startY - (((rectangle.maxPoint.y - minY) * yDifference) / ((maxY - minY) / numDivisions)));

            g2.setStroke(new BasicStroke(2,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND));
            g2.drawLine(x1, y1, x1,y2);
            g2.drawLine(x1, y2, x2,y2);
            g2.drawLine(x1, y1, x2,y1);
            g2.drawLine(x2, y1, x2,y2);
        }

        g2.setStroke(new BasicStroke(1f));
        g2.setColor(Color.RED);

        for (Point point : points) {
            // Calculate the normalized coordinates within the bounded area

            int x1 = startX + (int) (((point.x - minX) * xDifference) / ((maxX - minX) / numDivisions));
            int y1 = startY - (int) (((point.y - minY) * yDifference) / ((maxY - minY) / numDivisions));

            g.fillOval(x1-3, y1 - 3, 6, 6);
        }

        g2.dispose();
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(width, height);
    }

    public static void createAndShowGui(double minX, double maxX, double minY, double maxY, Rectangle[] rectangles, Point[] points) {
        SwingUtilities.invokeLater(() -> {
            GraphPanel graphPanel = new GraphPanel(minX, maxX, minY, maxY, rectangles, points);
            JFrame frame = new JFrame("R Tree - Visual Representation");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.getContentPane().add(graphPanel);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}