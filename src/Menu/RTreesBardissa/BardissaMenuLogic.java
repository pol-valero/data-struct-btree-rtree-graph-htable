package Menu.RTreesBardissa;

import Menu.Menu;
import Parsers.DatasetLoaderF3;
import RTreesF3.Entities.RTree;

public class BardissaMenuLogic {

    private static RTree rTree;

    public static void showAddHedge() {

        boolean error;

        String type;
        do {
            type = Menu.askForString("\nTipus de la bardissa: ");

            if (type.equals("CIRCLE") || type.equals("SQUARE")) {
                error = false;
            } else {
                System.out.println("\nNomés pot ser CIRCLE o SQUARE");
                error = true;
            }

        } while (error);

        float size = Menu.askForFloat("Mida de la bardissa: ", 0, Float.MAX_VALUE);
        double latitude = Menu.askForDouble("Latitud de la bardissa: ", 0, Double.MAX_VALUE);
        double longitude = Menu.askForDouble("Longitud de la bardissa: ", 0, Double.MAX_VALUE);
        String color = Menu.askForString("Color de la bardissa: ");

        //rTree.addHedge();

        System.out.println("\nUna nova bardissa aparegué a la Bretanya.");

    }

    // We only create the tree if it was not already created before
    public static void checkIfTreeCreated() {

        // If tree is not created yet, instantiate one.
        if (rTree == null) {

            rTree = new RTree();

            DatasetLoaderF3.loadHedges(Menu.R_TREES_DATASET, rTree);
        }
    }

}
