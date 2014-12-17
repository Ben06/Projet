package fr.miage.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
		Model.getInvoker().executePlugins(Model.getGUI());
	}

}
