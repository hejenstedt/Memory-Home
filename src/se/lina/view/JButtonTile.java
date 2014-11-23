package se.lina.view;

import java.util.Objects;

import javax.swing.JButton;

import se.lina.controller.MemoryBoardController;

public class JButtonTile extends JButton {

	private static final long serialVersionUID = 1L;
	private final int row;
	private final int column;
	private MemoryBoardController controller;

	public int getRow() {
		return row;
	}

	public int getColumn() {
		return column;
	}

	public JButtonTile(int row, int column, String value,
			MemoryBoardController controller) {
		super(value);

		Objects.nonNull(controller);

		if (row < 0 || column < 0) {
			throw new IllegalArgumentException("Row or Column was < 0");
		}

		this.row = row;
		this.column = column;
		this.controller = controller;
	}

	void onClick() {
		controller.tileSelected(row, column);
	}

}
