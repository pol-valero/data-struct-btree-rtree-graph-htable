package RTreesF3.Entities;

public class RTree {
    RNode root;

    public void addHedge(Hedge hedge) {
        add(hedge);
    }

    public void removeHedge(Hedge hedge) {

    }

    private void add(Hedge hedge) {

        // If the tree is empty, add the Hedge for the first time.
        if (root == null) {
            root = new RNode();
            root.addObject(hedge);
            root.setHeight(0);
        }
        else {
            // S'ha d'anar mirant nodes fins arribar a les fulles.
            // NOU APPROACH: El root és sempre un rectangle ja instanciat, i cada rectangle té més rectangles o punts.
//            for (NodeObject nodeObject : root.getNodeObjects()){
//
//            }
        }
    }
}
