package TreesF2.Entities;

public class Node {

    private Citizen citizen;
    public Node left;
    public Node right;
    public Node parent;
    public int height;

    public Node (Citizen citizen) {
        this.citizen = citizen;
        this.left = null;
        this.right = null;
        this.parent = null;
    }

    public Node (Citizen citizen, Node parentNode) {
        this.citizen = citizen;
        this.left = null;
        this.right = null;
        this.parent = parentNode;
        this.height = 0;    // Initialize height value for null nodes.
    }

    public boolean sameID(long otherID) {
        return this.citizen.sameID(otherID);
    }

    public Citizen getCitizen() {
        return citizen;
    }

    public void setCitizen (Citizen citizen) {
        this.citizen = citizen;
    }

    public float getCitizenWeight() {
        return citizen.getWeight();
    }

    public boolean isLeaf() {
        return (right == null && left == null);
    }

    public int getHeight() {
        return height;
    }

    // Get the height of the current node: maximum between right and left node + 1 (the node itself).
    public void calculateHeight() {

        // Case were there is no children (leaf node)
        if (right == null && left == null) {
            height = 1;
            return;
        }

        // Case where there is no right child
        if (right == null) {
            height = left.height + 1;
            return;
        }

        // Case where is no left child
        if (left == null) {
            height = right.height + 1;
            return;
        }

        // Case where the node has both children
        height = Math.max(right.height, left.height) + 1;
    }

    public void printCitizen(boolean tabInFront, boolean starInFront) {
        citizen.printInfo(tabInFront, starInFront);
    }

}
