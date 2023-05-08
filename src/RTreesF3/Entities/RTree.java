package RTreesF3.Entities;

public class RTree {
    private static final int MAX_NODE_SIZE = 3;
    private static final int MIN_NODE_SIZE = MAX_NODE_SIZE / 3;

    private Node root;

    public void addHedge(Hedge hedge) {
        add(hedge, root);
    }

    public void removeHedge(Hedge hedge) {

    }

    private void add(Hedge hedge, Node currentNode) {

        if (!currentNode.isLeaf()) {
            //TODO: See if there is a better way to avoid doing a cast. Idea: put all methods in interface and return null in the classes that don't need them
            ((RectangleNode) currentNode).getMinimumRectangle(hedge.getPoint());
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
