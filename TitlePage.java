import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JSlider;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.util.ArrayList;
public class TitlePage extends JFrame implements ActionListener{
	
	private JFrame goo;

	  private JButton easy, medium, hard, quit;
	  private JPanel j1;
	  private JLabel title;
	

	public TitlePage()
	{
		 this.setName("HexSnake");
		 this.setTitle("HexSnake");
		 title = new JLabel("Select a difficulty to begin:");
		 easy = new JButton("EASY");
		 easy.addActionListener(this);
		 this.setResizable(false);
		 
		 quit = new JButton("QUIT");
		 quit.addActionListener(this);
		 medium = new JButton("MEDIUM");
		 medium.addActionListener(this);
		 
		 hard = new JButton("HARD");
		 hard.addActionListener(this);
		
		 j1= new ImagePanel(new ImageIcon("background1.png").getImage());
//		 j1= new ImagePanel(new ImageIcon(getClass().getResource("background1.png")).getImage());

		 title.setFont(new Font("Verdana", Font.PLAIN, 18));
		 title.setForeground(Color.CYAN);
		 goo = new JFrame("HexSnake");
		 goo.setBounds(50, 50, 750, 600);
		 goo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		j1.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		
		   j1.add(new JLabel());
		   j1.add(new JLabel(""));
		   j1.add(new JLabel(""));
//		   j1.add(title);
		   j1.add(new JLabel(""));
		   j1.add(new JLabel(""));


	
		   
		   j1.setPreferredSize(new Dimension(50,50));
		   
		   JPanel bottomPanel = new JPanel();
	        bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
	        bottomPanel.setBackground(Color.BLACK);
	        bottomPanel.add(title);
	        bottomPanel.add(easy);
	        bottomPanel.add(medium);
	        bottomPanel.add(hard);
	        bottomPanel.add(quit);
	        goo.add(bottomPanel,BorderLayout.PAGE_END);
		   goo.add(j1);
		   goo.setPreferredSize(new Dimension(100,100));
		   goo.setVisible(true);

		   

	}


	@Override
	public void actionPerformed(ActionEvent e) {
		JButton src = (JButton)e.getSource();
		 if (src == easy)
		    {

			    
			 	goo.dispose();
			 	JFrame window = new JFrame("HexSnake");
			    window.setBounds(50, 50, 750, 600);
			    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			    window.setResizable(false);
			    Game game = new Game(new Snake(), 3);
			    JMenuBar menu = new SnakeMenu(game);
			   
			    window.setJMenuBar(menu);
			    //JPanel a = new TitlePage(game);
			    
			   
			    Box box = Box.createHorizontalBox();
			    game.setPreferredSize(new Dimension(400,400));
			    box.add(game);
			    //box.add(a);
			    game.getCPanel().setPreferredSize(new Dimension(50,400));
			  
			  
			    box.add(game.getCPanel());
			    window.getContentPane().add(box);
			    window.setLocation(goo.getLocation());
			    window.setVisible(true);
			    

			 	
	    			
		    }
		 if (src == medium)
		    {

			    
			 	goo.dispose();
			 	JFrame window = new JFrame("HexSnake");
			    window.setBounds(50, 50, 750, 600);
			    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			    window.setResizable(false);
			    Game game = new Game(new Snake(), 2);
			    JMenuBar menu = new SnakeMenu(game);
			   
			    window.setJMenuBar(menu);
			    //JPanel a = new TitlePage(game);
			    
			   
			    Box box = Box.createHorizontalBox();
			    game.setPreferredSize(new Dimension(400,400));
			    box.add(game);
			    //box.add(a);
			    game.getCPanel().setPreferredSize(new Dimension(50,400));
			  
			  
			    box.add(game.getCPanel());
			    window.getContentPane().add(box);
			    window.setLocation(goo.getLocation());
			    window.setVisible(true);

			 	
	    			
		    }
		 if (src == hard)
		    {

			    
			 	goo.dispose();
			 	JFrame window = new JFrame("HexSnake");
			    window.setBounds(50, 50, 750, 600);
			    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			    window.setResizable(false);
			    Game game = new Game(new Snake(), 1);
			    JMenuBar menu = new SnakeMenu(game);
			   
			    window.setJMenuBar(menu);
			    //JPanel a = new TitlePage(game);
			    
			   
			    Box box = Box.createHorizontalBox();
			    game.setPreferredSize(new Dimension(400,400));
			    box.add(game);
			    //box.add(a);
			    game.getCPanel().setPreferredSize(new Dimension(50,400));
			  
			  
			    box.add(game.getCPanel());
			    window.getContentPane().add(box);
			    window.setLocation(goo.getLocation());
			    window.setVisible(true);

			 	
	    			
		   }
		 if (src == quit)
			 System.exit(0);
		
	}
}