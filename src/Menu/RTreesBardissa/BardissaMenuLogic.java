package Menu.RTreesBardissa;

import Auxiliar.MyArrayList;
import Menu.Menu;
import Parsers.DatasetLoaderF3;
import RTreesF3.Entities.*;

import javax.swing.*;

public class BardissaMenuLogic {

    private static RTree rTree;

    public static void showAddHedge() {

        boolean error;

        String type;
        do {
            type = Menu.askForString("\nTipus de la bardissa: ");

            if (type.equals("CIRCLE") || type.equals("SQUARE")) {
                error = false;
            } else {
                System.out.println("\nNomés pot ser CIRCLE o SQUARE");
                error = true;
            }

        } while (error);

        float size = Menu.askForFloat("Mida de la bardissa: ", 0, Float.MAX_VALUE);
        double latitude = Menu.askForDouble("Latitud de la bardissa: ", -Double.MAX_VALUE, Double.MAX_VALUE);
        double longitude = Menu.askForDouble("Longitud de la bardissa: ", -Double.MAX_VALUE, Double.MAX_VALUE);
        String color = Menu.askForString("Color de la bardissa: ");

        rTree.addHedge(new Hedge(type, size, latitude, longitude, color));

        System.out.println("\nUna nova bardissa aparegué a la Bretanya.");

    }

    public static void showDeleteHedge() {

        double latitude = Menu.askForDouble("\nLatitud de la bardissa: ", -Double.MAX_VALUE, Double.MAX_VALUE);
        double longitude = Menu.askForDouble("Longitud de la bardissa: ", -Double.MAX_VALUE, Double.MAX_VALUE);

        boolean deleted;

        deleted = rTree.removeHedge(new Point (longitude, latitude));

        if (deleted) {
            System.out.println("\nLa bardissa s'ha eliminat, per ser integrada a una tanca.");
        } else {
            System.out.println("\nEn aquest punt no hi ha cap bardissa.");
        }

    }

    public static void showAreaSearch() {
        String firstPoint = Menu.askForString("\nEntra el primer punt de l’àrea (lat,long): ");
        String secondPoint = Menu.askForString("Entra el segon punt de l’àrea (lat,long): ");

        Point p1 = new Point(Double.parseDouble(firstPoint.split(",")[1]), Double.parseDouble(firstPoint.split(",")[0]));
        Point p2 = new Point(Double.parseDouble(secondPoint.split(",")[1]), Double.parseDouble(secondPoint.split(",")[0]));

        //We calculate the topRightCorner and bottomLeftCorner of the search area
        Point minPoint = Point.findBottomLeftPoint(p1, p2);
        Point maxPoint = Point.findTopRightPoint(p1, p2);

        MyArrayList<Hedge> hedgesFound = rTree.areaSearch(minPoint, maxPoint);

        System.out.println("\nS'han trobat " + hedgesFound.size() + " bardisses en aquesta àrea\n");

        for (Hedge hedge: hedgesFound) {

            System.out.print("\t* " + hedge.getPoint().y + ", " + hedge.getPoint().x + ": " + hedge.getType());

            if (hedge.getType().equals("CIRCLE")) {
                System.out.println(" (r=" + hedge.getSize() + "m) " + hedge.getColor());
            } else {
                System.out.println(" (s=" + hedge.getSize() + "m) " + hedge.getColor());
            }

        }

        System.out.println();

    }

    public static void showKNN() {
        String firstPoint = Menu.askForString("\nEntra el punt a consultar (lat,long): ");
        int k = Menu.askForInteger("Entra la quantitat de bardisses a considerar (K): ",0 , DatasetLoaderF3.getHedgeLinesNum());

        Point p1 = new Point(Double.parseDouble(firstPoint.split(",")[1]), Double.parseDouble(firstPoint.split(",")[0]));

        MyArrayList<Hedge> hedgesFound = rTree.KNN(p1, k);

        int circle = 0;
        int square = 0;
        String color = hedgesFound.get(0).getColor();

        for (Hedge hedge:hedgesFound) {
            color = calculateColorAverage(color, hedge.getColor());
            if (hedge.getType().equals("CIRCLE")){
                circle++;
            }else {
                square++;
            }
        }
        // ANSI escape sequence to set the color
        String colorEscape = "\u001B[38;2;" +
                Integer.parseInt(color.substring(1, 3), 16) + ";" +
                Integer.parseInt(color.substring(3, 5), 16) + ";" +
                Integer.parseInt(color.substring(5, 7), 16) + "m";

        // ANSI escape sequence to reset the color
        String resetEscape = "\u001B[0m";

        if (circle > square){
            System.out.println("\nTipus majoritari: CIRCLE");
        }else if(circle < square){
            System.out.println("\nTipus majoritari: SQUARE");
        }else{
            System.out.println("\nTipus majoritari: SAME");
        }
        System.out.println("Color mitjà: " + colorEscape + color + resetEscape + "\n");

    }

    /**
     Extret de
     */
    private static String calculateColorAverage(String color1, String color2) {
        // Obtener los valores decimales de los componentes de color
        int red1 = Integer.parseInt(color1.substring(1, 3), 16);
        int green1 = Integer.parseInt(color1.substring(3, 5), 16);
        int blue1 = Integer.parseInt(color1.substring(5, 7), 16);

        int red2 = Integer.parseInt(color2.substring(1, 3), 16);
        int green2 = Integer.parseInt(color2.substring(3, 5), 16);
        int blue2 = Integer.parseInt(color2.substring(5, 7), 16);

        // Calcular la media de cada componente de color
        int averageRed = (red1 + red2) / 2;
        int averageGreen = (green1 + green2) / 2;
        int averageBlue = (blue1 + blue2) / 2;

        // Convertir los valores decimales a hexadecimal
        String hexRed = Integer.toHexString(averageRed);
        String hexGreen = Integer.toHexString(averageGreen);
        String hexBlue = Integer.toHexString(averageBlue);

        // Asegurarse de que cada componente hexadecimal tenga 2 dígitos
        hexRed = padZeroes(hexRed);
        hexGreen = padZeroes(hexGreen);
        hexBlue = padZeroes(hexBlue);

        // Concatenar los componentes de color para formar el nuevo color
        String averageColor = "#" + hexRed + hexGreen + hexBlue;
        return averageColor;
    }

    // Método auxiliar para asegurarse de que un componente hexadecimal tenga 2 dígitos
    private static String padZeroes(String hex) {
        if (hex.length() < 2) {
            hex = "0" + hex;
        }
        return hex;
    }

    // We only create the tree if it was not already created before
    public static void checkIfTreeCreated() {

        // If tree is not created yet, instantiate one.
        if (rTree == null) {

            rTree = new RTree();

            DatasetLoaderF3.loadHedges(Menu.R_TREES_DATASET, rTree);
        }
        // System.out.println(rTree);
    }
    public static void visualRepresentation() {
        rTree.showVisualRepresentation();
    }

}
