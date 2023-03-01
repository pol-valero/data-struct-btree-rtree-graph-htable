package Algorithms;

import Entities.Graph;
import Entities.PlaceOfInterest;

import java.util.LinkedList;
import java.util.Queue;

public class BFS {

	public static void kindgomExploration(Graph graph, PlaceOfInterest initialNode) {
		Queue<PlaceOfInterest> kingdomQueue = new LinkedList<>();
		Queue<PlaceOfInterest> outsideQueue = new LinkedList<>();
//		myQueue kingdomQueue = new myQueue();
//		myQueue outsideQueue = new myQueue();

		// First initialNode will always be in the same kingdom.
		kingdomQueue.add(initialNode);
		initialNode.justVisited();

		// First show places on the same kingdom.
		while (!kingdomQueue.isEmpty()) {
			PlaceOfInterest currentNode = kingdomQueue.poll();

			// Show the place information if it belongs to the same kingdom.
			if (currentNode.sameKingdom(initialNode) && !currentNode.samePlace(initialNode)) {
				System.out.println(currentNode.showInformation());
			}

			PlaceOfInterest[] adjacentNodes = graph.getAdjacents(currentNode);

			// Iterate through all the adjacent nodes.
			for (PlaceOfInterest adjacentNode : adjacentNodes) {
				if (!adjacentNode.isVisited()) {
					adjacentNode.justVisited();

					// Check if the adjacent initialNode belongs to the same kingdom.
					if (adjacentNode.sameKingdom(initialNode)) {
						kingdomQueue.add(adjacentNode);
					} else {
						outsideQueue.add(adjacentNode);
					}
				}
			}
		}

		// Get all the adjacent nodes that are not directly connected to the initial's node kingdom.
		while (!outsideQueue.isEmpty()) {
			PlaceOfInterest currentNode = outsideQueue.poll();

			// Show the place information if it belongs to the same kingdom.
			if (currentNode.sameKingdom(initialNode) && !currentNode.samePlace(initialNode)) {
				System.out.println(currentNode.showInformation());
			}

			PlaceOfInterest[] adjacentNodes = graph.getAdjacents(currentNode);

			// Iterate through all the adjacent nodes.
			for (PlaceOfInterest adjacentNode : adjacentNodes) {
				if (!adjacentNode.isVisited()) {
					adjacentNode.justVisited();
					outsideQueue.add(adjacentNode);
				}
			}
		}
	}
}
