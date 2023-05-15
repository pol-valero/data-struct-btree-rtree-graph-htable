package RTreesF3.Entities;

import Auxiliar.MyArrayList;

public class Rectangle {

    public Point maxPoint;  //Top right corner
    public Point minPoint;  //Bottom left corner
    public Point centerPoint;  //Center point, used to measure distance between rectangles

    public Node containerNode;  //Node that contains this rectangle, among other rectangles
    public Node childNode; //Child node of this rectangle, which can contain other rectangles or hedges

    public Rectangle(Node containerNode, Node childNode, Point maxPoint, Point minPoint) {

        childNode.parent = this;

        this.containerNode = containerNode;
        this.childNode = childNode;
        this.maxPoint = maxPoint;
        this.minPoint = minPoint;
        this.centerPoint = new Point((maxPoint.x + minPoint.x) / 2, (maxPoint.y + minPoint.y) / 2);
    }

    // Method that checks if the rectangle contains a certain point.
    public boolean containsPoint(Point point) {
        return maxPoint.x >= point.x && minPoint.x <= point.x && maxPoint.y >= point.y && minPoint.y <= point.y;
    }

    public static boolean containsPoint(Point rectangleMinPoint, Point rectangleMaxPoint, Point point) {
        return rectangleMaxPoint.x >= point.x && rectangleMinPoint.x <= point.x && rectangleMaxPoint.y >= point.y && rectangleMinPoint.y <= point.y;
    }

    public double calculatePerimeterIncrease(Point point) {

        double perimeterIncrease = 0;

        if (point.x < minPoint.x) {
            perimeterIncrease += 2 * (minPoint.x - point.x);
        }

        if (point.x > maxPoint.x) {
            perimeterIncrease += 2 * (point.x - maxPoint.x);
        }

        if (point.y < minPoint.y) {
            perimeterIncrease += 2 * (minPoint.y - point.y);
        }

        if (point.y > maxPoint.y) {
            perimeterIncrease += 2 * (point.y - maxPoint.y);
        }

        return perimeterIncrease;

    }

    //This function adjusts the size of the rectangle so that it can contain all the specified "pointsInside"
    private void adjustRectangle(MyArrayList<Point> pointsInside) {

        double maxX = Double.MIN_VALUE;
        double maxY = Double.MIN_VALUE;
        double minX = Double.MAX_VALUE;
        double minY = Double.MAX_VALUE;

        for (Point point : pointsInside) {

            if (point.x > maxX) {
                maxX = point.x;
            }

            if (point.x < minX) {
                minX = point.x;
            }

            if (point.y > maxY) {
                maxY = point.y;
            }

            if (point.y < minY) {
                minY = point.y;
            }

        }

        maxPoint = new Point(maxX, maxY);
        minPoint = new Point(minX, minY);
        centerPoint = new Point((maxX + minX) / 2, (maxY + minY) / 2);

    }

    public void expand(Rectangle rectangle) {

        MyArrayList<Point> pointsInsideRectangle = new MyArrayList<>();

        pointsInsideRectangle.add(rectangle.maxPoint);
        pointsInsideRectangle.add(rectangle.minPoint);
        pointsInsideRectangle.add(maxPoint);
        pointsInsideRectangle.add(minPoint);

        adjustRectangle(pointsInsideRectangle);

    }

    public void expand(Hedge hedge) {

        MyArrayList<Point> pointsInsideRectangle = new MyArrayList<>();

        pointsInsideRectangle.add(hedge.getPoint());
        pointsInsideRectangle.add(maxPoint);
        pointsInsideRectangle.add(minPoint);

        adjustRectangle(pointsInsideRectangle);

    }



}
