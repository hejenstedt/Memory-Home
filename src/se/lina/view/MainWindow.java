package se.lina.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import se.lina.controller.MemoryBoardController;
import se.lina.model.ModelObserver;
import se.lina.model.Tile;

public class MainWindow implements ModelObserver {

	private MemoryBoardController controller;
	private int noOfRows;
	private int noOfColumns;
	private ArrayList<JButtonTile> buttonList;
	private JFrame mainFrame;
	private JPanel gamePanel;
	private int noOfPlayers;

	public static void main(String[] args) {
		// MemoryBoard memoryModel = new MemoryBoard();
		// ControllerTest controller = new ControllerTest(memoryModel);
		// MainWindow mainWindow = new MainWindow(controller);
		// memoryModel.register(mainWindow);
	}

	public MainWindow(MemoryBoardController controller) {
		this.controller = controller;

		mainFrame = new JFrame("Memory Game");

		int playerPanelWidth = 300;
		int gamePanelWidth = 1000;
		int buttonSize = 150;
		noOfRows = 4;
		noOfColumns = 4;
		int marginals = 20;
		int windowHeight = (buttonSize + marginals) * noOfRows;
		noOfPlayers = 3;
		
		mainFrame.setLayout(new FlowLayout());

		JPanelPlayers playerPanel = new JPanelPlayers(playerPanelWidth,
				windowHeight, Color.YELLOW, "Players:");
		playerPanel.addPlayerSettings(noOfPlayers);
		
		mainFrame.add(playerPanel);

		gamePanel = new JPanel();

		gamePanel.setName("GAME BOARD");
		gamePanel.setBackground(Color.CYAN);
		gamePanel.setSize(gamePanelWidth, windowHeight);
		mainFrame.add(gamePanel);

		gamePanel.setLayout(new GridLayout(noOfRows, noOfColumns, marginals,
				marginals));

		buttonList = new ArrayList<>();
		for (int i = 0; i < noOfRows; i++) {
			for (int j = 0; j < noOfColumns; j++) {
				JButtonTile tempButton = new JButtonTile(i, j, "", controller);
				tempButton.setPreferredSize(new Dimension(buttonSize,
						buttonSize));
				tempButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent event) {
						tempButton.onClick();
					}
				});
				gamePanel.add(tempButton);
				buttonList.add(tempButton);
			}
		}

		mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		mainFrame.pack();
		mainFrame.setVisible(true);

	}

	@Override
	public void tileTurned(Tile tile, int row, int column) {

		for (JButtonTile jButton : buttonList) {
			if (jButton.getRow() == row && jButton.getColumn() == column) {
				if (!tile.isFaceUp()) {
					waitAWhile(500);
				}
				jButton.setText(tile.isFaceUp() ? tile.getValue() : "");
			}
		}

		mainFrame.invalidate();
		mainFrame.pack();
	}

	private void waitAWhile(int millis) {
		try {
			Thread.sleep(millis);
		} catch (Exception e) {

		}
	}

	@Override
	public void gameTurnResult(boolean wasMatch) {
		// TODO: remove me

	
	}

}
