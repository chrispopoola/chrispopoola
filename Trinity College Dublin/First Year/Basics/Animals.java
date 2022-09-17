package introToProgramming;
import java.util.Scanner;

public class Animals {

	public static void main(String[] args) 
	{

		Scanner Input = new Scanner (System.in);


		System.out.print("Are you cold-blooded? ");

		boolean bloodAnswer = Input.nextBoolean(); 

		if (bloodAnswer == true) 
		{

			System.out.print("Do you have scales? ");

			boolean coveringAnswer = Input.nextBoolean();

			if (coveringAnswer == true) { 

				System.out.print("Do you have fins? ");

				boolean finsAnswer = Input.nextBoolean();

				if (finsAnswer == true) {

					System.out.print("You are a fish. "); 
				}
				else {
					System.out.print("You are a reptile. ");
				}
			}

			else {
				System.out.print("You are an Amphibian. ");
			} }
		else {
			System.out.print("Do you have feathers? ");

			boolean feathersAnswer = Input.nextBoolean();

			if (feathersAnswer == true) {

				System.out.print("You are a bird. ");

			}
			else {
				System.out.print("You are a mammal. ");
			}

		}
		Input.close();
	} }

