package icom4015.project2.minesweeper;

import java.awt.Component;
import java.awt.Graphics;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 * Class designed to check adjacent labels to see how many bombs are close to then put a number.
 * @author 
 *
 */
public class LabelUnderTile {

	private JLabel[] labelUnderTiles = new JLabel[81];
	private ImageIcon image;
	
	/**
	 * Creates the labels that go under the front tiles
	 * @param bombLocations the location of the bombs
	 */
	public LabelUnderTile(int[] bombLocations)
	{
		int c = 0;
		for(int i = 0 ; i < 81 ; i++)
		{
			if(i == bombLocations[c])
			{
				image = new ImageIcon("images/mine.png");
				Icon img = (Icon) image;
				labelUnderTiles[i] = new JLabel(img);
				c++;
				if(c==10)
					c=0;
			}
			
			else
			{
				image = new ImageIcon("images/amir2.JPG");
				Icon img = (Icon) image;
				labelUnderTiles[i] = new JLabel(img);
			}
		}
	}
	
	
	/**
	 * Gets all the labels under the tiles.
	 * @return the label
	 */
	public JLabel[] getLabels()
	{
		return labelUnderTiles;
	}
	
}
