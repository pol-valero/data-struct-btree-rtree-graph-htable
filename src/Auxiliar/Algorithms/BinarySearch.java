package Auxiliar.Algorithms;

import java.util.List;

public class BinarySearch {

	// Method used to search an element in log(n) time, in which array has to be sorted ascendant (using MergeSort).
	public static <T extends Comparable<T>> T binarySearch(List<T> array, T element) {
		int start = 0;
		int end = array.size() - 1;

		while (start <= end) {
			int middle = (start + end) / 2;

			// If middle element is lower than the element searched, search on the second half of the array (which has higher elements).
			if (array.get(middle).compareTo(element) < 0) {
				start = middle + 1;
			}

			// If middle element is higher than the element searched, search on the first half of the array (which has lower elements).
			else if (array.get(middle).compareTo(element) > 0) {
				end = middle - 1;
			}

			// If the middle element is the one searched (element), return it.
			else {
				return array.get(middle);
			}
		}

		return null;
	}
}
