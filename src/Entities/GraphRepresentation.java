package Entities;

import parsers.DatasetLoaderF1;

public class GraphRepresentation {

    private int placesNum;
    private int routesNum;
    private KnownRoute[][] matrix;
    private PlaceOfInterest[] places;
    private KnownRoute[] routes;

    /**
     * Class constructor
     * @param datasetName name of the dataset to be loaded
     */
    public GraphRepresentation(String datasetName) {

        places = DatasetLoaderF1.loadPlaces(datasetName);
        routes = DatasetLoaderF1.loadRoutes(datasetName);

        placesNum = places.length;
        routesNum = routes.length;

        matrix = new KnownRoute[placesNum][placesNum];

        createGraph();

    }

    private void createGraph() {

        for(int i = 0; i < placesNum; i++) {
            for(int j = 0; j < placesNum; j++) {
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

    public void print(){

        System.out.println("\nGraph representation with adjacency matrix: \n");
        for(int i = 0; i < placesNum; i++){
            for(int j = 0; j < placesNum; j++){
                if (matrix[i][j] != null) {
                    System.out.print(places[i].getName() + "-" + places[j].getName() + "@dist:" + matrix[i][j].getDistance() + "    ");
                } else {
                    System.out.print(matrix[i][j] + "    ");
                }
            }
            System.out.println();
        }
    }


    // TODO: Mostrar primer els regnes que estan connectats directament (sense passar per altres regnes).
    // TODO: Tenir en compte que podrien haver regnes no connectats (no s'han de mostrar).

}
