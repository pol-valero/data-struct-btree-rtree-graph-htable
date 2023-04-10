package TreesF2.Entities;

import Auxiliar.MyArrayList;

public class Node {

    private MyArrayList<Citizen> citizen;   // Citizens array of a node to put all the citizens with same weight together
    public Node left;
    public Node right;
    public Node parent;
    public int height;

    public Node (Citizen citizen) {
        this.citizen = new MyArrayList<>(new Citizen[]{citizen});
        this.left = null;
        this.right = null;
        this.parent = null;
        this.height = 0;
    }

    public Node (Citizen citizen, Node parentNode) {
        this.citizen = new MyArrayList<>(new Citizen[]{citizen});
        this.left = null;
        this.right = null;
        this.parent = parentNode;
        this.height = 0;    // Initialize height value for null nodes.
    }

    public boolean sameID(int numCitizen, long otherID) {
        return this.citizen.get(numCitizen).sameID(otherID);
    }

    public Citizen[] getCitizens() {
        return citizen.toArray(new Citizen[0]);
    }

    public void setCitizens(Citizen[] citizens) {
        this.citizen = new MyArrayList<>(citizens);
    }

    public float getCitizenWeight() {
        return citizen.get(0).getWeight();
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

    public void printCitizen(int numCitizen, boolean tabInFront, boolean starInFront) {
        citizen.get(numCitizen).printInfo(tabInFront, starInFront);
    }

    public void addCitizen(Citizen citizen) {
        this.citizen.add(citizen);
    }

    public void removeCitizen(Citizen citizen) {
        this.citizen.remove(citizen);
    }

}
