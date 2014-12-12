package fr.miage.javaproject;

/*Une classe pour définir les noeuds contenu dans l'arbre*/

public class Nodes extends java.io.File {

	public Nodes (String dir){
		super(dir);
	}

	public Nodes (Nodes parent, String child){
		super(parent,child);

	}

	public String toString() {
		return getName();
	}


}


