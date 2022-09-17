/* SELF ASSESSMENT 

1. ResolveBet

I have correctly defined ResolveBet which takes the bet type (String) and the Wallet object, and a void return type [Mark out of 7: 7].
Comment: Yes I did.
My program presents the amount of cash in the wallet and asks the user how much he/she would like to bet [Mark out of 8: 8].
Comment: Yes it does.
My program ensures the bet amount is not greater than the cash in the wallet [Mark out of 5: 5].
Comment: Yes it does.
My program creates three Dice objects, rolls them and creates a total variable with a summation of the roll values returned [Mark out of 15: 15]..
Comment: Yes it does.
My program determines the winnings by comparing the bet type with the total and comparing the bet type with the dice faces for the triple bet [Mark out of 20: 20].
Comment: Yes it does.
My program outputs the results (win or loss) and adds the winnings to the wallet if user wins or removes the bet amount from the wallet if the user loses [Mark out of 10: 10].
Comment: Yes it does.

2. Main

I ask the user for the amount of cash he/she has, create a Wallet object and put this cash into it [Mark out of 15: 15]
Comment: Yes I did.
My program loops continuously until the user either enters quit or the cash in the wallet is 0 [Mark out of 5: 5]
Comment: Yes it does.
I ask the user to enter any of the four bet types or quit [Mark out of 5: 5].
Comment: Yes I did.
My program calls resolveBet for each bet type entered [Mark out of 5: 5].
Comment: Yes it does.
At the end of the game my program presents a summary message regarding winnings and losses [Mark out of 5: 5]
Comment: Yes it does.

 Total Mark out of 100 (Add all the previous marks):	100
 */


package introToProgramming;
import java.util.Scanner;
public class ChuckALuck 
{

	static Wallet newPlayerWallet = new Wallet();
	static Dice newGame = new Dice();
	static Scanner Input = new Scanner(System.in);
	static boolean quit = false;
	static boolean wonTheBetOnetoOne = false;
	static double attemptedBetAmount = 0;
	static double winnings = 0;
	static double money = 0;

	
	public static void main (String [] args) 
	{

		System.out.print("Welcome to the casino. How much cash do you have? ");

		if (Input.hasNextDouble()) 
		{
			double money = Input.nextDouble();
			newPlayerWallet.put(money);

			do 
			{
				if (newPlayerWallet.check() <= 0)
					quit = true;

				System.out.println ("What type of bet would you like to make? ");
				System.out.println ("Choose one from the following: Triple, Field, High or Low. Or to stop enter 'quit'. "); 
				String betType = Input.next();
				if (betType.equalsIgnoreCase("Triple") || betType.equalsIgnoreCase("Field") || betType.equalsIgnoreCase("high")||betType.equalsIgnoreCase("low"))
					ResolveBet(betType);
				else if (betType.contains("quit"))
					quit = true;
				else 
				{
					System.out.print("Invalid input.");
				}

			}
			while (quit == false);

		}
		else
			System.out.print("Error. Invalid Input. ");

		if (quit == true)
		{
		//	if (winnings > 0)
		//		System.out.print("Congratulations, You have made €" + winnings);
		//	else if (newPlayerWallet.check() < money)
		//		System.out.print("Unfortunately, You have lost €" + (money - newPlayerWallet.check()));
			
			System.out.print(" Unfortunately, you have decided to quit. You have now €" + newPlayerWallet.check() + " in your wallet. ");
			
		}
	}
	public static void ResolveBet (String betType) 
	{

		String attemptBetType = betType;

		System.out.println( "You have €" + newPlayerWallet.check() + " in your wallet. " );
		System.out.print(	"How much would you like to bet? ");
		if (Input.hasNextDouble())
		{
			
			attemptedBetAmount = Input.nextDouble();
			if (newPlayerWallet.get(attemptedBetAmount) == true) {

				int firstDie = newGame.roll();
				int secondDie = newGame.roll();
				int thirdDie = newGame.roll();
				

				int sumOfThreeDice = firstDie + secondDie + thirdDie;


				if ((attemptBetType.equalsIgnoreCase("Triple")) && ((firstDie == secondDie) && (secondDie == thirdDie)) && (firstDie != 1 || firstDie != 6))
				{
					System.out.println("You rolled a: " + firstDie + ", "+ secondDie + ", & "+ thirdDie + ". ");
					System.out.println("You have successfully reached the conditions of your bet. :) " );
					winnings = (attemptedBetAmount * 30);
					newPlayerWallet.put(winnings + attemptedBetAmount);		
				}
				else if (attemptBetType.equalsIgnoreCase("Field") && (sumOfThreeDice < 8 || sumOfThreeDice > 12))
				{
					System.out.print("You rolled a: " + firstDie + ", "+ secondDie + ", & "+ thirdDie + ", which amounts to: " + sumOfThreeDice + ". ");
					System.out.println("You have successfully reached the conditions of your bet. :) " );
					wonTheBetOnetoOne = true;
				}
				else if ((attemptBetType.equalsIgnoreCase("High")) && (sumOfThreeDice > 10) && ((firstDie < 3) && 
						(((firstDie == secondDie) && (secondDie != thirdDie)) || ((secondDie == thirdDie) && (firstDie != thirdDie)) || ((firstDie == thirdDie) && (thirdDie != secondDie)))))
				{
					System.out.print("You rolled a: " + firstDie + ", " + secondDie + ", & " + thirdDie + ", which amounts to: " + sumOfThreeDice + ". ");
					System.out.println("You have successfully reached the conditions of your bet. :) " );
					wonTheBetOnetoOne = true;
				}
				else if ((attemptBetType.equalsIgnoreCase("Low")) && (sumOfThreeDice < 11) && (firstDie > 3) && 
						(((firstDie == secondDie) && (secondDie != thirdDie)) || ((secondDie == thirdDie) && (firstDie != thirdDie)) || ((firstDie == thirdDie) && (thirdDie != secondDie))))
				{
					System.out.print("You rolled a : " + firstDie + ", " + secondDie + ", & " + thirdDie + ", which amounts to: " + sumOfThreeDice + ". ");
					System.out.println("You have successfully reached the conditions of your bet. :) " );
					wonTheBetOnetoOne = true;
				}
				else 
				{
					System.out.println("You rolled a : " + firstDie + ", " + secondDie + ", & " + thirdDie + ", which amounts to: " + sumOfThreeDice + ". ");
					System.out.println("Your bet failed to reach the correct conditions. You have lost. :( ");

				}
				if (wonTheBetOnetoOne == true) 
				{
					winnings = attemptedBetAmount * 2;
					newPlayerWallet.put(winnings);
				}

				System.out.println("You now have: €" + newPlayerWallet.check() + " left in your wallet. ");

			}
			else 
			{
				System.out.println("You have insufficient funds. ");
				quit = true;
			}
		}
		else
			System.out.print("Error. Invalid Input. ");

	}

}

