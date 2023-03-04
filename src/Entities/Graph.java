package Entities;

import Parsers.DatasetLoaderF1;

import java.util.ArrayList;

public class Graph {
	private KnownRoute[][] nodes;
	private int placesNum;	// Columns of the matrix (nodes)
	private int routesNum;	// Edges between all the nodes
	private PlaceOfInterest[] places;
	private KnownRoute[] routes;

	public Graph(String datasetName) {
		places = DatasetLoaderF1.loadPlaces(datasetName);
		routes = DatasetLoaderF1.loadRoutes(datasetName);

		placesNum = places.length;
		routesNum = routes.length;

		nodes = new KnownRoute[placesNum][placesNum];
		createGraph();
	}

	private void createGraph() {
		for (int i = 0; i < placesNum; i++) {
			for (int j = 0; j < placesNum; j++) {
				nodes[i][j] = findRoute(i, j);
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

	public void printRelations(){

		System.out.println("\nGraph representation with adjacency matrix (showing relations): \n");
		for(int i = 0; i < placesNum; i++){
			for(int j = 0; j < placesNum; j++){
				if (nodes[i][j] != null) {
					System.out.print(places[i].getName() + "<->" + places[j].getName() + "@dist:" + nodes[i][j].getDistance() + "    ");
				} else {
					System.out.print(nodes[i][j] + "    ");
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
				if (nodes[i][j] != null) {
					System.out.printf("%-20s", nodes[i][j].getDistance());
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
			KnownRoute currentRoute = nodes[rowIndex][columns];

			// Check if the current node is an adjacent
			if (currentRoute != null && currentNode != node) {	// Check if exists
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
	public double getRouteDistance(int actualPlace, int adjacentPlace) {
		return (nodes[actualPlace][adjacentPlace].getDistance());
	}

	// Get time A between two adjacent nodes.
	public double getRouteTimeA(int actualPlace, int adjacentPlace) {
		return (nodes[actualPlace][adjacentPlace].getTimeA());
	}

	// Get time B between two adjacent nodes.
	public double getRouteTimeE(int actualPlace, int adjacentPlace) {
		return (nodes[actualPlace][adjacentPlace].getTimeE());
	}
}
