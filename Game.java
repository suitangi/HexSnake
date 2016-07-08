
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;


public class Game extends JPanel implements ActionListener, KeyListener{

	private int gridLength = 20;
	private int gridWidth = 20;
	private int score = 0;
	private int time = 0;
	private Snake snake;
	private Food food;
	private Point grid[][];
	private ControlPanel controlpanel;
	private int difficulty = 3; // 1 = difficult; 2 = medium, 3 = easy
	private Color gColor;
	private Color fdColor = Color.ORANGE;
	private Color bColor;
	private boolean started = false;
	private boolean paused = false;
	private boolean rainbow = false;
	private boolean how = false;
	private boolean about = false;
	private boolean irri = false;
	private boolean nyan = false;
	private AudioInputStream nyanmu;
	private Clip clip;
	private ScoreKeeper sk = new ScoreKeeper();
	
	//constructor:
	public Game(Snake sn, int dif)
	{
		sk.importData();
		snake = sn;
		difficulty = dif;
		setFocusable(true);
		//makes a food at a random, empty Point
		food = new Food(foodPoint());
		addKeyListener(this);
		controlpanel = new ControlPanel(this);
		Timer clock = new Timer(50, this);
		clock.start();
		setBackColor(Color.BLACK);
		setGridColor(Color.CYAN);
		

		grid = new Point[gridLength][gridWidth];

	    // fills the grid with respective points
	    int r = 0, c = 0;
	    while(r < gridLength)
	    {
	    	c = 0;
	    	while (c < gridWidth)
	    	{
	    		grid[r][c] = new Point(c, r);
	    		c++;
	    	}
	    	r++;
	    }

		
	}
	
	public void paintComponent(Graphics g)
	{
		this.setBackground(bColor);
		g.setColor(gColor);
	    super.paintComponent(g);
	    
	    
	    //draws the grid
	    int r = 0;
	    int c = 0;
	    while(r < gridLength)
	    {
	    	c = 0;
	    	while (c < gridWidth)
	    	{
	    		drawHex(g, grid[r][c], false);
	    		c++;
	    	}
	    	r++;
	    }
	    g.drawLine(520, 0, 520, 550);
	    
	    drawSnake(g);
	    drawFood(g);
	    
	    if(paused)
	    {
	    	g.setColor(bColor);
	    	g.fillRect(93, 238, 340, 52);
	    	g.setColor(gColor);
	    	g.drawRect(93, 238, 340, 52);
	    	g.setFont(new Font("Verdana", Font.BOLD, 42));
	    	g.drawString("Game Paused", 100, 280);
	    }
	    repaint();
	    
	    //how to play
	    if (how)
	    {
	    	g.setColor(bColor);
	    	g.fillRect(50, 100, 450, 270);
	    	
	    	g.setFont(new Font("Arial", Font.PLAIN, 12));
	    	g.setColor(gColor);
	    	g.drawString("Use the keys Q, W, E, A, S, D as shown on", 235, 180);
	    	g.drawString("the figure on the right to move the snake.", 235, 194);
	    	g.drawString("Use the P key to pause the game, and the N", 235, 208);
	    	g.drawString("key for a new game. The colors of the game", 235, 222);
	    	g.drawString("can be changed under the appearance menu.", 235, 236);
	    	g.drawString("The difficulty can be changed under the", 235, 250);
	    	g.drawString("difficulty menu. To start, move the snake in", 235, 264);
	    	g.drawString("any direction, with the Q, W, E, A, S, D keys.", 235, 278);
	    	
	    	g.setFont(new Font("Verdana", Font.BOLD, 14));
	    	g.drawString("How to Play", 225, 120);
	    	g.drawRect(50, 100, 450, 270);
	    	g.drawString("*Press Space to continue*",175, 343);
	    	g.drawString("Q", 90, 212);
	    	g.drawString("E", 197, 212);
	    	g.drawString("W", 144, 180);
	    	g.drawString("A", 90, 273);
	    	g.drawString("D", 197, 273);
	    	g.drawString("S", 144, 305);
	    	
	    	int x = 90, y = 240;
	    	int xval[] = {x, x+32, x+88, x+120, x+88, x+32};
			int yval [] = {y, y - 52, y - 52, y , y+48, y+48};
			g.drawPolygon(xval, yval, 6);
	    }
	    if (about)
	    {
	    	g.setColor(bColor);
	    	g.fillRect(50, 100, 450, 270);
	    	
	    	g.setFont(new Font("Arial", Font.PLAIN, 12));
	    	g.setColor(gColor);
	    	g.drawString("HexSnake is a twist on the classic snake game. Instead", 170, 180);
	    	g.drawString("of four directions, the snake can move in six different", 170, 194);
	    	g.drawString("directions. Thus, the grid is consisted of hexagons.", 170, 208);

	    	g.drawString("No part of this program may be duplicated, copied,", 140, 256);
	    	g.drawString("recorded without the permission of the creators.", 148, 270);
	    	g.drawString("Copyright (C) 2014 by Ignatius Liu and Jeffery Cao. All rights reserved.", 90, 284);
	    	g.drawString("Concept and design by Ignatius Liu and Jeffery Cao", 138, 298);
	    	
	    	g.setFont(new Font("Verdana", Font.BOLD, 14));
	    	g.drawString("About HexSnake", 220, 120);
	    	g.drawRect(50, 100, 450, 270);
	    	g.drawString("*Press Space to continue*",175, 343);

	    	g.setFont(new Font("Verdana", Font.BOLD, 42));
	    	g.drawString("?", 100, 207);
	    	g.drawOval(85, 163 , 55, 55);
	    	g.drawOval(84, 162 , 57, 57);
	    	g.drawOval(83, 161 , 59, 59);
	    }
	    repaint();


	   
	 }
	
