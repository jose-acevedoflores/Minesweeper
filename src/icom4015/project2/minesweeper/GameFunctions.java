package icom4015.project2.minesweeper;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

public class GameFunctions {
	
	private static Tile[][] frontTiles;
	private MineGenerator mineGenerator;
	private JPanel playPanelFront;
	private JPanel playPanelBelow;
	private static int gameSize = 9;
	protected static boolean lost=false;
	private static boolean[][] streak;

	
	/**
	 * Creates a game functions object that sets the playing area size (playPanel) and the 
	 * mineGenarator used to place the mines in random locations by the setGameReady method. 
	 * @param ft the frontTile array
	 * @param mg the mineGenerator 
	 */
	public GameFunctions()
	{
		
		mineGenerator =  new MineGenerator();
		frontTiles = new Tile[gameSize][gameSize];
		streak =  new boolean[gameSize+2][gameSize+2];
		num = new int[gameSize][gameSize][1];
	
		playPanelFront = new JPanel(new GridLayout(gameSize,gameSize));
		playPanelFront.setBorder(new BevelBorder(BevelBorder.LOWERED));
	//	playPanelFront.setMaximumSize(new Dimension(900, 900));
		playPanelFront.setMaximumSize(new Dimension(465, 465));
		
		playPanelBelow = new JPanel(new GridLayout(gameSize,gameSize));
		playPanelBelow.setBorder(new BevelBorder(BevelBorder.LOWERED));
		//playPanelBelow.setMaximumSize(new Dimension(900, 900));
		playPanelBelow.setMaximumSize(new Dimension(465, 465));
	}

	/**
	 * Set the bomb at the locations the were created using the MineGenerator.
	 */
	public void setGameReady()
	{
		
	
		LabelUnderTile labelUnderTile;
		int c=0;
		int d=0;//This parameter (d) is introduce because to represent the bomb location in a linear way.
				//For example position 12 linearly corresponds to [1][3] and it's easier to work with. 
		
		//In this loop we create the labels that go under the tiles and we determine if the label should be a bomb.
		for(int i = 0 ; i < gameSize ; i++)
		{
			for(int j = 0; j < gameSize; j++)
			{
				if(mineGenerator.getBombLocations()[c] == d)
				{
					labelUnderTile = new LabelUnderTile(true);	
					c++;
					if(c==10)//Reset c so we don't get out of bounds.
						c=0;
				}
				else
					labelUnderTile = new LabelUnderTile(false);

				d++;
				frontTiles[i][j] = new Tile(labelUnderTile, i, j);
			}
		}
		
		/*-----------------------------------------------------------------*/
		/* In this code segment we introduce an integer array that controls the loop that sets the number of bombs near a given tile.
		   It's bigger by one column at the right and one at the left and one row on the top and one row on the bottom.
		   This extra rows are filled with -2. This represents an illegal position because the index
		 	for the frontTiles array would be out of bounds and there is no tile at that position.
		 */
		int[][] biggerGrid = new int[gameSize+2][gameSize+2];
		
			for(int j =0; j < gameSize+2; j++)
			{
				biggerGrid[0][j]=-2;
			}
			for(int j =0; j < gameSize+2; j++)
			{
				biggerGrid[gameSize+1][j]=-2;
			}
		
			for(int i =0; i < gameSize+2; i++)
			{
				biggerGrid[i][0]=-2;
			}
			for(int i =0; i < gameSize+2; i++)
			{
				biggerGrid[i][gameSize+1]=-2;
			}
	
		
		/*--------------------------------------------------------------------*/
		
		//In this loop we fill the labels near the bombs with numbers.
		for(int i = 0 ; i < gameSize + 1; i++)
		{
			for(int j = 0 ; j < gameSize + 1  ; j++)
			{
				int bombsNear=0;
				
				if(biggerGrid[i][j]!=-2) // If the tile we are checking is not an empty tile then-> 
				{
					
					if(!frontTiles[i-1][j-1].checkBomb()) // -> Check if this tile doesn't contain a bomb.
					{										
						//The -1 in the index is to get the equivalent position for the fronTiles array  given the indexes of the biggerGrid array.
						
						for(int a = i-1; a < i+2 ; a++) // In here we go from one row before up to one row below the tile we are at. 
						{
							for(int b = j-1 ; b < j+2 ; b++)
							{
								if(biggerGrid[a][b]!=-2) // If that tile is not on an illegal zone then we can check if there is a bomb there
								{
									if(frontTiles[a-1][b-1].checkBomb())
									{
										bombsNear++;
									}
								}
							}
						}
						frontTiles[i-1][j-1].setUnderTileNumber(bombsNear);
					}//end if
				}
			}
		}
		

		GameFunctions.setEmptyStreak();
		
		//Adding the tiles to the panels.
		for(int i = 0; i < gameSize; i++)
		{
			for(int j = 0; j < gameSize; j++)
			{
				playPanelFront.add(frontTiles[i][j].getFrontTileLabel());
				playPanelBelow.add(frontTiles[i][j].getUnderTileLabel());				
			}
		}
	}
	
