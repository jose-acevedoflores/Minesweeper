package icom4015.project2.minesweeper;

import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Menus {

	private JButton mainButton;
	
	private MenuBar menuBar;
	private Menu game;
	
	private MenuItem newGame;
	
	private MenuItem beginner;
	private MenuItem intermidiate;
	private MenuItem expert;
	private MenuItem custom;
		
	private MenuItem bestTimes;
	
	private MenuItem exit;
	private ActionListener gL; 
	private ExitListener exitListener;
	
	private int beginnerRows=9;
	private int beginnerColumns=9;
	private int beginnersBombs= 10;
	
	private int intermidiateRows=16;
	private int intermidiateColumns=16;
	private int intermidiateBombs = 40;
	
	private int expertRows=16;
	private int expertColumns = 30;
	private int expertBombs = 99;
	
	private int customRows=9;
	private int customColumns =9;
	private int customBombs=10;
	
	/**
	 * Create the menus that will appear on the frame.
	 */
	public Menus()
	{
		
		gL = new MenuAndButtonListener();
		exitListener = new ExitListener();
		
		mainButton = new JButton();
		mainButton.addActionListener(gL);
		
		menuBar = new MenuBar();
		game = new Menu("Game");
		
		newGame = new MenuItem("    New");
		game.addActionListener(gL);
		game.add(newGame);
	
		game.addSeparator();
		
		beginner = new MenuItem("\u2713  Beginner");
		beginner.addActionListener(gL);
		game.add(beginner);
		
		intermidiate = new MenuItem("    Intermidiate");
		intermidiate.addActionListener(gL);
		game.add(intermidiate);
	
		expert = new MenuItem("    Expert");
		expert.addActionListener(gL);
		game.add(expert);
		
		custom = new MenuItem("    Custom...");
		custom.addActionListener(gL);
		game.add(custom);
		
		game.addSeparator();
				
		bestTimes= new MenuItem("    Best Times...");
		game.add(bestTimes);
		
		game.addSeparator();
		
		exit = new MenuItem("    Exit");
		exit.addActionListener(exitListener);
		game.add(exit);
		
		menuBar.add(game);
	
	}
	
	
	
	/**
	 * Gets the menu bar  for the frame.
	 * @return the menu bar
	 */
	public MenuBar getMenuBar()
	{
		return menuBar;
	}
	
	public JButton getMainButton()
	{
		return mainButton;
	}
	
	
	private class MenuAndButtonListener implements ActionListener
	{

		
		@Override
		public void actionPerformed(ActionEvent arg) 
		{
			if(arg.getActionCommand().equals("    New") || arg.getSource() == mainButton)
			{
				if(beginner.getLabel().equals("\u2713  Beginner"))
				{
					Minesweeper.setNewGame(beginnerRows, beginnerColumns, 10);
				}
				else if(intermidiate.getLabel().equals("\u2713  Intermidiate"))
				{
					Minesweeper.setNewGame(intermidiateRows, intermidiateColumns, 40);
				}
				else if(expert.getLabel().equals("\u2713  Expert"))
				{
					Minesweeper.setNewGame(expertRows, expertColumns,99);
				}
				else if(custom.getLabel().equals("\u2713  Custom..."))
				{
					Minesweeper.setNewGame(customRows, customColumns, customBombs);
				}
			}
			
			else if(arg.getActionCommand().equals("    Beginner"))
			{
				beginner.setLabel("\u2713  Beginner");
				intermidiate.setLabel("    Intermidiate");
				expert.setLabel("    Expert");
				custom.setLabel("    Custom...");
				Minesweeper.setNewGame(beginnerRows,  beginnerColumns, beginnersBombs);
			}
			else if(arg.getActionCommand().equals("    Intermidiate"))
			{
				beginner.setLabel("    Beginner");
				intermidiate.setLabel("\u2713  Intermidiate");
				expert.setLabel("    Expert");
				custom.setLabel("    Custom...");
				Minesweeper.setNewGame(intermidiateRows, intermidiateColumns, intermidiateBombs);
				
			}
			else if(arg.getActionCommand().equals("    Expert"))
			{
				beginner.setLabel("    Beginner");
				intermidiate.setLabel("    Intermidiate");
				expert.setLabel("\u2713  Expert");
				custom.setLabel("    Custom...");
				Minesweeper.setNewGame(expertRows, expertColumns, expertBombs);
			
			}
			else if(arg.getActionCommand().equals("    Custom...") || arg.getActionCommand().equals("\u2713  Custom...") )
			{
				beginner.setLabel("    Beginner");
				intermidiate.setLabel("    Intermidiate");
				expert.setLabel("    Expert");
				custom.setLabel("\u2713  Custom...");

				String rows="0", columns="0", bombs="0";
				
				rows = JOptionPane.showInputDialog("Type rows: ");
				columns = JOptionPane.showInputDialog("Type columns: ");
				bombs = JOptionPane.showInputDialog("Bombs: ");
				
				
				try{
					customRows = Math.min(Integer.parseInt(rows),  24); // max 24
					customColumns = Math.min(Integer.parseInt(columns), 30); // max 30
					
					customBombs = Math.min(Integer.parseInt(bombs), 667); // max 667
					
					if(customRows*customColumns -40 < customBombs  )
					{
						customBombs = customRows*customColumns -40;
					}
					
				}
				catch(NumberFormatException e)
				{
					JOptionPane.showMessageDialog(null, "Illegal arguments");
				}
				
				
				Minesweeper.setNewGame(customRows, customColumns, customBombs);
		
			}
			
						
		}
		

	}
	
}
