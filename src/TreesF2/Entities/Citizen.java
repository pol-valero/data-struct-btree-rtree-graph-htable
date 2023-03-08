package TreesF2.Entities;

public class Citizen {
	private final int ID;	// Non-consecutive but unique ID.
	private final String name;	// Not unique and may contain +1 word.
	private final float weight;	// Weight in kilograms (kg).
	private final String kingdom;	// Not unique and may contain +1 word.

	public Citizen(int ID, String name, float weight, String kingdom) {
		this.ID = ID;
		this.name = name;
		this.weight = weight;
		this.kingdom = kingdom;
	}
}
