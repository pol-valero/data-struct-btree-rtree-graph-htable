package RTreesF3.Entities;

import Auxiliar.MyArrayList;
import RTreesF3.Exceptions.NotHedgeNodeException;
import RTreesF3.Exceptions.NotRectangleNodeException;


public class RectangleNode extends Node {

    public MyArrayList<Rectangle> rectangles;


    @Override
    public boolean isLeaf() {
        return false;
    }

    @Override
    public void addElement(Rectangle rectangle) {
        rectangles.add(rectangle);
    }

    @Override
    public void addElement(Hedge hedge) {
        throw new NotHedgeNodeException();
    }

    @Override
    void removeElement(Rectangle rectangle) {
        rectangles.remove(rectangle);
    }

    @Override
    void removeElement(Hedge hedge) {
        throw new NotHedgeNodeException();
    }

    @Override
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

    @Override
    public int getSize() {
        return rectangles.size();
    }

    @Override
    public void expandParentRectangles(Hedge hedge) {
        throw new NotHedgeNodeException();
    }

    @Override
    public void expandParentRectangles(Rectangle rectangle) {

        Rectangle parentRectangle = parent;

        do {

            parentRectangle.expand();
            parentRectangle = parentRectangle.containerNode.parent;

        } while (parentRectangle != null);
    }

    @Override
    MyArrayList<Hedge> findFurthestHedges() {
        throw new NotRectangleNodeException();
    }

    @Override
    MyArrayList<Rectangle> findFurthestRectangles() {
        //TODO:
        return null;
    }

}
