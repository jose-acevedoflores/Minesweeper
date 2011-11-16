package Minesweeper;

import java.awt.Color;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;


/**
 * Creates the fields for the flags and the time in seconds that will 
 * be printed out onto screen. 
 * @author 
 *
 */
public class TimerBomb implements ActionListener 
{

	private JLabel bombsLeft;
	private JLabel timer = new JLabel(getTime());
	private int flagLabelCounter;
	private int seconds= 0;
	
	
	/**
	 * Creates the labels that will be printed out onto screen.
	 * @param bombNumber the bomb number.
	 */
	public TimerBomb(int  bombNumber)
	{
		flagLabelCounter=  bombNumber;
		timer = new JLabel(getTime());
		bombsLeft = new JLabel(Integer.toString(flagLabelCounter));
	}

	/**
	 * Gets the amount of flags that are left to be places and sends them to a text field
	 * and colors the field. 
	 * @return
	 */
	public JLabel getBombsLeft()
	{
		bombsLeft.setFont(new Font(null, Font.BOLD, 30));
		bombsLeft.setBackground(Color.black);
		bombsLeft.setForeground(Color.red);
		return bombsLeft;
	}
	
	/**
	 * Sets the label for the flags left to count and prints them on screen.
	 * @param increaseORDecrease
	 */
	public void setFlagsLabel(int increaseORDecrease)
	{
		flagLabelCounter -=  increaseORDecrease;
		bombsLeft.setText(Integer.toString(flagLabelCounter));
	}

	/**
	 * Gets the text for the timer and gives it color. 
	 * @return
	 */
	public JLabel timerText()
	{
		timer.setFont(new Font(null, Font.BOLD, 30));
		timer.setBackground(Color.black);
		timer.setForeground(Color.red);
		return timer;
	}

	/**
	 * Gets the time as  a string in order for it to be printed out on a text field. 
	 * @return
	 */
	public String getTime()
	{	
		if(seconds < 10)
			return "00"+seconds;
		else if(seconds < 100)
			return "0"+seconds;
		else
			return ""+seconds;
	}

	//Counts the seconds and sets the time to the timer.
	public void actionPerformed(ActionEvent arg0) 
	{	
		seconds++;
		timer.setText(getTime());
	}
}
