package fr.miage.plugins.analyse;

import java.awt.AWTEvent;
import java.awt.Component;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.AWTEventListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JViewport;
import javax.swing.SwingUtilities;
import javax.swing.border.BevelBorder;

import fr.miage.GUI.CannotAccessErrorFrame;
import fr.miage.GUI.CreateFolderGUI;
import fr.miage.GUI.GUI;
import fr.miage.GUI.SuppressConfirmation;
import fr.miage.Model.Model;

public class PropertyPlugin implements IPluginAnalyse
{

	GUI myGUI;
	JFrame proprietes;
	@Override
	public void trier(JFrame f)
	{

	}

	@Override
	public void ajouterDonees(GUI myGUI)
	{
		/*JOptionPane.showMessageDialog(myGUI, "Plugin d'Analyse PropertyPlugin chargé !", "ExploGin", JOptionPane.INFORMATION_MESSAGE);*/
		this.myGUI = myGUI;
//		System.out.println("PropertyPlugin.ajouterDonees(...).MyPopUp.ajouterDonees() start");
		Component[] comp = myGUI.getContentPane().getComponents();
		for (int i = 0; i < comp.length; i++)
		{
			System.out.println("GUI.main() " + comp[i].getName());
			if (comp[i].getName().equals("scrollPane"))
			{
				System.out.println("GUI.main() dans le scrollPane");
				JScrollPane scroll = (JScrollPane) comp[i];
				JViewport view = scroll.getViewport();
				Component[] list = view.getComponents();
				for (int j = 0; j < list.length; j++)
				{
					if (list[j].getName().equals("list"))
					{

//						JList liste = (JList) list[j];

						PropertyPlugin.this.myGUI.getList().removeMouseListener(PropertyPlugin.this.myGUI.getMouseAdapter());
//						MouseListener[] listener = PropertyPlugin.this.myGUI.getList().getMouseListeners();
//						for (int k = 0; k<listener.length; k++)
//						{
//							System.out.println("Mouse listener de la liste : "+listener[k].toString());
//							PropertyPlugin.this.myGUI.getList().removeMouseListener(listener[k]);
//						}
						PropertyPlugin.this.myGUI.getList().addMouseListener(new MouseAdapter()
						{
							public void mouseClicked(MouseEvent evt)
							{
								final JPopupMenu popup;
								popup = new JPopupMenu();
								Point mouseLocation = PropertyPlugin.this.myGUI.getMousePosition();
								if (SwingUtilities.isLeftMouseButton(evt))
								{
									JList list = (JList) evt.getSource();
									if (evt.getClickCount() == 1) // selection d'un ï¿½lï¿½ment, utilisï¿½ pour la suppresion de fichier
									{
										int index = list.locationToIndex(evt.getPoint());
										Model.setSelectedFile(Model.getContenu(index));
									}
									if (evt.getClickCount() == 2) // double clic sur un ï¿½lï¿½ment, exploration de son contenu
									{
										int index = list.locationToIndex(evt.getPoint());
										if (Model.getContenu(index).isDirectory())
										{
											try
											{
												CannotAccessErrorFrame error = null;
												boolean result = PropertyPlugin.this.myGUI.getFileListing().setRepCourant(Model.getContenu(index).getCanonicalPath());
												if (!result) // erreur, impossible d'accï¿½der au dossier (dossier protï¿½gï¿½ / systï¿½me)
													error = new CannotAccessErrorFrame();
												else
													PropertyPlugin.this.myGUI.rebuildList();
											} catch (IOException e)
											{
												e.printStackTrace();
											}
										} else
										{
											System.out.println("GUI.rebuildList().new MouseAdapter() {...}.mouseClicked() pas un repertoire!");
										}
									}
								} else
								{
									
//									System.out.println("GUI.main(...).new MouseAdapter() {...}.mouseClicked() rightclick");
//									System.out.println("PropertyPlugin.ajouterDonees(...).new MouseAdapter() {...}.mouseClicked() mousepos : "+PropertyPlugin.this.myGUI.getMousePosition());
//									JPopupMenu popup;
									PropertyPlugin.this.proprietes = new JFrame();

									ActionListener menuListener = new ActionListener()
									{
										public void actionPerformed(ActionEvent event)
										{
											if (event.getActionCommand().equals("Nouveau"))
											{
												CreateFolderGUI create = new CreateFolderGUI();
											} else if (event.getActionCommand().equals("Supprimer"))
											{
												SuppressConfirmation suppr = new SuppressConfirmation();
											} else
											{
												// proprietes = new JFrame();
												if (Model.getFileToDelete() != null)
													proprietes.setTitle("Proprietes de "+Model.getFileToDelete().getName());
												else
													proprietes.setTitle("");
												proprietes.setSize(330, 274);
												proprietes.getContentPane().setLayout(null);

												JLabel lblNom = new JLabel("Nom");
												lblNom.setBounds(10, 21, 46, 14);
												proprietes.getContentPane().add(lblNom);

												JLabel lblEmplacement = new JLabel("Emplacement");
												lblEmplacement.setBounds(10, 93, 69, 14);
												proprietes.getContentPane().add(lblEmplacement);

												JLabel lblNewLabel = new JLabel("Type");
												lblNewLabel.setBounds(10, 56, 46, 14);
												proprietes.getContentPane().add(lblNewLabel);

												JLabel lblTaille = new JLabel("Taille");
												lblTaille.setBounds(10, 130, 46, 14);
												proprietes.getContentPane().add(lblTaille);

												JLabel lblCreLe = new JLabel("Cr\u00E9e le");
												lblCreLe.setBounds(10, 169, 46, 14);
												proprietes.getContentPane().add(lblCreLe);

												JTextArea lblFileName;
												if (Model.getFileToDelete() != null)
													lblFileName = new JTextArea(Model.getFileToDelete().getName());
												else
													lblFileName = new JTextArea("Taille du fichier");
												lblFileName.setBounds(105, 21, 200, 14);
												proprietes.getContentPane().add(lblFileName);

												JLabel lblFileType;
												if (Model.getFileToDelete() != null)
												{
													if (Model.getFileToDelete().isDirectory())
														lblFileType = new JLabel("Dossier");
													else if (Model.getFileToDelete().getName().endsWith(".zip") || Model.getFileToDelete().getName().endsWith(".jar")
															|| Model.getFileToDelete().getName().endsWith(".rar"))
														lblFileType = new JLabel("Archive");
													else
														lblFileType = new JLabel("Fichier");
												} else
													lblFileType = new JLabel("Type de fichier");
												lblFileType.setBounds(105, 56, 200, 14);
												proprietes.getContentPane().add(lblFileType);

												JTextArea lblFilepath = null;
												if (Model.getFileToDelete() != null)
													try
													{
														lblFilepath = new JTextArea(Model.getFileToDelete().getParentFile().getCanonicalPath() );
													} catch (IOException e1)
													{
														// TODO Auto-generated catch block
														e1.printStackTrace();
													}
												else
													lblFilepath = new JTextArea("Chemin du fichier");
												lblFilepath.setBounds(105, 93, 200, 14);
												proprietes.getContentPane().add(lblFilepath);

												JLabel lblCreated;
												if (Model.getFileToDelete() != null)
												{
													BasicFileAttributes attr = null;
													try
													{
														attr = Files.readAttributes(Model.getFileToDelete().toPath(), BasicFileAttributes.class);
													} catch (IOException e)
													{
														// TODO Auto-generated catch block
														e.printStackTrace();
													}

													String size = Long.toString(attr.size())+" bytes";
													JLabel lblFilesize = new JLabel(size);
													lblFilesize.setBounds(105, 130, 200, 14);
													proprietes.getContentPane().add(lblFilesize);
													
													// System.out.println("creationTime: " + attr.creationTime());
													// System.out.println("lastAccessTime: " + attr.lastAccessTime());
													// System.out.println("lastModifiedTime: " + attr.lastModifiedTime());

													lblCreated = new JLabel(attr.creationTime().toString());
												} else
													lblCreated = new JLabel("Date de creation");
												lblCreated.setBounds(105, 169, 200, 14);
												proprietes.getContentPane().add(lblCreated);
												proprietes.setLocationRelativeTo(null);
												// proprietes.setD
												proprietes.setVisible(true);
											}
										}
									};
									
									
									JMenuItem item;
									popup.add(item = new JMenuItem("Nouveau", new ImageIcon("2.gif")));
									item.setHorizontalTextPosition(JMenuItem.RIGHT);
									item.addActionListener(menuListener);
									popup.add(item = new JMenuItem("Supprimer", new ImageIcon("3.gif")));
									item.setHorizontalTextPosition(JMenuItem.RIGHT);
									item.addActionListener(menuListener);

									popup.addSeparator();
									popup.add(item = new JMenuItem("Proprietes"));
									item.addActionListener(menuListener);

									popup.setLabel("Justification");
									popup.setBorder(new BevelBorder(BevelBorder.RAISED));
								    if(popup != null)
								    {
								        Toolkit.getDefaultToolkit().addAWTEventListener(new AWTEventListener()
								        {
								            @Override
								            public void eventDispatched(AWTEvent event) {

								                if(event instanceof MouseEvent)
								                {
								                    MouseEvent m = (MouseEvent)event;
								                    if(m.getID() == MouseEvent.MOUSE_CLICKED)
								                    {
								                        popup.setVisible(false);
								                        Toolkit.getDefaultToolkit().removeAWTEventListener(this);
								                    }
								                }
								                if(event instanceof WindowEvent)
								                {
								                    WindowEvent we = (WindowEvent)event;
								                    if(we.getID() == WindowEvent.WINDOW_DEACTIVATED || we.getID() == WindowEvent.WINDOW_STATE_CHANGED)
								                    {
								                        popup.setVisible(false);
								                        Toolkit.getDefaultToolkit().removeAWTEventListener(this);
								                    }
								                }
								            }

								        }, AWTEvent.MOUSE_EVENT_MASK | AWTEvent.WINDOW_EVENT_MASK);

								    }
								    popup.setLocation(evt.getXOnScreen(), evt.getYOnScreen());
									popup.setVisible(true);
								}
							}
						});
					}
				}
			}
		}

	}

}