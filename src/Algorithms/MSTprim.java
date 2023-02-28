package Algorithms;

import Entities.Graph;
import Entities.KnownRoute;
import Entities.PlaceOfInterest;

import java.util.ArrayList;

public class MSTprim {

    public static void frequentRoutesDetection(Graph graph) {

        PlaceOfInterest origin = graph.getFirstNode();
        ArrayList<PlaceOfInterest> mstNodes = new ArrayList<>();
        ArrayList<KnownRoute> mstRoutes = new ArrayList<>();

        mstPrim(graph, origin, mstNodes, mstRoutes);
        printMST(mstNodes, mstRoutes);

    }

    private static void mstPrim (Graph graph, PlaceOfInterest origin, ArrayList<PlaceOfInterest> mstNodes, ArrayList<KnownRoute> mstRoutes) {

        mstNodes.add(origin);

        while (mstNodes.size() < graph.getSize()) {

            KnownRoute minRoute = null;
            PlaceOfInterest[] nodes = graph.getPlaces();

            //minRoute = findMinAdjacentRoute

            mstRoutes.add(minRoute);
        }

    }

    private static void printMST (ArrayList<PlaceOfInterest> mstNodes, ArrayList<KnownRoute> mstRoutes) {

    }



}

