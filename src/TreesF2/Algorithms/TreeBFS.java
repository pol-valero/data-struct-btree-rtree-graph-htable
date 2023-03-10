package TreesF2.Algorithms;

import Auxiliar.MyQueue;
import TreesF2.Entities.Citizen;
import TreesF2.Entities.Node;

public class TreeBFS {
	public static Citizen findCitizenById (Node rootNode, long citizenId) {
		MyQueue<Node> myQueue = new MyQueue<>();
		myQueue.add(rootNode);

		while (!myQueue.isEmpty()) {
			Node newNode = myQueue.poll();

			// Check if newNode has the same ID.
			if (newNode.sameID(citizenId)) {
				return newNode.getCitizen();
			}

			// Add left child to the queue.
			if (newNode.left != null) {
				myQueue.add(newNode.left);
			}

			// Add right child to the queue.
			if (newNode.right != null) {
				myQueue.add(newNode.right);
			}
		}

		return null;
	}
}
