package introToProgramming;

import java.util.Random;

public class C4RandomAIPlayer extends ConnectPlayer 
{
	char compPiece;
	
	public C4RandomAIPlayer (char piece)
	{
		this.compPiece = piece;
	}
	
	@Override
	public int columnToPick()
	{
		Random generator = new Random();
		int column = generator.nextInt(7);
		return column;
	}

	@Override
	public char getPiece()
	{
		return this.compPiece;
	}
}
