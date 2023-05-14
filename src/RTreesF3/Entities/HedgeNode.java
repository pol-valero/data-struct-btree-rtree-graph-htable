package RTreesF3.Entities;

import Auxiliar.MyArrayList;
import RTreesF3.Exceptions.NotRectangleNodeException;

public class HedgeNode extends Node{

    public MyArrayList<Hedge> hedges = new MyArrayList<>();

    @Override
    public boolean isLeaf() {
        return true;
    }

    @Override
    public void addElement(Rectangle rectangle) {
        throw new NotRectangleNodeException();
    }


    @Override
    public void addElement(Hedge hedge) {
        hedges.add(hedge);
    }

    @Override
    void removeElement(Rectangle rectangle) {
        throw new NotRectangleNodeException();
    }

    @Override
    void removeElement(Hedge hedge) {
        hedges.remove(hedge);
    }

    @Override
    public Rectangle getMinimumRectangle(Point point) {
        throw new NotRectangleNodeException();
    }

    @Override
    public int getSize() {
        return hedges.size();
    }

    public void expandParentRectangles(Hedge hedge) {

        Rectangle parentRectangle = parent;

        do {

            parentRectangle.expand(hedge);
            parentRectangle = parentRectangle.containerNode.parent;

        } while (parentRectangle != null);
    }

    @Override
    public void expandParentRectangles(Rectangle rectangle) {
        throw new NotRectangleNodeException();
    }

    @Override
    public void compactParentRectangles(Hedge hedge) {
        //TODO:
    }

    @Override
    public void compactParentRectangles(Rectangle rectangle) {
        throw new NotRectangleNodeException();
    }

    @Override
    MyArrayList<Hedge> findFurthestHedges() {

        MyArrayList<Hedge> furthestHedges = new MyArrayList<>(2);

        double distance;
        double bestDistance = 0;

        for (int i = 0; i < hedges.size(); i++) {

            for (int j = 0; j < hedges.size(); j++) {

                distance = findPointsDistance(hedges.get(i).getPoint(), hedges.get(j).getPoint());

                if (distance > bestDistance) {
                    bestDistance = distance;
                    furthestHedges.set(0, hedges.get(i));
                    furthestHedges.set(1, hedges.get(j));
                }

            }
        }

        return furthestHedges;

    }

    @Override
    MyArrayList<Rectangle> findFurthestRectangles() {
        throw new NotRectangleNodeException();
    }

    @Override
    MyArrayList<Hedge> getHedges() {
        return hedges;
    }

    @Override
    MyArrayList<Rectangle> getRectangles() {
        throw new NotRectangleNodeException();
    }


}
