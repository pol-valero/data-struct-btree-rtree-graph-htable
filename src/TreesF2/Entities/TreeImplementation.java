package TreesF2.Entities;

public class TreeImplementation implements Tree {

    private Node root = null;


    @Override
    public void addCitizen(Citizen citizen) {
        root = add(root, citizen);
    }

    @Override
    public void removeCitizen(long citizenId) {

    }

    @Override
    public void printRepresentation() {

        System.out.println();

        if (root.right != null) {
            print("", root.right, true);
        }

        nodePrint(root);

        if (root.left != null) {
            print("", root.left, false);
        }

    }

    private void print (String stringIndentation, Node node, boolean rightNode) {

        String stringIndentationAux;

        if (node.right != null) {
            if (rightNode) {
                stringIndentationAux = stringIndentation + "      ";
            } else {
                stringIndentationAux = stringIndentation + "|     ";
            }
            print(stringIndentationAux, node.right, true);
        }

        System.out.print(stringIndentation);

        //System.out.print("|");

        //Temporary form of representation until we solve the problem about the lack of space between branches
        if (rightNode) {
            System.out.print(" /");
        } else {
            System.out.print(" \\");
        }

        System.out.print("--- ");
        nodePrint(node);

        if (node.left != null) {
            if (!rightNode) {
                stringIndentationAux = stringIndentation + "      ";
            } else {
                stringIndentationAux = stringIndentation + "|     ";
            }
            print(stringIndentationAux, node.left, false);
        }

    }

    private void nodePrint (Node node) {
        Citizen citizen = node.getCitizen();

        if (node.equals(root)) {
            System.out.print("* ");
        }

        System.out.println(citizen.getName() + " (" + citizen.getId() + ", Regne de " + citizen.getKingdom() + "): " + citizen.getWeight() + "kg");
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
