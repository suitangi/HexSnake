import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Dimension;

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
public class ControlPanel extends JPanel implements ActionListener{

	public JLabel diffField, scoreField, speedField, diff, score, lengthField, length, display, panel;
	private JLabel highscore, table, n1, n2, n3, n4, n5, s1, s2, s3, s4, s5;
	private JPanel control;
	private Game gm;
	long startTime;

	public ControlPanel(Game game)
	{
		gm = game;
	   // setup GUI:
	   diff = new JLabel("Difficulty:");
	   display = new JLabel("HexSnake");
	   highscore = new JLabel("Highscore");
	   table = new JLabel("Table");
	   panel = new JLabel("Panel");
	   score = new JLabel("Score:");
	   diffField = new JLabel("Easy");
	   length = new JLabel("Length:");
	   lengthField = new JLabel("1");
	   scoreField = new JLabel("0");
	   
	   n1 = new JLabel("1. " + gm.getSKeeper().getScores(gm.getDifficulty())[0].getName());
	   n2 = new JLabel("2. " + gm.getSKeeper().getScores(gm.getDifficulty())[1].getName());
	   n3 = new JLabel("3. " + gm.getSKeeper().getScores(gm.getDifficulty())[2].getName());
	   n4 = new JLabel("4. " + gm.getSKeeper().getScores(gm.getDifficulty())[3].getName());
	   n5 = new JLabel("5. " + gm.getSKeeper().getScores(gm.getDifficulty())[4].getName());
	   
	   s1 = new JLabel(gm.getSKeeper().getScores(gm.getDifficulty())[0].getScore()+ "");
	   s2 = new JLabel(gm.getSKeeper().getScores(gm.getDifficulty())[1].getScore()+ "");
	   s3 = new JLabel(gm.getSKeeper().getScores(gm.getDifficulty())[2].getScore()+ "");
	   s4 = new JLabel(gm.getSKeeper().getScores(gm.getDifficulty())[3].getScore()+ "");
	   s5 = new JLabel(gm.getSKeeper().getScores(gm.getDifficulty())[4].getScore()+ "");
	   
	   
	   control = new JPanel();
	   
	   
	   
	   display.setFont(new Font("Verdana", Font.BOLD, 14));
	   panel.setFont(new Font("Verdana", Font.BOLD, 14));
	   highscore.setFont(new Font("Verdana", Font.BOLD, 14));
	   table.setFont(new Font("Verdana", Font.BOLD, 14));
	   
	   control.setLayout(new GridLayout(21,2,5,5));
	   control.add(new JLabel(""));
	   control.add(new JLabel(""));
	   control.add(display);
	   control.add(panel);
	   control.add(new JLabel(""));
	   control.add(new JLabel(""));
	   control.add(diff);
	   control.add(diffField);
	   control.add(new JLabel(""));
	   control.add(new JLabel(""));
	   control.add(score);
	   control.add(scoreField);
	   control.add(new JLabel(""));
	   control.add(new JLabel(""));
	   control.add(length);
	   control.add(lengthField);
	   control.add(new JLabel(""));
	   control.add(new JLabel(""));
	   control.add(new JLabel(""));
	   control.add(new JLabel(""));
	   control.add(highscore);
	   control.add(table);
	   control.add(new JLabel(""));
	   control.add(new JLabel(""));
	   control.add(n1);
	   control.add(s1);
	   control.add(new JLabel(""));
	   control.add(new JLabel(""));
	   control.add(n2);
	   control.add(s2);
	   control.add(new JLabel(""));
	   control.add(new JLabel(""));
	   control.add(n3);
	   control.add(s3);
	   control.add(new JLabel(""));
	   control.add(new JLabel(""));
	   control.add(n4);
	   control.add(s4);
	   control.add(new JLabel(""));
	   control.add(new JLabel(""));
	   control.add(n5);
	   control.add(s5);

	   
	   //write it here
	   add(control);
	   this.setPreferredSize(new Dimension(150,500));

	   
	}

	public ArrayList<JLabel> getAllLabels()
	{
		ArrayList<JLabel> labels = new ArrayList<JLabel>();
		labels.add(display);
		labels.add(panel);
		labels.add(diff);
		labels.add(diffField);
		labels.add(score);
		labels.add(scoreField);
		labels.add(length);
		labels.add(lengthField);
		labels.add(highscore);
		labels.add(table);
		labels.add(n1);
		labels.add(n2);
		labels.add(n3);
		labels.add(n4);
		labels.add(n5);
		labels.add(s1);
		labels.add(s2);
		labels.add(s3);
		labels.add(s4);
		labels.add(s5);
		
		return labels;
	}
	
	
	public void update(int score)
	{
		scoreField.setText(String.format("%03d", score));
		
	}

	public JPanel getControl()
	{
		return control;
	}
	public JLabel getDiffField()
	{
		return diffField;
	}
	public JLabel getLengthField()
	{
		return lengthField;
	}

	public void hsupdate()
	{
	   n1.setText("1. " + gm.getSKeeper().getScores(gm.getDifficulty())[0].getName());
	   n2.setText("2. " + gm.getSKeeper().getScores(gm.getDifficulty())[1].getName());
	   n3.setText("3. " + gm.getSKeeper().getScores(gm.getDifficulty())[2].getName());
	   n4.setText("4. " + gm.getSKeeper().getScores(gm.getDifficulty())[3].getName());
	   n5.setText("5. " + gm.getSKeeper().getScores(gm.getDifficulty())[4].getName());
	   
	   s1.setText(gm.getSKeeper().getScores(gm.getDifficulty())[0].getScore()+ "");
	   s2.setText(gm.getSKeeper().getScores(gm.getDifficulty())[1].getScore()+ "");
	   s3.setText(gm.getSKeeper().getScores(gm.getDifficulty())[2].getScore()+ "");
	   s4.setText(gm.getSKeeper().getScores(gm.getDifficulty())[3].getScore()+ "");
	   s5.setText(gm.getSKeeper().getScores(gm.getDifficulty())[4].getScore()+ "");
	}


	public void actionPerformed(ActionEvent e) {
		JButton src = (JButton)e.getSource();
		
		
	}
	
	
}
