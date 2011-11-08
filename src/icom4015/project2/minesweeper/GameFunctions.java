package icom4015.project2.minesweeper;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

public class GameFunctions {
	
	private Tile[][] frontTiles;
	private MineGenerator mineGenerator;
	private JPanel playPanelFront;
	private JPanel playPanelBelow;
	private int gameSize = 9;
	
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
	
		playPanelFront = new JPanel(new GridLayout(gameSize,gameSize));
		playPanelFront.setBorder(new BevelBorder(BevelBorder.LOWERED));
		playPanelFront.setMaximumSize(new Dimension(465, 465));
		
		playPanelBelow = new JPanel(new GridLayout(gameSize,gameSize));
		playPanelBelow.setBorder(new BevelBorder(BevelBorder.LOWERED));
		playPanelBelow.setMaximumSize(new Dimension(465, 465));
	}

	/**
	 * Set the bomb at the locations the were created using the MineGenerator.
	 */
	public void setGameReady()
	{
		
		int c=0;
		LabelUnderTile labelUnderTile;
		
		int d=0;//This parameter is introduce because to represent the bomb location in a linear way.
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
				frontTiles[i][j] = new Tile(labelUnderTile);
			}
		}
		
		//In this loop we fill the labels near the bombs with numbers.
		//i and j start at 1 and finish at 7 so we take the inner block (so the index -1 doesn't go out of bounds)
		for(int i = 1 ; i < gameSize-1; i++)
		{
			for(int j = 1 ; j < gameSize-1 ; j++)
			{
				int bombsNear=0;
				if(!frontTiles[i][j].checkBomb())
				{
					for(int a = i-1; a < i+2 ; a++)
					{
						for(int b = j-1 ; b < j+2 ; b++)
						{
							if(frontTiles[a][b].checkBomb())
							{
								bombsNear++;
							}
						}
					}
					frontTiles[i][j].setUnderTileNumber(bombsNear);
				}//end if
			}
		}
		
		//This loop sets the number label of the leftmost column
		for(int i=1 ; i < gameSize-1 ; i++)
		{
			
			int bombsNear=0;
			if(!frontTiles[i][0].checkBomb())
			{
				for(int a = i-1; a < i+2 ; a++)
				{
					for(int b = 0 ; b < 2 ; b++)
					{
						if(frontTiles[a][b].checkBomb())
						{
							bombsNear++;
						}
					}
				}
				frontTiles[i][0].setUnderTileNumber(bombsNear);
			}//end if

		}
		
		//This loop sets the number label of the rightmost column
		for(int i=1 ; i < gameSize-1 ; i++)
		{
			int bombsNear=0;
			if(!frontTiles[i][ gameSize-1].checkBomb())
			{
				for(int a = i-1; a < i+2 ; a++)
				{
					for(int b = gameSize-1 ; b > gameSize-3 ; b--)
					{
						if(frontTiles[a][b].checkBomb())
						{
							bombsNear++;
						}
					}
				}
				frontTiles[i][ gameSize-1].setUnderTileNumber(bombsNear);
			}//end if

		}
		
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
		return playPanelBelow;
	}
	
}
