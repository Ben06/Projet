package fr.miage.fileListing;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FolderErrorFrame extends JFrame {

	
	public FolderErrorFrame() 
	{
		this.setTitle("Erreur");
		this.setSize(300, 91);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Cr\u00E9ation de dossier impossible");
		lblNewLabel.setBounds(10, 12, 185, 29);
		getContentPane().add(lblNewLabel);
		
		JButton btnNewButton = new JButton("OK");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				FolderErrorFrame.this.dispose();
			}
		});
		btnNewButton.setBounds(217, 11, 57, 31);
		getContentPane().add(btnNewButton);
		this.setVisible(true);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	
	public static void main(String[] args) {
		
		FolderErrorFrame errorFrame = new FolderErrorFrame();
	}
	
	
}
