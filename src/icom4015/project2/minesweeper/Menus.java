package icom4015.project2.minesweeper;

import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menus {

	private MenuBar menuBar;
	private Menu game;
	
	private MenuItem newGame;

	private MenuItem beginner;
	private MenuItem intermidiate;
	private MenuItem expert;
	private MenuItem custom;
	
	private MenuItem marks;
	private MenuItem color;
	private MenuItem sounds;
	
	private MenuItem bestTimes;
	
	private MenuItem exit;
	private ActionListener gL; 
	/**
	 * Create the menus that will appear on the frame.
	 */
	public Menus()
	{
		gL = new GameLevelListener();
		menuBar = new MenuBar();
		game = new Menu("Game");
		
		newGame = new MenuItem("    New");
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
		
		marks = new MenuItem("\u2713  Marks(?)");
		game.add(marks);
		
		color = new MenuItem("\u2713  Color");
		game.add(color);
		
		sounds = new MenuItem("    Sounds");
		game.add(sounds);
		
		game.addSeparator();
		
		bestTimes= new MenuItem("    Best Times...");
		game.add(bestTimes);
		
		game.addSeparator();
		
		exit = new MenuItem("    Exit");
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
	
	
	private class GameLevelListener implements ActionListener
	{

		
		@Override
		public void actionPerformed(ActionEvent arg) 
		{
	
			if(arg.getActionCommand().equals("    Beginner"))
			{
				int rows = 9, columns = 9;
				beginner.setLabel("\u2713  Beginner");
				intermidiate.setLabel("    Intermidiate");
				expert.setLabel("    Expert");
				custom.setLabel("    Custom...");
				Minesweeper.setNewGame(rows, columns, 10);
			}
			else if(arg.getActionCommand().equals("    Intermidiate"))
			{
				int rows = 10, columns = 15;
				beginner.setLabel("    Beginner");
				intermidiate.setLabel("\u2713  Intermidiate");
				expert.setLabel("    Expert");
				custom.setLabel("    Custom...");
				Minesweeper.setNewGame(rows, columns, 40);
				
			}
			else if(arg.getActionCommand().equals("    Expert"))
			{
				int rows = 15, columns = 20;
				beginner.setLabel("    Beginner");
				intermidiate.setLabel("    Intermidiate");
				expert.setLabel("\u2713  Expert");
				custom.setLabel("    Custom...");
				Minesweeper.setNewGame(rows, columns,60);
			
			}
			else if(arg.getActionCommand().equals("    Custom..."))
			{
				beginner.setLabel("    Beginner");
				intermidiate.setLabel("    Intermidiate");
				expert.setLabel("    Expert");
				custom.setLabel("\u2713  Custom...");
				//Minesweeper.setNewGame(rows, columns);
		
			}
			
		}
		

	}
	
}
