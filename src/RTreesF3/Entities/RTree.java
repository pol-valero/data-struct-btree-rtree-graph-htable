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

        if (currentNode.getSize() > MAX_NODE_SIZE) {
            //TODO: Split the parent. Split the parent's parent and so on if necessary (in case the parent is also full)
        }
        /*
        // If the tree is empty, add the Hedge for the first time.
        if (root == null) {
            root = new Rectangle(hedge.getPoint(), hedge.getPoint());
            root.addObject(hedge);
            root.setHeight(0);
        }
        else {
            // S'ha d'anar mirant nodes fins a arribar a les fulles.
            // NOU APPROACH: El root és sempre un rectangle ja instanciat, i cada rectangle té més rectangles o punts.
            boolean found = false;

            // Get to the leaf rectangle (only points).
            while (!currentRectangle.isLeaf()) {
                // Loop through all the objects in the rectangle.
            }

            if (!currentRectangle.isLeaf()) {
                for (IObject object : currentRectangle.nodeObjects) {
                    if (object.isRectangle()) {
                        currentRectangle = (Rectangle) object;
                    }
                }
            }

            // Check if the points size reached the maximum.
            if (currentRectangle.nodeObjects.size() == MAX_TREE_SIZE) {
                // Re-organize the rectangles.
            } else {
                currentRectangle.addObject(hedge);
            }


        }*/
    }
}
