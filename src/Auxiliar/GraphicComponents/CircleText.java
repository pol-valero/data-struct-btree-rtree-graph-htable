package Auxiliar.GraphicComponents;

import BinaryTreesF2.Entities.Citizen;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;

public class CircleText extends JPanel {
	private final String[] text;
	private final float value;
	private final int xCenterPos;
	private final int yCenterPos;
	private final int radius;
	private final static Font TEXT_FONT = new Font("Arial", Font.PLAIN, 12);
	private final static Font VALUE_FONT = new Font("Arial", Font.PLAIN, 12);
	private final boolean isRoot;

	public CircleText(String[] text, float value, int xCenterPos, int yCenterPos, int radius, boolean isRoot) {
		this.text = text;
		this.value = value;
		this.xCenterPos = xCenterPos;
		this.yCenterPos = yCenterPos;
		this.radius = radius;
		this.isRoot = isRoot;
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.BLACK);
		Graphics2D g2d = (Graphics2D) g;

		// Enable antialiasing to avoid seeing pixels in the circle.
		((Graphics2D)g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		// Draw the circle (inside a rectangle where (xo, yo) = (x-r, y-r) and (xf, yf) = (x+r, y+r).
		float diameter = radius * 2.0f;
		g2d.setStroke(new BasicStroke(2));	// Set the stroke to a width of 2 pixels.
		Shape circle = new Ellipse2D.Double(xCenterPos - radius, yCenterPos - radius, diameter, diameter);

		if (isRoot) {
			// Set the background color and fill the circle
			g2d.setPaint(Color.YELLOW);
		}
		else {
			// Set the background color and fill the circle
			g2d.setPaint(Color.LIGHT_GRAY);
		}

		g2d.fill(circle);
		g2d.setPaint(Color.BLACK);
		g2d.draw(circle);

		// Print all the text related to the node.
		int heightDistance = 0;
		for (String citizen : text) {

			// Center the names of the citizens below the circle depending on how long it is.
			g.setFont(TEXT_FONT);
			int[] nameCoords = TextCentering.centerText(g, citizen, xCenterPos, yCenterPos + 15, getHeight());

			// Draw the name of the citizen's node.
			g.drawString(citizen, nameCoords[0], nameCoords[1] + radius + heightDistance);
			heightDistance += 20;
		}

		// Center the value inside the circle depending on how long it is.
		g.setFont(VALUE_FONT);
		int[] valueCoords = TextCentering.centerText(g, String.valueOf(value), xCenterPos, yCenterPos + 1, getHeight());

		// Draw the value of the citizen's node.
		g.drawString(Float.toString(value), valueCoords[0], valueCoords[1]);

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
