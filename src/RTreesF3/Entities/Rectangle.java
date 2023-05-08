package RTreesF3.Entities;

import Auxiliar.MyArrayList;

public class Rectangle implements IObject {

    public Point maxPoint;  //Top right corner
    public Point minPoint;  //Bottom left corner
    public Point centerPoint;  //Center point, used to measure distance between rectangles
    public MyArrayList<IObject> nodeObjects;
    private int height;

    public Rectangle(Point maxPoint, Point minPoint) {
        this.maxPoint = maxPoint;
        this.minPoint = minPoint;
        this.centerPoint = new Point((maxPoint.x + minPoint.x) / 2, (maxPoint.y + minPoint.y) / 2);
        this.nodeObjects = new MyArrayList<>();
    }

    public void addObject(IObject object) {
        nodeObjects.add(object);
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public boolean isLeaf() {
        return height == 0;
    }

    // Method that checks if the rectangle contains a certain point.
    public boolean containsPoint(Point point) {
        return maxPoint.x >= point.x && minPoint.x <= point.x && maxPoint.y >= point.y && minPoint.y <= point.y;
    }

    @Override
    public boolean isRectangle() {
        return true;
    }
}
