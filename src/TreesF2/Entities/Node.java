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

    public Citizen getCitizen() {
        return citizen;
    }

    public void setCitizen (Citizen citizen) {
        this.citizen = citizen;
    }

    public float getCitizenWeight() {
        return citizen.getWeight();
    }

}
