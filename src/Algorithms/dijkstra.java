package Algorithms;

import Entities.Graph;
import Entities.PlaceOfInterest;
import Entities.Swallow;

public class dijkstra {
    public static PlaceOfInterest[] premiumMessaging(Graph graph, PlaceOfInterest initialNode, PlaceOfInterest finalNode, Swallow swallow) {
        int N = graph.getSize();

        PlaceOfInterest camins[] = new PlaceOfInterest[N];

        int dist[] = new int[N];
        boolean visited[] = new boolean[N];

        PlaceOfInterest actual = initialNode;

        // Initialize all distances as INFINITE and visited[] as false
        for (int i = 0; i < N; i++) {
            dist[i] = Integer.MAX_VALUE;
            visited[i] = false;
        }
        // La distancia del vertice origen hacia el mismo es siempre 0
        dist[0] = 0;

        // WHILE

        return camins;
    }
}
