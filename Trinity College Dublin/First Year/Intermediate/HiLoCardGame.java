/* SELF ASSESSMENT
   1. Did I use appropriate CONSTANTS instead of numbers within the code?
       Mark out of 5:	5
       Comment: Yes, I declared most numbers (excluding 0) as a CONSTANT.
   2. Did I use easy-to-understand, meaningful CONSTANT names formatted correctly in UPPERCASE?
       Mark out of 5:	5
       Comment: Yes, I used full upper-case letters for the names of my CONSTANTS.
   3. Did I use easy-to-understand meaningful variable names?
       Mark out of 10:	10
       Comment: Yes, I do indeed believe my variable names are meaningful and easy to understand
   4. Did I format the variable names properly (in lowerCamelCase)?
       Mark out of 5:	5
       Comment: Yes, I uSeD lOwErCaMeLcAsE in my variable names.
   5. Did I indent the code appropriately?
       Mark out of 10:	10
       Comment: Yes, I used proper indentation in my code.
   6. Did I use an appropriate loop to allow the user to enter their guesses until they win or lose?
       Mark out of 20: 	20
       Comment: Yes, I used a do-while loop.
   7. Did I check the input to ensure that invalid input was handled appropriately?
       Mark out of 10: 	10
       Comment: Yes, (although as a personal preference, I opted to give the user another chance if their input is invalid, 
				this is mainly due to the possibility of misspelling of answers due to haste in typing, which is just regular 
				human error).
   8. Did I generate the cards properly using random number generation (assuming all cards are equally likely each time)?
       Mark out of 10: 	10
       Comment: Yes, I imported java.util.Random and used two random number generators.
   9. Did I output the cards correctly as 2, 3, 4, ... 9, 10, Jack, Queen, King?
       Mark out of 10: 	10
       Comment: Yes, (also you forgot to mention Ace).
   10. Did I report whether the user won or lost the game before the program finished?
       Mark out of 10:	10
       Comment: Yes, indeed I did.
   11. How well did I complete this self-assessment?
       Mark out of 5:	5
       Comment: I would say I completed the self-assessment pretty well if I do say so myself (weird, I guess I'm self-assessing my self-assessment).
   Total Mark out of 100 (Add all the previous marks):	100
 */
package introToProgramming;

import java.util.Random;
import java.util.Scanner;

public class HiLoCardGame {

	public static void main(String[] args) {

		Scanner Input = new Scanner (System.in);	

		final int CARD_MAX_VALUE = 13;
		String userGuessAttempt;
		boolean youHaveLost = false;

		final String USER_PICKED_HIGHER = "higher";
		final String USER_PICKED_EQUAL = "equal";
		final String USER_PICKED_LOWER = "lower";


		Random generatorCurrentCard = new Random();
		int currentCard = generatorCurrentCard.nextInt(CARD_MAX_VALUE)+1;

		String firstCard = Integer.toString(currentCard);

		if (firstCard.contains("11"))
			firstCard = "Jack";
		
		else if (firstCard.contains("12"))
			firstCard = "Queen";
		
		else if (firstCard.contains("13"))
			firstCard = "King";
		
		else if (firstCard.contains("1"))
			firstCard = "Ace";
		

		System.out.print("The card is a " + firstCard + ". ");
		System.out.print("Do you think the next card is higher, lower or equal to this card. ");
		int numberOfSuccesses = 0;

		do {
			userGuessAttempt = Input.next();

			Random generatorNewCard = new Random();
			int newCard = generatorNewCard.nextInt(CARD_MAX_VALUE)+1;

			String computerOutput = Integer.toString(newCard);

			if (computerOutput.contains("11")) 
				computerOutput = "Jack";
			
			else if (computerOutput.contains("12")) 
				computerOutput = "Queen";
			
			else if (computerOutput.contains("13")) 
				computerOutput = "King";
			
			else if (computerOutput.contains("1")) 
				computerOutput = "Ace";
			

			if (userGuessAttempt.contains(USER_PICKED_HIGHER) && newCard > currentCard) {
				System.out.print("You were right. Have another go. The card is a " + computerOutput + ". " );
				System.out.print("Do you think the next card is higher, lower or equal to this card. ");
				numberOfSuccesses++;
				currentCard = newCard;
			}
			else if (userGuessAttempt.contains(USER_PICKED_HIGHER) && newCard < currentCard) {
				System.out.print("You have guessed incorrectly and have thus lost the game. The Card is "+ computerOutput +".");
				youHaveLost = true;
			}
			else if (userGuessAttempt.contains(USER_PICKED_LOWER) && newCard < currentCard) {
				System.out.print("You were right. Have another go. The card is a " + computerOutput + ". " );
				System.out.print("Do you think the next card is higher, lower or equal to this card. ");
				numberOfSuccesses++;
				currentCard = newCard;
			}
			else if (userGuessAttempt.contains(USER_PICKED_LOWER) && newCard > currentCard) {
				System.out.print("You have guessed incorrectly and have thus lost the game. ");
				youHaveLost = true;
			}
			else if (userGuessAttempt.contains(USER_PICKED_EQUAL) && newCard == currentCard) {
				System.out.print("You were right. Have another go. The card is a " + computerOutput + ". " );
				System.out.print("Do you think the next card is higher, lower or equal to this card. ");
				numberOfSuccesses++;
				currentCard = newCard;
			}
			else if (userGuessAttempt.contains(USER_PICKED_EQUAL) && newCard != currentCard) {
				System.out.print("You have guessed incorrectly and have thus lost the game. ");
				youHaveLost = true;
			}
			else  {
				System.out.print("Error, Invalid Input. Have another go. The card is a " + computerOutput + ". "); 
				numberOfSuccesses = 0;
				currentCard = newCard;
			}
		}
		while (numberOfSuccesses < 4 || youHaveLost == false); {}

		if (numberOfSuccesses == 4) 
			System.out.println("Congratulations, You have guessed correctly 4 times in a row, therefore you have won the game. ");
		else {}
		
		Input.close();
	}
}
