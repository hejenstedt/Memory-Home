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
import se.lina.players.PlayerEventObserver;
import se.lina.players.Players;

public class PlayersJPanel extends JPanel {

	private static final long serialVersionUID = 4487935764355359491L;

	public PlayersJPanel(int playerPanelWidth, int windowHeight, Color color,
			String name, MemoryBoardController boardController) {
		setName(name);
		setBackground(color);
		setPreferredSize(new Dimension(playerPanelWidth, windowHeight));
		setLayout(new FlowLayout());
		JLabel header = new JLabel("PLAYERS");
		header.setFont(new Font("Helvetica", Font.BOLD, 15));
		header.setHorizontalAlignment(JLabel.CENTER);
		header.setPreferredSize(new Dimension(250, 50));

		this.add(header);
		JButtonAddPlayer addPlayer = new JButtonAddPlayer(boardController);
		this.add(addPlayer);

	}

	void addPlayerSettings(ArrayList<Player> players) {
		// TODO: add players from PlayerController

		for (int i = 0; i < players.size(); i++) {
			addPlayerView(players.get(i).getName(), players.get(i).getScore());
		}

	}

	void addPlayerView(String name, int score2) {

		JPanel playerView = new JPanel();
		playerView.setLayout(new GridLayout(1, 2));

		// JTextField player = new JTextField();

		JLabel player = new JLabel(name);

		player.setFont(new Font("Courier New", Font.ITALIC, 15));
		player.setForeground(Color.DARK_GRAY);
		player.setHorizontalAlignment(JLabel.CENTER);

		JLabel score = new JLabel(score2+"");
		score.setHorizontalAlignment(JLabel.CENTER);

		playerView.add(player);
		playerView.add(score);
		playerView.setPreferredSize(new Dimension(250, 50));
		playerView.setBackground(Color.WHITE);
		this.add(playerView);
		this.revalidate();
		this.repaint();
	}

}
