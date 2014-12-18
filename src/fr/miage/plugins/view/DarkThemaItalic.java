package fr.miage.plugins.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Font;


import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;

import javax.swing.JScrollPane;
import javax.swing.JViewport;

import javax.swing.border.LineBorder;


public class DarkThemaItalic implements IPluginView {
	
	Font myFont = new Font("Corbel", Font.ITALIC, 13); /*Changement de police d'écriture*/
	Font myFont1 = new Font("Corbel", Font.ITALIC, 12); 
	
	
	@Override
	public void changerTaille(JFrame f) /*Changer la taille de la fenetre + le titre*/
	{
		//JOptionPane.showMessageDialog(f, "Plugin de Vue DarkThemaItalic chargé !", "ExploGin", JOptionPane.INFORMATION_MESSAGE);
		//f.setSize(550,550);
		f.setTitle("ExploGin -Plugin de vue DarkThemaItalic-");
		
	}
	
	@Override
	public void changerCouleur(JFrame f) /*Changer background*/
	{

		f.getContentPane().setBackground(Color.decode("#bdc3c7"));
		f.revalidate();
		f.repaint();

		
		
		
		Component[] comp = f.getContentPane().getComponents();
		for (int i = 0; i<comp.length; i++)
		{
			System.out.println("DarkThema.changerCouleur() "+comp[i].getName());
		}

	}