	 //draws a hexagon in the set coordinates of Point pt, 
	 //if filled is true, then the hexagon will be filled
	 public void drawHex(Graphics g, Point pt, boolean filled)
	 {
		 int x = (pt.getX() * 22) + 36, y;
		 if(pt.getX() % 2 == 1) //if it is in the odd column
			 y = (pt.getY() * 24) + 47;
		 else
			 y = (pt.getY() * 24) + 34;
		 
		 int xval[] = {x, x+8, x+22, x+30, x+22, x+8};
		 int yval [] = {y, y - 13, y - 13, y , y+12, y+12};
		 if (filled)
			 g.fillPolygon(xval, yval, 6);
		 else
			 g.drawPolygon(xval, yval, 6);

	 }


	 
	 //draws the snakehead
	 public void drawSnakeHead(Graphics g)
	 {
		 if(nyan)
		 {
			 g.setColor(bColor);
			 drawHex(g, snake.getHead(), true);

			 Image img1 = Toolkit.getDefaultToolkit().getImage("nyan.png");

				 Point pt = snake.getHead();
				 int x = (pt.getX() * 22) + 36, y;
				 if(pt.getX() % 2 == 1) //if it is in the odd column
					 y = (pt.getY() * 24) + 47;
				 else
					 y = (pt.getY() * 24) + 34;			 
				 g.drawImage(img1, x-3,y-13, this);
			 
		 }
		 else
		 {
			 drawHex(g, snake.getHead(), true);
			 g.setColor(new Color((int)(gColor.getRed() / 2.4), (int)(gColor.getGreen() / 2.4),
		 				(int)(gColor.getBlue() / 2.4)));
			 if(gColor.getRed() < 60 && gColor.getGreen() < 60 && gColor.getBlue() < 60)
				 g.setColor(new Color((int)(gColor.getRed() + 80), (int)(gColor.getGreen() + 80),
			 				(int)(gColor.getBlue() + 80)));
			
			 Point pt = snake.getHead();
			 int x = (pt.getX() * 22) + 36, y;
			 if(pt.getX() % 2 == 1) //if it is in the odd column
				 y = (pt.getY() * 24) + 47;
			 else
				 y = (pt.getY() * 24) + 34;
			 
			 switch(snake.getDirection())
			 {
			 	case 0:
			 		g.fillOval(x + 9, y - 11, 4, 4);
			 		g.fillOval(x + 17, y - 11, 4, 4);
			 		break;
			 	case 1:
			 		g.fillOval(x + 19, y - 11, 4, 4);
			 		g.fillOval(x + 23, y - 5, 4, 4);
			 		break;
			 	case 2:
			 		g.fillOval(x + 19, y + 4, 4, 4);
			 		g.fillOval(x + 23, y - 2, 4, 4);
			 		break;
			 	case 3:
			 		g.fillOval(x + 9, y + 6, 4, 4);
			 		g.fillOval(x + 17, y + 6, 4, 4);
			 		break;
			 	case 4:
			 		g.fillOval(x + 8, y + 4, 4, 4);
			 		g.fillOval(x + 4, y - 2, 4, 4);
			 		break;
			 	case 5:
			 		g.fillOval(x + 8, y - 11, 4, 4);
			 		g.fillOval(x + 4, y - 5, 4, 4);
			 		break;
			 }
		 }
	 }
	 
