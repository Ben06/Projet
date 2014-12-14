package fr.miage.GUI;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class CannotAccessErrorFrame extends JFrame
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CannotAccessErrorFrame()
	{
		this.setSize(380, 115);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Impossible d'acc\u00E9der au dossier, dossier prot\u00E9g\u00E9");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setBounds(10, 11, 371, 26);
		getContentPane().add(lblNewLabel);
		
		JButton btnOk = new JButton("Ok");
		btnOk.setBounds(165, 43, 52, 23);
		btnOk.addActionListener(new ActionListener()
		{
			
			@Override
			public void actionPerformed(ActionEvent e)
			{
				CannotAccessErrorFrame.this.dispose();
			}
		});
		
		getContentPane().add(btnOk);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
}
