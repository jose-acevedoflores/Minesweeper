package icom4015.project2.minesweeper;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CustomMenuController {
	
	private static JTextField customRows = new JTextField(5);
	private static JTextField customColumns = new JTextField(5);
	private static JTextField customBombs = new JTextField(5);
	private static JButton okButton = new JButton("OK");
	private static JButton cancelButton = new JButton("Cancel");
	private static JFrame frame = new JFrame("Custom Field");
	
	public void showInputDialog(String rows, String columns, String bombs)
	{
		ButtonListener buttonListener = new ButtonListener();
		okButton.addActionListener(buttonListener);
		cancelButton.addActionListener(buttonListener);
		
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(3,3));
		
		
		JLabel rowLabel = new JLabel("Rows: ");
		JLabel columnLabel = new JLabel("Columns");
		JLabel bombsLabel = new JLabel("Bombs");
		
		
		frame.setSize(300,100);
		
		panel.add(rowLabel);
		panel.add(customRows);
		panel.add(okButton);
		
		panel.add(columnLabel);
		panel.add(customColumns);
		panel.add(Box.createRigidArea(new Dimension(0,0)));
		
		panel.add(bombsLabel);
		panel.add(customBombs);
		panel.add(cancelButton);
		
		frame.add(panel);
		
		frame.setResizable(false);
		frame.setVisible(true);
	}
	
	/** 
	 * Control the user clicks in the custom field window. 
	 * Controls the ok button and the cancel button.
	 */
	private class ButtonListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent arg)
		{
			if(arg.getSource() == okButton)
			{
				String row = customRows.getText();
				String column = customColumns.getText();
				String bombs = customBombs.getText();
				
				
				frame.dispose();
			}
			else if (arg.getSource() == cancelButton)
			{
				System.out.println("cancel");
				frame.dispose();
			}
			
		}
		
	}
	

}
