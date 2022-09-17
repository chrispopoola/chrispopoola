package introToProgramming;

import java.util.Scanner;

public class C4HumanPlayer extends ConnectPlayer {
	
	public static final char GAME_WIDTH = 7;
	char playerPiece;
	static Scanner Input = new Scanner(System.in);
	
	public C4HumanPlayer (char piece) {
		this.playerPiece = piece;
	}
	
	@ Override
	public int columnToPick() {
		
		boolean validColumnPicked = true;
		int column = 0;
		
		while (validColumnPicked) {
			
			System.out.print("Enter a move for " + this.getPiece() + ". Remember you must enter a column number between 0-6. ");
			
			if (Input.hasNextInt()) {
				int columnPicked = Input.nextInt();
				if (columnPicked >= 0 && columnPicked <= 6) {
					validColumnPicked = true;
					column = columnPicked;
					return column;
				}
				else 
					System.out.print("Invalid column number picked. Enter an column number between 0-6.");	
			}
			else 
				System.out.print("Invalid Entry. Enter an integer value between 0-6.");
		}
		return column;
	}
	
	@Override
	public char getPiece() {
		return this.playerPiece;
	}	
}
