package fr.miage.main;

import fr.miage.GUI.GUI;
import fr.miage.Model.Model;
import fr.miage.chargementDynamique.PluginInvoker;
import fr.miage.chargementDynamique.RepositoryLoader;
import fr.miage.fileListing.FileListing;

public class Main
{
	public static void main(String[] args)
	{
		FileListing listing = new FileListing();
		Model.setListing(listing);
		
		PluginInvoker invoker = new PluginInvoker();
		Model.setInvoker(invoker);
		
		RepositoryLoader repo = new RepositoryLoader();
		repo.init();
		
		if (!Model.isViewEmpty())
			Model.setViewPlugin(Model.getFirstViewPlugin());
		if (!Model.isAnalyseEmpty())
			Model.setAnalysisPlugin(Model.getFirstAnalysisPlugin());

		Model.setRepCourant(Model.getListing().getRepCourant());
		Model.setContenu(Model.getListing().getContenu());
		Model.setFileNames(Model.getListing().getFileNames());
		
		GUI myGUI = new GUI();
		Model.setGUI(myGUI);
	}
	
}
