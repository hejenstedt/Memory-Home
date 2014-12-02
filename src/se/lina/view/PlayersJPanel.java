package se.lina.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
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

		JLabel size = new JLabel("DIMENSIONS OF BOARD");
		setLabelFormat(size);
		this.add(size);

		BoardSizeJButton smallBoardButton = new BoardSizeJButton(boardController,"small", 4, 4);
		BoardSizeJButton mediumBoardButton = new BoardSizeJButton(boardController,"medium", 6, 6);
		BoardSizeJButton largeBoardButton = new BoardSizeJButton(boardController,"large", 8,8);
		this.add(smallBoardButton);
		this.add(mediumBoardButton);
		this.add(largeBoardButton);
		
		JLabel header = new JLabel("PLAYERS");
		setLabelFormat(header);

		this.add(header);
		JButtonAddPlayer addPlayer = new JButtonAddPlayer(boardController);
		this.add(addPlayer);
	}

	private void setLabelFormat(JLabel size) {
		size.setFont(new Font("Helvetica", Font.BOLD, 15));
		size.setHorizontalAlignment(JLabel.CENTER);
		size.setPreferredSize(new Dimension(250, 50));
	}

	void addPlayerSettings(ArrayList<Player> players) {

		for (int i = 0; i < players.size(); i++) {
			addPlayerView(players.get(i).getName(), players.get(i).getScore(),
					players.get(i).isCurrentPlayer());
		}

	}

	void addPlayerView(String name, int score2, boolean isCurrentPlayer) {

		JPanel playerView = new JPanel();

		playerView.setLayout(new GridLayout(1, 2));

		JLabel player = new JLabel(name);
		player.setFont(new Font("Arial", Font.ITALIC, 18));
		player.setForeground(Color.DARK_GRAY);
		player.setHorizontalAlignment(JLabel.CENTER);

		JLabel score = new JLabel(score2 + "");
		score.setFont(new Font("Arial", Font.ITALIC, 18));
		score.setForeground(Color.DARK_GRAY);
		score.setHorizontalAlignment(JLabel.CENTER);

		playerView.add(player);
		playerView.add(score);
		playerView.setPreferredSize(new Dimension(250, 50));

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
