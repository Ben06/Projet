/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.application;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.event.TreeExpansionEvent;
import javax.swing.event.TreeExpansionListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

//public class JTreeArbre extends JTree {
    public class GenereTree extends JTree{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	DefaultMutableTreeNode noeud;
	File savingFile;
	
	public GenereTree()
	{
				
		noeud = new DefaultMutableTreeNode(new File("Poste de travail"));
		savingFile = new File("tree.mem");
		
		// recupere tous les drives du systeme
		for (int i=0 ; i<File.listRoots().length ; i++) {
			noeud.add(new DefaultMutableTreeNode((File.listRoots()[i])));			
			creerFils((DefaultMutableTreeNode)noeud.getChildAt(i));
		}
		
		
		addListenerToTree(this);
		
		

		this.setModel(new DefaultTreeModel(noeud));		
	}
	
	
	
	public DefaultMutableTreeNode getNoeud() {
		return noeud;
	}


        public void addListenerToTree(GenereTree tree)
        {
            // si l'on click sur un noeud creation de ses fils
		tree.addTreeSelectionListener(new TreeSelectionListener() {
			
			@Override
			public void valueChanged(TreeSelectionEvent e) {
				//System.out.println(( (DefaultMutableTreeNode) getLastSelectedPathComponent()).getFile().getName());
				if((( (DefaultMutableTreeNode) e.getPath().getLastPathComponent()).getUserObject().toString()).equals("Poste de travail"));
				{
					( (DefaultMutableTreeNode) e.getPath().getLastPathComponent()).removeAllChildren();
					creerFils((DefaultMutableTreeNode)e.getPath().getLastPathComponent());
					( (DefaultTreeModel) getModel()).reload(( (DefaultMutableTreeNode) e.getPath().getLastPathComponent()));
					TreePath pt = new TreePath(e.getPath());
					System.out.println(pt);
				}
				
			}
		});
		//***********************************************************
            // si on developpe un noeud creation de ses fils
		tree.addTreeExpansionListener(new TreeExpansionListener() {
			
			@Override
			public void treeExpanded(TreeExpansionEvent e) {
				if(!( (DefaultMutableTreeNode) e.getPath().getLastPathComponent()).getUserObject().equals(new File("Poste de travail")));
				{
					( (DefaultMutableTreeNode) e.getPath().getLastPathComponent()).removeAllChildren();
					creerFils((DefaultMutableTreeNode)e.getPath().getLastPathComponent());
					( (DefaultTreeModel) getModel()).reload(( (DefaultMutableTreeNode) e.getPath().getLastPathComponent()));
				}
			}
			
			@Override
			public void treeCollapsed(TreeExpansionEvent event) {
				// TODO Auto-generated method stub
				
			}
		});
		//***********************************************************
        }

	public void creerFils(DefaultMutableTreeNode n)
	{
		
		if( ((File)n.getUserObject()).isDirectory() )
		{
			for (File file : ((File)n.getUserObject()).listFiles()) {
				if(file.isDirectory())
				{
					DefaultMutableTreeNode nouv = new DefaultMutableTreeNode(file);
					nouv.add(new DefaultMutableTreeNode());
					n.add(nouv);
				}	
			}
		
		}
	}
	
	
	public void expandSingleNode(JTree tree, TreeModel data, TreePath path) {

		DefaultMutableTreeNode node = (DefaultMutableTreeNode) path.getLastPathComponent();
		int count = data.getChildCount(node);
			if (count == 0) {
			if (data.isLeaf(node))
				tree.expandPath(path.getParentPath());
			else
				tree.expandPath(path);
		} 
		else
			for (int i = 0; i < count; i++)
				expandSingleNode(tree,data,	path.pathByAddingChild(data.getChild(node, i)));
		}
	
	
	/**
	 * Returns a TreePath containing the specified node.
	 * @param node
	 * @return
	 */
	public static TreePath getPath(DefaultMutableTreeNode node) {
	    List<DefaultMutableTreeNode> list = new ArrayList<DefaultMutableTreeNode>();
	    boolean test = true;
	    if(((File)node.getUserObject()).getName().equals("Poste de travail")) test = false;
	    
	    // Add all nodes to list
	    while (node != null) {
	        list.add(node);
	        node = (DefaultMutableTreeNode)node.getParent();
	    }
	    if(test)
	    list.add(new DefaultMutableTreeNode(new File("Poste de travail")));
	    Collections.reverse(list);

	    // Convert array of nodes to TreePath
	    return new TreePath(list.toArray());
	}
	
	public void saveTree()
        {
            try
            {
                FileOutputStream fos = new FileOutputStream(savingFile.getAbsolutePath());
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                this.removeAll();
                oos.writeObject(this);
                oos.flush();
                oos.close();
                fos.close();
            }
            catch(Exception e) 
            {
                e.printStackTrace();
            }
        }
	
        public GenereTree loadTree()
        {
            GenereTree tree=null;
            try
            {
                FileInputStream fin = new FileInputStream(savingFile);
                ObjectInputStream ois = new ObjectInputStream(fin);
                tree = (GenereTree) ois.readObject();
                addListenerToTree(tree);
                return tree;
                
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
            return tree;
        }
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		 JFrame       frame   = new JFrame();
         GenereTree  arbre   = new GenereTree();
         File f  = new File("D:\\");
         
         DefaultMutableTreeNode nf = new DefaultMutableTreeNode(f);
         nf.setParent((DefaultMutableTreeNode)arbre.getModel().getRoot());
         for (int i=0 ; i<File.listRoots().length ; i++) {
 			nf.add(new DefaultMutableTreeNode((File.listRoots()[i])));			
 			//creerFils((DefaultMutableTreeNode)noeud.getChildAt(i));
 		}
        
         
         
         TreePath path = new TreePath(new Object[]{arbre.getModel().getRoot(),nf});
         
         
         System.out.println(path.equals(arbre.getPathForRow(2)));
         
         System.out.println(nf);
        JScrollPane  scroll  = new JScrollPane(arbre);
        frame.getContentPane().add(scroll);
        frame.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        frame.setBounds(0, 0, 800, 400);
        frame.setVisible(true);
	}
        
        
        public File getSavinFile()
        {
            return this.savingFile;
        }

}
