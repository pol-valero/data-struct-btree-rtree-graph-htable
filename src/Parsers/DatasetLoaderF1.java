package Parsers;

import GraphsF1.Entities.Climate;
import GraphsF1.Entities.KnownRoute;
import GraphsF1.Entities.PlaceOfInterest;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class DatasetLoaderF1 {

    private static final String parseSeparator = ";";

    private static PlaceOfInterest csvLineToPlace(String csvLine) {
        String[] field = csvLine.split(parseSeparator);

        return new PlaceOfInterest(Integer.parseInt(field[0]), field[1], field[2], Climate.stringToEnum(field[3]));
    }

    private static KnownRoute csvLineToRoute(String csvLine) {
        String[] field = csvLine.split(parseSeparator);

        return new KnownRoute(Integer.parseInt(field[0]), Integer.parseInt(field[1]), Float.parseFloat(field[2]), Float.parseFloat(field[3]), Float.parseFloat(field[4]));
    }

    public static PlaceOfInterest[] loadPlaces(String pathName) {

        int placesLinesNum;
        int j = 0;

        Path path = Path.of(pathName);
        PlaceOfInterest[] places = null;

        try {
            List<String> csvLines = Files.readAllLines(path);

            placesLinesNum = Integer.parseInt(csvLines.get(0));
            places = new PlaceOfInterest[placesLinesNum];

            for (int i = 1; i <= placesLinesNum; i++) {
                    places[j] = (csvLineToPlace(csvLines.get(i)));
                    j++;
            }

            return places;
        } catch (IOException e) {
            return places;
        }
    }

    public static KnownRoute[] loadRoutes(String pathName) {

        int placesLinesNum;
        int routesLinesNum;
        int j = 0;


        Path path = Path.of(pathName);
        KnownRoute[] routes = null;

        try {
            List<String> csvLines = Files.readAllLines(path);

            placesLinesNum = Integer.parseInt(csvLines.get(0));
            routesLinesNum = Integer.parseInt(csvLines.get(placesLinesNum + 1));
            routes = new KnownRoute[routesLinesNum];

            for (int i = placesLinesNum + 2; i <= routesLinesNum + placesLinesNum + 1; i++) {
                routes[j] = csvLineToRoute(csvLines.get(i));
                j++;
            }

            return routes;
        } catch (IOException e) {
            return routes;
        }
    }

}
