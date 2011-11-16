package Minesweeper;

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
	private int numberHere;
	
	/**
	 * Creates the labels that goes under the front tiles
	 * @param bombLocations the location of the bombs
	 */
	public LabelUnderTile(boolean bombHere)
	{
		if(bombHere)
		{
			image = new ImageIcon("images/image20x20/bomb.jpg");
			numberHere=-1;
		}
		else
		{
			numberHere=0;
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
	public void setNumber(int n)
	{
		numberHere = n;
		label = new JLabel("      "+Integer.toString(numberHere));
		label.setSize(48, 48);
	}
	
}
