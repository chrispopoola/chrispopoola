package introToProgramming;

public class Connect4Grid2DArray implements Connect4Grid
{
	public static final int GAME_HEIGHT = 6;
	public static final int GAME_WIDTH = 7;
	public static final char EMPTY_SPACE = ' ';
	char[][] game;
	
	Connect4Grid2DArray()
	{
		game = new char[GAME_HEIGHT][GAME_WIDTH];
		emptyGrid();
	}
	
	@Override
	public String toString()
	{
		String gameString = "";
		
		for (int row = 0; row < GAME_HEIGHT; row++)
		{
			gameString = gameString + "\n|"; //puts in the borders for the columns;
			
			for (int column = 0; column < GAME_WIDTH; column++)
			{
				gameString = gameString + game[row][column];
				gameString = gameString + "|";
			}
		}
		gameString = gameString + "\n---------------";
		return gameString += "\n 0 1 2 3 4 5 6 \n";
	}
	
	@Override
	public void emptyGrid()
	{
		for (int row = 0; row < GAME_HEIGHT; row++)
		{
			for (int column = 0; column < GAME_WIDTH; column++)
			{
				game[row][column] = EMPTY_SPACE;
			}
		}
	}
	
	@Override
	public boolean isValidColumn (int column)
	{
		if (column >= 0 && column < GAME_WIDTH)
		{
			if (isColumnFull(column) == false)
				return true;
		}
		return false;
	}
	
	@Override
	public boolean isColumnFull (int column)
	{
		for (int row = 0; row < game.length; row++)
		{
			if (game[row][column] == EMPTY_SPACE)
				return false;
		}
		return true;
	}

	@Override
	public void dropPiece (ConnectPlayer player, int column)
	{
		for(int row = 0; row < GAME_HEIGHT; row++)
		{
			if (game[row][column] != EMPTY_SPACE)
			{
				game[row-1][column] = player.getPiece();
				return;
			}
		}
		game[GAME_HEIGHT-1][column] = player.getPiece();
	}
	
	@Override
	public boolean didLastPieceConnect4()
	{
		for (int row = 0; row < GAME_HEIGHT; row++)
		{
			for (int column = 0; column < GAME_WIDTH; column++)
			{
				char spaceToCheck = game[row][column];
				
				if (spaceToCheck == EMPTY_SPACE)
				{
					continue;
				}
				if (column + 3 < GAME_WIDTH && spaceToCheck == game[row][column+1] && spaceToCheck == game[row][column+2] && spaceToCheck == game[row][column+3])
					return true;
				if (row + 3 < GAME_HEIGHT)
				{
						if (spaceToCheck == game[row+1][column] && spaceToCheck == game[row+2][column] && spaceToCheck == game[row+3][column])
							return true;
						if (spaceToCheck == game[row+1][column+1] && spaceToCheck == game[row+2][column+2] && spaceToCheck == game[row+3][column+3])
							return true;
						if (spaceToCheck == game[row+1][column-1] && spaceToCheck == game[row+2][column-2] && spaceToCheck == game[row+3][column-3])
							return true;
				}
			}
		}
		return false;
	}
	
	@Override
	public boolean isGridFull ()
	{
		for (int column = 0; column < game[0].length; column++)
		{
			if (game[0][column] == EMPTY_SPACE)
				return false;
		}
		return false;
	}
		
}




















