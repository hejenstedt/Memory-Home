package se.lina.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
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
	private JPanel gamePanel;
	private int noOfPlayers;
	private PlayersJPanel playerPanel;

	public static void main(String[] args) {
		// MemoryBoard memoryModel = new MemoryBoard();
		// ControllerTest controller = new ControllerTest(memoryModel);
		// MainWindow mainWindow = new MainWindow(controller);
		// memoryModel.register(mainWindow);
	}

	public MainWindow(MemoryBoardController boardController,
			Players playerController) {
		this.controller = boardController;

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

		playerPanel = new PlayersJPanel(playerPanelWidth, windowHeight,
				Color.YELLOW, "Players:", boardController);

//		 playerPanel.addPlayerSettings(noOfPlayers);

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
				JButtonTile tempButton = new JButtonTile(i, j, "",
						boardController);
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

	private void updatePlayersInMainWindow() {
		mainFrame.repaint();
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
	}

	private void waitAWhile(int millis) {
		try {
			Thread.sleep(millis);
		} catch (Exception e) {

		}
	}

	@Override
	public void gameTurnResult(boolean wasMatch) {
		// playerController.getCurrentPlayer().getScore();
		//
		// for (int i = 0; i < noOfPlayers; i++) {
		//
		// }
		//
	}

	@Override
	public void playerSettingsChanged(ArrayList<Player> players) {

		for (int i = 1; i < players.size(); i++) {
			playerPanel.remove(2);//TODO - kan vara fel?
			
		}
		playerPanel.addPlayerSettings(players);
//		playerPanel.addPlayerView(player.getName(), player.getScore());
		updatePlayersInMainWindow();
	}

}
