package fr.miage.GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.URLClassLoader;

import javax.swing.JFileChooser;
import javax.swing.JFrame;

import chargementDynamique.MyClassLoader;
import fr.miage.Model.Model;
import fr.miage.plugins.analyse.IPluginView;
import fr.miage.plugins.view.IPluginAnalyse;


public class SelectPluginFrame extends JFrame
{
	MyClassLoader mcl = new MyClassLoader();
	Model model = new Model();


	public SelectPluginFrame()
	{
		this.setSize(451, 400);
		JFileChooser jf = new JFileChooser();
		jf.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{
				Model.setPluginToLoad(jf.getSelectedFile());
				// System.out.println(Model.getPluginToLoad().getParent());
				SelectPluginFrame.this.dispose();
				mcl.path.add(Model.getPluginToLoad().getParentFile());
				try
				{
					WrongPluginFrame error;
					Model.setPlugin(mcl.loadClass(Model.getPluginToLoadStr()));
					Class[] interfaces = Model.getPlugin().getInterfaces();
					// System.out.println("SelectPluginFrame.SelectPluginFrame().new ActionListener() {...}.actionPerformed() "+
					// Model.getPlugin().getName());
					if (interfaces.length != 0)
					{
						for (int i = 0; i < interfaces.length; i++)
						{
							System.out.println("SelectPluginFrame.SelectPluginFrame() " + Model.getPlugin().getInterfaces()[i]);
							if (interfaces[i].getName().contains("IPluginView"))
							{
								Model.setViewPlugin(Model.getPlugin());
								System.out.println("Plugin de vue ajouté");
							} else if (interfaces[i].getName().contains("IPluginAnalyse"))
							{
								Model.setAnalysisPlugin(Model.getPlugin());
								System.out.println("Plugin d'analyse ajouté");
							} else
								error = new WrongPluginFrame();
						}
					}
					else
					{
						error = new WrongPluginFrame();
					}
				} catch (ClassNotFoundException e1)
				{
					e1.printStackTrace();
				}
			}
		});
		this.getContentPane().add(jf);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}


	public static void main(String[] args)
	{
		SelectPluginFrame oui = new SelectPluginFrame();
	}
}
