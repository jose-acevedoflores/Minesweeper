package icom4015.project2.minesweeper;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;


public class BestTimes implements ActionListener {

	private static File bestTimesHistory = new File("/Users/Home/Desktop/MinesweeperBestTimes.txt");
	
	public static void setBestTime(int time, String difficulty) throws FileNotFoundException
	{
		
		Scanner in = new Scanner(bestTimesHistory);
		
		
		String[] fileLines = new String[3];
		
		for(int i = 0; i < 3; i++)
		{
			fileLines[i] = in.nextLine();
		}
		
		int difficultyNum=0;
		
		if(difficulty.equals("beginner"))
		{
			difficultyNum=0;
		}
		else if(difficulty.equals("intermidiate"))
		{
			difficultyNum = 1;
		}
		else if(difficulty.equals("intermidiate"))
		{
			difficultyNum = 2;
		}
		
		System.out.println("diffnum"+difficultyNum );
		
		
		int previousScore = Integer.parseInt(fileLines[difficultyNum]);

		if(previousScore > time)
		{
			fileLines[difficultyNum] = Integer.toString(time);
			PrintWriter out = new PrintWriter(bestTimesHistory);
			try{
				for(int i = 0; i < 3; i++)
				{
					
					out.println(fileLines[i]);
				}
			}
			finally
			{
				if(out != null)
					out.close();
			}
		}


		

		
	}
	
	
	
	public void actionPerformed(ActionEvent arg) 
	{
		
		System.out.println("here");
		
	}

}
