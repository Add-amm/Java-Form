package com.java.form;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class LabeledTextField extends JPanel {
	
	protected String placeholder;
	
	public LabeledTextField() {}

	public LabeledTextField(String text, int cols, String placeholder, int width) {
		this.placeholder = placeholder;
		
		JLabel t = new JLabel(text);
		t.setFont(new Font("Arial", Font.BOLD, 20));
		t.setPreferredSize(new Dimension(width, 60));
		JTextField f = new JTextField(cols);
		f.setText(placeholder);
		f.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				if (f.getText().isEmpty()){
					f.setText(placeholder);
				}
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				if (f.getText().equals(placeholder)) {
					f.setText("");
				}
			}
		});
		f.setPreferredSize(new Dimension(cols, 27));
		setLayout(new FlowLayout(FlowLayout.LEFT));
		add(t); add(f);
		setOpaque(false);
	}
	
	public void setLabelText(String label) {
		((JLabel)getComponent(0)).setText(label);
	}
	
	public String getText() {
		return ((JTextField)getComponent(1)).getText();
	}
	
	public void setText(String text) {
		((JTextField)getComponent(1)).setText(text);
	}
	
	public void reload() {
		setText(placeholder);
	}
}

