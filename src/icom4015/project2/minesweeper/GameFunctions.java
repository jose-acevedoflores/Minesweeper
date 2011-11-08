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
	
	/**
	 * Creates a game functions object that sets the playing area size (playPanel) and the 
	 * mineGenarator used to place the mines in random locations by the setGameReady method. 
	 * @param ft the frontTile array
	 * @param mg the mineGenerator 
	 */
	public GameFunctions()
	{
		mineGenerator =  new MineGenerator();
		frontTiles = new Tile[9][9];
	
		playPanelFront = new JPanel(new GridLayout(9,9));
		playPanelFront.setBorder(new BevelBorder(BevelBorder.LOWERED));
		playPanelFront.setMaximumSize(new Dimension(465, 465));
		
		playPanelBelow = new JPanel(new GridLayout(9,9));
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
		for(int i = 0 ; i < 9 ; i++)
		{
			for(int j = 0; j < 9; j++)
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
		
		for(int i = 0; i < 9; i++)
		{
			for(int j = 0; j < 9; j++)
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
	
}
