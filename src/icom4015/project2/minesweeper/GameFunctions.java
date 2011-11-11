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
		streak =  new boolean[gameSize][gameSize];
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
		
		/*--------------------------------------------------------------------*/
		
		//In this loop we fill the labels near the bombs with numbers.
		for(int i = 0 ; i < gameSize ; i++)
		{
			for(int j = 0 ; j < gameSize  ; j++)
			{
				int bombsNear=0;
				
				if(i>=0 && i < gameSize && j >=0 && j < gameSize) // If the tile we are checking is not an empty tile then-> 
				{
					
					if(!frontTiles[i][j].checkBomb()) // -> Check if this tile doesn't contain a bomb.
					{										
						
						for(int a = i-1; a < i+2 ; a++) // In here we go from one row before up to one row below the tile we are at. 
						{
							for(int b = j-1 ; b < j+2 ; b++)
							{
								if(a>=0 && a < gameSize && b >=0 && b < gameSize) // If that tile is not on an illegal zone then we can check if there is a bomb there
								{
									if(frontTiles[a][b].checkBomb())
									{
										bombsNear++;
									}
								}
							}
						}
						frontTiles[i][j].setUnderTileNumber(bombsNear);
					}//end if
				}
			}
		}
		
		/*--------------------------------------------------------------------*/

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
	
		int d = 0 ; 
		for(int i = 0 ; i < gameSize ; i++)
		{
			for(int j = 0 ; j < gameSize ; j++)
			{
				if(frontTiles[i][j].getUnderTileLabelNumber() == 0)
				{
					streak[i][j] = true;
					
				}
				num[i][j][0] = d;
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

		for(int i = row-1; i < row+2; i++)
		{
			for(int j = column-1 ; j < column+2; j++)
			{
				if(i>=0 && i < gameSize && j>=0 && j < gameSize)
				{	
					if(streak[i][j] && !GameFunctions.isNumberInArrayList(num[i][j][0], zeroesUncovered))
					{
						frontTiles[i][j].setFrontTileLabel();
						zeroesUncovered.add(num[i][j][0]);
						
			/*----------------------------------------------------------*/
						
						for(int a = i-1; a < i+2; a++)
						{	
							for(int b = j-1; b < j+2; b++)
							{
								if(a>=0 && a < gameSize && b>=0 && b < gameSize)
								{
									if(		frontTiles[a][b].getUnderTileLabelNumber()!=0
											&& frontTiles[a][b].getUnderTileLabelNumber() != -1
											&& !GameFunctions.isNumberInArrayList(num[i][j][0], numsUncovered))
									{
										frontTiles[a][b].setFrontTileLabel();
										numsUncovered.add(num[a][b][0]);
									}
								}
							}
						}
			/*----------------------------------------------------------*/
						revealEmptyStreak(i,j);	
					}
				}//Index controling if

			}
		}
		return;
	}
	
	
	private static int[][][] num;
	private static ArrayList<Integer> zeroesUncovered = new ArrayList<Integer>();
	private static ArrayList<Integer> numsUncovered = new ArrayList<Integer>();
	
	
}
