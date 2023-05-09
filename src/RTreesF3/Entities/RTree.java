package RTreesF3.Entities;

import static RTreesF3.Algorithms.PointDistance.distanceBetweenPoints;

public class RTree {
    private static final int MAX_TREE_SIZE = 3;
    private static final int MIN_TREE_SIZE = MAX_TREE_SIZE / 3;
    private Rectangle root;

    public void addHedge(Hedge hedge) {
        add(hedge, root);
    }

    public void removeHedge(Hedge hedge) {

    }

    private void add(Hedge hedge, Rectangle currentRectangle) {

        // If the tree is empty, add the Hedge for the first time.
        if (root == null) {
            root = new Rectangle(hedge.getPoint(), hedge.getPoint());
            root.addObject(hedge);
        }
        else {
            // S'ha d'anar mirant nodes fins a arribar a les fulles.
            // NOU APPROACH: El root és sempre un rectangle ja instanciat, i cada rectangle té més rectangles o punts.
            boolean found = false;

            if (currentRectangle.isLeaf() && currentRectangle == root) {
                addPoint(currentRectangle, hedge);
            }

            // Case where the point is contained in the rectangle.
            if (!currentRectangle.isLeaf() && currentRectangle.containsPoint(hedge.getPoint())) {
                for (IObject object : currentRectangle.nodeObjects) {
                    if (object.isRectangle()) {
                        currentRectangle = (Rectangle) object;

                        if (currentRectangle.containsPoint(hedge.getPoint())) {
                            add(hedge, currentRectangle);
                        }
                    }
                }
            }

            // Check if the points size reached the maximum.
            if (currentRectangle.containsPoint(hedge.getPoint()) && currentRectangle.isLeaf()) {
                addPoint(currentRectangle, hedge);
            }
        }
    }

    private void addPoint(Rectangle rectangle, Hedge hedge) {
        if (rectangle.nodeObjects.size() == MAX_TREE_SIZE) {
            // Re-organize the rectangles.
        } else {
            rectangle.addObject(hedge);
            updateRectangle(rectangle, hedge);
        }
    }

    private void updateRectangle(Rectangle rectangle, Hedge hedge) {
        if (biggerPoint(rectangle, hedge.getPoint(), rectangle.maxPoint)) {
            rectangle.setMaxPoint(hedge.getPoint());
        }
        else {
            rectangle.setMinPoint(hedge.getPoint());
        }
    }

    private boolean biggerPoint(Rectangle rectangle, Point newPoint, Point oldPoint) {
        return (distanceBetweenPoints(rectangle.centerPoint, newPoint) > distanceBetweenPoints(rectangle.centerPoint, oldPoint));
    }
}
