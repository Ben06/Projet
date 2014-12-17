/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.plugins.htm;
import interfaces.InterfacePlugin;

import java.awt.Dimension;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;


public class PluginHtm implements InterfacePlugin{

	@Override
	public String getName() {
		return "Plugin htm";
	}

	@Override
	public String getExtension()
	{
		return "htm";
	}

	@SuppressWarnings("unused")
	@Override
	public JFrame getTraitement(File file)
	{
		JFrame f = new JFrame(file.getAbsolutePath());
		f.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		try {
			JEditorPane pane = new JEditorPane();
			JEditorPane editorPane = new JEditorPane();
			editorPane.setEditable(false);

			java.net.URL helpURL = new URL("file:///"+file.getAbsolutePath());//Plugin2.class.getResource(file.getAbsolutePath());
			if (helpURL != null) {
				try {
					editorPane.setPage(helpURL);
				} catch (IOException e) {
					System.err.println("Attempted to read a bad URL: " + helpURL);
				}
			} else {
				System.err.println("Couldn't find file: TextSamplerDemoHelp.html");
			}

			//Put the editor pane in a scroll pane.
			JScrollPane editorScrollPane = new JScrollPane(editorPane);
			editorScrollPane.setVerticalScrollBarPolicy(
					JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
			editorScrollPane.setPreferredSize(new Dimension(250, 145));
			editorScrollPane.setMinimumSize(new Dimension(10, 10));
			f.setContentPane(new JScrollPane(editorPane));
		}
		catch (IOException ex) {
			Logger.getLogger(PluginHtm.class.getName()).log(Level.SEVERE, null, ex);
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
