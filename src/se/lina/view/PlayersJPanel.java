package se.lina.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;

import se.lina.controller.MemoryBoardController;
import se.lina.players.Player;

public class PlayersJPanel extends JPanel {

	private static final long serialVersionUID = 4487935764355359491L;
	private Color color;
	private String name;
	private int playerPanelWidth;
	private int windowHeight;
	private MemoryBoardController boardController;

	public PlayersJPanel(int playerPanelWidth, int windowHeight,
			MemoryBoardController boardController) {
		this.playerPanelWidth = playerPanelWidth;
		this.windowHeight = windowHeight;
		this.boardController = boardController;

		name = "Players:";
		color = Color.YELLOW;

		initiatePanel(this.boardController);

	}

	private void initiatePanel(MemoryBoardController boardController) {

		setName(name);
		setBackground(color);

		setPreferredSize(new Dimension(playerPanelWidth, windowHeight));
		setLayout(new FlowLayout());

		JLabel size = new HeaderJLabel("DIMENSIONS OF BOARD");
		this.add(size);

		BoardSizeJButton smallBoardButton = new BoardSizeJButton(
				boardController, "small", 4, 4);
		this.add(smallBoardButton);

		BoardSizeJButton mediumBoardButton = new BoardSizeJButton(
				boardController, "medium", 6, 6);
		this.add(mediumBoardButton);

		BoardSizeJButton largeBoardButton = new BoardSizeJButton(
				boardController, "large", 8, 8);
		this.add(largeBoardButton);

		JLabel header = new HeaderJLabel("PLAYERS");
		this.add(header);

		JButtonAddPlayer addPlayer = new JButtonAddPlayer(boardController);
		this.add(addPlayer);

		JPanel playerViewHeaders = new PlayerViewJPanel("Name", "Score",
				"# moves");
		playerViewHeaders.setOpaque(!isOpaque());
		this.add(playerViewHeaders);

	}

	void addPlayerSettings(ArrayList<Player> players) {

		for (int i = 0; i < players.size(); i++) {
			addPlayerView(players.get(i).getName(), players.get(i).getScore(),
					players.get(i).isCurrentPlayer(), players.get(i)
							.getNoOfMoves());
		}

	}

	void addPlayerView(String name, int score2, boolean isCurrentPlayer,
			int noOfMoves2) {

		JPanel playerView = new PlayerViewJPanel(name, score2 + "", noOfMoves2
				+ "");

		if (isCurrentPlayer) {
			playerView.setBackground(Color.GREEN);
		} else {
			playerView.setBackground(Color.WHITE);
		}

		this.add(playerView);
		this.revalidate();
	}

	void updatePlayerView() {

		this.removeAll();
		initiatePanel(boardController);

	}

}
