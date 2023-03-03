package Entities;

import Parsers.DatasetLoaderF1;

import java.util.ArrayList;

public class Graph {
	private KnownRoute[][] matrix;
	private int placesNum;	// Number of different places in the graph
	private int routesNum;	// Number of edges between all the nodes
	private PlaceOfInterest[] places;
	private KnownRoute[] routes;

	public Graph(String datasetName) {
		places = DatasetLoaderF1.loadPlaces(datasetName);
		routes = DatasetLoaderF1.loadRoutes(datasetName);

		placesNum = places.length;
		routesNum = routes.length;

		matrix = new KnownRoute[placesNum][placesNum];
		createGraph();
	}

	private void createGraph() {
		for (int i = 0; i < placesNum; i++) {
			for (int j = 0; j < placesNum; j++) {
				matrix[i][j] = findRoute(i, j);
			}
		}
	}

	private KnownRoute findRoute(int i, int j) {

		for (int k = 0; k < routesNum; k++) {
			if (routes[k].containsPlaces(places[i].getId(), places[j].getId())) {
				return routes[k];
			}
		}

		return null;
	}

	public void printMatrixWithRelations(){

		System.out.println("\nGraph representation with adjacency matrix (showing relations): \n");
		for(int i = 0; i < placesNum; i++){
			for(int j = 0; j < placesNum; j++){
				if (matrix[i][j] != null) {
					System.out.print(places[i].getName() + "<->" + places[j].getName() + "@dist:" + matrix[i][j].getDistance() + "    ");
				} else {
					System.out.print(matrix[i][j] + "    ");
				}
			}
			System.out.println();
			System.out.println();
		}
	}

	public void printMatrix(){

		System.out.println("\nGraph representation with adjacency matrix (showing places): \n");

		// Show name of Kingdoms
		System.out.printf("%-20s", " ");
		for (int i = 0; i < placesNum; i++) {
			System.out.printf("%-20s", "(" + places[i].getKingdom() + ")");
		}
		System.out.println();

		// Show name of places
		System.out.printf("%-20s", " ");
		for (int i = 0; i < placesNum; i++) {
			System.out.printf("%-20s", places[i].getName());
		}

		System.out.println();
		System.out.println();

		// Print the whole matrix
		for(int i = 0; i < placesNum; i++){
			System.out.printf("%-25s", places[i].getName());	// Name of the place on the left
			for(int j = 0; j < placesNum; j++){
				if (matrix[i][j] != null) {
					System.out.printf("%-20s", matrix[i][j].getDistance());
				} else {
					if (i == j) {
						System.out.printf("%-20s", "0");
					}
					else {
						System.out.printf("%-20s", "-1");
					}
				}
			}
			System.out.println();
			System.out.println();
		}
	}

	public PlaceOfInterest[] getAdjacents(PlaceOfInterest node) {
		ArrayList<PlaceOfInterest> adjacents = new ArrayList<>();
		int rowIndex = node.getRowIndex();

		for (int columns = 0; columns < placesNum; columns++) {
			PlaceOfInterest currentNode = getPlaceByIndex(columns);
			KnownRoute currentRoute = matrix[rowIndex][columns];

			// Check if the current node is an adjacent
			if (currentRoute != null) {	// Check if exists
				if (currentRoute.getDistance() != -1) {	// Check conditions
					adjacents.add(currentNode);    // Add node to Array
				}
			}
		}

		return adjacents.toArray(new PlaceOfInterest[0]);	// Return Array of Adjacent Nodes
	}

	public PlaceOfInterest getPlaceByIndex(int placeIndex) {
		return places[placeIndex];
	}

	public PlaceOfInterest getPlaceByID(int placeID) {
		PlaceOfInterest node = null;

		// Try to get the place with the ID. TODO: QuickSort + Binary Search
		for (PlaceOfInterest place : places) {
			if (place.getId() == placeID) {
				node = place;
				break;
			}
		}

		return node;
	}

	public int getSize() {
		return placesNum;
	}

	// Getter to obtain all the nodes in the graph.
	public PlaceOfInterest[] getPlaces() {
		return places;
	}


	// Get distance between two adjacent nodes.
	public float getRouteDistance(int actualPlace, int adjacentPlace) {
		return (matrix[actualPlace][adjacentPlace].getDistance());
	}

	// Get time between two adjacent nodes.
	public double getRouteTime(int actualPlace, int adjacentPlace) {
		return (matrix[actualPlace][adjacentPlace].getTime());
	}


	//Getter to obtain the first node
	public PlaceOfInterest getFirstNode() {
		return places[0];
	}


	public KnownRoute getRoute(int actualPlace, int adjacentPlace) {
		return matrix[actualPlace][adjacentPlace];
	}

	public boolean routeExists(int actualPlace, int adjacentPlace) {
		if(matrix[actualPlace][adjacentPlace] == null) {
			return false;
		} else {
			return true;
		}
	}

}
