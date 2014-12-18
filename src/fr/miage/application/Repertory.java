package fr.miage.application;

import interfaces.InterfacePlugin;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Repertory{

   private JPanel repertoire;
   private File lastDir;
   
   public Repertory()
   {
       repertoire = new JPanel();
       repertoire.setPreferredSize(new Dimension(600, 3000));
   }
   
   public JPanel createRepertory(File f, List<InterfacePlugin> l)
   {
       repertoire.removeAll();
           for(File fich :f.listFiles())
           {
               if(fich.isDirectory())
                   afficherDossier(fich, l);
               else
                   afficherFichier(fich, l);
               
           }
       repertoire.setVisible(false);
       
       repertoire.validate();
       repertoire.setVisible(true);
       return repertoire;
   }
   
   
   public void afficherDossier(final File f, final List<InterfacePlugin> l)
   {
       lastDir = f;
       JButton b = new JButton(f.getName());
       b.setPreferredSize(new Dimension(100, 20));
       b.setIcon(javax.swing.filechooser.FileSystemView.getFileSystemView().getSystemIcon(f));
       b.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                repertoire.removeAll();
                createRepertory(f, l);
            }
        });
       repertoire.add(b);
   }
   
   public void afficherFichier(final File f, final List<InterfacePlugin> l)
   {
       JButton b = new JButton(f.getName());
       b.setPreferredSize(new Dimension(100, 20));
       b.setIcon(javax.swing.filechooser.FileSystemView.getFileSystemView().getSystemIcon(f));
       b.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                boolean test=false;
                for(InterfacePlugin plugin :l)
                {
                    if(plugin.getExtension().equals(getFileExtension(f)))
                    {
                        runFile(f,plugin);
                        test = true;
                    }
                }
                if(!test)
                    JOptionPane.showMessageDialog(null, "cette extension n'est pas prise en charge");
            }
        });
       repertoire.add(b);
   }
   
   public void runFile(File f, InterfacePlugin plugin)
   {
     plugin.getTraitement(f);  
   }
   
   public String getFileExtension(File f)
   {
       int index = f.getName().lastIndexOf(".");
       String extension = f.getName().substring(index+1, f.getName().length());
       return extension;
   }
   
   
   public File getDernierDossier()
   {
       return lastDir;
   }
   
}