	 //draws the snake
	 public void drawSnake(Graphics g)
	 {
		 Color color = gColor;
		 ArrayList<Point> points = snake.getPoints();
		 g.setColor(gColor);
		 double rr = 1.0 + (color.getRed() / 510.0), bb = 1.0 + (color.getBlue() / 510.0), 
			 gg = 1.0 + (color.getGreen() / 510.0);
		 int counter = 1;
		 Image img2 = Toolkit.getDefaultToolkit().getImage("rainbow.png");
		 for(Point pt: points)
		 {
			 int y;
			 if(pt.getX() % 2 == 1) //if it is in the odd column
				 y = (pt.getY() * 24) + 47;
			 else
				 y = (pt.getY() * 24) + 34;
			 drawHex(g, pt, true);
			 if(nyan && counter == 2)
			 {	
				 g.setColor(bColor);
				 drawHex(g, pt, true);
				 Image img1 = Toolkit.getDefaultToolkit().getImage("poptart.png");
				 g.drawImage(img1, (pt.getX() * 22) + 37, y-10, this);
				 
			 }
			 if(nyan && counter > 2)
			 {	
				 g.setColor(bColor);
				 drawHex(g, pt, true);
				 g.drawImage(img2, (pt.getX() * 22) + 37, y-13, this);
			 }
			 if (irri)
			 {
				 color = color.brighter();
				 color = new Color((int)(color.getRed() / bb), (int)(color.getGreen() / rr),
		 				(int)(color.getBlue() / gg));
				 g.setColor(color);
			 }
			counter++;
		 }
		 g.setColor(gColor);
		 drawSnakeHead(g);
	 }
	 
	 //draws the food with the Color color
	 public void drawFood(Graphics g)
	 {
		 Point pt = food.getPos();
		 g.setColor(fdColor);
		 drawHex(g, pt, true);
		 g.setColor(gColor);
		 drawHex(g, pt, false);
		 
	 }
	 
	 //finds a random, empty Point for food
	 public Point foodPoint()
	 {
		 Point fpos = new Point((int)(Math.random() * gridWidth),
					(int)(Math.random() * gridLength));
			boolean empty = false;
			while(!empty)
			{
				fpos = new Point((int)(Math.random() * gridWidth),
						(int)(Math.random() * gridLength));
				empty = !snake.checkPos(fpos);
			}
			return fpos;
	 }
	 
	 //restarts the game
	 public void newGame()
	 {
		 snake = new Snake();
		 score = 0;
		 food = new Food(foodPoint());
		 time = 0;
		 started = false;
		 controlpanel.update(score);
		 
		 repaint();
	 }
	 
	 //displays game over message, restarts
	 public void gameOver()
	 {
		 ScoreEntry other = sk.getScores(difficulty)[4];
			
		int i = 5;
		while (score > other.getScore() && i >= 0)
		{
			i--;
			if(i > 0)
				other = sk.getScores(difficulty)[i - 1];
		}
		if (i == -1)
			i++;
		if (i < 5)
		{
			String name = JOptionPane.showInputDialog("Your name:", "Player");
			int k = 4;
			while(k > i)
			{
			    sk.getScores(difficulty)[k] = sk.getScores(difficulty)[k - 1];
				k--;
			}
			sk.getScores(difficulty)[i] = new ScoreEntry(name, score);
			
		}
		
		 sk.exportData(difficulty);
		 String diff;
		 if (difficulty == 1)
		 {
			 diff = "Difficult";
		 }
		 else if (difficulty == 2)
		 {
			 diff = "Medium";
		 }
		 else
		 {
			 diff = "Easy";
		 }
		 JOptionPane.showMessageDialog(null, "Game over!\n" + "Game Mode: " + diff + 
				 "\nYour final score was: " + score + "\nSnake Length:" + snake.getPoints().size(), "Game Over", 
				 JOptionPane.INFORMATION_MESSAGE);
		 controlpanel.hsupdate();
		 newGame();
		 

		 
	 }
	 
