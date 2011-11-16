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
	
	private static ImageIcon flag = new ImageIcon("images/image20x20/Red-Flag.jpg");
	private static ImageIcon questionMark = new ImageIcon("images/image20x20/Question_mark.png");
	private static ImageIcon normalTile = new ImageIcon("images/image20x20/tile.png");
	private static int moves;
	
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
		moves=0;
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
	 * Gets the string label of this style.
	 * @return 
	 */
	public String getCurrentTile()
	{
		return currentTile;
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
		
		if( labelUnderTile.getNumberHere() == 0)
		{	frontTile.setIcon(null);
			if(!currentTile.equals("under"))
				moves++;	
			currentTile = "under";
		}
		else
		{
			ImageIcon t = new ImageIcon("images/image20x20/numbers/num"+Integer.toString(labelUnderTile.getNumberHere())+".png");
			frontTile.setIcon(t);
			if(!currentTile.equals("under"))
				moves++;	
			currentTile = "under";
		}
	}
	
	public void setFrontTileOnLost()
	{
		if(labelUnderTile.getNumberHere() == -1)			
		{	
			frontTile.setIcon(labelUnderTile.getLabel().getIcon());
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

			if(arg0.getButton() == MouseEvent.BUTTON1)
			{
				if(!Panels.getTimer().isRunning() && !Panels.gameFunctions.lost && !Panels.gameFunctions.won )
					Panels.setTimer(true);
				
				if(!currentTile.equals("flag")&& !currentTile.equals("under")&&!Panels.gameFunctions.lost&& !Panels.gameFunctions.won)
				{
					currentTile = "under";
					
					if(labelUnderTile.getLabel().getIcon() == null && labelUnderTile.getNumberHere() != 0 ) // If it's null the under it we have a number 
					{
						ImageIcon t = new ImageIcon("images/image20x20/numbers/num"+Integer.toString(labelUnderTile.getNumberHere())+".png");
						frontTile.setIcon(t);
						moves++;
					}
					if(labelUnderTile.getNumberHere() == 0)
					{
						Panels.gameFunctions.revealEmptyStreak(row,column);
						moves++;
					}
					if(labelUnderTile.bombHere())
					{
						frontTile.setIcon(new ImageIcon("images/image20x20/bomb-explode.png"));
						
						Panels.gameFunctions.revealBombs(row, column);
						
						Panels.mainButton.setIcon(new ImageIcon("images/image20x20/lostgame.png"));
						Panels.setTimer(false);
						Panels.gameFunctions.lost=true;
					}
					
				}
				
				if(moves == (Panels.gameFunctions.gameColumns*Panels.gameFunctions.gameRows)- Panels.gameFunctions.mineGenerator.getHowManyBombs())
				{
					Panels.gameFunctions.won=true;
					Panels.mainButton.setIcon(new ImageIcon("images/image48x48/wongame.png"));
					Panels.setTimer(false);
				}
			}

			//Right click pressed
			else if(arg0.getButton() == MouseEvent.BUTTON3)
			{	
				if(currentTile.equals("flag") && !Panels.gameFunctions.lost)
				{
					frontTile.setIcon(questionMark);
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
		public void mousePressed(MouseEvent arg0) {
			
			if(!Panels.gameFunctions.lost)
			{
				ImageIcon expectativeFace = new ImageIcon("images/image20x20/amir2.JPG");
				Panels.mainButton.setIcon(expectativeFace);
			}
		}

		@Override
		public void mouseReleased(MouseEvent arg0) 
		{
			if(!Panels.gameFunctions.lost)
			{
				ImageIcon buttonIcon = new ImageIcon("images/image48x48/startgame.png"); 
				Panels.mainButton.setIcon(buttonIcon);
			}
		}

		
	}
/*--------------------------------Inner class----------------------------------*/	

}
