package TreesF2.Entities;

import Auxiliar.MyArrayList;
import TreesF2.Algorithms.TreeBFS;

// Class used as a shared tree which is implemented as BinaryTree and AVLTree
public abstract class Tree {

    public Node root = null;   // Root of the tree (has no father Node)

    // Adds a node to the tree
    public abstract void addCitizen(Citizen citizen);

    // Removes a node from the tree
    public abstract void removeCitizen (long citizenId);

    // Prints all the nodes in the tree with the corresponding branches
    public void printRepresentation() {

        //We print the right part of the tree
        if (root.left != null) {
            print("", root.left, true);
        }
        // Tab space if there is no left part
        else {
            System.out.println();
        }

        for (int i = 0; i < root.getCitizens().length; i++) {
            Citizen rootCitizen = root.getCitizens()[i];

            if (i == 0) {
                rootCitizen.printInfo(false, true);   // Print the star in front of the citizen just if the Node is the root.
            }
            else {
                if (root.right != null) {
                    System.out.print("| ");
                    rootCitizen.printInfo(false, false);   // Print the star in front of the citizen just if the Node is the root.
                }
                else {
                    System.out.print("  ");
                    rootCitizen.printInfo(false, false);   // Print the star in front of the citizen just if the Node is the root.
                }
            }
        }

        //We print the left part of the tree
        if (root.right != null) {
            print("", root.right, false);
        }

    }

    private void print(String stringIndentation, Node node, boolean leftNode) {
        String stringIndentationAux;

        if (node.left != null) {
            if (leftNode) {
                stringIndentationAux = stringIndentation + "      ";
            } else {
                stringIndentationAux = stringIndentation + "|     ";
            }
            print(stringIndentationAux, node.left, true);
        }

        // Join the nodes to the parents, just those who have both a child and a parent on their left.
        if (node.left == null && node.parent != null && node.parent.right == node && node.right != null) {   // (node.parent != null && node.parent.left == node) would be (node.parent.left == node)
            System.out.println(stringIndentation + "|");
        }

        System.out.print(stringIndentation);

        // Add an indentation to the last nodes of the tree (leaves)
        if (!leftNode) {
            if (node.isLeaf()) {
                System.out.println("|");    //The space is already contained in the prior print of the indentation
                System.out.print(stringIndentation);
            }
        }

        // Check if it is the last right node of a branch
        if (node.left == null && leftNode) {
            System.out.println();
            System.out.print(stringIndentation);
        }

        System.out.print("|--- ");
//        for (Citizen nodeCitizen : node.getCitizens()) {
//            nodeCitizen.printInfo(false, node.equals(root));    // Print the star in front of the citizen just if the Node is the root.
//        }

        for (int i = 0; i < node.getCitizens().length; i++) {
            Citizen nodeCitizen = node.getCitizens()[i];

            // Print "|" only when there is a left child node.
            if (i > 0 && node.left != null && node.right != null && leftNode) {
                System.out.print(stringIndentation + "|    ");
            }
            else if (i > 0 && node.parent.left == node && leftNode) {
                System.out.print(stringIndentation + "|    ");
            } else if (i > 0 && node.parent.right == node) {
                System.out.print(stringIndentation + "     ");
            }

            nodeCitizen.printInfo(false, node.equals(root));    // Print the star in front of the citizen just if the Node is the root.
        }

        // Check if the parent of the node is on the left.
        if (node.parent.left == node && node.isLeaf()) {
            System.out.println(stringIndentation + "|");
        }

        // Check if a node only has a right child.
        if (!node.isLeaf() && node.right == null) {

            // Solves a problem with a random '|' printed.
            if (leftNode) {
                System.out.println(stringIndentation + "|");
            }
            else {
                // Avoid printing a line separator at the end.
                if (!stringIndentation.equals("")) {
                    System.out.println(stringIndentation);
                }
            }
        }

        if (node.right != null) {
            if (!leftNode) {
                stringIndentationAux = stringIndentation + "      ";
            } else {
                stringIndentationAux = stringIndentation + "|     ";
            }
            print(stringIndentationAux, node.right, false);
        }

        // Adding an extra indentation when a leaf node has a parent node to its right.
        if (node.isLeaf() && node.parent.right == node) {
            System.out.println(stringIndentation);
        }

    }

