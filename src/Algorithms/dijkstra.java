package Algorithms;

import Entities.Graph;
import Entities.PlaceOfInterest;
import Entities.Swallow;
import Entities.myArrayList;

import java.util.ArrayList;

public class dijkstra {
    public static PlaceOfInterest[] premiumMessaging(Graph graph, PlaceOfInterest initialNode, PlaceOfInterest finalNode, Swallow swallow) {
        int N = graph.getSize();
        int visited = 0;

        myArrayList<PlaceOfInterest> camins = new myArrayList<>();
        camins.add(initialNode);

        myArrayList<Double> dist = new myArrayList<>();
        // La distancia del vértice origen hacia el mismo es siempre 0
        dist.add(0);

        // Initialize all distances as INFINITE and visited[] as false
        for (int i = 0; i < N; i++) {
            dist.add(Integer.MAX_VALUE);
        }

        int actual = 0;
        int adj = 0;

        // WHILE
        while(visited < graph.getSize() && !finalNode.isVisited()){

            PlaceOfInterest currentNode = (PlaceOfInterest) camins.get(actual);
            PlaceOfInterest[] adjacentNodes = graph.getAdjacents(currentNode);

            // Iterate through all the adjacent nodes.
            for (PlaceOfInterest adjacentNode : adjacentNodes) {

                if (!adjacentNode.isVisited()) {
                    adjacentNode.justVisited();
                    visited++;
                    double nova = (Double) dist.get(actual) + graph.getRouteDistance(adjacentNode.getId(), currentNode.getId());
                    // Check if the dist of the adjacent is bigger than the new one
                    if ((Double) dist.get(adj) > nova) {
                        dist.set(adj, nova);
                        camins.set(adj, adjacentNode);
                    }
                }
                adj++;
            }
            actual++;
        }

        updateDist(dist, camins, initialNode, finalNode, swallow); // Update the total distance of the Swallow
        return camins.toArray();
    }

    private static void updateDist(myArrayList<Double> dist, myArrayList<PlaceOfInterest> way, PlaceOfInterest initialNode, PlaceOfInterest finalNode, Swallow swallow) {
        PlaceOfInterest currentNode = finalNode;
        PlaceOfInterest nextNode = (PlaceOfInterest) way.get(way.size() - 1);
        double totalDist = 0;

        while (currentNode != initialNode) {
            totalDist = (Double) dist.get(way.indexOf(currentNode));
            currentNode = nextNode;
            way.indexOf(currentNode);
        }

        swallow.updateDist(totalDist);
    }
}
