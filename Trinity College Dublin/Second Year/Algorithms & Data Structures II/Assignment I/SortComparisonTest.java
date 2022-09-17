import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

//-------------------------------------------------------------------------
/**
 *  Test class for SortComparison.java
 *
 *  @author Christiana Popoola
 *  @version HT 2020
 *  
 *   		Times All Recorded in nano seconds!
     * 
     * 										InsertionSort	SelectionSort	QuickSort	MergeSort
     * 		numbers1000.txt					3.312			4.050			3.582		4.585
     * 		numbers10000.txt				2.964			3.901			3.520		3.894
     * 		numbers1000Duplicates.txt		3.871			3.988			3.976		4.154
     *		numbersNearlyOrdered1000.txt	4.283			3.769			4.319		4.833
     * 		numbersReverse1000.txt			4.217			3.861			3.890		4.272
     * 		numbersSorted1000.txt			3.684			3.990			3.849		4.018
     * 
     
 */

@RunWith(JUnit4.class)
public class SortComparisonTest
{
    //~ Constructor ........................................................
    @Test
    public void testConstructor()
    {
        new SortComparison();
    }

    //~ Public Methods ........................................................

    // ----------------------------------------------------------
    /**
     * Check that the methods work for empty arrays
     */
    @Test
    public void testInsertionSort() {
    	double[] testArray1 = {};
    	assertEquals("This array is empty and it should return null. ", null, SortComparison.insertionSort(testArray1));
    	double[] testArray2 = {97, 93, 13, 17, 83, 87, 27, 23};
    	assertEquals("This array should be ordered. ", "[13.0, 17.0, 23.0, 27.0, 83.0, 87.0, 93.0, 97.0]", Arrays.toString(SortComparison.insertionSort(testArray2)));
    }

    @Test
    public void testSelectionSort() {
    	double[] testArray1 = {};
    	double[] testArray2 = {97, 93, 13, 17, 83, 87, 27, 23};
    	assertEquals("This array is empty and it should return null. ", null, SortComparison.selectionSort(testArray1));
    	assertEquals("This array should be ordered. ", "[13.0, 17.0, 23.0, 27.0, 83.0, 87.0, 93.0, 97.0]", Arrays.toString(SortComparison.selectionSort(testArray2)));
    }
    
    @Test
    public void testQuickSort() {
    	double[] testArray1 = {};
    	double[] testArray2 = {97, 93, 13, 17, 83, 87, 27, 23};
    	
    	assertEquals("This array is empty and it should return null. ", null, SortComparison.quickSort(testArray1));
    	assertEquals("This array should be ordered. ", "[13.0, 17.0, 23.0, 27.0, 83.0, 87.0, 93.0, 97.0]", Arrays.toString(SortComparison.quickSort(testArray2)));


    }
    
    @Test
    public void testMergeSort() {
    	double[] testArray1 = {};
    	double[] testArray2 = {97, 93, 13, 17, 83, 87, 27, 23};
    	assertEquals("This array is empty and it should return null. ", null, SortComparison.mergeSort(testArray1));
    	assertEquals("This array should be ordered. ", "[13.0, 17.0, 23.0, 27.0, 83.0, 87.0, 93.0, 97.0]", Arrays.toString(SortComparison.mergeSort(testArray2)));
    
    	double[] testArray3 = {9, 10, 11, 12, 6, 5, 4, 3};
    	assertEquals("This array should be ordered. ", "[3.0, 4.0, 5.0, 6.0, 9.0, 10.0, 11.0, 12.0]", Arrays.toString(SortComparison.mergeSort(testArray3)));
	
    }
    
    @Test
    public void testIsEmpty() {
    	double[] testArray1 = {};
    	double[] testArray2 = {97, 93, 13, 17, 83, 87, 27, 23};
    	assertTrue("This should be true. ", SortComparison.isEmpty(testArray1));
    	assertFalse("This should be false. ", SortComparison.isEmpty(testArray2));
    }
    // TODO: add more tests here. Each line of code and ech decision in Collinear.java should
    // be executed at least once from at least one test.

    // ----------------------------------------------------------
    /**
     *  Main Method.
     *  Use this main method to create the experiments needed to answer the experimental performance questions of this assignment.
     * @throws FileNotFoundException 
     *
     */
//    public static void main(String[] args) throws FileNotFoundException
//    {
//		double test_array1[] = new double[10000];
//		
//		File file = new File("src/numbers10000.txt");
//		Scanner Input = new Scanner(file);
//		for(int i = 0; Input.hasNextDouble(); i++) 
//			test_array1[i] = Input.nextDouble();
//
//		System.out.println(Arrays.toString(SortComparison.mergeSort(test_array1)));
//		
//		Stopwatch stopwatch = new Stopwatch();
//
//		double time = stopwatch.elapsedTime();
//		System.out.println("Elapsed time: " + time);
//		
//		Input.close();
//		
//    }

}
// Bigger than pivot and same as last number
