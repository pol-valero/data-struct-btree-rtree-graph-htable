package Menu.RTreesBardissa;

import Auxiliar.MyArrayList;
import Menu.Menu;
import Parsers.DatasetLoaderF3;
import RTreesF3.Entities.Hedge;
import RTreesF3.Entities.Point;
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

        rTree.addHedge(new Hedge(type, size, latitude, longitude, color));

        System.out.println("\nUna nova bardissa aparegué a la Bretanya.");

    }

    public static void showAreaSearch() {
        String firstPoint = Menu.askForString("Entra el primer punt de l’àrea (lat,long): ");
        String secondPoint = Menu.askForString("Entra el segon punt de l’àrea (lat,long): ");

        Point p1 = new Point(Double.parseDouble(firstPoint.split(",")[1]), Double.parseDouble(firstPoint.split(",")[0]));
        Point p2 = new Point(Double.parseDouble(secondPoint.split(",")[1]), Double.parseDouble(secondPoint.split(",")[0]));

        MyArrayList<Hedge> hedgesFound = rTree.areaSearch(p1, p2);

        System.out.println("\nS'han trobat " + hedgesFound.size() + " bardisses en aquesta àrea\n");

        for (Hedge hedge: hedgesFound) {
            System.out.println("\t* " + hedge.getPoint().y + ", " + hedge.getPoint().x + ": " + hedge.getType());
            //TODO: Print the other fields
        }

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
