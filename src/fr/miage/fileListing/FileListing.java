package fr.miage.fileListing;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.DosFileAttributes;
import java.util.ArrayList;
import java.util.List;

import fr.miage.Model.Model;


// bug : lorsque l'on remonte jusqu'a c:, ou que l'on souhaite définir c: répertoire courant, on ne peut pas, à la place le repertoire courant
// "." est ajouté à la place. A corriger par la suite.

public class FileListing
{

	Model model = new Model();

	/**
	 * liste du contenu du repertoire que l'on souhaite afficher
	 */
	List<File> contenu = new ArrayList<>();

	List<String> fileNames = new ArrayList<>();
	/**
	 * repertoire courant (celui selectionné par l'utilisateur, "." par default
	 */
	File repCourant;

	/**
	 * nom de l'os
	 */
	String osName;


	// au démarrage, afficher le contenu du dossier courant (default)
	public FileListing()
	{

		repCourant = new File(".");
		osName = System.getProperty("os.name").toLowerCase();
		File[] listFiles = repCourant.listFiles();
		for (int i = 0; i < listFiles.length; i++)
		{
			contenu.add(listFiles[i]);
			fileNames.add(listFiles[i].getName());
		}
	}


	// ///////////////////////////////////////////////////////////////////
	// ////////////////////////// Getters & Setters //////////////////////
	// ///////////////////////////////////////////////////////////////////

	public File getRepCourant()
	{
		return this.repCourant;
	}


	public List<String> getFileNames()
	{
		return fileNames;
	}


	public boolean setRepCourant(String repCourant)
	{
		// System.out.println("FileListing.setRepCourant() repToChange : " +
		// repCourant);
		File repToChange = new File(repCourant);

		if (repToChange.listFiles() == null)
		{
			return false;
		} else
		{
			this.repCourant = repToChange;
			// System.out.println("FileListing.setRepCourant() repCourant après setter : "
			// + this.repCourant);
			this.contenu.clear();
			this.fileNames.clear();
			System.out.println("FileListing.setRepCourant() avant le listFile");
			File[] listFiles = repToChange.listFiles();
			System.out.println("FileListing.setRepCourant() après le listFile " + listFiles);

			for (int i = 0; i < listFiles.length; i++)
			{
				// System.out.println("FileListing.setRepCourant() listFiles true");
				this.contenu.add(listFiles[i]);
				this.fileNames.add(listFiles[i].getName());
			}
			return true;
		}
	}


	public List<File> getContenu()
	{
		return this.contenu;
	}


	// ///////////////////////////////////////////////////////////////////

	/**
	 * Remonter dans l'arborescence
	 * 
	 * @param null
	 * @return
	 */
	public boolean remonter()
	{
		try
		{
			String currentPath = repCourant.getCanonicalPath();
			System.out.println("FileListing.remonter() " + System.getProperty("os.name"));
			if (osName.contains("windows"))
			{
				String[] folders = currentPath.split("\\\\");
				String upPath = "";
				for (int i = 0; i < folders.length - 1; i++)
				{
					if (i == folders.length - 2)
						upPath += folders[i];
					else
						upPath += folders[i] + "\\";
				}
				System.out.println("------------------------------- \n FileListing.remonter() " + upPath);
				setRepCourant(upPath);
				System.out.println("FileListing.remonter() après le setRepCourant : " + repCourant.getCanonicalPath());
				File[] listFiles = repCourant.listFiles();
				contenu.clear();
				fileNames.clear();
				if (listFiles != null)
				{
					for (int i = 0; i < listFiles.length; i++)
					{
						// System.out.println("FileListing.remonter() "+listFiles[i]);
						contenu.add(listFiles[i]);
						fileNames.add(listFiles[i].getName());
					}
					return true;
				} else
					return false;
			}
			if (osName.contains("nux") || osName.contains("nux") || osName.contains("aix"))
			{
				String[] folders = currentPath.split("/");
				String upPath = "";
				for (int i = 0; i < folders.length - 1; i++)
				{
					if (i == folders.length - 2)
						upPath += folders[i];
					else
						upPath += folders[i] + "/";
				}
				System.out.println("------------------------------- \n FileListing.remonter() " + upPath);
				setRepCourant(upPath);
				System.out.println("FileListing.remonter() après le setRepCourant : " + repCourant.getCanonicalPath());
				File[] listFiles = repCourant.listFiles();
				contenu.clear();
				fileNames.clear();
				if (listFiles != null)
				{
					for (int i = 0; i < listFiles.length; i++)
					{
						// System.out.println("FileListing.remonter() "+listFiles[i]);
						contenu.add(listFiles[i]);
						fileNames.add(listFiles[i].getName());
					}
					return true;
				} else
					return false;
			}
		} catch (IOException e)
		{
			return false;
		}
		return false;
	}


	/**
	 * Créer un dossier/fichier, dans le dossier courant
	 * 
	 * @param nomDossier
	 *            /fichier
	 * @return boolean création possible ou impossible
	 */
	public boolean createFile(String name)
	{
		boolean result = false;
		try
		{
			if (osName.contains("nux") || osName.contains("nux") || osName.contains("aix"))
			{
				result = new File(model.getRepCourant().getCanonicalPath() + "/" + name).mkdir();
			}
			if (osName.contains("windows"))
			{
				result = new File(model.getRepCourant().getCanonicalPath() + "\\" + name).mkdir();
			}
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}


	public String toString()
	{
		String res = "";
		for (int i = 0; i < contenu.size(); i++)
		{
			res += contenu + "\n";
		}
		return res;
	}


	public static void main(String[] args) throws IOException
	{
		// FileListing list = null;
		// list = new FileListing();
		//
		// list.setRepCourant(".");

		File c = new File("c:");
		File[] listFiles = c.listFiles();
		for (int i = 0; i < listFiles.length; i++)
		{
			System.out.println(listFiles[i].getCanonicalPath());
		}
	}
}
