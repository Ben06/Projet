package fr.miage.GUI;

import java.awt.Component;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JViewport;
import javax.swing.SwingUtilities;
import javax.swing.border.BevelBorder;

import chargementDynamique.RepositoryLoader;
import fr.miage.Model.Model;
import fr.miage.fileListing.FileListing;

public class GUI extends JFrame
{

	FileListing listing;
	Model model = new Model();
	JList list;
	JScrollPane scrollPane;
	RepositoryLoader repo = new RepositoryLoader();
	private MouseAdapter mouseListener;
	/**
	 * liste d�roulante contenant les diff�rents plugins d'analyse pr�sents dans le r�pertoire de plugins
	 */
	JComboBox analysisPlugins;
	/**
	 * liste d�roulante contenant les diff�rents plugins de vue pr�sents dans le r�pertoire de plugins
	 */
	JComboBox viewPlugins;
	/**
	 * liste des plugins que l'utilisateur a appliqu� depuis l'ouverture du logiciel
	 */
	String tmp = "";

	GUI()
	{
		listing = new FileListing();
		model.setRepCourant(listing.getRepCourant());
		model.setContenu(listing.getContenu());
		model.setFileNames(listing.getFileNames());

		try
		{
			if (Model.isWindows())
				repo.parcours(new File("C:\\Plugins"));
			else
				repo.parcours(new File(System.getProperty("user.home") + "/Plugins"));
		} catch (NullPointerException npe)
		{
			if (Model.isWindows())
			{
				new File("C:\\Plugins").mkdir();
				repo.parcours(new File("C:\\Plugins"));
			} else
			{
				new File(System.getProperty("user.home") + "/Plugins").mkdir();
				repo.parcours(new File(System.getProperty("user.home") + "/Plugins"));
			}
		}

		if (!Model.isViewEmpty())
			Model.setViewPlugin(Model.getFirstViewPlugin());
		if (!Model.isAnalyseEmpty())
			Model.setAnalysisPlugin(Model.getFirstAnalysisPlugin());

		this.setTitle("Explorateur");
		this.setSize(500, 500);
		getContentPane().setLayout(null);

		// cr�er un nouveau dossier, dans le repertoire courant de l'explorateur
		JButton newFile = new JButton("New");
		newFile.setName("newFile");
		newFile.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				CreateFolderGUI createFolder = new CreateFolderGUI();
			}
		});

		newFile.setBounds(105, 28, 67, 23);
		getContentPane().add(newFile);

		// remonter l'arborescence d'un cran
		JButton btnRemonter = new JButton("UP");
		btnRemonter.setName("btnRemonter");
		btnRemonter.addActionListener(new ActionListener()
		{
			CannotAccessErrorFrame error = null;

			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				boolean result = listing.remonter();
				if (result)
					rebuildList();
				else
					error = new CannotAccessErrorFrame();
			}
		});

		btnRemonter.setBounds(182, 28, 63, 23);
		getContentPane().add(btnRemonter);

		// retourner au repertoire d'origine (".")
		JButton btnHome = new JButton("Home");
		btnHome.setName("btnHome");
		btnHome.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				listing.setRepCourant(".");
				rebuildList();
			}
		});

		btnHome.setBounds(21, 28, 74, 23);
		getContentPane().add(btnHome);

		// liste contenant le contenu du dossier courant

		scrollPane = new JScrollPane();
		scrollPane.setName("scrollPane");
		scrollPane.setSize(439, 205);
		scrollPane.setLocation(21, 75);
		list = new JList(Model.getContenu().toArray());
		list.setName("list");
		list.setBounds(155, 76, 319, 205);
		mouseListener = (new MouseAdapter()
		{
			public void mouseClicked(MouseEvent evt)
			{
				if (SwingUtilities.isLeftMouseButton(evt))
				{
					JList list = (JList) evt.getSource();
					if (evt.getClickCount() == 1) // selection d'un �l�ment, utilis� pour la suppresion de fichier
					{
						int index = list.locationToIndex(evt.getPoint());
						GUI.this.model.setSelectedFile(GUI.this.model.getContenu(index));

					}
					if (evt.getClickCount() == 2) // double clic sur un �l�ment, exploration de son contenu
					{
						int index = list.locationToIndex(evt.getPoint());
						if (GUI.this.model.getContenu(index).isDirectory())
						{
							try
							{
								CannotAccessErrorFrame error = null;
								boolean result = listing.setRepCourant(GUI.this.model.getContenu(index).getCanonicalPath());
								if (!result) // erreur, impossible d'acc�der au dossier (dossier prot�g� / syst�me)
									error = new CannotAccessErrorFrame();
								else
									rebuildList();
							} catch (IOException e)
							{
								e.printStackTrace();
							}
						} else
						{
							System.out.println("GUI.rebuildList().new MouseAdapter() {...}.mouseClicked() pas un repertoire!");
						}
					}
				}
			}
		});

		GUI.this.list.addMouseListener(mouseListener);
		
		scrollPane.setViewportView(list);
		getContentPane().add(scrollPane);

		/**
		 * bouton de suppresion de fichier / dossier
		 */
		JButton btnDelete = new JButton("Delete");
		btnDelete.setName("btnDelete");
		btnDelete.setBounds(255, 28, 63, 23);
		btnDelete.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{
				SuppressConfirmation delete = new SuppressConfirmation();
			}
		});

		getContentPane().add(btnDelete);

		/**
		 * bouton d'ajout de plugin
		 */
		JButton btnAddPlugin = new JButton("Add Plugin");
		btnAddPlugin.setName("btnAddPlugin");
		btnAddPlugin.setBounds(371, 28, 89, 23);
		btnAddPlugin.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{
				SelectPluginFrame plugins = new SelectPluginFrame();
			}
		});
		getContentPane().add(btnAddPlugin);

		/**
		 * bouton permettant d'executer les plugins choisis
		 */
		JButton btnAppliquerPlugins = new JButton("Appliquer Plugins");
		btnAppliquerPlugins.setName("btnAppliquerPlugins");
		btnAppliquerPlugins.setBounds(170, 412, 130, 23);
		btnAppliquerPlugins.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{
				if (tmp == "")
					if (Model.getAnalysisPlugin() != null)
						if (Model.getViewPlugin() != null)
							tmp = Model.getAnalysisPlugin().getName() + System.getProperty("line.separator") + Model.getViewPlugin().getName();
						else
							tmp = Model.getAnalysisPlugin().getName() + System.getProperty("line.separator") + "";
					else if (Model.getViewPlugin() != null)
						tmp = "" + System.getProperty("line.separator") + Model.getViewPlugin().getName();
					else
						tmp = "" + System.getProperty("line.separator") + "";
				else if (Model.getAnalysisPlugin() != null)
					if (Model.getViewPlugin() != null)
						tmp += System.getProperty("line.separator") + Model.getAnalysisPlugin().getName() + System.getProperty("line.separator") + Model.getViewPlugin().getName();
					else
						tmp += System.getProperty("line.separator") + Model.getAnalysisPlugin().getName() + System.getProperty("line.separator") + "";
				else if (Model.getViewPlugin() != null)
					tmp += System.getProperty("line.separator") + "" + System.getProperty("line.separator") + Model.getViewPlugin().getName();
				else
					tmp += System.getProperty("line.separator") + "" + System.getProperty("line.separator") + "";
				executePlugins();
			}
		});

		getContentPane().add(btnAppliquerPlugins);

		/**
		 * liste d�roulante contenant les diff�rents plugins de vue pr�sents dans le r�pertoire plugins
		 */

		if (Model.viewPluginsNameToArray() != null)
		{
			viewPlugins = new JComboBox(Model.viewPluginsNameToArray());
		} else
		{
			viewPlugins = new JComboBox();
		}
		viewPlugins.setName("viewPlugins");
		viewPlugins.setBounds(170, 306, 250, 20);
		viewPlugins.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{
				String selected = viewPlugins.getSelectedItem().toString();
				Model.setViewPlugin(Model.getViewPlugin(selected));
			}
		});
		getContentPane().add(viewPlugins);

		if (Model.analysisPluginsNameToArray() != null)
		{
			analysisPlugins = new JComboBox(Model.analysisPluginsNameToArray());
		} else
		{
			analysisPlugins = new JComboBox();
		}
		analysisPlugins.setBounds(170, 346, 250, 20);
		analysisPlugins.setName("analysisPlugins");
		analysisPlugins.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{
				String selected = analysisPlugins.getSelectedItem().toString();
				Model.setAnalysisPlugin(Model.getAnalysisPlugin(selected));

			}
		});
		getContentPane().add(analysisPlugins);

		JLabel lblPluginsDeVue = new JLabel("Plugins de vue");
		lblPluginsDeVue.setBounds(21, 309, 151, 14);
		lblPluginsDeVue.setName("lblPluginsDeVue");
		getContentPane().add(lblPluginsDeVue);

		JLabel lblPluginsDanalyse = new JLabel("Plugins d'analyse");
		lblPluginsDanalyse.setBounds(21, 349, 121, 14);
		lblPluginsDanalyse.setName("lblPluginsDanalyse");
		getContentPane().add(lblPluginsDanalyse);

		JButton btnSauvegarder = new JButton("Sauvegarder");
		btnSauvegarder.setName("btnSauvegarder");
		btnSauvegarder.setBounds(330, 412, 130, 23);
		btnSauvegarder.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{
				PrintWriter writer = null;
				try
				{
					String[] lines = tmp.split(System.getProperty("line.separator"));
					writer = new PrintWriter("preferences.txt", "UTF-8");
					for (int i = 0; i < lines.length; i++)
					{
						writer.println(lines[i]);
					}
					writer.close();
				} catch (FileNotFoundException e1)
				{
				} catch (UnsupportedEncodingException e1)
				{
				}
				PreferencesSavedFrame saved = new PreferencesSavedFrame();
			}
		});
		getContentPane().add(btnSauvegarder);

		JButton reset = new JButton("Reset");
		reset.setBounds(21, 412, 121, 23);
		reset.setName("reset");
		reset.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{
				reset();
			}
		});
		getContentPane().add(reset);

		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		applyPreferences();
	}

	/**
	 * methode invoquant les diff�rentes methodes des plugins permet d'appliquer les plugins s�lectionn�s par l'utilisateur
	 */
	public void executePlugins()
	{
		// JPanel contentPane = (JPanel) GUI.this.getContentPane();
		// Model.setLastPanel(contentPane);
		System.out.println("GUI.executePlugins() view : " + Model.getViewPlugin().getName());
		System.out.println("GUI.executePlugins() Analyse : " + Model.getAnalysisPlugin().getName());
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
						methodsView[i].invoke(view, GUI.this);
					}
					if (methodsView[i].getName().equals("changerTaille"))
					{
						methodsView[i].invoke(view, GUI.this);
					}
					if (methodsView[i].getName().equals("changerFormeBoutons"))
					{
						methodsView[i].invoke(view, GUI.this);
					}
					if (methodsView[i].getName().equals("ajouterElement"))
					{
						methodsView[i].invoke(view, GUI.this);
					}
					if (methodsView[i].getName().equals("customList"))
					{
						methodsView[i].invoke(view, GUI.this);
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
							methodsAnalysis[j].invoke(analyse, GUI.this);
						}
						if (methodsAnalysis[j].getName().equals("ajouterDonees"))
						{
							methodsAnalysis[j].invoke(analyse, GUI.this);
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
	 * reconstruit la liste contenant le contenu du dossier explor� afin de reconstruire la liste, on en cr�e une autre identique avec les nouvelles valeurs
	 */
	public void rebuildList()
	{
		Model.setRepCourant(listing.getRepCourant());
		Model.setContenu(listing.getContenu());
		Model.setFileNames(listing.getFileNames());

		// refreshing

		GUI.this.getContentPane().remove(GUI.this.list);
		GUI.this.getContentPane().remove(GUI.this.scrollPane);

		GUI.this.scrollPane.removeAll();
		GUI.this.list.removeAll();

		GUI.this.list.clearSelection();

		scrollPane = new JScrollPane();
		scrollPane.setName("scrollPane");
		scrollPane.setSize(439, 205);
		scrollPane.setLocation(21, 75);

		GUI.this.list.setListData(Model.getFileNames().toArray());

		GUI.this.scrollPane.setViewportView(GUI.this.list);
		GUI.this.getContentPane().add(scrollPane);
		GUI.this.getContentPane().revalidate();
		GUI.this.getContentPane().repaint();

	}

	public void applyPreferences()
	{
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
						execute(line);
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

	public void execute(String className)
	{
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
							methods[i].invoke(pluginObject, GUI.this);
						}
						if (methods[i].getName().equals("ajouterDonees"))
						{
							methods[i].invoke(pluginObject, GUI.this);
						}
					} else
					{
						if (methods[i].getName().equals("changerCouleur"))
						{
							methods[i].invoke(pluginObject, GUI.this);
						}
						if (methods[i].getName().equals("changerTaille"))
						{
							methods[i].invoke(pluginObject, GUI.this);
						}
						if (methods[i].getName().equals("changerFormeBoutons"))
						{
							methods[i].invoke(pluginObject, GUI.this);
						}
						if (methods[i].getName().equals("ajouterElement"))
						{
							methods[i].invoke(pluginObject, GUI.this);
						}
						if (methods[i].getName().equals("customList"))
						{
							methods[i].invoke(pluginObject, GUI.this);
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

	public void reset()
	{
		GUI.this.dispose();
		new File("preferences.txt").delete();
		GUI myGUI = new GUI();
	}

	public FileListing getFileListing()
	{
		return this.listing;
	}
	
	public JList getList()
	{
		return this.list;
	}
	
	public MouseAdapter getMouseAdapter()
	{
		return this.mouseListener;
	}

	public static void main(String[] args)
	{
		GUI myGUI = new GUI();

	}
}
