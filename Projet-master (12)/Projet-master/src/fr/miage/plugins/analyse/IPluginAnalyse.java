package fr.miage.plugins.analyse;

import javax.swing.JFrame;

public interface IPluginAnalyse
{
	public void trier(JFrame f); // permet de trier la liste en fonction de la date / taille / type
	public void ajouterDonees(JFrame f); //permet, par exemple, d'afficher la date, la taille, le type d'un fichier
	public void displayJTree(JFrame f); //permet d'afficher une Jtree pour se situer dans l'arborescence
	public void afficheChemin(JFrame f); //Une JTextField affichant le chemin c:/
}
