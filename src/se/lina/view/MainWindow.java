package se.lina.view;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;

import se.lina.controller.MemoryBoardController;
import se.lina.model.GameObserver;
import se.lina.model.Tile;
import se.lina.players.Player;
import se.lina.players.PlayerEventObserver;
import se.lina.players.Players;

public class MainWindow implements GameObserver, PlayerEventObserver {

	private MemoryBoardController controller;
	private int noOfRows;
	private int noOfColumns;
	private ArrayList<JButtonTile> buttonList;
	private JFrame mainFrame;
	private GameBoardJPanel gamePanel;
	private PlayersJPanel playerPanel;
	private int marginals;
	private int buttonSize;
	private int windowHeight;

	public MainWindow(int rows, int columns) {
	}

	public MainWindow(MemoryBoardController boardController,
			Players playerController, int rows, int columns) {
		this.controller = boardController;
		noOfRows = rows;
		noOfColumns = columns;

		initiateNewMainFrame();

	}

	void initiateNewMainFrame() {
		mainFrame = new JFrame("Memory Game");
		int playerPanelWidth = 300;
		marginals = 20;
		windowHeight = 750;
		buttonSize = (windowHeight - (marginals * (noOfRows - 1))) / noOfRows;
		buttonList = new ArrayList<>();

		mainFrame.setLayout(new FlowLayout());

		addPlayerPanel(playerPanelWidth);

		addGamePanel();

		mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		mainFrame.pack();
		mainFrame.setVisible(true);

	}

	private void addGamePanel() {
		gamePanel = new GameBoardJPanel(noOfRows, noOfColumns, marginals);

		mainFrame.add(gamePanel);

		for (int i = 0; i < noOfRows; i++) {
			for (int j = 0; j < noOfColumns; j++) {
				JButtonTile tempButton = new JButtonTile(i, j, controller,
						buttonSize);
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
	}

	private void addPlayerPanel(int playerPanelWidth) {
		playerPanel = new PlayersJPanel(playerPanelWidth, windowHeight,
				controller);

		mainFrame.add(playerPanel);
	}

	@Override
	public void tileTurned(Tile tile, int row, int column) {
		for (JButtonTile jButton : buttonList) {
			if (jButton.getRow() == row && jButton.getColumn() == column) {
				jButton.setText(tile.isFaceUp() ? tile.getValue() : "");
			}
		}
	}

	@Override
	public void gameTurnResult(boolean wasMatch, Tile selectedTile,
			Tile lastTile) {
		waitAWhile(500);
		for (JButtonTile jButtonTile : buttonList) {
			if (jButtonTile.getText().equals(lastTile.getValue())) {
				jButtonTile.setText(lastTile.isFaceUp() ? lastTile.getValue() : "");
			}
			if (jButtonTile.getText().equals(selectedTile.getValue())) {
				jButtonTile.setText(selectedTile.isFaceUp() ? selectedTile.getValue() : "");
			}

		}
	}

	private void waitAWhile(int millis) {
		try {
			Thread.sleep(millis);
		} catch (Exception e) {

		}
	}

	@Override
	public void playerSettingsChanged(ArrayList<Player> players) {

		playerPanel.updatePlayerView();
		playerPanel.addPlayerSettings(players);

	}

	@Override
	public void gameOver() {
		// TODO: not sure what to do here
	}

	@Override
	public void winnerFound(Player winner) {
		String name = winner.getName();
		int score = winner.getScore();
		JOptionPane.showMessageDialog(null, name + " won with " + score
				+ " points!", "Winner!", JOptionPane.PLAIN_MESSAGE);
		String[] options = { "Yes, play again with same settings",
				"Yes, play again but change settings", "No, Exit Game" };

		int n = JOptionPane.showOptionDialog(null,
				"Do you want to play again?", "Play again",
				JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,
				null, options, options[0]);

		if (n == 0 || n == 1) {
			// TODO: add another choice for option 1 - play again but change
			// settings
			controller.startNewGame();
		} else if (n == 2) {
			// TODO: add are you sure prompt
			System.exit(0);
		}
	}

	@Override
	public void reStartBoard() {
		buttonList.forEach(button -> button.setText(""));
	}

	@Override
	public void reStartBoardWithNewSize(int rows, int columns) {
		noOfRows = rows;
		noOfColumns = columns;

		buttonList.clear();
		buttonSize = (windowHeight - (marginals * (noOfRows - 1))) / noOfRows;

		mainFrame.remove(gamePanel);

		addGamePanel();

		mainFrame.pack();
		mainFrame.setVisible(true);

	}


}
