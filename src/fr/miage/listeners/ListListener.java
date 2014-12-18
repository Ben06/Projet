package fr.miage.listeners;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.swing.JList;
import javax.swing.SwingUtilities;

import fr.miage.GUI.CannotAccessErrorFrame;
import fr.miage.Model.Model;

public class ListListener extends MouseAdapter
{

	public void mouseClicked(MouseEvent evt)
	{
		if (SwingUtilities.isLeftMouseButton(evt))
		{
			JList list = (JList) evt.getSource();
			if (evt.getClickCount() == 1) // selection d'un �l�ment, utilis� pour la suppresion de fichier
			{
				int index = list.locationToIndex(evt.getPoint());
				Model.setSelectedFile(Model.getContenu(index));

			}
			if (evt.getClickCount() == 2) // double clic sur un �l�ment, exploration de son contenu
			{
				int index = list.locationToIndex(evt.getPoint());
				if (Model.getContenu(index).isDirectory())
				{
					try
					{
						CannotAccessErrorFrame error = null;
						boolean result = Model.getListing().setRepCourant(Model.getContenu(index).getCanonicalPath());
						if (!result) // erreur, impossible d'acc�der au dossier (dossier prot�g� / syst�me)
							error = new CannotAccessErrorFrame();
						else
							Model.getGUI().rebuildList();
					} catch (IOException e)
					{
						e.printStackTrace();
					}
				} else
				{
					System.out.println("GUI.rebuildList().new MouseAdapter() {...}.mouseClicked() pas un repertoire!");
				}
			}
		}
	}
}
