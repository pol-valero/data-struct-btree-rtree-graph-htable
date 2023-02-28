package Algorithms;

import Entities.Graph;
import Entities.KnownRoute;
import Entities.PlaceOfInterest;

public class MSTprim {

    public static void frequentRoutesDetection(Graph graph) {

        KnownRoute[][] mst = new KnownRoute[graph.getSize()][graph.getSize()];
        PlaceOfInterest origin = graph.getFirstNode();
        mstPrim(mst, origin);
        printMST(mst);

    }

    private static void mstPrim (KnownRoute[][] mst, PlaceOfInterest origin) {

    }

    private static void printMST (KnownRoute[][] mst) {

    }



}

