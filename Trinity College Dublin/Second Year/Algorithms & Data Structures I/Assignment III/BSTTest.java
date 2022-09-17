import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

//-------------------------------------------------------------------------
/**
 *  Test class for Doubly Linked List
 *
 *  @version 3.1 09/11/15 11:32:15
 *
 *  @author  TODO
 */

@RunWith(JUnit4.class)
public class BSTTest
{
  
  //TODO write more tests here.

  
  /** <p>Test {@link BST#prettyPrintKeys()}.</p> */
      
 @Test
 public void testPrettyPrint() {
     BST<Integer, Integer> bst = new BST<Integer, Integer>();
     assertEquals("Checking pretty printing of empty tree",
             "-null\n", bst.prettyPrintKeys());
      
                          //  -7
                          //   |-3
                          //   | |-1
                          //   | | |-null
     bst.put(7, 7);       //   | |  -2
     assertEquals("Checking pretty printing of single element tree", "-7\n" + " |-null\n" + "  -null\n", bst.prettyPrintKeys());
     bst.put(8, 8);       //   | |   |-null
     bst.put(3, 3);       //   | |    -null
     bst.put(1, 1);       //   |  -6
     bst.put(2, 2);       //   |   |-4
     bst.put(6, 6);       //   |   | |-null
     bst.put(4, 4);       //   |   |  -5
     bst.put(5, 5);       //   |   |   |-null
                          //   |   |    -null
                          //   |    -null
                          //    -8
                          //     |-null
                          //      -null
     
     String result = 
      "-7\n" +
      " |-3\n" + 
      " | |-1\n" +
      " | | |-null\n" + 
      " | |  -2\n" +
      " | |   |-null\n" +
      " | |    -null\n" +
      " |  -6\n" +
      " |   |-4\n" +
      " |   | |-null\n" +
      " |   |  -5\n" +
      " |   |   |-null\n" +
      " |   |    -null\n" +
      " |    -null\n" +
      "  -8\n" +
      "   |-null\n" +
      "    -null\n";
     assertEquals("Checking pretty printing of non-empty tree", result, bst.prettyPrintKeys());
     }

  
     /** <p>Test {@link BST#delete(Comparable)}.</p> */
     @Test
     public void testDelete() {
         BST<Integer, Integer> bst = new BST<Integer, Integer>();
         bst.delete(1);
         assertEquals("Deleting from empty tree", "()", bst.printKeysInOrder());
         
         bst.put(7, 7);   //        _7_
         bst.put(8, 8);   //      /     \
         bst.put(3, 3);   //    _3_      8
         bst.put(1, 1);   //  /     \
         bst.put(2, 2);   // 1       6
         bst.put(6, 6);   //  \     /
         bst.put(4, 4);   //   2   4
         bst.put(5, 5);   //        \
                          //         5
         
         assertEquals("Checking order of constructed tree",
                 "(((()1(()2()))3((()4(()5()))6()))7(()8()))", bst.printKeysInOrder());
         
         bst.delete(13);
         assertEquals("Deleting non-existent key",
                 "(((()1(()2()))3((()4(()5()))6()))7(()8()))", bst.printKeysInOrder());
 
         bst.delete(8);
         assertEquals("Deleting leaf", "(((()1(()2()))3((()4(()5()))6()))7())", bst.printKeysInOrder());
 
         bst.delete(6);
         assertEquals("Deleting node with single child",
                 "(((()1(()2()))3(()4(()5())))7())", bst.printKeysInOrder());
 
         bst.delete(3);
         assertEquals("Deleting node with two children",
                 "(((()1())2(()4(()5())))7())", bst.printKeysInOrder());
         
    	 BST<Integer, Integer> bst1 = new BST<Integer, Integer>();
    	 bst1.delete(3);
    	 bst1.put(7, 70);
    	 bst1.delete(7);
    	 assertTrue("Checking to see if tree is empty after deletion", bst1.isEmpty());
     }
     @Test
     public void testPrintKeysInOrder() {
    	 BST<Integer, Integer> bst1 = new BST<Integer, Integer>();
    	 BST<Integer, Integer> bst2 = new BST<Integer, Integer>();
    	 bst1.put(7, 70); 
    	 assertEquals ("Checking for the correct order", "(()7())", bst1.printKeysInOrder());
    	 bst1.put(6, 60);    	 
    	 bst1.put(5, 50);
    	 bst1.put(4, 40);
    	 bst1.put(1, 10);
    	 bst1.put(2, 20);
    	 bst1.put(3, 30);


    	 assertEquals ("Checking for the correct order", "(((((()1(()2(()3())))4())5())6())7())", bst1.printKeysInOrder());
    	 bst1.delete(2);
    	 assertEquals ("Checking for the correct order after deletion", "(((((()1(()3()))4())5())6())7())", bst1.printKeysInOrder());
    	 bst1.put(8, 80);   	 
    	 assertEquals ("Checking for the correct order after addition", "(((((()1(()3()))4())5())6())7(()8()))", bst1.printKeysInOrder());

    	 assertEquals ("Checking for the correct output if empty", "()", bst2.printKeysInOrder());
    	 bst2.put(1, 100);    	 
    	 assertEquals ("Checking for the correct output if only one node", "(()1())", bst2.printKeysInOrder());
     }
     @Test
     public void testHeight() {
    	 BST<Integer, Integer> bst = new BST<Integer, Integer>();
    	 assertEquals("Checking to see correct height output for empty tree", -1, bst.height());
    	 bst.put(1, 20);
    	 assertEquals("Checking to see correct height output for tree with only root", 0, bst.height());
    	 bst.put(4, 40);
    	 bst.put(2, 10);
    	 bst.put(3, 30);
    	 
    	 assertEquals("Checking to see correct height output for tree with 4 elements", 3, bst.height());
    	 bst.put(7, 70);  
    	 bst.put(6, 60);    	 
    	 bst.put(5, 50);
    	 assertEquals("Checking to see correct height output for tree with 7 elements", 4, bst.height());  
    	 
    	 BST<Integer, Integer> bst1 = new BST<Integer, Integer>();
    	 bst.put(1, 1);
    	 bst.put(2, 2);
    	 bst.put(3, 3);
    	 bst.put(4, 4);
    	 bst.put(5, 5);
    	 assertEquals("Checking to see correct height output for tree with 5 elements", 4, bst.height());
    	 
     }
     @Test
     public void testMedian() {
    	 BST<Integer, Integer> bst = new BST<Integer, Integer>();
    	 assertNull("Checking to see if null is returned", bst.median());
    	 bst.put(5, 50);
    	 bst.put(7, 70);     	 
    	 bst.put(6, 60);    	 
    	 bst.put(4, 40);
    	 bst.put(1, 10);
    	 bst.put(2, 20);
    	 bst.put(3, 30);
    	 assertEquals("Checking to see correct median output", "4", bst.median().toString()); 

    	 BST<Integer, Integer> bst1 = new BST<Integer, Integer>();
    	 assertNull("Checking to see if null is returned", bst1.median());
    	 bst1.put(2, 60); 
    	 bst1.put(14, 40);
    	 bst1.put(9, 70);     	    	 
    	 bst1.put(10, 50);
    	 bst1.put(1, 10);

    	 assertEquals("Checking to see correct median output", "9", bst1.median().toString()); 
     }
     @Test
     public void testIsEmpty() {
    	 BST<Integer, Integer> bst = new BST<Integer, Integer>();  
    	 assertTrue("Checking to see if true is returned", bst.isEmpty());
    	 bst.put(5, 50);
    	 bst.put(7, 70);     	 
    	 bst.put(6, 60);    	 
    	 bst.put(4, 40);
    	 assertFalse("Checking to see if false is returned", bst.isEmpty());
     }
     @Test
     public void testSize() {
    	 BST<Integer, Integer> bst = new BST<Integer, Integer>();
    	 assertEquals("Checking to see if null result is returned", 0, bst.size());
    	 bst.put(7, 70);  
    	 bst.put(6, 60);    	 
    	 bst.put(5, 50);
    	 assertEquals("Checking to see correct size output", 3, bst.size());
     }
     @Test 
     public void testContains() {
    	 BST<Integer, Integer> bst = new BST<Integer, Integer>();
    	 bst.put(5, 50);
    	 bst.put(7, 70);     	 
    	 bst.put(6, 60);    	 
    	 bst.put(4, 40);
    	 bst.put(1, 10);
    	 bst.put(2, 20);
    	 bst.put(3, 30);
    	 
    	 assertTrue("Checking for true output when search for key: 7", bst.contains(7));
    	 assertFalse("Checking for false output when serach for key: 20 ", bst.contains(20));
     }
     @Test 
     public void testGet() {
    	 BST<Integer, Integer> bst = new BST<Integer, Integer>();
    	 bst.put(5, 50);
    	 bst.put(7, 70);     	 
    	 bst.put(6, 60);    	 
    	 bst.put(4, 40);
    	 bst.put(1, 10);
    	 bst.put(2, 20);
    	 bst.put(3, 30); 
    	 
    	 assertEquals("Checking for correct value output", "70", bst.get(7).toString());
    	 
    	 assertNull("Checking for correct null output", bst.get(9));
     }
     @Test
     public void testPut() {
    	 BST<Integer, Integer> bst = new BST<Integer, Integer>();
    	 bst.put(5, 50);
    	 bst.put(6, 70);     	 
    	 bst.put(6, 60);    	 
    	 bst.put(4, 40);
    	 bst.put(1, 10);
    	 bst.put(2, null);
    	 bst.put(3, 30);
    	 
    	 assertEquals("Checking for correct value output", "30", bst.get(3).toString());
    	 
    	 assertNull("Checking for correct null output", bst.get(10));
     }
     @Test
     public void testRank() {
    	 BST<Integer, Integer> bst = new BST<Integer, Integer>();
    	 assertEquals("Checking for correct output", 0, bst.rank(3));
     }
     
}
