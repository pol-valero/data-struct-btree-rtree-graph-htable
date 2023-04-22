package BinaryTreesF2.Entities.Trees;

import BinaryTreesF2.Entities.Citizen;
import BinaryTreesF2.Entities.Node;
import BinaryTreesF2.Entities.BinaryTree;

public class AVLTree extends BinaryTree {

	@Override
	public void addCitizen(Citizen citizen) {
		root = add(root, citizen, null);    // The parent node of the root will always be NULL.
	}

	@Override
	public void removeCitizen(long citizenId) {
		Citizen citizen = findCitizenById(citizenId);
		root = remove(root, citizen);
	}

	int height(Node node) {

		// Case in which the node does not exist, it is a NULL pointer (height = -1);
		if (node == null){
			return -1;
		}

		// Case in which the node exists, therefore, returns its height.
		else {
			node.calculateHeight();
			return node.getHeight();
		}
	}

	int getBalance(Node currentNode) {
		currentNode.calculateHeight();
		return height(currentNode.left) - height(currentNode.right);
	}

	// Function that calculates if the node is balanced (Checks the BF). Rotates the node if it's unbalanced.
	private Node balance (Node currentNode, Citizen citizen) {

		int balance = getBalance(currentNode);	// FB value is checked because BF = h(left) - h(right)

		// The unbalanced functions will be called only on the node that has the wrong balanced factor (BF < -2 o BF > 2)

		// Positive balance = right rotate or right + left rotate.
		// Negative balance = left rotate or left + right rotate.
		// Since the add function is recursive, the first unbalance node is checked (the lowest one).

		// If (valueToInsert < currentNode.left) and (BF > 1) -> left-left tree
		if (currentNode.left != null && balance > 1 && citizen.getWeight() < currentNode.left.getCitizenWeight()) {
			return rightRotate(currentNode);
		}

		// If (valueToInsert > currentNode.right) and (BF < -1) -> right-right tree
		if (currentNode.right != null && balance < -1 && citizen.getWeight() > currentNode.right.getCitizenWeight()) {
			return leftRotate(currentNode);
		}

		// If (valueToInsert > currentNode.left) and (BF > 1) -> left-right tree
		if (currentNode.left != null && balance > 1 && citizen.getWeight() > currentNode.left.getCitizenWeight()) {
			currentNode.left = leftRotate(currentNode.left);
			return rightRotate(currentNode);
		}

		// If (valueToInsert < currentNode.right) and (BF < -1) -> right-left tree
		// RL = center to the right, and left child new center. Then center's parent rotates left.
		if (currentNode.right != null && balance < -1 && citizen.getWeight() < currentNode.right.getCitizenWeight()) {
			currentNode.right = rightRotate(currentNode.right);
			return leftRotate(currentNode);
		}

		return currentNode;
	}

	private Node rightRotate(Node currentNode) {	// Current node = y

		// Initialize the main nodes to make the rotation.
		Node center = currentNode.left;	// Assign the center node (left of the current node).
		Node rightNode = center.right;	// Assign the right node to the right child of the center node.

		// Assign the rotated nodes.
		center.right = currentNode;		// Current becomes the right child of the center (because current is higher).
		currentNode.left = rightNode;	// The right child of the center becomes the left child of the current (because this child is lower than current).

		// Assign all the necessary parents.
		if (rightNode != null) {	// In case the center does not have a right child.
			rightNode.parent = currentNode;		// The parent of the center's right child is the current node.
		}
		center.parent = currentNode.parent;	// The parent of the new center becomes the parent of the current node.
		currentNode.parent = center;		// The current node's parent becomes the new center.

		// Re-calculate the height of each node (only the ones that rotate).
		center.calculateHeight();
		currentNode.calculateHeight();

		return center;
	}

	// Método para realizar una rotación hacia la izquierda
	private Node leftRotate(Node currentNode) {

		// Initialize the main nodes to make the rotation.
		Node center = currentNode.right;
		Node leftNode = center.left;

		// Assign the rotated nodes.
		center.left = currentNode;
		currentNode.right = leftNode;

		// Assign all the necessary parents.
		if (leftNode != null) {	// In case the center does not have a right child.
			leftNode.parent = currentNode;
		}
		center.parent = currentNode.parent;
		currentNode.parent = center;

		// Re-calculate the height of each node (only the ones that rotate).
		center.calculateHeight();
		currentNode.calculateHeight();

		return center;
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

		if (valueToInsert < currentNodeValue) {     // We go to the left child if the value that we want to insert is lower than the current node's value
			currentNode.left = add(currentNode.left, citizen, currentNode);
		} else if (valueToInsert > currentNodeValue) {       // We go to the right child if the value that we want to insert is higher than or equal to the current node's value
			currentNode.right = add(currentNode.right, citizen, currentNode);
		} else if (valueToInsert == currentNodeValue) {
			currentNode.addCitizen(citizen);
		}

		// Case where the node is added
		currentNode = balance(currentNode, citizen);

		return currentNode;

	}

	private Node remove (Node currentNode, Citizen citizen) {

		if (currentNode == null) {
			return null;
		}

		// We go to the left child if the value that we want to delete is higher than the current node's value
		if (citizen.getWeight() > currentNode.getCitizenWeight()) {
			currentNode.right = remove(currentNode.right, citizen);
			currentNode.calculateHeight(); // Re-calculate the height of the current node.
			currentNode = balance(currentNode, citizen);
			return currentNode;
		}

		//We go to the right child if the value that we want to delete is lower than the current node's value
		if (citizen.getWeight() < currentNode.getCitizenWeight()) {
			currentNode.left = remove(currentNode.left, citizen);
			currentNode.calculateHeight(); // Re-calculate the height of the current node.
			currentNode = balance(currentNode, citizen);
			return currentNode;
		}

		// Node to delete found
		if (citizen.getWeight() == currentNode.getCitizenWeight()) {

			if (currentNode.getCitizens().length == 1) {
				//If the node does not have children, we return null (replacing this node with null in the parent)
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

			} else {
				// Just remove the citizen and update the list, but keep the node in the same place (return the same node).
				currentNode.removeCitizen(citizen);
				return currentNode;
			}
			/////////////////////////////////
		}

		//If the node has two children, we need to reorganize the tree.

		//We will need to replace the node with another node that has a suitable value.
		//Knowing the value of the node that we want to delete, we will choose the node with the
		//next biggest value as a substitute. To choose this node, we will first go to the left node
		//(which has a greater value) and then find the lowest value in the subtree. This value will
		//be the next biggest value that we were searching for

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


