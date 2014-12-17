package fr.miage.GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextField;

import fr.miage.Model.ErrorModel;

import javax.swing.JLabel;

public class ErrorFrame extends JFrame
{
	
	public ErrorFrame()
	{
		this.setTitle("Erreur de chargement de plugin");
		this.setSize(326, 140);
		
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		
		JButton btnOk = new JButton("Ok");
		btnOk.addActionListener(new ActionListener()
		{
			
			@Override
			public void actionPerformed(ActionEvent e)
			{
				ErrorFrame.this.dispose();
			}
		});
		btnOk.setBounds(110, 68, 89, 23);
		getContentPane().add(btnOk);
		
		JLabel label = new JLabel("");
		if(ErrorModel.getPluginErrorCode()==1)
			label = new JLabel("<html>Erreur d'impl�mentation, le plugin "+ErrorModel.getPluginNameProblem()+ "<br>doit impl�menterl'interface IPluginView ou <br>IPluginAnalyse</html>");
		if(ErrorModel.getPluginErrorCode()==2)
			label = new JLabel("<html>L'interface impl�ment�e par le plugin "+ErrorModel.getPluginNameProblem()+" n'est pas pr�sente<br> dans le dossier de ce plugin</html>");
		if(ErrorModel.getPluginErrorCode()==3)
			label = new JLabel("<html>Le plugin "+ErrorModel.getPluginNameProblem()+" ne contient pas le bon nombre<br> de m�thode, son createur a probablement utilisait une interface incorrect</html>");
		if(ErrorModel.getPluginErrorCode()==4)
			label = new JLabel("<html>Le plugin "+ErrorModel.getPluginNameProblem()+" est in�xistant<br> inacc�sible, ou illisible</html>");
		if(ErrorModel.getPluginErrorCode()==5)
			label = new JLabel("<html>Le fichier "+ErrorModel.getPluginNameProblem()+" n'est pas une classe</html>");
		
		
		if(ErrorModel.getPluginInternalErrorCode()==1)
			label = new JLabel("Le plugin "+ErrorModel.getPluginNameProblem()+" est in�xistant");
		if(ErrorModel.getPluginInternalErrorCode()==2)	
			label = new JLabel("Erreur interne, acc�s ill�gal plugin : "+ErrorModel.getPluginNameProblem());
		if(ErrorModel.getPluginInternalErrorCode()==3)
			label = new JLabel("Erreur interne, argument ill�gal, plugin : "+ErrorModel.getPluginNameProblem());
		if(ErrorModel.getPluginInternalErrorCode()==4)	
			label = new JLabel("Erreur interne, erreur d'invocation, plugin : "+ErrorModel.getPluginNameProblem());
		label.setBounds(10, 11, 290, 50);
		getContentPane().add(label);
	}
}
