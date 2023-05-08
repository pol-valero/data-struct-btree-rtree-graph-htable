package RTreesF3.Entities;

import Auxiliar.MyArrayList;

public class Rectangle {

    public Point maxPoint;  //Top right corner
    public Point minPoint;  //Bottom left corner
    public Point centerPoint;  //Center point, used to measure distance between rectangles

    private Node containingNode;  //Node that contains this rectangle, among other rectangles
    private Node childNode; //Child node of this rectangle, which can contain other rectangles or hedges

    public Rectangle(Point maxPoint, Point minPoint) {
        this.maxPoint = maxPoint;
        this.minPoint = minPoint;
        this.centerPoint = new Point((maxPoint.x + minPoint.x) / 2, (maxPoint.y + minPoint.y) / 2);
    }

    // Method that checks if the rectangle contains a certain point.
    public boolean containsPoint(Point point) {
        return maxPoint.x >= point.x && minPoint.x <= point.x && maxPoint.y >= point.y && minPoint.y <= point.y;
    }

    public double calculatePerimeterIncrease(Point point) {
        //TODO: Calculate the perimeter increase for the rectangle to contain the point
        return 0;

    }

}
