package TreesF2.Entities;

public class TreeImplementation implements Tree {

    private Node root = null;
    private int COUNT = 10;


    @Override
    public void addCitizen(Citizen citizen) {
        root = add(root, citizen);
    }

    @Override
    public void removeCitizen(long citizenId) {

    }

    @Override
    public void printTree() {

    }

    private Node add(Node currentNode, Citizen citizen) {

        float valueToInsert;
        float currentNodeValue;

        if (currentNode == null) {
            return new Node(citizen);
        }

        valueToInsert = citizen.getWeight();
        currentNodeValue = currentNode.getCitizen().getWeight();

        if (valueToInsert < currentNodeValue) {
            currentNode.left = add(currentNode.left, citizen);
        } else if (valueToInsert > currentNodeValue){
            currentNode.right = add(currentNode.right, citizen);
        } else {
            return currentNode;
        }

        return currentNode;
    }

}
