package fr.miage.plugins.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JViewport;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;


public class TestPluginView2 implements IPluginView {
	
	Font myFont = new Font("Serif", Font.ITALIC, 13); /*Changement de police d'écriture*/
	
	
	
	

	
	@Override
	public void changerTaille(JFrame f) /*Changer la taille de la fenetre + le titre*/
	{
		f.setSize(550,550);
		f.setTitle("Explo");
		
		
	
		
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
			System.out.println("TestPluginView.changerCouleur() "+comp[i].getName());
		}

	}

	@Override
	public void changerFormeBoutons(JFrame f) /*Changer bouton*/
	{

		Component[] components = f.getContentPane().getComponents();

		 for(int i=0; i<components.length; i++){ 
			 
			 System.out.println("TestPluginView.changerFormeBoutons() "+components[i].getName());
			 
			
			 	if(components[i].getName().equals("btnHome")){
			 		
			 		
			 		components[i].setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
			 		components[i].setBackground(Color.decode("#34495e"));
			 		components[i].setForeground(Color.decode("#ecf0f1"));
			 		((JComponent) components[i]).setBorder( new LineBorder(Color.decode("#d35400")));
			 		((JComponent) components[i]).setToolTipText("Accueil");
			 		components[i].setFont(myFont);
			 		 
			 		try{
			 			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
			 			}
			 			catch(Exception e){
			 			e.printStackTrace();}
			 		
			 		/*components[i].setPreferredSize(new Dimension(5, 5));*/
			 		
			 	}
			 	
			 	if(components[i].getName().equals("newFile")){
			 		components[i].setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
			 		components[i].setBackground(Color.decode("#34495e"));
			 		components[i].setForeground(Color.decode("#ecf0f1"));
			 		((JComponent) components[i]).setBorder( new LineBorder(Color.decode("#d35400")));
			 		((JComponent) components[i]).setToolTipText("Nouveau Dossier");
			 		components[i].setFont(myFont);
			 		
			 	}
			 	
			 	if(components[i].getName().equals("btnRemonter")){
			 		components[i].setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
			 		components[i].setBackground(Color.decode("#34495e"));
			 		components[i].setForeground(Color.decode("#ecf0f1"));
			 		((JComponent) components[i]).setBorder( new LineBorder(Color.decode("#d35400")));
			 		((JComponent) components[i]).setToolTipText("Remonter");
			 		components[i].setFont(myFont);
			 		
			 	}
			 	
			 	if(components[i].getName().equals("btnDelete")){
			 		components[i].setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
			 		components[i].setBackground(Color.decode("#34495e"));
			 		components[i].setForeground(Color.decode("#ecf0f1"));
			 		((JComponent) components[i]).setBorder( new LineBorder(Color.decode("#d35400")));
			 		((JComponent) components[i]).setToolTipText("Effacer");
			 		components[i].setFont(myFont);
			 		
			 	}
			 	
			 	if(components[i].getName().equals("btnAddPlugin")){
			 		components[i].setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
			 		components[i].setBackground(Color.decode("#34495e"));
			 		components[i].setForeground(Color.decode("#ecf0f1"));
			 		((JComponent) components[i]).setBorder( new LineBorder(Color.decode("#d35400")));
			 		((JComponent) components[i]).setToolTipText("Nouveau Dossier");
			 		components[i].setFont(myFont);
			 		components[i].setFont(myFont);
			 		
			 	}
			 	
			 	if(components[i].getName().equals("btnAppliquerPlugins")){
			 		
			 		components[i].setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
			 		components[i].setBackground(Color.decode("#34495e"));
			 		components[i].setForeground(Color.decode("#ecf0f1"));
			 		((JComponent) components[i]).setBorder( new LineBorder(Color.decode("#d35400")));
			 		((JComponent) components[i]).setToolTipText("Appliquer Plugin");
			 		components[i].setFont(myFont);
			
			 	}
			 	
			 	if(components[i].getName().equals("btnSauvegarder")){
			 		
			 		components[i].setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
			 		components[i].setBackground(Color.decode("#34495e"));
			 		components[i].setForeground(Color.decode("#ecf0f1"));
			 		((JComponent) components[i]).setBorder( new LineBorder(Color.decode("#d35400")));
			 		((JComponent) components[i]).setToolTipText("Sauvegarder Plugin");
			 		components[i].setFont(myFont);
			
			 	}
			 	
			 	if(components[i].getName().equals("btnAnnulerPlugins")){
			 		
			 		components[i].setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
			 		components[i].setBackground(Color.decode("#34495e"));
			 		components[i].setForeground(Color.decode("#ecf0f1"));
			 		((JComponent) components[i]).setBorder( new LineBorder(Color.decode("#d35400")));
			 		((JComponent) components[i]).setToolTipText("Annuler Plugin");
			 		components[i].setFont(myFont);
			
			 	}
			 	
			 
			
	
		}
		 
	}

	@Override
	public void ajouterElement(JFrame f) /*Icones sur la liste */ /*Recuperer JList*/
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
						
						list[j].setForeground(Color.decode("#34495e"));
						((JComponent) list[j]).setBorder( new LineBorder(Color.decode("#34495e")) );
						ImageIcon icon = new ImageIcon("image.jpg");
						
					}
				}
			}
		}
			 
			 		
			 		
			 		
			 
			 	}
			 	
		 
			 	
		 
			 
			 
	@Override
	public void customList(JFrame f)
	{

		Component[] components = f.getContentPane().getComponents();

		 for(int i=0; i<components.length; i++){ 
			 
			 System.out.println("TestPluginView.customList() "+components[i].getName());
			 
			 if(components[i].getName().equals("lblPluginsDeVue")){
					
			 		components[i].setForeground(Color.decode("#34495e"));
			 		components[i].setFont(myFont);
			 		
			 	}
				
				if(components[i].getName().equals("lblPluginsDanalyse")){
					
			 		components[i].setForeground(Color.decode("#34495e"));
			 		components[i].setFont(myFont);
			 		
			 	}
			 
	
		if(components[i].getName().equals("analysisPlugins")){
			components[i].setBackground(Color.decode("#34495e"));
	 		components[i].setForeground(Color.decode("#ecf0f1"));
	 		((JComponent) components[i]).setBorder( new LineBorder(Color.decode("#d35400")));
	 		components[i].setFont(myFont);
	 		
	 	}
		
		if(components[i].getName().equals("viewPlugins")){
			components[i].setBackground(Color.decode("#34495e"));
	 		components[i].setForeground(Color.decode("#ecf0f1"));
	 		((JComponent) components[i]).setBorder( new LineBorder(Color.decode("#d35400")));
	 		components[i].setFont(myFont);
	 		
	 	}
		
	
		
	}
		 
	}



	
	


}


