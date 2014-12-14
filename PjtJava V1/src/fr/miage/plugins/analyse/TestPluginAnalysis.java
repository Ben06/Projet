package fr.miage.plugins.analyse;

import java.awt.Color;
import java.awt.Component;
import java.io.File;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JViewport;
import javax.swing.border.LineBorder;

public class TestPluginAnalysis implements IPluginAnalyse {

	@Override
	public void trier (JFrame f){ /* Acces à la liste pour trier les fichier par date par taille*/
		
		Component[] components = f.getContentPane().getComponents();

		 for(int i=0; i<components.length; i++){ 
			 
			 System.out.println("TestPluginAnalysis.trier() "+components[i].getName());
			 
			
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
							
						
						 class Filewalker { /**/

							    public void walk( String path ) {

							        File root = new File( path );
							        File[] list = root.listFiles();

							        for ( File f : list ) {
							            if ( f.isDirectory() ) {
							                walk( f.getAbsolutePath() );
							                System.out.println( "Dir:" + f.getAbsoluteFile() );
							            }
							            else {
							                System.out.println( "File:" + f.getAbsoluteFile() );
							            }
							        }
							    
						
						
							 
							    }
							    
						 }
	
						}
						
					}
					
				}
				
				
		 }
		 
		 
	}


	@Override
	public void ajouterDonees(JFrame f) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void displayJTree(JFrame f) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afficheChemin(JFrame f) {
		// TODO Auto-generated method stub
		
	}
	
}