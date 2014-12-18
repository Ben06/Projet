package fr.miage.plugins.view;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JList;

import fr.miage.GUI.GUI;

public interface IPluginView
{
	public void changerTaille(JFrame f);
	public void changerCouleur(JFrame f);
	public void changerFormeBoutons(JFrame f);
	public void ajouterElement(JFrame f);
	public void customList(JFrame f);
	
}
