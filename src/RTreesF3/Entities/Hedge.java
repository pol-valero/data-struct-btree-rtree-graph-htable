package RTreesF3.Entities;

import static java.lang.Math.sqrt;

public class Hedge{

    private String type;
    private float size;
    private Point point;
    private String color;
    public Node containerNode; //This attribute is not always used. Make sure it is not null before using.

    public Hedge(String type, float size, double latitude, double longitude, String color) {
        this.type = type;
        this.size = size;
        this.color = color;

        point = new Point(longitude, latitude);
    }

    public String getType() {
        return type;
    }

    public float getSize() {
        return size;
    }

    public Point getPoint() {
        return point;
    }

    public String getColor() {
        return color;
    }

    private double calculateDistance(Point p1, Point p2) {
        return sqrt((p1.x - p2.x) * (p1.x - p2.x) + (p1.y - p2.y) * (p1.y - p2.y));
    }
}
