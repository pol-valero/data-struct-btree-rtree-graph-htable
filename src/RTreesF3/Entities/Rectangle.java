package RTreesF3.Entities;

public class Rectangle extends NodeObject {

    public Point maxPoint;  //Top right corner
    public Point minPoint;  //Bottom left corner
    public Point centerPoint;  //Center point, used to measure distance between rectangles

    public Rectangle(Point maxPoint, Point minPoint) {
        this.maxPoint = maxPoint;
        this.minPoint = minPoint;
        this.centerPoint = new Point((maxPoint.x + minPoint.x) / 2, (maxPoint.y + minPoint.y) / 2);
    }

}
