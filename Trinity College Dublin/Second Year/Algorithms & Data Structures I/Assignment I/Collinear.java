// -------------------------------------------------------------------------
/**
 *  This class contains only two static methods that search for points on the
 *  same line in three arrays of integers. 
 *
 *  @author  
 *  @version 18/09/18 12:21:09
 */
class Collinear
{

	public static void main(String[] args)
	{
		In input3 = new In("r02000-3.txt");
		In input2 = new In("r02000-2.txt");
		In input1 = new In("r02000-1.txt");
		
		int[] a3 = input3.readAllInts();
		int[] a2 = input2.readAllInts();
		int[] a1 = input1.readAllInts();

		Stopwatch stopwatch = new Stopwatch();

		StdOut.println("Number of collinear points: " + Collinear.countCollinear(a3, a2, a1));

		double time = stopwatch.elapsedTime();
		StdOut.println("Elapsed time: " + time);
	}

	/*
	countCollinear();
	
		N = 1000; Number of collinear points = 41,582; Elapsed time = 0.369
		
		N = 2000; Number of collinear points = 4,035; Elapsed time = 3.34
		
		N = 4000; Number of collinear points = 31,783; Elapsed time = 24.697
		
		Estimate of N = 5000:
		
		log5000 (base 2) = 11.9658; slope from graph = 3.032; y-intercept = -31.609
		
			Formula: T(N) = (2^b) * N^a 
				b = y-intercept (@ x = 0) = -31.609 // data taken from plotted graph
				a = slope = 3.032 // data taken from plotted graph
			(2^-31.609) * 5000^3.032 = 50.121 = T(N) estimate
		
		Actual result: N = 5000; Number of collinear points = 61,322; Elapsed time = 50.006
		
		Percentage error of prediction:
		Error = ((Actual time)-(Predicted time)) * 100 / (Predicted time)
		((50.006)-(50.121))*100 / (50.121) = -0.229 = 2.29%
		
	countCollinearFast();
		
		N = 1000; Number of collinear points = 41,582; Elapsed time = 0.073
		
		N = 2000; Number of collinear points = 4,035; Elapsed time = 0.247
		
		N = 4000; Number of collinear points = 31,783; Elapsed time = 0.778
		
		N = 5000; Number of collinear points = 61,322; Elapsed time = 1.349
		
		Speedup = (time of countCollinear)/(time of countCollinearFast)
		
		N = 1000; Speedup = 0.369/0.073 = 5.05
		N = 2000; Speedup = 3.34/0.247 = 13.52
		N = 4000: Speedup = 24.697/0.778 = 31.74
		N = 5000; Speedup = 50.006/1.349 = 37.07
	/*
	// ----------------------------------------------------------
	/**
	 * Counts for the number of non-hoizontal lines that go through 3 points in arrays a1, a2, a3.
	 * This method is static, thus it can be called as Collinear.countCollinear(a1,a2,a3)
	 * @param a1: An UNSORTED array of integers. Each integer a1[i] represents the point (a1[i], 1) on the plain.
	 * @param a2: An UNSORTED array of integers. Each integer a2[i] represents the point (a2[i], 2) on the plain.
	 * @param a3: An UNSORTED array of integers. Each integer a3[i] represents the point (a3[i], 3) on the plain.
	 * @return the number of points which are collinear and do not lie on a horizontal line.
	 *
	 * Array a1, a2 and a3 contain points on the horizontal line y=1, y=2 and y=3, respectively.
	 * A non-horizontal line will have to cross all three of these lines. Thus
	 * we are looking for 3 points, each in a1, a2, a3 which lie on the same
	 * line.
	 *
	 * Three points (x1, y1), (x2, y2), (x3, y3) are collinear (i.e., they are on the same line) if
	 * 
	 * x1(y2−y3)+x2(y3−y1)+x3(y1−y2)=0 
	 *
	 * In our case y1=1, y2=2, y3=3.
	 *
	 * You should implement this using a BRUTE FORCE approach (check all possible combinations of numbers from a1, a2, a3)
	 *
	 * ----------------------------------------------------------
	 *
	 * 
	 *  Order of Growth
	 *  -------------------------
	 *
	 *  Caclulate and write down the order of growth of your algorithm. You can use the asymptotic notation.
	 *  You should adequately explain your answer. Answers without adequate explanation will not be counted.
	 *
	 *  Order of growth: N^3
	 *
	 *  Explanation: 3 linear for-loops (triple loop)
	 */
	static int countCollinear(int[] a1, int[] a2, int[] a3)
	{
		int count = 0;
		int y1 = 1, y2 = 2, y3 =3;

		for (int i = 0; i < a1.length; i++)
		{
			for (int j = 0; j < a2.length; j++)
			{
				for (int k = 0; k < a3.length; k++)
				{

					if (((a1[i] * (y2 - y3)) + (a2[j] * (y3 - y1)) + (a3[k] * (y1 - y2))) == 0)
					{
						count++;
					}
				}
			}
		}
		return count;
	}

