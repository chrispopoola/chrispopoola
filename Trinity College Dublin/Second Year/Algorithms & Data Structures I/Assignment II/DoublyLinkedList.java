import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.Scanner;

// -------------------------------------------------------------------------
/**
 *  This class contains the methods of Doubly Linked List.
 *
 *  @author  
 *  @version 09/10/18 11:13:22
 */


/**
 * Class DoublyLinkedList: implements a *generic* Doubly Linked List.
 * @param <T> This is a type parameter. T is used as a class name in the
 * definition of this class.
 *
 * When creating a new DoublyLinkedList, T should be instantiated with an
 * actual class name that extends the class Comparable.
 * Such classes include String and Integer.
 *
 * For example to create a new DoublyLinkedList class containing String data: 
 *    DoublyLinkedList<String> myStringList = new DoublyLinkedList<String>();
 *
 * The class offers a toString() method which returns a comma-separated sting of
 * all elements in the data structure.
 * 
 * This is a bare minimum class you would need to completely implement.
 * You can add additional methods to support your code. Each method will need
 * to be tested by your jUnit tests -- for simplicity in jUnit testing
 * introduce only public methods.
 */
class DoublyLinkedList<T extends Comparable<T>>
{
	private int size = 0;

	/**
	 * private class DLLNode: implements a *generic* Doubly Linked List node.
	 */
	private class DLLNode
	{
		public final T data; // this field should never be updated. It gets its
		// value once from the constructor DLLNode.
		public DLLNode next;
		public DLLNode prev;


		/**
		 * Constructor
		 * @param theData : data of type T, to be stored in the node
		 * @param prevNode : the previous Node in the Doubly Linked List
		 * @param nextNode : the next Node in the Doubly Linked List
		 * @return DLLNode
		 */
		public DLLNode(T theData, DLLNode prevNode, DLLNode nextNode) 
		{
			data = theData;
			prev = prevNode;
			next = nextNode;
		}
	}

	// Fields head and tail point to the first and last nodes of the list.
	private DLLNode head, tail;

	/**
	 * Constructor of an empty DLL
	 * @return DoublyLinkedList
	 */
	public DoublyLinkedList() 
	{
		head = null;
		tail = null;

	}

	/**
	 * Tests if the doubly linked list is empty
	 * @return true if list is empty, and false otherwise
	 *
	 * Worst-case asymptotic running time cost: Theta(1)
	 *
	 * Justification:
	 *  This method only carries out basic operations (assignment, variable declaration, which have a 
	 *  worst-case asymptotic running time cost of Theta(1)	 
	 *  */
	public boolean isEmpty()
	{
		if (head == null)  
		{
			size = 0;
			return true;
		}
		return false;
	}

	public int size()
	{
		return size;
	}

	/**
	 * Inserts an element in the doubly linked list
	 * @param pos : The integer location at which the new data should be
	 *      inserted in the list. We assume that the first position in the list
	 *      is 0 (zero). If pos is less than 0 then add to the head of the list.
	 *      If pos is greater or equal to the size of the list then add the
	 *      element at the end of the list.
	 * @param data : The new data of class T that needs to be added to the list
	 * @return none
	 *
	 * Worst-case asymptotic running time cost: Theta(N)
	 *
	 * Justification:
	 *  This method only carries out basic operations (assignment, variable declaration, which have a 
	 *  worst-case asymptotic running time cost of Theta(1), the method will then run as many as N times
	 *  thus making the worst-case asymptotic running time cost Theta(N)
	 */
	public void insertBefore(int pos, T data) 
	{		
		DLLNode current, previous;

		if (isEmpty())
		{
			head = tail = new DLLNode(data, null, null);

		}
		else
			if (pos <= 0)
			{
				head.prev = new DLLNode(data, null, head);
				head = head.prev;
			}
			else if (pos > size()-1)
			{
				tail.next = new DLLNode(data, tail, null);
				tail = tail.next;
			}
			else
			{
				current = head;
				for(int i = 0; i < pos; i++)
				{
					current = current.next;
				}
				previous = current.prev;
				DLLNode node = new DLLNode(data, null, null);
				node.next = current;
				node.prev = previous;
				previous.next = node;
				current.prev = node;
			}

		size++;
	}

