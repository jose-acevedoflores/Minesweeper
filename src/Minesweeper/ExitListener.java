package Minesweeper;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * This class takes care of exiting the program when the menu item is clicked is clicked. 
 */
public class ExitListener implements ActionListener {
	
	
	public void actionPerformed(ActionEvent e)
	{
		if(e.getActionCommand().equals("    Exit"))
			System.exit(0);
	}

}
