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

	public long getID() {
		return id;
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

	public void printInfo(boolean tabInFront, boolean starInFront) {

		// In case of having tabInFront, a \t will be printed before the Citizen's information.
		// In case of having starInFront, a star will be printed before the Citizen's information.

		if (tabInFront) {
			System.out.print("\t");
		}

		if (starInFront) {
			System.out.print("* ");
		}

		System.out.println(name + " (" + id + ", Regne de " + kingdom + "): " + weight + "kg");
	}
}
