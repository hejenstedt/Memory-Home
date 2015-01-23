package se.lina.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class PlayerViewJPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	public PlayerViewJPanel(String name, String score2, String noOfMoves2) {
		setLayout(new GridLayout(1, 3));

		JLabel player = new JLabel(name);
		setLabelSubHeaderFormat(player);

		JLabel score = new JLabel(score2);
		setLabelSubHeaderFormat(score);
		
		JLabel noOfMoves = new JLabel(noOfMoves2);
		setLabelSubHeaderFormat(noOfMoves);
		
		add(player);
		add(score);
		add(noOfMoves);
		setPreferredSize(new Dimension(250, 50));	
	}
	
	private void setLabelSubHeaderFormat(JLabel labelName) {
		labelName.setFont(new Font("Arial", Font.BOLD, 15));
		labelName.setForeground(Color.DARK_GRAY);
		labelName.setHorizontalAlignment(JLabel.CENTER);
	}

	
}
