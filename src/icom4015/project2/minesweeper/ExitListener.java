package icom4015.project2.minesweeper;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ExitListener implements ActionListener {
	
	
	public void actionPerformed(ActionEvent e)
	{
		if(e.getActionCommand().equals("    Exit"))
			System.exit(0);
	}

}
