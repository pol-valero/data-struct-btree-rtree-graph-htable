package RTreesF3.Entities;

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
        currentNode.expandParentRectangles(hedge);

        //If the HedgeNode is full, we have to split the parent rectangle
        if (currentNode.getSize() > MAX_NODE_SIZE) {
            splitParent(currentNode);

            //We split the parent's parent and so on if necessary (in case the parent node is also full)
            Node parentNode = currentNode.parent.containerNode;
            while (parentNode.getSize() > MAX_NODE_SIZE) {
                splitParent(parentNode);
                parentNode = parentNode.parent.containerNode;
            }
        }

    }

    private void splitParent(Node node) {

        Node parentNode;

        //If the parent is not null, the node that we have to split is a normal RectangleNode.
        //If the parent is null, the node that we have to split is a root node.
        if (node.parent != null) {
            parentNode = node.parent.containerNode;
            //parentNode.removeElement(node.parent);
        } else {
            //If we have to split the root node, a new parent node must be created (which will become the root)
            root = new RectangleNode();
            parentNode = root;
        }

    }
}
