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

    }

    private static ArrayList<KnownRoute> mstPrim (Graph graph, PlaceOfInterest origin) {

        ArrayList<KnownRoute> mstRoutes = new ArrayList<>();
        int totalNodeNum = graph.getSize();

        //Number of nodes that are currently in the MST
        int mstNodeNum = 0;
        float minDist;

        int firstNodeIndex = 0;
        int secondNodeIndex = 0;
        boolean validEdgeFound;

        //Used to mark nodes that are included in the MST
        boolean[] existsInMST = new boolean[graph.getSize()];

        existsInMST[origin.getRowIndex()] = true;

        while (mstNodeNum < totalNodeNum) {

            minDist = Float.MAX_VALUE;
            validEdgeFound = false;

            for (int i = 0; i < totalNodeNum; i++) {

                for (int j = 0; j < totalNodeNum; j++) {

                    firstNodeIndex = i;
                    secondNodeIndex = j;

                    if (checkValidEdge(firstNodeIndex, secondNodeIndex, existsInMST) && graph.getRouteDistance(firstNodeIndex, secondNodeIndex) < minDist) {
                        minDist = graph.getRouteDistance(firstNodeIndex, secondNodeIndex);
                        validEdgeFound = true;
                    }

                }

            }

            if (validEdgeFound) {
                mstRoutes.add(graph.getRoute(firstNodeIndex, secondNodeIndex));
                existsInMST[firstNodeIndex] = true;
                existsInMST[secondNodeIndex] = true;
            }

        }

        return mstRoutes;

    }

    private static boolean checkValidEdge(int firstNodeIndex, int secondNodeIndex, boolean[] existsInMST) {

        if (firstNodeIndex == secondNodeIndex) {
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

