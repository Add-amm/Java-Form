package com.java.form;

import javax.swing.JButton;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class ButtonPanel extends JPanel {

	public ButtonPanel(String[] labels) {
		for (String label : labels) {
			add((new JButton(label)));
		}
		setOpaque(false);
	}

}