    // Given an id, it returns the Citizen object that has that id.
    public Citizen findCitizenById(long citizenId) {
        return TreeBFS.findCitizenById(root, citizenId);
    }

    //Given a starting node, searches for the right node that has the lowest value
    public Node findMinNode(Node node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    // Given a minimum and maximum weight, find Citizens whose weight is between that range.
    public void findCitizensInRange(float max, float min) {

        MyArrayList<Citizen> witches = new MyArrayList<>();
        findCitizensInRange(max, min, root, witches);

        // Print all the witches (in case there is any)
        if (witches.size() > 0) {

            // Take into account if there is only one witch discovered (or +1 one)
            if (witches.size() == 1) {
                System.out.println("S'ha descobert " + witches.size() + " bruixa!");
            }
            else {
                System.out.println("S'han descobert " + witches.size() + " bruixes!");
            }

            // Print all the witches information
            for (Citizen witch : witches) {
                witch.printInfo(true, true);
            }
        }
        else {
            System.out.println("No s'ha descobert cap bruixa.");
        }
    }

    private void findCitizensInRange(float max, float min, Node node, MyArrayList<Citizen> witches) {

        // Check if exploring the nodes with a lower value than the current node is interesting: the current node value is over Minimum Value.
        if (node.left != null && node.getCitizenWeight() >= min) {
            findCitizensInRange(max, min, node.left, witches);
        }

        // Print the node if it meets the requirements: the Citizen's weight is between the limits / bounds (it's a Witch).
        if (min <= node.getCitizenWeight() && max >= node.getCitizenWeight()) {
            for (Citizen citizen : node.getCitizens()) {
                witches.add(citizen);
            }
        }

        // Check if exploring the nodes with a higher value than the current node is interesting: the current node value is below Maximum Value.
        if (node.right != null && node.getCitizenWeight() <= max ) {
            findCitizensInRange(max, min, node.right, witches);
        }
    }


    private void findWitchDuck(Object object, Node currentNode, MyArrayList<Citizen> result) {
        // Tots els habitants que pesin igual que l'objecte -> fer una cerca

        if (currentNode != null) {
            if (currentNode.getCitizenWeight() == object.getWeight()) {
                for (int i = 0; i < currentNode.getCitizens().length; i++) {
                    result.add(currentNode.getCitizens()[i]);
                }
            } else if (currentNode.getCitizenWeight() < object.getWeight()) {
                findWitchDuck(object, currentNode.right, result);
            } else if (currentNode.getCitizenWeight() > object.getWeight()) {
                findWitchDuck(object, currentNode.left, result);
            }
        }
    }

    private Citizen findWitchWood(Object object, Node currentNode) {
        // Primer habitant que pesi menys que l'objecte -> esquerra
        if (currentNode != null) {
            if (currentNode.getCitizenWeight() < object.getWeight()) {
                return currentNode.getCitizens()[0];
            } else {
                return findWitchWood(object, currentNode.left);
            }
        } else{
            return null;
        }
    }

    private Citizen findWitchStone(Object object, Node currentNode) {
        // Primer habitant que pesi més que l'objecte -> dreta
        if (currentNode != null) {
            if (currentNode.getCitizenWeight() > object.getWeight()) {
                return currentNode.getCitizens()[0];
            } else {
                return findWitchStone(object, currentNode.right);
            }
        } else {
            return null;
        }
    }

    public Citizen findWitch(Object object, MyArrayList<Citizen> witches) {
        if (object.sameObject(ObjectType.WOOD)) {
            return findWitchWood(object, root);
        } else if (object.sameObject(ObjectType.STONE)) {
            return findWitchStone(object, root);
        } else if (object.sameObject(ObjectType.DUCK)) {
            findWitchDuck(object, root, witches);
        }
        return null;
    }
}
