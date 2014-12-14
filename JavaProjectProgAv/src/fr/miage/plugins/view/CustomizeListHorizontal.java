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
import javax.swing.filechooser.FileSystemView;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import fr.miage.Model.Model;
import fr.miage.fileListing.FileListing;

import javax.swing.JList;


public class CustomizeListHorizontal implements IPluginView
{

	Font myFont = new Font("Serif", Font.BOLD, 12);


	@Override
	public void changerTaille(JFrame f)
	{
	}


	@Override
	public void changerCouleur(JFrame f)
	{
	}


	@Override
	public void changerFormeBoutons(JFrame f)
	{
	}


	@Override
	public void ajouterElement(JFrame f)
	{
	}


	@SuppressWarnings({ "unchecked", "rawtypes" })
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
				        ((JList) viewComponent[j]).setLayoutOrientation(javax.swing.JList.HORIZONTAL_WRAP);
				        ((JList) viewComponent[j]).setVisibleRowCount(-1);

					}
				}
			}
		}
	}

}
