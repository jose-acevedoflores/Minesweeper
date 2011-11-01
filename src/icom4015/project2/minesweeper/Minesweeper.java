package icom4015.project2.minesweeper;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
/**
 * Class designed to create the frame and present it to the user
 * @author 
 *
 */
public class Minesweeper {

	/**
	 * Main method
	 * @param args not used
	 */
	public static void main(String[] args) {
		JFrame frame = new JFrame("Minesweeper");
		ImageIcon icon = new ImageIcon("images/mine2.png");
		JPanel timerAndBombPanel = new JPanel();
		JPanel all = new JPanel();
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(500, 650);
		
		//Setting the look of the all panel.
		all.setLayout(new BoxLayout(all, BoxLayout.Y_AXIS));
		all.setBorder(new BevelBorder(BevelBorder.RAISED));
		
		//Setting the menus
		Menus menuBar = new Menus();
		frame.setMenuBar(menuBar.getMenuBar());
		
		//Set the game
		MineGenerator mg = new MineGenerator();
		GameFunctions gameFunctions = new GameFunctions(mg);
		gameFunctions.setGameReady();
		
		//Adding empty space between the menus and the timer panel.
		all.add(Box.createRigidArea(new Dimension(0,25)));
		
		//Adding the timer and bombs left panel
		timerAndBombPanel.setBorder(new BevelBorder(BevelBorder.LOWERED));
		timerAndBombPanel.setMaximumSize(new Dimension(465,60));
		all.add(timerAndBombPanel);
		
		//Adding empty space between the two components
		all.add(Box.createRigidArea(new Dimension(0,25)));
		
		//Adding the playing area.
		
		all.add(gameFunctions.getPlayPanel());
		
		frame.add(all);
		frame.setIconImage(icon.getImage());
		frame.setResizable(false);
		frame.setVisible(true);
		
	}

}
