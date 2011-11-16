package Minesweeper;


/**
 * Class designed to create the frame and present it to the user
 * @author 
 *
 */
public class Minesweeper {

	private static Panels mainWindow = new Panels();
	private static Menus menuBar = new Menus();
	
	
	/**
	 * Main method
	 * @param args not used
	 */
	public static void main(String[] args) 
	{
		//Sets a beginner level game
		mainWindow.setPanels(9, 9, 10, menuBar, menuBar.getMainButton(),menuBar.getDifficulty());
	}
	
	/**
	 * Set a new game with the given rows and columns.
	 * @param row
	 * @param column
	 */
	public static void setNewGame(int row , int column, int  bombNumber)
	{
		mainWindow.dispose();
		mainWindow = new Panels();
		mainWindow.setPanels(row, column,  bombNumber,menuBar, menuBar.getMainButton(), menuBar.getDifficulty() );
	}

}