package BinaryTreesF2.Entities.Trees;

import BinaryTreesF2.Entities.Citizen;
import BinaryTreesF2.Entities.Node;

public class BSTTree extends BinaryTreesF2.Entities.BinaryTree {

    @Override
    public void addCitizen(Citizen citizen) {
        add(root, citizen, null);    // The parent node of the root will always be NULL.
    }

    @Override
    public void removeCitizen(long citizenId) {
        Citizen citizen = findCitizenById(citizenId);
        root = remove(root, citizen);
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

    private Node remove (Node currentNode, Citizen citizen) {

        // Case where the node to remove is not found.
        if (currentNode == null) {
            return null;
        }

        // We go to the right child if the value that we want to delete is higher than the current node's value
        if (citizen.getWeight() > currentNode.getCitizenWeight()) {
            currentNode.right = remove(currentNode.right, citizen);
            currentNode.calculateHeight(); // Re-calculate the height of the current node.
            return currentNode;
        }

        //We go to the left child if the value that we want to delete is lower than the current node's value
        if (citizen.getWeight() < currentNode.getCitizenWeight()) {
            currentNode.left = remove(currentNode.left, citizen);
            currentNode.calculateHeight(); // Re-calculate the height of the current node.
            return currentNode;
        }

        // Node to delete found - We have to make sure the citizen is the one with the select ID.
        if (citizen.getWeight() == currentNode.getCitizenWeight()) {

            if (currentNode.getCitizens().length == 1) {
                //If the node does not have children, we return null (replacing this node with null)
                if (currentNode.right == null && currentNode.left == null) {
                    return null;
                }

                // Case in which the node deleted has a right child.
                if (currentNode.left == null) {

                    // Check if the currentNode is a left or right child
                    if (currentNode.parent.right == currentNode) {
                        currentNode.parent.right = currentNode.right;
                    }
                    else {
                        currentNode.parent.left = currentNode.right;
                    }

                    currentNode.right.parent = currentNode.parent;

                    currentNode.right.calculateHeight(); // Re-calculate the height of the current node.
                    return currentNode.right;
                }

                // If the node only has one child, we return the child (replacing this node with the node's child in the parent)
                if (currentNode.right == null) {

                    // Check if the currentNode is a left or right child
                    if (currentNode.parent.left == currentNode) {
                        currentNode.parent.left = currentNode.left;
                    }
                    else {
                        currentNode.parent.right = currentNode.left;
                    }

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

        Node tempNode = findMinNode(currentNode.right); // Find the node with the lowest value in the right subtree (given an origin/root node) = successor "inordre"

        // Specific case for root
        if (currentNode != root) {
            // Check if the removed node was a right or a left child.
            if (currentNode.parent.right == currentNode) {
                // Assign the parent of the new node to the node to be deleted parent.
                currentNode.parent.right = tempNode;
            } else {
                currentNode.parent.left = tempNode;
            }
            tempNode.parent = currentNode.parent;

            // Keep the same children that the node to be deleted has.
            tempNode.left = currentNode.left;

            tempNode.calculateHeight(); // Re-calculate the height of the current node.
            return tempNode;
        }

        // Case when the root node is removed.
        else {
            // There is no parent.
            tempNode.parent.left = tempNode.right;  // Assign the same child of the successor node to its parent (always a left child node).
            tempNode.parent = null;                 // Root node has no parent.

            // Change the current root node (assign its children to the new root node).
            tempNode.right = root.right;
            tempNode.left = root.left;
            root = tempNode;

            root.calculateHeight(); // Re-calculate the height of the current node.
            return tempNode;
        }
    }

}
