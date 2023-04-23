import Menu.BinaryTreesBruixes.BruixesMenuOptions;
import Menu.Menu;
import Menu.GraphsOrenetes.OrenetesMenuLogic;
import Menu.BinaryTreesBruixes.BruixesMenuLogic;
import Menu.GraphsOrenetes.OrenetesMenuOptions;
import BinaryTreesF2.Entities.Trees.TreeType;
import Menu.RTreesBardissa.BardissaMenuLogic;
import Menu.RTreesBardissa.BardissaMenuOptions;

public class Main {

    private final static String GRAPHS_DATASET = "files/graphs/graphsXXS.paed";     // Relative path inside /src folder
    private final static String BINARY_TREES_DATASET = "files/trees/treeXS.paed";   // Relative path inside /src folder
    private final static TreeType treeType = TreeType.AVLTREE;  // Select the type of tree used with Binary Trees.
    private final static String R_TREES_DATASET = "files/rtrees/rtreeXXS.paed";        // Relative path inside /src folder

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

                // Binary Trees (phase 2)
                case BRUIXES -> { BruixesMenuOptions bruixesOption;
                    do {
                        bruixesOption = Menu.showWitchesMenu(BINARY_TREES_DATASET);
                        BruixesMenuLogic.checkIfTreeCreated(treeType);  // Check if BSTTree is already created or not
                        switch (bruixesOption) {
                            case ADD_CITIZEN -> BruixesMenuLogic.showAddCitizen();                              // Add a node to the tree
                            case REMOVE_CITIZEN -> BruixesMenuLogic.showRemoveCitizen();                        // Remove a node from the tree
                            case VISUAL_REPRESENTATION -> BruixesMenuLogic.showTreeRepresentation(treeType);    // Visual tree representation
                            case WITCHES_IDENTIFICATION -> BruixesMenuLogic.showWitchIdentification();          // Search nodes with same, higher or lower value
                            case BATUDA -> BruixesMenuLogic.showBatuda();                                       // Search nodes between a range of values
                        }
                    } while (bruixesOption != BruixesMenuOptions.PREVIOUS_MENU);
                }

                // R-Trees (phase 3)
                case BARDISSA -> { BardissaMenuOptions bardissaMenuOptions;
                    do {
                        bardissaMenuOptions = Menu.showHedgeMenu(R_TREES_DATASET);
                        BardissaMenuLogic.checkIfTreeCreated();  // Check if RTree is already created or not

                        switch (bardissaMenuOptions) {
                            case ADD_HEDGE -> BardissaMenuLogic.showAddHedge();    // Add a hedge to the tree
                            case REMOVE_HEDGE -> {}             // Remove a hedge from the tree
                            case VISUAL_REPRESENTATION -> {}    // Visual R-BSTTree representation
                            case AREA_SEARCH -> {}              // Search points in an area
                            case AESTHETIC_OPTIMIZATION -> {}   //
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