package Algorithms.auxiliar;

import Entities.PlaceOfInterest;

public class MergeSort {
	public static void mergeSort(PlaceOfInterest[] nodes, int start, int end) {

		// Cas en què hi ha més d'un element a l'array, i s'ha d'ordenar.
		if (start < end) {
			int mig = (start + end) / 2;
			mergeSort(nodes, start, mig);         // Dividir l'array per la meitat (primera meitat).
			mergeSort(nodes, mig + 1, end);  // Dividir l'array per la meitat (segona meitat).
			merge(nodes, start, mig, end);        // Ordenar cada meitat de l'array.
		}

		// Cas trivial en què només hi ha un element a l'array, per tant, start = end.
		return;
	}

	// Subfunció de l'algorisme "Merge Sort", que implementa la lògica d'ordenar les sub-arrays alfabèticament.
	public static void merge (PlaceOfInterest[] nodes, int start, int mig, int end) {
		int left = start;       // Posicionar el punter d'inici d'array de la primera meitat a la primera posició.
		int right = mig + 1;    // Posicionar el punter d'inici d'array de la segona meitat a la posició del mig + 1.
		int pos = start;        // Posicionar el cursor a la primera posició de l'array.
		PlaceOfInterest[] temp = new PlaceOfInterest[nodes.length];  // Creació d'una array de vaixells temporals per anar emmagatzemant els vaixells ordenats.

		// Es comprova cada posició de cada meitat mentre no s'hagi arribat al final de cada meitat.
		while (left <= mig && right <= end) {

			// Es compara si les cadenes estan ordenades alfabèticament.
			if (nodes[left].getId() < nodes[right].getId()) {
				temp[pos] = nodes[left];     // Si estan ordenades, es guarda la primera cadena.
				left++;     // S'actualitzen el punter de la primera posició de la primera meitat, i el cursor.
				pos++;
			}
			else {
				temp[pos] = nodes[right];    // Si no estan ordenades, es guarda la segona cadena.
				right++;    // S'actualitzen el punter de la primera posició de la segona meitat, i el cursor.
				pos++;
			}
		}

		// Un cop s'ha arribat al final d'una de les dues meitats, es copia cada meitat respectivament.
		while (left <= mig) {
			temp[pos] = nodes[left];     // Es copien els vaixells restants de la primera meitat.
			left++;
			pos++;
		}

		while (right <= end) {
			temp[pos] = nodes[right];    // Es copien els vaixells restants de la segona meitat.
			right++;
			pos++;
		}

		int aux = start;

		// Es fa un swap sencer de l'array, intercanviant els vaixells de l'array a ordenar pels de la temporal, amb l'ordre corresponent.
		while (aux <= end) {
			nodes[aux] = temp[aux];
			aux++;
		}
	}
}
