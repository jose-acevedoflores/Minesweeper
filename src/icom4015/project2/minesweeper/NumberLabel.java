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
public class NumberLabel {

	private JLabel numLabel;
	private ImageIcon image;
	
	
	public NumberLabel()
	{
		image = new ImageIcon("images/mine.png");
		Icon img = (Icon) image;
		
		numLabel = new JLabel(img);
	}
	
	
	/**
	 * Gets the label associated with this tile.
	 * @return the label
	 */
	public JLabel getLabel()
	{
		return numLabel;
	}
	
}
