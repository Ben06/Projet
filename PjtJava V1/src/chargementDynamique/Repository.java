package chargementDynamique;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Repository {

	/**
	 * dossier dans lequel la recherche va s'effectuer
	 */
	public File base;

	/**
	 * liste des fichiers .class
	 */
	private List<String> listClassNames = new ArrayList<>();

	public Repository(File base) {
		if (base.isDirectory())
			this.base = base;
		else
			System.out.println("Pas un dossier");
	}

	public List<String> parcours(File base) {
		File[] listFiles = base.listFiles();
		for (int i = 0; i < listFiles.length; i++) {
			// System.out.println("Repository.parcours() listFiles[i].getName() : "+listFiles[i].getName());
			if (listFiles[i].isDirectory()) {
				// System.out.println("Repository.parcours() dans le if");
				parcours(listFiles[i]);
			} else if (listFiles[i].getName().endsWith(".class")) {
				String className  = listFiles[i].getPath().substring(this.base.getPath().length());
				String canonicalClassName = className.substring(1);
				String goodClassName = canonicalClassName.replaceAll("\\\\", "\\.");
				String finalClassName = goodClassName.substring(0, goodClassName.length()-6); // on enleve le .class
//				System.out.println("Repository.parcours() " +finalClassName);
				if (finalClassName.contains("bin"))
				{
					int ind = finalClassName.indexOf("bin");
//					System.out.println("Repository.parcours() "+ind);
					String withoutBin = finalClassName.substring(ind+4);
//					System.out.println("Repository.parcours() "+withoutBin);
					listClassNames.add(withoutBin);
				}
				else
					listClassNames.add(finalClassName);
			}
		}
		// System.out.println("Repository.parcours() : listClassFiles.size() : "+listClassFiles.size());
		return listClassNames;
	}

	public List<Class<?>> load() {
		List<Class<?>> listClass = new ArrayList<Class<?>>();
		MyClassLoader mcl = new MyClassLoader();
		mcl.path.add(base);
		int i = 0;
		for (String name : listClassNames) {
			try {
//				System.out.println("Repository.load() ajout de la classe : "+name );
				Class cl = mcl.loadClass(name); // bug car mauvais nom de package, il faut enlever le "\\bin\\" et tout ce qu'il se trouve avant
				System.out.println("Repository.load() classe ajoutée : "+cl.toString() +" par le classLoader : "+cl.getClassLoader());
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return listClass;
	}

	public void afficheList(List<?> list) {

		for (int i = 0; i < list.size(); i++) {
			System.out.println("Element " + i + " de la liste : " + list.get(i));
		}

	}

	public static void main(String[] args) {

		File base = new File("/Users/deptinfo/Documents/TP");
		Repository repo = new Repository(base);
		List<String> list = repo.parcours(base);
		// repo.afficheList(list);
		List<Class<?>> listClass = repo.load();

	}
}
