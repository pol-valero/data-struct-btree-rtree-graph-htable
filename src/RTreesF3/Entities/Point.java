package RTreesF3.Entities;

public class Point {

    public double x;
    public double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /*public boolean isMinPoint(Point point) {
        return x <= point.x && y <= point.y;
    }*/

    //Given two opposite corners of a rectangle (p1, p2) finds the TopRightPoint
    public static Point findTopRightPoint(Point p1, Point p2) {
        double maxX = Math.max(p1.x, p2.x);
        double maxY = Math.max(p1.y, p2.y);

        return new Point(maxX, maxY);
    }

    //Given two opposite corners of a rectangle (p1, p2) finds the BottomLeftPoint
    public static Point findBottomLeftPoint(Point p1, Point p2) {
        double minX = Math.min(p1.x, p2.x);
        double minY = Math.min(p1.y, p2.y);

        return new Point(minX, minY);
    }

    //Given two opposite corners of a rectangle (p1, p2) finds the TopLeftPoint
    public static Point findTopLeftPoint(Point p1, Point p2) {
        double minX = Math.min(p1.x, p2.x);
        double maxY = Math.max(p1.y, p2.y);

        return new Point(minX, maxY);
    }

    //Given two opposite corners of a rectangle (p1, p2) finds the BottomRightPoint
    public static Point findBottomRightPoint(Point p1, Point p2) {
        double maxX = Math.max(p1.x, p2.x);
        double minY = Math.min(p1.y, p2.y);

        return new Point(maxX, minY);
    }

}
