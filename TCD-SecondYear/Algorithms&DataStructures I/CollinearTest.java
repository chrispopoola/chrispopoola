
import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.Arrays;

//-------------------------------------------------------------------------
/**
 *  Test class for Collinear.java
 *
 *  @author  
 *  @version 18/09/18 12:21:26
 */
@RunWith(JUnit4.class)
public class CollinearTest
{
    //~ Constructor ........................................................
    @Test
    public void testConstructor()
    {
      new Collinear();
    }

    //~ Public Methods ........................................................

    // ----------------------------------------------------------
    /**
     * Check that the two methods work for empty arrays
     */
    @Test
    public void testEmpty()
    {
        int expectedResult = 0;

        assertEquals("countCollinear failed with 3 empty arrays",       expectedResult, Collinear.countCollinear(new int[0], new int[0], new int[0]));
        assertEquals("countCollinearFast failed with 3 empty arrays", expectedResult, Collinear.countCollinearFast(new int[0], new int[0], new int[0]));
    }

    // ----------------------------------------------------------
    /**
     * Check for no false positives in a single-element array
     */
    @Test
    public void testSingleFalse()
    {
        int[] a3 = { 15 };
        int[] a2 = { 5 };
        int[] a1 = { 10 };

        int expectedResult = 0;

        assertEquals("countCollinear({10}, {5}, {15})",       expectedResult, Collinear.countCollinear(a1, a2, a3) );
        assertEquals("countCollinearFast({10}, {5}, {15})", expectedResult, Collinear.countCollinearFast(a1, a2, a3) );
    }

    // ----------------------------------------------------------
    /**
     * Check for no false positives in a single-element array
     */
    @Test
    public void testSingleTrue()
    {
        int[] a3 = { 15, 5 };       int[] a2 = { 5 };       int[] a1 = { 10, 15, 5 };

        int expectedResult = 1;

        assertEquals("countCollinear(" + Arrays.toString(a1) + "," + Arrays.toString(a2) + "," + Arrays.toString(a3) + ")",     expectedResult, Collinear.countCollinear(a1, a2, a3));
        assertEquals("countCollinearFast(" + Arrays.toString(a1) + "," + Arrays.toString(a2) + "," + Arrays.toString(a3) + ")", expectedResult, Collinear.countCollinearFast(a1, a2, a3));
    }


    // TODO: add more tests here. Each line of code and each decision in Collinear.java should
    // be executed at least once from at least one test.
    @Test
    public void testCountCollinear ()
    {
    	// testing countCollinear() with 4 sets of collinear points
    	int[] a1 = {22, 14, 13, 7, 3, 5};
    	int[] a2 = {2, 8, 13, 17, 22};
    	int[] a3 = {1, 7, 11, 17, 21};
    	    	
    	int expectedValue = 4; 
    	
    	assertEquals("Should have 4 sets of collinear points", expectedValue, Collinear.countCollinear(a1, a2, a3));
    	
    	// testing countCollinear() with 1 set of collinear points
    	int[] a4 = {22, 35, 13, 7, 3, 5};
    	int[] a5 = {6, 8, 9, 10, 11, 13};
    	int[] a6 = {1, 2, 3, 6, 8};
    	
    	int expectedValue2 = 1;
    	
    	assertEquals("Should have 1 set of collinear points", expectedValue2, Collinear.countCollinear(a4, a5, a6));
    	
    	// testing countCollinear() with 0 set of collinear points
    	int[] a7 = {22, 35, 13, 7, 3, 5};
    	int[] a8 = {6, 8, 9, 10, 11, 13};
    	int[] a9 = {1, 2, 10, 6, 8};
    	
    	int expectedValue3 = 0;
    	
    	assertEquals("Should have 0 sets of collinear points", expectedValue3, Collinear.countCollinear(a7, a8, a9));
    }
    
    @Test
    public void testCountCollinearFast ()
    {
     	// testing countCollinearFast() with 4 sets of collinear points
    	int[] a1 = {22, 14, 13, 7, 3, 5};
    	int[] a2 = {2, 8, 13, 17, 22};
    	int[] a3 = {1, 7, 11, 17, 21};
    	
    	int expectedValue = 4; 
    	
    	assertEquals("Should have 4 sets of collinear points", expectedValue, Collinear.countCollinearFast(a1, a2, a3));
    	
    	// testing countCollinearFast() with 1 set of collinear points
    	int[] a4 = {22, 35, 13, 7, 3, 5};
    	int[] a5 = {6, 8, 9, 10, 11, 13};
    	int[] a6 = {1, 2, 3, 6, 8};
    	
    	int expectedValue2 = 1;
    	
    	assertEquals("Should have 1 set of collinear points", expectedValue2, Collinear.countCollinearFast(a4, a5, a6));
    	
    	// testing countCollinearFast() with 0 set of collinear points
    	int[] a7 = {22, 35, 13, 7, 3, 5};
    	int[] a8 = {6, 8, 9, 10, 11, 13};
    	int[] a9 = {1, 2, 10, 6, 8};
    	
    	int expectedValue3 = 0;
    	
    	assertEquals("Should have 0 sets of collinear points", expectedValue3, Collinear.countCollinearFast(a7, a8, a9));
    }
    
    @Test
    public void testBinarySearch ()
    {
    	int[] array1 = {97, 73, 37, 41, 59, 13, 3, 21};
    	
    	Collinear.sort(array1);
    	
    	assertTrue("Should find 3", Collinear.binarySearch(array1, 3));
    	assertFalse("Should not find 7", Collinear.binarySearch(array1, 7));
    			
    }

}
