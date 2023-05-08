package RTreesF3.Entities;

public class Hedge implements IObject {

    private String type;
    private float size;
    private Point point;
    private String color;

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

    @Override
    public boolean isRectangle() {
        return false;
    }
}
