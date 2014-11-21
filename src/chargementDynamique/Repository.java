package chargementDynamique;

import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.List;

public class Repository<T> {
	private File base;
	private MyClassLoader classLoader;
	private Class<T> superClass;
	
	public Repository(File base, Class<T> superClass) {
		this.base = base;
		this.classLoader = new MyClassLoader(base);
		this.superClass = superClass;
		
	}
	
	public List<Class<? extends T>> load() {
		ArrayList<Class<? extends T>> list = new ArrayList<Class<? extends T>>();
		load(base, list, "");
		return list;
	}
	
	private void load(File b, List<Class<? extends T>> list, String name){
		File[] children = b.listFiles(new FileFilter() {
			
			@Override
			public boolean accept(File pathname) {
				return pathname.isDirectory() || 
						pathname.getName().endsWith(".class") ||
						pathname.getName().endsWith(".jar");
			}
		});
		
//		System.out.println("base:"+b);
		for(File child:children) { 
//			System.out.println(child);
			if (child.isDirectory()) load(child, list, name+child.getName()+".");
			else if (child.getName().endsWith(".class")) {
				String childName = child.getName().substring(0, child.getName().length()-6); // remove .class
				try {
					Class<?> cl = classLoader.loadClass(name+childName);
					if (superClass.isAssignableFrom(cl))
						list.add(cl.asSubclass(superClass));
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
			}
				
		}
	}
	
	public static void main(String[] args) {
		File f = new File("d:/workspace/MenuAction/bin");
		if (!f.exists()) {
			System.err.println(f+" does not exist");
			System.exit(1);
		}
		Repository<ActionListener> rep = new Repository<ActionListener>(f, ActionListener.class);
		List<Class<? extends ActionListener>> l = rep.load();
		System.out.println(l.size()+" classe(s) loaded");
	}
}