	/**
	 * Returns the data stored at a particular position
	 * @param pos : the position
	 * @return the data at pos, if pos is within the bounds of the list, and null otherwise.
	 *
	 * Worst-case asymptotic running time cost: Theta(N)
	 *
	 * Justification:
	 *  This method only carries out basic operations (assignment, variable declaration, which have a 
	 *  worst-case asymptotic running time cost of Theta(1), the method will then run as many as N times
	 *  thus making the worst-case asymptotic running time cost Theta(N)
	 *
	 */
	public T get(int pos) 
	{
		DLLNode current = null;
		if (isEmpty() || pos > size()-1 || pos < 0)
		{
			return null;
		}

		else if (pos <= size()/2)
		{
			current = head;
			for (int i = 0; i < pos; i++)
			{
				current = current.next;	
			}
			return current.data;
		}
		else 
		{
			current = tail;
			for (int i = size-1; i > pos; i--)
			{
				current = current.prev;	
			}
			return current.data;
		}

	}

	/**
	 * Deletes the element of the list at position pos.
	 * First element in the list has position 0. If pos points outside the
	 * elements of the list then no modification happens to the list.
	 * @param pos : the position to delete in the list.
	 * @return true : on successful deletion, false : list has not been modified. 
	 *
	 * Worst-case asymptotic running time cost: Theta(N)
	 *
	 * Justification:
	 *  This method only carries out basic operations (assignment, variable declaration, which have a 
	 *  worst-case asymptotic running time cost of Theta(1), the method will then run as many as N times
	 *  thus making the worst-case asymptotic running time cost Theta(N)
	 *  The method also iterates through two individual for loops which would each have a running cost of N
	 */
	public boolean deleteAt(int pos) 
	{

		if (isEmpty() || pos < 0 || pos > size-1)
		{
			return false;
		}
		else if (pos == size()-1)
		{
			if (size()==1)
			{
				head = tail = null;
				size--;
				return true;
			}
			else {
				tail = tail.prev;
				tail.next = null;
				size--;
				return true;
			}
		}
		else if (pos == 0)
		{

			if (size()==1)
			{
				head = tail = null;
				size--;
				return true;
			}
			else {
				head = head.next;
				head.prev = null;
				size--;
				return true;
			}
		}
		else
		{
			DLLNode current = null;
			if (pos <= size()/2)
			{
				current = head;
				for (int i = 0; i != pos; i++)
				{
					current = current.next;	
				}
				current.next.prev = current.prev;
				current.prev.next = current.next;

			}
			else
			{
				current = tail;
				for (int i = size()-1; i != pos; i--)
				{
					current = current.prev;	
				}
				current.next.prev = current.prev;
				current.prev.next = current.next;
			}
			size--;
			return true;
		}
	}

	/**
	 * Reverses the list.
	 * If the list contains "A", "B", "C", "D" before the method is called
	 * Then it should contain "D", "C", "B", "A" after it returns.
	 *
	 * Worst-case asymptotic running time cost: Theta(N)
	 *
	 * Justification:
	 *  This method only carries out basic operations (assignment, variable declaration, which have a 
	 *  worst-case asymptotic running time cost of Theta(1), the method will then run as many as N times
	 *  thus making the worst-case asymptotic running time cost Theta(N)
	 */
	public void reverse()
	{
		DLLNode placeHolder = null;
		tail = head;
		DLLNode current = head;

		while (current != null)
		{
			// swap prev & next
			placeHolder = current.prev;
			current.prev = current.next;
			current.next = placeHolder;
			current = current.prev; // next node
		}
		if (placeHolder != null)
			head = placeHolder.prev;
	}

	/**
	 * Removes all duplicate elements from the list.
	 * The method should remove the _least_number_ of elements to make all elements uniqueue.
	 * If the list contains "A", "B", "C", "B", "D", "A" before the method is called
	 * Then it should contain "A", "B", "C", "D" after it returns.
	 * The relative order of elements in the resulting list should be the same as the starting list.
	 *
	 * Worst-case asymptotic running time cost: Theta(N^2)
	 *
	 * Justification:
	 *  This method only carries out basic operations (assignment, variable declaration, which have a 
	 *  worst-case asymptotic running time cost of Theta(1).
	 *  It also runs through a double for loop which would iterate though N*N, = N^2 times, so the overall 
	 *  worst-case asymptotic running time cost
	 */
	public void makeUnique()
	{
		DLLNode current, index;
		int count;

		if(!isEmpty())
		{
			for (current = head; current != null; current = current.next)
			{
				count = 0;
				int i = 0;
				for (index = head; index !=null; index = index.next)
				{

					if (current.data == index.data)
						count++;
					if (count > 1)
					{
						deleteAt(i);
					}
					i++;
				}
			}
		}
	}


