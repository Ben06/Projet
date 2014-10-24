package fr.miage.fileListing;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ExempleExplorer {

	ExempleExplorer()
	{	
		JFrame frame = new JFrame("Explorer");
		JFileChooser fileChooser = new JFileChooser();
		JPanel panel = new JPanel();
		panel.add(fileChooser);
//		JMenu menu = new JMe
//		fileChooser.add(arg0);
		
		frame.add(panel);
		
		frame.setSize(500, 500);
		frame.setVisible(true);
		
	}
	
	
	public static void main(String[] args) {
		ExempleExplorer ex = new ExempleExplorer();
	}
	
}
