package fr.miage.chargementDynamique;

//Importation des packages dont on va se servir
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

import fr.miage.Model.Model;


public class MyZip
{

	/**
	 * décompresse le fichier zip dans le répertoire donné
	 * 
	 * @param folder
	 *            le répertoire où les fichiers seront extraits
	 * @param zipfile
	 *            le fichier zip à décompresser
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static byte[] unzip(File zipfile, String name) throws FileNotFoundException, IOException
	{

		ZipFile zip = new ZipFile(zipfile.getPath());
		ZipInputStream zis = new ZipInputStream(new BufferedInputStream(new FileInputStream(zipfile.getCanonicalFile())));

		// extractions des entrées du fichiers zip (i.e. le contenu du zip)
		ZipEntry ze = null;
		try
		{
			while ((ze = zis.getNextEntry()) != null)
			{
				File f = new File(ze.getName());

				if (f.getPath().endsWith(".class")) // à modif par la suite
				{
					if (f.getPath().contains(name))
					{
						System.out.println("MyZip.unzip() trouvé " + f.getCanonicalPath());
						InputStream in = zip.getInputStream(ze);
						ByteArrayOutputStream baos = new ByteArrayOutputStream();
						int b;
						while ((b = in.read()) != -1)
						{
							baos.write(b);
						}
						return baos.toByteArray();
					}
				}

			}
		} finally
		{
			// fermeture de la ZipInputStream
			zis.close();
		}
		return null;
	}


	public static String unzipGetPackage(File zipfile, String name) throws FileNotFoundException, IOException
	{

		ZipFile zip = new ZipFile(zipfile.getPath());
		ZipInputStream zis = new ZipInputStream(new BufferedInputStream(new FileInputStream(zipfile.getCanonicalFile())));

		// extractions des entrées du fichiers zip (i.e. le contenu du zip)
		ZipEntry ze = null;
		try
		{
			while ((ze = zis.getNextEntry()) != null)
			{
				// Pour chaque entrée, on crée un fichier
				// dans le répertoire de sortie "folder"
				File f = new File(ze.getName());
				if (f.getPath().endsWith(".class")) // à modif par la suite
				{
//					System.out.println("MyZip.unzipGetPackage() f.getName="+f.getName());
					// modifier le chemin avant
					String packageName = "";
					String fname = f.getName();
//					System.out.println("MyZip.unzipGetPackage() name = "+name);
//					System.out.println("MyZip.unzipGetPackage() "+fname+".contains("+name+")="+fname.contains(name));
					if (fname.contains(name))
					{
//						System.out.println("MyZip.unzip() trouvé " + f.getCanonicalPath());
						packageName = f.getParentFile().getCanonicalPath();
						int pos = fname.lastIndexOf(".");
						if (pos > 0)
						{ // on enlève le .class du nom du plugin à charger
//							System.out.println("MyZip.unzipGetPackage() on enlève le .class");
							fname = fname.substring(0, pos);
//							System.out.println("MyZip.unzipGetPackage() fname "+fname);
						}

						if (f.getParentFile().getCanonicalPath().contains("\\Plugins\\") || f.getParentFile().getCanonicalPath().contains("/Plugins/") )
						{
//							System.out.println("MyZip.unzipGetPackage() "+f.getParentFile().getCanonicalPath());
							int binIndex;
							if(Model.isWindows())
								binIndex = f.getParentFile().getCanonicalPath().indexOf("\\Plugins\\");
							else
								binIndex = f.getParentFile().getCanonicalPath().indexOf("/Plugins/");
							if (binIndex > 0)
							{
//								System.out.println("MyZip.unzipGetPackage() packageName : "+packageName);
//								System.out.println("MyZip.unzipGetPackage() binIndex "+binIndex);
								packageName = packageName.substring(binIndex+9);
								if(Model.isWindows())
									packageName = packageName.replaceAll("\\\\", "\\.");
								else
									packageName = packageName.replaceAll("/", "\\.");
//								System.out.println("MyZip.unzip() packageName " + packageName);
//								System.out.println("MyZip.unzipGetPackage() className : " + packageName +"."+fname);
								return packageName +"."+fname;
							}

						} else if (f.getParentFile().getCanonicalPath().contains("\\Plugins") || f.getParentFile().getCanonicalPath().contains("/Plugins"))
						{
//							System.out.println("MyZip.unzip() pas de package");
							return fname;
						}
					}
				}

			}
		} finally
		{
			// fermeture de la ZipInputStream
			zis.close();
		}
		return null;
	}
}
