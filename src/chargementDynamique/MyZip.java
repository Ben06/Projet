package chargementDynamique;

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

public class MyZip {

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
	public static byte[] unzip(File zipfile, String name)
			throws FileNotFoundException, IOException {

		// création de la ZipInputStream qui va servir à lire les données du
		// fichier zip
//		System.out.println("MyZip.unzip() "+zipfile.getPath());
		ZipFile zip = new ZipFile(zipfile.getPath());
		ZipInputStream zis = new ZipInputStream(new BufferedInputStream(
				new FileInputStream(zipfile.getCanonicalFile())));

		// extractions des entrées du fichiers zip (i.e. le contenu du zip)
		ZipEntry ze = null;
		try {
			while ((ze = zis.getNextEntry()) != null) {
				// Pour chaque entrée, on crée un fichier
				// dans le répertoire de sortie "folder"
				File f = new File(ze.getName());
				
				String chemin = name.replaceAll("\\.","\\\\");
				if(f.getPath().endsWith(".class")) // à modif par la suite
				{
					// modifier le chemin avant
					
					if (f.getPath().contains(chemin))
//					if(f.getPath().equals(anObject))) à modifier par la suite
					{
						System.out.println("MyZip.unzip() trouvé "+f.getPath());
						InputStream in = zip.getInputStream(ze);
						ByteArrayOutputStream baos = new ByteArrayOutputStream();
//						byte[] buf = new byte[1024];
						int b;
						while((b = in.read())!=-1){
							baos.write(b);
//							baos.write(buf, 0, b);
						}
						return baos.toByteArray();
					}
				}
				
		}} finally {
			// fermeture de la ZipInputStream
			zis.close();
		}
		return null;
	}

	// Pour utiliser la méthode unzip il n'y a qu'à faire l'appel suivante
	// depuis n'importe quelle autre méthode:

	// MyZip.unzip("patatos.zip", "C:/patatos");

	// Ce qui va décompresser "patatos.zip" dans "C:/patatos".

	// On peut éventuellement rajouter une méthode main à notre class MyZip:

}
