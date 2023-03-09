package Menu.Bruixes;

import Menu.Menu;
import Parsers.DatasetLoaderF2;
import TreesF2.Entities.Citizen;
import TreesF2.Entities.Tree;
import TreesF2.Entities.TreeImplementation;

public class BruixesMenuLogic {

    private static Tree tree;

    public static void showAddCitizen() {

        checkIfTreeCreated();

        long id = Menu.askForInteger("\nIdentificador de l'habitant:  ", 0, Integer.MAX_VALUE);
        String name = Menu.askForString("Nom de l'habitant:  ");
        float weight = Menu.askForFloat("Pes de l'habitant:  ", 1, 200);
        String kingdom = Menu.askForString("Regne de l'habitant:  ");

        tree.addCitizen(new Citizen(id, name, weight, kingdom));

        System.out.println("\n" + name + " ens acompanyarà a partir d'ara.");

    }

    public static void showRemoveCitizen() {

        checkIfTreeCreated();

        long id = Menu.askForInteger("\nIdentificador de l'habitant:  ", 0, Integer.MAX_VALUE);

        String name = tree.findCitizenById(id).getName();

        tree.removeCitizen(id);

        System.out.println("\n" + name + " ha estat transformat en un grill.");

    }

    public static void showTreeRepresentation() {

        checkIfTreeCreated();

        tree.printRepresentation();
    }

    public static void showWitchIdentification() {

        checkIfTreeCreated();

    }

    public static void showBatuda() {

        checkIfTreeCreated();

    }

    private static void checkIfTreeCreated() {
        if (tree == null) {
            tree = new TreeImplementation();
            DatasetLoaderF2.loadCitizens(Menu.TREES_DATASET, tree);
        }
    }
}
