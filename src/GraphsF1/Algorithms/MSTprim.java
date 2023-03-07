package GraphsF1.Algorithms;

import GraphsF1.Entities.Graph;
import GraphsF1.Entities.KnownRoute;
import GraphsF1.Entities.PlaceOfInterest;

import Auxiliar.myArrayList;


public class MSTprim {

    public static void frequentRoutesDetection(Graph graph) {

        myArrayList<KnownRoute> knownRoutes;

        //Any node could be selected as the origin. We choose the first node as the origin.
        PlaceOfInterest origin = graph.getFirstNode();

        knownRoutes = mstPrim(graph, origin);

        printMst(knownRoutes, graph);

    }

    private static void printMst (myArrayList<KnownRoute> knownRoutes, Graph graph) {

        float distance = 0;

        for (KnownRoute knownRoute: knownRoutes) {
            distance += knownRoute.getDistance();
            System.out.println("\n" + graph.getPlaceByID(knownRoute.getPlaceA()).getName() + " <--> " + graph.getPlaceByID(knownRoute.getPlaceB()).getName());
        }

        System.out.println("\n\nDistància a recórrer: " + distance);

    }

    private static myArrayList<KnownRoute> mstPrim (Graph graph, PlaceOfInterest origin) {

        myArrayList<KnownRoute> mstRoutes = new myArrayList<>();

        //Number of nodes in the original graph
        int totalNodesNum = graph.getSize();

        //Number of nodes that are currently in the MST
        int mstNodeNum;

        float minDist;

        KnownRoute minRoute = null;

        int firstNodeIndex = 0;
        int secondNodeIndex = 0;

        boolean validEdgeFound;

        //Used to mark nodes that are included in the MST
        boolean[] existsInMST = new boolean[graph.getSize()];

        existsInMST[origin.getRowIndex()] = true;
        mstNodeNum = 1;

        //We keep searching until all nodes of the original graph appear in the MST
        while (mstNodeNum < totalNodesNum) {

            minDist = Float.MAX_VALUE;
            validEdgeFound = false;

            for (int i = 0; i < totalNodesNum; i++) {

                for (int j = 0; j < totalNodesNum; j++) {

                    //We check if the current edge is a possible candidate to be selected
                    if (checkValidEdge(i, j, existsInMST, graph)) {

                        //We check if the current edge is better than the best edge yet
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

                //We add the route to the MST
                mstRoutes.add(minRoute);

                //We mark both nodes as visited even though one of them is already marked as visited.
                //We do this because we do not know which one of them is already marked as visited.
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
        return existsInMST[firstNodeIndex] != existsInMST[secondNodeIndex];
    }

}

