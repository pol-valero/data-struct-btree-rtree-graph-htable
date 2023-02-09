package Entities;

public class KnownRoute {
	private final int placeA;
	private final int placeB;
	private final int timeE;
	private final int timeA;
	private final int distance;

	public KnownRoute(int placeA, int placeB, int timeE, int timeA, int distance) {
		this.placeA = placeA;
		this.placeB = placeB;
		this.timeE = timeE;
		this.timeA = timeA;
		this.distance = distance;
	}
}
