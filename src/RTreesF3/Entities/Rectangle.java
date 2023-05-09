package RTreesF3.Entities;

import Auxiliar.MyArrayList;

public class Rectangle implements IObject {

    public Point maxPoint;  //Top right corner
    public Point minPoint;  //Bottom left corner
    public Point centerPoint;  //Center point, used to measure distance between rectangles
    public MyArrayList<IObject> nodeObjects;

    public Rectangle(Point maxPoint, Point minPoint) {
        this.maxPoint = maxPoint;
        this.minPoint = minPoint;
        this.centerPoint = new Point((maxPoint.x + minPoint.x) / 2, (maxPoint.y + minPoint.y) / 2);
        this.nodeObjects = new MyArrayList<>();
    }

    public void addObject(IObject object) {
        nodeObjects.add(object);
    }

    public boolean isLeaf() {
        return !nodeObjects.get(0).isRectangle();
    }

    // Method that checks if the rectangle contains a certain point.
    public boolean containsPoint(Point point) {
        return maxPoint.x >= point.x && minPoint.x <= point.x && maxPoint.y >= point.y && minPoint.y <= point.y;
    }

    @Override
    public boolean isRectangle() {
        return true;
    }

    public void setMaxPoint(Point maxPoint) {
        this.maxPoint = maxPoint;
    }

    public void setMinPoint(Point minPoint) {
        this.minPoint = minPoint;
    }
}
