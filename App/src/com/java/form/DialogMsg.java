package com.java.form;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class DialogMsg extends JDialog {

	public DialogMsg(String msg, Object o) {
		// Creation de la boite de dialogue d'affichage
		setSize(635, 245);
		setResizable(false);
		setLocationRelativeTo(null);
		setTitle("Moyenne des notes");
					
		// Le panel principal
		JPanel p = new JPanel();
		JLabel l = new JLabel(msg + o);
		l.setBorder(new EmptyBorder(90,0,0,0));
		p.add(l);
					
		setContentPane(p);
		setVisible(true);
	}
}
