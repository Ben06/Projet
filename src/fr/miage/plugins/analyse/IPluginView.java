package fr.miage.plugins.analyse;

import javax.swing.JComponent;
import javax.swing.JFrame;

public interface IPluginView
{
	public void changerTaille(JFrame f);
	public void changerCouleur(JFrame f);
	public void changerFormeBoutons(JFrame f);
	public void ajouterElement(JFrame f, JComponent component);
	
}
