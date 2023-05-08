package RTreesF3.Entities;

import Auxiliar.MyArrayList;


public class RectangleNode implements Node {

    private MyArrayList<Rectangle> rectangles;

    @Override
    public boolean isLeaf() {
        return false;
    }

    public Rectangle getMinimumRectangle(Point point) {

        //We return the rectangle that contains the point, if it exists
        for (Rectangle rectangle: rectangles) {
            if (rectangle.containsPoint(point)) {
                return rectangle;
            }
        }

        //If no rectangle contains the point, we return the rectangle that has to "expand" less to contain the point
        double minimumDistance = Double.MAX_VALUE;
        Rectangle minimumRectangle = null;

        for (Rectangle rectangle: rectangles) {

            double distance = rectangle.calculatePerimeterIncrease(point);

            if (distance < minimumDistance) {
                minimumRectangle = rectangle;
                minimumDistance = distance;
            }

        }
        return minimumRectangle;

    }
}
