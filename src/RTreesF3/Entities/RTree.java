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
            return;
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
                splitRectangle(parentNode.parent, parentNode);

                //If the node's parent is null, it means that we have reached the root node
                //In this case we stop going through the tree and we exit the while loop
                if (parentNode.parent != null) {
                    parentNode = parentNode.parent.containerNode;
                } else {
                    return;
                }
            }
        }

    }

    private void splitRectangle(Rectangle rectangleParent, Node childNode) {

        Node parentNode = null;

        //If the parent is not null, the node that we have to split is a normal RectangleNode.
        if (rectangleParent != null) {
            parentNode = rectangleParent.containerNode;

            //We remove the rectangle parent from the node, as it will be divided into two new rectangles.
            parentNode.removeElement(rectangleParent);
        }

        //If the parent is null, the node that we have to split is a root node.
        if (rectangleParent == null) {
            //If we have to split the root node, a new parent node (which will become the new root node) must be created.
            root = new RectangleNode();

            //In the variable "childNode" we still have all the rectangles that where in the root node that we just deleted

            parentNode = root;
        }

        //Now we need to get all the elements of the childNode, in order to find the two elements (Hedges or Rectangles, depending on the
        //node type) that are the furthest

        //If the childNode contains Hedges
        if (childNode.isLeaf()) {

            //We will choose the furthest hedges to initialize the two rectangles
            MyArrayList<Hedge> furthestHedges = childNode.findFurthestHedges();

            Point pointHedge1 = furthestHedges.get(0).getPoint();
            Point pointHedge2 = furthestHedges.get(1).getPoint();

            Node hedgeNode1 = new HedgeNode();
            Node hedgeNode2 = new HedgeNode();
            
            //We initialize two new rectangles that will contain the hedges of the rectangle that we have to split. At first, this rectangles will have no area.
            Rectangle rectangle1 = new Rectangle(parentNode, hedgeNode1, pointHedge1, pointHedge1);
            Rectangle rectangle2 = new Rectangle(parentNode, hedgeNode2, pointHedge2, pointHedge2);

            //We add the two new rectangles in the parent node. As the splitRectangle method is called for every parent, in this method we will not check
            // if the creation of the two new rectangles results in the node having more rectangles than it can hold (as this is checked in the add method)
            parentNode.addElement(rectangle1);
            parentNode.addElement(rectangle2);

            MyArrayList<Hedge> hedgesInNode = childNode.getHedges();
            MyArrayList<Rectangle> rectangles = new MyArrayList<>();

            rectangles.add(rectangle1);
            rectangles.add(rectangle2);

            Rectangle minimumRectangle;     //Rectangle that will have to expand less

            //We put each element (that previously was in the rectangle that we just divided) in the rectangle that has to expand less (one of the two new rectangles created)
            for (Hedge hedgeInNode: hedgesInNode) {

                minimumRectangle = Node.getMinimumRectangle(hedgeInNode.getPoint(), rectangles);
                minimumRectangle.childNode.addElement(hedgeInNode);
                minimumRectangle.childNode.expandParentRectangles(hedgeInNode);

            }

        }

        //If the childNode contains Rectangles
        if (!childNode.isLeaf()) {

            //We will choose the furthest rectangles to initialize the two rectangles
            MyArrayList<Rectangle> furthestRectangles = childNode.findFurthestRectangles();

            Point r1MinPoint = furthestRectangles.get(0).minPoint;
            Point r1MaxPoint = furthestRectangles.get(0).maxPoint;

            Point r2MinPoint = furthestRectangles.get(1).minPoint;
            Point r2MaxPoint = furthestRectangles.get(1).maxPoint;

            Node rectangleNode1 = new RectangleNode();
            Node rectangleNode2 = new RectangleNode();

            //We initialize two new rectangles that will contain the childRectangles of the rectangle that we have to split.
            // At first, this rectangles will have an area equal to each of the furthest rectangles.
            Rectangle rectangle1 = new Rectangle(parentNode, rectangleNode1, r1MaxPoint ,r1MinPoint);
            Rectangle rectangle2 = new Rectangle(parentNode, rectangleNode2, r2MaxPoint, r2MinPoint);

            //We add the two new rectangles in the parent node. As the splitRectangle method is called for every parent, in this method we will not check
            // if the creation of the two new rectangles results in the node having more rectangles than it can hold (as this is checked in the add method)
            parentNode.addElement(rectangle1);
            parentNode.addElement(rectangle2);

            MyArrayList<Rectangle> rectanglesInNode = childNode.getRectangles();
            MyArrayList<Rectangle> rectangles = new MyArrayList<>();

            rectangles.add(rectangle1);
            rectangles.add(rectangle2);

            Rectangle minimumRectangle;     //Rectangle that will have to expand less

            //We put each element (that previously was in the rectangle that we just divided) in the rectangle that has to expand less (one of the two new rectangles created)
            for (Rectangle rectangleInNode: rectanglesInNode) {

                minimumRectangle = Node.getMinimumRectangle(rectangleInNode.centerPoint, rectangles);
                minimumRectangle.childNode.addElement(rectangleInNode);
                minimumRectangle.childNode.expandParentRectangles(rectangleInNode);

            }

        }

    }

    private void areaSearch(Point p1, Point p2, Node currentNode, MyArrayList<Hedge> hedgesFound) {


        if (!currentNode.isLeaf()) {
            //At this point the currentNode is a rectangleNode.

            MyArrayList<Rectangle> rectanglesInNode = currentNode.getRectangles();

            //We go through all the rectangles in the node to see which ones overlap with the search area
            for (Rectangle rectangle : rectanglesInNode) {

                //When a rectangle overlaps with the search area, we "explore" this rectangle (by calling the
                //areaSearch method again). This process is done again and again until the currentNode is a leaf node.
                if (rectangleOverlaps(p1, p2, rectangle)) {
                    areaSearch(p1, p2, rectangle.childNode, hedgesFound);
                }

            }

        } else {

            MyArrayList<Hedge> hedgesInNode = currentNode.getHedges();

            //At this point the currentNode is a hedgeNode.
            //We go through all the hedges to see which ones we have to add
            for (Hedge hedge : hedgesInNode) {

                if (hedgeOverlaps(p1, p2, hedge)) {
                    hedgesFound.add(hedge);
                }

            }

        }
    }

    //This method checks if a rectangle overlaps with the search area (defined by p1, p2)
    private boolean rectangleOverlaps (Point p1, Point p2, Rectangle rectangle) {

         if (Rectangle.containsPoint(p1, p2, rectangle.minPoint) || Rectangle.containsPoint(p1, p2, rectangle.maxPoint)) {
            return true;
        } else {
            return false;
        }

        //TODO: Debug
    }

    //This method checks if a hedge overlaps with the search area (defined by p1, p2)
    private boolean hedgeOverlaps (Point p1, Point p2, Hedge hedge) {

        if (Rectangle.containsPoint(p1, p2, hedge.getPoint())) {
            return true;
        } else {
            return false;
        }
        //TODO: Check if p1 is minpoint and p2 is maxpoint (or pass always a rectangle instead to facilitate understanding)
    }

    public MyArrayList<Hedge> areaSearch(Point p1, Point p2) {

        MyArrayList<Hedge> hedgesFound = new MyArrayList<>();

        areaSearch(p1, p2, root, hedgesFound);

        return hedgesFound;
    }



}
