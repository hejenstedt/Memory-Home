package se.lina.view;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import se.lina.controller.MemoryBoardController;
import se.lina.model.Tile;

public class GameBoardJPanel extends JPanel {

	private static final long serialVersionUID = -1140924105481123604L;
	private int marginals;
	private int noOfRows;
	private int noOfColumns;
	private MemoryBoardController controller;
	private ArrayList<JButtonTile> buttonList;
	private ImageIcon backgroundImage;
	private String fileWay;

	public GameBoardJPanel(int noOfRows, int noOfColumns, int marginals,
			MemoryBoardController controller, ImageIcon backgroundImage, String fileway) {
		super();
		this.controller = controller;
		this.noOfRows = noOfRows;
		this.noOfColumns = noOfColumns;
		this.marginals = marginals;
		this.backgroundImage = backgroundImage;
		this.fileWay = fileway;
		this.setName("GAME BOARD");
		this.setBackground(Color.CYAN);
		buttonList = new ArrayList<>();
		this.setLayout(new GridLayout(noOfRows, noOfColumns, marginals,
				marginals));
		

	}

	public void addButtonsForTiles(int buttonSize) {
		for (int i = 0; i < noOfRows; i++) {
			for (int j = 0; j < noOfColumns; j++) {
				JButtonTile tempButton = new JButtonTile(i, j, controller,
						buttonSize, backgroundImage);
				tempButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent event) {
						tempButton.onClick();
					}
				});
				add(tempButton);
				buttonList.add(tempButton);
			}
		}
	}

	void changeBoardSize(int noOfRows, int noOfColumns) {
		this.setLayout(new GridLayout(noOfRows, noOfColumns, marginals,
				marginals));
	}
	
	public void updateTurnedTile(Tile tile, int row, int column) {
		for (JButtonTile jButton : buttonList) {
			if (jButton.getRow() == row && jButton.getColumn() == column) {
				jButton.changeIcon(tile.isFaceUp() ? new ImageIcon(fileWay
						+ tile.getValue() + ".gif") : backgroundImage);
				jButton.setName(tile.isFaceUp() ? tile.getValue() : "not turned");
			}
		}
	}
	
	public void updateAllTiles(Tile selectedTile, Tile lastTile) {
		for (JButtonTile jButtonTile : buttonList) {
			if (jButtonTile.getName().equals(lastTile.getValue())) {
				jButtonTile.changeIcon(lastTile.isFaceUp() ? new ImageIcon(
						fileWay + lastTile.getValue() + ".gif")
						: backgroundImage);
				jButtonTile.setName(lastTile.isFaceUp() ? lastTile.getValue()
						: "not turned");
			}
			if (jButtonTile.getName().equals(selectedTile.getValue())) {
				jButtonTile.changeIcon(selectedTile.isFaceUp() ? new ImageIcon(
						fileWay + selectedTile.getValue() + ".gif")
						: backgroundImage);
				jButtonTile.setName(selectedTile.isFaceUp() ? selectedTile
						.getValue() : "not turned");
			}

		}
	}

}
