/* SELF ASSESSMENT 
   1.  createSequence:
Did I use the correct method definition?
Mark out of 5: 3
Comment: I believe I did use the method definition accurately but I made some sort of error which meant method functions overlapped.
Did I create an array of size n (passed as the parameter) and initialise it?
Mark out of 5: 5
Comment: Yes indeed I did.
Did I return the correct item?
Mark out of 5: 5
Comment: Yes indeed I did.
   2.  crossOutMultiples
Did I use the correct method definition?
Mark out of 5: 3
Comment:
Did I ensure the parameters are not null and one of them is a valid index into the array
Mark out of 2: 2
Comment: Yes indeed I did
Did I loop through the array using the correct multiple?
Mark out of 5: 5
Comment: Yes indeed I did
Did I cross out correct items in the array that were not already crossed out?
Mark out of 3: 3
Comment: Yes indeed I did
   3.  sieve   
Did I have the correct function definition?
Mark out of 5: 0
Comment: N/A
Did I make calls to other methods?
Mark out of 5: 0
Comment: N/A
Did I return an array with all non-prime numbers are crossed out?
Mark out of 2: 0
Comment: N/A
   4.  sequenceTostring  
Did I have the correct function definition?
Mark out of 5: 5
Comment: Yes indeed I did
Did I ensure the parameter to be used is not null?
Mark out of 3: 3
Comment: Yes indeed I did
Did I Loop through the array updating the String variable with the non-crossed out numbers and the crossed numbers in brackets? 
Mark out of 10:	10
Comment:    Yes indeed.
   5.  nonCrossedOutSubseqToString  
Did I have the correct function definition
Mark out of 5:	5
Comment: Yes indeed.
Did I ensure the parameter to be used is not null?  
Mark out of 3: 0
Comment: 
Did I loop through the array updating the String variable with just the non-crossed out numbers? 
Mark out of 5: 5
Comment: Yes indeed.
   6.  main  
Did I ask  the user for input n and handles input errors?  
Mark out of 5:	0
Comments:
Did I make calls to other methods (at least one)?
Mark out of 5:	5
Comment:  Yes indeed.
Did I print the output as shown in the question?  
Mark out of 5:	5
Comment: Yes indeed.
   7.  Overall
Is my code indented correctly?
Mark out of 4:	4
Comments: Yes indeed.
Do my variable names make sense?
Mark out of 4:	4
Comments: Yes indeed.
Do my variable names, method names and class name follow the Java coding standard
Mark out of 4:	4
Comments: Yes indeed.
      Total Mark out of 100 (Add all the previous marks): 76 
*/
package introToProgramming;
import java.util.Scanner;


import java.lang.Math;

public class SieveOfEratosthenes {

	static Scanner Input = new Scanner(System.in);
	static double N;
	static int firstPrimeNumber = 2;

	public static int [] createSequence (double N) 
	{
		int [] listOfAllNumbers = new int[(int) (N-1)];

		if (N >= firstPrimeNumber) 
		{
			for (int i = 0; i < N-1 ; i++ ) 
			{
				listOfAllNumbers [i] = firstPrimeNumber++;
			}
		}
		else 
		{
			System.out.print("Error. Enter an integer >= 2. ");
		}
		return listOfAllNumbers;
	}

	public static boolean [] crossOutHigherMultiples (int[] listOfNumbers) 
	{
		int [] listOfAllNumbers = new int [listOfNumbers.length];
		System.arraycopy(listOfNumbers, 0, listOfAllNumbers, 0, listOfNumbers.length);

		double squareRootOfN = Math.sqrt(N);

		boolean [] testArray = new boolean [listOfAllNumbers.length];
		for (int t = 0; t < listOfAllNumbers.length; t++) 
		{
			testArray [t] = true;
		}

		for (int i = listOfAllNumbers.length-1; i > 0; i--) 
		{
			for (int j = 0; listOfAllNumbers[j] <= squareRootOfN; j++) {

				if (listOfAllNumbers[i] % listOfAllNumbers[j] == 0 && listOfAllNumbers[i] != listOfAllNumbers[j] )
					testArray[i] = false;
			}
		}
		return testArray;
	}

	public static String [] sequenceToString (boolean[] testArray, int[] listOfNumbers) 
	{
		String stringArrayOfAllNumbers [] = new String[listOfNumbers.length];

		for (int k = 0; k < listOfNumbers.length; k++) 
		{
			stringArrayOfAllNumbers[k] = String.valueOf(listOfNumbers[k]);
		}

		for (int k = 0; k < testArray.length; k++) 
		{
			if (testArray[k] == false)
			{
				stringArrayOfAllNumbers [k] = "[" + listOfNumbers[k] + "]";
			}
		}
		return stringArrayOfAllNumbers; 
	}
	
	public static String [] nonCrossedOutSubseqToString (boolean[] booleanListOfNumbers, String [] StrListOfNumbers) 
	{
		int count = 0;
		String temporaryStringArray [] = new String [StrListOfNumbers.length];
		System.arraycopy(StrListOfNumbers, 0, temporaryStringArray, 0, StrListOfNumbers.length);

		for (int j = 0; j < booleanListOfNumbers.length; j++) 
		{
			if (booleanListOfNumbers[j] == true) 
			{
				count++;
			}
		}
		count = temporaryStringArray.length - count;

		for (int i = 0; i < booleanListOfNumbers.length; i++ )
		{
			if (booleanListOfNumbers[i] == true) 
			{
				System.out.print ((i <= count) ? temporaryStringArray[i] + ", " : temporaryStringArray[i] + ". ");
			}
		}

		return temporaryStringArray;
	}
	
	public static void main (String[] args) 
	{
		System.out.print("Enter an integer >= 2. ");
		N = Input.nextDouble();

		int [] listOfNumbers = createSequence (N);
		for (int i = 0; i < N-1; i++ )
		{
			System.out.print ( (i < N-2) ? listOfNumbers[i] + ", " : listOfNumbers[i] + ". " + "\n");
		}
		
		boolean[] booleanListOfNumbers = crossOutHigherMultiples (listOfNumbers);

		String [] StrListOfNumbers = sequenceToString (booleanListOfNumbers, listOfNumbers);
		
		for (int i = 0; i < N-1; i++ )
		{
			System.out.print ((i < N-2) ? StrListOfNumbers[i] + ", " : StrListOfNumbers[i] + ". " + "\n");
		}

		nonCrossedOutSubseqToString (booleanListOfNumbers, StrListOfNumbers);

		Input.close();
	}

	

}
