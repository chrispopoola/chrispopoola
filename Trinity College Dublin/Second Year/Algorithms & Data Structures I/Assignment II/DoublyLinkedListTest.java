import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

//-------------------------------------------------------------------------
/**
 *  Test class for Doubly Linked List
 *
 *  @author  
 *  @version 13/10/16 18:15
 */
@RunWith(JUnit4.class)
public class DoublyLinkedListTest
{
    //~ Constructor ........................................................
    @Test
    public void testConstructor()
    {
      new DoublyLinkedList<Integer>();
    }

    //~ Public Methods ........................................................

    // ----------------------------------------------------------
    /**
     * Check if the insertBefore works
     */
    @Test
    public void testInsertBefore()
    {
        // test non-empty list
        DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
        testDLL.insertBefore(0,1);
        testDLL.insertBefore(1,2);
        testDLL.insertBefore(2,3);

        testDLL.insertBefore(0,4);
        assertEquals( "Checking insertBefore to a list containing 3 elements at position 0", "4,1,2,3", testDLL.toString() );
        testDLL.insertBefore(1,5);
        assertEquals( "Checking insertBefore to a list containing 4 elements at position 1", "4,5,1,2,3", testDLL.toString() );
        testDLL.insertBefore(2,6);       
        assertEquals( "Checking insertBefore to a list containing 5 elements at position 2", "4,5,6,1,2,3", testDLL.toString() );
        testDLL.insertBefore(-1,7);        
        assertEquals( "Checking insertBefore to a list containing 6 elements at position -1 - expected the element at the head of the list", "7,4,5,6,1,2,3", testDLL.toString() );
        testDLL.insertBefore(7,8);        
        assertEquals( "Checking insertBefore to a list containing 7 elemenets at position 8 - expected the element at the tail of the list", "7,4,5,6,1,2,3,8", testDLL.toString() );
        testDLL.insertBefore(700,9);        
        assertEquals( "Checking insertBefore to a list containing 8 elements at position 700 - expected the element at the tail of the list", "7,4,5,6,1,2,3,8,9", testDLL.toString() );

        // test empty list
        testDLL = new DoublyLinkedList<Integer>();
        testDLL.insertBefore(0,1);        
        assertEquals( "Checking insertBefore to an empty list at position 0 - expected the element at the head of the list", "1", testDLL.toString() );
        testDLL = new DoublyLinkedList<Integer>();
        testDLL.insertBefore(10,1);        
        assertEquals( "Checking insertBefore to an empty list at position 10 - expected the element at the head of the list", "1", testDLL.toString() );
        testDLL = new DoublyLinkedList<Integer>();
        testDLL.insertBefore(-10,1);        
        assertEquals( "Checking insertBefore to an empty list at position -10 - expected the element at the head of the list", "1", testDLL.toString() );
     }
    
    @Test
    public void testIsEmpty()
    {
    	DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
    	
    	assertTrue("Should be empty", testDLL.isEmpty());
    	testDLL.insertBefore(0, 1);
    	testDLL.insertBefore(1, 2);
    	testDLL.insertBefore(2, 3);
    	assertFalse("Should not be empty", testDLL.isEmpty());
    }
    
    @Test
    public void testSize()
    {
    	DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
    	
    	int expectedSize = 0;
    	assertEquals("Should be equal", expectedSize, testDLL.size());
    	
    	testDLL.insertBefore(0, 1);
    	testDLL.insertBefore(1, 2);
    	testDLL.insertBefore(2, 3);
    	expectedSize = 3;
    	assertEquals("Should be equal", expectedSize, testDLL.size());
    }
    
    @Test
    public void testGet()
    {
    	DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
    	int expectedValue;
    	testDLL.insertBefore(0, 1);
    	testDLL.insertBefore(1, 2);
    	testDLL.insertBefore(2, 3);
    	testDLL.insertBefore(3, 4);
    	testDLL.insertBefore(4, 5);
    	testDLL.insertBefore(5, 6);
    	
    	expectedValue = 6;
    	assertEquals("Should get 6", expectedValue, (int)testDLL.get(5));
    	expectedValue = 4;
    	assertEquals("Should get 4", expectedValue, (int)testDLL.get(3));
    	expectedValue = 3;
    	assertEquals("Should get 3", expectedValue, (int)testDLL.get(2));
    	expectedValue = 7;
    	assertNotEquals("Should not get 7", expectedValue, (int)testDLL.get(1));
    	
    	DoublyLinkedList<Integer> testDLL1 = new DoublyLinkedList<Integer>();
    	assertEquals("Should get null", null, testDLL1.get(5));    	
    	
    }

    @Test
    public void testDeleteAt()
    {
    	DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
    	
    	testDLL.insertBefore(0, 1);
    	testDLL.insertBefore(1, 2);
    	testDLL.insertBefore(2, 3);
    	testDLL.insertBefore(3, 4);
    	testDLL.insertBefore(4, 5);
    	testDLL.insertBefore(5, 6);
//     	
    	assertTrue("Should be true", testDLL.deleteAt(0));
    	assertTrue("Should be true", testDLL.deleteAt(4));
    	assertTrue("Should be true", testDLL.deleteAt(1));
    	
    	DoublyLinkedList<Integer> testDLL1 = new DoublyLinkedList<Integer>();
    	assertFalse("Should be false", testDLL1.deleteAt(5));    
    	
    	DoublyLinkedList<Integer> testDLL2 = new DoublyLinkedList<Integer>();
    	testDLL2.insertBefore(0, 1);
    	assertTrue("Should be true", testDLL2.deleteAt(0)); 
    }
    
