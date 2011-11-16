package icom4015.project2.minesweeper;

import java.awt.BorderLayout;
import java.awt.Dimension;


import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.BevelBorder;


public class Panels extends JFrame
{
	
	private static TimerBomb tb;// = new TimerBomb();
 	private static Timer timer;// = new Timer(1000, tb);//This creates the timer
 	
	protected static GameFunctions gameFunctions;
	protected static JButton mainButton;
	
	public void setPanels(int row, int column, int bombNumber, Menus menuBar, JButton panelButton, String difficulty)
	{
		gameFunctions = new GameFunctions(row,column, bombNumber, difficulty);
		tb = new TimerBomb( bombNumber);
		timer = new Timer(1000, tb);
		
		mainButton = panelButton;
		
		setTitle("Minesweeper");
		ImageIcon icon = new ImageIcon("images/image48x48/mine-logo.png");
		setIconImage(icon.getImage());
		setResizable(false);
		JPanel timerAndBombPanel = new JPanel();
		JPanel all = new JPanel();
		JPanel forButton = new JPanel();   // creates a jpanel for the button

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		int frameWidth = column*20 + 40;
		int frameHeight = row*20 +190;
		setSize(frameWidth+20, frameHeight);

		//Setting the look of the all panel.
		all.setLayout(new BoxLayout(all, BoxLayout.Y_AXIS));
		all.setBorder(new BevelBorder(BevelBorder.RAISED));

		//Setting the menus
		setMenuBar(menuBar.getMenuBar());


		//Adding empty space between the menus and the timer panel.
		all.add(Box.createRigidArea(new Dimension(0,25)));

		//Adding the timer and bombs left panel
		timerAndBombPanel.setBorder(new BevelBorder(BevelBorder.LOWERED));
		timerAndBombPanel.setMaximumSize(new Dimension(frameWidth,60));
		timerAndBombPanel.setLayout(new BorderLayout());//added line for layout
		timerAndBombPanel.add(tb.getBombsLeft(), BorderLayout.WEST);
		
		ImageIcon buttonIcon = new ImageIcon("images/image48x48/startgame.png"); // creates the image for the button
		mainButton.setIcon(buttonIcon); //sets the image for the button
		mainButton.setPreferredSize(new Dimension(60,50)); //sets the size for the button
		forButton.add(mainButton);//sends the button to a panel
		

		timerAndBombPanel.add(forButton, BorderLayout.CENTER);//adds the button to the middle
		timerAndBombPanel.add(tb.timerText(), BorderLayout.EAST);//added for timer
		all.add(timerAndBombPanel);

		//Adding empty space between the two components
		all.add(Box.createRigidArea(new Dimension(0,25)));

		
		//Set the game
		gameFunctions.setGameReady();

		//Adding the playing area.
		all.add(gameFunctions.getPlayPanel());

		UnderLabelFrame ulf = new UnderLabelFrame(gameFunctions.getUnderPanel());

		add(all);

		setVisible(true);

	}
	
	/**
	 * Updates the label on the frame containing how many bombs are left.
	 * @param increaseORDecrease 1 if we added a flag, -1 if we deleted a flag.
	 */
	public static void setFlagsLabel(int increaseORDecrease)
	{
		tb.setFlagsLabel(increaseORDecrease);
	}
	
	/**
	 * Gets the TimerBomb object associated with the current game.
	 * @return the timer bomb.
	 */
	public static TimerBomb getTimerBomb()
	{
		return tb;
	}
	

	/**
	 * Gets the timer object associated with the ongoing game.
	 * @return the timer object.
	 */
	public static Timer getTimer()
	{
		return timer;
	}
	
	/**
	 * 
	 * @param isFirstTime true if this is the first tile being pressed.
	 * false if the player lost or won the game.
	 */
	public static void setTimer(boolean isFirstTime)
	{
		if (isFirstTime )
			timer.start();
		
		else if(!isFirstTime)
			timer.stop();
	}
	
}
