import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class Couleurs extends JFrame implements ActionListener {
	
	JPanel panneau = new JPanel();
	 public Couleurs() {
		JButton bouton = new JButton("choix de la couleur");
		
		panneau.setPreferredSize(new Dimension(200, 200));
		add(bouton, BorderLayout.NORTH);
		add(panneau, BorderLayout.SOUTH);
		bouton.addActionListener(this);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocation(100, 100);
		pack();
		setVisible(true);
	 }
	  public void actionPerformed(ActionEvent evt) {
		Color couleur = JColorChooser.showDialog
	(null, "couleur du fond", Color.WHITE);
	panneau.setBackground(couleur);
	}
	}
	
class EssaiCouleurs {
	 public static void main(String[] arg) {
		new Couleurs();
	 }
	}


