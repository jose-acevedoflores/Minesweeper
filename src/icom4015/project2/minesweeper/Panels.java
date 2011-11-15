package icom4015.project2.minesweeper;

import java.awt.BorderLayout;
import java.awt.Dimension;


import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.BevelBorder;


public class Panels extends JFrame
{
	
	private static TimerBomb tb;// = new TimerBomb();
 	protected static Timer timer;// = new Timer(1000, tb);//This creates the timer
 	
	private static Menus menuBar = new Menus();
	private static GameFunctions gameFunctions;
	
	public void setPanels(int row, int column)
	{
		gameFunctions = new GameFunctions(row,column);
		tb = new TimerBomb();
		timer = new Timer(1000, tb);
		
	//	JFrame frame = new JFrame("Minesweeper");
		setTitle("Minesweeper");
		ImageIcon icon = new ImageIcon("images/mine-logo.png");
		setIconImage(icon.getImage());
		setResizable(false);
		JPanel timerAndBombPanel = new JPanel();
		JPanel all = new JPanel();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(500, 650);

		//Setting the look of the all panel.
		all.setLayout(new BoxLayout(all, BoxLayout.Y_AXIS));
		all.setBorder(new BevelBorder(BevelBorder.RAISED));

		//Setting the menus
		setMenuBar(menuBar.getMenuBar());


		//Adding empty space between the menus and the timer panel.
		all.add(Box.createRigidArea(new Dimension(0,25)));

		//Adding the timer and bombs left panel
		timerAndBombPanel.setBorder(new BevelBorder(BevelBorder.LOWERED));
		timerAndBombPanel.setMaximumSize(new Dimension(465,60));
		timerAndBombPanel.setLayout(new BorderLayout());//added line for layout
		timerAndBombPanel.add(tb.getBombsLeft(), BorderLayout.WEST);
		timerAndBombPanel.add(tb.timerText(), BorderLayout.EAST);//added for timer
		all.add(timerAndBombPanel);

		//Adding empty space between the two components
		all.add(Box.createRigidArea(new Dimension(0,25)));

		
		//Set the game
		gameFunctions.setGameReady();

		//Adding the playing area.
		all.add(gameFunctions.getPlayPanel());

		//UnderLabelFrame ulf = new UnderLabelFrame(gameFunctions.getUnderPanel());

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
