package RTreesF3.Entities;

import Auxiliar.MyArrayList;

public class RNode {
    private MyArrayList<NodeObject> nodeObjects = new MyArrayList<>();

    private int height;

    public void setHeight(int height) {
        this.height = height;
    }

    public boolean isLeaf() {
        return height == 0;
    }

    public void addObject(NodeObject object) {
        nodeObjects.add(object);
    }

    public MyArrayList<NodeObject> getNodeObjects() {
        return nodeObjects;
    }
}
