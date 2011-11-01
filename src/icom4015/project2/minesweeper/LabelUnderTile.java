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

	private ImageIcon image;
	private JLabel label;
	private boolean bombHere;
	private int numberHere=0;
	
	/**
	 * Creates the labels that goes under the front tiles
	 * @param bombLocations the location of the bombs
	 */
	public LabelUnderTile(boolean bombHere)
	{
		if(bombHere)
		{
			image = new ImageIcon("images/mine.png");
		}
		else
		{
			image = new ImageIcon("images/amir2.JPG");
		}
		
		this.bombHere = bombHere;
		label = new JLabel(image);
	}
	
	/**
	 * Gets the label associated with this position.
	 * @return the label
	 */
	public JLabel getLabel()
	{
		return label;
	}
	
	/**
	 * Checks if there is a bomb in this tile.
	 * @return true if there is a bomb, false otherwise.
	 */
	public boolean bombHere()
	{
		return bombHere;
	}
	
	/**
	 * Gets the number associated with this position.
	 * @return the number.
	 */
	public int getNumberHere()
	{
		return numberHere;
	}
	
	/**
	 * Set the number of bombs that surround this tile.
	 */
	private void setNumbers()
	{
		
	}
	
}
