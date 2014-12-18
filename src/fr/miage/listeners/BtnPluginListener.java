package fr.miage.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import fr.miage.GUI.SelectPluginFrame;

public class BtnPluginListener implements ActionListener
{

	@Override
	public void actionPerformed(ActionEvent e)
	{
		SelectPluginFrame plugins = new SelectPluginFrame();
	}

}
