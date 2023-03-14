package Menu.Bruixes;

import Auxiliar.MyArrayList;
import Menu.Menu;
import Parsers.DatasetLoaderF2;
import TreesF2.Entities.Citizen;
import TreesF2.Entities.ObjectType;
import TreesF2.Entities.Object;
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
        String name = Menu.askForString(Menu.separator + "Nom de l'objecte: ");
        float weight = Menu.askForFloat("Pes de l'objecte: ", 0, Float.MAX_VALUE);
        ObjectType objectType = Menu.askforObject("Tipus d'objecte: ");

        Object object = new Object(name, weight, objectType);
        Citizen witch = null;
        MyArrayList<Citizen> witches = null;

        if (object.getObjectType() == ObjectType.WOOD || object.getObjectType() == ObjectType.STONE) {
            witch = tree.findWitchByWoodAndStone(object);
        } else if (object.getObjectType() == ObjectType.DUCK) {
            witches = tree.findWitchByDuck(object);
        }

        if (witch == null || witches == null) {
            System.out.println("No hi ha cap habitant que compleixi aquesta condició.");
        } else {
            if (witch != null) {
                witch.printInfo(true);
            }
            if (witch != null) {
                for (int i = 0; i < witches.size(); i++) {
                    witches.get(i).printInfo(true);
                }
            }
        }
    }

    public static void showBatuda() {

        checkIfTreeCreated();

        float max, min;

        System.out.println();
        min = Menu.askForFloat("Pes mínim: ", 0.00f, Float.MAX_VALUE);
        max = Menu.askForFloat("Pes màxim: ", min, Float.MAX_VALUE);
        System.out.println();

        tree.findCitizensInRange(max, min);

    }

    //We only create the tree if it was not already created before
    private static void checkIfTreeCreated() {
        if (tree == null) {
            tree = new BinaryTree();
            DatasetLoaderF2.loadCitizens(Menu.TREES_DATASET, tree);
        }
    }
}
