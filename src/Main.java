import java.util.ArrayList;
import java.util.LinkedList;


public class Main {

    public static void main(String[] args) {

        int option;

        do {
            Menu.showMenu();
            option = Menu.askForInteger("Please choose an option: ", 1, 5);
            Menu.runSelectedMenu(option);
        } while (option != 5);

    }

}