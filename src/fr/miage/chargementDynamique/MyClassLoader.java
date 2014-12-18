package fr.miage.chargementDynamique;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.security.SecureClassLoader;
import java.util.ArrayList;

import fr.miage.Model.Model;

public class MyClassLoader extends SecureClassLoader
{

	public ArrayList<File> path = new ArrayList<File>();

	@Override
	protected Class<?> findClass(String name) throws ClassNotFoundException
	{
		byte[] b = null;
		try
		{
			b = loadClassData(name);
			// System.out.println(b.toString());
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return super.defineClass(name, b, 0, b.length);
	}

	private byte[] loadClassData(String name) throws ClassNotFoundException, IOException
	{

		// System.out.println("MyClassLoader.loadClassData() nom de la classe à charger : " + name);
		String chemin = "";
		if (Model.isWindows())
			chemin = name.replaceAll("\\.", "\\\\");
		else if (Model.isUnix())
			chemin = name.replaceAll("\\.", "/");

		// System.out.println("MyClassLoader.loadClassData() chemin après remplacement : " + chemin);

		for (File f : path)
		{

			if (f.getName().endsWith(".zip"))
			{
				String fullchemin = chemin.concat(".class");
//				System.out.println("MyClassLoader.loadClassData() dans le zip");
				MyZip mz = new MyZip();
				byte[] b = mz.unzip(f, fullchemin);
				if (b != null)
					return b;
			}

			else if (f.getName().contains(".jar"))
			{
				String fullchemin = chemin.concat(".class");
//				System.out.println("MyClassLoader.loadClassData() dans le jar");
				MyJar mj = new MyJar();
				byte[] b = mj.readJar(f, fullchemin);
				if (b != null)
					return b;

			}

			else if (f.isDirectory())
			{
				// System.out.println("MyClassLoader.loadClassData() dans le isDirectory()");
				String fullchemin = chemin.concat(".class");
				// System.out.println("MyClassLoader.loadClassData() fullcheminn : " + fullchemin);
				File[] listFiles = f.listFiles();
				for (int i = 0; i < listFiles.length; i++)
				{
					String n = listFiles[i].getPath();
					// System.out.println("MyClassLoader.loadClassData() chemin avec le bin : " + n);
					File file = new File(n);
					if (n.contains(fullchemin))
					{
						if (file.exists())
						{
							System.out.println("trouvé");
							byte[] b = Files.readAllBytes(file.toPath());
							if (b != null)
								return b;
						}
					}
				}
			}
		}
		return null;
	}
}