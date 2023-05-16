package HashMapsF4.Entities;

public class Accused {
	private final String name;
	private final long seenRabbits;
	final Profession profession;
	private boolean heretic;

	public Accused(String name, long seenRabbits, String profession) {
		this.name = name;
		this.seenRabbits = seenRabbits;
		this.profession = Profession.stringToEnum(profession);
		heretic = false;
	}

	public String getName() {
		return name;
	}

	public long getSeenRabbits() {
		return seenRabbits;
	}

	public Profession getProfession() {
		return profession;
	}

	public boolean sameProfession(Profession otherProfession) {
		return profession == otherProfession;
	}

	public boolean isHeretic() {
		return heretic;
	}

	public void setHeretic(boolean heretic) {
		this.heretic = heretic;
	}

	public void printInfo(int numTabs) {

		appendTabs(numTabs);
		System.out.println(name);
		appendTabs(numTabs);
		System.out.println("\t* Nombre de conills vistos: " + seenRabbits);
		appendTabs(numTabs);
		System.out.println("\t* Professió: " + profession);
		appendTabs(numTabs);
		System.out.print("\t* Heretge? ");

		if (heretic) {
			System.out.println("Sí");
		}
		else {
			System.out.println("No");
		}
	}

	private void appendTabs(int numTabs) {
		for (int i = 1; i <= numTabs; i++) {
			System.out.print("\t");
		}
	}

	public boolean rabbitsSeen(long minRabbits, long maxRabbits) {
		return (minRabbits <= seenRabbits && seenRabbits <= maxRabbits);
	}

	public boolean sameName(String otherName) {
		return name.equals(otherName);
	}
}
