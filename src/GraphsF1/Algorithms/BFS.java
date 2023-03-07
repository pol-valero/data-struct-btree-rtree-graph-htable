package GraphsF1.Algorithms;

import GraphsF1.Entities.Graph;
import GraphsF1.Entities.PlaceOfInterest;
import Auxiliar.myQueue;

public class BFS {

	public static void kingdomExploration(Graph graph, PlaceOfInterest initialNode) {
		myQueue<PlaceOfInterest> queue = new myQueue<>();

		// First initialNode will always be in the same kingdom.
		queue.add(initialNode);
		initialNode.justVisited();

		// First show places on the same kingdom.
		while (!queue.isEmpty()) {
			PlaceOfInterest currentNode = queue.poll();

			// Show the place information if it belongs to the same kingdom.
			if (currentNode.sameKingdom(initialNode) && !currentNode.samePlace(initialNode)) {
				System.out.println(currentNode.showInformation());
				DFS.kingdomExploration(graph, currentNode);
			}

			PlaceOfInterest[] adjacentNodes = graph.getAdjacents(currentNode);

			// Iterate through all the adjacent nodes.
			for (PlaceOfInterest adjacentNode : adjacentNodes) {
				if (!adjacentNode.isVisited()) {
					adjacentNode.justVisited();

					// Check if the adjacent initialNode belongs to the same kingdom.
					queue.add(adjacentNode);
				}
			}
		}
	}
}
