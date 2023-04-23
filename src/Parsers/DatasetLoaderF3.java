package Parsers;

import RTreesF3.Entities.Hedge;
import RTreesF3.Entities.RTree;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class DatasetLoaderF3 {

    private static final String parseSeparator = ";";

    private static Hedge csvLineToHedge(String csvLine) {
        String[] field = csvLine.split(parseSeparator);

        return new Hedge(field[0], Float.parseFloat(field[1]), Double.parseDouble(field[2]), Double.parseDouble(field[3]), field[4]);
    }

    public static void loadHedges(String pathName, RTree rTree) {

        Hedge hedge;
        int hedgeLinesNum;

        Path path = Path.of(pathName);

        try {

            List<String> csvLines = Files.readAllLines(path);

            hedgeLinesNum = Integer.parseInt(csvLines.get(0));

            for (int i = 1; i <= hedgeLinesNum; i++) {
                hedge = (csvLineToHedge(csvLines.get(i)));

                //rTree.addHedge();   //As we are reading the dataset, we create the RTree structure
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
