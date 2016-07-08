import java.util.ArrayList;


public class Snake {

	private ArrayList<Point> points = new ArrayList<Point>();
	private int direction = 0; // the direction the snake is moving from 0 to 5
	private int nextDir = 0; //the direction the snake plans on moving next (0 to 5)
	
	//constructs a snake at 10, 10
	public Snake()
	{
		points.add(new Point(10, 10));

	}
	
	//returns the Point of the head of the snake
	public Point getHead()

	{
		return points.get(0);
	}
	
	//returns the Point of the tail of the snake	
	public Point getTail()
	{
		return points.get(points.size() - 1);
	}
	
	//returns the direction the snake is moving in from 0 to 5
	public int getDirection()
	{
		return direction;
	}
	
	//returns the direction the snake is planning to move
	public int getNextDir()
	{
		return nextDir;
	}
	
	//sets the direction with a value from 0 to 5
	public void setDirection(int dira)
	{
		if (dira >= 0 && dira <= 5 && (points.size() == 1 || dira != (direction + 3) % 6))
			direction = dira;
		else
			return;
	}
	
	//sets the direction the snake will plan to move
	public void setNextDir(int dir)
	{
		nextDir = dir;
	}
	
	//returns 0 if the Point pt is empty
	//returns 1 if the Point is not edible or out of grid
	//returns 2 if the Point contains a food
	public int checkPoint(int wid, int len, Point pt, Food food)
	{
		if (pt.getX() >= wid || pt.getY() >= len || pt.getX() < 0 || pt.getY() < 0)
			return 1;
		else if (checkPos(pt))
			return 1;
		else if (pt.getX() == food.getPos().getX() && 
				pt.getY() == food.getPos().getY())
			return 2;
		else
			return 0;
	}
	
	//returns true if Point pt is a Point in the snake
	public boolean checkPos(Point pt)
	{
		for(Point pos: points)
		{
			if (pt.getX() == pos.getX() && 
					pt.getY() == pos.getY())
				return true;
		}
		return false;
	}
	
	//moves the snake to Point next
	public void move(Point next, boolean removeTail)
	{
		points.add(0, next);
		if(removeTail)
			points.remove(points.size() - 1);
	}

	//adds a Point pt to the end of the snake
	public void addPoint(Point pt)
	{
		points.add(pt);
	}
	
	//returns the ArrayList of points
	public ArrayList<Point> getPoints()
	{
		return points;
	}
	
}
