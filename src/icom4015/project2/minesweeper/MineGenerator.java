package icom4015.project2.minesweeper;

import java.util.Random;
/**
 * Generates the mines at Random positions from 1 - 81.
 * @author 
 *
 */
public class MineGenerator {

	private Random generator;
	private int[] bombLocations = new int[10];
	
	/**
	 * Creates the random generator and generates and number that corresponds to the tile where the bomb is going to be.
	 */
	public MineGenerator()
	{
		generator = new Random();
		
		for(int i = 0; i < 10; i++)
		{
			bombLocations[i] = 1+generator.nextInt(80);
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
	
}
