package Algorithms;

import Entities.Graph;
import Entities.KnownRoute;
import Entities.PlaceOfInterest;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MSTprim {

    public static void frequentRoutesDetection(Graph graph) {

        ArrayList<KnownRoute> knownRoutes;

        PlaceOfInterest origin = graph.getFirstNode();

        knownRoutes = mstPrim(graph, origin);

        printMst(knownRoutes, graph);

    }

    private static void printMst (ArrayList<KnownRoute> knownRoutes, Graph graph) {

        float distance = 0;

        for (KnownRoute knownRoute: knownRoutes) {
            //System.out.println("\n Place A: " + knownRoute.getPlaceA() + "  Place B: " + knownRoute.getPlaceB());
            distance += knownRoute.getDistance();
            System.out.println("\n" + graph.getPlaceByID(knownRoute.getPlaceA()).getName() + " <--> " + graph.getPlaceByID(knownRoute.getPlaceB()).getName());
        }
        System.out.println("\n\nDistància a recórrer: " + distance);
    }

    private static ArrayList<KnownRoute> mstPrim (Graph graph, PlaceOfInterest origin) {

        ArrayList<KnownRoute> mstRoutes = new ArrayList<>();
        int totalNodeNum = graph.getSize();

        //Number of nodes that are currently in the MST
        int mstNodeNum = 0;
        float minDist;

        KnownRoute minRoute = null;

        int firstNodeIndex = 0;
        int secondNodeIndex = 0;
        boolean validEdgeFound;

        //Used to mark nodes that are included in the MST
        boolean[] existsInMST = new boolean[graph.getSize()];

        existsInMST[origin.getRowIndex()] = true;
        mstNodeNum = 1;

        while (mstNodeNum < totalNodeNum) {

            minDist = Float.MAX_VALUE;
            validEdgeFound = false;

            for (int i = 0; i < totalNodeNum; i++) {

                for (int j = 0; j < totalNodeNum; j++) {

                    if (checkValidEdge(i, j, existsInMST, graph)) {
                        if (graph.getRouteDistance(i, j) < minDist) {
                            minDist = graph.getRouteDistance(i, j);
                            minRoute = graph.getRoute(i, j);
                            validEdgeFound = true;

                            firstNodeIndex = i;
                            secondNodeIndex = j;
                        }
                    }

                }

            }

            if (validEdgeFound) {
                mstRoutes.add(minRoute);
                existsInMST[firstNodeIndex] = true;
                existsInMST[secondNodeIndex] = true;
                mstNodeNum++;
            }

        }

        return mstRoutes;

    }

    private static boolean checkValidEdge(int firstNodeIndex, int secondNodeIndex, boolean[] existsInMST, Graph graph) {

        if (firstNodeIndex == secondNodeIndex) {
            return false;
        }

        if (!graph.routeExists(firstNodeIndex, secondNodeIndex)) {
            return false;
        }

        //The edge will be valid (return true) only if one of the nodes is already in the MST and the other one is not.
        //Therefore, if both nodes exist or both nodes don't exist in the MST, we return false as the edge is invalid.
        if (existsInMST[firstNodeIndex] == existsInMST[secondNodeIndex]) {
            return false;
        }

        return true;
    }

}

