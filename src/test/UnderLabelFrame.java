package test;


import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

public class UnderLabelFrame {

	public UnderLabelFrame(JPanel panel, int frameHeight, int frameWidth)
	{
		JFrame frame = new JFrame("Minesweeper");
		ImageIcon icon = new ImageIcon("images/image48x48/mine-logo.png");
		JPanel timerAndBombPanel = new JPanel();
		JPanel all = new JPanel();
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize( frameWidth+20, frameHeight);
		
		//Setting the look of the all panel.
		all.setLayout(new BoxLayout(all, BoxLayout.Y_AXIS));
		all.setBorder(new BevelBorder(BevelBorder.RAISED));
		
		
		//Adding empty space between the menus and the timer panel.
		all.add(Box.createRigidArea(new Dimension(0,25)));
		
		//Adding the timer and bombs left panel
		timerAndBombPanel.setBorder(new BevelBorder(BevelBorder.LOWERED));
		timerAndBombPanel.setMaximumSize(new Dimension(frameWidth,60));
		all.add(timerAndBombPanel);
		
		//Adding empty space between the two components
		all.add(Box.createRigidArea(new Dimension(0,25)));
		

		//Adding the playing area.
		all.add(panel);
		
		frame.add(all);
		frame.setIconImage(icon.getImage());
		frame.setResizable(false);
		frame.setVisible(true);
	}
}
