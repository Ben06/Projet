/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.plugins.jpg;
import interfaces.InterfacePlugin;
import java.awt.Dimension;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class PluginJpg implements InterfacePlugin{

    @Override
    public String getName() {
        return "Plugin jpg";
    }

    @Override
    public String getExtension()
    {
        return "jpg";
    }
    
    @Override
    public JFrame getTraitement(File file)
    {
        JFrame f = new JFrame(file.getAbsolutePath());
        f.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        JLabel label = new JLabel(new ImageIcon(file.getAbsolutePath()));
        //f.setContentPane(new JLabel("lancement du fichier "+f.getName()));
        
        f.setBounds(0,0,200,200);
        JScrollPane pane = new JScrollPane(label);
        pane.setVerticalScrollBarPolicy(
        JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        pane.setPreferredSize(new Dimension(250, 145));
        pane.setMinimumSize(new Dimension(10, 10));
        f.setContentPane(pane);
        f.setVisible(true);       
        return f;
    }

    @Override
    public JPanel getDetail() {
        JPanel p = new JPanel();
        p.add(new JLabel("Plugin : "+this.getName()));
        p.add(new JLabel("Extension : "+this.getExtension()));
        return p;
    }
    
}
