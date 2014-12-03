package chargementDynamique;

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

public class MyJar {

	public static byte[] readJar(File jarfile, String name)
			throws FileNotFoundException, IOException {

		JarFile jar = new JarFile(jarfile);
		JarInputStream jis = new JarInputStream(new BufferedInputStream(
				new FileInputStream(jarfile.getCanonicalFile())));
		JarEntry je = null;
		try {
			while ((je = jis.getNextJarEntry()) != null) {
				File f = new File(je.getName());

				String chemin = name.replaceAll("\\.", "\\\\");
				if (f.getPath().endsWith(".class")) // à modif par la suite
				{
					// modifier le chemin avant

					if (f.getPath().contains(chemin))
					// if(f.getPath().equals(anObject))) à modifier par la suite
					{
						System.out.println("MyJar.unzip() trouvé "+ f.getPath());
						InputStream in = jar.getInputStream(je);
						ByteArrayOutputStream baos = new ByteArrayOutputStream();
						// byte[] buf = new byte[1024];
						int b;
						while ((b = in.read()) != -1) {
							baos.write(b);
							// baos.write(buf, 0, b);
						}
						return baos.toByteArray();
					}
				}
			}
		} finally {
			jis.close();
		}
		// fermeture de la ZipInputStream
		return null;
	}
}
