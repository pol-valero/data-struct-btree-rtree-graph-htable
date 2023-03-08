import Menu.Menu;
import Menu.OrenetesMenuLogic;

public class Main {

    private final static String GRAPHS_DATASET = "graphsXXS.paed";

    public static void main(String[] args) {

        do {
            switch (Menu.showMainMenu()) {
                case ORENETES -> {
                    switch (Menu.showOrenetesMenu(GRAPHS_DATASET)) {
                        case KINGDOM_EXPLORATION -> OrenetesMenuLogic.showKingdomExploration();
                        case COMMON_ROUTES -> OrenetesMenuLogic.showFrequentRoutesDetection();
                        case PREMIUM_MESSAGING -> OrenetesMenuLogic.showPremiumMessaging();
                    }
                }
                case OPTION_2 -> System.out.println(2);
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