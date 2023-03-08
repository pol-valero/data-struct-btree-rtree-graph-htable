package TreesF2.Entities;

public class Object {
	private final String name;	// Name of the object.
	private final float weight;	// Weight in kilograms (kg).
	private final ObjectType type;	// Type of the object (DUCK, WOOD or STONE).

	public Object(String name, float weight, ObjectType type) {
		this.name = name;
		this.weight = weight;
		this.type = type;
	}
}
