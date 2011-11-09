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
public class Tile {
	
	private static ImageIcon flag = new ImageIcon("images/Red-Flag.jpg");
	private static ImageIcon questionMark = new ImageIcon("images/Question_mark.png");
	private static ImageIcon normalTile = new ImageIcon("images/tile.png");
	private static int bombFlags;
	private String currentTile;
	private JLabel frontTile;
	private LabelUnderTile labelUnderTile;
	
	private static boolean lost = false;
	
	
	/**
	 * Creates the panel that contains the front tile and the corresponding label below it.
	 * It also adds the mouseListeners 
	 */
	public Tile(LabelUnderTile t)
	{
		labelUnderTile = t;
		frontTile = new JLabel(normalTile);
		MouseListener m = new TileListener();
		frontTile.addMouseListener(m);
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
	
	public boolean checkBomb()
	{
		return labelUnderTile.bombHere();
	}
	
	public void setUnderTileNumber(int n)
	{
		labelUnderTile.setNumber(n);
	}
	
	
	public class TileListener implements MouseListener{


		@Override
		public void mouseClicked(MouseEvent arg0) {

			ImageIcon img;
			if(arg0.getButton() == MouseEvent.BUTTON1)
			{
				if(!currentTile.equals("flag")&& !lost)
				{
					img = (ImageIcon) getUnderTileLabel().getIcon();
					frontTile.setIcon(img);
					currentTile = "under";
					if(labelUnderTile.bombHere())
					{
						System.out.println("Boooom");
						lost=true;
					}
				}
			}

			//Right click pressed
			else if(arg0.getButton() == MouseEvent.BUTTON3)
			{	
				if(currentTile.equals("flag") && !lost)
				{
					frontTile.setIcon(questionMark);
					bombFlags--;
					currentTile = "question";
				}
				else if(currentTile.equals("question") && !lost)
				{	
					frontTile.setIcon(normalTile);
					currentTile = "normalTile";
				}
				else if(currentTile.equals("normalTile") && !lost)
				{
					frontTile.setIcon(flag);
					bombFlags++;
					currentTile = "flag";
				}
			}


		}

		//Do nothing methods.
		@Override
		public void mouseEntered(MouseEvent arg0) {}

		@Override
		public void mouseExited(MouseEvent arg0) {}

		@Override
		public void mousePressed(MouseEvent arg0) {}

		@Override
		public void mouseReleased(MouseEvent arg0) 
		{
			if(!lost)
				System.out.println("Scary face is on");
		}
	}
}
