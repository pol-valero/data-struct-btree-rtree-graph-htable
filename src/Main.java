import Menu.TaulesHeretges.HeretgesMenuLogic;
import Menu.TaulesHeretges.HeretgesMenuOptions;
import Menu.TreesBruixes.BruixesMenuOptions;
import Menu.Menu;
import Menu.GraphsOrenetes.OrenetesMenuLogic;
import Menu.TreesBruixes.BruixesMenuLogic;
import Menu.GraphsOrenetes.OrenetesMenuOptions;
import TreesF2.Entities.Trees.TreeType;

public class Main {

    private final static String GRAPHS_DATASET = "files/graphs/graphsXXS.paed"; // Relative path inside /src folder
    private final static String TREES_DATASET = "files/trees/treeXS.paed";      // Relative path inside /src folder
    private final static String TABLES_DATASET = "files/tables/tablesS.paed";  // Relative path inside /src folder

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
                        bruixesOption = Menu.showWitchesMenu(TREES_DATASET);
                        BruixesMenuLogic.checkIfTreeCreated(TreeType.AVLTREE);  // Check if Tree is already created or not
                        switch (bruixesOption) {
                            case ADD_CITIZEN -> BruixesMenuLogic.showAddCitizen();          // Add a node to the tree
                            case REMOVE_CITIZEN -> BruixesMenuLogic.showRemoveCitizen();    // Remove a node from the tree
                            case VISUAL_REPRESENTATION -> BruixesMenuLogic.showTreeRepresentation();    // Visual tree representation
                            case WITCHES_IDENTIFICATION -> BruixesMenuLogic.showWitchIdentification();  //
                            case BATUDA -> BruixesMenuLogic.showBatuda();   // Search between a range
                        }
                    } while (bruixesOption != BruixesMenuOptions.PREVIOUS_MENU);
                }

                case OPTION_3 -> System.out.println(3);

                case TAULES -> { HeretgesMenuOptions heretgesMenuOptions;
                    do {
                        heretgesMenuOptions = Menu.showHereticsMenu(TABLES_DATASET);
                        HeretgesMenuLogic.checkIfTableCreated();

                        switch (heretgesMenuOptions) {
                            case ADD_ACCUSED -> HeretgesMenuLogic.addAccused();
                            case REMOVE_ACCUSED -> HeretgesMenuLogic.removeAccused();
                            case GRACE_EDICT -> HeretgesMenuLogic.edictOfGrace();
                            case ACCUSED_FINAL_TRIAL -> HeretgesMenuLogic.accusedFinalTrial();
                            case RABBITS_FINAL_TRIAL -> HeretgesMenuLogic.rabbitsFinalTrial();
                            case PROFESSIONS_HISTOGRAM -> HeretgesMenuLogic.professionsHistogram();
                        }

                    } while (heretgesMenuOptions != HeretgesMenuOptions.PREVIOUS_MENU);
                }

                case EXIT -> {
                    System.out.println(Menu.EXIT);
                    System.exit(0);
                }
            }

        } while (true);

    }

}