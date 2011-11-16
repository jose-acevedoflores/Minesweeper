package Minesweeper;

import java.util.Random;
/**
 * Generates the mines at Random positions from 1 - row*columns. 
 *
 */
public class MineGenerator {

	private Random generator;
	private int[] bombLocations;
	private int thisManyBombs;
	
	/**
	 * Creates the random generator and generates and number that corresponds to the tile where the bomb is going to be.
	 */
	public MineGenerator(int rows, int columns, int bombNumber)
	{
		bombLocations = new int[bombNumber];
		generator = new Random();
		thisManyBombs = bombNumber;
		int possiblePlaces = rows*columns;
		int nextBomb = 0;
		
		
		for(int i = 0; i < bombLocations.length ; i++)
		{
			nextBomb =  generator.nextInt(possiblePlaces);
			
			for(int j=0; j < i; j++)
			{
				if(bombLocations[j] == nextBomb)
				{
					nextBomb =  generator.nextInt(possiblePlaces);
					//Set it to minus one so that when we find a repeated bomb 
					//the for loop will start from 0 (j++)
					j=-1; 
				}
			}
			
			bombLocations[i] = nextBomb;

		}
		
		
		//Sort the bomb positions in ascending order.
		for(int i = 0 ; i < bombLocations.length - 1; i++)
		{
			for (int c = 0; c < bombLocations.length - 1 - i; c++)
			{
				if(bombLocations[c] > bombLocations[c+1])
				{
					int temp = bombLocations[c+1];
					bombLocations[c+1] = bombLocations[c];
					bombLocations[c] = temp;
				}
			}
		}
		
	}
	
	/**
	 * Gets the array containing the bomb locations.
	 * @return the bomb locations
	 */
	public int[] getBombLocations()
	{
		return bombLocations;
	}
	
	/**
	 * Gets how many bombs are used in the current game
	 * @return the bombs in this game.
	 */
	public int getHowManyBombs()
	{
		return thisManyBombs;
	}
	
}
