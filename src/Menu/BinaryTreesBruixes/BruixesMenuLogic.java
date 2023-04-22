package Menu.BinaryTreesBruixes;

import Auxiliar.MyArrayList;
import BinaryTreesF2.Entities.BinaryTree;
import BinaryTreesF2.Entities.Trees.BSTTree;
import Menu.Menu;
import Parsers.DatasetLoaderF2;
import BinaryTreesF2.Entities.Citizen;
import BinaryTreesF2.Entities.ObjectType;
import BinaryTreesF2.Entities.Object;
import BinaryTreesF2.Entities.Trees.AVLTree;
import BinaryTreesF2.Entities.Trees.TreeType;

public class BruixesMenuLogic {

    private static BinaryTree binaryTree;

    public static void showAddCitizen() {
        long id = Menu.askForInteger("\nIdentificador de l'habitant: ", 0, Integer.MAX_VALUE);
        String name = Menu.askForString("Nom de l'habitant: ");
        float weight = Menu.askForFloat("Pes de l'habitant: ", 1, 200);
        String kingdom = Menu.askForString("Regne de l'habitant: ");

        binaryTree.addCitizen(new Citizen(id, name, weight, kingdom));

        System.out.println("\n" + name + " ens acompanyarà a partir d'ara.");
    }

    public static void showRemoveCitizen() {
        long id = Menu.askForInteger("\nIdentificador de l'habitant: ", 0, Integer.MAX_VALUE);
        Citizen citizen = binaryTree.findCitizenById(id);

        //Only if the citizen appears in the binaryTree we execute the delete function
        if (citizen != null) {
            binaryTree.removeCitizen(id);
            System.out.println("\n" + citizen.getName() + " ha estat transformat en un grill.");
        } else {
            System.out.println("\nL'ID introduït no correspon a cap habitant de l'arbre.");
        }

    }

    public static void showTreeRepresentation(TreeType treeType) {
        binaryTree.visualRepresentation(treeType);
        binaryTree.printRepresentation();
    }

    public static void showWitchIdentification() {
        binaryTree.printRepresentation();
        String name = Menu.askForString(Menu.separator + "Nom de l'objecte: ");
        float weight = Menu.askForFloat("Pes de l'objecte: ", 0, Float.MAX_VALUE);
        ObjectType objectType = Menu.askforObject("Tipus d'objecte: ");

        Object object = new Object(name, weight, objectType);
        Citizen witch;
        MyArrayList<Citizen> witches = new MyArrayList<>();

        witch = binaryTree.findWitch(object, witches);

        if (witch == null && witches.size() == 0) {
            System.out.println("\t* No hi ha cap habitant que compleixi aquesta condició.");
        } else {
            if (witch != null) {
                witch.printInfo(true, true);
            }
            if (witches.size() != 0) {
                for (int i = 0; i < witches.size(); i++) {
                    witches.get(i).printInfo(true, true);
                }
            }
        }
    }

    public static void showBatuda() {
        float max, min;

        System.out.println();
        min = Menu.askForFloat("Pes mínim: ", 0.00f, Float.MAX_VALUE);
        max = Menu.askForFloat("Pes màxim: ", min, Float.MAX_VALUE);
        System.out.println();

        binaryTree.findCitizensInRange(max, min);

    }

    // We only create the binaryTree if it was not already created before
    public static void checkIfTreeCreated(TreeType treeType) {

        // If binaryTree is not created yet, instantiate one.
        if (binaryTree == null) {

            // "BSTTree" class, which uses a Binary Search BSTTree (not balanced) structure.
            if (treeType == TreeType.BINARYTREE) {
                binaryTree = new BSTTree();
            }
            else {
                // "AVLTree" class, which uses Balanced BSTTree structure.
                binaryTree = new AVLTree();
            }
            DatasetLoaderF2.loadCitizens(Menu.BINARY_TREES_DATASET, binaryTree);
        }
    }
}
