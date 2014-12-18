package fr.miage.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import fr.miage.GUI.SuppressConfirmation;

public class BtnDeleteListener implements ActionListener
{

	@Override
	public void actionPerformed(ActionEvent e)
	{
		SuppressConfirmation delete = new SuppressConfirmation();
	}

}
