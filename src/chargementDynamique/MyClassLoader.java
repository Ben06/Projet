package chargementDynamique;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.SecureClassLoader;
import java.util.ArrayList;
import java.util.jar.JarFile;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class MyClassLoader extends SecureClassLoader {
	private ArrayList<File> path = new ArrayList<File>();

	public MyClassLoader(File classPath) {
		super();
		addPath(classPath);
	}

	public void addPath(File classPath) {
		if (!classPath.exists()) 
			throw new IllegalArgumentException(classPath+" does not exist!");		
		this.path.add(classPath);
	}

	@Override
	protected Class<?> findClass(String name) throws ClassNotFoundException {
		byte[] b = loadClassData(name);
		return super.defineClass(name, b, 0, b.length);
	}

	private byte[] loadClassData(String name) throws ClassNotFoundException {
		for(File dir : path) {
			byte[] b = loadClassData(dir, name);
			if (b != null) return b;
		}
		throw new ClassNotFoundException(name);
	}

	private byte[] loadClassData(File base, String name) {
		System.out.println("load "+name+" in "+base);
		name = name.replace('.', File.separatorChar)+".class";

		try {
			InputStream is = getFile(base, name);
			if (is==null) return null;

			byte[] buf = new byte[is.available()];
			is.read(buf);
			return buf;
		} catch (IOException e) {
			e.printStackTrace();
			throw new ExceptionInInitializerError("Error loading "+name+":"+e.getMessage());
		}
	}

	private InputStream getFile(File base, String name) throws IOException {
		if (base.isDirectory()) {
			File f = new File(base, name);
			if (f.exists()) 
				return new FileInputStream(f);
			else return null;
		}
		else {
			ZipFile zipFile = null;
			if (base.getName().endsWith(".jar"))
				zipFile = new JarFile(base);		
			else if (base.getName().endsWith(".zip"))
				zipFile = new ZipFile(base);
			else return null;

			ZipEntry entry = zipFile.getEntry(name);
			if (entry == null) return null;			

			return zipFile.getInputStream(entry);
		}
	}
}
