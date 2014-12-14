package chargementDynamique;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

public class URLClassLoaderTesting
{

	public static void main(String[] args)
	{
		File rep = new File("C:\\Users\\deptinfo\\Documents\\TP\\Programmation Avancée\\bin\\fr\\miage\\tp1\\ex1b");
		try
		{
			URL url = rep.toURI().toURL();
			URL[] urls = {url};
			URLClassLoader ucl = new URLClassLoader(urls);
			ucl.loadClass("fr.miage.tp1.ex1b.Test");
			System.out.println("done");
		} catch (MalformedURLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
