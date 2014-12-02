package se.lina.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import se.lina.controller.MemoryBoardController;

public class BoardSizeJButton extends JButton {

	private static final long serialVersionUID = 1L;
	private int columns;
	private int rows;
	private MemoryBoardController controller;

	public BoardSizeJButton(MemoryBoardController controller, String sizeName,
			int rows, int columns) {

		super(sizeName);
		this.controller = controller;
		this.rows = rows;
		this.columns = columns;

		this.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent event) {
				onClick();
			}

		});

	}

	void onClick() {
		//TODO: låt denna bara funka om man har lagt till minst en spelare...
		
		controller.changeBoardSize(rows, columns);
	}

}
