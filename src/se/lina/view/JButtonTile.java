package se.lina.view;

import java.awt.Dimension;
import java.awt.Image;
import java.util.Objects;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import se.lina.controller.MemoryBoardController;

public class JButtonTile extends JButton {

	private static final long serialVersionUID = 1L;
	private final int row;
	private final int column;
	private MemoryBoardController boardController;
	private int buttonSize;

	public int getRow() {
		return row;
	}

	public int getColumn() {
		return column;
	}

	public JButtonTile(int row, int column,
			MemoryBoardController boardController, int buttonSize, Icon icon) {
		super(icon);
		Objects.nonNull(boardController);
		if (row < 0 || column < 0) {
			throw new IllegalArgumentException("Row or Column was < 0");
		}
		this.setName("not turned");
		this.buttonSize = buttonSize;
		this.setPreferredSize(new Dimension(this.buttonSize, this.buttonSize));
//		this.setFont(new Font("Arial", Font.BOLD, 30));
		this.row = row;
		this.column = column;
		this.boardController = boardController;
	}

	void onClick() {
		boardController.tileSelected(row, column);
	}

	public void changeIcon(ImageIcon icon) {
		Image img = icon.getImage();
		Image newimg = img.getScaledInstance( buttonSize, buttonSize,  java.awt.Image.SCALE_SMOOTH ) ;
		this.setIcon(new ImageIcon(newimg));
	}

}
