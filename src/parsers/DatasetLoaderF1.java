package parsers;

import Entities.Climate;
import Entities.KnownRoute;
import Entities.PlaceOfInterest;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class DatasetLoaderF1 {

    private static String separator = ";";

    private static PlaceOfInterest csvLineToPlace(String csvLine) {
        String[] field = csvLine.split(separator);

        Climate climate = null;

        switch (field[3]) {
            case "TROPICAL":
                 climate = Climate.TROPICAL;
            break;
            case "CONTINENTAL":
                 climate = Climate.CONTINENTAL;
            break;
            case "POLAR":
                 climate = Climate.POLAR;
            break;
        }

        PlaceOfInterest place = new PlaceOfInterest(Integer.parseInt(field[0]), field[1], field[2], climate);

        return place;
    }

    private static KnownRoute csvLineToRoute(String csvLine) {
        String[] field = csvLine.split(separator);

        KnownRoute route = new KnownRoute(Integer.parseInt(field[0]), Integer.parseInt(field[1]), Float.parseFloat(field[2]), Float.parseFloat(field[3]), Float.parseFloat(field[4]));

        return route;
    }

    public static ArrayList<PlaceOfInterest> loadPlaces(String datasetName) {

        int placesLinesNum;

        Path path = Path.of("files/" + datasetName);
        ArrayList<PlaceOfInterest> places = new ArrayList<>();

        try {
            List<String> csvLines = Files.readAllLines(path);

            placesLinesNum = Integer.parseInt(csvLines.get(0));

            for (int i = 1; i <= placesLinesNum; i++) {
                    places.add(csvLineToPlace(csvLines.get(i)));
            }

            return places;
        } catch (IOException e) {
            return places;
        }
    }

    public static ArrayList<KnownRoute> loadRoutes(String datasetName) {

        int placesLinesNum;
        int routesLinesNum;

        KnownRoute prova;

        Path path = Path.of("files/" + datasetName);
        ArrayList<KnownRoute> routes = new ArrayList<>();

        try {
            List<String> csvLines = Files.readAllLines(path);

            placesLinesNum = Integer.parseInt(csvLines.get(0));
            routesLinesNum = Integer.parseInt(csvLines.get(placesLinesNum + 1));

            for (int i = placesLinesNum + 2; i <= routesLinesNum + placesLinesNum + 1; i++) {
                routes.add(csvLineToRoute(csvLines.get(i)));


            }
            return routes;
        } catch (IOException e) {
            return routes;
        }
    }

}
