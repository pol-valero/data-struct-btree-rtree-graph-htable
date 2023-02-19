package Menu;

import Entities.KnownRoute;

public class GraphRepresentation {
    private final int N;
    private KnownRoute[][] matrix;

    /**
     * Constructor de clase
     * @param N numero de nodos
     */
    public GraphRepresentation(int N) {
        this.N = N;
        matrix = new KnownRoute[N][N];

        //se inicializa matriz en 0
        for(int i = 0; i < N; i++){
            for(int j = 0; j< N; j++) {
                matrix[i][j] = null;
            }
        }
    }
    /**
     * Añadir numero trayectos a la matriz
     * @param i,j numero de nodos
     */

    // TODO: Mostrar primer els regnes que estan connectats directament (sense passar per altres regnes).
    // TODO: Tenir en compte que podrien haver regnes no connectats (no s'han de mostrar).

    public void add(KnownRoute knownRoute){
        matrix[knownRoute.getPlaceA()][knownRoute.getPlaceB()] = knownRoute;
        matrix[knownRoute.getPlaceB()][knownRoute.getPlaceA()] = knownRoute;
    }

    public void remove(int i, int j){
        if(matrix[i][j]>0)
            matrix[i][j] -= 1;
    }

    public void print(){
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                System.out.print( matrix[i][j] + "  " );
            }
            System.out.println();
        }
    }

}
