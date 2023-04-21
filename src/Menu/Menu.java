package Menu;

import Menu.BinaryTreesBruixes.BruixesMenuOptions;
import Menu.GraphsOrenetes.OrenetesMenuOptions;
import BinaryTreesF2.Entities.ObjectType;
import Menu.RTreesBardissa.BardissaMenuOptions;

import java.util.Scanner;

public class Menu {

    public final static String separator = System.lineSeparator();
    public static String GRAPHS_DATASET;
    public static String BINARY_TREES_DATASET;
    public static String R_TREES_DATASET;

    public static final String MAIN_MENU = separator +
            "'`^\\ The Hashy Grail /^´'" + separator + separator +
            "1. Sobre orenetes i cocos (Grafs)" +separator +
            "2. Caça de bruixes (Arbres binaris de cerca)" + separator +
            "3. Tanques de bardissa (Arbres R)" + separator +
            "4. PER ESPECIFICAR" + separator + separator +
            "5. Exit" + separator;
    public static final String ORENETES_MENU =
            "A. Exploració del regne" + separator +
            "B. Detecció de trajectes habituals" + separator +
            "C. Missatgeria premium" + separator + separator +
            "D. Tornar enrere" + separator;

    public static final String BRUIXES_MENU =
            "A. Afegir habitant" + separator +
            "B. Eliminar habitant" + separator +
            "C. Representació visual" + separator +
            "D. Identificació de bruixes" + separator +
            "E. Batuda" + separator + separator +
            "F. Tornar enrere" + separator;

    public static final String BARDISSA_MENU =
            "A. Afegir bardissa" + separator +
            "B. Eliminar bardissa" + separator +
            "C. Visualització" + separator +
            "D. Cerca per àrea" + separator +
            "E. Optimització estètica" + separator + separator +
            "F. Tornar enrere" + separator;

    public static final String EXIT = "\u001B[31m "+separator+"Aturant The Hashy Grail..."+separator+" \u001B[0m";


    // Shows main menu of the program and asks to choose an option.
    public static MainMenuOptions showMainMenu() {
        do {
            System.out.println(separator + MAIN_MENU);
            int option = askForInteger("Esculli una opció: ", 1, 5);

            // Ejecutar la opción escogida.
            switch (option) {
                case 1 -> { return MainMenuOptions.ORENETES; }
                case 2 -> { return MainMenuOptions.BRUIXES; }
                case 3 -> { return MainMenuOptions.BARDISSA; }
                case 4 -> { return MainMenuOptions.OPTION_4; }
                case 5 -> { return MainMenuOptions.EXIT; }
            }
        } while (true);
    }

    // Sub-menu for the ORENETES option (selected previously in the main menu).
    public static OrenetesMenuOptions showOrenetesMenu(String graph_dataset) {
        GRAPHS_DATASET = graph_dataset;

        System.out.println(separator + ORENETES_MENU);
        String option = askForCharacter("Quina funcionalitat vol executar? ", 'A', 'D');

        switch (option) {
            case "A" -> { return OrenetesMenuOptions.KINGDOM_EXPLORATION; }
            case "B" -> { return OrenetesMenuOptions.COMMON_ROUTES; }
            case "C" -> { return OrenetesMenuOptions.PREMIUM_MESSAGING; }
            default -> { return OrenetesMenuOptions.PREVIOUS_MENU; }
        }
    }

    // Sub-menu for the WITCHES option (selected previously in the main menu).
    public static BruixesMenuOptions showWitchesMenu(String trees_dataset) {
        BINARY_TREES_DATASET = trees_dataset;

        System.out.println(separator + BRUIXES_MENU);
        String option = askForCharacter("Quina funcionalitat vol executar? ", 'A', 'F');

        switch (option) {
            case "A" -> { return BruixesMenuOptions.ADD_CITIZEN; }
            case "B" -> { return BruixesMenuOptions.REMOVE_CITIZEN; }
            case "C" -> { return BruixesMenuOptions.VISUAL_REPRESENTATION; }
            case "D" -> { return BruixesMenuOptions.WITCHES_IDENTIFICATION; }
            case "E" -> { return BruixesMenuOptions.BATUDA; }
            default -> { return BruixesMenuOptions.PREVIOUS_MENU; }
        }
    }

