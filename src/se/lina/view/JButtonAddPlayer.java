package se.lina.view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import se.lina.controller.MemoryBoardController;

public class JButtonAddPlayer extends JButton {

	private static final long serialVersionUID = 8976976414176452286L;
	private MemoryBoardController boardController;

	public JButtonAddPlayer(MemoryBoardController boardController) {
		this.boardController = boardController;
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
		String newPlayerName = (String) JOptionPane.showInputDialog(
				"Please enter the new players name", null);

		boardController.newPlayerAdded(newPlayerName);
		
	}

}
