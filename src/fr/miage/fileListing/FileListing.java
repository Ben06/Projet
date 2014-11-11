package fr.miage.fileListing;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

// bug : lorsque l'on remonte jusqu'a c:, ou que l'on souhaite définir c: répertoire courant, on ne peut pas, à la place le repertoire courant
// "." est ajouté à la place. A corriger par la suite.

public class FileListing {

	/**
	 * liste du contenu du repertoire que l'on souhaite afficher
	 */
	List<File> contenu = new ArrayList<>();

	List<String> fileNames = new ArrayList<>();
	/**
	 * repertoire courant (celui selectionné par l'utilisateur, "." par default
	 */
	File repCourant;

	// au démarrage, afficher le contenu du dossier courant (default)
	FileListing() {
		repCourant = new File(".");
		File[] listFiles = repCourant.listFiles();
		for (int i = 0; i < listFiles.length; i++) {
			contenu.add(listFiles[i]);
			fileNames.add(listFiles[i].getName());
		}
	}

	// ///////////////////////////////////////////////////////////////////
	// ////////////////////////// Getters & Setters //////////////////////
	// ///////////////////////////////////////////////////////////////////

	public File getRepCourant() {
		return this.repCourant;
	}

	public List<String> getFileNames() {
		return fileNames;
	}

	public void setFileNames(List<String> fileNames) {
		this.fileNames = fileNames;
	}

	public void setRepCourant(String repCourant) {
		System.out.println("FileListing.setRepCourant() repToChange : "+repCourant);
		File repToChange = new File(repCourant);
		this.repCourant = repToChange;
		System.out.println("FileListing.setRepCourant() repCourant après setter : "+ this.repCourant);
		this.contenu.clear();
		this.fileNames.clear();
		File[] listFiles = this.repCourant.listFiles();
//		System.out.println("FileListing.setRepCourant() listfile.length() : "+ listFiles.length);
		for (int i = 0; i < listFiles.length; i++) {
			this.contenu.add(listFiles[i]);
			this.fileNames.add(listFiles[i].getName());
		}
	}

	public List<File> getContenu() {
		return this.contenu;
	}

	// ///////////////////////////////////////////////////////////////////

	/**
	 * Remonter dans l'arborescence
	 * 
	 * @param null
	 * @return
	 */
	public void remonter() {
		try {
			if (repCourant.getCanonicalPath().endsWith(":")) {
				System.out
						.println("FileListing.remonter() impossible de remonter plus, repertoire courant = root");
			} else {
				String currentPath = repCourant.getCanonicalPath();
				String[] folders = currentPath.split("\\\\");
				String upPath = "";
				for (int i = 0; i < folders.length - 1; i++) {
					if (i == folders.length - 2)
						upPath += folders[i];
					else
						upPath += folders[i] + "\\";
				}
				System.out
						.println("------------------------------- \n FileListing.remonter() "
								+ upPath);
				setRepCourant(upPath);
				System.out
						.println("FileListing.remonter() après le setRepCourant : "
								+ repCourant.getCanonicalPath());
				File[] listFiles = repCourant.listFiles();
				contenu.clear();
				fileNames.clear();
				for (int i = 0; i < listFiles.length; i++) {
					// System.out.println("FileListing.remonter() "+listFiles[i]);
					contenu.add(listFiles[i]);
					fileNames.add(listFiles[i].getName());
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Créer un dossier/fichier, dans le dossier courant
	 * 
	 * @param nomDossier
	 *            /fichier
	 * @return boolean création possible ou impossible
	 */
	public boolean createFile(String name) {
		boolean result = new File(name).mkdir();
		return result;
	}

	public String toString() {
		String res = "";
		for (int i = 0; i < contenu.size(); i++) {
			res += contenu + "\n";
		}
		return res;
	}

	public static void main(String[] args) throws IOException {
		FileListing list = null;
		list = new FileListing();

		list.setRepCourant("C:\\Users\\deptinfo\\Documents\\Ma musique");
		
		for (int i = 0; i < list.contenu.size(); i++) {
			try {
				System.out.println("FileListing.main() "
						+ list.contenu.get(i).getCanonicalPath());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
