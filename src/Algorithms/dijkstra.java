package Algorithms;

import Entities.Climate;
import Entities.Graph;
import Entities.PlaceOfInterest;
import Entities.Swallow;

import java.util.Arrays;

public class dijkstra {
    public static PlaceOfInterest[] premiumMessaging(Graph graph, PlaceOfInterest initialNode, PlaceOfInterest finalNode, Swallow swallow) {
        int N = graph.getSize();
        int visited = 0;

        PlaceOfInterest[] nodes = initNodes(graph, initialNode);
        PlaceOfInterest currentNode = initialNode;

        PlaceOfInterest[] camins = new PlaceOfInterest[N];
        camins[0] = initialNode;

        Float[] dist = new Float[N];
        Float[] time = new Float[N];

        // Initialize all distances as INFINITE
        for (int i = 0; i < N; i++) {
            dist[i] = 0.0f;
            time[i] = Float.MAX_VALUE;
        }
        time[0] = 0.0f;// La distancia del vértice origen hacia el mismo es siempre 0

        while (visited < N && !finalNode.isVisited()){

            PlaceOfInterest[] adjacentNodes = graph.getAdjacents(currentNode);

            // Iterate through all the adjacent nodes.
            for (PlaceOfInterest adjacentNode : adjacentNodes) {
                if (!adjacentNode.isVisited()) {
                    // Check if the distance is > 50 km when the swallow has a coco
                    if (swallow.getHasCoco()){
                        if (graph.getRouteDistance(currentNode.getRowIndex(), adjacentNode.getRowIndex()) <= 50) {
                            calculateTime(graph, swallow, nodes, currentNode, camins, dist, time, adjacentNode);
                        }
                    } else {
                        calculateTime(graph, swallow, nodes, currentNode, camins, dist, time, adjacentNode);
                    }
                }
            }
            currentNode = nodes[getMinNode(time, nodes)];
            currentNode.justVisited();
            visited++;
        }
        if(finalNode.isVisited()) {
            return finalWay(camins, time, finalNode, nodes, swallow, dist);
        } else {
            swallow.updateTime(Float.MAX_VALUE);
            return null;
        }
    }

    private static void calculateTime(Graph graph, Swallow swallow, PlaceOfInterest[] nodes, PlaceOfInterest currentNode, PlaceOfInterest[] camins, Float[] dist, Float[] time, PlaceOfInterest adjacentNode) {
        float nova;

        int indexCurrentNode = indexOfWays(currentNode, nodes);
        int indexAdjacentNode = indexOfWays(adjacentNode, nodes);

        if (swallow.getNotClimate() == Climate.TROPICAL){
            // European Swallow
            if (time[indexCurrentNode] != Float.MAX_VALUE) {
                nova = time[indexCurrentNode] + graph.getRouteTimeE(currentNode.getRowIndex(), adjacentNode.getRowIndex());
            } else {
                nova = graph.getRouteTimeE(currentNode.getRowIndex(), adjacentNode.getRowIndex());
            }

            // Check if the time of the adjacent is bigger than the new one
            if (time[indexAdjacentNode] > nova && graph.getRouteTimeE(currentNode.getRowIndex(), adjacentNode.getRowIndex()) != -1) {
                time[indexAdjacentNode] = nova;
                camins[indexAdjacentNode] = currentNode;
                dist[indexAdjacentNode] = graph.getRouteDistance(currentNode.getRowIndex(), adjacentNode.getRowIndex());
            }
        } else {
            // African Swallow
            if (time[indexCurrentNode] != Float.MAX_VALUE) {
                nova = time[indexCurrentNode] + graph.getRouteTimeA(currentNode.getRowIndex(), adjacentNode.getRowIndex());
            } else {
                nova = graph.getRouteTimeA(currentNode.getRowIndex(), adjacentNode.getRowIndex());
            }

            // Check if the time of the adjacent is bigger than the new one
            if (time[indexAdjacentNode] > nova && graph.getRouteTimeA(currentNode.getRowIndex(), adjacentNode.getRowIndex()) != -1) {
                time[indexAdjacentNode] = nova;
                camins[indexAdjacentNode] = currentNode;
                dist[indexAdjacentNode] = graph.getRouteDistance(currentNode.getRowIndex(), adjacentNode.getRowIndex());
            }
        }
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

    private static int getMinNode(Float[] time, PlaceOfInterest[] nodes) {
        float min = Float.MAX_VALUE;
        int minIndex = 0;

        for (int i = 0; i < time.length; i++) {
            if (time[i] < min && !nodes[i].isVisited()) {
                minIndex = i;
                min = time[i];
            }
        }
        return minIndex;
    }

    private static PlaceOfInterest[] finalWay(PlaceOfInterest[] ways, Float[] time, PlaceOfInterest finalNode, PlaceOfInterest[] nodes, Swallow swallow, Float[] dist) {
        PlaceOfInterest[] finalWay = new PlaceOfInterest[ways.length];
        PlaceOfInterest nextNode = finalNode;
        float totalDist = 0.0f;

        int node = indexOfWays(nextNode, nodes);
        int current = 0;
        int counter = ways.length-1;

        while (current < ways.length && node != 0) {
            nextNode.notVisited();
            finalWay[counter] = nextNode;

            totalDist += dist[node];

            nextNode = ways[node];
            node = indexOfWays(nextNode, nodes);
            current++;
            counter--;
        }

        finalWay[current] = ways[0];

        int index = indexOfWays(finalNode, nodes);
        swallow.updateDist(totalDist);
        swallow.updateTime(time[index]);

        return finalWay;
    }
}
