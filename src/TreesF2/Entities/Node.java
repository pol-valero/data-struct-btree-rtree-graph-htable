package TreesF2.Entities;

public class Node {

    private Citizen citizen;
    public Node left;
    public Node right;
    public Node parent;

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
}
