package introToProgramming;
/* SELF ASSESSMENT
   1. Did I use appropriate easy-to-understand, meaningful variables and CONSTANTS within the code? 
       Mark out of 10: 10
       Comment: Yes, yes I did
   2. Did I format the variable and CONSTANT names appropriate (in lowerCamelCase and UPPERCASE)?
       Mark out of 5: 5
       Comment: Yes, yes I did
   3. Did I generate the computer's choice in each game correctly using a Random number generator?
       Mark out of 10: 10
       Comment: Yes, yes I did
   4. Did I input the user's choice in each game correctly?
       Mark out of 10: 10
       Comment: Yes, yes I did
   5. Did I correctly compare the choices and update the score appropriately?
       Mark out of 20: 20
       Comment: Yes, yes I did
   6. Did I inform the user of who won each game (and why) correctly?
       Mark out of 10: 10
       Comment: Yes, yes I did
   7. Did I use an appropriate for loop to allow the player to play 5 games?  There should be only one loop.
       Mark out of 20: 20
       Comment: Yes, yes I did
   8. Did I output the final scores correctly after the 5 games were played. 
       Mark out of 10: 10
       Comment: Yes, yes I did
   9. How well did I complete this self-assessment?
       Mark out of 5: 5
       Comment: Pretty amazingly if I do say so myself
   Total Mark out of 100 (Add all the previous marks): 100
 */
import java.util.Scanner;
import java.util.Random;

public class RockPaperScissors {

	public static void main(String[] args) {
		Scanner Input = new Scanner (System.in);

		System.out.println("Input 1, 2 or 3. (Rock = 1, Paper = 2, Scissors = 3. ");

		int userInput = 0;
		int computerInput = 0;
		int userScore = 0;
		int computerScore = 0;

		final int ROCK = 1;
		final int PAPER = 2;
		final int SCISSORS = 3;
		final int MAX_NUMBER = 3;
		final int MAX_NUMBER_OF_ROUNDS = 5;

		Random  generator = new Random();

		for (int roundNumber = 0; roundNumber < MAX_NUMBER_OF_ROUNDS; roundNumber++) {
			System.out.println("Choose your object. ");
			userInput = Input.nextInt();
			computerInput = generator.nextInt(MAX_NUMBER)+1;

			if (userInput < MAX_NUMBER && userInput != 0) {

				if (computerInput == ROCK && userInput == ROCK) 
					System.out.println("We have drawn because I have chosen Rock. ");
				
				else if (computerInput == ROCK && userInput == PAPER) {
					System.out.println("You have won this round becuase I have chosen Rock. ");
					userScore++;
				}
				else if (computerInput == ROCK && userInput == SCISSORS) {
					System.out.println("You have lost this round because I have chosen Rock. ");
					computerScore++;
				}
				else if (computerInput == PAPER && userInput == ROCK) {
					System.out.println("You have lost this round because I have chosen Paper. ");
					computerScore++;
				}
				else if (computerInput == PAPER && userInput == PAPER) {
					System.out.println("We have drawn this round because I have chosen Paper. ");
				}
				else if (computerInput == PAPER && userInput == SCISSORS) {
					System.out.println("You have won this round because I have chosen Paper. ");
					userScore++;
				}
				else if (computerInput == SCISSORS && userInput == ROCK) {
					System.out.println("You have won this round because I have chosen Paper. ");
					userScore++;
				}
				else if (computerInput == SCISSORS && userInput == PAPER) {
					System.out.println("You have lost this round because I have chosen Paper. ");
					computerScore++;
				}
				else if (computerInput == SCISSORS && userInput == SCISSORS) 
					System.out.println("We have drawn this round because I have chosen Paper. ");
				
			}
			else
				System.out.println("Invalid Input. You have lost a turn. "); 
			

			System.out.println("The final scores are: User: " + userScore + " Computer: " + computerScore + ".");
		}
		Input.close();

	}
}
