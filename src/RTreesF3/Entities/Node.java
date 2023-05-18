package RTreesF3.Entities;

import Auxiliar.MyArrayList;

public abstract class Node {

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

    abstract void compactParentRectangles(Hedge hedge);

    abstract void compactParentRectangles(Rectangle rectangle);

    abstract MyArrayList<Hedge> findFurthestHedges();

    abstract MyArrayList<Rectangle> findFurthestRectangles();

    abstract MyArrayList<Hedge> getHedges();

    abstract MyArrayList<Rectangle> getRectangles();

    public static Rectangle getMinimumRectangle(Point point, MyArrayList<Rectangle> rectangles) {


        //The point entered is the centerPoint of the rectangle

        //If the size of the rectangle is exactly the same as the point's, it means
        //that the point is one of the two furthest points and has to be introduced in
        //this rectangle (as the rectangle's dimensions were initialized with the point's coordinates)
        for (Rectangle rectangle: rectangles) {
            if (rectangle.centerPoint.x == point.x && rectangle.centerPoint.y == point.y) {
                return rectangle;
            }
        }

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

    protected double findPointsDistance(Point p1, Point p2) {
        return Math.sqrt(Math.pow(p2.x - p1.x, 2) + Math.pow(p2.y - p1.y, 2));
    }


}
