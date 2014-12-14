package fr.miage.plugins.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JViewport;
import javax.swing.border.LineBorder;

import fr.miage.GUI.GUI;


public class ClearThema implements IPluginView {

	Font myFont = new Font("Serif", Font.BOLD, 13); /*Changement de police d'écriture*/

	 	
	




	@Override
	
	
	public void changerTaille(JFrame f) /*Changer la taille de la fenetre + le titre*/
	{
		JOptionPane.showMessageDialog(f, "Plugin de Vue ClearThema selectionné ! Poursuivre", "Plugin de vue", JOptionPane.INFORMATION_MESSAGE);
		f.setSize(550,550);
		f.setTitle("Explo");
		
		/*JLabel lbl = new JLabel("Plugins de vue");
		lbl.setBounds(21, 309, 151, 14);
		GUI.this.getContentPane().add(lbl);*/
		
		
		
	}


	@Override
	public void changerCouleur(JFrame f) /*Changer background*/
	{
		
		
		

		f.getContentPane().setBackground(Color.decode("#ecf0f1"));
		f.revalidate();
		f.repaint();


	}
	


	@Override
	public void changerFormeBoutons(JFrame f) /*Changer bouton*/
	{

		Component[] components = f.getContentPane().getComponents();

		for(int i=0; i<components.length; i++){ 

			System.out.println("ClearThema.changerFormeBoutons() "+components[i].getName());


			if(components[i].getName().equals("btnHome")){


				components[i].setCursor(new Cursor(Cursor.HAND_CURSOR));
				components[i].setBackground(Color.decode("#d35400"));
				components[i].setForeground(Color.decode("#ecf0f1"));
				((JComponent) components[i]).setBorder( new LineBorder(Color.decode("#34495e")));
				((JComponent) components[i]).setToolTipText("Accueil");
				components[i].setFont(myFont);


				/*components[i].setPreferredSize(new Dimension(5, 5));*/

			}

			if(components[i].getName().equals("newFile")){
				components[i].setCursor(new Cursor(Cursor.HAND_CURSOR));
				components[i].setBackground(Color.decode("#d35400"));
				components[i].setForeground(Color.decode("#ecf0f1"));
				((JComponent) components[i]).setBorder( new LineBorder(Color.decode("#34495e")) );
				((JComponent) components[i]).setToolTipText("Nouveau Dossier");
				components[i].setFont(myFont);

			}

			if(components[i].getName().equals("btnRemonter")){
				components[i].setCursor(new Cursor(Cursor.HAND_CURSOR));
				components[i].setBackground(Color.decode("#d35400"));
				components[i].setForeground(Color.decode("#ecf0f1"));
				((JComponent) components[i]).setBorder( new LineBorder(Color.decode("#34495e")) );
				((JComponent) components[i]).setToolTipText("Remonter");
				components[i].setFont(myFont);

			}

			if(components[i].getName().equals("btnDelete")){
				components[i].setCursor(new Cursor(Cursor.HAND_CURSOR));
				components[i].setBackground(Color.decode("#d35400"));
				components[i].setForeground(Color.decode("#ecf0f1"));
				((JComponent) components[i]).setBorder( new LineBorder(Color.decode("#34495e")) );
				((JComponent) components[i]).setToolTipText("Effacer");
				components[i].setFont(myFont);

			}

			if(components[i].getName().equals("btnAddPlugin")){
				
				
				components[i].setCursor(new Cursor(Cursor.HAND_CURSOR));
				components[i].setBackground(Color.decode("#d35400"));
				components[i].setForeground(Color.decode("#ecf0f1"));
				((JComponent) components[i]).setToolTipText("Nouveau Dossier");
				((JComponent) components[i]).setBorder( new LineBorder(Color.decode("#34495e")) );
				components[i].setFont(myFont);
				

			}

			if(components[i].getName().equals("btnAppliquerPlugins")){

				components[i].setCursor(new Cursor(Cursor.HAND_CURSOR));
				components[i].setBackground(Color.decode("#d35400"));
				components[i].setForeground(Color.decode("#ecf0f1"));
				((JComponent) components[i]).setBorder( new LineBorder(Color.decode("#34495e")) );
				((JComponent) components[i]).setToolTipText("Appliquer Plugin");
				components[i].setFont(myFont);

			}

			if(components[i].getName().equals("btnSauvegarder")){

				components[i].setCursor(new Cursor(Cursor.HAND_CURSOR));
				components[i].setBackground(Color.decode("#d35400"));
				components[i].setForeground(Color.decode("#ecf0f1"));
				((JComponent) components[i]).setBorder( new LineBorder(Color.decode("#34495e")) );
				((JComponent) components[i]).setToolTipText("Sauvegarder Plugin");
				components[i].setFont(myFont);

			}

			if(components[i].getName().equals("btnAnnulerPlugins")){

				components[i].setCursor(new Cursor(Cursor.HAND_CURSOR));
				components[i].setBackground(Color.decode("#d35400"));
				components[i].setForeground(Color.decode("#ecf0f1"));
				((JComponent) components[i]).setBorder( new LineBorder(Color.decode("#34495e")) );
				((JComponent) components[i]).setToolTipText("Annuler Plugin");
				components[i].setFont(myFont);

			}
			
			if(components[i].getName().equals("reset")){
				
				
				components[i].setCursor(new Cursor(Cursor.HAND_CURSOR));
				components[i].setBackground(Color.decode("#d35400"));
				components[i].setForeground(Color.decode("#ecf0f1"));
				((JComponent) components[i]).setBorder( new LineBorder(Color.decode("#34495e")) );
				((JComponent) components[i]).setToolTipText("Annuler Plugin");
				components[i].setFont(myFont);
			}

			/*if(components[i].getName().equals("btnCrer")){

				components[i].setCursor(new Cursor(Cursor.HAND_CURSOR));
				components[i].setBackground(Color.decode("#d35400"));
				components[i].setForeground(Color.decode("#ecf0f1"));
				((JComponent) components[i]).setBorder( new LineBorder(Color.decode("#34495e")) );
				((JComponent) components[i]).setToolTipText("Annuler Plugin");
				components[i].setFont(myFont);

			}
			
			if(components[i].getName().equals("textArea")){

				components[i].setCursor(new Cursor(Cursor.HAND_CURSOR));
				components[i].setBackground(Color.decode("#d35400"));
				components[i].setForeground(Color.decode("#ecf0f1"));
				((JComponent) components[i]).setBorder( new LineBorder(Color.decode("#34495e")) );
				((JComponent) components[i]).setToolTipText("Annuler Plugin");
				components[i].setFont(myFont);

			}
*/

			if(components[i].getName().equals("lblPluginsDeVue")){

				components[i].setForeground(Color.decode("#d35400"));

				components[i].setFont(myFont);

			}
			
			if(components[i].getName().equals("lblPluginsDanalyse")){

				components[i].setForeground(Color.decode("#d35400"));

				components[i].setFont(myFont);

			}
			
			if(components[i].getName().equals("analysisPlugins")){

				components[i].setBackground(Color.decode("#ecf0f1"));
				components[i].setForeground(Color.decode("#d35400"));
				
				((JComponent) components[i]).setBorder( new LineBorder(Color.decode("#34495e")) );
				components[i].setFont(myFont);

			}

			if(components[i].getName().equals("viewPlugins")){
				components[i].setBackground(Color.decode("#ecf0f1"));
				components[i].setForeground(Color.decode("#d35400"));
				((JComponent) components[i]).setBorder( new LineBorder(Color.decode("#34495e")) );
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
							
							list[j].setForeground(Color.decode("#d35400"));
							((JComponent) list[j]).setBorder( new LineBorder(Color.decode("#d35400")) );
							
						
							
							 	
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




