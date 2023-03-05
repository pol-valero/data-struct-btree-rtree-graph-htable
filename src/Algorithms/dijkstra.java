package Algorithms;

import Entities.Graph;
import Entities.PlaceOfInterest;
import Entities.Swallow;
import Entities.myArrayList;

// Intentar canviar el graph que em passen:
    // Fer un Swap amb el primer lloc amb el lloc inici

public class dijkstra {
    public static myArrayList<PlaceOfInterest> premiumMessaging(Graph graph, PlaceOfInterest initialNode, PlaceOfInterest finalNode, Swallow swallow) {
        int N = graph.getSize();
        int visited = 0;

        myArrayList<PlaceOfInterest> camins = new myArrayList<>();
        camins.add(initialNode);

        myArrayList<Double> time = new myArrayList<>();
        // La distancia del vértice origen hacia el mismo es siempre 0
        time.add(0.0);

        // Initialize all distances as INFINITE and visited[] as false
        for (int i = 0; i < N; i++) {
            time.add(Double.MAX_VALUE);
        }

        int actual = 0;
        int adj;

        // WHILE
        while (visited < N && !finalNode.isVisited()){
            PlaceOfInterest currentNode = graph.getPlaceByIndex(actual);
            PlaceOfInterest[] adjacentNodes = graph.getAdjacents(currentNode);
            adj = 0;

            // Iterate through all the adjacent nodes.
            for (PlaceOfInterest adjacentNode : adjacentNodes) {

                if (!adjacentNode.isVisited()) {
                    // Comprovar clima
                    //adjacentNode.justVisited();

                     // Comprovar 50 km
                    double nova = time.get(actual) + graph.getRouteTime(adjacentNode.getRowIndex(), currentNode.getRowIndex());
                    // Check if the time of the adjacent is bigger than the new one
                    if (time.get(adj) > nova) {
                        if (adj > camins.size()){
                            time.add(nova);
                            camins.add(currentNode);
                        } else {
                            time.set(adj, nova);
                            camins.set(adj, currentNode);
                        }
                    }
                }
                adj++;
            }
            actual++;
            visited++;
            currentNode.justVisited();
        }

        //updateDist(time, camins, initialNode, finalNode, swallow); // Update the total distance of the Swallow

        return finalWay(camins, time);
    }

    private static myArrayList<PlaceOfInterest> finalWay(myArrayList<PlaceOfInterest> ways, myArrayList<Double> time) {
        myArrayList<PlaceOfInterest> finalWay = new myArrayList<PlaceOfInterest>();
        PlaceOfInterest nextNode;
        //double totalTime = 0.0;

        int node = ways.size()-1;
        while (node < ways.size() && node != 0) {
            nextNode = ways.get(node);
            finalWay.add(nextNode);
            node = nextNode.getRowIndex();
        }

        return finalWay;
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
