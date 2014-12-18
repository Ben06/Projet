package fr.miage.chargementDynamique;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.jar.JarInputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

import fr.miage.Model.Model;


public class MyJar
{

	public static byte[] readJar(File jarfile, String name) throws FileNotFoundException, IOException
	{

		JarFile jar = new JarFile(jarfile.getPath());
		JarInputStream jis = new JarInputStream(new BufferedInputStream(new FileInputStream(jarfile.getCanonicalFile())));

		// extractions des entrées du fichiers zip (i.e. le contenu du zip)
		JarEntry je = null;
		try
		{
			while ((je = jis.getNextJarEntry()) != null)
			{
				File f = new File(je.getName());

				if (f.getPath().endsWith(".class")) // à modif par la suite
				{
					if (f.getPath().contains(name))
					{
//						System.out.println("MyZip.unzip() trouvé " + f.getCanonicalPath());
						InputStream in = jar.getInputStream(je);
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
			jis.close();
		}
		return null;
	}


	public static String unjarGetPackage(File jarfile, String name) throws FileNotFoundException, IOException
	{

		JarFile jar = new JarFile(jarfile.getPath());
		JarInputStream jis = new JarInputStream(new BufferedInputStream(new FileInputStream(jarfile.getCanonicalFile())));

		// extractions des entrées du fichiers zip (i.e. le contenu du zip)
		JarEntry je = null;
		try
		{
			while ((je = jis.getNextJarEntry()) != null)
			{
				// Pour chaque entrée, on crée un fichier
				// dans le répertoire de sortie "folder"
				File f = new File(je.getName());
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
			jis.close();
		}
		return null;
	}
}
