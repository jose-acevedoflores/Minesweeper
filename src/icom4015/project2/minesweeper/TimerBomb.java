package icom4015.project2.minesweeper;

import java.awt.Color;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;


public class TimerBomb implements ActionListener 
{

	private JLabel bombsLeft;
	private JLabel timer = new JLabel(getTime());
	private int flagLabelCounter;
	private int seconds= 0;
	private int minutes= 0;
	
	
	public TimerBomb()
	{
		flagLabelCounter= 10;
		timer = new JLabel(getTime());
		bombsLeft = new JLabel(Integer.toString(flagLabelCounter));
	}


	public JLabel getBombsLeft()
	{
		bombsLeft.setFont(new Font(null, Font.BOLD, 30));
		bombsLeft.setBackground(Color.black);
		bombsLeft.setForeground(Color.red);
		return bombsLeft;
	}
	
	public void setFlagsLabel(int increaseORDecrease)
	{
		flagLabelCounter -=  increaseORDecrease;
		bombsLeft.setText(Integer.toString(flagLabelCounter));
	}

	public JLabel timerText()
	{
		timer.setFont(new Font(null, Font.BOLD, 30));
		timer.setBackground(Color.black);
		timer.setForeground(Color.red);
		return timer;
	}

	public String getTime()
	{
		if (seconds <10)
			return minutes + ":0" + seconds;
		else return minutes + ":" + seconds;
	}


	public void actionPerformed(ActionEvent arg0) 
	{

		if (seconds == 59)
		{
			seconds = 0;
			minutes++;
		}
		
		seconds++;
		timer.setText(getTime());

	}
}
