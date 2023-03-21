package GraphsF1.Entities;

public class PlaceOfInterest implements Comparable<PlaceOfInterest> {
	private final int id;
	private final String name;
	private final String kingdom;
	private final Climate climate;
	private int rowIndex;
	private boolean visited;

	public PlaceOfInterest(int id, String name, String kingdom, Climate climate) {
		this.id = id;
		this.name = name;
		this.kingdom = kingdom;
		this.climate = climate;
		visited = false;
	}

	// Constructor used to instantiate a comparable PlaceOfInterest instance for the generic classes (BinarySearch and MergeSort).
	public PlaceOfInterest(int id) {
		this.id = id;
		this.kingdom = null;
		this.climate = null;
		this.name = null;
	}

	public String getName() {
		return name;
	}

	public String getKingdom() {
		return kingdom;
	}

	public int getRowIndex() {
		return rowIndex;
	}

	public boolean isVisited() {
		return visited;
	}

	public void justVisited() {
		visited = true;
	}

	public boolean sameKingdom(PlaceOfInterest otherPlace) {
		return kingdom.equals(otherPlace.getKingdom());
	}

	public boolean samePlace(PlaceOfInterest otherPlace) {
		return (this.id == otherPlace.id);
	}

	public String showInformation() {
		return (id + " - " + name + ", " + "Regne de " + kingdom + " (" + climate.enumToString() + ")");
	}
	public void notVisited(){
		visited = false;
	}

	public void setRowIndex(int rowIndex) {
		this.rowIndex = rowIndex;
	}

	// Method that allows the comparison of two places of interest based on their IDs (which are unique). This can be used in generic classes.
	@Override
	public int compareTo(PlaceOfInterest otherPlace) {
		// Returns a negative integer, zero, or a positive integer depending on whether the ID of this place is less than, equal to or greater than the ID of the otherPlace.
		return Integer.compare(this.id, otherPlace.id);
	}
}
