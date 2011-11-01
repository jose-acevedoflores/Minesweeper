package icom4015.project2.minesweeper;

import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;

public class Menus {

	private Menu game;
	private Menu help;
	private MenuBar menuBar;
	
	/**
	 * Create the menus that will appear on the frame.
	 */
	public Menus()
	{
		menuBar = new MenuBar();
		game = new Menu("Game");
		help = new Menu("Help"); 
		
		
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
