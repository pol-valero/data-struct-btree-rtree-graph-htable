package RTreesF3.Entities;

import Auxiliar.Algorithms.MergeSort;
import Auxiliar.MyArrayList;
import org.w3c.dom.css.Rect;

import static java.lang.Math.sqrt;

public class RTree {

    private static final int MAX_NODE_SIZE = 3;
    private static final int MIN_NODE_SIZE = MAX_NODE_SIZE / 3;

    private Node root;

    public RTree () {

    }

    private void initTree() {

        root = new RectangleNode();

        //We create a first rectangle that contains all the possible space. All the first MAX_NODE_SIZE hedges will be contained in this rectangle.
        //This will be very useful for when we have to divide the first HedgeNode's rectangle parent into two new rectangles.
        Point maxPoint = new Point(Double.MAX_VALUE, Double.MAX_VALUE);
        Point minPoint = new Point(-Double.MAX_VALUE, -Double.MAX_VALUE);

        HedgeNode firstHedgeNode = new HedgeNode();

        Rectangle rectangle = new Rectangle(root, firstHedgeNode, maxPoint, minPoint);

        root.addElement(rectangle);

    }

    public void addHedge(Hedge hedge) {

        if (root == null || root.getSize() == 0) {
            initTree();
        }

        add(hedge, root);
    }

    private void add(Hedge hedge, Node currentNode) {

        //We go through the R-Tree until we find a leaf node, which contains Hedges
        while (!currentNode.isLeaf()) {

            Rectangle minRectangle = currentNode.getMinimumRectangle(hedge.getPoint());

            currentNode = minRectangle.childNode;

        }

        //At this point we know that the currentNode is a HedgeNode containing Hedges
        currentNode.addElement(hedge);

        //We expand all the parent rectangles (because when we add an element to the rectangle that has
        //to expand less, all the parents of this rectangle also have to expand to contain the newly expanded rectangle)
        currentNode.expandParentRectangles(hedge);


        boolean full;

        //If the HedgeNode is full, we have to split the parent rectangle
        if (currentNode.getSize() > MAX_NODE_SIZE) {

            do {

                full = splitRectangle(currentNode.parent, currentNode);

                //We split the parent's parent and so on if necessary (in case the parent rectangle node is also full)
                if (full) {
                    currentNode = currentNode.parent.containerNode;
                }

            } while (full);

        }

    }

