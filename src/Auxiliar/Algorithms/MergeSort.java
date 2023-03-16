package Auxiliar.Algorithms;

import GraphsF1.Entities.PlaceOfInterest;

public class MergeSort {
	// MergeSort sort algorithm in ascendent order, used to sort the nodes by ID to then apply Binary Search and look for their ID, with cost O(n · log[n])
	public static <T extends Comparable<T>> void mergeSort(T[] array, int start, int end) {

		// Cas en què hi ha més d'un element a l'array, i s'ha d'ordenar.
		if (start < end) {
			int mig = (start + end) / 2;
			mergeSort(array, start, mig);         // Dividir l'array per la meitat (primera meitat).
			mergeSort(array, mig + 1, end);  // Dividir l'array per la meitat (segona meitat).
			merge(array, start, mig, end);        // Ordenar cada meitat de l'array.
		}

		// Cas trivial en què només hi ha un element a l'array, per tant, start = end.
	}

	// Subfunció de l'algorisme "Merge Sort", que implementa la lògica d'ordenar les sub-arrays alfabèticament.
	public static <T extends Comparable<T>> void merge (T[] array, int start, int mig, int end) {
		int left = start;       // Posicionar el punter d'inici d'array de la primera meitat a la primera posició.
		int right = mig + 1;    // Posicionar el punter d'inici d'array de la segona meitat a la posició del mig + 1.
		int pos = start;        // Posicionar el cursor a la primera posició de l'array.
		T[] temp = (T[]) new Comparable[array.length];  // Creació d'una array de nodes temporals per anar emmagatzemant els nodes ordenats.

		// Es comprova cada posició de cada meitat mentre no s'hagi arribat al final de cada meitat.
		while (left <= mig && right <= end) {

			// Es compara si les cadenes estan ordenades alfabèticament.
			if (array[left].compareTo(array[right]) < 0) {
				temp[pos] = array[left];     // Si estan ordenades, es guarda la primera cadena.
				left++;     // S'actualitzen el punter de la primera posició de la primera meitat, i el cursor.
				pos++;
			}
			else {
				temp[pos] = array[right];    // Si no estan ordenades, es guarda la segona cadena.
				right++;    // S'actualitzen el punter de la primera posició de la segona meitat, i el cursor.
				pos++;
			}
		}

		// Un cop s'ha arribat al final d'una de les dues meitats, es copia cada meitat respectivament.
		while (left <= mig) {
			temp[pos] = array[left];     // Es copien els nodes restants de la primera meitat.
			left++;
			pos++;
		}

		while (right <= end) {
			temp[pos] = array[right];    // Es copien els nodes restants de la segona meitat.
			right++;
			pos++;
		}

		int aux = start;

		// Es fa un swap sencer de l'array, intercanviant els vaixells de l'array a ordenar pels de la temporal, amb l'ordre corresponent.
		while (aux <= end) {
			array[aux] = temp[aux];

			// Check if the element is a PlaceOfInterest, so we can update its RowIndex (necessary in order to keep the relations).
			if (array[aux] instanceof PlaceOfInterest place) {
				place.setRowIndex(aux);	// Update node RowIndex since its position in array changed.
			}

			aux++;
		}
	}
}
