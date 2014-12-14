package fr.miage.Model;

import java.awt.Container;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JPanel;

import fr.miage.GUI.GUI;

public class Model
{

	// public static FileListing list;
	/**
	 * repertoire courant de l'explorateur
	 */
	public static File repCourant;

	/**
	 * contenu du repertoire courant
	 */
	public static List<File> contenu = new ArrayList<File>();

	public static List<String> fileNames = new ArrayList<>();
	/**
	 * fichier selectionné dans l'explorateur, utilisé pour la suppression de fichier
	 */
	public static File fileToDelete;

	private static File pluginToLoad;

	/**
	 * liste des plugins de vue
	 */
	private static Map<String, Class> viewPlugins = new LinkedHashMap<String, Class>();

	/**
	 * liste des plugins d'analyse
	 */
	private static Map<String, Class> analysisPlugins = new LinkedHashMap<String, Class>();

	/**
	 * le plugin de vue sélectionné dans la liste déroulante
	 */
	private static Class viewPlugin;

	/**
	 * le pluginn d'analyse séléctionné dans la liste déroulante
	 */
	private static Class analysisPlugin;

	/**
	 * plugin à charger, ajouté via le bouton AddPlugin du GUI
	 */
	private static Class plugin;

	private static String osName = System.getProperty("os.name").toLowerCase();

	/**
	 * utilisé pour revenir en arrière dans l'application des plugins
	 */
	private static JPanel lastPanel;
	
	private static GUI myGUI;

	public static GUI getGUI(){
		return myGUI;
	}
	
	public static void setGUI(GUI gui){
		Model.myGUI=gui;
	}
	public static void refreshContenu()
	{
		Model.contenu.clear();
		File[] listFile = Model.getRepCourant().listFiles();
		for (int i = 0; i<listFile.length; i++)
		{
			Model.contenu.add(listFile[i]);
		}
	}

	public static JPanel getLastPanel()
	{
		return lastPanel;
	}

	public static void setLastPanel(Container container)
	{
		Model.lastPanel = (JPanel) container;
	}

	public static boolean isAnalyseEmpty()
	{
		if (analysisPlugins.size() != 0)
			return false;
		else
			return true;
	}

	public static Object[] getContent()
	{
		return Model.getContenu().toArray();
	}

	public static boolean isViewEmpty()
	{
		if (viewPlugins.size() != 0)
			return false;
		else
			return true;
	}

	public static Class getFirstViewPlugin()
	{
		return viewPlugins.entrySet().iterator().next().getValue();
	}

	public static Class getFirstAnalysisPlugin()
	{
		return analysisPlugins.entrySet().iterator().next().getValue();
	}

	public static String[] viewPluginsNameToArray()
	{
		String[] viewPluginsNames = new String[viewPlugins.size()];
		int i = 0;
		for (String key : viewPlugins.keySet())
		{
			viewPluginsNames[i] = key;
			i++;
		}
		return viewPluginsNames;
	}

	public static String[] analysisPluginsNameToArray()
	{
		String[] analysisPluginsNames = new String[analysisPlugins.size()];
		int i = 0;
		for (String key : analysisPlugins.keySet())
		{
			analysisPluginsNames[i] = key;
			i++;
		}
		return analysisPluginsNames;
	}

	public static Class getViewPlugin(String name)
	{
		return viewPlugins.get(name);
	}

	public static Class getAnalysisPlugin(String name)
	{
		return analysisPlugins.get(name);
	}

	public static void addViewPlugin(Class cl)
	{
		viewPlugins.put(cl.getName(), cl);
	}

	public static void addAnalysisPlugin(Class cl)
	{
		analysisPlugins.put(cl.getName(), cl);
	}

	public static Class getPlugin()
	{
		return plugin;
	}

	public static void setPlugin(Class plugin)
	{
		Model.plugin = plugin;

	}

	/**
	 * retourne le plugin de vue sélectionné dans la liste déroulante
	 * 
	 * @return
	 */
	public static Class getViewPlugin()
	{
		return viewPlugin;
	}

	/**
	 * retourne le plugin d'analyse sélectionné dans la liste déroulante
	 * 
	 * @return
	 */
	public static Class getAnalysisPlugin()
	{
		return analysisPlugin;
	}

	public static void setViewPlugin(Class viewPlugin)
	{
		Model.viewPlugin = viewPlugin;
	}

	public static void setAnalysisPlugin(Class analysisPlugin)
	{
		Model.analysisPlugin = analysisPlugin;
	}

