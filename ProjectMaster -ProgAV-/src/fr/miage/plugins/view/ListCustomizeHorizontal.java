package fr.miage.plugins.view;
import java.awt.Color;
import java.awt.Component;

import java.awt.Font;

import java.io.File;

import java.io.IOException;

import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;

import javax.swing.JScrollPane;

import javax.swing.JViewport;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileSystemView;



import fr.miage.Model.Model;





public class ListCustomizeHorizontal implements IPluginView
{

	Font myFont = new Font("Tahoma", Font.PLAIN, 12);


	@Override
	public void changerTaille(JFrame f)
	{
		
		f.setTitle("ExploGin -Plugin de vue ListCustomizeHorizontal-");
	}


	@Override
	public void changerCouleur(JFrame f)
	{
		f.getContentPane().setBackground(Color.decode("#ecf0f1"));
		f.revalidate();
		f.repaint();
	}


	@Override
	public void changerFormeBoutons(JFrame f)
	{
		
		Component[] components = f.getContentPane().getComponents();

		for(int i=0; i<components.length; i++){ 

			System.out.println("ListCustomize.changerFormeBoutons() "+components[i].getName());


			if(components[i].getName().equals("btnHome")){

				
				
				
				((JButton)components[i]).setText("");
				((JButton)components[i]).setIcon(new ImageIcon("Img/home.png"));

				components[i].setBackground(Color.decode("#c0392b"));
				components[i].setForeground(Color.decode("#ecf0f1"));
				((JComponent) components[i]).setBorder( new LineBorder(Color.decode("#c0392b")));
				((JComponent) components[i]).setToolTipText("Accueil");
				components[i].setFont(myFont);

			}

			if(components[i].getName().equals("newFile")){
	
				
				((JButton)components[i]).setText("");
				((JButton)components[i]).setIcon(new ImageIcon("Img/directory.png"));
				
				components[i].setBackground(Color.decode("#c0392b"));
				components[i].setForeground(Color.decode("#ecf0f1"));
				((JComponent) components[i]).setBorder( new LineBorder(Color.decode("#c0392b")));
				((JComponent) components[i]).setToolTipText("Accueil");
				components[i].setFont(myFont);
			}

			if(components[i].getName().equals("btnRemonter")){
				
				//img = ImageIO.read(new URL("http://icons.iconarchive.com/icons/fatcow/farm-fresh/16/bullet-arrow-up-icon.png"));
				((JButton)components[i]).setText("");
				((JButton)components[i]).setIcon(new ImageIcon("Img/buttonarrowup.png"));
				
				components[i].setBackground(Color.decode("#c0392b"));
				components[i].setForeground(Color.decode("#ecf0f1"));
				((JComponent) components[i]).setBorder( new LineBorder(Color.decode("#c0392b")));
				((JComponent) components[i]).setToolTipText("Accueil");
				components[i].setFont(myFont);

			}

			if(components[i].getName().equals("btnDelete")){
				
				
				
				((JButton)components[i]).setText("");
				((JButton)components[i]).setIcon(new ImageIcon("Img/delete.png"));
					
				components[i].setBackground(Color.decode("#c0392b"));
				components[i].setForeground(Color.decode("#ecf0f1"));
				((JComponent) components[i]).setBorder( new LineBorder(Color.decode("#c0392b")));
				((JComponent) components[i]).setToolTipText("Accueil");
				components[i].setFont(myFont);

			}

			if(components[i].getName().equals("btnAddPlugin")){
	
				
				
				((JButton)components[i]).setText("");
				((JButton)components[i]).setIcon(new ImageIcon("Img/add.png"));

				components[i].setBackground(Color.decode("#c0392b"));
				components[i].setForeground(Color.decode("#ecf0f1"));
				((JComponent) components[i]).setBorder( new LineBorder(Color.decode("#c0392b")));
				((JComponent) components[i]).setToolTipText("Accueil");
				components[i].setFont(myFont);

			}

			if(components[i].getName().equals("btnAppliquerPlugins")){

	
				
				((JButton)components[i]).setText("");
				((JButton)components[i]).setIcon(new ImageIcon("Img/tick.png"));
				components[i].setBackground(Color.decode("#c0392b"));
				components[i].setForeground(Color.decode("#ecf0f1"));
				((JComponent) components[i]).setBorder( new LineBorder(Color.decode("#c0392b")));
				((JComponent) components[i]).setToolTipText("Accueil");
				components[i].setFont(myFont);

			}

			if(components[i].getName().equals("btnSauvegarder")){
	
					
				
				((JButton)components[i]).setText("");
				((JButton)components[i]).setIcon(new ImageIcon("Img/save.png"));
				components[i].setBackground(Color.decode("#c0392b"));
				components[i].setForeground(Color.decode("#ecf0f1"));
				((JComponent) components[i]).setBorder( new LineBorder(Color.decode("#c0392b")));
				((JComponent) components[i]).setToolTipText("Accueil");
				components[i].setFont(myFont);

			}
			
			if(components[i].getName().equals("reset")){

				
				
				((JButton)components[i]).setText("");
				((JButton)components[i]).setIcon(new ImageIcon("Img/reset.png"));
				
				components[i].setBackground(Color.decode("#c0392b"));
				components[i].setForeground(Color.decode("#ecf0f1"));
				((JComponent) components[i]).setBorder( new LineBorder(Color.decode("#c0392b")));
				((JComponent) components[i]).setToolTipText("Accueil");
				components[i].setFont(myFont);

			}


			


			if(components[i].getName().equals("lblPluginsDeVue")){

				
				((JLabel)components[i]).setText("");
				((JLabel)components[i]).setBounds(70, 309, 151, 14);
				((JLabel)components[i]).setIcon(new ImageIcon("Img/vue.png"));
			
				components[i].setForeground(Color.decode("#c0392b"));
				/*components[i].setFont(myFont);*/

			}

			if(components[i].getName().equals("lblPluginsDanalyse")){

				
				
				((JLabel)components[i]).setText("");
				((JLabel)components[i]).setBounds(70, 349, 121, 14);
				((JLabel)components[i]).setIcon(new ImageIcon("Img/file.png"));
				components[i].setForeground(Color.decode("#c0392b"));
				components[i].setFont(myFont);

			}


			if(components[i].getName().equals("analysisPlugins")){
				components[i].setBackground(Color.decode("#c0392b"));
				components[i].setForeground(Color.decode("#ecf0f1"));
				((JComponent) components[i]).setBorder( new LineBorder(Color.decode("#c0392b")));
				components[i].setFont(myFont);

			}

			if(components[i].getName().equals("viewPlugins")){
				components[i].setBackground(Color.decode("#c0392b"));
				components[i].setForeground(Color.decode("#ecf0f1"));
				((JComponent) components[i]).setBorder( new LineBorder(Color.decode("#c0392b")));
				components[i].setFont(myFont);	
			}




		}



	}




	


