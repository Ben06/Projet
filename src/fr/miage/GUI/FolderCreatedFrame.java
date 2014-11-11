package fr.miage.GUI;

import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class FolderCreatedFrame extends JFrame {
	
	public FolderCreatedFrame() 
	{
		this.setSize(190, 110);
		getContentPane().setLayout(null);
		
		JLabel lblDossierCre = new JLabel("Dossier cr\u00E9e");
		lblDossierCre.setBounds(10, 26, 87, 27);
		getContentPane().add(lblDossierCre);
		
		JButton btnNewButton = new JButton("OK");
		btnNewButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				FolderCreatedFrame.this.dispose();
			}});
		
		btnNewButton.setBounds(107, 26, 57, 27);
		getContentPane().add(btnNewButton);
		
		this.setVisible(true);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	
	public static void main(String[] args) {
		
		FolderCreatedFrame create = new FolderCreatedFrame();
		
	}

}

