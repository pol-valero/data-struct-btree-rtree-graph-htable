package RTreesF3.Entities;

import Auxiliar.MyArrayList;

public class RTree {
    private static final int MAX_NODE_SIZE = 3;
    private static final int MIN_NODE_SIZE = MAX_NODE_SIZE / 3;

    private Node root;

    public RTree () {

        root = new RectangleNode();

        //We create a first rectangle that contains all the possible space. All the first MAX_NODE_SIZE hedges will be contained in this rectangle.
        //This will be very useful for when we have to divide the first HedgeNode's rectangle parent into two new rectangles.
        Point maxPoint = new Point(Double.MAX_VALUE, Double.MAX_VALUE);
        Point minPoint = new Point(Double.MIN_VALUE, Double.MIN_VALUE);

        HedgeNode firstHedgeNode = new HedgeNode();

        Rectangle rectangle = new Rectangle(root, firstHedgeNode, maxPoint, minPoint);

        root.addElement(rectangle);

    }

    public void addHedge(Hedge hedge) {
        add(hedge, root);
    }

    public void removeHedge(Hedge hedge) {

    }

    private void add(Hedge hedge, Node currentNode) {

        //We go through the R-Tree until we find a leaf node, which contains Hedges
        if (!currentNode.isLeaf()) {
            Rectangle minRectangle = currentNode.getMinimumRectangle(hedge.getPoint());
            add(hedge, minRectangle.childNode);
        }

        //At this point we know that the currentNode is a HedgeNode containing Hedges
        currentNode.addElement(hedge);

        //We expand all the parent rectangles (because when we add an element to the rectangle that has
        //to expand less, all the parents of this rectangle also have to expand to contain the newly expanded rectangle)
        currentNode.expandParentRectangles(hedge);

        //If the HedgeNode is full, we have to split the parent rectangle
        if (currentNode.getSize() > MAX_NODE_SIZE) {
            splitRectangle(currentNode.parent, currentNode);

            //We split the parent's parent and so on if necessary (in case the parent node is also full)
            Node parentNode = currentNode.parent.containerNode;
            while (parentNode.getSize() > MAX_NODE_SIZE) {
                splitRectangle(currentNode.parent, currentNode);
                parentNode = parentNode.parent.containerNode;
            }
        }

    }

    private void splitRectangle(Rectangle parent, Node childNode) {

        Node parentNode;

        //If the parent is not null, the node that we have to split is a normal RectangleNode.
        if (parent != null) {
            parentNode = parent.containerNode;
            parentNode.removeElement(parent);
            //TODO: Once we remove one element, we have to compact all the parent rectangles
        }

        //If the parent is null, the node that we have to split is a root node.
        if (parent == null) {
            //If we have to split the root node, a new parent node (which will become the new root node) must be created.
            root = new RectangleNode();

            //In the variable "childNode" we still have all the rectangles that where in the root node that we just deleted

            parentNode = root;
        }

        //Now we need to get all the elements of the childNode, in order to find the two elements (Hedges or Rectangles, depending on the
        //node type) that are the furthest

        //If the childNode contains Hedges
        if (childNode.isLeaf()) {

            MyArrayList<Hedge> furthestHedges = childNode.findFurthestHedges();

        }

        //If the childNode contains Rectangles
        if (!childNode.isLeaf()) {

            MyArrayList<Rectangle> furthestRectangles = childNode.findFurthestRectangles();

        }

    }
}
