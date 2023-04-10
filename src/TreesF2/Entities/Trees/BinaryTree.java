package TreesF2.Entities.Trees;

import TreesF2.Entities.Citizen;
import TreesF2.Entities.Node;
import TreesF2.Entities.Tree;

public class BinaryTree extends Tree {

    @Override
    public void addCitizen(Citizen citizen) {
        add(root, citizen, null);    // The parent node of the root will always be NULL.
    }

    @Override
    public void removeCitizen(long citizenId) {
        Citizen citizen = findCitizenById(citizenId);
        root = remove(root, citizen, false);
    }

    private void add(Node currentNode, Citizen citizen, Node parentNode) {
        float valueToInsert;
        float currentNodeValue;

        // When the current node is null, a new node can be inserted into the position
        // (we've reached a leaf node, or it is the first node of the tree: the root)
        if (currentNode == null) {
            root = new Node(citizen, parentNode);
            return;
        }

        valueToInsert = citizen.getWeight();
        currentNodeValue = currentNode.getCitizenWeight();

        if (valueToInsert < currentNodeValue) {     // We go to the left child if the value that we want to insert is lower than the current node's value
            if (currentNode.left != null) {
                add(currentNode.left, citizen, currentNode);
            }
            else {
                currentNode.left = new Node(citizen, currentNode);
            }
        } else if (valueToInsert > currentNodeValue) {      // We go to the right child if the value that we want to insert is higher than or equal to the current node's value
            if (currentNode.right != null) {
                add(currentNode.right, citizen, currentNode);
            }
            else {
                currentNode.right = new Node(citizen, currentNode);
            }
        } else {
            // If value of the node has the same weight means there already is a node with that weight, so a citizen is added to the node's citize list.
            currentNode.addCitizen(citizen);
        }

        // Case where the node is added
        currentNode.calculateHeight();
    }

    private Node remove (Node currentNode, Citizen citizen, boolean removeAll) {

        // Case where the node to remove is not found.
        if (currentNode == null) {
            return null;
        }

        // We go to the right child if the value that we want to delete is higher than the current node's value
        if (citizen.getWeight() > currentNode.getCitizenWeight()) {
            currentNode.right = remove(currentNode.right, citizen, removeAll);
            currentNode.calculateHeight(); // Re-calculate the height of the current node.
            return currentNode;
        }

        //We go to the left child if the value that we want to delete is lower than the current node's value
        if (citizen.getWeight() < currentNode.getCitizenWeight()) {
            currentNode.left = remove(currentNode.left, citizen, removeAll);
            currentNode.calculateHeight(); // Re-calculate the height of the current node.
            return currentNode;
        }

        // Node to delete found - We have to make sure the citizen is the one with the select ID.
        if (citizen.getWeight() == currentNode.getCitizenWeight()) {

            if (removeAll || currentNode.getCitizens().length == 1) {
                //If the node does not have children, we return null (replacing this node with null)
                if (currentNode.right == null && currentNode.left == null) {
                    return null;
                }

                // Loop through all the right nodes until we find the citizen with the same ID.
                if (currentNode.left == null) {

                    // If the ID is not the same, go to the next right node.
                    while (!currentNode.sameID(0, citizen.getID())) {
                        currentNode = currentNode.right;
                    }

                    currentNode.right.parent = currentNode.parent;
                    currentNode.right.calculateHeight(); // Re-calculate the height of the current node.

                    return currentNode.right;
                }

                // If the node only has one child, we return the child (replacing this node with the node's child in the parent)
                if (currentNode.right == null) {

                    currentNode.left.parent = currentNode.parent;
                    currentNode.left.calculateHeight(); // Re-calculate the height of the current node.
                    return currentNode.left;
                }
            }
            else {
                // Just remove the citizen and update the list, but keep the node in the same place (return the same node).
                currentNode.removeCitizen(citizen);
                return currentNode;
            }
            /////////////////////////////////
        }

        //If the node has two children, we need to reorganize the tree.

        //We will need to replace the node with another node that has a suitable value.
        //Knowing the value of the node that we want to delete, we will choose the node with the
        //next biggest value as a substitute. To choose this node, we will first go to the right node
        //(which has a greater value) and then find the lowest value in the subtree. This value will
        //be the next biggest value that we were searching for.

        Node tempNode = findMinNode(currentNode.right); //Finds the node with the lowest value in the subtree (given an origin/root node)
        currentNode.setCitizens(tempNode.getCitizens());  //We change the node's citizen information; effectively eliminating the older node.
        currentNode.right = remove(currentNode.right, tempNode.getCitizens()[0], true); //We delete the node that had been chosen as a substitute. If we did not delete it, it would be duplicated in the tree.

        currentNode.calculateHeight(); // Re-calculate the height of the current node.
        return currentNode;
    }

}
