package fr.miage.plugins.view;


import java.awt.Color;



import javax.swing.JColorChooser;
import javax.swing.JFrame;




public class PersonalizeColor implements IPluginView{

	@Override
	public void changerTaille(JFrame f) {
		
		
		
	}
	
	@Override
	public void changerCouleur(JFrame f) {
		
		Color newColor = JColorChooser.showDialog(null, "Choisir la couleur de votre choix", Color.DARK_GRAY);
		f.getContentPane().setBackground(newColor);
		f.revalidate();
		f.repaint();
		
		
	}


	@Override
	public void changerFormeBoutons(JFrame f) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void ajouterElement(JFrame f) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void customList(JFrame f) {
		// TODO Auto-generated method stub
		
	}






	

	
	


				
	}

	
	


