/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.plugins.txt;

import interfaces.InterfacePlugin;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.JLabel;


public class PluginTxt implements InterfacePlugin {
    
    @Override
    public String getName()
    {
        return "Plugin txt";
    }
    
    
    @Override
    public String getExtension()
    {
        return "txt";
    }
    
    @Override
    public JFrame getTraitement(File file)
    {
        JFrame f = new JFrame(file.getAbsolutePath());
        f.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String text = new String();
            String ligne= new String();
            while((ligne = br.readLine())!= null) {
              text += ligne + "\n";
            }
            br.close();
            JScrollPane scrollpane = new JScrollPane(new JTextArea(text));
            f.setContentPane(scrollpane);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(PluginTxt.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch(IOException e) {
            Logger.getLogger(PluginTxt.class.getName()).log(Level.SEVERE, null, e);
        }
        
        
        f.setBounds(0,0,200,200);
                
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
