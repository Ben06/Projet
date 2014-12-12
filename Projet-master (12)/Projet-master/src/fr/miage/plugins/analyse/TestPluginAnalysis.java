package fr.miage.plugins.analyse;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.util.Arrays;

import javax.swing.JFrame;

public class TestPluginAnalysis implements IPluginAnalyse {
	
	@Override
	public void trier (JFrame f){
		
		Component[] components = f.getContentPane().getComponents();

		 for(int i=0; i<components.length; i++){ 
			 
			 System.out.println("TestPluginAnalysis.trier() "+components[i].getName());
			 
			
			 	if(components[i].getName().equals("list")){
			 		
			 		/*Peut etre avec Arrays.sort()  */
			 		
			 		
			 	}
			 	
		 }
		
	}

	@Override
	public void ajouterDonees(JFrame f) {
	
		
	}

	@Override
	public void displayJTree(JFrame f) {
		
		
	}

	@Override
	public void afficheChemin(JFrame f) {
		
	}

}
