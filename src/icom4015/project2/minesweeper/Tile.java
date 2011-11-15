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
	
	private static ImageIcon flag = new ImageIcon("images/image48x48/Red-Flag.jpg");
	private static ImageIcon questionMark = new ImageIcon("images/image48x48/Question_mark.png");
	private static ImageIcon normalTile = new ImageIcon("images/image48x48/tile.png");
	private int bombFlags;
	
	private String currentTile;
	private JLabel frontTile;
	private LabelUnderTile labelUnderTile;
	private int row;
	private int column;
	
	
	/**
	 * Creates the panel that contains the front tile and the corresponding label below it.
	 * It also adds the mouseListeners 
	 */
	public Tile(LabelUnderTile t, int row, int column)
	{
		labelUnderTile = t;
		frontTile = new JLabel(normalTile);
		MouseListener m = new TileListener();
		frontTile.addMouseListener(m);
		currentTile = "normalTile";
		this.row = row;
		this.column = column;
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
	 * Gets the number associated with the tile under this front tile.
	 * @return the number in this tile.
	 */
	public int getUnderTileLabelNumber()
	{
		return labelUnderTile.getNumberHere();
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
	
	/**
	 * Checks if there is a bomb under this tile.
	 * @return true if there is a bomb, false otherwise.
	 */
	public boolean checkBomb()
	{
		return labelUnderTile.bombHere();
	}
	
	/**
	 * Sets the number under this tile.
	 * @param n the number under this tile.
	 */
	public void setUnderTileNumber(int n)
	{
		labelUnderTile.setNumber(n);
	}
	
	/**
	 * This method is used to set the front label on this tile when the user
	 * doesn't directly click on it. This happens when the cascade effect (clicking on an empty space)
	 * takes effect.  
	 */
	public void setFrontTileLabel()
	{
		
		if(labelUnderTile.getNumberHere() == 0)
		{	frontTile.setIcon(null);
			currentTile = "under";
		}
		else
		{
			ImageIcon t = new ImageIcon("images/image48x48/numbers/num"+Integer.toString(labelUnderTile.getNumberHere())+".png");
			frontTile.setIcon(t);
			currentTile = "under";

		}
	}
	
/*--------------------------------Inner class----------------------------------*/	
	/**
	 * 
	 * @author Jose L.
	 *
	 */
	public class TileListener implements MouseListener{


		@Override
		public void mouseClicked(MouseEvent arg0) {

			ImageIcon img;
			if(arg0.getButton() == MouseEvent.BUTTON1)
			{
				if(!Panels.timer.isRunning() && !Panels.gameFunctions.lost)
					Panels.setTimer(true);
				
				if(!currentTile.equals("flag")&& !Panels.gameFunctions.lost)
				{
					
					
					if(labelUnderTile.getLabel().getIcon() != null) // If it's null the under it we have a number
					{
						img = (ImageIcon) getUnderTileLabel().getIcon();//this will be the bomb icon
						frontTile.setIcon(img);
					}
					else
					{
						ImageIcon t = new ImageIcon("images/image48x48/numbers/num"+Integer.toString(labelUnderTile.getNumberHere())+".png");
						frontTile.setIcon(t);
					}
					if(labelUnderTile.getNumberHere() == 0)
					{
						Panels.gameFunctions.revealEmptyStreak(row,column);
					}
					currentTile = "under";
					if(labelUnderTile.bombHere())
					{
						System.out.println("Boooom");
						Panels.setTimer(false);
						Panels.gameFunctions.lost=true;
					}
					
				}
			}

			//Right click pressed
			else if(arg0.getButton() == MouseEvent.BUTTON3)
			{	
				if(currentTile.equals("flag") && !Panels.gameFunctions.lost)
				{
					frontTile.setIcon(questionMark);
					bombFlags--;
					Panels.setFlagsLabel(-1); //This makes the bomb counter label on the frame increase by one
					currentTile = "question";
				}
				else if(currentTile.equals("question") && !Panels.gameFunctions.lost)
				{	
					frontTile.setIcon(normalTile);
					currentTile = "normalTile";
				}
				else if(currentTile.equals("normalTile") && !Panels.gameFunctions.lost)
				{
					frontTile.setIcon(flag);
					bombFlags++;
					Panels.setFlagsLabel(1);//This makes the bomb counter label on the frame decrease by one
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
			if(!Panels.gameFunctions.lost)
				System.out.println("Scary face is off");
		}

		
	}
/*--------------------------------Inner class----------------------------------*/	

}
