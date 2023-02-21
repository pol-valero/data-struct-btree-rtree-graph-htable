import Menu.Menu;

public class Main {
    public static void main(String[] args) {

        do {
            switch (Menu.showMainMenu()) {
                case ORENETES -> Menu.showOrenetesMenu();
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