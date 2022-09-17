package introToProgramming;
import java.util.Scanner;

public class SquareNumbers {

	public static void main(String[] args) {

		Scanner Input = new Scanner (System.in);	
		int squaredNumbers = 0;

		System.out.print ("Enter a integer limit. ");
		int limit = Input.nextInt();

		for (int number = 0; number < limit; number++) {

			squaredNumbers = number * number;
			System.out.print("The numbers whose sqaures are less than " + limit + " are: ");
			System.out.println(Math.sqrt(squaredNumbers));
		}
		Input.close();
	}
}
