package se.lina.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class JPanelPlayers extends JPanel {

	private static final long serialVersionUID = 4487935764355359491L;

	public JPanelPlayers(int playerPanelWidth, int windowHeight, Color color,
			String name) {
		setName(name);
		setBackground(color);
		setPreferredSize(new Dimension(playerPanelWidth, windowHeight));
		setLayout(new FlowLayout());
		JLabel header = new JLabel("PLAYERS");
		header.setFont(new Font("Helvetica", Font.BOLD, 15));
		header.setHorizontalAlignment(JLabel.CENTER);
		header.setPreferredSize(new Dimension(250, 50));

		this.add(header);
		JButtonAddPlayer addPlayer = new JButtonAddPlayer();
		this.add(addPlayer);

	}

	void addPlayerSettings(int noOfPlayers) {
		// TODO: add players from PlayerController

		for (int i = 0; i < noOfPlayers; i++) {
			addPlayerView("Player " + (i + 1));
		}

	}

	private void addPlayerView(String name) {

		JPanel playerView = new JPanel();
		playerView.setLayout(new GridLayout(1, 2));

		// JTextField player = new JTextField();

		JLabel player = new JLabel(name);

		player.setFont(new Font("Courier New", Font.ITALIC, 15));
		player.setForeground(Color.DARK_GRAY);
		player.setHorizontalAlignment(JLabel.CENTER);

		JLabel score = new JLabel("0");
		score.setHorizontalAlignment(JLabel.CENTER);

		playerView.add(player);
		playerView.add(score);
		playerView.setPreferredSize(new Dimension(250, 50));
		playerView.setBackground(Color.WHITE);
		this.add(playerView);
	}

}
