package interfaces;

import java.io.*;
import javax.swing.JFrame;
import javax.swing.JPanel;

public interface InterfacePlugin {

public String getName();
public String getExtension();
public JFrame getTraitement(File f);
public JPanel getDetail();
}