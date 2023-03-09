package Parsers;


import TreesF2.Entities.Citizen;
import com.sun.source.tree.Tree;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class DatasetLoaderF2 {

    private static final String parseSeparator = ";";

    private static Citizen csvLineToCitizen(String csvLine) {
        String[] field = csvLine.split(parseSeparator);

        return new Citizen(Long.parseLong(field[0]), field[1], Float.parseFloat(field[2]), field[3]);
    }

    public static void loadCitizens(String datasetName, Tree tree) {

        Citizen citizen;
        int citizenLinesNum;

        Path path = Path.of("files/trees/" + datasetName);

        try {

            List<String> csvLines = Files.readAllLines(path);

            citizenLinesNum = Integer.parseInt(csvLines.get(0));

            for (int i = 1; i <= citizenLinesNum; i++) {
                citizen = (csvLineToCitizen(csvLines.get(i)));
                //System.out.println("Id: " + citizen.getId() + "  Name: " + citizen.getName() + "  Weight: " + citizen.getWeight() + "  Kingdom: " + citizen.getKingdom());
                //tree.add(citizen);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
