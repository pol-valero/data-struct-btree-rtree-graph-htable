package RTreesF3.Entities;

import Auxiliar.MyArrayList;
import RTreesF3.Exceptions.NotHedgeNodeException;


public class RectangleNode extends Node {

    public MyArrayList<Rectangle> rectangles = new MyArrayList<>();


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
    public Rectangle getRectangle(Point point) {

        //Returns the rectangle that contains the point.
        for (Rectangle rectangle: rectangles) {
            if (rectangle.containsPoint(point)) {
                return rectangle;
            }
        }

        //We return null if it does not exist
        return null;

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

        while (parentRectangle != null) {

            parentRectangle.expand(rectangle);
            parentRectangle = parentRectangle.containerNode.parent;

        }

    }

    @Override
    MyArrayList<Hedge> findFurthestHedges() {
        throw new NotHedgeNodeException();
    }

    @Override
    MyArrayList<Rectangle> findFurthestRectangles() {

        MyArrayList<Rectangle> furthestRectangles = new MyArrayList<>(2);

        double distance;
        double bestDistance = 0;

        for (int i = 0; i < rectangles.size(); i++) {

            for (int j = 0; j < rectangles.size(); j++) {

                distance = findPointsDistance(rectangles.get(i).centerPoint, rectangles.get(j).centerPoint);

                if (distance > bestDistance) {
                    bestDistance = distance;
                    furthestRectangles.set(0, rectangles.get(i));
                    furthestRectangles.set(1, rectangles.get(j));
                }

            }
        }

        return furthestRectangles;

    }

    @Override
    MyArrayList<Hedge> getHedges() {
        throw new NotHedgeNodeException();
    }

    @Override
    MyArrayList<Rectangle> getRectangles() {
        return rectangles;
    }

}