	// ----------------------------------------------------------
	/**
	 * Counts for the number of non-hoizontal lines that go through 3 points in arrays a1, a2, a3.
	 * This method is static, thus it can be called as Collinear.countCollinearFast(a1,a2,a3)
	 * @param a1: An UNSORTED array of integers. Each integer a1[i] represents the point (a1[i], 1) on the plain.
	 * @param a2: An UNSORTED array of integers. Each integer a2[i] represents the point (a2[i], 2) on the plain.
	 * @param a3: An UNSORTED array of integers. Each integer a3[i] represents the point (a3[i], 3) on the plain.
	 * @return the number of points which are collinear and do not lie on a horizontal line.
	 *
	 * In this implementation you should make non-trivial use of InsertionSort and Binary Search.
	 * The performance of this method should be much better than that of the above method.
	 *
	 *
	 *  Order of Growth
	 *  -------------------------
	 *
	 *  Caclulate and write down the order of growth of your algorithm. You can use the asymptotic notation.
	 *  You should adequately explain your answer. Answers without adequate explanation will not be counted.
	 *
	 *  Order of Growth: N^2 // (* logN) ignore lower powered
	 *
	 *  Explanation: two linear for-loops (double loop) & divide in half (binary search)
	 *
	 *
	 */
	static int countCollinearFast(int[] a1, int[] a2, int[] a3)
	{
		// x1*(y2−y3) + x2*(y3−y1) + x3*(y1−y2)=0          
		// y1 = 1; y2 = 2; y3 = 3;
		// y2-y3 = -1; y3-y1 = 2; y1-y2 = -1;
		// x1*(-1) + x2*(2) + x3*(-1)=0     =         x1 = (2 * x2) - x3

		Collinear.sort(a1);
		int count = 0;

		for (int i = 0; i < a2.length; i++)
		{
			for (int j = 0; j < a3.length; j++)
			{
				int number = ((2 * a2[i]) - a3[j]);
				if (Collinear.binarySearch(a1, number))
					count++;
			}
		}
		return count;
	}

	// ----------------------------------------------------------
	/**
	 * Sorts an array of integers according to InsertionSort.
	 * This method is static, thus it can be called as Collinear.sort(a)
	 * @param a: An UNSORTED array of integers. 
	 * @return after the method returns, the array must be in ascending sorted order.
	 *
	 * ----------------------------------------------------------
	 *
	 *  Order of Growth
	 *  -------------------------
	 *
	 *  Caclulate and write down the order of growth of your algorithm. You can use the asymptotic notation.
	 *  You should adequately explain your answer. Answers without adequate explanation will not be counted.
	 *
	 *  Order of Growth: N^2
	 *
	 *  Explanation: Two linear for-loops.
	 *
	 */
	static void sort(int[] a)
	{
		for ( int j = 1; j<a.length; j++)
		{
			int i = j - 1;
			while(i>=0 && a[i]>a[i+1])
			{
				int temp = a[i];
				a[i] = a[i+1];
				a[i+1] = temp;
				i--;
			}
		}
	}

	// ----------------------------------------------------------
	/**
	 * Searches for an integer inside an array of integers.
	 * This method is static, thus it can be called as Collinear.binarySearch(a,x)
	 * @param a: A array of integers SORTED in ascending order.
	 * @param x: An integer.
	 * @return true if 'x' is contained in 'a'; false otherwise.
	 *
	 * ----------------------------------------------------------
	 *
	 *  Order of Growth
	 *  -------------------------
	 *
	 *  Caclulate and write down the order of growth of your algorithm. You can use the asymptotic notation.
	 *  You should adequately explain your answer. Answers without adequate explanation will not be counted.
	 *
	 *  Order of Growth: logN
	 *
	 *  Explanation: divides in half, then works through array upwards or downwards
	 *
	 */
	static boolean binarySearch(int[] a, int x)
	{
		int lo = 0, hi = a.length-1;
		while (lo <= hi)
		{
			int mid = lo + (hi - lo) / 2;

			if (x < a[mid]) hi = mid - 1;
			else if (x > a[mid]) lo = mid + 1;
			else return true;
		}
		return false;
	}

}
