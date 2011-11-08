package icom4015.project2.minesweeper;


import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 * This class keeps the button position and the corresponding label behind it.
 * @author 
 *
 */
public class Tile implements MouseListener{
	
	private static ImageIcon flag = new ImageIcon("images/Red-Flag.jpg");
	private static ImageIcon questionMark = new ImageIcon("images/tile2.png");
	private static ImageIcon normalTile = new ImageIcon("images/tile.png");
	private static int bombFlags;
	private String currentTile;
	private JLabel frontTile;
	private LabelUnderTile labelUnderTile;
	
	
	/**
	 * Creates the panel that contains the front tile and the corresponding label below it.
	 * It also adds the mouseListeners 
	 */
	public Tile(LabelUnderTile t)
	{
		labelUnderTile = t;
		frontTile = new JLabel(normalTile);
		frontTile.addMouseListener(this);
		currentTile = "normalTile";
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

	/**
	 * Gets the amount of flag labels on screen.
	 * @return the amount of flag labels.
	 */
	public int getFlagsOnScreen()
	{
		return bombFlags;
	}
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		ImageIcon img;
		if(arg0.getButton() == MouseEvent.BUTTON1)
		{
			if(!currentTile.equals("flag"))
			{
				img = (ImageIcon) this.getUnderTileLabel().getIcon();
				frontTile.setIcon(img);
				currentTile = "under";
			}
		}
		
		//Right click pressed
		else if(arg0.getButton() == MouseEvent.BUTTON3)
		{	
			if(currentTile.equals("flag"))
			{
				frontTile.setIcon(questionMark);
				bombFlags--;
				currentTile = "question";
			}
			else if(currentTile.equals("question"))
			{	
				frontTile.setIcon(normalTile);
				currentTile = "normalTile";
			}
			else if(currentTile.equals("normalTile") )
			{
				frontTile.setIcon(flag);
				bombFlags++;
				currentTile = "flag";
			}
		}
		
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
}
