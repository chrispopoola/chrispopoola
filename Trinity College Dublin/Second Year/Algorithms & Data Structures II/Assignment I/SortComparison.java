
// -------------------------------------------------------------------------

/**
 *  This class contains static methods that implementing sorting of an array of numbers
 *  using different sort algorithms.
 *
 *  @author Christiana Popoola
 *  @version HT 2020
 */

class SortComparison {

	//	static Scanner Input = new Scanner(System.in);
	/**
	 * Sorts an array of doubles using InsertionSort.
	 * This method is static, thus it can be called as SortComparison.sort(a)
	 * @param a: An unsorted array of doubles.
	 * @return array sorted in ascending order.
	 * 
	 */
	static double [] insertionSort (double a[]){
		if (isEmpty(a))
			return null;
		else {
			double sorted[] = a;
			double temporary;

			for (int i = 1; i < sorted.length; i++) {
				for (int j = i; j > 0; j--) {
					if (sorted[j] < sorted[j-1]) {
						temporary = sorted[j];
						sorted[j] = sorted[j-1];
						sorted[j-1] = temporary;
					}
				}
			}
			return sorted;
		}
	} // end insertionsort

	/**
	 * Sorts an array of doubles using Selection Sort.
	 * This method is static, thus it can be called as SortComparison.sort(a)
	 * @param a: An unsorted array of doubles.
	 * @return array sorted in ascending order
	 *
	 */
	static double [] selectionSort (double a[]){
		if (isEmpty(a))
			return null;
		else {
			double sorted[] = a;

			for (int i = 0; i < sorted.length-1; i++) {
				int min_index = i;
				for (int j = i+1; j < sorted.length; j++) {
					if (sorted[j] < sorted[min_index])
						min_index = j;
				}
				double temporary = sorted[min_index];
				sorted[min_index] = sorted[i];
				sorted[i] = temporary;
			}
			return sorted;
		}
	} // end selectionsort

	/**
	 * Sorts an array of doubles using Quick Sort.
	 * This method is static, thus it can be called as SortComparison.sort(a)
	 * @param a: An unsorted array of doubles.
	 * @return array sorted in ascending order
	 *
	 */
	static double [] quickSort (double a[]) {
		if (isEmpty(a))
			return null;
		else {
			double sorted[] = a;
			quickRecursive(sorted, 0, sorted.length-1);
			return sorted;
		}
	} // end quicksort

	static void quickRecursive(double sorted[], int low, int high) {
		if (high <= low)
			return;
		int pivotPos = partition(sorted, low, high);
		quickRecursive(sorted, low, pivotPos-1);
		quickRecursive(sorted, pivotPos+1, high);
	}

	private static int partition(double sorted[], int low, int high) {
		int i = low,  j = high+1;
		double pivot = sorted[low];

		while(true) {
			while((Double.compare(sorted[++i], pivot) < 0)) 
				if(i == high)
					break;

			while((Double.compare(pivot,  sorted[--j]) < 0)) 
				if(j == low) 
					break;

			if(i >= j) 
				break;
			double temporary = sorted[i];
			sorted[i] = sorted[j];
			sorted[j] = temporary;
		}
		sorted[low] = sorted[j];
		sorted[j] = pivot;
		return j;
	}


	/**
	 * Sorts an array of doubles using Merge Sort.
	 * This method is static, thus it can be called as SortComparison.sort(a)
	 * @param a: An unsorted array of doubles.
	 * @return array sorted in ascending order
	 *
	 */
	/**
	 * Sorts an array of doubles using iterative implementation of Merge Sort.
	 * This method is static, thus it can be called as SortComparison.sort(a)
	 *
	 * @param a: An unsorted array of doubles.
	 * @return after the method returns, the array must be in ascending sorted order.
	 */

	static double[] mergeSort (double a[]) {
		if (isEmpty(a))
			return null;
		else {
			double b[] = new double[a.length];
			mSort(a, b, 0, a.length-1);
			return a;
		}
	} // end mergesort

	private static void mSort(double a[], double b[], int low, int high) {
		if (high <= low) 
			return;
		int middle = low + (high-low) / 2;
		mSort(a, b, low, middle);
		mSort(a, b, middle+1, high);
		if (Double.compare(a[middle+1], a[middle]) >= 0)
			return;
		merge(a, b, low, middle, high);
	}

	private static void merge(double a[], double b[], int low, int middle, int high) {
		for (int k = low; k <= high; k++)
			b[k] = a[k];
			
		int i = low, j = middle+1;
		for (int k = low; k <= high; k++) {

			if (i > middle)
				a[k] = b[j++];
			else if (j > high)
				a[k] = b[i++];
			else if ((Double.compare(b[j], b[i])) < 0 ) 
				a[k] = b[j++];
			else
				a[k] = b[i++];
		}
	}

	static boolean isEmpty(double a[]) {
		boolean isEmpty = false;
		if (a.length <= 0)
			isEmpty = true;
		return isEmpty;
	}

//	public static void main(String[] args) { 	
//		double test_array1[] = {700, 300, 730, 370, 3, 37, 73};
//		double test_array2[] = {100, 90, 80, 70, 60, 50, 40};
//		double test_array3[] = {97, 87, 13, 23, 73, 37, 53, 47};
//		double test_array4[] = {73, 77, 97, 93, 33, 37, 57, 53};
//
//		double sort1[] = insertionSort(test_array1);	
//		double sort2[] = selectionSort(test_array2);
//		double sort3[] = quickSort(test_array3);
//		double sort4[] = mergeSort(test_array4);
//
//		for(int i = 0; i < test_array1.length; i++) {       	
//			if (i == test_array1.length-1)
//				System.out.print(sort1[i] + "\n");
//			else
//				System.out.print(sort1[i] + " ");
//		}
//
//		for(int i = 0; i < test_array2.length; i++) {       	
//			if (i == test_array2.length-1)
//				System.out.print(sort2[i] + "\n");
//			else
//				System.out.print(sort2[i] + " ");
//		}
//
//		for(int i = 0; i < test_array3.length; i++) {       	
//			if (i == test_array3.length-1)
//				System.out.print(sort3[i] + "\n");
//			else
//				System.out.print(sort3[i] + " ");
//		}
//
//		for(int i = 0; i < test_array4.length; i++) {       	
//			if (i == test_array4.length-1)
//				System.out.print(sort4[i] + "\n");
//			else
//				System.out.print(sort4[i] + " ");
//		}
//	}

}//end class

