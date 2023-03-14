package TreesF2.Entities;

import Auxiliar.MyArrayList;
import TreesF2.Algorithms.TreeBFS;

// Class used as a shared tree which is implemented as BinaryTree and AVLTree
public abstract class Tree {

    public Node root;   // Root of the tree (has no father Node)

    // Adds a node to the tree
    public abstract void addCitizen(Citizen citizen);

    // Removes a node from the tree
    public abstract void removeCitizen (long citizenId);

    // Prints all the nodes in the tree with the corresponding branches
    public void printRepresentation() {
        System.out.println();

        //We print the right part of the tree
        if (root.right != null) {
            print("", root.right, true);
        }

        root.printCitizen(false, true);   // Print the star in front of the citizen just if the Node is the root.

        //We print the left part of the tree
        if (root.left != null) {
            print("", root.left, false);
        }

    }

    private void print(String stringIndentation, Node node, boolean rightNode) {
        String stringIndentationAux;

        if (node.right != null) {
            if (rightNode) {
                stringIndentationAux = stringIndentation + "      ";
            } else {
                stringIndentationAux = stringIndentation + "|     ";
            }
            print(stringIndentationAux, node.right, true);
        }

        // Join the nodes to the parents, just those who have both a child and a parent on their left.
        if (node.right == null && node.parent != null && node.parent.left == node && node.left != null) {   // (node.parent != null && node.parent.left == node) would be (node.parent.left == node)
            System.out.println(stringIndentation + "|");
        }

        System.out.print(stringIndentation);

        // Add an indentation to the last nodes of the tree (leaves)
        if (!rightNode) {
            if (node.isLeaf()) {
                System.out.println("|");    //The space is already contained in the prior print of the indentation
                System.out.print(stringIndentation);
            }
        }

        // Check if it is the last right node of a branch
        if (node.right == null && rightNode) {
            System.out.println();
            System.out.print(stringIndentation);
        }

        System.out.print("|--- ");
        node.printCitizen(false, node.equals(root));   // Print the star in front of the citizen just if the Node is the root.

        // Check if the parent of the node is on the left.
        if (node.parent.right == node && node.isLeaf()) {
            System.out.println(stringIndentation + "|");
        }

        // Check if a node only has a right child.
        if (!node.isLeaf() && node.left == null) {

            // Solves a problem with a random '|' printed.
            if (rightNode) {
                System.out.println(stringIndentation + "|");
            }
            else {
                System.out.println(stringIndentation);
            }
        }

        if (node.left != null) {
            if (!rightNode) {
                stringIndentationAux = stringIndentation + "      ";
            } else {
                stringIndentationAux = stringIndentation + "|     ";
            }
            print(stringIndentationAux, node.left, false);
        }

        // Adding an extra indentation when a leaf node has a parent node to its right.
        if (node.isLeaf() && node.parent.left == node) {
            System.out.println(stringIndentation);
        }

    }

    // Given an id, it returns the Citizen object that has that id.
    public Citizen findCitizenById(long citizenId) {
        return TreeBFS.findCitizenById(root, citizenId);
    }

    //Given a starting node, searches for the right node that has the lowest value
    public Node findMinNode(Node node) {
        while (node.right != null) {
            node = node.right;
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
        if (node.right != null && node.getCitizenWeight() >= min) {
            findCitizensInRange(max, min, node.right, witches);
        }

        // Print the node if it meets the requirements: the Citizen's weight is between the limits / bounds (it's a Witch).
        if (min <= node.getCitizenWeight() && max >= node.getCitizenWeight()) {
            witches.add(node.getCitizen());
        }

        // Check if exploring the nodes with a higher value than the current node is interesting: the current node value is below Maximum Value.
        if (node.left != null && node.getCitizenWeight() <= max ) {
            findCitizensInRange(max, min, node.left, witches);
        }
    }

}
