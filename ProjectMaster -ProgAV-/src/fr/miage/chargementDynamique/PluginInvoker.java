package fr.miage.chargementDynamique;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import fr.miage.GUI.GUI;
import fr.miage.Model.Model;

public class PluginInvoker
{
	
	GUI myGUI;

	/**
	 * methode invoquant les diff�rentes methodes des plugins permet d'appliquer les plugins s�lectionn�s par l'utilisateur
	 * � mettre dans chargement dynamique
	 */
	public void executePlugins(GUI myGUI)
	{
		this.myGUI = myGUI;
		// JPanel contentPane = (JPanel) GUI.this.getContentPane();
		// Model.setLastPanel(contentPane);
//		System.out.println("GUI.executePlugins() view : " + Model.getViewPlugin().getName());
//		System.out.println("GUI.executePlugins() Analyse : " + Model.getAnalysisPlugin().getName());
		Method[] methodsView = null;
		if (Model.getViewPlugin() != null)
			methodsView = Model.getViewPlugin().getMethods();

		Method[] methodsAnalysis = null;
		if (Model.getAnalysisPlugin() != null)
			methodsAnalysis = Model.getAnalysisPlugin().getMethods();

		if (methodsView != null)
		{
			for (int i = 0; i < methodsView.length; i++)
			{
				try
				{
					Object view = Model.getViewPlugin().newInstance();
					// System.out.println(methodsView[i].getName());
					if (methodsView[i].getName().equals("changerCouleur"))
					{
						methodsView[i].invoke(view, this.myGUI);
					}
					if (methodsView[i].getName().equals("changerTaille"))
					{
						methodsView[i].invoke(view, this.myGUI);
					}
					if (methodsView[i].getName().equals("changerFormeBoutons"))
					{
						methodsView[i].invoke(view, this.myGUI);
					}
					if (methodsView[i].getName().equals("ajouterElement"))
					{
						methodsView[i].invoke(view, this.myGUI);
					}
					if (methodsView[i].getName().equals("customList"))
					{
						methodsView[i].invoke(view, this.myGUI);
					}
				} catch (InstantiationException e2)
				{
					// TODO Auto-generated catch block
					e2.printStackTrace();
				} catch (IllegalAccessException e2)
				{
					// TODO Auto-generated catch block
					e2.printStackTrace();
				} catch (IllegalArgumentException e1)
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (InvocationTargetException e1)
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
			if (methodsAnalysis != null)
			{
				for (int j = 0; j < methodsAnalysis.length; j++)
				{
					Object analyse;
//					System.out.println("GUI.executePlugins() dans le for analyse");
					try
					{
//						System.out.println("GUI.executePlugins() nom de la m�thode : "+methodsAnalysis[j].getName());
						analyse = Model.getAnalysisPlugin().newInstance();
						if (methodsAnalysis[j].getName().equals("trier"))
						{
							methodsAnalysis[j].invoke(analyse, this.myGUI);
						}
						if (methodsAnalysis[j].getName().equals("ajouterDonees"))
						{
							methodsAnalysis[j].invoke(analyse, this.myGUI);
						}
					} catch (InstantiationException e)
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IllegalAccessException e)
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IllegalArgumentException e)
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (InvocationTargetException e)
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
	}
	
	/**
	 * execute les plugins lus dans le fichier de pr�f�rences
	 * � mettre dans chargelment dynamique
	 * @param className
	 */
	public void execute(String className, GUI myGUI)
	{
		this.myGUI = myGUI;
		Class plugin = null;
		String pluginType = "";
		if (Model.getAnalysisPlugin(className) != null)
		{
			plugin = Model.getAnalysisPlugin(className);
			pluginType = "Analyse";
			// System.out.println("GUI.execute() analyse");
		} else if (Model.getViewPlugin(className) != null)
		{
			plugin = Model.getViewPlugin(className);
			pluginType = "View";
			// System.out.println("GUI.execute() view "+Model.getViewPlugin(className));
		}

		if (plugin != null)
		{
			Method[] methods = plugin.getMethods();
			try
			{
				Object pluginObject = plugin.newInstance();
				for (int i = 0; i < methods.length; i++)
				{
					if (pluginType == "Analyse")
					{
						if (methods[i].getName().equals("trier"))
						{
							methods[i].invoke(pluginObject, this.myGUI);
						}
						if (methods[i].getName().equals("ajouterDonees"))
						{
							methods[i].invoke(pluginObject, this.myGUI);
						}
					} else
					{
						if (methods[i].getName().equals("changerCouleur"))
						{
							methods[i].invoke(pluginObject, this.myGUI);
						}
						if (methods[i].getName().equals("changerTaille"))
						{
							methods[i].invoke(pluginObject, this.myGUI);
						}
						if (methods[i].getName().equals("changerFormeBoutons"))
						{
							methods[i].invoke(pluginObject, this.myGUI);
						}
						if (methods[i].getName().equals("ajouterElement"))
						{
							methods[i].invoke(pluginObject, this.myGUI);
						}
						if (methods[i].getName().equals("customList"))
						{
							methods[i].invoke(pluginObject, this.myGUI);
						}
					}
				}

			} catch (InstantiationException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}
	/**
	 * charge les pr�f�rences de l'utilisateur � mettre dans chargement dynamique
	 */
	public void applyPreferences(GUI myGUI)
	{
		this.myGUI = myGUI;
		BufferedReader br;
		try
		{
			br = new BufferedReader(new FileReader("preferences.txt"));
			try
			{
				StringBuilder sb = new StringBuilder();
				String line = br.readLine();

				while (line != null)
				{
					sb.append(line);
					sb.append(System.lineSeparator());
					line = br.readLine();
					if (line != "")
						Model.getInvoker().execute(line, this.myGUI);
				}
			} catch (IOException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally
			{
				try
				{
					br.close();
				} catch (IOException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} catch (FileNotFoundException e)
		{
		}

	}
	
}
