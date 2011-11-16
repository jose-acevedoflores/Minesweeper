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
 	protected static Timer timer;// = new Timer(1000, tb);//This creates the timer
 	
	protected static GameFunctions gameFunctions;

	
	public void setPanels(int row, int column, int bombNumber, Menus menuBar, JButton mainButton)
	{
		gameFunctions = new GameFunctions(row,column, bombNumber);
		tb = new TimerBomb( bombNumber);
		timer = new Timer(1000, tb);

		
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
		
		ImageIcon buttonIcon = new ImageIcon("images/image20x20/startgame.png"); // creates the image for the button
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
	
	
	public static void setFlagsLabel(int increaseORDecrease)
	{
		tb.setFlagsLabel(increaseORDecrease);
	}

	public static void setTimer(boolean isFirstTime)
	{
		if (isFirstTime )
			timer.start();
		
		else if(!isFirstTime)
			timer.stop();
	}
	
}
