package fr.miage.GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.URLClassLoader;

import javax.swing.JFileChooser;
import javax.swing.JFrame;

import chargementDynamique.MyClassLoader;
import chargementDynamique.MyJar;
import chargementDynamique.MyZip;
import fr.miage.Model.Model;
import fr.miage.plugins.analyse.IPluginAnalyse;
import fr.miage.plugins.view.IPluginView;


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
				System.out.println("SelectPluginFrame.SelectPluginFrame().new ActionListener() {...}.actionPerformed() "+Model.getPluginToLoad().getName());
				if (Model.getPluginToLoad().getName().endsWith(".zip"))
				{
//					mcl.path.add(Model.getPluginToLoad());
					System.out.println("SelectPluginFrame.SelectPluginFrame().new ActionListener() {...}.actionPerformed() fichier zip");
					MyZip zip = new MyZip();
					try
					{
						mcl.path.add(Model.getPluginToLoad());
						String className="";
						int zipindex = Model.getPluginToLoad().getName().indexOf(".zip");
						if(zipindex>0)
							className = Model.getPluginToLoad().getName().substring(0, zipindex);
//						System.out.println("SelectPluginFrame.SelectPluginFrame().new ActionListener() {...}.actionPerformed() wut ? "+className);
						Model.setPlugin(mcl.loadClass(zip.unzipGetPackage(Model.getPluginToLoad(), className)));
					} catch (FileNotFoundException e1)
					{
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1)
					{
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (ClassNotFoundException e1)
					{
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}
				if(Model.getPluginToLoad().getName().endsWith(".jar"))
				{
					mcl.path.add(Model.getPluginToLoad());
					System.out.println("SelectPluginFrame.SelectPluginFrame().new ActionListener() {...}.actionPerformed() fichier zip");
					MyJar jar = new MyJar();
					try
					{
						mcl.path.add(Model.getPluginToLoad());
						String className="";
						int zipindex = Model.getPluginToLoad().getName().indexOf(".zip");
						if(zipindex>0)
							className = Model.getPluginToLoad().getName().substring(0, zipindex);
//						System.out.println("SelectPluginFrame.SelectPluginFrame().new ActionListener() {...}.actionPerformed() wut ? "+className);
						Model.setPlugin(mcl.loadClass(jar.unjarGetPackage(Model.getPluginToLoad(), className)));
					} catch (FileNotFoundException e1)
					{
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1)
					{
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (ClassNotFoundException e1)
					{
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}
				else
				{
					mcl.path.add(Model.getPluginToLoad().getParentFile());
					try
					{
						Model.setPlugin(mcl.loadClass(Model.getPluginToLoadStr()));
					} catch (ClassNotFoundException e1)
					{
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				SelectPluginFrame.this.dispose();
				WrongPluginFrame error;
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
				} else
				{
					error = new WrongPluginFrame();
				}
				SelectPluginFrame.this.dispose();
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
