package fr.miage.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import fr.miage.GUI.PreferencesSavedFrame;
import fr.miage.Model.Model;

public class SauvegarderListener implements ActionListener
{

	@Override
	public void actionPerformed(ActionEvent e)
	{
		PrintWriter writer = null;
		try
		{
			String[] lines = Model.getTmp().split(System.getProperty("line.separator"));
			writer = new PrintWriter("preferences.txt", "UTF-8");
			for (int i = 0; i < lines.length; i++)
			{
				writer.println(lines[i]);
			}
			writer.close();
		} catch (FileNotFoundException e1)
		{
		} catch (UnsupportedEncodingException e1)
		{
		}
		PreferencesSavedFrame saved = new PreferencesSavedFrame();
	}

}
