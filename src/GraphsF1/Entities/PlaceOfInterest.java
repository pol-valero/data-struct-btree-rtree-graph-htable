package GraphsF1.Entities;

public class PlaceOfInterest {
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

	public int getId() {
		return id;
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
}
