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

    // Get the height of the current node: maximum between right and left node + 1 (the node itself).
    public int calculateHeight() {

        // Case were there is no children (leaf node)
        if (right == null && left == null) {
            return 0;
        }

        // Case where there is no right child
        if (right == null) {
            return left.height + 1;
        }

        // Case where is no left child
        if (left == null) {
            return right.height + 1;
        }

        // Case where the node has both children
        return Math.max(right.height, left.height) + 1;
    }
}
