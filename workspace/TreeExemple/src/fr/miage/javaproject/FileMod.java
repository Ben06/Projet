package fr.miage.javaproject;

import java.util.Arrays;

import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

/*Une classe pour définir un TreeModel*/

public class FileMod implements TreeModel {

	private Nodes root;

	public FileMod(String directory){
		root = new Nodes(directory);

	}

	public Object getRoot(){
		return root;

	}

	public Object getChild (Object parent , int index){
		Nodes parentNodes = (Nodes)	parent;
		return new Nodes(parentNodes,parentNodes.listFiles()[index].getName());

	}

	public int getChildCount(Object parent){
		Nodes parentNodes = (Nodes)	parent;	
		if(parent == null || !parentNodes.isDirectory() || parentNodes.listFiles()==null){
			return 0;
		}

		return parentNodes.listFiles().length;

	}

	public boolean isLeaf(Object node){
		return (getChildCount(node)==0);
	}




	public void valueForPathChanged(TreePath path, Object newValue) {
	}

	public void addTreeModelListener(TreeModelListener l) {


	}

	public void removeTreeModelListener(TreeModelListener l) {
	}



	@Override
	public int getIndexOfChild(Object parent, Object child) {
		Nodes parentNodes =(Nodes) parent;
		Nodes childNodes = (Nodes) child;
		return Arrays.asList(parentNodes.list()).indexOf(childNodes.getName());

	}

}
