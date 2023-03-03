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
        double nova = 0.0;
        PlaceOfInterest[] nodes = initNodes(graph, initialNode);
        PlaceOfInterest currentNode = initialNode;

        //int intialRowIndex = initialNode.getRowIndex();
        //graph.swapTwoNodes(initialNode.getRowIndex(), 0);

        PlaceOfInterest[] camins = new PlaceOfInterest[N];
        camins[0] = initialNode;

        Double[] time = new Double[N];
        time[0] = 0.0;// La distancia del vértice origen hacia el mismo es siempre 0

        // Initialize all distances as INFINITE
        for (int i = 1; i < N; i++) {
            time[i] = Double.MAX_VALUE;
        }

        // WHILE
        while ( !finalNode.isVisited()){

            PlaceOfInterest[] adjacentNodes = graph.getAdjacents(currentNode);

            // Iterate through all the adjacent nodes.
            for (PlaceOfInterest adjacentNode : adjacentNodes) {
                if (!adjacentNode.isVisited()) {
                    //adjacentNode.justVisited();

                    // Comprovar clima
                    // Comprovar 50 km

                    if (time[currentNode.getRowIndex()] != Double.MAX_VALUE) {
                        nova = time[currentNode.getRowIndex()] + graph.getRouteTimeA(currentNode.getRowIndex(), adjacentNode.getRowIndex());
                    } else {
                        nova = graph.getRouteTimeA(currentNode.getRowIndex(), adjacentNode.getRowIndex());
                    }


                    // Check if the time of the adjacent is bigger than the new one
                    if (time[indexOfWays(adjacentNode, nodes)] > nova && graph.getRouteTimeA(currentNode.getRowIndex(), adjacentNode.getRowIndex()) != -1) {
                        time[indexOfWays(adjacentNode, nodes)] = nova;
                        camins[indexOfWays(adjacentNode, nodes)] = currentNode;

                        //time[adjacentNode.getRowIndex()] = nova;
                        //camins[adjacentNode.getRowIndex()] = currentNode;
                    }
                }
            }

            visited++; // Comptem quants portem de visitats per saber si ja els hem visitat tots i podem parar
            currentNode = graph.getPlaceByIndex(getMinNode(time, graph)); // ACTUAL = VALOR MÍNIM DE D NO VISITATS (AGAFAR EL NODE AMB MENYS DISTÀNCIA)
            currentNode.justVisited(); // ACTUAL.VISITAT = CERT
        }

        //updateDist(time, camins, initialNode, finalNode, swallow); // Update the total distance of the Swallow

        //graph.swapTwoNodes(intialRowIndex, 0);

        return finalWay(camins, time, finalNode, nodes);
    }

    private static PlaceOfInterest[] initNodes(Graph graph, PlaceOfInterest initialNode) {
        PlaceOfInterest[] nodes = graph.getPlaces().clone();
        nodes[initialNode.getRowIndex()] = graph.getPlaceByIndex(0);
        nodes[0] = graph.getPlaceByIndex(initialNode.getRowIndex());
        return nodes;
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

    private static PlaceOfInterest[] finalWay(PlaceOfInterest[] ways, Double[] time, PlaceOfInterest finalNode, PlaceOfInterest[] nodes) {
        PlaceOfInterest[] finalWay = new PlaceOfInterest[ways.length];
        PlaceOfInterest nextNode = finalNode;
        //double totalTime = 0.0;

        int node = ways.length-1;
        int counter = 0;

        while (counter < ways.length && node != 0) {
            finalWay[counter] = nextNode;
            nextNode = ways[node];
            node = indexOfWays(nextNode, nodes);
            counter++;
        }

        finalWay[counter] = ways[0];

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
