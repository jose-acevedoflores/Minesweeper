package icom4015.project2.minesweeper;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

public class GameFunctions {
	
	private FrontTile[] frontTiles;
	private MineGenerator mineGenerator;
	private JPanel playPanel;
	
	/**
	 * Creates a game functions object that sets the playing area size (playPanel) and the 
	 * mineGenarator used to place the mines in random locations by the setGameReady method. 
	 * @param ft the frontTile array
	 * @param mg the mineGenerator 
	 */
	public GameFunctions( MineGenerator mg)
	{
		mineGenerator = mg;
		frontTiles = new FrontTile[81];
	
		playPanel = new JPanel(new GridLayout(9,9));
		playPanel.setBorder(new BevelBorder(BevelBorder.LOWERED));
		playPanel.setMaximumSize(new Dimension(465, 465));
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
			
			frontTiles[i] = new FrontTile(labelUnderTile);
		}
		
		for(int a = 0; a < frontTiles.length; a++)
		{
			playPanel.add(frontTiles[a].getUnderTileLabel());
		}
	}
	
	
	public JPanel getPlayPanel()
	{
		return playPanel;
	}
	
}
