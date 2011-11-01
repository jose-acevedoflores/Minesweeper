package icom4015.project2.minesweeper;

import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;

public class Menus {

	private MenuBar menuBar;
	
	private Menu game;
	private MenuItem newGame;

	private Menu help;
	private MenuItem aboutMinesweeper;
	
	
	/**
	 * Create the menus that will appear on the frame.
	 */
	public Menus()
	{
		menuBar = new MenuBar();
		
		game = new Menu("Game");
		newGame = new MenuItem("New");
		game.add(newGame);
		
		help = new Menu("Help"); 
		aboutMinesweeper = new MenuItem("About Minesweeper");
		help.add(aboutMinesweeper);
		
		menuBar.add(game);
		menuBar.add(help);
	}
	
	/**
	 * Gets the menu bar  for the frame.
	 * @return the menu bar
	 */
	public MenuBar getMenuBar()
	{
		return menuBar;
	}
	
	
}
