package fr.miage.plugins.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Font;
import java.io.File;
import java.io.FileFilter;
import java.io.IOException;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.JViewport;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileSystemView;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import fr.miage.Model.Model;
import fr.miage.fileListing.FileListing;

import javax.swing.JList;


public class ListCustomize implements IPluginView
{

	Font myFont = new Font("Tahoma", Font.PLAIN, 12);
	JLabel jlabel;

	@Override
	public void changerTaille(JFrame f)
	{

		JOptionPane.showMessageDialog(f, "Plugin de Vue CustomList selectionné ! Poursuivre", "Plugin de vue", JOptionPane.INFORMATION_MESSAGE);
		f.setSize(550,550);
		f.setTitle("ExploList");
		
	}


	@Override
	public void changerCouleur(JFrame f)
	{
		f.getContentPane().setBackground(Color.white);
		f.revalidate();
		f.repaint();
	}


	@Override
	public void changerFormeBoutons(JFrame f)
	{

		Component[] components = f.getContentPane().getComponents();

		for(int i=0; i<components.length; i++){ 

			System.out.println("ListCustomize.changerFormeBoutons() "+components[i].getName());


			if(components[i].getName().equals("btnHome")){



				components[i].setBackground(Color.decode("#e74c3c"));
				components[i].setForeground(Color.decode("#ecf0f1"));
				((JComponent) components[i]).setBorder( new LineBorder(Color.decode("#c0392b")));
				((JComponent) components[i]).setToolTipText("Accueil");
				components[i].setFont(myFont);

			}

			if(components[i].getName().equals("newFile")){
				components[i].setBackground(Color.decode("#e74c3c"));
				components[i].setForeground(Color.decode("#ecf0f1"));
				((JComponent) components[i]).setBorder( new LineBorder(Color.decode("#c0392b")));
				((JComponent) components[i]).setToolTipText("Accueil");
				components[i].setFont(myFont);
			}

			if(components[i].getName().equals("btnRemonter")){
				components[i].setBackground(Color.decode("#e74c3c"));
				components[i].setForeground(Color.decode("#ecf0f1"));
				((JComponent) components[i]).setBorder( new LineBorder(Color.decode("#c0392b")));
				((JComponent) components[i]).setToolTipText("Accueil");
				components[i].setFont(myFont);

			}

			if(components[i].getName().equals("btnDelete")){
				components[i].setBackground(Color.decode("#e74c3c"));
				components[i].setForeground(Color.decode("#ecf0f1"));
				((JComponent) components[i]).setBorder( new LineBorder(Color.decode("#c0392b")));
				((JComponent) components[i]).setToolTipText("Accueil");
				components[i].setFont(myFont);

			}

			if(components[i].getName().equals("btnAddPlugin")){


				components[i].setBackground(Color.decode("#e74c3c"));
				components[i].setForeground(Color.decode("#ecf0f1"));
				((JComponent) components[i]).setBorder( new LineBorder(Color.decode("#c0392b")));
				((JComponent) components[i]).setToolTipText("Accueil");
				components[i].setFont(myFont);

			}

			if(components[i].getName().equals("btnAppliquerPlugins")){

				components[i].setBackground(Color.decode("#e74c3c"));
				components[i].setForeground(Color.decode("#ecf0f1"));
				((JComponent) components[i]).setBorder( new LineBorder(Color.decode("#c0392b")));
				((JComponent) components[i]).setToolTipText("Accueil");
				components[i].setFont(myFont);

			}

			if(components[i].getName().equals("btnSauvegarder")){

				components[i].setBackground(Color.decode("#e74c3c"));
				components[i].setForeground(Color.decode("#ecf0f1"));
				((JComponent) components[i]).setBorder( new LineBorder(Color.decode("#c0392b")));
				((JComponent) components[i]).setToolTipText("Accueil");
				components[i].setFont(myFont);

			}

			if(components[i].getName().equals("btnAnnulerPlugins")){

				components[i].setBackground(Color.decode("#e74c3c"));
				components[i].setForeground(Color.decode("#ecf0f1"));
				((JComponent) components[i]).setBorder( new LineBorder(Color.decode("#c0392b")));
				((JComponent) components[i]).setToolTipText("Accueil");
				components[i].setFont(myFont);

			}


			if(components[i].getName().equals("lblPluginsDeVue")){
				components[i].setForeground(Color.decode("#e74c3c"));
				components[i].setFont(myFont);

			}

			if(components[i].getName().equals("lblPluginsDanalyse")){

				components[i].setForeground(Color.decode("#e74c3c"));
				components[i].setFont(myFont);

			}


			if(components[i].getName().equals("analysisPlugins")){
				components[i].setBackground(Color.decode("#e74c3c"));
				components[i].setForeground(Color.decode("#ecf0f1"));
				((JComponent) components[i]).setBorder( new LineBorder(Color.decode("#c0392b")));
				components[i].setFont(myFont);

			}

			if(components[i].getName().equals("viewPlugins")){
				components[i].setBackground(Color.decode("#e74c3c"));
				components[i].setForeground(Color.decode("#ecf0f1"));
				((JComponent) components[i]).setBorder( new LineBorder(Color.decode("#c0392b")));
				components[i].setFont(myFont);	
			}




		}



	}







