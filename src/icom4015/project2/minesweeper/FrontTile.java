package icom4015.project2.minesweeper;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * This class keeps the button position and the corresponding label behind it.
 * @author 
 *
 */
public class FrontTile {
	

	private FrontTile frontTile;
	private LabelUnderTile labelUnderTile;
	
	/**
	 * Creates the panel that contains the front tile and the corresponding label below it.
	 * It also adds the mouseListeners 
	 */
	public FrontTile(LabelUnderTile t)
	{
		labelUnderTile = t;
		
	}

	/**
	 * Gets the label associated with this position.
	 * @return the label
	 */
	public JLabel getUnderTileLabel()
	{
		return labelUnderTile.getLabel();
	}
	
}
