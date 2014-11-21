package fr.miage.application;

import chargementDynamique.Repository;
import interfaces.InterfacePlugin;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;


public class Application extends JFrame {

    private JPanel global,gauche,droit;
    private JScrollPane scrolldroit,scrollgauche;
    private JMenuBar menu;
    private JMenu fichier,plugins;
    private JMenuItem save,load,quitter;
    private List<Class<?>> l;
    private List<InterfacePlugin> listePlugins;
    private File distinationFile;
    private Repertory repertoire;
    private GenereTree arbre;
    private Dimension d;
    
    public Application() throws InstantiationException, IllegalAccessException
    {
        d = Toolkit.getDefaultToolkit().getScreenSize();
        distinationFile = new File("../Plugins/build/classes");
        listePlugins = new ArrayList<InterfacePlugin>();
        
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setBounds(0,0,d.width,d.height-40);
        
        menu = new JMenuBar();
        fichier = new JMenu("fichier");
            save = new JMenuItem("save");
            save.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                arbre.saveTree();
            }
        });
            load = new JMenuItem("importer");
            load.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if(arbre.getSavinFile().exists())
                {
                    arbre = arbre.loadTree();
                }
                else 
                    JOptionPane.showMessageDialog(null, "il n'ya pas de fichier de sauvegarde !!");
                scrollgauche.getViewport().remove(arbre);
                scrollgauche.getViewport().add(arbre);
                scrollgauche.getViewport().validate();
            }
        });
            quitter = new JMenuItem("Quitter");
        fichier.add(save);
        fichier.add(load);
        fichier.add(quitter);
        
        plugins = new JMenu("plugins");
        
        Repository<Object> rep = new Repository<Object>(distinationFile,Object.class);
        l = rep.load();
         for(Class<?> c :l)
        {
            Class<?>[] interfaces = c.getInterfaces();
            for(Class<?> uneinterface :interfaces)
                    {
                        c.getName().equals("interfeces.InterfacePlugin");
                        InterfacePlugin plugin = (InterfacePlugin) c.newInstance();
                        listePlugins.add(plugin);
                        buildMenu(plugin);
                    }
            
        }
        
         menu.add(fichier);
         menu.add(plugins);
        this.setJMenuBar(menu);
        
        global = new JPanel();
        global.setLayout(new BorderLayout());
        
        repertoire = new Repertory();
        
        // faut mettre la classe qui genere le JTree
        // exemple tree = new ClasseGenereTree();
        arbre = new GenereTree();
        
        /*DefaultMutableTreeNode node = new DefaultMutableTreeNode(new File("C:/"));
        DefaultTreeModel model = new DefaultTreeModel(node);*/
        //tree.setModel(model);
        ///arbre.setModel(model);
        
        addListenerToTree(arbre,repertoire);
        droit = repertoire.createRepertory(new File("/"), listePlugins);
        scrolldroit = new JScrollPane(droit);
        global.add(scrolldroit, BorderLayout.CENTER);
       
        //le panel pour la liste des repertoires de gauche 
        scrollgauche = new JScrollPane();
        scrollgauche.getViewport().setPreferredSize(new Dimension(200, 600));
        scrollgauche.getViewport().add(arbre);
        global.add(scrollgauche,BorderLayout.WEST);
        this.setContentPane(global);
    }
    
    private void buildMenu(final InterfacePlugin c)
    {
        
        JMenuItem item = new JMenuItem(c.getName());
        item.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, c.getDetail());
                
            }
        });
        plugins.add(item);
    }
    
    
    public void addListenerToTree(final JTree arb, Repertory r)
    {
        arb.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
                droit = repertoire.createRepertory((File)((DefaultMutableTreeNode)arb.getLastSelectedPathComponent()).getUserObject(), listePlugins);
            }

            @Override
            public void mousePressed(MouseEvent e) {
                
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                
            }

            @Override
            public void mouseExited(MouseEvent e) {
                
            }
        });
    }
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        try
        {
            new Application().setVisible(true);
        }
        catch(IllegalAccessException e){e.printStackTrace();}
        catch(InstantiationException e){e.printStackTrace();}
    }
}
