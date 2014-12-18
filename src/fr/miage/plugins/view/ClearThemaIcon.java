package fr.miage.plugins.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Font;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JViewport;
import javax.swing.border.LineBorder;

public class ClearThemaIcon implements IPluginView
{

	Font myFont = new Font("Corbel", Font.BOLD, 13); /* Changement de police d'écriture */
	Font myFont1 = new Font("Corbel", Font.BOLD, 12);

	@Override
	public void changerTaille(JFrame f) /* Changer la taille de la fenetre + le titre */
	{

		f.setTitle("ExploGin -Plugin de vue ClearThema");
		f.revalidate();
		f.repaint();

	}

	@Override
	public void changerCouleur(JFrame f) /* Changer background */
	{

		f.getContentPane().setBackground(Color.decode("#ecf0f1"));
		f.revalidate();
		f.repaint();

	}

	@Override
	public void changerFormeBoutons(JFrame f) /* Changer bouton */
	{

		Component[] components = f.getContentPane().getComponents();

		for (int i = 0; i < components.length; i++)
		{

//			System.out.println("ClearThema.changerFormeBoutons() " + components[i].getName());

			if (components[i].getName().equals("btnHome"))
			{

//				System.out.println("ClearThemaIcon.changerFormeBoutons() btnHome, ressources exists ? "+new File("classes/test/ressources/home1.png").exists());
				((JButton) components[i]).setText("");
				((JButton) components[i]).setIcon(new ImageIcon("./test-classes/home1.png"));

				components[i].setCursor(new Cursor(Cursor.HAND_CURSOR));
				components[i].setBackground(Color.decode("#d35400"));
				components[i].setForeground(Color.decode("#ecf0f1"));
				((JComponent) components[i]).setBorder(new LineBorder(Color.decode("#34495e")));
				((JComponent) components[i]).setToolTipText("Accueil");
				components[i].setFont(myFont);

				/* components[i].setPreferredSize(new Dimension(5, 5)); */

			}

			if (components[i].getName().equals("newFile"))
			{

				((JButton) components[i]).setText("");
				((JButton) components[i]).setIcon(new ImageIcon("./test-classes/directory1.png"));

				components[i].setCursor(new Cursor(Cursor.HAND_CURSOR));
				components[i].setBackground(Color.decode("#d35400"));
				components[i].setForeground(Color.decode("#ecf0f1"));
				((JComponent) components[i]).setBorder(new LineBorder(Color.decode("#34495e")));
				((JComponent) components[i]).setToolTipText("Nouveau Dossier");
				components[i].setFont(myFont);

			}

			if (components[i].getName().equals("btnRemonter"))
			{

				((JButton) components[i]).setText("");
				((JButton) components[i]).setIcon(new ImageIcon("./test-classes/buttonarrowup1.png"));

				components[i].setCursor(new Cursor(Cursor.HAND_CURSOR));
				components[i].setBackground(Color.decode("#d35400"));
				components[i].setForeground(Color.decode("#ecf0f1"));
				((JComponent) components[i]).setBorder(new LineBorder(Color.decode("#34495e")));
				((JComponent) components[i]).setToolTipText("Remonter");
				components[i].setFont(myFont);

			}

			if (components[i].getName().equals("btnDelete"))
			{

				((JButton) components[i]).setText("");
				((JButton) components[i]).setIcon(new ImageIcon("./test-classes/delete1.gif"));

				components[i].setCursor(new Cursor(Cursor.HAND_CURSOR));
				components[i].setBackground(Color.decode("#d35400"));
				components[i].setForeground(Color.decode("#ecf0f1"));
				((JComponent) components[i]).setBorder(new LineBorder(Color.decode("#34495e")));
				((JComponent) components[i]).setToolTipText("Effacer");
				components[i].setFont(myFont);

			}
			if (components[i].getName().equals("btnAddPlugin"))
			{

				((JButton) components[i]).setText("");
				((JButton) components[i]).setIcon(new ImageIcon("./test-classes/add1.png"));

				components[i].setCursor(new Cursor(Cursor.HAND_CURSOR));
				components[i].setBackground(Color.decode("#d35400"));
				components[i].setForeground(Color.decode("#ecf0f1"));
				((JComponent) components[i]).setToolTipText("Nouveau Plugin");
				((JComponent) components[i]).setBorder(new LineBorder(Color.decode("#34495e")));
				components[i].setFont(myFont);

			}

			if (components[i].getName().equals("btnAppliquerPlugins"))
			{

				((JButton) components[i]).setText("");
				((JButton) components[i]).setIcon(new ImageIcon("./test-classes/tick1.png"));

				components[i].setCursor(new Cursor(Cursor.HAND_CURSOR));
				components[i].setBackground(Color.decode("#d35400"));
				components[i].setForeground(Color.decode("#ecf0f1"));
				((JComponent) components[i]).setBorder(new LineBorder(Color.decode("#34495e")));
				((JComponent) components[i]).setToolTipText("Appliquer Plugin");
				components[i].setFont(myFont);

			}

			if (components[i].getName().equals("btnSauvegarder"))
			{

				((JButton) components[i]).setText("");
				((JButton) components[i]).setIcon(new ImageIcon("./test-classes/save1.png"));

				components[i].setCursor(new Cursor(Cursor.HAND_CURSOR));
				components[i].setBackground(Color.decode("#d35400"));
				components[i].setForeground(Color.decode("#ecf0f1"));
				((JComponent) components[i]).setBorder(new LineBorder(Color.decode("#34495e")));
				((JComponent) components[i]).setToolTipText("Sauvegarder Plugin");
				components[i].setFont(myFont);

			}

			if (components[i].getName().equals("reset"))
			{

				((JButton) components[i]).setText("");
				((JButton) components[i]).setIcon(new ImageIcon("./test-classes/reset1.png"));

				components[i].setCursor(new Cursor(Cursor.HAND_CURSOR));
				components[i].setBackground(Color.decode("#d35400"));
				components[i].setForeground(Color.decode("#ecf0f1"));
				((JComponent) components[i]).setBorder(new LineBorder(Color.decode("#34495e")));
				((JComponent) components[i]).setToolTipText("Retour fenetre initial");
				components[i].setFont(myFont);
			}

			if (components[i].getName().equals("lblPluginsDeVue"))
			{

				((JLabel) components[i]).setText("");
				((JLabel) components[i]).setBounds(70, 309, 151, 14);
				((JLabel) components[i]).setIcon(new ImageIcon("./test-classes/vue.png"));

				components[i].setForeground(Color.decode("#e74c3c"));

				components[i].setForeground(Color.decode("#d35400"));

				components[i].setFont(myFont);

			}

			if (components[i].getName().equals("lblPluginsDanalyse"))
			{

				((JLabel) components[i]).setText("");
				((JLabel) components[i]).setBounds(70, 349, 121, 14);
				System.out.println("ClearThemaIcon.changerFormeBoutons() file exists ? ");
				((JLabel) components[i]).setIcon(new ImageIcon("./test-classes/file.png"));

				components[i].setForeground(Color.decode("#d35400"));
				// components[i].setForeground(Color.decode("#d35400"));

				components[i].setFont(myFont);

			}

			if (components[i].getName().equals("analysisPlugins"))
			{

				components[i].setBackground(Color.decode("#ecf0f1"));
				components[i].setForeground(Color.decode("#d35400"));

				((JComponent) components[i]).setBorder(new LineBorder(Color.decode("#34495e")));
				components[i].setFont(myFont1);

			}

			if (components[i].getName().equals("viewPlugins"))
			{
				components[i].setBackground(Color.decode("#ecf0f1"));
				components[i].setForeground(Color.decode("#d35400"));
				((JComponent) components[i]).setBorder(new LineBorder(Color.decode("#34495e")));
				components[i].setFont(myFont1);

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
			if (components[i].getName().equals("scrollPane"))
			{
				System.out.println("GUI.main() dans le scrollPane");
				JScrollPane scroll = (JScrollPane) components[i];
				JViewport view = scroll.getViewport();
				Component[] list = view.getComponents();
				for (int j = 0; j < list.length; j++)
				{
					if (list[j].getName().equals("list"))
					{

						list[j].setForeground(Color.decode("#d35400"));
						((JComponent) list[j]).setBorder(new LineBorder(Color.decode("#d35400")));

					}
				}
			}
		}
	}

	@Override
	public void customList(JFrame f)
	{
	}

}
