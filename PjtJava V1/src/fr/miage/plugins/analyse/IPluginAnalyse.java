package fr.miage.plugins.analyse;

import javax.swing.JFrame;

public interface IPluginAnalyse
{
	public void trier(JFrame f); // permet de trier la liste en fonction de la date / taille / type
	public void ajouterDonees(JFrame f); //permet, par exemple, d'afficher la date, la taille, le type d'un fichier
	void displayJTree(JFrame f);
	void afficheChemin(JFrame f);
}
