package com.java.form;

import java.util.Vector;

public class GestionnaireNote {

	private Vector<Note> ListNote;
	
	public GestionnaireNote() {
		ListNote = new Vector<>();
	}
	
	public void add(Note n) {
		ListNote.add(n);
	}

	public Vector<Note> getListNote() {
		return ListNote;
	}

	public void setListNote(Vector<Note> listNote) {
		ListNote = listNote;
	}
	
	public String toString() {
		String res = "";
		for (Note n : ListNote) {
			res += "Nom : " + n.getNom() + ", Prénom : " + n.getPrenom() + ", Note : " + n.getNote() + "\n";
		}
		return res;
	}
	
	public int getSize() {
		return ListNote.size();
	}
	
	public float getNoteMax() {
		
		if (ListNote.size()==0) return Float.NaN;
		
		float max = 0;
		for (Note n : ListNote) {
			if (n.getNote() > max) {
				max = n.getNote();
			}
		}
		return max;
	}
	
	public float getNoteMin() {
		
		if (ListNote.size()==0) return Float.NaN;
		
		float min = 20;
		for (Note n : ListNote) {
			if (n.getNote() < min) {
				min = n.getNote();
			}
		}
		return min;
	}
	
	public String Majorant() {
		for (Note n : ListNote) {
			if (n.getNote()==getNoteMax()) {
				return n.getNom() + " " + n.getPrenom();
			}
		}
		return null;
	}
	
	public float Moyenne() {
		// Initialisation d'une variable pour moyenne + calcul de moyenne
		float moy = 0;
		
		for (Note n : ListNote) {
			moy += n.getNote();
		}
		
		return moy / ListNote.size();
	}
	
	public float Retenue() {
		float r = 0;
		
		for (Note n : ListNote) {
			if (n.getNote() >= 12) {
				r += 1;
			}
		}
		
		return r / ListNote.size() * 100;
	}
}