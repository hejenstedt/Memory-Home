package se.lina.view;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JLabel;

public class HeaderJLabel extends JLabel {

	private static final long serialVersionUID = 1L;

	public HeaderJLabel(String name) {
		super(name);
		setFont(new Font("Helvetica", Font.BOLD, 15));
		setHorizontalAlignment(JLabel.CENTER);
		setPreferredSize(new Dimension(250, 50));
	}

}
