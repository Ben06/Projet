package fr.miage.plugins.view;

import java.awt.Color;

import javax.swing.JComponent;
import javax.swing.JFrame;

import fr.miage.plugins.analyse.IPluginView;

public class TestPluginView implements IPluginView
{
	
	@Override
	public void changerTaille(JFrame f)
	{
		
	}

	@Override
	public void changerCouleur(JFrame f)
	{
		f.getContentPane().setBackground(Color.BLUE);
		f.revalidate();
		f.repaint();
	}

	@Override
	public void changerFormeBoutons(JFrame f)
	{
		
	}

	@Override
	public void ajouterElement(JFrame f, JComponent component)
	{
		// TODO Auto-generated method stub
		
	}

}
