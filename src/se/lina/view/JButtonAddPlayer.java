package se.lina.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import se.lina.players.Player;
import se.lina.players.PlayerController;

public class JButtonAddPlayer extends JButton {

	private static final long serialVersionUID = 8976976414176452286L;
	private PlayerController playerController;

	public JButtonAddPlayer() {
		playerController = new PlayerController();
		setText("Add Player");
		setBackground(Color.GREEN);

		this.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent event) {
				onClick();
			}

		});
	}

	void onClick() {
		// TODO: open new dialogwindow to get player name and initialize player
		String s = (String) JOptionPane.showInputDialog(
				"Please enter the new players name", null);

		playerController.addPlayer(new Player(s));
	}
}
