package Auxiliar.GraphicComponents;

import java.awt.*;

public class TextCentering {

	// Note: The font has to be set before centering the text so that it takes it into account when centering the text.
	public static int[] centerText(Graphics g, String textToCenter, int xCenterPos, int yCenterPos, int height) {
		Graphics2D g2d = (Graphics2D) g.create();
		FontMetrics fm = g2d.getFontMetrics();
		int textWidth = fm.stringWidth(textToCenter);

		int[] coords = new int[2];
		coords[0] = xCenterPos - (textWidth / 2);
		coords[1] = yCenterPos + (((height - fm.getHeight()) / 2) + fm.getAscent());

		return coords;
	}
}
