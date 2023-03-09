package TreesF2.Entities;

public class Node {

    private Citizen citizen;
    public Node left;
    public Node right;

    public Node (Citizen citizen) {
        this.citizen = citizen;
        this.left = null;
        this.right = null;
    }

    public Citizen getCitizen() {
        return citizen;
    }

}