    // Sub-menu for the HEDGE option (selected previously in the main menu).
    public static BardissaMenuOptions showHedgeMenu(String RTreesDataset) {
        R_TREES_DATASET = RTreesDataset;

        System.out.println(separator + BARDISSA_MENU);
        String option = askForCharacter("Quina funcionalitat vol executar? ", 'A', 'F');

        switch (option) {
            case "A" -> { return BardissaMenuOptions.ADD_HEDGE; }
            case "B" -> { return BardissaMenuOptions.REMOVE_HEDGE; }
            case "C" -> { return BardissaMenuOptions.VISUAL_REPRESENTATION; }
            case "D" -> { return BardissaMenuOptions.AREA_SEARCH; }
            case "E" -> { return BardissaMenuOptions.AESTHETIC_OPTIMIZATION; }
            default -> { return BardissaMenuOptions.PREVIOUS_MENU; }
        }
    }

    // Asks for an option number, and checks if a wrong input is entered
    public static int askForInteger(String askMessage, int min, int max) {
        int option = min - 1;   // Asegura que la condición del bucle se siga cumpliendo.
        String errorMessage = "Error: Introdueix un número entre " + min + " i " + max + "." + separator;

        do {
            try {
                Scanner sc = new Scanner(System.in);
                System.out.print(askMessage);
                option = Integer.parseInt(sc.nextLine());

                if (option < min || option > max) {
                    System.out.println(errorMessage);
                }

            } catch (NumberFormatException e) {
                System.out.println(errorMessage);
            }

        } while (option < min || option > max);

        return option;
    }

    public static String askForCharacter(String askMessage, char firstChar, char lastChar) {
        String option; // Assegura que la condición del bucle se siga cumpliendo.
        String errorMessage = "Error: Introdueix un caràcter entre " + firstChar + " i " + lastChar + "." + separator;

        do {
            Scanner sc = new Scanner(System.in);
            System.out.print(askMessage);
            option = sc.nextLine().toUpperCase();

            if (option.length() != 1 || option.charAt(0) < firstChar || option.charAt(0) > lastChar) {
                System.out.println(errorMessage);
            }
        } while (option.length() != 1 || option.charAt(0) < firstChar || option.charAt(0) > lastChar);

        return option;
    }

    public static boolean askForBoolean(String askMessage) {
        String option = "";
        String errorMessage = "Error: Introdueix \"SI\" o \"NO\"." + separator;

        do {
            try {
                Scanner sc = new Scanner(System.in);
                System.out.print(askMessage);
                option = sc.nextLine();
                option = option.toUpperCase();

                if (!(option.equals("SI") || option.equals("NO"))) {
                    System.out.println(errorMessage);
                }

            } catch (NumberFormatException e) {
                System.out.println(errorMessage);
            }

        } while (!(option.equals("SI") || option.equals("NO")));

        return option.equals("SI");
    }

    public static String askForString (String askMessage) {
        String option = "";
        String errorMessage = "Error: Introdueix una cadena de caràcters." + separator;

        do {
            try {
                Scanner sc = new Scanner(System.in);
                System.out.print(askMessage);
                option = sc.nextLine();

                if (option.equals("")) {
                    System.out.println(errorMessage);
                }

            } catch (NumberFormatException e) {
                System.out.println(errorMessage);
            }

        } while (option.equals(""));

        return option;
    }


    public static float askForFloat (String askMessage, float min, float max) {
        float option = min - 1;   // Asegura que la condición del bucle se siga cumpliendo.
        String errorMessage = "Error: Introdueix un número real entre " + min + " i " + max + "." + separator;

        do {
            try {
                Scanner sc = new Scanner(System.in);
                System.out.print(askMessage);
                option = Float.parseFloat(sc.nextLine());

                if (option < min || option > max) {
                    System.out.println(errorMessage);
                }

            } catch (NumberFormatException e) {
                System.out.println(errorMessage);
            }

        } while (option < min || option > max);

        return option;
    }

    public static ObjectType askforObject(String s) {
        while(true){
            String objectType = askForString(s);
            if (objectType.equalsIgnoreCase("WOOD")) {
                return ObjectType.WOOD;
            } else if (objectType.equalsIgnoreCase("DUCK")) {
                return ObjectType.DUCK;
            } else if (objectType.equalsIgnoreCase("STONE")) {
                return ObjectType.STONE;
            }
            System.out.println("Error: Introdueix \"WOOD\", \"DUCK\" o \"STONE\".\n");
        }
    }
}
