package fr.miage.GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.swing.JFileChooser;
import javax.swing.JFrame;

import fr.miage.Model.Model;
import fr.miage.chargementDynamique.MyClassLoader;
import fr.miage.chargementDynamique.MyJar;
import fr.miage.chargementDynamique.MyZip;


public class SelectPluginFrame extends JFrame
{
	MyClassLoader mcl = new MyClassLoader();
	Model model = new Model();
	String folder = "";
	File f = null;
	JFileChooser jf;

	public SelectPluginFrame()
	{
		this.setSize(451, 400);
		jf = new JFileChooser();
		jf.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{
				Model.setPluginToLoad(jf.getSelectedFile());
				if (Model.getPluginToLoad().getName().endsWith(".zip"))
				{
					try
					{
						WrongPluginFrame error;
						MyZip zip = new MyZip();
						String name = "";
						int zipIndex = Model.getPluginToLoad().getName().indexOf(".zip");
						if(zipIndex>0)
							name = Model.getPluginToLoad().getName().substring(0, zipIndex);
						
						String className = zip.unzipGetPackage(Model.getPluginToLoad(), name);
						mcl.path.add(Model.getPluginToLoad());
						Class cl = mcl.loadClass(className);
						Class[] interfaces = cl.getInterfaces();

						boolean isConforme = false;
						for (int i = 0; i < interfaces.length; i++)
						{
							if (interfaces[i].getName().contains("IPluginView") || interfaces[i].getName().contains("IPluginAnalyse"))
							{
								isConforme = true;
							}
						}

						System.out.println("SelectPluginFrame.SelectPluginFrame().new ActionListener() {...}.actionPerformed() dans le if "+isConforme);
						if (isConforme)
						{
							File pasteLocation = null;
							InputStream is = new FileInputStream(Model.getPluginToLoad());

							if (Model.isWindows())
								pasteLocation = new File("C:\\Plugins\\" + Model.getPluginToLoad().getName());
							else
								pasteLocation = new File("/Plugins/" + Model.getPluginToLoad().getName());
							
							OutputStream os = new FileOutputStream(pasteLocation);

							byte[] buffer = new byte[1024];
							int length;

							while ((length = is.read(buffer)) > 0)
							{
								os.write(buffer, 0, length);
							}

							is.close();
							os.close();

						}
						else
							error = new WrongPluginFrame();
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
						WrongPluginFrame error = new WrongPluginFrame();
					}
				}
				else if(Model.getPluginToLoad().getName().endsWith(".jar"))
				{
					try
					{
						WrongPluginFrame error;
						MyJar jar = new MyJar();
						String name = "";
						int zipIndex = Model.getPluginToLoad().getName().indexOf(".jar");
						if(zipIndex>0)
							name = Model.getPluginToLoad().getName().substring(0, zipIndex);
						
	//					System.out.println("SelectPluginFrame.SelectPluginFrame().new ActionListener() {...}.actionPerformed()"+name);
						String className = jar.unjarGetPackage(Model.getPluginToLoad(), name);
	//					System.out.println("SelectPluginFrame.SelectPluginFrame().new ActionListener() {...}.actionPerformed()"+className);
						mcl.path.add(Model.getPluginToLoad());
						Class cl = mcl.loadClass(className);
						Class[] interfaces = cl.getInterfaces();
	
						boolean isConforme = false;
						for (int i = 0; i < interfaces.length; i++)
						{
							if (interfaces[i].getName().contains("IPluginView") || interfaces[i].getName().contains("IPluginAnalyse"))
							{
								isConforme = true;
							}
						}
	
	//					System.out.println("SelectPluginFrame.SelectPluginFrame().new ActionListener() {...}.actionPerformed() dans le if "+isConforme);
						if (isConforme)
						{
							File pasteLocation = null;
							InputStream is = new FileInputStream(Model.getPluginToLoad());
	
							if (Model.isWindows())
								pasteLocation = new File("C:\\Plugins\\" + Model.getPluginToLoad().getName());
							else
								pasteLocation = new File("/Plugins/" + Model.getPluginToLoad().getName());
							
							OutputStream os = new FileOutputStream(pasteLocation);
	
							byte[] buffer = new byte[1024];
							int length;
	
							while ((length = is.read(buffer)) > 0)
							{
								os.write(buffer, 0, length);
							}
	
							is.close();
							os.close();
	
						}
						else
							error = new WrongPluginFrame();
						}
						 catch (FileNotFoundException e1)
						{
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (IOException e1)
						{
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (ClassNotFoundException e1)
						{
							WrongPluginFrame error = new WrongPluginFrame();
						}
				}

				else
				{
					try
					{
						WrongPluginFrame error;
						NoInterfaceFrame noInter;
						String chemin = Model.getPluginToLoad().getCanonicalPath();

						int pluginsIndex = chemin.lastIndexOf("Plugins");

						String fullClassName = Model.getPluginToLoadStr();

						boolean isConforme = false;
						// vérification de la conformité du plugin
						try
						{
							mcl.path.add(Model.getPluginToLoad().getParentFile());
							Class cl = mcl.loadClass(Model.getPluginToLoadStr());
							Class[] interfaces = cl.getInterfaces();
							for (int i = 0; i < interfaces.length; i++)
							{
								if (interfaces[i].getName().contains("IPluginView") || interfaces[i].getName().contains("IPluginAnalyse"))
									isConforme = true;
							}
						} catch (ClassNotFoundException | ClassFormatError e1) // classe illisible/nulle/impossible à charger
						{
							error = new WrongPluginFrame();
						} catch (NullPointerException e2) // exception thrown quand l'interface qu'implémente le plugin n'est pas présente dans son dossier
						{
							noInter = new NoInterfaceFrame();
						}

						if (isConforme == true) // si le plugin est conforme, alors on l'ajoute au dossier de plugin
						{
							String[] splited = fullClassName.split("\\.");

							for (int i = 0; i < splited.length - 1; i++) // on récupère l'arborescence du plugin (package) et on la copie dans le dossier de plugin
							{
								if (i == 0)
								{
									if (Model.isWindows())
										folder = "\\";
									else
										folder = "/";

									if (Model.isWindows())
									{
										f = new File("C:\\Plugins" + folder + splited[i]);
										f.mkdir();
									} else
									{
										f = new File("/Plugins" + folder + splited[i]);
										f.mkdir();
									}
								} else
								{
									if (Model.isWindows())
										folder = f.getCanonicalPath() + "\\";
									else
										folder = f.getCanonicalPath() + "/";

									if (Model.isWindows())
									{
										f = new File(folder + "\\" + splited[i]);
										f.mkdir();
									} else
									{
										f = new File(folder + "/" + splited[i]);
										f.mkdir();
									}
								}

							}

							// on recopie le fichier dans le dossier de plugin, à l'emplacement créer précédemment
							File pasted = null;
							if (Model.isWindows())
								pasted = new File(f.getCanonicalPath() + "\\" + Model.getPluginToLoad().getName());
							else
								pasted = new File(f.getCanonicalPath() + "/" + Model.getPluginToLoad().getName());

							InputStream in = new FileInputStream(Model.getPluginToLoad());
							OutputStream os = new FileOutputStream(pasted);

							byte[] buffer = new byte[1024];
							int length;

							while ((length = in.read(buffer)) > 0)
							{
								os.write(buffer, 0, length);
							}

							in.close();
							os.close();

							// on recopie maintenant l'interface qu'implémente le plugin dans le dossier de plugin

							File[] list = Model.getPluginToLoad().getParentFile().listFiles();
							for (int i = 0; i < list.length; i++)
							{
								if (list[i].getName().contains("IPluginView") || list[i].getName().contains("IPluginAnalyse"))
								{
									File pasted2 = null;
									if (Model.isWindows())
										pasted2 = new File(f.getCanonicalPath() + "\\" + list[i].getName());
									else
										pasted2 = new File(f.getCanonicalPath() + "/" + list[i].getName());

									InputStream in2 = new FileInputStream(list[i]);
									OutputStream os2 = new FileOutputStream(pasted2);

									byte[] buffer2 = new byte[1024];
									int length2;

									while ((length2 = in2.read(buffer2)) > 0)
									{
										os2.write(buffer2, 0, length2);
									}
									in2.close();
									os2.close();
								}
							}
						} else
						// le plugin selectionné n'implémente pas une des interface de vue / analyse
						{
							error = new WrongPluginFrame();
						}
					} catch (FileNotFoundException e1)
					{
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1)
					{
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				SelectPluginFrame.this.dispose();
			}
		});
		this.getContentPane().add(jf);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
	}


	public static void main(String[] args)
	{
		SelectPluginFrame oui = new SelectPluginFrame();
	}
}
