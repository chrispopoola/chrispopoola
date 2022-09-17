package introToProgramming;
import java.util.Scanner;


import java.lang.Math;
public class StandardDeviation {

	public static void main(String[] args) 
	{
		Scanner Input = new Scanner (System.in);

		System.out.print("Enter an integer");
		double num1 = Input.nextDouble();

		System.out.print("Enter an integer");
		double num2 = Input.nextDouble();

		System.out.print("Enter an integer");
		double num3 = Input.nextDouble();

		double average = (num1 + num2 + num3)/3; 


		double deviation1 = ((num1 - average) * (num1 - average))/ 3;
		deviation1 = Math.sqrt(deviation1);

		double deviation2 = ((num2 - average) * (num2 - average))/ 3;
		deviation2 = Math.sqrt(deviation2);

		double deviation3 = ((num3 - average) * (num3 - average))/ 3;
		deviation3 = Math.sqrt(deviation3);

		double standardDeviation = (deviation1 + deviation2 + deviation3)/3;

		System.out.println("Your average is " + average);
		System.out.println("Your standard deviation is " + standardDeviation);

		Input.close();



	}

}