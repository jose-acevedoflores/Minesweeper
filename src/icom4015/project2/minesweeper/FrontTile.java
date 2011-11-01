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
	
	private JLabel[] frontTiles = new JLabel[81];
	private JPanel labelPanel;
	private JPanel frontPanel;
	
	/**
	 * Creates the panel that contains the front tile and the corresponding label below it.
	 * It also adds the mouseListeners 
	 */
	public FrontTile(JLabel[] labelsUnderTiles)
	{
		labelPanel = new JPanel(new GridLayout(9,9));
		
		for(int i = 0; i < 81 ; i++)
		{
			labelPanel.add(labelsUnderTiles[i]);
		}
	}

	/**
	 * Gets the playing area.
	 * @return the play area.
	 */
	public JPanel getPlayPanel()
	{
		return labelPanel;
	}
	
}
