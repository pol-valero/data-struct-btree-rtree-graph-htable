package RTreesF3.Entities;

import Auxiliar.MyArrayList;

public abstract class Node {

    //TODO: Check if interface attributes are static and therefore this solution does not work (as each node should have a different parent)
    public Rectangle parent = null;

    abstract boolean isLeaf();

    abstract void addElement(Rectangle rectangle);

    abstract void addElement(Hedge hedge);

    abstract void removeElement(Rectangle rectangle);

    abstract void removeElement(Hedge hedge);

    abstract int getSize();

    abstract Rectangle getMinimumRectangle(Point point);

    abstract void expandParentRectangles(Hedge hedge);

    abstract void expandParentRectangles(Rectangle rectangle);

    abstract MyArrayList<Hedge> findFurthestHedges();

    abstract MyArrayList<Rectangle> findFurthestRectangles();



}
