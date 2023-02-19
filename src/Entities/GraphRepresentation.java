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

        //We initialize the matrix with null values
        for(int i = 0; i < placesNum - 1; i++) {
            for(int j = 0; j < placesNum - 1; j++) {
                matrix[i][j] = null;
            }
        }

        createGraph();

    }

    private void createGraph() {

        for(int i = 0; i < placesNum - 1; i++) {
            for(int j = 0; j < placesNum - 1; j++) {
                matrix[i][j] = findRoute(i, j);
            }
        }


    }

    private KnownRoute findRoute(int i, int j) {

        for (int k = 0; k < routesNum - 1; k++) {
            if (routes[k].containsPlaces(i, j)) {
                return routes[k];
            }
        }

        return null;
    }


    // TODO: Mostrar primer els regnes que estan connectats directament (sense passar per altres regnes).
    // TODO: Tenir en compte que podrien haver regnes no connectats (no s'han de mostrar).

    public void add(KnownRoute knownRoute){
        matrix[knownRoute.getPlaceA()][knownRoute.getPlaceB()] = knownRoute;
        matrix[knownRoute.getPlaceB()][knownRoute.getPlaceA()] = knownRoute;
    }

    public void remove(int i, int j){
        /*if(matrix[i][j]>0)
            matrix[i][j] -= 1;*/
    }

    public void print(){
        for(int i = 0; i < placesNum; i++){
            for(int j = 0; j < placesNum; j++){
                System.out.print( matrix[i][j] + "  " );
            }
            System.out.println();
        }
    }

}