	/**
	 * Gets the playing panel with all the labels and tiles set.
	 * @return the playPanel 
	 */
	public JPanel getPlayPanel()
	{
		return playPanelFront;
	}
	
	/**
	 * Gets the panel under the playing panel, where the bombs and number labels are found.
	 * @return
	 */
	public JPanel getUnderPanel()
	{
		return playPanelBelow;
	}
	
	
	/**
	 * Sets the empty streaks for a game. By empty streak I mean when a tile that has a zero under it is clicked all the tiles that also contain 
	 * zero are freed up until the ones that have a number.
	 */
	private static void setEmptyStreak()
	{
		
		int streakSize = gameSize+1;
		int d = 0 ; 
		for(int i = 1 ; i < streakSize ; i++)
		{
			for(int j = 1 ; j < streakSize ; j++)
			{
				if(frontTiles[i-1][j-1].getUnderTileLabelNumber() == 0)
				{
					streak[i][j] = true;
					
				}
				num[i-1][j-1][0] = d;
				d++;
			}
		}
		
	}
	
	/**
	 * This methods check's if a given number n is present in an ArrayList.
	 * @param n the number to look in the arrayList.
	 * @param uncovered the arraylist of uncovered tiles so far.
	 * @return
	 */
	public static boolean isNumberInArrayList(int n , ArrayList<Integer> uncovered)
	{
		for(int i = 0 ; i < uncovered.size(); i++)
		{
			if(n == uncovered.get(i))
				return true;
		}

		return false;
	}
	
	
	
	/**
	 * Reveals the empty streaks associated with the tile pressed. 
	 */
	public static void revealEmptyStreak(int row , int column)
	{
		setStreak(row+1,column+1);
	}
	
	
	/**
	 * 
	 * @param iPrime
	 * @param jPrime
	 * @param streakSize
	 * @param currentBlock
	 */
	public static void setStreak(int iPrime, int jPrime)
	{
		
		for(int i = iPrime-1; i < iPrime+2; i++)
		{
			for(int j = jPrime-1 ; j < jPrime+2; j++)
			{
				
				if(streak[i][j] && !GameFunctions.isNumberInArrayList(num[i-1][j-1][0], zeroesUncovered))
				{
					frontTiles[i-1][j-1].setFrontTileLabel();
					zeroesUncovered.add(num[i-1][j-1][0]);
					
					for(int a = i-2; a < i+1; a++)
					{	
						for(int b = j-2; b < j+1; b++)
						{
							if(a>=0 && a < gameSize && b>=0 && b < gameSize)
							{
								if(frontTiles[a][b].getUnderTileLabelNumber() != 0 
										&&frontTiles[a][b].getUnderTileLabelNumber() != -1 
										&& !GameFunctions.isNumberInArrayList(num[a][b][0], numsUncovered))
								{
									frontTiles[a][b].setFrontTileLabel();
									numsUncovered.add(num[a][b][0]);
								}
							}
						}
					}
					
					setStreak(i,j);
				}

			}
		}
		return;
	}
	
	private static int[][][] num;
	private static ArrayList<Integer> zeroesUncovered = new ArrayList<Integer>();
	private static ArrayList<Integer> numsUncovered = new ArrayList<Integer>();
	
	
}
