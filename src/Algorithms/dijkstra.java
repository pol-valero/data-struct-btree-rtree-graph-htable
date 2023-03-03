package Algorithms;

import Entities.Graph;
import Entities.PlaceOfInterest;
import Entities.Swallow;
import Entities.myArrayList;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

// Intentar canviar el graph que em passen:
    // Fer un Swap amb el primer lloc amb el lloc inici

public class dijkstra {
    public static PlaceOfInterest[] premiumMessaging(Graph graph, PlaceOfInterest initialNode, PlaceOfInterest finalNode, Swallow swallow) {
        int N = graph.getSize();
        int visited = 0;
        PlaceOfInterest currentNode = initialNode;
        Collections.swap(Arrays.asList(graph.getPlaces()), initialNode.getRowIndex(), 0);

        PlaceOfInterest[] camins = new PlaceOfInterest[N];
        camins[0] = initialNode;

        Double[] time = new Double[N];
        time[0] = 0.0;// La distancia del vértice origen hacia el mismo es siempre 0

        // Initialize all distances as INFINITE
        for (int i = 1; i < N; i++) {
            time[i] = Double.MAX_VALUE;
        }

        // WHILE
        while (visited < N && !finalNode.isVisited()){

            PlaceOfInterest[] adjacentNodes = graph.getAdjacents(currentNode);

            // Iterate through all the adjacent nodes.
            for (PlaceOfInterest adjacentNode : adjacentNodes) {
                if (!adjacentNode.isVisited()) {
                    //adjacentNode.justVisited();

                    // Comprovar clima
                    // Comprovar 50 km

                    double nova = time[currentNode.getRowIndex()] + graph.getRouteTime(adjacentNode.getRowIndex(), currentNode.getRowIndex());

                    // Check if the time of the adjacent is bigger than the new one
                    if (time[adjacentNode.getRowIndex()] > nova) {
                        time[adjacentNode.getRowIndex()] = nova;
                        camins[adjacentNode.getRowIndex()] = currentNode;
                    }
                }
            }

            visited++; // Comptem quants portem de visitats per saber si ja els hem visitat tots i podem parar
            currentNode = graph.getPlaceByIndex(getMinNode(time, graph)); // ACTUAL = VALOR MÍNIM DE D NO VISITATS (AGAFAR EL NODE AMB MENYS DISTÀNCIA)
            currentNode.justVisited(); // ACTUAL.VISITAT = CERT
        }

        //updateDist(time, camins, initialNode, finalNode, swallow); // Update the total distance of the Swallow

        return camins;
    }

    private static int indexOfWays(PlaceOfInterest element, PlaceOfInterest[] ways) {

        for (int elementIndex = 0; elementIndex < ways.length; elementIndex++) {
            if (ways[elementIndex] == element) {
                return elementIndex;
            }
        }
        return -1;
    }

    private static int getMinNode(Double[] time, Graph graph) {
        Double min = Double.MAX_VALUE;
        int minIndex = 0;

        for (int i = 0; i < time.length; i++) {
            if (time[i] < min && !graph.getPlaceByIndex(i).isVisited()) {
                minIndex = i;
                min = time[i];
            }
        }

        return minIndex;
    }

    private static PlaceOfInterest[] finalWay(PlaceOfInterest[] ways, Double[] time) {
        PlaceOfInterest[] finalWay = new PlaceOfInterest[ways.length];
        PlaceOfInterest nextNode;
        //double totalTime = 0.0;

        int node = ways.length-1;
        while (node < ways.length && node != 0) {
            nextNode = ways[node];
            finalWay[node] = nextNode;
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
