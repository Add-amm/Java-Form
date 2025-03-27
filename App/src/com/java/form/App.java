package com.java.form;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class App extends JFrame implements ActionListener {
	
	ButtonPanel listOfButtons;
	LabeledTextField tf1;
	LabeledTextField tf2;
	LabeledTextField tf3;
	JLabel Statut = new JLabel("");
	private GestionnaireNote g = new GestionnaireNote();
	
	
	public App() {
		JPanel p = new JPanel();
		p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
		
		// p1 : panel contenant le titre
		JPanel p1 = new JPanel();
		p1.setBackground(Color.WHITE);
		JLabel title = new JLabel("Gestion d'Etudiants");
		title.setFont(new Font("Arial", Font.BOLD, 30));
		title.setForeground(Color.decode("#8AAAE5"));
		title.setBorder(new EmptyBorder(65, 0, 10, 0));
		p1.add(title);
		p.add(p1);
		
		// p2 : panel contenant les infos du formulaire
		JPanel p2 = new JPanel();
		p2.setBackground(Color.WHITE);
		p2.setLayout(new BoxLayout(p2, BoxLayout.Y_AXIS));
		tf1 = new LabeledTextFieldWithAsterid("Nom", 45, "Veuillez entrer le nom...", 130);
		tf2 = new LabeledTextFieldWithAsterid("Prénom", 45, "Veuillez entrer le prénom...", 130);
		tf3 = new LabeledTextFieldWithAsterid("Note", 45, "Veuillez entrer la note...", 130);
		((JTextField)(tf3.getComponent(1))).addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				((JButton)(listOfButtons.getComponent(0))).doClick();
			}
		});
		p2.add(tf1); p2.add(tf2); p2.add(tf3);
		p2.setBorder(new EmptyBorder(0, 15, 0, 0));
		p.add(p2);
		
		// p3 : panel contenant les differents bouton
		JPanel p3 = new JPanel();
		p3.setBackground(Color.WHITE);
		listOfButtons = new ButtonPanel(new String[] {"Ajouter", "Moyenne", "NoteMax", "NoteMin", "Majorant", "%Retenue", "Reset", "Quitter"});
		for (int i = 0; i < 8; i++)
			((JButton)listOfButtons.getComponent(i)).addActionListener(this);
		p3.add(listOfButtons);
		p.add(p3);
		
		// p4 : panel contenant l'info
		JPanel p4 = new JPanel();
		p4.setBackground(Color.WHITE);
		Statut.setVisible(false);
		Statut.setFont(new Font("Arial", Font.ITALIC, 13));
		p4.add(Statut);
		p.add(p4);
				
		setContentPane(p);
		setTitle("Mon formulaire");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1100, 800);
		setMinimumSize(new Dimension(730, 600));
		setLocationRelativeTo(null); // Centrer la fenetre principal
		setVisible(true);
		
	}

	public static void main(String[] args) {
		new App();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==(JButton)listOfButtons.getComponent(0)) { // Bouton Ajouter
			
			// Collecte des informations
			try {
			Note n = new Note(tf1.getText(), tf2.getText(), Float.parseFloat(tf3.getText()));
			g.add(n);
			Statut.setText("Note ajoutée !");
			} catch (NumberFormatException exc) {
				tf3.reload();
				Statut.setText("Veuillez entrer une note...");
				return ;
			} finally {
				Statut.setVisible(true);
			}
			
			// Reintialisation des champ de saisie
			reloadFields();
			
			// Test dans la console
			// System.out.println(g);
		}
		
		if (e.getSource()==(JButton)listOfButtons.getComponent(1)) { // Bouton Moyenne
			new DialogMsg("La moyenne des notes est : ", String.format("%.2f", g.Moyenne()));
			Statut.setVisible(false);
			reloadFields();
		}
		
		if (e.getSource()==(JButton)listOfButtons.getComponent(2)) { // Bouton NoteMax
			new DialogMsg("La note maximale est : ", g.getNoteMax());
			Statut.setVisible(false);
			reloadFields();
		}
		
		if (e.getSource()==(JButton)listOfButtons.getComponent(3)) { // Bouton NoteMin
			new DialogMsg("La note minimale est : ", g.getNoteMin());
			Statut.setVisible(false);
			reloadFields();
		}
		
		if (e.getSource()==(JButton)listOfButtons.getComponent(4)) { // Bouton Majorant
			new DialogMsg("Le majorant est : ", g.Majorant());
			Statut.setVisible(false);
			reloadFields();
		}
		
		if (e.getSource()==(JButton)listOfButtons.getComponent(5)) { // Bouton %Retenue
			new DialogMsg("Le % de retenue est : ", String.format("%.2f", g.Retenue()));
			Statut.setVisible(false);
			reloadFields();
		}
		
		if (e.getSource()==(JButton)listOfButtons.getComponent(6)) { // Bouton Reset
			reloadFields();
			g = new GestionnaireNote();
			Statut.setText("Tous les notes sont réintialiser !");
			Statut.setVisible(true);
		}
		
		if (e.getSource()==(JButton)listOfButtons.getComponent(7)) { // Bouton Quitter
			System.exit(0);
		}
	}
	
	public void reloadFields() {
		tf1.reload();
		tf2.reload();
		tf3.reload();
	}
}