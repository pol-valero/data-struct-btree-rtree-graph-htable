package Menu.Bruixes;

import Menu.Menu;
import Parsers.DatasetLoaderF2;
import TreesF2.Entities.Citizen;
import TreesF2.Entities.Tree;
import TreesF2.Entities.Trees.BinaryTree;

public class BruixesMenuLogic {

    private static Tree tree;

    public static void showAddCitizen() {
        checkIfTreeCreated();

        long id = Menu.askForInteger("\nIdentificador de l'habitant: ", 0, Integer.MAX_VALUE);
        String name = Menu.askForString("Nom de l'habitant: ");
        float weight = Menu.askForFloat("Pes de l'habitant: ", 1, 200);
        String kingdom = Menu.askForString("Regne de l'habitant: ");

        tree.addCitizen(new Citizen(id, name, weight, kingdom));

        System.out.println("\n" + name + " ens acompanyarà a partir d'ara.");
    }

    public static void showRemoveCitizen() {
        checkIfTreeCreated();

        long id = Menu.askForInteger("\nIdentificador de l'habitant: ", 0, Integer.MAX_VALUE);
        Citizen citizen = tree.findCitizenById(id);

        //Only if the citizen appears in the tree we execute the delete function
        if (citizen != null) {
            tree.removeCitizen(id);
            System.out.println("\n" + citizen.getName() + " ha estat transformat en un grill.");
        } else {
            System.out.println("\nEl ID introduït no correspon a cap habitant de l'arbre.");
        }

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

        float max, min;

        min = Menu.askForFloat("Pes mínim: ", 0.00f, Float.MAX_VALUE);
        max = Menu.askForFloat("Pes màxim: ", 0.00f, Float.MAX_VALUE);

        tree.

    }

    //We only create the tree if it was not already created before
    private static void checkIfTreeCreated() {
        if (tree == null) {
            tree = new BinaryTree();
            DatasetLoaderF2.loadCitizens(Menu.TREES_DATASET, tree);
        }
    }
}