	@Override
	public void changerFormeBoutons(JFrame f) /*Changer bouton*/
	{

		Component[] components = f.getContentPane().getComponents();

		 for(int i=0; i<components.length; i++){ 
			 
			 System.out.println("DarkThema.changerFormeBoutons() "+components[i].getName());
			 
			
			 	if(components[i].getName().equals("btnHome")){
			 		
			 		 
					
					((JButton)components[i]).setText("");
					((JButton)components[i]).setIcon(new ImageIcon("Img/home1.png"));
			 		
			 		
			 		components[i].setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
			 		components[i].setBackground(Color.decode("#2980b9"));
			 		//components[i].setForeground(Color.decode("#ecf0f1"));
			 		((JComponent) components[i]).setBorder( new LineBorder(Color.decode("#d35400")));
			 		((JComponent) components[i]).setToolTipText("Repertoire courant");
			 		//components[i].setFont(myFont);
			 		 
			 		
			 		
			 		/*components[i].setPreferredSize(new Dimension(5, 5));*/
			 		
			 	}
			 	
			 	if(components[i].getName().equals("newFile")){
			 		 
					
					((JButton)components[i]).setText("");
					((JButton)components[i]).setIcon(new ImageIcon("Img/directory1.png"));
					
			 		components[i].setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
			 		components[i].setBackground(Color.decode("#2980b9"));
			 		//components[i].setForeground(Color.decode("#ecf0f1"));//
			 		((JComponent) components[i]).setBorder( new LineBorder(Color.decode("#d35400")));
			 		((JComponent) components[i]).setToolTipText("Nouveau Dossier");
			 		//components[i].setFont(myFont);
			 		
			 	}
			 	
			 	if(components[i].getName().equals("btnRemonter")){
			 		 
					
					((JButton)components[i]).setText("");
					((JButton)components[i]).setIcon(new ImageIcon("Img/buttonarrowup1.png"));
					
			 		components[i].setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
			 		components[i].setBackground(Color.decode("#2980b9"));
			 		components[i].setForeground(Color.decode("#ecf0f1"));
			 		((JComponent) components[i]).setBorder( new LineBorder(Color.decode("#d35400")));
			 		((JComponent) components[i]).setToolTipText("Remonter");
			 		components[i].setFont(myFont);
			 		
			 	}
			 	
			 	if(components[i].getName().equals("btnDelete")){
			 		
			 		 
					
					((JButton)components[i]).setText("");
					((JButton)components[i]).setIcon(new ImageIcon("Img/delete1.gif"));
			 		components[i].setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
			 		components[i].setBackground(Color.decode("#2980b9"));
			 		components[i].setForeground(Color.decode("#ecf0f1"));
			 		((JComponent) components[i]).setBorder( new LineBorder(Color.decode("#d35400")));
			 		((JComponent) components[i]).setToolTipText("Supprimer Dossier");
			 		components[i].setFont(myFont);
			 		
			 	}
			 	
			 	if(components[i].getName().equals("btnAddPlugin")){
			 		
 
					
					((JButton)components[i]).setText("");
					((JButton)components[i]).setIcon(new ImageIcon("Img/add1.png"));
			 		components[i].setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
			 		components[i].setBackground(Color.decode("#2980b9"));
			 		components[i].setForeground(Color.decode("#ecf0f1"));
			 		((JComponent) components[i]).setBorder( new LineBorder(Color.decode("#d35400")));
			 		((JComponent) components[i]).setToolTipText("Nouveau Plugin");
			 		/*components[i].setFont(myFont);
			 		components[i].setFont(myFont);*/
			 		
			 	}
			 	
			 	if(components[i].getName().equals("btnAppliquerPlugins")){
			 		
			 		 
					
					((JButton)components[i]).setText("");
					((JButton)components[i]).setIcon(new ImageIcon("Img/tick1.png"));
			 		
					components[i].setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
			 		components[i].setBackground(Color.decode("#2980b9"));
			 		components[i].setForeground(Color.decode("#ecf0f1"));
			 		((JComponent) components[i]).setBorder( new LineBorder(Color.decode("#d35400")));
			 		((JComponent) components[i]).setToolTipText("Appliquer Plugin");
			 		components[i].setFont(myFont);
			
			 	}
			 	
			 	if(components[i].getName().equals("btnSauvegarder")){
			 		
			 		 
					
					((JButton)components[i]).setText("");
					((JButton)components[i]).setIcon(new ImageIcon("Img/save1.png"));
			 		
					components[i].setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
			 		components[i].setBackground(Color.decode("#2980b9"));
			 		components[i].setForeground(Color.decode("#ecf0f1"));
			 		((JComponent) components[i]).setBorder( new LineBorder(Color.decode("#d35400")));
			 		((JComponent) components[i]).setToolTipText("Sauvegarder Plugin");
			 		components[i].setFont(myFont);
			
			 	}
			 	
			 	
			 	if(components[i].getName().equals("reset")){
					
			 		 
					
					((JButton)components[i]).setText("");
					((JButton)components[i]).setIcon(new ImageIcon("Img/reset1.png"));
			 		
					components[i].setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
					components[i].setBackground(Color.decode("#2980b9"));
					components[i].setForeground(Color.decode("#ecf0f1"));
					((JComponent) components[i]).setBorder( new LineBorder(Color.decode("#d35400")) );
					((JComponent) components[i]).setToolTipText("Fenetre initial");
					components[i].setFont(myFont);
				}
			 	
if(components[i].getName().equals("lblPluginsDeVue")){
					
	 
	
	((JLabel)components[i]).setText("");
	((JLabel)components[i]).setBounds(70, 309, 151, 14);
	((JLabel)components[i]).setIcon(new ImageIcon("Img/vue.png"));
			 		
	components[i].setForeground(Color.decode("#2980b9"));
			 		components[i].setFont(myFont);
			 		
			 	}
				
				if(components[i].getName().equals("lblPluginsDanalyse")){
				
					
					((JLabel)components[i]).setText("");
					((JLabel)components[i]).setBounds(70, 349, 121, 14);
					((JLabel)components[i]).setIcon(new ImageIcon("Img/file.png"));
			 		
					components[i].setForeground(Color.decode("#2980b9"));
			 		components[i].setFont(myFont);
			 		
			 	}
			 
	
		if(components[i].getName().equals("analysisPlugins")){
			components[i].setBackground(Color.decode("#2980b9"));
	 		components[i].setForeground(Color.decode("#ecf0f1"));
	 		((JComponent) components[i]).setBorder( new LineBorder(Color.decode("#d35400")));
	 		components[i].setFont(myFont1);
	 		
	 	}
		
		if(components[i].getName().equals("viewPlugins")){
			components[i].setBackground(Color.decode("#2980b9"));
	 		components[i].setForeground(Color.decode("#ecf0f1"));
	 		((JComponent) components[i]).setBorder( new LineBorder(Color.decode("#d35400")));
	 		components[i].setFont(myFont1);
	 		
	 	}
		
	
		
	}
			 	
			 
			
	
		}
		 
	

	@Override
	public void ajouterElement(JFrame f) /*Recuperer JList*/ /*JList apparances*/
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
						
						list[j].setForeground(Color.decode("#2980b9"));
						((JComponent) list[j]).setBorder(new LineBorder(Color.decode("#e74c3c")) );
					
						
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
