package com.java.form;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JLabel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class LabeledTextFieldWithAsterid extends LabeledTextField {
	
	private String text;
	
	public LabeledTextFieldWithAsterid(String text, int cols, String placeholder, int width) {
		this.placeholder = placeholder;
		this.text = text;
		
		JLabel t = new JLabel("<html>" + text + "<span style='color:red;'>*</span></html>");
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
		f.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				removeAsteroid();
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				if (f.getText().isEmpty()) {
					addAsteroid();
				}
			}
			
			@Override
			public void keyPressed(KeyEvent e) {}
		});
		f.setPreferredSize(new Dimension(cols, 27));
		setLayout(new FlowLayout(FlowLayout.LEFT));
		add(t); add(f);
		setOpaque(false);
	}
	
	public void removeAsteroid() {
		((JLabel)getComponent(0)).setText(text);
	}
	
	public void addAsteroid() {
		((JLabel)getComponent(0)).setText("<html>" + text + "<span style='color:red;'>*</span></html>");
	}
	
	public void reload() {
		setText(placeholder);
		addAsteroid();
	}
}
