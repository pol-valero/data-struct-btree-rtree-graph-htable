import Menu.Menu;
import Menu.OrenetesMenuLogic;

public class Main {

    private final static String GRAPHS_DATASET = "graphsXXS.paed";
    private final static String TREES_DATASET = "treeXXS.paed";

    public static void main(String[] args) {

        do {
            switch (Menu.showMainMenu()) {
                case ORENETES -> {  // Graphs (phase 1)
                    switch (Menu.showOrenetesMenu(GRAPHS_DATASET)) {
                        case KINGDOM_EXPLORATION -> OrenetesMenuLogic.showKingdomExploration(); // DFS + BFS
                        case COMMON_ROUTES -> OrenetesMenuLogic.showFrequentRoutesDetection();  // MST Prim
                        case PREMIUM_MESSAGING -> OrenetesMenuLogic.showPremiumMessaging();     // Djikstra
                    }
                }
                case BRUIXES -> {
                    switch (Menu.showWitchesMenu(TREES_DATASET)) {
                        case ADD_CITIZEN -> OrenetesMenuLogic.showKingdomExploration(); // DFS + BFS
                        case REMOVE_CITIZEN -> OrenetesMenuLogic.showFrequentRoutesDetection();  // MST Prim
                        case VISUAL_REPRESENTATION -> OrenetesMenuLogic.showPremiumMessaging();     // Djikstra
                        case WITCHES_IDENTIFICATION -> OrenetesMenuLogic.showPremiumMessaging();     // Djikstra
                        case FIGHT -> OrenetesMenuLogic.showPremiumMessaging();     // Djikstra
                    }
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