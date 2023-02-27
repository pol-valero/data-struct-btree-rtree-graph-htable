package Algorithms;

import Entities.Graph;
import Entities.PlaceOfInterest;
import Entities.Swallow;

public class dijkstra {
    public static PlaceOfInterest[] premiumMessaging(Graph graph, PlaceOfInterest initialNode, PlaceOfInterest finalNode, Swallow swallow) {
        int N = graph.getSize();
        int visited = 0;

        PlaceOfInterest camins[] = new PlaceOfInterest[N];
        camins[0] = initialNode;

        double dist[] = new double[N];
        // La distancia del vértice origen hacia el mismo es siempre 0
        dist[0] = 0;

        // Initialize all distances as INFINITE and visited[] as false
        for (int i = 0; i < N; i++) {
            dist[i] = Integer.MAX_VALUE;
        }

        int actual = 0;
        int adj = 0;

        // WHILE
        while(visited < graph.getSize() && !finalNode.isVisited()){

            PlaceOfInterest currentNode = camins[actual];
            PlaceOfInterest[] adjacentNodes = graph.getAdjacents(currentNode);

            // Iterate through all the adjacent nodes.
            for (PlaceOfInterest adjacentNode : adjacentNodes) {

                if (!adjacentNode.isVisited()) {
                    adjacentNode.justVisited();
                    double nova = dist[actual] + graph.getRouteDistance(adjacentNode.getId(), currentNode.getId());
                    // Check if the dist of the adjacent is bigger than the new one
                    if (dist[adj] > nova) {
                        dist[adj] = nova;
                        camins[adj] = adjacentNode;
                    }
                }
                adj++;
            }
            actual++;
        }

        updateDist(dist, camins); // Update the total distance of the Swallow
        return camins;
    }

    private static void updateDist(double[] dist, PlaceOfInterest way[]) {

    }
}
