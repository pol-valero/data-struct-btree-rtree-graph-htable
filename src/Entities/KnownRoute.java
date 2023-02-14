package Entities;

public class KnownRoute {
	private final int placeA;
	private final int placeB;
	private final float timeE;
	private final float timeA;
	private final float distance;

	public KnownRoute(int placeA, int placeB, float timeE, float timeA, float distance) {
		this.placeA = placeA;
		this.placeB = placeB;
		this.timeE = timeE;
		this.timeA = timeA;
		this.distance = distance;
	}

	public int getPlaceA() {
		return placeA;
	}

	public int getPlaceB() {
		return placeB;
	}

	public float getTimeE() {
		return timeE;
	}

	public float getTimeA() {
		return timeA;
	}

	public float getDistance() {
		return distance;
	}
}
