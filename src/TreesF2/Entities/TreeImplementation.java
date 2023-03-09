package TreesF2.Entities;

import Auxiliar.myArrayList;

public class TreeImplementation implements Tree {

    private Node root = null;
    private myArrayList<Citizen> citizens = new myArrayList<>();

    @Override
    public void addCitizen(Citizen citizen) {
        citizens.add(citizen);
        root = add(root, citizen);
    }

    @Override
    public void removeCitizen(long citizenId) {
        Citizen citizen = findCitizenById(citizenId);
        root = remove(root, citizen);
        citizens.remove(citizen);
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
            currentNode.right = add(currentNode.right, citizen);
        } else if (valueToInsert > currentNodeValue){
            currentNode.left = add(currentNode.left, citizen);
        } else {
            return currentNode;
        }

        return currentNode;
    }

    private Node remove (Node currentNode, Citizen citizen) {
        if (currentNode == null) {
            return null;
        }

        if (citizen.getWeight() > currentNode.getCitizen().getWeight()) {
            currentNode.left = remove(currentNode.left, citizen);
            return currentNode;
        }

        if (citizen.getWeight() == currentNode.getCitizen().getWeight()) {
            //Node to delete found

            if (currentNode.right == null && currentNode.left == null) {
                return null;
            }

            if (currentNode.right == null) {
                return currentNode.left;
            }

            if (currentNode.left == null) {
                return currentNode.right;
            }
        }

        if (citizen.getWeight() < currentNode.getCitizen().getWeight()) {
            currentNode.right = remove(currentNode.right, citizen);
            return currentNode;
        }

        //
        Node tempNode = findMinNode(currentNode.left);
        currentNode.setCitizen(tempNode.getCitizen());
        currentNode.left = remove(currentNode.left, tempNode.getCitizen());

        return currentNode;

    }

    public Citizen findCitizenById (long citizenId) {

        for (Citizen citizen : citizens) {
            if (citizen.getId() == citizenId) {
                return citizen;
            }
        }

        return null;
    }

    //
    private Node findMinNode(Node node) {
        while (node.right != null) {
            node = node.right;
        }
        return node;
    }

}
