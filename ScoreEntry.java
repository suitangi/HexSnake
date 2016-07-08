
public class ScoreEntry {

	private String name;
	private int score;
	
	public ScoreEntry(String n, int s)
	{
		name = n;
		score = s;
	}
	public ScoreEntry()
	{
		name = "<NOBODY>";
		score = 0;
	}
	public String getName()
	{
		return name;
	}
	public int getScore()
	{
		return score;
	}
	public String toString()
	{
		return name + ": " + score;
	}
	
}
