package org.we3whoreign.wisp.gui;

import java.io.IOException;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

import org.we3whoreign.wisp.resources.ClassManager;
import org.we3whoreign.wisp.resources.StaticResources;
import org.we3whoreign.wisp.utils.StringUtils;
import org.we3whoreign.wisp.utils.jdom.Handler;

/**
 * Listener for any event sent from the JTRee that holds the class hierarchy.
 * @author ryzngard
 *
 */
public class TreeBeard implements TreeSelectionListener {
	private TreePath oldSelection;

	@Override
	public void valueChanged(TreeSelectionEvent arg0) {
		TreePath selection = arg0.getPath();
		
		//Check if the path hasn't changed
		if(selection == oldSelection){
			return;
		}
		
		String path = StringUtils.toFileName(selectionToPath(selection));
		
		if(ClassManager.getClassByName(path) != null){
			if(StaticResources.getWisp()==null){
				System.out.println("FUCKK FUCK FUCK FUCK FUCK FUCK FUCK FUCK FUCK");
			}
			else{
				
				//Change the DFOCharacter object to reflect change in class
				if(StaticResources.getCharacter().resetOK()){
					StaticResources.getCharacter().setDFOClass(
							ClassManager.getInstance().getDFOClass(path));
					
					//reflect changes in GUI
					StaticResources.getWisp().updateSkills();
					
					//Reset characters SP and update 
					StaticResources.getCharacter().resetSp();
					StaticResources.getWisp().updateRemainingSP(StaticResources.getCharacter().getRemainingSP());
					
					oldSelection = selection;
					if(StaticResources.getCharacter().getDFOClass() == null){
						System.out.println("FUCK OFF");
					}
				}
				else{
					JTree jtree = (JTree)arg0.getSource();
					jtree.setSelectionPath(oldSelection);
				}
			}
		}
		
		//StaticResources.getWisp().revalidate();
		StaticResources.getWisp().wipeSkills();
		StaticResources.getWisp().repaint();
		

	}
	
	private String selectionToPath(TreePath selection){
		Object[] path = selection.getPath();
		if(path == null || path.length <= 0){
			return "";
		}
		
		String ret = path[path.length-1].toString();
		
		//skip the first element, since it is Root
		//for(int i = 1; i < path.length; i++){
		//	
		//	ret += pathify(path[i].toString()) + "/";
		//}
		//return ret.substring(0,ret.length()-1);
		
		return ret;
	}
	

}
