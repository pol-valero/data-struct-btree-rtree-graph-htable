package Menu.Bruixes;

import Menu.Menu;
import Parsers.DatasetLoaderF2;
import TreesF2.Entities.Tree;
import TreesF2.Entities.TreeImplementation;

public class BruixesMenuLogic {

    private static Tree tree;

    public static void showAddCitizen() {
    }

    public static void showRemoveCitizen() {

    }

    public static void showTreeRepresentation() {
        tree = new TreeImplementation();
        DatasetLoaderF2.loadCitizens(Menu.TREES_DATASET, tree);
        tree.printRepresentation();
    }

    public static void showWitchIdentification() {

    }

    public static void showBatuda() {

    }
}
