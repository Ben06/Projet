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
	 * repertoire courant (celui selectionné par l'utilisateur, "." par default
	 */
	File repCourant;
	
	// au démarrage, afficher le contenu du dossier courant (default)
	FileListing()
	{
		repCourant = new File(".");
		File[] files = repCourant.listFiles();
		for (int i = 0; i<files.length; i++)
		{
			contenu.add(files[i]);
		}
	}
	
	
	/////////////////////////////////////////////////////////////////////
	//////////////////////////// Getters & Setters //////////////////////
	/////////////////////////////////////////////////////////////////////

	public File getRepCourant() {
		return this.repCourant;
	}

	public void setRepCourant(File repCourant) {
		try
		{
			this.repCourant = repCourant;
			this.contenu.clear();
			File[] listFiles = this.repCourant.listFiles();
			for (int i = 0; i<listFiles.length; i++){
				this.contenu.add(listFiles[i]);
			}
		}
		catch(Exception e)
		{
			System.out.println("Fichier/Dossier inexistant");
		}
	}

	public List<File> getContenu() {
		return this.contenu;
	}
	
	/////////////////////////////////////////////////////////////////////
	
	/**
	 * Remonter dans l'arborescence
	 * @param null
	 * @return 
	 */
	public void remonter(){
		repCourant = new File("..");
		File[] listFiles = repCourant.listFiles();
		contenu.clear();
		for (int i = 0; i<listFiles.length; i++){
			contenu.add(listFiles[i]);
		}
	}

	
//	/**
//	 * Descendre dans l'arborescence
//	 * @param null
//	 * @return
//	 */
//	
	/**
	 * Créer un dossier/fichier, dans le dossier courant
	 * @param nomDossier/fichier
	 * @return boolean création possible ou impossible
	 */
	public boolean createFile(String name){
		try
		{
		File newFile = new File("./"+name);
		System.out.println("Fichier créé "+newFile.getPath());
		this.contenu.add(newFile);
		System.out.println("Fichier ajouté au contenu");
		return true;
		}
		catch(Exception e){
			System.out.println("Création impossible");
			return false;
		}
		
	}

	
}
