package icom4015.project2.minesweeper;

/**
 * Class designed to create the frame and present it to the user
 * @author 
 *
 */
public class Minesweeper {

	private static Panels mainWindow = new Panels();
	/**
	 * Main method
	 * @param args not used
	 */
	public static void main(String[] args) 
	{
		
		mainWindow.setPanels(9, 9);
	}
	
	
	public static void setNewGame(int row , int column)
	{
		mainWindow.dispose();
		mainWindow = new Panels();
		mainWindow.setPanels(row, column);
	}

}