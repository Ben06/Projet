package fr.miage.fileListing;

import java.io.File;
import java.util.ArrayList;
import java.util.List;




public class FileListing {

	/**
	 * liste du contenu du repertoire que l'on souhaite afficher
	 */
	List<File> contenu = new ArrayList<>();
	
	
	/**
	 * repertoire courant (celui selectionn� par l'utilisateur, "." par default
	 */
	File repCourant;
	
	// au d�marrage, afficher le contenu du dossier courant (default)
	FileListing(){
		repCourant = new File(".");
		File[] files = repCourant.listFiles();
		for (int i = 0; i<files.length; i++)
		{
			
		}
		
	}
	
	
	/////////////////////////////////////////////////////////////////////
	//////////////////////////// Getters & Setters //////////////////////
	/////////////////////////////////////////////////////////////////////

	public File getRepCourant() {
		return repCourant;
	}

	public void setRepCourant(File repCourant) {
		this.repCourant = repCourant;
	}

	public List<File> getContenu() {
		return contenu;
	}
	
	/////////////////////////////////////////////////////////////////////
	
	/**
	 * Remonter dans l'arborescence
	 * @param null
	 * @return 
	 */
//	public remonter

	
//	/**
//	 * Descendre dans l'arborescence
//	 * @param null
//	 * @return
//	 */
//	
	/**
	 * Cr�er un dossier, dans le dossier courant
	 * @param nomDossier
	 * @return boolean cr�ation possible ou impossible
	 */


	
}
