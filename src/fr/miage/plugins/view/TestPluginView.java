package fr.miage.plugins.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Font;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;


public class TestPluginView implements IPluginView
{

	Font myFont = new Font("Serif", Font.BOLD, 12);


	@Override
	public void changerTaille(JFrame f)
	{
		f.setSize(600, 600);
		f.setTitle("Explo");

	}


	@Override
	public void changerCouleur(JFrame f)
	{

		f.getContentPane().setBackground(Color.white);
		f.revalidate();
		f.repaint();

		Component[] comp = f.getContentPane().getComponents();
		for (int i = 0; i < comp.length; i++)
		{
			System.out.println("TestPluginView.changerCouleur() " + comp[i].getName());
		}

	}


	@Override
	public void changerFormeBoutons(JFrame f)
	{

		Component[] components = f.getContentPane().getComponents();

		for (int i = 0; i < components.length; i++)
		{

			System.out.println("TestPluginView.changerFormeBoutons() " + components[i].getName());

			if (components[i].getName().equals("btnHome"))
			{

				components[i].setCursor(new Cursor(Cursor.HAND_CURSOR));
				components[i].setBackground(Color.orange);
				components[i].setForeground(Color.white);
				((JComponent) components[i]).setToolTipText("Accueil");
				components[i].setFont(myFont);

				/* components[i].setPreferredSize(new Dimension(5, 5)); */

			}

			if (components[i].getName().equals("newFile"))
			{
				components[i].setCursor(new Cursor(Cursor.HAND_CURSOR));
				components[i].setBackground(Color.orange);
				components[i].setBackground(Color.orange);
				components[i].setForeground(Color.white);
				((JComponent) components[i]).setToolTipText("Nouveau Dossier");
				components[i].setFont(myFont);

			}

			if (components[i].getName().equals("btnRemonter"))
			{
				components[i].setCursor(new Cursor(Cursor.HAND_CURSOR));
				components[i].setBackground(Color.orange);
				components[i].setBackground(Color.orange);
				components[i].setForeground(Color.white);
				((JComponent) components[i]).setToolTipText("Remonter");
				components[i].setFont(myFont);

			}

			if (components[i].getName().equals("btnDelete"))
			{
				components[i].setCursor(new Cursor(Cursor.HAND_CURSOR));
				components[i].setBackground(Color.orange);
				components[i].setBackground(Color.orange);
				components[i].setForeground(Color.white);
				((JComponent) components[i]).setToolTipText("Effacer");
				components[i].setFont(myFont);

			}

			if (components[i].getName().equals("btnAddPlugin"))
			{
				components[i].setCursor(new Cursor(Cursor.HAND_CURSOR));
				components[i].setBackground(Color.orange);
				components[i].setForeground(Color.white);
				((JComponent) components[i]).setToolTipText("Nouveau Dossier");
				components[i].setFont(myFont);
				components[i].setFont(myFont);

			}

			if (components[i].getName().equals("btnAppliquerPlugins"))
			{

				components[i].setCursor(new Cursor(Cursor.HAND_CURSOR));
				components[i].setBackground(Color.orange);
				components[i].setForeground(Color.white);
				((JComponent) components[i]).setToolTipText("Appliquer Plugin");
				components[i].setFont(myFont);

			}

		}

	}


	@Override
	public void ajouterElement(JFrame f) /* Icones sur la liste *//*
																 * Recuperer
																 * JList
																 */
	{

		Component[] components = f.getContentPane().getComponents();

		for (int i = 0; i < components.length; i++)
		{

			System.out.println("TestPluginView.ajouterElement " + components[i].getName());

			if (components[i].getName().equals("list"))
			{

				components[i].setBackground(Color.black);

			}

		}

	}


	@Override
	public void customList(JFrame f)
	{

		Component[] components = f.getContentPane().getComponents();

		for (int i = 0; i < components.length; i++)
		{

			System.out.println("TestPluginView.customList() " + components[i].getName());

			if (components[i].getName().equals("analysisPlugins"))
			{
				components[i].setBackground(Color.orange);

			}

			if (components[i].getName().equals("lblPluginsDeVuePlugins"))
			{
				components[i].setBackground(Color.orange);

			}

		}

	}

}