	 public void actionPerformed(ActionEvent arg0)
	 {
		 	 time++;
		 	 
		 	 //sets the difficulty text in controlPanel
			 if (difficulty == 1)
			 {
				 controlpanel.getDiffField().setText("Difficult");
			 }
			 else if (difficulty == 2)
			 {
				 controlpanel.getDiffField().setText("Medium");
			 }
			 else
			 {
				 controlpanel.getDiffField().setText("Easy");
			 }
			 
			 
			 //sets the length test in the controlPanel
			 controlpanel.getLengthField().setText(snake.getPoints().size() + "");
			 
			//finds the next point
			Point head = snake.getHead(), next = new Point();
			if (started && time % (2 * difficulty) == 0 && !paused)
			{
				
				if (rainbow)
				{
					Random r = new Random();
					setBackColor(Color.BLACK);
					Color randomColor = new Color(r.nextFloat(), r.nextFloat(), r.nextFloat());
					randomColor = randomColor.brighter();
					setGridColor(randomColor);
				}
				
				snake.setDirection(snake.getNextDir());
				if(snake.getHead().getX() % 2 == 0) // if the snake head is in an odd column
				{
					switch(snake.getDirection())
					{
						case 0:
							next = new Point(head.getX(), head.getY() - 1);
							break;
						case 1:
							next = new Point(head.getX() + 1, head.getY() - 1);
							break;
						case 2:
							next = new Point(head.getX() + 1, head.getY());
							break;
						case 3:
							next = new Point(head.getX(), head.getY() + 1);
							break;
						case 4:
							next = new Point(head.getX() - 1, head.getY());
							break;
						case 5:
							next = new Point(head.getX() - 1, head.getY() - 1);
							break;
					}
				}
				else // if the snake head is in an even column
				{
					switch(snake.getDirection())
					{
						case 0:
							next = new Point(head.getX(), head.getY() - 1);
							break;
						case 1:
							next = new Point(head.getX() + 1, head.getY() );
							break;
						case 2:
							next = new Point(head.getX() + 1, head.getY() + 1);
							break;
						case 3:
							next = new Point(head.getX(), head.getY() + 1);
							break;
						case 4:
							next = new Point(head.getX() - 1, head.getY() + 1);
							break;
						case 5:
							next = new Point(head.getX() - 1, head.getY());
							break;
					}
				}
				
				
				//moves the snake depending on the condition
				int check = snake.checkPoint(gridWidth, gridLength, next, food);
				
					if (check == 0) // if next is empty
						snake.move(next, true);
					else if (check == 2) // if next contains a fruit
					{
						snake.move(next, false);
						food = new Food(foodPoint());
						score += (20 + snake.getPoints().size());
						controlpanel.update(score);
					}	
					else //if next will cause the snake to die
						gameOver();
					
					repaint();

			}
			
		}

