package GraphsF1.Entities;

import Parsers.DatasetLoaderF1;

import java.util.ArrayList;

import static Auxiliar.Algorithms.BinarySearch.binarySearch;
import static Auxiliar.Algorithms.MergeSort.mergeSort;

public class Graph {
	private final KnownRoute[][] matrix;
	private final int placesNum;	// Columns of the matrix (nodes)
	private final int routesNum;	// Edges between all the nodes
	private final PlaceOfInterest[] places;
	private final KnownRoute[] routes;

	public Graph(String datasetName) {
		places = DatasetLoaderF1.loadPlaces(datasetName);
		routes = DatasetLoaderF1.loadRoutes(datasetName);

		placesNum = places.length;
		routesNum = routes.length;

		// Sort places using MergeSort and then apply BinarySearch to look for an ID (lower cost).
		mergeSort(places, 0, placesNum - 1);

		matrix = new KnownRoute[placesNum][placesNum];
		createGraph();
	}

	private void createGraph() {

		PlaceOfInterest place1, place2;
		for (int i = 0; i < routesNum; i++) {
			place1 = getPlaceByID(routes[i].getPlaceA());
			place2 = getPlaceByID(routes[i].getPlaceB());
			matrix[place1.getRowIndex()][place2.getRowIndex()] = routes[i];
			matrix[place2.getRowIndex()][place1.getRowIndex()] = routes[i];

		}
	}

	public void printRelations(){

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
					System.out.printf("%-20s", "-1");
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

		// Get the place with the ID using Binary Search (since we use MergeSort for the array).
		return binarySearch(places, new PlaceOfInterest(placeID));
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

	// Get time A between two adjacent nodes.
	public float getRouteTimeA(int actualPlace, int adjacentPlace) {
		return (matrix[actualPlace][adjacentPlace].getTimeA());
	}

	// Get time B between two adjacent nodes.
	public float getRouteTimeE(int actualPlace, int adjacentPlace) {
		return (matrix[actualPlace][adjacentPlace].getTimeE());
	}

	//Getter to obtain the first node
	public PlaceOfInterest getFirstNode() {
		return places[0];
	}

	//Gets the route with all the info between the two nodes.
	public KnownRoute getRoute(int actualPlace, int adjacentPlace) {
		return matrix[actualPlace][adjacentPlace];
	}

	//Checks if a route between to places exists and returns FALSE if it doesn't
	public boolean routeExists(int actualPlace, int adjacentPlace) {
		return matrix[actualPlace][adjacentPlace] != null;
	}
}
