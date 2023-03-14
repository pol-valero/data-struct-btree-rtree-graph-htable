package TreesF2.Entities;

public class Citizen {
	private final long id;	// Non-consecutive but unique ID.
	private final String name;	// Not unique and may contain +1 word.
	private final float weight;	// Weight in kilograms (kg).
	private final String kingdom;	// Not unique and may contain +1 word.

	public Citizen(long id, String name, float weight, String kingdom) {
		this.id = id;
		this.name = name;
		this.weight = weight;
		this.kingdom = kingdom;
	}

	public String getName() {
		return name;
	}

	public float getWeight() {
		return weight;
	}

	public boolean sameID(long otherID) {
		return this.id == otherID;
	}

	public void printInfo(boolean starInFront) {

		// In case of showing representation, do not print the star in front of the Citizen info.
		if (starInFront) {
			System.out.println("* " + name + " (" + id + ", Regne de " + kingdom + "): " + weight + "kg");
		}
		else {
			System.out.println(name + " (" + id + ", Regne de " + kingdom + "): " + weight + "kg");
		}
	}
}
