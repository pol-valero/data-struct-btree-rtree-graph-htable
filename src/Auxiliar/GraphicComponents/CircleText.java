package Auxiliar.GraphicComponents;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;

public class CircleText extends JPanel {
	private final String text;
	private final float value;
	private final int xCenterPos;
	private final int yCenterPos;
	private final int radius;
	private final static int nameSpace = 20;
	private final Font TEXT_FONT;
	private final Font VALUE_FONT;

	public CircleText(String text, float value, int xCenterPos, int yCenterPos, int radius, Font textFont, Font valueFont) {
		this.text = text;
		this.value = value;
		this.xCenterPos = xCenterPos;
		this.yCenterPos = yCenterPos;
		this.radius = radius;
		this.TEXT_FONT = textFont;
		this.VALUE_FONT = valueFont;
	}

//	public void paintComponent(Graphics g) {
//		super.paintComponent(g);
//		int x = getWidth() / 2;
//		int y = getHeight() / 2;
//		int radius = Math.min(getWidth(), getHeight()) / 4;
//		g.setColor(Color.black);
//		g.drawOval(x - radius, y - radius, radius * 2, radius * 2);
//		g.drawString(text, x + 60, y + 25);
//		g.drawString(Float.toString(value), x - 10, y + 5);
//	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.BLACK);
		Graphics2D g2d = (Graphics2D) g;

		// Enable anti-aliasing to avoid seeing pixels in the circle.
		((Graphics2D)g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		// Draw the circle (inside a rectangle where (xo, yo) = (x-r, y-r) and (xf, yf) = (x+r, y+r).
		float diameter = radius * 2.0f;

		g2d.setStroke(new BasicStroke(2));	// Set the stroke to a width of 2 pixels.
		g.setFont(TEXT_FONT);

//		g2d.drawOval(xCenterPos - radius, yCenterPos - radius, diameter, diameter);
		Shape circle = new Ellipse2D.Double(xCenterPos - radius, yCenterPos - radius, diameter, diameter);
		g2d.draw(circle);

		// Center the name below the circle depending on how long it is.
		int[] nameCoords = TextCentering.centerText(g, text, xCenterPos, yCenterPos);

		// Draw the name of the citizen's node.
		g.drawString(text, nameCoords[0], nameCoords[1] + radius);

		// Center the value inside the circle depending on how long it is.
		FontMetrics fmValue = g.getFontMetrics();
		int valueWidth = fmValue.stringWidth(String.valueOf(value));
		int valueX = xCenterPos - (valueWidth / 2) + 1;	// + 1 is added to perfectly adjust the value
		int valueY = yCenterPos + (fmValue.getAscent() / 2);

		// Draw the value of the citizen's node.
		g.drawString(Float.toString(value), valueX, valueY);

		// Disable anti-aliases and restore all the properties.
		g2d.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_DEFAULT);
		g2d.setStroke(new BasicStroke(1f)); // Reset to default stroke
	}

	@Override
	public void paintBorder(Graphics g) {
		Insets insets = getInsets();
		int x = insets.left;
		int y = insets.top;
		int width = getWidth() - insets.left - insets.right;
		int height = getHeight() - insets.top - insets.bottom;
		g.setColor(Color.RED);
		g.drawRect(x, y, width, height);
	}
}
