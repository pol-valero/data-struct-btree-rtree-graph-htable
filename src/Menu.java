
import java.lang.reflect.Field;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.ArrayList;

public class Menu {

    private static long initialTime;
    private static long executionTime;

    public static void finished() {
        executionTime = System.nanoTime() - initialTime;
    }

    private static void graphMenu() {
        int option;


        //Change options as needed. More approaches can be added
        do {
            showGraphMenu();
            option = askForInteger("Please choose an option: ", 1, 3);
            switch (option) {

                case 1:
                    break;

                case 2:
                    break;

            }

        } while (option != 3);

    }

    public static void runSelectedMenu(int option) {

        switch (option) {
            case 1:
                graphMenu();
                break;

            case 2:
                break;

            case 3:
                break;

            case 4:
                break;

            case 5:
                System.out.print("\u001B[31m");
                System.out.print("\nAturant The Hashy Grail...\n");
                System.out.print("\u001B[0m");
                break;
        }
    }

    //Asks for an option number, and checks if a wrong input is entered
    static int askForInteger(String message, int min, int max) {

        Scanner s = new Scanner(System.in);

        int option = min - 1;

        do {
            System.out.print(message);
            try {
                option = s.nextInt();
                if (option < min || option > max) {
                    System.out.printf("\nPlease enter a number between %d and %d.%n\n", min, max);
                }
            } catch (InputMismatchException e) {
                System.out.println("\nPlease enter a number.\n");
            } finally {
                s.nextLine();
            }
        } while (option < min || option > max);

        return option;
    }


    public static void showMenu() {

        System.out.println(
                "\n'`^\\ The Hashy Grail /^´'\n\n" +
                "1. Sobre orenetes i cocos (Grafs)\n" +
                "2. PER ESPECIFICAR\n" +
                "3. PER ESPECIFICAR\n" +
                "4. PER ESPECIFICAR\n\n" +
                "5. Exit");
    }
    public static void showGraphMenu() {

        System.out.println(
                "\nA. Exploració del regne\n" +
                "B. Detecció de trajectes habituals\n" +
                "C. Missatgeria premium\n\n" +
                "D. Tornar enrere\n");
    }


    private static void printSortedObjectList(ArrayList<?> objects, Boolean ascendingOrder) {
        int i;

        if (ascendingOrder) {
            for (i = 0; i < objects.size(); i++) {
                printObjectFields(objects, i);
            }
        } else {
            for (i = objects.size() - 1; i >= 0; i--) {
                printObjectFields(objects, i);
            }
        }
    }

    private static void printObjectFields(ArrayList<?> objects, int i) {
        System.out.print("\n\n");
        for (Field field : objects.get(i).getClass().getFields()) {
            String name = field.getName();
            Object value;
            try {
                value = field.get(objects.get(i));
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
            if (!name.equals("abilities")) {
                System.out.printf("%s: %s\n", name, value);
            } else {
                System.out.printf("%s: ", name);
                int[] fieldArrayValues = (int[]) value;
                System.out.printf(" Windsurf(%d/10)", fieldArrayValues[0]);
                System.out.printf("  Optimist(%d/10)", fieldArrayValues[1]);
                System.out.printf("  Laser(%d/10)", fieldArrayValues[2]);
                System.out.printf("  PatíCatalà(%d/10)", fieldArrayValues[3]);
                System.out.printf("  HobieDragoon(%d/10)", fieldArrayValues[4]);
                System.out.printf("  HobieCat(%d/10)\n", fieldArrayValues[5]);
            }
        }
    }

}
