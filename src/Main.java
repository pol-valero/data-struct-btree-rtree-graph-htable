import Menu.BinaryTreesBruixes.BruixesMenuOptions;
import Menu.Menu;
import Menu.GraphsOrenetes.OrenetesMenuLogic;
import Menu.BinaryTreesBruixes.BruixesMenuLogic;
import Menu.GraphsOrenetes.OrenetesMenuOptions;
import BinaryTreesF2.Entities.Trees.TreeType;
import Menu.RTreesBardissa.BardissaMenuOptions;

public class Main {

    private final static String GRAPHS_DATASET = "files/graphs/graphsXXS.paed"; // Relative path inside /src folder
    private final static String BINARY_TREES_DATASET = "files/trees/treeXS.paed";      // Relative path inside /src folder
    private final static String R_TREES_DATASET = "files/trees/treeXS.paed";     // Relative path inside /src folder

    public static void main(String[] args) {

        do {
            switch (Menu.showMainMenu()) {

                // Graphs (phase 1)
                case ORENETES -> { OrenetesMenuOptions orenetesOption;
                    do {
                        orenetesOption = Menu.showOrenetesMenu(GRAPHS_DATASET);
                        switch (orenetesOption) {
                            case KINGDOM_EXPLORATION -> OrenetesMenuLogic.showKingdomExploration(); // GraphDFS + GraphBFS
                            case COMMON_ROUTES -> OrenetesMenuLogic.showFrequentRoutesDetection();  // MST Prim
                            case PREMIUM_MESSAGING -> OrenetesMenuLogic.showPremiumMessaging();     // Djikstra
                        }
                    } while (orenetesOption != OrenetesMenuOptions.PREVIOUS_MENU);
                }

                // Trees (phase 2)
                case BRUIXES -> { BruixesMenuOptions bruixesOption;
                    do {
                        bruixesOption = Menu.showWitchesMenu(BINARY_TREES_DATASET);
                        BruixesMenuLogic.checkIfTreeCreated(TreeType.AVLTREE);  // Check if Tree is already created or not
                        switch (bruixesOption) {
                            case ADD_CITIZEN -> BruixesMenuLogic.showAddCitizen();                      // Add a node to the tree
                            case REMOVE_CITIZEN -> BruixesMenuLogic.showRemoveCitizen();                // Remove a node from the tree
                            case VISUAL_REPRESENTATION -> BruixesMenuLogic.showTreeRepresentation();    // Visual tree representation
                            case WITCHES_IDENTIFICATION -> BruixesMenuLogic.showWitchIdentification();  // Search nodes with same, higher or lower value
                            case BATUDA -> BruixesMenuLogic.showBatuda();                               // Search nodes between a range of values
                        }
                    } while (bruixesOption != BruixesMenuOptions.PREVIOUS_MENU);
                }

                case BARDISSA -> { BardissaMenuOptions bardissaMenuOptions;
                    do {
                        bardissaMenuOptions = Menu.showHedgeMenu(R_TREES_DATASET);

                        // Check if Tree is already created or not

                        switch (bardissaMenuOptions) {
                            case ADD_HEDGE -> BruixesMenuLogic.showAddCitizen();                        // Add a hedge to the tree
                            case REMOVE_HEDGE -> BruixesMenuLogic.showRemoveCitizen();                  // Remove a hedge from the tree
                            case VISUAL_REPRESENTATION -> BruixesMenuLogic.showTreeRepresentation();    // Visual R-Tree representation
                            case AREA_SEARCH -> BruixesMenuLogic.showWitchIdentification();             // Search points in an area
                            case AESTHETIC_OPTIMIZATION -> BruixesMenuLogic.showBatuda();               //
                        }
                    } while (bardissaMenuOptions != BardissaMenuOptions.PREVIOUS_MENU);
                }

                case OPTION_4 -> System.out.println(4);

                case EXIT -> {
                    System.out.println(Menu.EXIT);
                    System.exit(0);
                }
            }

        } while (true);

    }

}