package Auxiliar.GraphicComponents;

import java.awt.*;

public class TextCentering {
	public static int[] centerText(Graphics g, String textToCenter, int xCenterPos, int yCenterPos) {
		FontMetrics fmName = g.getFontMetrics();
		int nameWidth = fmName.stringWidth(textToCenter);

		int[] coords = new int[2];
		coords[0] = xCenterPos - (nameWidth / 2);
		coords[1] = yCenterPos + (fmName.getAscent() / 2) + 10;

		return coords;
	}
}
