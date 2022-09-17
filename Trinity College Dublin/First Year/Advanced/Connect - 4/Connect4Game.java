/* SELF ASSESSMENT

Connect4Game class (35 marks)
My class creates references to the Connect 4 Grid and two Connect 4 Players. It asks the user whether he/she would like to play/quit inside a loop. If the user decides to play then: 1. Connect4Grid2DArray is created using the Connect4Grid interface, 2. the two players are initialised - must specify the type to be ConnectPlayer, and 3. the game starts. In the game, I ask the user where he/she would like to drop the piece. I perform checks by calling methods in the Connect4Grid interface. Finally a check is performed to determine a win. 
Comment:	35 Yeah

Connect4Grid interface (10 marks)
I define all 7 methods within this interface.
Comment:	10 Yeah

Connect4Grid2DArray class (25 marks) 
My class implements the Connect4Grid interface. It creates a grid using a 2D array Implementation of the method to check whether the column to drop the piece is valid. It provides as implementation of the method to check whether the column to drop the piece is full. It provides as implementation of the method to drop the piece.  It provides as implementation of the method to check whether there is a win.
Comment:	25 Yeah

ConnectPlayer abstract class (10 marks)
My class provides at lest one non-abstract method and at least one abstract method. 
Comment:	10 Yeah

C4HumanPlayer class (10 marks)
My class extends the ConnectPlayer claas and overrides the abstract method(s). It provides the Human player functionality.
Comment:	10 Yeah

C4RandomAIPlayer class (10 marks)
My class extends the ConnectPlayer claas and overrides the abstract method(s). It provides AI player functionality. 
Comment:	10 Yeah

Total Marks out of 100:	100

*/
package introToProgramming;

import java.util.Scanner;

public class Connect4Game
{
	public static final char PLAYER_ONE_PIECE = '+';
	public static final char PLAYER_TWO_PIECE = 'o';
	static ConnectPlayer PlayerOne;
	static ConnectPlayer PlayerTwo;

	static Scanner Input = new Scanner (System.in);
	static Connect4Grid2DArray game = new Connect4Grid2DArray();


	public static void main (String [] args)
	{
		boolean finished = false;

		while (!finished)
		{
			game.emptyGrid();
			String playerOneAnswer = "";
			String playerTwoAnswer = "";

			while (!playerOneAnswer.equalsIgnoreCase("human") && !playerOneAnswer.equalsIgnoreCase("AI") && !playerOneAnswer.equalsIgnoreCase("quit"))
			{
				System.out.print("Please enter what you would like Player One to be, 'Human' or 'AI' player or you can 'quit'. ");
				playerOneAnswer = Input.next();
				
				if (!playerOneAnswer.equalsIgnoreCase("human") && !playerOneAnswer.equalsIgnoreCase("AI") && !playerOneAnswer.equalsIgnoreCase("quit"))
					System.out.println("Invalid input. Please enter either 'Human', 'AI', or 'quit'. \n");
				else if (playerOneAnswer.equalsIgnoreCase("human"))
					PlayerOne = new C4HumanPlayer (PLAYER_ONE_PIECE);
				else if (playerOneAnswer.equalsIgnoreCase("AI"))
					PlayerOne = new C4RandomAIPlayer (PLAYER_ONE_PIECE);
				else if (playerOneAnswer.equalsIgnoreCase("quit"))
					finished = true;
			}
			while (!playerTwoAnswer.equalsIgnoreCase("human") && !playerTwoAnswer.equalsIgnoreCase("AI") && !playerTwoAnswer.equalsIgnoreCase("quit") && !playerOneAnswer.equalsIgnoreCase("quit"))
			{
				System.out.print("Please enter what you would like Player Two to be, 'Human' or 'AI' player or you can 'quit'. ");
				playerTwoAnswer = Input.next();

				if (!playerTwoAnswer.equalsIgnoreCase("human") && !playerTwoAnswer.equalsIgnoreCase("AI") && !playerTwoAnswer.equalsIgnoreCase("quit"))
					System.out.println("Invalid input. Please enter either 'Human', 'AI', or 'quit'. \n");
				else if (playerTwoAnswer.equalsIgnoreCase("human"))
					PlayerTwo = new C4HumanPlayer (PLAYER_TWO_PIECE);
				else if (playerTwoAnswer.equalsIgnoreCase("AI"))
					PlayerTwo = new C4RandomAIPlayer (PLAYER_TWO_PIECE);
				else if (playerTwoAnswer.equalsIgnoreCase("quit"))
					finished = true;
			}

			if (!finished)
			{
				System.out.print(game.toString());
				while (!game.didLastPieceConnect4() && !game.isGridFull())
				{
					boolean validColumn = false;

					while (!validColumn)
					{
						int P1Column = PlayerOne.columnToPick();
						if (game.isValidColumn(P1Column))
						{
							validColumn = true;
							game.dropPiece(PlayerOne, P1Column);
							System.out.print("\nPlayer One places piece: " + PlayerOne.getPiece() + " in column " + P1Column + ".");
						}
						else
							System.out.print("Invalid column. Try again. ");
					}
					
					System.out.print(game.toString());
					validColumn = false;
					
					if (!game.didLastPieceConnect4())
					{
						while (!validColumn)
						{
							int P2Column = PlayerTwo.columnToPick();
							
							if (game.isValidColumn(P2Column))
							{
								validColumn = true;
								game.dropPiece(PlayerTwo, P2Column);
								System.out.print("\nPlayer Two places piece: " + PlayerTwo.getPiece() + " in column " + P2Column + ".");
								
								if (game.didLastPieceConnect4())
									System.out.print("\nCongratulations, Player Two has won! \n");
							}
							else
								System.out.print("Invalid column. Try again. ");
						}
						System.out.print(game.toString());
					}
					else
						System.out.print("\nCongratulations, Player One has won! \n");
					if (game.isGridFull() && !game.didLastPieceConnect4())
						System.out.print("\nHard luck, the game was a draw. \n");
				}
			}
			else if (finished)
				System.out.print("\nThanks for playing. \n");
		}
	}
}
