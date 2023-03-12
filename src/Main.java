import Menu.Bruixes.BruixesMenuOptions;
import Menu.Menu;
import Menu.Orenetes.OrenetesMenuLogic;
import Menu.Bruixes.BruixesMenuLogic;
import Menu.Orenetes.OrenetesMenuOptions;

public class Main {

    private final static String GRAPHS_DATASET = "files/graphs/graphsXXL.paed"; // Relative path inside /src folder
    private final static String TREES_DATASET = "files/trees/treeXS.paed";     // Relative path inside /src folder

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
                        switch (bruixesOption) {
                            case ADD_CITIZEN -> BruixesMenuLogic.showAddCitizen();
                            case REMOVE_CITIZEN -> BruixesMenuLogic.showRemoveCitizen();
                            case VISUAL_REPRESENTATION -> BruixesMenuLogic.showTreeRepresentation();
                            case WITCHES_IDENTIFICATION -> BruixesMenuLogic.showWitchIdentification();
                            case BATUDA -> BruixesMenuLogic.showBatuda();
                        }
                    } while (bruixesOption != BruixesMenuOptions.PREVIOUS_MENU);
                }

                case OPTION_3 -> System.out.println(3);
                case OPTION_4 -> System.out.println(4);
                case EXIT -> {
                    System.out.println(Menu.EXIT);
                    System.exit(0);
                }
            }

        } while (true);

    }

}