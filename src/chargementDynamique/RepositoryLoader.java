package chargementDynamique;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import fr.miage.GUI.WrongPluginFrame;
import fr.miage.Model.Model;


public class RepositoryLoader
{
	static File base;

	static MyClassLoader mcl = new MyClassLoader();


	public boolean parcours(File base)
	{

		if (!base.exists())
			return false;
		File[] listFiles = base.listFiles();
		if (listFiles.length == 0)
			return false;
		else
		{
			for (int i = 0; i < listFiles.length; i++)
			{
				System.out.println("RepositoryLoader.parcours() " + listFiles[i].getName());
				if (listFiles[i].isDirectory())
				{
					parcours(listFiles[i]);
				} else if (listFiles[i].getName().endsWith(".class") && !(listFiles[i].getName().contains("$")))
				{
					try
					{
						String classPath = listFiles[i].getCanonicalPath();
						int index = classPath.indexOf("Plugins");
						String packageName = classPath.substring(index + 8);
						System.out.println("RepositoryLoader.parcours() packageName : " + packageName);
						if (Model.isWindows())
							packageName = packageName.replaceAll("\\\\", "\\.");
						if (Model.isUnix())
							packageName = packageName.replaceAll("/", "\\.");

						System.out.println("RepositoryLoader.parcours() packageName avec les points : " + packageName);

						int classIndex = packageName.indexOf(".class");
						if (classIndex > 0)
						{
							packageName = packageName.substring(0, classIndex);
							System.out.println("RepositoryLoader.parcours() packageName fini : " + packageName);
						}
						if (!(packageName.contains("IPluginAnalyse") || packageName.contains("IPluginView")))
						{
							mcl.path.add(listFiles[i].getParentFile());
							Class cl = mcl.loadClass(packageName);
							// System.out.println("RepositoryLoader.parcours() classe chargée : "
							// + cl.getName());
							Class[] interfaces = cl.getInterfaces();
							// System.out.println("SelectPluginFrame.SelectPluginFrame().new ActionListener() {...}.actionPerformed() "+
							// Model.getPlugin().getName());
							if (interfaces.length != 0)
							{
								for (int j = 0; j < interfaces.length; j++)
								{
									// System.out.println("SelectPluginFrame.SelectPluginFrame() "
									// + cl.getInterfaces()[j]);
									if (interfaces[j].getName().contains("IPluginView"))
									{
										Model.addViewPlugin(cl);
										System.out.println("Plugin de vue ajouté : " + cl.getName());
									} else if (interfaces[j].getName().contains("IPluginAnalyse"))
									{
										Model.addAnalysisPlugin(cl);
										System.out.println("Plugin d'analyse ajouté " + cl.getName());
									}
								}
							} 
						}
						else
							System.out.println("RepositoryLoader.parcours() interface, on ne la charge pas");
					} catch (IOException e)
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (ClassNotFoundException e)
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else if (listFiles[i].getName().endsWith(".zip"))
				{
					MyZip zip = new MyZip();
					try
					{
						System.out.println("RepositoryLoader.parcours() dans le zip");
						int index = listFiles[i].getName().indexOf(".zip");
						String name="";
						if (index>0)
							name = listFiles[i].getName().substring(0, index);
						String className = zip.unzipGetPackage(listFiles[i], name);
						mcl.path.add(listFiles[i]);
						Class cl = mcl.loadClass(className);
						
						Class[] interfaces = cl.getInterfaces();
						// System.out.println("SelectPluginFrame.SelectPluginFrame().new ActionListener() {...}.actionPerformed() "+
						// Model.getPlugin().getName());
						if (interfaces.length != 0)
						{
							for (int j = 0; j < interfaces.length; j++)
							{
								// System.out.println("SelectPluginFrame.SelectPluginFrame() "
								// + cl.getInterfaces()[j]);
								if (interfaces[j].getName().contains("IPluginView"))
								{
									Model.addViewPlugin(cl);
									System.out.println("Plugin de vue ajouté : " + cl.getName());
								} else if (interfaces[j].getName().contains("IPluginAnalyse"))
								{
									Model.addAnalysisPlugin(cl);
									System.out.println("Plugin d'analyse ajouté " + cl.getName());
								}
							}
						} else
							System.out.println("RepositoryLoader.parcours() interface, on ne la charge pas");
					} catch (FileNotFoundException e)
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e)
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (ClassNotFoundException e)
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					} 
					
				}
				else if(listFiles[i].getName().endsWith(".jar"))
				{
					MyJar jar = new MyJar();
					try
					{
						String className = jar.unjarGetPackage(listFiles[i], listFiles[i].getName());
						mcl.path.add(listFiles[i]);
						Class cl = mcl.loadClass(className);
						
						Class[] interfaces = cl.getInterfaces();
						// System.out.println("SelectPluginFrame.SelectPluginFrame().new ActionListener() {...}.actionPerformed() "+
						// Model.getPlugin().getName());
						if (interfaces.length != 0)
						{
							for (int j = 0; j < interfaces.length; j++)
							{
								// System.out.println("SelectPluginFrame.SelectPluginFrame() "
								// + cl.getInterfaces()[j]);
								if (interfaces[j].getName().contains("IPluginView"))
								{
									Model.addViewPlugin(cl);
									System.out.println("Plugin de vue ajouté : " + cl.getName());
								} else if (interfaces[j].getName().contains("IPluginAnalyse"))
								{
									Model.addAnalysisPlugin(cl);
									System.out.println("Plugin d'analyse ajouté " + cl.getName());
								}
							}
						} else
							System.out.println("RepositoryLoader.parcours() interface, on ne la charge pas");
					} catch (FileNotFoundException e)
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e)
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
			return true;
		}
	}


	public static void main(String[] args)
	{
		RepositoryLoader repo = new RepositoryLoader();
		repo.base = new File("C:\\Users\\deptinfo\\Documents\\Plugins");
		repo.parcours(base);

	}

}
