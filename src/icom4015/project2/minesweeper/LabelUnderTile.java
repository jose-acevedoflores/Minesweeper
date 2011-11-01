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
		
		label = new JLabel(image);
	}
	
	
	public JLabel getLabel()
	{
		return label;
	}
	
}
