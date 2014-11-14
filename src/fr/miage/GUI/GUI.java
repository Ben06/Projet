package fr.miage.GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTree;

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

		// créer un nouveau dossier, dans le repertoire courant
		JButton newFile = new JButton("New");
		newFile.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				CreateFolderGUI createFolder = new CreateFolderGUI();
			}
		});

		newFile.setBounds(117, 28, 67, 23);
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

		btnRemonter.setBounds(208, 28, 79, 23);
		getContentPane().add(btnRemonter);

		// retourner à c: ou au bureau, à définir
		JButton btnHome = new JButton("Home");
		btnHome.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				listing.setRepCourant("C:\\Users\\deptinfo\\Documents\\GitHub\\ProjetProg\\Projet");
				rebuildList();
			}
		});

		btnHome.setBounds(21, 28, 74, 23);
		getContentPane().add(btnHome);

		scrollPane = new JScrollPane();
		scrollPane.setSize(305, 205);
		scrollPane.setLocation(155, 75);
		list = new JList(Model.getFileNames().toArray());
		list.setBounds(155, 76, 319, 205);

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
							// System.out.println("GUI.GUI().new MouseAdapter() {...}.mouseClicked() avant le set rep");
							CannotAccessErrorFrame error = null;
							boolean result = listing.setRepCourant(GUI.this.model.getContenu(index).getCanonicalPath());
							if (!result)
								error = new CannotAccessErrorFrame();
							else
								rebuildList();
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

		scrollPane.setViewportView(list);
		getContentPane().add(scrollPane);

		JLabel lblPlugins = new JLabel("Plugins de visualisation");
		lblPlugins.setBounds(21, 323, 115, 14);
		getContentPane().add(lblPlugins);

		JLabel lblPluginsDanalyse = new JLabel("Plugins d'analyse");
		lblPluginsDanalyse.setBounds(21, 374, 115, 14);
		getContentPane().add(lblPluginsDanalyse);

		// liste des plugins de visualisation disponible, qu'on chargera à
		// l'aide d'un classLoader personnalisé
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(245, 320, 137, 20);
		getContentPane().add(comboBox);

		// liste des plugins d'analyse disponible, qu'on chargera à l'aide d'un
		// classLoader personnalisé
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(245, 371, 137, 20);
		getContentPane().add(comboBox_1);

		JTree tree = new JTree();
		tree.setBounds(21, 76, 124, 205);
		getContentPane().add(tree);

		JButton btnDelete = new JButton("Delete");
		btnDelete.setBounds(312, 28, 63, 23);
		btnDelete.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{
				SuppressConfirmation delete = new SuppressConfirmation();
			}
		});
		getContentPane().add(btnDelete);

		// JScrollPane scrollPane = new JScrollPane();
		// scrollPane.setBounds(155, 76, 200, 50);
		// getContentPane().add(scrollPane);
		// this.pack();
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}


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

		GUI.this.scrollPane = new JScrollPane();
		GUI.this.scrollPane.setSize(305, 205);
		GUI.this.scrollPane.setLocation(155, 75);

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
