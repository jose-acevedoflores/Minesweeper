package icom4015.project2.minesweeper;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

public class GameFunctions {
	
	private Tile[] frontTiles;
	private MineGenerator mineGenerator;
	private JPanel playPanelFront;
	private JPanel playPanelBelow;
	
	/**
	 * Creates a game functions object that sets the playing area size (playPanel) and the 
	 * mineGenarator used to place the mines in random locations by the setGameReady method. 
	 * @param ft the frontTile array
	 * @param mg the mineGenerator 
	 */
	public GameFunctions( MineGenerator mg)
	{
		mineGenerator = mg;
		frontTiles = new Tile[81];
	
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
		for(int i = 0 ; i < frontTiles.length ; i++)
		{
			if(mineGenerator.getBombLocations()[c] == i)
			{
				 labelUnderTile = new LabelUnderTile(true);	
				 c++;
				 if(c==10)//Reset c so we don't get out of bounds.
					 c=0;
			}
			else
				labelUnderTile = new LabelUnderTile(false);
			
			frontTiles[i] = new Tile(labelUnderTile);
		}
		
		for(int a = 0; a < frontTiles.length; a++)
		{
			playPanelFront.add(frontTiles[a].getFrontTileLabel());
			playPanelBelow.add(frontTiles[a].getUnderTileLabel());
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
