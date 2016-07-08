import java.awt.*;
import java.util.*;
import java.io.*;

import javax.swing.JOptionPane;

public class ScoreKeeper {
	
	private File easy, medium, hard;
	private String easyName = "Easy.txt";
	private String mediumName = "Medium.txt";
	private String hardName = "Hard.txt";
	private ScoreEntry[] Easy = new ScoreEntry[5];
	private ScoreEntry[] Medium = new ScoreEntry[5];
	private ScoreEntry[] Hard = new ScoreEntry[5];
	
	public void importData()
	{
		//fills the highscore arrays with empty players
		int i = 0;
		while (i < 5)
		{
			Easy[i] = new ScoreEntry();
			Medium[i] = new ScoreEntry();
			Hard[i] = new ScoreEntry();
			i++;
		}
		
		//loads in the easy mode highscore data
		Scanner input = null;
		easy = new File(easyName);
		String data = "", name = "", scor = "";
		int sep = 0, score;
		try
		{
			input = new Scanner(easy);
		}
		catch(FileNotFoundException ex)
		{
			PrintWriter output = null;
			easy = new File(easyName);
			try
			{
				output = new PrintWriter(easy);
			}
			catch(FileNotFoundException e)
			{
				JOptionPane.showMessageDialog(null, "Failed to load highscore data", "Error", JOptionPane.ERROR_MESSAGE);
			}
			output.close();
			
		}
		try
		{
			input = new Scanner(easy);
		}
		catch(FileNotFoundException ex)
		{
			JOptionPane.showMessageDialog(null, "Failed to load highscore data", "Error", JOptionPane.ERROR_MESSAGE);
		}
		
		i = 0;
		while(input.hasNextLine() && i < 5)
		{
			data = input.nextLine();
			sep = data.indexOf(":");
			name = data.substring(0, sep);
			scor = data.substring(sep + 2);
			score = Integer.parseInt(scor);
			Easy[i] = new ScoreEntry(name, score);
			i++;
		}
		
		//medium data
		medium = new File(mediumName);
		data = "";
		name = "";
		scor = "";
		sep = 0;
		try
		{
			input = new Scanner(medium);
		}
		catch(FileNotFoundException ex)
		{
			PrintWriter output = null;
			easy = new File(mediumName);
			try
			{
				output = new PrintWriter(medium);
			}
			catch(FileNotFoundException e)
			{
				JOptionPane.showMessageDialog(null, "Failed to load highscore data", "Error", JOptionPane.ERROR_MESSAGE);
			}
			output.close();
			
		}
		try
		{
			input = new Scanner(medium);
		}
		catch(FileNotFoundException ex)
		{
			JOptionPane.showMessageDialog(null, "Failed to load highscore data", "Error", JOptionPane.ERROR_MESSAGE);
		}
		
		i = 0;
		while(input.hasNextLine() && i < 5)
		{
			data = input.nextLine();
			sep = data.indexOf(":");
			name = data.substring(0, sep);
			scor = data.substring(sep + 2);
			score = Integer.parseInt(scor);
			Medium[i] = new ScoreEntry(name, score);
			i++;
		}
		
		//hard data
		hard = new File(hardName);
		data = "";
		name = "";
		scor = "";
		sep = 0;
		try
		{
			input = new Scanner(hard);
		}
		catch(FileNotFoundException ex)
		{
			PrintWriter output = null;
			easy = new File(hardName);
			try
			{
				output = new PrintWriter(hard);
			}
			catch(FileNotFoundException e)
			{
				JOptionPane.showMessageDialog(null, "Failed to load highscore data", "Error", JOptionPane.ERROR_MESSAGE);
			}
			output.close();
			
		}
		try
		{
			input = new Scanner(hard);
		}
		catch(FileNotFoundException ex)
		{
			JOptionPane.showMessageDialog(null, "Failed to load highscore data", "Error", JOptionPane.ERROR_MESSAGE);
		}
		
		i = 0;
		while(input.hasNextLine() && i < 5)
		{
			data = input.nextLine();
			sep = data.indexOf(":");
			name = data.substring(0, sep);
			scor = data.substring(sep + 2);
			score = Integer.parseInt(scor);
			Hard[i] = new ScoreEntry(name, score);
			i++;
		}
		
		
	}
	
	public void exportData(int diff)
	{
		PrintWriter output = null;
		easy = new File(easyName);
		medium = new File(mediumName);
		hard = new File(hardName);
		if (diff == 3)
		{
			try
			{
				output = new PrintWriter(easy);
			}
			catch(FileNotFoundException ex)
			{
				JOptionPane.showMessageDialog(null, "Failed to write highscore data", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
		if (diff == 2)
		{
			try
			{
				output = new PrintWriter(medium);
			}
			catch(FileNotFoundException ex)
			{
				JOptionPane.showMessageDialog(null, "Failed to write highscore data", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
		if (diff == 1)
		{
			try
			{
				output = new PrintWriter(hard);
			}
			catch(FileNotFoundException ex)
			{
				JOptionPane.showMessageDialog(null, "Failed to write highscore data", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}

		int i = 0;
		while(i < 5)
		{
			if (diff == 3)
				output.println(Easy[i]);
			if (diff == 2)
				output.println(Medium[i]);
			if (diff == 1)
				output.println(Hard[i]);
			i++;
		}
		output.close();
	}
	
	public ScoreEntry[] getScores(int diff)
	{
		if (diff == 1)
			return Hard;
		else if (diff == 2)
			return Medium;
		else
			return Easy;
	}
	
}
