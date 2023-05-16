package Parsers;

import HashMapsF4.Algorithms.Table;
import HashMapsF4.Entities.Accused;
import HashMapsF4.Entities.Profession;
import Menu.TaulesHeretges.HeretgesMenuLogic;
import TreesF2.Entities.Citizen;
import TreesF2.Entities.Tree;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class DatasetLoaderF4 {

    private static final String parseSeparator = ";";

    private static Accused csvLineToAccused(String csvLine) {
        String[] field = csvLine.split(parseSeparator);

        return new Accused(field[0], Long.parseLong(field[1]), field[2]);
    }

    public static void loadAccused(String pathName, Table<String, Accused> table) {

        Accused accused;
        int accusedLinesNum;
        Path path = Path.of(pathName);

        try {
            List<String> csvLines = Files.readAllLines(path);
            accusedLinesNum = Integer.parseInt(csvLines.get(0));

            for (int i = 1; i <= accusedLinesNum; i++) {
                accused = (csvLineToAccused(csvLines.get(i)));

                table.add(accused.getName(), accused);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