	@Override
	public void ajouterElement(JFrame f)

	{
		Component[] components = f.getContentPane().getComponents();




		for (int i = 0; i < components.length; i++)
		{
			System.out.println("GUI.main() " + components[i].getName());
			if(components[i].getName().equals("scrollPane"))
			{
				System.out.println("GUI.main() dans le scrollPane");
				JScrollPane scroll = (JScrollPane) components[i];
				JViewport view = scroll.getViewport();
				Component[] list = view.getComponents();
				for (int j=0; j<list.length; j++)
				{
					if(list[j].getName().equals("list"))
					{

						list[j].setForeground(Color.red);
						((JComponent) list[j]).setBorder( new LineBorder(Color.red));


					}

				}

			}


		}

	}


	@Override
	public void customList(JFrame f)
	{
		Component[] contentComponent = f.getContentPane().getComponents();
		for (int i = 0; i < contentComponent.length; i++)
		{
			if (contentComponent[i].getName().equals("scrollPane"))
			{
				// System.out.println("TestPluginView.customList() "+contentComponent[i].getName());
				JScrollPane scroll = (JScrollPane) contentComponent[i];
				JViewport view = scroll.getViewport();
				Component[] viewComponent = view.getComponents();
				for (int j = 0; j < viewComponent.length; j++)
				{
					// System.out.println("TestPluginView.customList() dans le scrollPane "+viewComponent[]);
					if (viewComponent[j].getName().equals("list"))
					{

						class FileRenderer extends DefaultListCellRenderer
						{

							private boolean pad;
							private Border padBorder = new EmptyBorder(3, 3, 3, 3);


							FileRenderer(boolean pad)
							{
								this.pad = pad;
							}


							@Override
							public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected,
									boolean cellHasFocus)
							{

								Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
								JLabel l = (JLabel) c;
								// System.out.println("TestPluginView.customList(...).FileRenderer.getListCellRendererComponent() value : "+value);
								Model.getRepCourant();
								File f = null;
								try
								{
									if (Model.isWindows())
										f = new File(Model.getRepCourant().getCanonicalPath() + "\\" + value);
									else
										f = new File(Model.getRepCourant().getCanonicalPath() + "/" + value);
								} catch (IOException e)
								{
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								l.setText(f.getName());
								l.setIcon(FileSystemView.getFileSystemView().getSystemIcon(f));

								if (pad)
								{
									l.setBorder(padBorder);
								}

								return l;
							}
						}

						((JList) viewComponent[j]).setCellRenderer(new FileRenderer(true));

						((JList) viewComponent[j]).setVisibleRowCount(9);

					}
				}
			}
		}
	}

}
