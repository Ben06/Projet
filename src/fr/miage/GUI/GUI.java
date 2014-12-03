package fr.miage.GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;

import fr.miage.Model.Model;
import fr.miage.fileListing.FileListing;


public class GUI extends JFrame
{

	FileListing listing;
	Model model = new Model();
	JList list;
	JScrollPane scrollPane;


	GUI()
	{
		listing = new FileListing();
		model.setRepCourant(listing.getRepCourant());
		model.setContenu(listing.getContenu());
		model.setFileNames(listing.getFileNames());

		this.setTitle("Explorateur");
		this.setSize(500, 500);
		getContentPane().setLayout(null);

		// créer un nouveau dossier, dans le repertoire courant de l'explorateur
		JButton newFile = new JButton("New");
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
		scrollPane.setSize(439, 205);
		scrollPane.setLocation(21, 75);
		list = new JList(Model.getFileNames().toArray());
		list.setBounds(155, 76, 319, 205);

		GUI.this.list.addMouseListener(new MouseAdapter() 
		{
			public void mouseClicked(MouseEvent evt)
			{
				JList list = (JList) evt.getSource();
				if (evt.getClickCount() == 1) // selection d'un élément, utilisé pour la suppresion de fichier
				{
					int index = list.locationToIndex(evt.getPoint());
					GUI.this.model.setSelectedFile(GUI.this.model.getContenu(index));
				}
				if (evt.getClickCount() == 2) // double clic sur un élément, exploration de son contenu
				{
					int index = list.locationToIndex(evt.getPoint());
					if (GUI.this.model.getContenu(index).isDirectory())
					{
						try
						{
							CannotAccessErrorFrame error = null;
							boolean result = listing.setRepCourant(GUI.this.model.getContenu(index).getCanonicalPath());
							if (!result) // erreur, impossible d'accéder au dossier (dossier protégé / système)
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

		});

		scrollPane.setViewportView(list);
		getContentPane().add(scrollPane);

		/**
		 * bouton de suppresion de fichier / dossier
		 */
		JButton btnDelete = new JButton("Delete");
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
		btnAppliquerPlugins.setBounds(182, 348, 130, 23);
		btnAppliquerPlugins.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{
				executePlugins();
			}
		});

		getContentPane().add(btnAppliquerPlugins);

		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	/**
	 * methode invoquant les différentes methodes des plugins
	 * permet d'appliquer les plugins sélectionnés par l'utilisateur
	 */
	public void executePlugins()
	{
		Method[] methods = Model.getViewPlugin().getMethods();
		for (int i = 0; i < methods.length; i++)
		{
			try
			{
				Object obj = Model.getPlugin().newInstance();
				
				System.out.println(methods[i].getName());
				if (methods[i].getName().equals("changerCouleur"))
				{
					methods[i].invoke(obj, GUI.this);
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
	}
	
	/**
	 * reconstruit la liste contenant le contenu du dossier exploré
	 * afin de reconstruire la liste, on en crée une autre identique
	 * avec les nouvelles valeurs
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

		// GUI.this.scrollPane = new JScrollPane();
		// GUI.this.scrollPane.setSize(305, 205);
		// GUI.this.scrollPane.setLocation(155, 75);

		scrollPane = new JScrollPane();
		scrollPane.setSize(439, 205);
		scrollPane.setLocation(21, 75);
		// list = new JList(Model.getFileNames().toArray());
		// list.setBounds(155, 76, 319, 205);
		//
		//
		GUI.this.list = new JList(Model.getFileNames().toArray());
		GUI.this.list.setBounds(155, 76, 319, 205);

		GUI.this.list.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent evt)
			{
				JList list = (JList) evt.getSource();
				if (evt.getClickCount() == 1)
				{
					int index = list.locationToIndex(evt.getPoint());
					GUI.this.model.setSelectedFile(GUI.this.model.getContenu(index));
				}
				if (evt.getClickCount() == 2)
				{
					int index = list.locationToIndex(evt.getPoint());
					if (GUI.this.model.getContenu(index).isDirectory())
					{
						try
						{
							CannotAccessErrorFrame error = null;
							boolean result = listing.setRepCourant(GUI.this.model.getContenu(index).getCanonicalPath());

							if (result)
								rebuildList();
							else
								error = new CannotAccessErrorFrame();

						} catch (IOException e)
						{
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					} else
					{
						System.out.println("GUI.rebuildList().new MouseAdapter() {...}.mouseClicked() pas un repertoire!");
					}

				} else if (evt.getClickCount() == 3)
				{ // Triple-click
					int index = list.locationToIndex(evt.getPoint());

				}
			}

		});

		GUI.this.scrollPane.setViewportView(GUI.this.list);
		GUI.this.getContentPane().add(scrollPane);
		GUI.this.getContentPane().revalidate();
		GUI.this.getContentPane().repaint();

	}


	public static void main(String[] args)
	{
		GUI myGUI = new GUI();

	}
}
