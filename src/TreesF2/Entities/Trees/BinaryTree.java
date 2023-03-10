package TreesF2.Entities.Trees;

import Auxiliar.MyArrayList;
import TreesF2.Entities.Citizen;
import TreesF2.Entities.Node;
import TreesF2.Entities.Tree;

public class BinaryTree implements Tree {

    private Node root = null;   //Root node of the tree. From this node we can obtain all the other nodes.
    private final MyArrayList<Citizen> citizens = new MyArrayList<>(); //List of all the citizens that are currently in the tree

    @Override
    public void addCitizen(Citizen citizen) {
        citizens.add(citizen);
        root = add(root, citizen, null);    // The parent node of the root will always be NULL.
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

        //We print the right part of the tree
        if (root.right != null) {
            print("", root.right, true);
        }

        nodePrint(root);    //We print the root node of the tree

        //We print the left part of the tree
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

    //Prints all the node's information
    private void nodePrint (Node node) {
        Citizen citizen = node.getCitizen();

        if (node.equals(root)) {
            System.out.print("* ");
        }

        System.out.println(citizen.getName() + " (" + citizen.getId() + ", Regne de " + citizen.getKingdom() + "): " + citizen.getWeight() + "kg");
    }


    private Node add(Node currentNode, Citizen citizen, Node parentNode) {
        float valueToInsert;
        float currentNodeValue;

        //When the current node is null, a new node can be inserted into the position
        // (we've reached a leaf node, or it is the first node of the tree: the root)
        if (currentNode == null) {
            return new Node(citizen, parentNode);
        }

        valueToInsert = citizen.getWeight();
        currentNodeValue = currentNode.getCitizenWeight();

        if (valueToInsert < currentNodeValue) {     //We go to the right child if the value that we want to insert is lower than the current node's value
            currentNode.right = add(currentNode.right, citizen, currentNode);
        } else if (valueToInsert > currentNodeValue) {      //We go to the left child if the value that we want to insert is higher than the current node's value
            currentNode.left = add(currentNode.left, citizen, currentNode);
        } else {
            return currentNode; //We return the currentNode if the value already exists (therefore not adding the new node as it has a duplicated value)
        }

        return currentNode;
    }

    private Node remove (Node currentNode, Citizen citizen) {

        if (currentNode == null) {
            return null;
        }

        //We go to the left child if the value that we want to delete is higher than the current node's value
        if (citizen.getWeight() > currentNode.getCitizenWeight()) {
            currentNode.left = remove(currentNode.left, citizen);
            return currentNode;
        }

        //We go to the right child if the value that we want to delete is lower than the current node's value
        if (citizen.getWeight() < currentNode.getCitizenWeight()) {
            currentNode.right = remove(currentNode.right, citizen);
            return currentNode;
        }

        // Node to delete found
        if (citizen.getWeight() == currentNode.getCitizenWeight()) {

            //If the node does not have children, we return null (replacing this node with null in the parent)
            if (currentNode.right == null && currentNode.left == null) {
                return null;
            }

            //If the node only has one child, we return the child (replacing this node with the node's child in the parent)
            if (currentNode.right == null) {
                currentNode.left.parent = currentNode.parent;
                return currentNode.left;
            }

            if (currentNode.left == null) {
                currentNode.right.parent = currentNode.parent;
                return currentNode.right;
            }
            /////////////////////////////////
        }

        //If the node has two children, we need to reorganize the tree.

        //We will need to replace the node with another node that has a suitable value.
        //Knowing the value of the node that we want to delete, we will choose the node with the
        //next biggest value as a substitute. To choose this node, we will first go to the left node
        //(which has a greater value) and then find the lowest value in the subtree. This value will
        //be the next biggest value that we were searching for

        Node tempNode = findMinNode(currentNode.left); //Finds the node with the lowest value in the subtree (given an origin/root node)
        currentNode.setCitizen(tempNode.getCitizen());  //We change the node's citizen information; effectively eliminating the older node.
        currentNode.left = remove(currentNode.left, tempNode.getCitizen()); //We delete the node that had been chosen as a substitute. If we did not delete it, it would be duplicated in the tree.

        return currentNode;
    }

    @Override
    public Citizen findCitizenById (long citizenId) {

        for (Citizen citizen : citizens) {
            if (citizen.getId() == citizenId) {
                return citizen;
            }
        }

        return null;
    }

    //Given a starting node, searches for the right node that has the lowest value
    private Node findMinNode(Node node) {
        while (node.right != null) {
            node = node.right;
        }
        return node;
    }

}