	/*----------------------- STACK API 
	 * If only the push and pop methods are called the data structure should behave like a stack.
	 */

	/**
	 * This method adds an element to the data structure.
	 * How exactly this will be represented in the Doubly Linked List is up to the programmer.
	 * @param item : the item to push on the stack
	 *
	 * Worst-case asymptotic running time cost: Theta(1)
	 *
	 * Justification:
	 *  This method only carries out basic operations (assignment, variable declaration, which have a 
	 *  worst-case asymptotic running time cost of Theta(1).
	 */
	public void push(T item) 
	{

		if (isEmpty()) 
		{
			head = tail = new DLLNode(item, null, null);
		}
		else
		{
			head.prev = new DLLNode(item, null, head);
			head = head.prev;
		}
	}

	/**
	 * This method returns and removes the element that was most recently added by the push method.
	 * @return the last item inserted with a push; or null when the list is empty.
	 *
	 * Worst-case asymptotic running time cost: Theta(1)
	 *
	 *  This method only carries out basic operations (assignment, variable declaration, which have a 
	 *  worst-case asymptotic running time cost of Theta(1).
	 */
	public T pop() 
	{
		if (!isEmpty())
		{
			T item = head.data;
			head = head.next;
			return item;
		}
		else
			return null;
	}

	/*----------------------- QUEUE API
	 * If only the enqueue and dequeue methods are called the data structure should behave like a FIFO queue.
	 */

	/**
	 * This method adds an element to the data structure.
	 * How exactly this will be represented in the Doubly Linked List is up to the programmer.
	 * @param item : the item to be enqueued to the stack
	 *
	 * Worst-case asymptotic running time cost: Theta(1)
	 *
	 * Justification:
	 *  This method only carries out basic operations (assignment, variable declaration, which have a 
	 *  worst-case asymptotic running time cost of Theta(1).
	 */
	public void enqueue(T item) 
	{
		if (isEmpty())
		{
			head = tail = new DLLNode(item, null, null);
		}
		else
		{
			tail.next = new DLLNode(item, tail, null);
			tail = tail.next;
		}
	}

	/**
	 * This method returns and removes the element that was least recently added by the enqueue method.
	 * @return the earliest item inserted with an equeue; or null when the list is empty.
	 *
	 * Worst-case asymptotic running time cost: Theta(1)
	 *
	 * Justification:
	 *  This method only carries out basic operations (assignment, variable declaration, which have a 
	 *  worst-case asymptotic running time cost of Theta(1).
	 */
	public T dequeue() 
	{
		if (!isEmpty())
		{
			T item = head.data;
			head = head.next;
			return item;
		}
		else
			return null;
	}


	/**
	 * @return a string with the elements of the list as a comma-separated
	 * list, from beginning to end
	 *
	 * Worst-case asymptotic running time cost:   Theta(n)
	 *
	 * Justification:
	 *  We know from the Java documentation that StringBuilder's append() method runs in Theta(1) asymptotic time.
	 *  We assume all other method calls here (e.g., the iterator methods above, and the toString method) will execute in Theta(1) time.
	 *  Thus, every one iteration of the for-loop will have cost Theta(1).
	 *  Suppose the doubly-linked list has 'n' elements.
	 *  The for-loop will always iterate over all n elements of the list, and therefore the total cost of this method will be n*Theta(1) = Theta(n).
	 */
	public String toString() 
	{
		StringBuilder s = new StringBuilder();
		boolean isFirst = true; 

		// iterate over the list, starting from the head
		for (DLLNode iter = head; iter != null; iter = iter.next)
		{
			if (!isFirst)
			{
				s.append(",");
			} else {
				isFirst = false;
			}
			s.append(iter.data.toString());
		}
		return s.toString();
	}

}


