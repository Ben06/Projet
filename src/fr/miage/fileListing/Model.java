package fr.miage.fileListing;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Model {
	
	/**
	 * repertoire courant de l'explorateur
	 */
	public static File repCourant;
	
	/**
	 * contenu du repertoire courant
	 */
	public static List<File> contenu = new ArrayList<File>();
	
	
	public static File getRepCourant() {
		return repCourant;
	}

	public static void setRepCourant(File repCourant) {
		Model.repCourant = repCourant;
	}

	public static List<File> getContenu() {
		return contenu;
	}

	public static void setContenu(List<File> contenu) {
		Model.contenu = contenu;
	}
	
	
	

}