	 public void keyPressed(KeyEvent e) 
		{	
			
			switch(e.getKeyCode())
			{
				case KeyEvent.VK_W:
					if (!started && !e.isAltDown())
					{
						started = true;
					}
					if(!paused && (snake.getPoints().size() == 1 || snake.getDirection() != 3))
							snake.setNextDir(0);
					break;
				case KeyEvent.VK_E:
					if (!started && !e.isAltDown())
					{
						started = true;
					}
					if(!paused && (snake.getPoints().size() == 1 || snake.getDirection() != 4))
						snake.setNextDir(1);
					break;
				case KeyEvent.VK_D:
					if (!started && !e.isAltDown())
					{
						started = true;
					}
					if(!paused && (snake.getPoints().size() == 1 || snake.getDirection() != 5))
						snake.setNextDir(2);
					break;
				case KeyEvent.VK_S:
					if (!started && !e.isAltDown())
					{
						started = true;
					}
					if(!paused && (snake.getPoints().size() == 1 || snake.getDirection() != 0))
						snake.setNextDir(3);
					break;
				case KeyEvent.VK_A:
					if (!started && !e.isAltDown())
					{
						started = true;
					}
					if(!paused && (snake.getPoints().size() == 1 || snake.getDirection() != 1))
						snake.setNextDir(4);
					break;
				case KeyEvent.VK_Q:
					if (!started && !e.isAltDown())
					{
						started = true;
					}
					if(!paused && (snake.getPoints().size() == 1 || snake.getDirection() != 2))
						snake.setNextDir(5);
					break;
				case KeyEvent.VK_P:
					pauseGame();
					break;
				case KeyEvent.VK_N:
			    	if(JOptionPane.showConfirmDialog(null, "Are you sure you want to restart?",
		    				"Restart?", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
		    			newGame();
		    		break;
				case KeyEvent.VK_ESCAPE:
			    	if(JOptionPane.showConfirmDialog(null, "Are you sure you want to exit?",
			    				"Exit?", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
			    		System.exit(0);
			    	break;
				case KeyEvent.VK_SPACE:
					if (how)
					{
						how = false;
						pauseGame();
					}
					if (about)
					{
						about = false;
						pauseGame();
					}
			}
			
		}
	 
	 //sets the grid and snake color
	 public void setGridColor(Color color)
	 {
		 ArrayList<JLabel> labels = controlpanel.getAllLabels();
		 for(JLabel label: labels)
		 {
			 label.setForeground(color);
		 }
		 gColor = color;
		 repaint();
	 }
	 
	 //sets the background color
	 public void setBackColor(Color color)
	 {
		 bColor = color;
		 controlpanel.getControl().setBackground(bColor);
		 controlpanel.setBackground(bColor);
		 ArrayList<JLabel> labels = controlpanel.getAllLabels();
		 for(JLabel label: labels)
		 {
			 label.setBackground(color);
		 }


		 repaint();
		 
	 }
	 
	 //sets the food color
	 public void setFoodColor(Color color)
	 {
		 fdColor = color;
		 repaint();
	 }
	 
	 //returns the grid and snake color
	 public Color getGridColor()
	 {
		 return gColor;
	 }
	 
	 //returns the background color
	 public Color getBackColor()
	 {
		 return bColor;
	 }
	 
	 //returns the food color
	 public Color getFoodColor()
	 {
		 return fdColor;
	 }
	 
	 //pauses or unpauses the game
	 public void pauseGame()
	 {
		 paused = !paused;
	 }
	 
	 //starts the game
	 public void startGame()
	 {
		 started = true;
	 }
	 
	 //returns true if the game has started
	 public boolean getGameStarted()
	 {
		 return started;
	 }
	 
	 //sets the difficulty of the game: 1 = difficult; 2 = medium, 3 = easy
	 public void setDifficulty(int diff)
	 {
		 difficulty = diff;
		 controlpanel.hsupdate();
	 }
	 
	 //gets the difficulty of the game: 1 = difficult; 2 = medium, 3 = easy
	 public int getDifficulty()
	 {
		 return difficulty;
	 }
	 
	 //turns on/off the rainbow mode
	 public void setRainbow()
	 {
		 rainbow = !rainbow;
	 }
	 
	 //turns on/off the NYAN mode
	 public void setNyan() throws UnsupportedAudioFileException, IOException, LineUnavailableException
	 {
		 
		 nyan = !nyan;
		 if (nyan)
		 {
			 File file = new File("nyanmusic.wav");
			 nyanmu = AudioSystem.getAudioInputStream(file);
			 clip = AudioSystem.getClip();
			 clip.open(nyanmu);
			 clip.start();
			 clip.loop(900);
		 }
		 else
		 {
			 clip.stop();
		 }
	 }
	 
	 //turns on/off the irridescent mode
	 public void setIrri()
	 {
		 irri = !irri;
	 }
	 
	//sets the how to play boolean
	 public void setHow(boolean set)
	 {
		 how = set;
	 }
	 
	 //sets the about boolean
	 public void setAbout(boolean set)
	 {
		 about = set;
	 }
	 
	 //returns the scoreKeeper
	 public ScoreKeeper getSKeeper()
	 {
		 return sk;
	 }
	 
	 //returns the ControlPanel
	 public ControlPanel getCPanel()
	 {
		 return controlpanel;
	 }
	 
	 //the theme function that sets a theme using a color
	 public void theme(Color color)
	 {
		setGridColor(color);
 		setFoodColor(new Color((int)(color.getRed() / 1.3), (int)(color.getGreen() / 1.3),
 				(int)(color.getBlue() / 1.3)));
 		setBackColor(new Color((int)(color.getRed() / 1.8), (int)(color.getGreen() / 1.8),
 				(int)(color.getBlue() / 1.8)));
	 }
	 
	 public static void main(String[] args)
	 {
		 
//		  // Set system look and feel:
//		  String plafName = UIManager.getSystemLookAndFeelClassName(); 
//		  try 
//		  { 
//			  UIManager.setLookAndFeel(plafName); 
//		  } 
//		  catch (Exception ex) 
//		  { 
//			  JOptionPane.showMessageDialog(null, "*** " + plafName + " PLAF not installed ***", "Error", 
//						 JOptionPane.ERROR_MESSAGE);
//		  }
//		
		
	       JFrame a = new TitlePage();
	    
	  }

	
	//unused implemented methods
	public void keyReleased(KeyEvent e) { }

	public void keyTyped(KeyEvent e) { }
	
}
	
			

