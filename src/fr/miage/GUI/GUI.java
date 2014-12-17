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

import fr.miage.Model.Model;
import fr.miage.chargementDynamique.PluginInvoker;
import fr.miage.chargementDynamique.RepositoryLoader;
import fr.miage.fileListing.FileListing;
import fr.miage.listeners.AppliquerPluginListener;
import fr.miage.listeners.BtnDeleteListener;
import fr.miage.listeners.BtnPluginListener;
import fr.miage.listeners.ListListener;
import fr.miage.listeners.SauvegarderListener;

public class GUI extends JFrame
{

	JList list;
	JScrollPane scrollPane;

	private MouseAdapter mouseListener;
	JLabel lblPathCourant;
	/**
	 * liste d�roulante contenant les diff�rents plugins d'analyse pr�sents dans le r�pertoire de plugins
	 */
	JComboBox analysisPlugins;
	/**
	 * liste d�roulante contenant les diff�rents plugins de vue pr�sents dans le r�pertoire de plugins
	 */
	JComboBox viewPlugins;

	public GUI()
	{
		Model.setRepCourant(Model.getListing().getRepCourant());
		Model.setContenu(Model.getListing().getContenu());
		Model.setFileNames(Model.getListing().getFileNames());

		this.setTitle("Explorateur");
		this.setSize(500, 500);
		getContentPane().setLayout(null);

		// cr�er un nouveau dossier, dans le repertoire courant de l'explorateur
		JButton newFile = new JButton("New");
		newFile.setName("newFile");

		// dans Action listener
		newFile.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				CreateFolderGUI createFolder = new CreateFolderGUI();
			}
		});

		newFile.setBounds(105, 11, 67, 23);
		getContentPane().add(newFile);

		// remonter l'arborescence d'un cran
		JButton btnRemonter = new JButton("UP");
		btnRemonter.setName("btnRemonter");

		// dans action listener
		btnRemonter.addActionListener(new ActionListener()
		{
			CannotAccessErrorFrame error = null;

			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				boolean result = Model.getListing().remonter();
				if (result)
					rebuildList();
				else
					error = new CannotAccessErrorFrame();
			}
		});

		btnRemonter.setBounds(182, 11, 63, 23);
		getContentPane().add(btnRemonter);

		// retourner au repertoire d'origine (".")
		JButton btnHome = new JButton("Home");
		btnHome.setName("btnHome");

		// dans action listener
		btnHome.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				Model.getListing().setRepCourant(".");
				rebuildList();
			}
		});

		btnHome.setBounds(21, 11, 74, 23);
		getContentPane().add(btnHome);

		// liste contenant le contenu du dossier courant

		scrollPane = new JScrollPane();
		scrollPane.setName("scrollPane");
		scrollPane.setSize(439, 205);
		scrollPane.setLocation(21, 75);
		list = new JList(Model.getFileNames().toArray());
		list.setName("list");
		list.setBounds(155, 76, 319, 205);

		// dans listener
		mouseListener = new ListListener();

		GUI.this.list.addMouseListener(mouseListener);

		scrollPane.setViewportView(list);
		getContentPane().add(scrollPane);

		/**
		 * bouton de suppresion de fichier / dossier
		 */
		JButton btnDelete = new JButton("Delete");
		btnDelete.setName("btnDelete");
		btnDelete.setBounds(255, 11, 63, 23);

		// dans listener
		// BtnDeleteListener deleteListener = new BtnDeleteListener();
		btnDelete.addActionListener(new BtnDeleteListener());

		getContentPane().add(btnDelete);

		JButton btnAddPlugin = new JButton("Add Plugin");
		btnAddPlugin.setName("btnAddPlugin");
		btnAddPlugin.setBounds(371, 11, 89, 23);

		btnAddPlugin.addActionListener(new BtnPluginListener());
		getContentPane().add(btnAddPlugin);

		/**
		 * bouton permettant d'executer les plugins choisis
		 */
		JButton btnAppliquerPlugins = new JButton("Appliquer Plugins");
		btnAppliquerPlugins.setName("btnAppliquerPlugins");
		btnAppliquerPlugins.setBounds(170, 412, 130, 23);

		btnAppliquerPlugins.addActionListener(new AppliquerPluginListener());
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

		btnSauvegarder.addActionListener(new SauvegarderListener());
		getContentPane().add(btnSauvegarder);

		JButton reset = new JButton("Reset");
		reset.setBounds(21, 412, 121, 23);
		reset.setName("reset");

		ActionListener resetListener = new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{
				reset();
			}
		};

		// dans listeners
		reset.addActionListener(resetListener);
		getContentPane().add(reset);

		lblPathCourant = null;
		try
		{
			// System.out.println("GUI.GUI() repcourant : "+Model.getRepCourant().getCanonicalPath());//+" parent : "+Model.getRepCourant().getParentFile().getCanonicalPath());
			lblPathCourant = new JLabel(Model.getRepCourant().getCanonicalPath());
		} catch (IOException e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		lblPathCourant.setBounds(23, 45, 297, 14);
		lblPathCourant.setName("lblPathCourant");
		getContentPane().add(lblPathCourant);

		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		Model.getInvoker().applyPreferences(this);
	}

	/**
	 * reconstruit la liste contenant le contenu du dossier explor� afin de reconstruire la liste, on en cr�e une autre identique avec les nouvelles valeurs
	 */
	public void rebuildList()
	{

		Model.setRepCourant(Model.getListing().getRepCourant());
		Model.setContenu(Model.getListing().getContenu());
		Model.setFileNames(Model.getListing().getFileNames());

		if (!Model.isViewEmpty())
			Model.setViewPlugin(Model.getFirstViewPlugin());
		if (!Model.isAnalyseEmpty())
			Model.setAnalysisPlugin(Model.getFirstAnalysisPlugin());
		
		try
		{
			lblPathCourant.setText(Model.getRepCourant().getCanonicalPath());
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

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

	public void reset()
	{
		GUI.this.dispose();
		new File("preferences.txt").delete();
		GUI myGUI = new GUI();
		Model.setGUI(myGUI);
	}

	public JList getList()
	{
		return this.list;
	}

	public MouseAdapter getMouseAdapter()
	{
		return this.mouseListener;
	}
	
	public void setMouseAdapter(MouseAdapter mouse)
	{
		this.mouseListener = mouse;
	}
}
