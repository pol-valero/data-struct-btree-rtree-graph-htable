package RTreesF3.Entities;

public interface Node {

    //TODO: Check if interface attributes are static and therefore this solution does not work (as each node should have a different parent)
    Rectangle parent = null;

    boolean isLeaf();

    void addElement(Rectangle rectangle);

    void addElement(Hedge hedge);

    int getSize();

    Rectangle getMinimumRectangle(Point point);

    void expandParentRectangles(Hedge hedge);

    void expandParentRectangles(Rectangle rectangle);


}
