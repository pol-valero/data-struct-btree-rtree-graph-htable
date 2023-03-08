package Auxiliar.Algorithms;

import GraphsF1.Entities.PlaceOfInterest;

public class BinarySearch {

	// Method used to search a place by an ID.
	public static PlaceOfInterest binarySearch(PlaceOfInterest[] places, int nodeID) {
		int start = 0;
		int end = places.length - 1;

		while (start <= end) {
			int middle = (start + end) / 2;

			if (places[middle].getId() < nodeID) {
				start = middle + 1;
			}

			else if (places[middle].getId() > nodeID) {
				end = middle - 1;
			}

			else if (places[middle].getId() == nodeID) {
				return places[middle];
			}
		}

		return null;
	}
}
