package fr.miage.fileListing;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JList;
import javax.swing.JButton;

import java.awt.TextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JMenuBar;
import javax.swing.JScrollBar;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTree;

public class GUI extends JFrame {

	FileListing list = new FileListing();
	Model model = new Model();
	
	GUI()
	{
		this.setTitle("Explorateur");
		this.setSize(500, 500);
		getContentPane().setLayout(null);
		
		// créer un nouveau dossier, dans le repertoire courant
		JButton newFile = new JButton("New");
		newFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CreateFolderGUI createFolder = new CreateFolderGUI();
			}
		});
		newFile.setBounds(129, 28, 53, 23);
		getContentPane().add(newFile);
		
		// remonter l'arborescence d'un cran
		JButton btnRemonter = new JButton("Remonter");
		btnRemonter.setBounds(208, 28, 79, 23);
		getContentPane().add(btnRemonter);
		
		
		// liste du contenu du dossier
		JList list = new JList();
//		list.add(model.getContenu());
		list.setBounds(155, 76, 300, 205);
		getContentPane().add(list);
		
		// retourner à c: ou au bureau, à définir
		JButton btnHome = new JButton("Home");
		btnHome.setBounds(45, 28, 59, 23);
		getContentPane().add(btnHome);
		
		JScrollBar scrollBar = new JScrollBar();
		scrollBar.setBounds(457, 76, 17, 205);
		getContentPane().add(scrollBar);
		
		
		JLabel lblPlugins = new JLabel("Plugins de visualisation");
		lblPlugins.setBounds(21, 323, 115, 14);
		getContentPane().add(lblPlugins);
		
		JLabel lblPluginsDanalyse = new JLabel("Plugins d'analyse");
		lblPluginsDanalyse.setBounds(21, 374, 115, 14);
		getContentPane().add(lblPluginsDanalyse);
		
		// liste des plugins de visualisation disponible, qu'on chargera à l'aide d'un classLoader personnalisé
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(245, 320, 137, 20);
		getContentPane().add(comboBox);
		
		// liste des plugins d'analyse disponible, qu'on chargera à l'aide d'un classLoader personnalisé
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(245, 371, 137, 20);
		getContentPane().add(comboBox_1);
		
		JTree tree = new JTree();
		tree.setBounds(21, 76, 124, 205);
		getContentPane().add(tree);
		
		this.setVisible(true);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	
	public static void main(String[] args) {
		GUI myGUI = new GUI();
		
	}
}
