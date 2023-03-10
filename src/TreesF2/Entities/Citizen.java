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

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public float getWeight() {
		return weight;
	}

	public String getKingdom() {
		return kingdom;
	}

	public boolean sameID(long otherID) {
		return this.id == otherID;
	}
}
