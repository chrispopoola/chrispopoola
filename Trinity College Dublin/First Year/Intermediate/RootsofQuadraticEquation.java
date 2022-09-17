package introToProgramming;

import java.util.Scanner;
import java.lang.Math;

public class RootsofQuadraticEquation {
	
	public static void main(String[] args) {

		Scanner Input = new Scanner (System.in);
		System.out.print("Enter values for the coefficients in your second order polynomial separated by spaces, or enter quit. ");
		
		double coefficientA = 0; 
		double coefficientB = 0;
		double coefficientC = 0; 
			
		if (Input.hasNextDouble()) {
		
			String Inputs = Input.nextLine();
			Scanner Numbers = new Scanner(Inputs);
			Numbers.useDelimiter(" ");

			coefficientA = Numbers.nextDouble();
			coefficientB = Numbers.nextDouble();
			coefficientC = Numbers.nextDouble();

			Numbers.close();

			double positiveFirstLineAnswer = (-coefficientB) + Math.sqrt((Math.pow(coefficientB, 2)) - (4 * coefficientA * coefficientC));
			double positiveQuadraticAnswer = positiveFirstLineAnswer/(2 * coefficientA);

			double negativeFirstLineAnswer = (-coefficientB) - Math.sqrt((Math.pow(coefficientB, 2)) - (4 * coefficientA * coefficientC));
			double negativeQuadraticAnswer = negativeFirstLineAnswer/(2 * coefficientA);

			System.out.println("The roots of the equation are x = " + positiveQuadraticAnswer + " and x = " + negativeQuadraticAnswer);
		}
		else if (Input.hasNext("quit")) {}
			
		else 
			System.out.println("Error. Invalid Input. ");
			
	Input.close();
	
	}
}
