import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JColorChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;


public class SnakeMenu extends JMenuBar implements ActionListener{

	private Game game;
	private JMenu file, help, pref, diff, appear, colors, colother;
	private JMenuItem newg, exit, how, about, gcolor, bcolor, fcolor, theme;
	private JCheckBoxMenuItem hard, medium, easy, rainbow, irri, nyan;

	//constructor:
	public SnakeMenu(Game sngame)
	{
	     game = sngame;
	     file = new JMenu("File");
	     pref = new JMenu("Perefrences");
	     help = new JMenu("Help");
	     diff = new JMenu("Difficulty");
	     appear = new JMenu("Appearence");
	     colors = new JMenu("Colors");
	     colother = new JMenu("Other");
	     
	     file.setMnemonic('F');
	     pref.setMnemonic('P');
	     help.setMnemonic('H');
	     appear.setMnemonic('A');
	     diff.setMnemonic('D');
	     
	     hard = new JCheckBoxMenuItem("Difficult", game.getDifficulty() == 1);
	     hard.addActionListener(this);
	     rainbow = new JCheckBoxMenuItem("Rainbow", false);
	     rainbow.addActionListener(this);
	     irri = new JCheckBoxMenuItem("Irridescent", false);
	     irri.addActionListener(this);
	     nyan = new JCheckBoxMenuItem("Nyan Cat", false);
	     nyan.addActionListener(this);
	     medium = new JCheckBoxMenuItem("Medium", game.getDifficulty() == 2);
	     medium.addActionListener(this);
	     easy = new JCheckBoxMenuItem("Easy", game.getDifficulty() == 3);
	     easy.addActionListener(this);
	     
	     newg = new JMenuItem("New Game");
	     newg.addActionListener(this);
	     exit = new JMenuItem("Exit");
	     exit.addActionListener(this);
	     gcolor = new JMenuItem("Grid & Snake");
	     gcolor.addActionListener(this);
	     bcolor = new JMenuItem("Background");
	     bcolor.addActionListener(this);
	     fcolor = new JMenuItem("Food");
	     fcolor.addActionListener(this);
	     theme = new JMenuItem("Theme");
	     theme.addActionListener(this);
	     
	     how = new JMenuItem("How to play");
	     how.addActionListener(this);
	     about = new JMenuItem("About");
	     about.addActionListener(this);
	     
	     add(file);
//	     add(pref);
	     add(appear);
	     add(diff);
	     add(help);
	     
	     appear.add(colors);
	     appear.add(colother);
	     
	     colors.add(gcolor);
	     colors.add(bcolor);
	     colors.add(fcolor);
	     
	     colother.add(theme);
	     colother.add(rainbow);
	     colother.add(irri);
	     colother.add(nyan);
		    
	     
	     file.add(newg);
	     file.add(exit);
	     
	     diff.add(hard);
	     diff.add(medium);
	     diff.add(easy);
	     
	     help.add(how);
	     help.add(about);
	}

	public JCheckBoxMenuItem getHard()
	{
		return hard;
	}
	public JCheckBoxMenuItem getMedium()
	{
		return medium;
	}
	public JCheckBoxMenuItem getEasy()
	{
		return easy;
	}
	
	
	public void actionPerformed(ActionEvent e)
	{
		JMenuItem src = (JMenuItem)e.getSource();
	    if (src == newg)
	    {
	    	if(JOptionPane.showConfirmDialog(null, "Are you sure you want to restart?",
    				"Restart?", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
    			game.newGame();
	    }
	    if(src == exit)
	    {
	    	if(JOptionPane.showConfirmDialog(null, "Are you sure you want to exit?",
	    				"Exit?", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
	    		System.exit(0);
	    }
	    
	    
	    if(src == hard)
	    {
	    	if (!game.getGameStarted())
		    {
		    	game.setDifficulty(1);
		    	hard.setSelected(true);
		    	medium.setSelected(false);
		    	easy.setSelected(false);
		    }
	    	else
	    		hard.setSelected(!hard.getState());
	    }
	    if(src == medium)
	    {
	    	if (!game.getGameStarted())
		    {
		    	game.setDifficulty(2);
		    	medium.setSelected(true);
		    	hard.setSelected(false);
		    	easy.setSelected(false);
		    }
	    	else
	    		medium.setSelected(!medium.getState());
	    }
	    if(src == easy)
	    {
	    	if (!game.getGameStarted())
		    {
		    	game.setDifficulty(3);
		    	easy.setSelected(true);
		    	medium.setSelected(false);
		    	hard.setSelected(false);
		    }
	    	else
	    		easy.setSelected(!easy.getState());
		}
	    
	    if(src == rainbow)
	    {
	    	game.setRainbow();
	    }
	    
	    if(src == irri)
	    {
	    	game.setIrri();
	    }
	    if(src == nyan)
	    {
	    	try {
				game.setNyan();
			} catch (UnsupportedAudioFileException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			} catch (LineUnavailableException e1) {
				e1.printStackTrace();
			}
	    }
	    
	    if(src == gcolor)
	    {
	    	Color pickedColor = JColorChooser.showDialog(this,
                    "Pick a color for the grid and snake", game.getGridColor());
	    	if (pickedColor != null)
	    		game.setGridColor(pickedColor);
	    }
	    if(src == bcolor)
	    {
	    	Color pickedColor = JColorChooser.showDialog(this,
                    "Pick a color for the background", game.getBackColor());
	    	if (pickedColor != null)
	    		game.setBackColor(pickedColor);
	    }
	    if(src == fcolor)
	    {
	    	Color pickedColor = JColorChooser.showDialog(this,
                    "Pick a color for the food", game.getFoodColor());
	    	if (pickedColor != null)
	    		game.setFoodColor(pickedColor);
	    }
	    if(src == theme)
	    {
	    	Color pickedColor = JColorChooser.showDialog(this,
                    "Pick a color for the theme", game.getGridColor());
	    	if (pickedColor != null)
	    	{
	    		game.theme(pickedColor);
	    	}
	    }
		    
	    if(src == about)
	    {
	    	game.pauseGame();
		    game.setAbout(true);
	    }
	    
	    if(src == how)
	    {
	    	game.pauseGame();
		    game.setHow(true);
	    }
	}
	    
	    	
		
	
}
