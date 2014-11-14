package fr.miage.fileListing;

import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class SuppressConfirmation extends JFrame
{

	public SuppressConfirmation()
	{
		getContentPane().setLayout(null);
		this.setSize(455, 135);
		JLabel lblEtesVousSur = new JLabel("Etes vous sur de vouloir supprimer "+Model.getSelectedFile().getName()+" d\u00E9finitivement ?");
		lblEtesVousSur.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblEtesVousSur.setBounds(10, 22, 457, 23);
		getContentPane().add(lblEtesVousSur);
		
		JButton btnOui = new JButton("Oui");
		btnOui.setBounds(154, 63, 62, 23);
		btnOui.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				Model.getSelectedFile().delete();
				SuppressConfirmation.this.dispose();
			}
		});
		getContentPane().add(btnOui);
		
		JButton btnNon = new JButton("Non");
		btnNon.setBounds(245, 63, 62, 23);
		btnNon.addActionListener(new ActionListener()
		{
			
			@Override
			public void actionPerformed(ActionEvent e)
			{
				SuppressConfirmation.this.dispose();
			}
		});
		getContentPane().add(btnNon);
		
		
		this.setVisible(true);
		
	}
}