	public static boolean isWindows()
	{
		if (Model.osName.contains("win"))
			return true;
		else
			return false;
	}

	public static boolean isUnix()
	{
		if (Model.osName.contains("nux") || Model.osName.contains("nix") || Model.osName.contains("aix"))
			return true;
		else
			return false;
	}

	/**
	 * transforme le chemin du plugin à chargé en nom de classe (accompagné du package)
	 * 
	 * @return
	 */
	public static String getPluginToLoadStr()
	{

		String fname = pluginToLoad.getName();
		String classname = "";
		int pos = fname.lastIndexOf(".");
		if (pos > 0)
		{ // on enlève le .class du nom du plugin à charger
			fname = fname.substring(0, pos);
		}

		try
		// on récupère le package du plugin
		{
			classname = pluginToLoad.getParentFile().getCanonicalPath();
			System.out.println("Model.getPluginToLoadStr() " + classname);
			if (osName.contains("windows"))
			{
				if (classname.contains("Plugins"))
				{
					// System.out.println("Model.getPluginToLoadStr() dans le if");
					int posBin = classname.lastIndexOf("Plugins");
					// System.out.println("Model.getPluginToLoadStr() " +
					// posBin);
					if (posBin > 0)
					{
						classname = classname.substring(posBin + 8);
						System.out.println("Model.getPluginToLoadStr() " + classname);
						classname = classname.replaceAll("\\\\", "\\.");
						System.out.println("Model.getPluginToLoadStr() " + classname);
					}

				} else
				{
					if (classname.contains("\\bin"))
					{
						System.out.println("Model.getPluginToLoadStr() pas de package");
						return fname;
					}
					return fname;

				}

			}
			if (osName.contains("nux") || osName.contains("nux") || osName.contains("aix"))
			{
				classname = pluginToLoad.getParentFile().getCanonicalPath();
				System.out.println("Model.getPluginToLoadStr() " + classname);
				if (osName.contains("windows"))
				{
					if (classname.contains("Plugins"))
					{
						System.out.println("Model.getPluginToLoadStr() dans le if");
						int posBin = classname.lastIndexOf("Plugins");
						System.out.println("Model.getPluginToLoadStr() " + posBin);
						if (posBin > 0)
						{
							classname = classname.substring(posBin + 8);
							System.out.println("Model.getPluginToLoadStr() " + classname);
							classname = classname.replaceAll("/", "\\.");
							System.out.println("Model.getPluginToLoadStr() " + classname);
						}

					} else
					{
						if (classname.contains("/bin"))
						{
							System.out.println("Model.getPluginToLoadStr() pas de package");
							return fname;
						}
						return fname;

					}
				}
			}
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return classname + "." + fname;
	}

	public static File getRepCourant()
	{
		return repCourant;
	}

	public static File getFileToDelete()
	{
		return fileToDelete;
	}

	public static void setFileToDelete(File fileToDelete)
	{
		Model.fileToDelete = fileToDelete;
	}

	public static File getPluginToLoad()
	{
		return pluginToLoad;
	}

	public static void setPluginToLoad(File pluginToLoad)
	{
		Model.pluginToLoad = pluginToLoad;
	}

	public static String getPluginClassName()
	{
		String className = pluginToLoad.getName().substring(0, pluginToLoad.getName().length() - 6);
		className = className.replace('/', '.');
		return className;
	}

	public static void setSelectedFile(File selectedFile)
	{
		Model.fileToDelete = selectedFile;
	}

	public static List<String> getFileNames()
	{
		return fileNames;
	}

	public static void setFileNames(List<String> fileNames)
	{
		Model.fileNames = fileNames;
	}

	public static String getFileNames(int index)
	{
		return Model.fileNames.get(index);
	}

	public static void setRepCourant(File repCourant)
	{
		Model.repCourant = repCourant;
	}

	public static List<File> getContenu()
	{
		return contenu;
	}

	public static File getContenu(int index)
	{
		return contenu.get(index);
	}

	public static void setContenu(List<File> contenu)
	{
		Model.contenu = contenu;
	}

	public static void main(String[] args)
	{
		Model.setPluginToLoad(new File("C:\\Users\\deptinfo\\Documents\\TP\\Programmation Avancée\\bin\\fr\\miage\\tp1\\ex2a\\SeLit.class"));
		// System.out.println(Model.getPluginToLoadStr());
		System.out.println(Model.getPluginToLoadStr());
	}

}