    @Test
    public void testReverse()
    {
    	DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
    	testDLL.insertBefore(0, 1);
    	testDLL.insertBefore(1, 2);
    	testDLL.insertBefore(2, 3);
    	testDLL.insertBefore(3, 4);
    	testDLL.insertBefore(4, 5);
    	testDLL.insertBefore(5, 6);
    	
    	testDLL.reverse();
    	assertNotEquals("Checking string elements (ints) are not still in the same order", "1,2,3,4,5,6", testDLL.toString());
    	
    	assertEquals("Checking string elements (ints) were reversed", "6,5,4,3,2,1", testDLL.toString());
    	
    	DoublyLinkedList<Integer> testDLL2 = new DoublyLinkedList<Integer>();
    	testDLL2.insertBefore(0, 10);
    	testDLL2.insertBefore(1, 70);
    	testDLL2.insertBefore(2, 50);
    	
    	testDLL2.reverse();
    	assertEquals("Checking string elements (ints) were reversed", "50,70,10", testDLL2.toString());
    	
    }
    
    @Test
    public void testMakeUnique()
    {
    	DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
    	testDLL.insertBefore(0, 1);
    	testDLL.insertBefore(1, 2);
    	testDLL.insertBefore(2, 3);
    	testDLL.insertBefore(3, 1);
    	testDLL.insertBefore(4, 7);
    	testDLL.insertBefore(5, 3);
    	
    	testDLL.makeUnique();
    	assertEquals("Checking for no repeated elements", "1,2,3,7", testDLL.toString()); 
    	
    	DoublyLinkedList<Integer> testDLL1 = new DoublyLinkedList<Integer>();
    	testDLL1.makeUnique();
    	assertEquals("Checking for no repeated elements", "", testDLL1.toString());
    }
    
    @Test
    public void testPush()
    {
    	DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
    	testDLL.push(10);
    	testDLL.push(20);
    	testDLL.push(30);
    	testDLL.push(40);
    	testDLL.push(50);
    	
    	assertEquals("Checking to see elements were push onto the stack appropriately (FILO)", "50,40,30,20,10", testDLL.toString());
    	
    }
    
    @Test
    public void testPop()
    {
    	int expectedValue;
    	DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
    	testDLL.push(10);
    	testDLL.push(20);
    	testDLL.push(30);
    	testDLL.push(40);
    	testDLL.push(50);
    	
    	
    	expectedValue = 50;
    	assertEquals("Checking that popped value is expectedValue", expectedValue, (int)testDLL.pop());
    	expectedValue = 40;
    	assertEquals("Checking that popped value is expectedValue", expectedValue, (int)testDLL.pop());
    	expectedValue = 30;
    	assertEquals("Checking that popped value is expectedValue", expectedValue, (int)testDLL.pop());
    	expectedValue = 20;
    	assertEquals("Checking that popped value is expectedValue", expectedValue, (int)testDLL.pop());
    	
    	DoublyLinkedList<Integer> testDLL1 = new DoublyLinkedList<Integer>();
    	assertEquals("Checking that popped value is null", null, testDLL1.pop());    	
    }
    
    @Test
    public void testEnqueue()
    {
    	DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
    	testDLL.enqueue(10);
    	testDLL.enqueue(20);
    	testDLL.enqueue(30);
    	testDLL.enqueue(40);
    	testDLL.enqueue(50);
    	
    	assertEquals("Checking to see elements were push onto the stack appropriately (FIFO)", "10,20,30,40,50", testDLL.toString());
    	
    }
    
    @Test
    public void testDequeue()
    {
    	int expectedValue;
    	DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
    	testDLL.enqueue(10);
    	testDLL.enqueue(20);
    	testDLL.enqueue(30);
    	testDLL.enqueue(40);
    	testDLL.enqueue(50);
    	
    	
    	expectedValue = 10;
    	assertEquals("Checking that dequeued value is expectedValue", expectedValue, (int)testDLL.dequeue());
    	expectedValue = 20;
    	assertEquals("Checking that dequeued value is expectedValue", expectedValue, (int)testDLL.dequeue());
    	expectedValue = 30;
    	assertEquals("Checking that dequeued value is expectedValue", expectedValue, (int)testDLL.dequeue());
    	expectedValue = 40;
    	assertEquals("Checking that dequeued value is expectedValue", expectedValue, (int)testDLL.dequeue());
    	
    	DoublyLinkedList<Integer> testDLL1 = new DoublyLinkedList<Integer>();
    	assertEquals("Checking that dequeued value is expectedValue", null, testDLL1.dequeue());   
    }
    
    @Test
    public void testToString()
    {
    	DoublyLinkedList<Integer> testDLL1 = new DoublyLinkedList<Integer>();
    	testDLL1.insertBefore(0, 10);
    	testDLL1.insertBefore(7, 20);
    	testDLL1.insertBefore(50, 30);
    	
    	assertEquals( "Checking toString converting elements into comma separated list", "10,20,30", testDLL1.toString() );
    	
    	DoublyLinkedList<Integer> testDLL2 = new DoublyLinkedList<Integer>();
    	testDLL2.push(300);
    	testDLL2.push(200);
    	testDLL2.push(100);
    	
    	assertEquals( "Checking toString converting elements into comma separated list", "100,200,300", testDLL2.toString() );   	
    	
    	DoublyLinkedList<Integer> testDLL3 = new DoublyLinkedList<Integer>();
    	testDLL3.enqueue(50);
    	testDLL3.enqueue(150);
    	testDLL3.enqueue(250);
    	
    	assertEquals( "Checking toString converting elements into comma separated list", "50,150,250", testDLL3.toString() );

    	
    }
    
}

