package GraphsF1.Algorithms;

import GraphsF1.Entities.Graph;
import GraphsF1.Entities.PlaceOfInterest;

public class DFS {

	public static void kingdomExploration(Graph graph, PlaceOfInterest initialNode) {
		// Avoid being shown the same node twice (in case it was called from BFS algorithm since it was already visited).
		if (!initialNode.isVisited()) {
			initialNode.justVisited();
			System.out.println(initialNode.showInformation());
		}

		PlaceOfInterest[] adjacents = graph.getAdjacents(initialNode);
		for (PlaceOfInterest adjacent : adjacents) {
			// Check if adjacent node belongs to the same kingdom.
			if (!adjacent.isVisited() && adjacent.sameKingdom(initialNode)) {
				kingdomExploration(graph, adjacent);
			}
		}
	}
}
