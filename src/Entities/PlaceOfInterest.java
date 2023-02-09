package Entities;

public class PlaceOfInterest {
	private final int id;
	private final String name;
	private final String kingdom;
	private final Climate climate;

	public PlaceOfInterest(int id, String name, String kingdom, Climate climate) {
		this.id = id;
		this.name = name;
		this.kingdom = kingdom;
		this.climate = climate;
	}
}
