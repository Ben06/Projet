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
import java.util.zip.ZipInputStream;


public class MyJar
{

	public static byte[] readJar(File jarfile, String name) throws FileNotFoundException, IOException
	{

		JarFile jar = new JarFile(jarfile);
		JarInputStream jis = new JarInputStream(new BufferedInputStream(new FileInputStream(jarfile.getCanonicalFile())));
		JarEntry je = null;
		try
		{
			while ((je = jis.getNextJarEntry()) != null)
			{
				File f = new File(je.getName());

				String chemin = name.replaceAll("\\.", "\\\\");
				if (f.getPath().endsWith(".class")) // à modif par la suite
				{
					// modifier le chemin avant

					if (f.getPath().contains(chemin))
					// if(f.getPath().equals(anObject))) à modifier par la suite
					{
						System.out.println("MyJar.unzip() trouvé " + f.getPath());
						InputStream in = jar.getInputStream(je);
						ByteArrayOutputStream baos = new ByteArrayOutputStream();
						// byte[] buf = new byte[1024];
						int b;
						while ((b = in.read()) != -1)
						{
							baos.write(b);
							// baos.write(buf, 0, b);
						}
						return baos.toByteArray();
					}
				}
			}
		} finally
		{
			jis.close();
		}
		// fermeture de la ZipInputStream
		return null;
	}


	public static String unjarGetPackage(File jarfile, String name) throws FileNotFoundException, IOException
	{

		JarFile jar = new JarFile(jarfile);
		JarInputStream jis = new JarInputStream(new BufferedInputStream(new FileInputStream(jarfile.getCanonicalFile())));
		JarEntry je = null;
		try
		{
			while ((je = jis.getNextJarEntry()) != null)
			{
				File f = new File(je.getName());

//				String chemin = name.replaceAll("\\.", "\\\\");
				if (f.getPath().endsWith(".class")) // à modif par la suite
				{
					// modifier le chemin avant
					String packageName = "";
					String fname = f.getName();
					
					if (f.getPath().contains(name))
					// if(f.getPath().equals(anObject))) à modifier par la suite
					{
						System.out.println("MyJar.unzip() trouvé " + f.getPath());
						System.out.println("MyZip.unzip() trouvé " + f.getCanonicalPath());
						packageName = f.getParentFile().getCanonicalPath();
						int pos = fname.lastIndexOf(".");
						if (pos > 0)
						{ // on enlève le .class du nom du plugin à charger
//							System.out.println("MyZip.unzipGetPackage() on enlève le .class");
							fname = fname.substring(0, pos);
//							System.out.println("MyZip.unzipGetPackage() fname "+fname);
						}

						if (f.getParentFile().getCanonicalPath().contains("\\bin\\"))
						{
//							System.out.println("MyZip.unzipGetPackage() "+f.getParentFile().getCanonicalPath());
							int binIndex = f.getParentFile().getCanonicalPath().indexOf("\\bin\\");
							if (binIndex > 0)
							{
//								System.out.println("MyZip.unzipGetPackage() packageName : "+packageName);
//								System.out.println("MyZip.unzipGetPackage() binIndex "+binIndex);
								packageName = packageName.substring(binIndex+5);
								packageName = packageName.replaceAll("\\\\", "\\.");
//								System.out.println("MyZip.unzip() packageName " + packageName);
//								System.out.println("MyZip.unzipGetPackage() className : " + packageName +"."+fname);
								return packageName +"."+fname;
							}

						} else if (f.getParentFile().getCanonicalPath().contains("\\bin"))
						{
							System.out.println("MyZip.unzip() pas de package");
							return fname;
						}
					}
				}
			}
		} finally
		{
			jis.close();
		}
		// fermeture de la ZipInputStream
		return null;
	}
}
