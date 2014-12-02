package se.lina.view;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JPanel;

import se.lina.controller.MemoryBoardController;

public class GameBoardJPanel extends JPanel {

	private static final long serialVersionUID = -1140924105481123604L;
	private int marginals;

	public GameBoardJPanel(int noOfRows, int noOfColumns, int marginals) {
		super();
		this.marginals = marginals;
		this.setName("GAME BOARD");
		this.setBackground(Color.CYAN);

		this.setLayout(new GridLayout(noOfRows, noOfColumns, marginals,
				marginals));
		
		
		
	}
	
	void changeBoardSize(int noOfRows, int noOfColumns){
		this.setLayout(new GridLayout(noOfRows, noOfColumns, marginals, marginals));
	}
	
	

}
