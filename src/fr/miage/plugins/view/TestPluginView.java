package fr.miage.plugins.view;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JList;

public class TestPluginView implements IPluginView
{
	
	@Override
	public void changerTaille(JFrame f)
	{
		f.setSize(1000, 1000);
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
		Component[] components = f.getComponents();
	}

	@Override
	public void ajouterElement(JFrame f)
	{
		
	}

	@Override
	public void customList(JFrame f)
	{
		
	}

}
