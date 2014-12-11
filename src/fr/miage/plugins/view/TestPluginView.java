package fr.miage.plugins.view;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JList;

import fr.miage.GUI.GUI;

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
		
		Component[] comp = f.getContentPane().getComponents();
		for (int i = 0; i<comp.length; i++)
		{
			System.out.println("TestPluginView.changerCouleur() "+comp[i].getName());
		}
	}

	@Override
	public void changerFormeBoutons(JFrame f)
	{
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
