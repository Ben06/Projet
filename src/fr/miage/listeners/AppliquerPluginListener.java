package fr.miage.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import fr.miage.Model.Model;

public class AppliquerPluginListener implements ActionListener
{
	@Override
	public void actionPerformed(ActionEvent e)
	{
		if (Model.getTmp() == "")
			if (Model.getAnalysisPlugin() != null)
				if (Model.getViewPlugin() != null)
					Model.setTmp(Model.getAnalysisPlugin().getName() + System.getProperty("line.separator") + Model.getViewPlugin().getName());
				else
					Model.setTmp(Model.getAnalysisPlugin().getName() + System.getProperty("line.separator") + "");
			else if (Model.getViewPlugin() != null)
				Model.setTmp("" + System.getProperty("line.separator") + Model.getViewPlugin().getName());
			else
				Model.setTmp("" + System.getProperty("line.separator") + "");
		else if (Model.getAnalysisPlugin() != null)
			if (Model.getViewPlugin() != null)
				Model.concatTmp(System.getProperty("line.separator") + Model.getAnalysisPlugin().getName() + System.getProperty("line.separator") + Model.getViewPlugin().getName());
			else
				Model.concatTmp(System.getProperty("line.separator") + Model.getAnalysisPlugin().getName() + System.getProperty("line.separator") + "");
		else if (Model.getViewPlugin() != null)
			Model.concatTmp(System.getProperty("line.separator") + "" + System.getProperty("line.separator") + Model.getViewPlugin().getName());
		else
			Model.concatTmp(System.getProperty("line.separator") + "" + System.getProperty("line.separator") + "");
		
		
		PrintWriter writer = null;
		try
		{
			String[] lines = Model.getTmp().split(System.getProperty("line.separator"));
			writer = new PrintWriter("tmp.txt", "UTF-8");
			for (int i = 0; i < lines.length; i++)
			{
				System.out.println("AppliquerPluginListener.actionPerformed() lines["+i+"] : "+lines[i]);
				writer.println(lines[i]);
			}
			if(Model.getStateNumber()==0)
				Model.setStateNumber(2);
			else
				Model.setStateNumber(Model.getStateNumber()+2);
			writer.close();
		} catch (FileNotFoundException e1)
		{
		} catch (UnsupportedEncodingException e1)
		{
		}
		Model.getInvoker().executePlugins(Model.getGUI());
	}

}
