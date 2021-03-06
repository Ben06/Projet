package fr.miage.GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JButton;

import fr.miage.Model.Model;
import fr.miage.fileListing.FileListing;

public class CreateFolderGUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String fileName="";
	Model model = new Model();
	FileListing list = new FileListing();
	JTextArea textArea;
	
	public CreateFolderGUI() {
		setTitle("Cr\u00E9ation d'un dossier");
		getContentPane().setLayout(null);
		this.setSize(401,140);
		JLabel lblNomDuDossier = new JLabel("Nom du dossier");
		lblNomDuDossier.setBounds(10, 30, 84, 14);
		getContentPane().add(lblNomDuDossier);
		textArea = new JTextArea();
		textArea.setBounds(99, 25, 235, 28);
		getContentPane().add(textArea);
		
		JButton btnCrer = new JButton("Cr\u00E9er");
		btnCrer.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
//				System.out.println(textArea.getText());
				boolean result = list.createFile(textArea.getText());
				if (result==true)
				{
					FolderCreatedFrame created = new FolderCreatedFrame();
					if(!created.isActive())
					{
						CreateFolderGUI.this.dispose();
					}
				}
				else
				{
					FolderErrorFrame error = new FolderErrorFrame();
				}
			}
		});
		btnCrer.setBounds(161, 64, 89, 23);
		getContentPane().add(btnCrer);
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	
	public static void main(String[] args) {
		CreateFolderGUI gui = new CreateFolderGUI();
	}
}
