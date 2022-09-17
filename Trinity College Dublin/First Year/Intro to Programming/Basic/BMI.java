package introToProgramming;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
public class BMI {

	public static void main(String[] args) {

		Scanner Input = new Scanner (System.in);

		System.out.print ("What is your height in metres? ");
		double height = Input.nextDouble();

		System.out.print ("What is your weight in kgs? ");
		double weight = Input.nextDouble();

		double BMI = weight / (height * height); 

		System.out.println ("Your BMI is " + BMI );

		Input.close();

	}


}
