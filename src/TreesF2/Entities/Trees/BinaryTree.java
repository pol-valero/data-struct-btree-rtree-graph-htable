package TreesF2.Entities.Trees;

import TreesF2.Entities.Citizen;
import TreesF2.Entities.Node;
import TreesF2.Entities.Tree;

public class BinaryTree extends Tree {

    @Override
    public void addCitizen(Citizen citizen) {
        root = add(root, citizen, null);    // The parent node of the root will always be NULL.
    }

    @Override
    public void removeCitizen(long citizenId) {
        Citizen citizen = findCitizenById(citizenId);
        root = remove(root, citizen);
    }

    private Node add(Node currentNode, Citizen citizen, Node parentNode) {
        float valueToInsert;
        float currentNodeValue;

        // When the current node is null, a new node can be inserted into the position
        // (we've reached a leaf node, or it is the first node of the tree: the root)
        if (currentNode == null) {
            Node node = new Node(citizen, parentNode);
            node.height = 0;    // Set a height of zero (it is a leaf node).
            return node;
        }

        valueToInsert = citizen.getWeight();
        currentNodeValue = currentNode.getCitizenWeight();

        if (valueToInsert < currentNodeValue) {     //We go to the left child if the value that we want to insert is lower than the current node's value
            currentNode.left = add(currentNode.left, citizen, currentNode);
        } else if (valueToInsert > currentNodeValue) {      //We go to the right child if the value that we want to insert is higher than the current node's value
            currentNode.right = add(currentNode.right, citizen, currentNode);
        } else {
            currentNode.calculateHeight();
            return currentNode; //We return the currentNode if the value already exists (therefore not adding the new node as it has a duplicated value)
        }

        // Case where the node is added
        currentNode.calculateHeight();
        return currentNode;
    }

    private Node remove (Node currentNode, Citizen citizen) {

        if (currentNode == null) {
            return null;
        }

        //We go to the left child if the value that we want to delete is higher than the current node's value
        if (citizen.getWeight() > currentNode.getCitizenWeight()) {
            currentNode.right = remove(currentNode.right, citizen);
            currentNode.calculateHeight(); // Re-calculate the height of the current node.
            return currentNode;
        }

        //We go to the right child if the value that we want to delete is lower than the current node's value
        if (citizen.getWeight() < currentNode.getCitizenWeight()) {
            currentNode.left = remove(currentNode.left, citizen);
            currentNode.calculateHeight(); // Re-calculate the height of the current node.
            return currentNode;
        }

        // Node to delete found
        if (citizen.getWeight() == currentNode.getCitizenWeight()) {

            //If the node does not have children, we return null (replacing this node with null in the parent)
            if (currentNode.right == null && currentNode.left == null) {
                return null;
            }

            //If the node only has one child, we return the child (replacing this node with the node's child in the parent)
            if (currentNode.left == null) {
                currentNode.right.parent = currentNode.parent;
                currentNode.right.calculateHeight(); // Re-calculate the height of the current node.
                return currentNode.right;
            }

            if (currentNode.right == null) {
                currentNode.left.parent = currentNode.parent;
                currentNode.left.calculateHeight(); // Re-calculate the height of the current node.
                return currentNode.left;
            }
            /////////////////////////////////
        }

        //If the node has two children, we need to reorganize the tree.

        //We will need to replace the node with another node that has a suitable value.
        //Knowing the value of the node that we want to delete, we will choose the node with the
        //next biggest value as a substitute. To choose this node, we will first go to the left node
        //(which has a greater value) and then find the lowest value in the subtree. This value will
        //be the next biggest value that we were searching for

        Node tempNode = findMinNode(currentNode.right); //Finds the node with the lowest value in the subtree (given an origin/root node)
        currentNode.setCitizen(tempNode.getCitizen());  //We change the node's citizen information; effectively eliminating the older node.
        currentNode.right = remove(currentNode.right, tempNode.getCitizen()); //We delete the node that had been chosen as a substitute. If we did not delete it, it would be duplicated in the tree.

        currentNode.calculateHeight(); // Re-calculate the height of the current node.
        return currentNode;
    }

}
