package Menu.TaulesHeretges;

import Auxiliar.MyArrayList;
import HashMapsF4.Entities.Profession;
import HashMapsF4.Entities.Synonym;
import HashMapsF4.Exceptions.AccusedNotFoundException;
import Menu.Menu;
import HashMapsF4.Algorithms.Table;
import HashMapsF4.Entities.Accused;
import Parsers.DatasetLoaderF4;

public class HeretgesMenuLogic {

	private static Table<String, Accused> table;

	private static long initialTime;
	private static long executionTime;
	private static final int NUM_PROFESSIONS = 8;

	public static void finished() {
		executionTime = System.nanoTime() - initialTime;
	}

	public static void checkIfTableCreated() {

		// Create the table
		if (table == null) {
			table = new Table<>();
		}

		DatasetLoaderF4.loadAccused(Menu.TABLES_DATASET, table);
	}

	public static void addAccused() {
		System.out.println();
		String name = Menu.askForString("Nom de l'acusat: ");
		long rabbits = Menu.askForLong("Nombre de conills vistos: ", 0, Long.MAX_VALUE);
		String profession = Menu.askForString("Professió: ");

		Accused accused = new Accused(name, rabbits, profession);

		// Add the accused
		table.add(accused.getName(), accused);
		System.out.println(System.lineSeparator() + "S'ha enregistrat un nou possible heretge.");
	}

	public static void removeAccused() {
		System.out.println();
		String accusedName = Menu.askForString("Nom de l'acusat: ");

		// Add the accused
		if (table.remove(accusedName)) {
			System.out.println(System.lineSeparator() + "L'execució pública de " + accusedName + " ha estat un èxit.");
		} else {
			System.out.println("No s'ha pogut trobar.");
		}

	}

	public static void edictOfGrace() {
		System.out.println();
		String accusedName = Menu.askForString("Nom de l'acusat: ");
		String heretic = Menu.askForString("Marcar com a heretge (Y/N)? ");

		Accused accused;
		try {
			accused = table.get(accusedName);

			System.out.println();
			if (heretic.equals("Y")) {
				if (accused.sameProfession(Profession.KING) || accused.sameProfession(Profession.QUEEN) || accused.sameProfession(Profession.CLERGYMAN)) {
					System.out.println("La Inquisició Espanyola no permet que " + accusedName + " sigui un heretge.");
				}
				else {
					System.out.println("La Inquisició Espanyola ha conclòs que " + accusedName + " és un heretge.");
					accused.setHeretic(true);
				}
			}
			else {
				System.out.println("La Inquisició Espanyola ha conclòs que " + accusedName + " no és un heretge.");
			}
		} catch (AccusedNotFoundException e) {
			System.out.println(e.getMessage());
		}

	}

	public static void accusedFinalTrial() {
		System.out.println();
		String accusedName = Menu.askForString("Nom de l'acusat: ");

		Accused accused;
		try {
			accused = table.get(accusedName);
			accused.printInfo(0);
		} catch (AccusedNotFoundException e) {
			System.out.println(e.getMessage());
		}
	}

	public static void rabbitsFinalTrial() {
		System.out.println();
		long minRabbits = Menu.askForLong("Nombre mínim de conills: ", 0, Long.MAX_VALUE);
		long maxRabbits = Menu.askForLong("Nombre màxim de conills: ", minRabbits, Long.MAX_VALUE);
		System.out.println();
		boolean firstTime = true;

		for (int i = 0; i < table.objects.length; i++) {
			MyArrayList<Accused> accusedList = table.objects[i].synonym;

			for (int j = 0; j < accusedList.size(); j++) {
				Accused accused = accusedList.get(j);

				// Only print the accused if it has seen the rabbits.
				if (accused.rabbitsSeen(minRabbits, maxRabbits)) {
					if (firstTime) {
						System.out.println("S'han trobat els següents acusats:");
						firstTime = false;
					}
					accused.printInfo(1);
					System.out.println();
				}
			}
		}

		if(firstTime) {
			System.out.println("No s'ha trobat cap acusat dins del rang de valors: ["+minRabbits+","+maxRabbits+"]");
		}
	}

	public static void professionsHistogram() {
		System.out.println();

		int[][] professions = new int[NUM_PROFESSIONS][2];	// [0] = heretges, [1] = total.

		// Loop through all the table.
		for (int i = 0; i < table.objects.length; i++) {
			MyArrayList<Accused> accusedList = table.objects[i].synonym;

			for (Accused accused : accusedList) {
				boolean heretic = accused.isHeretic();

				switch (accused.getProfession()) {
					case KING -> fillProfessions(professions, 0, heretic);
					case QUEEN -> fillProfessions(professions, 1, heretic);
					case KNIGHT -> fillProfessions(professions, 2, heretic);
					case PEASANT -> fillProfessions(professions, 3, heretic);
					case MINSTREL -> fillProfessions(professions, 4, heretic);
					case SHRUBBER -> fillProfessions(professions, 5, heretic);
					case CLERGYMAN -> fillProfessions(professions, 6, heretic);
					case ENCHANTER -> fillProfessions(professions, 7, heretic);
				}
			}
		}

		showVisualRepresentation();
	}

	private static void showVisualRepresentation() {
		// Make a table with the professions.
		//
	}

	private static void fillProfessions(int[][] professions, int profession, boolean heretic) {
		if (heretic) {
			professions[profession][0]++;
		}

		professions[profession][1]++;
	}
}
