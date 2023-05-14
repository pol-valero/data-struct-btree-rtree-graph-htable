package RTreesF3.Entities;

import Auxiliar.MyArrayList;

public abstract class Node {

    //TODO: Check if interface attributes are static and therefore this solution does not work (as each node should have a different parent)
    public Rectangle parent = null;

    abstract boolean isLeaf();

    abstract void addElement(Rectangle rectangle);

    abstract void addElement(Hedge hedge);

    abstract void removeElement(Rectangle rectangle);

    abstract void removeElement(Hedge hedge);

    abstract int getSize();

    abstract Rectangle getMinimumRectangle(Point point);

    abstract void expandParentRectangles(Hedge hedge);

    abstract void expandParentRectangles(Rectangle rectangle);

    abstract MyArrayList<Hedge> findFurthestHedges();

    abstract MyArrayList<Rectangle> findFurthestRectangles();

    abstract MyArrayList<Hedge> getHedges();

    abstract MyArrayList<Rectangle> getRectangles();

    public static Rectangle getMinimumRectangle(Point point, MyArrayList<Rectangle> rectangles) {

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