    private boolean splitRectangle(Rectangle rectangleParent, Node childNode) {

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

                Node node = rectangleInNode.childNode;
                Rectangle rectangle;

                //By doing this we "compact" the tree (we avoid having multiple rectangles
                //that only have one child that is a rectangleNode with a single rectangle)
                if (node.getSize() == 1 && !node.isLeaf()) {
                    rectangle = node.getRectangles().get(0);
                    rectangle.containerNode = minimumRectangle.childNode;
                    rectangleInNode = rectangle;
                } else {
                    rectangleInNode.containerNode = minimumRectangle.childNode;
                }

                minimumRectangle.childNode.addElement(rectangleInNode);
                minimumRectangle.childNode.expandParentRectangles(rectangleInNode);

            }

        }

        return parentNode.getSize() > MAX_NODE_SIZE;

    }


    public boolean removeHedge(Point point) {

        Node currentNode;

        MyArrayList<Hedge> hedges = new MyArrayList<>();

        areaSearch(point, point, root, hedges);

        if (hedges.size() == 0) {
            return false;
        } else {
            currentNode = hedges.get(0).containerNode;
        }

        boolean found = false;

        MyArrayList<Hedge> nodeHedges = currentNode.getHedges();

        for (Hedge hedge: nodeHedges) {

            if (hedge.getPoint().x == point.x && hedge.getPoint().y == point.y) {
                currentNode.removeElement(hedge);
                currentNode.compactParentRectangles();
                found = true;
            }

        }

        if (!found) {
            return false;
        }

        if (currentNode.getSize() < MIN_NODE_SIZE) {

            do {
                Node parentNode = currentNode.parent.containerNode;
                parentNode.removeElement(currentNode.parent);
                parentNode.compactParentRectangles();

                reinsertChildHedges(currentNode);

                currentNode = parentNode;

            } while (currentNode.getSize() < MIN_NODE_SIZE && currentNode.parent != null);

        }

        return true;
    }


    private void reinsertChildHedges(Node node) {

        if (node.isLeaf()) {
            MyArrayList<Hedge> hedges = node.getHedges();

            for (Hedge hedge: hedges) {
                addHedge(hedge);
            }

        }

        if (!node.isLeaf()) {

            MyArrayList<Hedge> hedges = new MyArrayList<>();

            Point minPoint = new Point(-Double.MAX_VALUE, -Double.MAX_VALUE);
            Point maxPoint = new Point(Double.MAX_VALUE, Double.MAX_VALUE);

            areaSearch(minPoint, maxPoint, node, hedges);

            for (Hedge hedge: hedges) {
                addHedge(hedge);
            }

        }

    }


    private void areaSearch(Point minPoint, Point maxPoint, Node currentNode, MyArrayList<Hedge> hedgesFound) {


        if (!currentNode.isLeaf()) {
            //At this point the currentNode is a rectangleNode.

            MyArrayList<Rectangle> rectanglesInNode = currentNode.getRectangles();

            //We go through all the rectangles in the node to see which ones overlap with the search area
            for (Rectangle rectangle : rectanglesInNode) {

                //When a rectangle overlaps with the search area, we "explore" this rectangle (by calling the
                //areaSearch method again). This process is done again and again until the currentNode is a leaf node.
                if (rectangleOverlaps(minPoint, maxPoint, rectangle)) {
                    areaSearch(minPoint, maxPoint, rectangle.childNode, hedgesFound);
                }

            }

        } else {

            MyArrayList<Hedge> hedgesInNode = currentNode.getHedges();

            //At this point the currentNode is a hedgeNode.
            //We go through all the hedges to see which ones we have to add
            for (Hedge hedge : hedgesInNode) {

                if (hedgeOverlaps(minPoint, maxPoint, hedge)) {
                    hedge.containerNode = currentNode;
                    hedgesFound.add(hedge);
                }

            }

        }
    }

    //This method checks if a rectangle overlaps with the search area (defined by minPoint, maxPoint)
    private boolean rectangleOverlaps (Point minPoint, Point maxPoint, Rectangle rectangle) {
        
        Point topLeftPointSearchArea = Point.findTopLeftPoint(minPoint, maxPoint);
        Point bottomRightPointSearchArea = Point.findBottomRightPoint(minPoint, maxPoint);

        Point topLeftPointRectangle = Point.findTopLeftPoint(rectangle.minPoint, rectangle.maxPoint);
        Point bottomRightPointRectangle = Point.findBottomRightPoint(rectangle.minPoint, rectangle.maxPoint);

        //If the search area contains any of the corners of the rectangle or if the rectangle contains any of the corners of the search area, then the rectangle overlaps with the search area and we return true
        if (Rectangle.containsPoint(rectangle.minPoint, rectangle.maxPoint, minPoint)
                || Rectangle.containsPoint(rectangle.minPoint, rectangle.maxPoint, maxPoint)
                || Rectangle.containsPoint(rectangle.minPoint, rectangle.maxPoint, topLeftPointSearchArea)
                || Rectangle.containsPoint(rectangle.minPoint, rectangle.maxPoint, bottomRightPointSearchArea)

                || Rectangle.containsPoint(minPoint, maxPoint, rectangle.maxPoint)
                    || Rectangle.containsPoint(minPoint, maxPoint, rectangle.minPoint)
                    || Rectangle.containsPoint(minPoint, maxPoint, topLeftPointRectangle)
                    || Rectangle.containsPoint(minPoint, maxPoint, bottomRightPointRectangle)) {

            return true;

        } else {

            return false;

        }

    }

    //This method checks if a hedge overlaps with the search area (defined by minPoint, maxPoint)
    private boolean hedgeOverlaps (Point minPoint, Point maxPoint, Hedge hedge) {

        if (Rectangle.containsPoint(minPoint, maxPoint, hedge.getPoint())) {
            return true;
        } else {
            return false;
        }

    }

    public MyArrayList<Hedge> areaSearch(Point minPoint, Point maxPoint) {

        MyArrayList<Hedge> hedgesFound = new MyArrayList<>();

        areaSearch(minPoint, maxPoint, root, hedgesFound);

        return hedgesFound;
    }

    public MyArrayList<Hedge> KNN(Point p1, int k) {

        MyArrayList<Hedge> hedgesFound = new MyArrayList<>();

        Node currentNode = root;
        while (!currentNode.isLeaf()) {

            Rectangle minRectangle = currentNode.getMinimumRectangle(p1);

            currentNode = minRectangle.childNode;

        }
        MyArrayList<Hedge> all_hedges = new MyArrayList<>();

        if (currentNode.getHedges().size() >= k){
            all_hedges = currentNode.getHedges();
        }else {
            foundRectangle_k(k, currentNode, all_hedges);
        }
        classify(hedgesFound, all_hedges, k, p1);
        return hedgesFound;
    }

    private void classify (MyArrayList<Hedge> hedgesFound, MyArrayList<Hedge> allHedges, int k, Point p1){
        MyArrayList<Knn> all_list = new MyArrayList<>();
        for (Hedge hedge:allHedges) {
            all_list.add(new Knn(hedge, p1));
        }
        Knn[] test = all_list.toArray(new Knn[0]);
        MergeSort.mergeSort(test, 0 , all_list.size() - 1);

        for (int i = 0; i < k; i++) {
            hedgesFound.add(test[i].getHedge());
        }
    }

    private void foundRectangle_k(int k, Node currentNode, MyArrayList<Hedge> all_hedges) {
        MyArrayList<Hedge> temp = new MyArrayList<>();
        while (temp.size() < k){
            temp = new MyArrayList<>();
            currentNode = currentNode.parent.containerNode;
            getHedges(currentNode, temp);
        }

        for (Hedge hedge:temp) {
            all_hedges.add(hedge);
        }
    }

    private void getHedges (Node currentNode, MyArrayList<Hedge> temp){
        if (!currentNode.isLeaf()){
            for (Rectangle rectangle:currentNode.getRectangles()) {
                getHedges(rectangle.childNode, temp);
            }
        }
        if (currentNode.isLeaf()) {
            for (Hedge hedge : currentNode.getHedges()) {
                temp.add(hedge);
            }
        }
    }



}
