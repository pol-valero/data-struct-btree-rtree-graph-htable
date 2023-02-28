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
        dist.add(0.0);

        // Initialize all distances as INFINITE and visited[] as false
        for (int i = 0; i < N; i++) {
            dist.add(Double.MAX_VALUE);
        }

        int actual = 0;
        int adj = 0;

        // WHILE
        while(visited < graph.getSize() && !finalNode.isVisited()){

            PlaceOfInterest currentNode = camins.get(actual);
            PlaceOfInterest[] adjacentNodes = graph.getAdjacents(currentNode);

            // Iterate through all the adjacent nodes.
            for (PlaceOfInterest adjacentNode : adjacentNodes) {

                if (!adjacentNode.isVisited()) {
                    // Comprovar clima
                    adjacentNode.justVisited();
                    visited++;
                     // Comprovar 50 km
                    double nova = dist.get(actual) + graph.getRouteDistance(adjacentNode.getRowIndex(), currentNode.getRowIndex());
                    // Check if the dist of the adjacent is bigger than the new one
                    if (dist.get(adj) > nova) {
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
        PlaceOfInterest nextNode = way.get(way.size() - 1);
        double totalDist = 0;

        while (currentNode != initialNode) {
            totalDist = dist.get(way.indexOf(currentNode));
            currentNode = nextNode;
            way.indexOf(currentNode);
        }

        swallow.updateDist(totalDist);
    }
}
