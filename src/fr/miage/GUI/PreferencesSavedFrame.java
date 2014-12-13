package fr.miage.GUI;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PreferencesSavedFrame extends JFrame
{

	public PreferencesSavedFrame()
	{
		this.setTitle("Préférences Sauvegardés");
		this.setSize(375, 115);
		this.setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		
		JButton btnOk = new JButton("Ok");
		btnOk.setBounds(134, 47, 89, 23);
		btnOk.addActionListener(new ActionListener()
		{
			
			@Override
			public void actionPerformed(ActionEvent e)
			{
				PreferencesSavedFrame.this.dispose();
			}
		});
		getContentPane().add(btnOk);
		
		JLabel lblVosPrfrencesOnt = new JLabel("Vos pr\u00E9f\u00E9rences ont bien \u00E9t\u00E9 sauvegard\u00E9es !");
		lblVosPrfrencesOnt.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblVosPrfrencesOnt.setBounds(24, 22, 339, 14);
		getContentPane().add(lblVosPrfrencesOnt);
		
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		
	}
}
