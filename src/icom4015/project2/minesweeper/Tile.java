package icom4015.project2.minesweeper;

import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * This class keeps the button position and the corresponding label behind it.
 * @author 
 *
 */
public class Tile {
	

	private ImageIcon image;
	private JLabel frontTile;
	private LabelUnderTile labelUnderTile;
	
	/**
	 * Creates the panel that contains the front tile and the corresponding label below it.
	 * It also adds the mouseListeners 
	 */
	public Tile(LabelUnderTile t)
	{
		labelUnderTile = t;
		image = new ImageIcon("images/tile.png");
		frontTile = new JLabel(image);
	}

	/**
	 * Gets the under label (bomb, number or nothing) associated with this position.
	 * @return the label
	 */
	public JLabel getUnderTileLabel()
	{
		return labelUnderTile.getLabel();
	}
	
	/**
	 * Gets the front label (tile, flag or ? sign) associated with this position.
	 * @return the label
	 */
	public JLabel getFrontTileLabel()
	{
		return frontTile;
	}
	
}
