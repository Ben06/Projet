package fr.miage.fileListing;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Model {
	
//	public static FileListing list;
	/**
	 * repertoire courant de l'explorateur
	 */
	public static File repCourant;
	
	/**
	 * contenu du repertoire courant
	 */
	public static List<File> contenu = new ArrayList<File>();
	
	
	public static List<String> fileNames = new ArrayList<>();
	
	
	public static File selectedFile;
	
//	public static FileListing getList() {
//		return list;
//	}
//
//	public static void setList(FileListing list) {
//		Model.list = list;
//	}
	
	
	public static File getRepCourant() {
		return repCourant;
	}

	public static File getSelectedFile()
	{
		return selectedFile;
	}

	public static void setSelectedFile(File selectedFile)
	{
		Model.selectedFile = selectedFile;
	}

	public static List<String> getFileNames() {
		return fileNames;
	}

	public static void setFileNames(List<String> fileNames) {
		Model.fileNames = fileNames;
	}
	
	public static String getFileNames(int index){
		return Model.fileNames.get(index);
	}
	public static void setRepCourant(File repCourant) {
		Model.repCourant = repCourant;
	}

	public static List<File> getContenu() {
		return contenu;
	}

	public static File getContenu(int index){
		return contenu.get(index);
	}
	
	public static void setContenu(List<File> contenu) {
		Model.contenu = contenu;
	}
	
	
	

}
