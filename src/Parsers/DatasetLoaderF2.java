package Parsers;

import BinaryTreesF2.Entities.BinaryTree;
import BinaryTreesF2.Entities.Citizen;

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

    public static void loadCitizens(String pathName, BinaryTree binaryTree) {

        Citizen citizen;
        int citizenLinesNum;

        Path path = Path.of(pathName);

        try {

            List<String> csvLines = Files.readAllLines(path);

            citizenLinesNum = Integer.parseInt(csvLines.get(0));

            for (int i = 1; i <= citizenLinesNum; i++) {
                citizen = (csvLineToCitizen(csvLines.get(i)));
                //System.out.println("Id: " + citizen.getId() + "  Name: " + citizen.getName() + "  Weight: " + citizen.getWeight() + "  Kingdom: " + citizen.getKingdom());

                binaryTree.addCitizen(citizen);   //As we are reading the dataset, we create the binaryTree structure
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