	@Override
	public void ajouterElement(JFrame f)
	{
	}


	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void customList(JFrame f)
	{
		Component[] contentComponent = f.getContentPane().getComponents();
		for (int i = 0; i < contentComponent.length; i++)
		{
			if (contentComponent[i].getName().equals("scrollPane"))
			{
				// System.out.println("TestPluginView.customList() "+contentComponent[i].getName());
				JScrollPane scroll = (JScrollPane) contentComponent[i];
				JViewport view = scroll.getViewport();
				Component[] viewComponent = view.getComponents();
				for (int j = 0; j < viewComponent.length; j++)
				{
					// System.out.println("TestPluginView.customList() dans le scrollPane "+viewComponent[]);
					if (viewComponent[j].getName().equals("list"))
					{

						class FileRenderer extends DefaultListCellRenderer
						{

							private boolean pad;
							private Border padBorder = new EmptyBorder(3, 3, 3, 3);


							FileRenderer(boolean pad)
							{
								this.pad = pad;
							}


							@Override
							public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected,
									boolean cellHasFocus)
							{

								Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
								JLabel l = (JLabel) c;
								// System.out.println("TestPluginView.customList(...).FileRenderer.getListCellRendererComponent() value : "+value);
								Model.getRepCourant();
								File f = null;
								try
								{
									if (Model.isWindows())
										f = new File(Model.getRepCourant().getCanonicalPath() + "\\" + value);
									else
										f = new File(Model.getRepCourant().getCanonicalPath() + "/" + value);
								} catch (IOException e)
								{
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								l.setText(f.getName());
								l.setIcon(FileSystemView.getFileSystemView().getSystemIcon(f));
								if (pad)
								{
									l.setBorder(padBorder);
								}

								return l;
							}
						}


						((JList) viewComponent[j]).setCellRenderer(new FileRenderer(true));
				        ((JList) viewComponent[j]).setLayoutOrientation(javax.swing.JList.HORIZONTAL_WRAP);
				        ((JList) viewComponent[j]).setVisibleRowCount(-1);

					}
				}
			}
		}
	}

}
