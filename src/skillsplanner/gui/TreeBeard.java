package skillsplanner.gui;

import java.util.List;

import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

import skillsplanner.Launcher;
import skillsplanner.utils.FileUtils;
import skillsplanner.utils.StringUtils;

public class TreeBeard implements TreeSelectionListener {

	@Override
	public void valueChanged(TreeSelectionEvent arg0) {
		
		TreePath selection = arg0.getPath();
		
		String path = selectionToPath(selection);
		
		if(FileUtils.isClassFile(path)){
			if(Launcher.getWisp()==null){
				System.out.println("FUCKK FUCK FUCK FUCK FUCK FUCK FUCK FUCK FUCK");
			}
			else{
				List<String> subclasses = FileUtils.getSubclasses(path);
				
				Launcher.getWisp().Tab1.setText(StringUtils.toCamelCase((subclasses.get(0) == null) ? "" : subclasses.get(0)));
				Launcher.getWisp().Tab2.setText(StringUtils.toCamelCase((subclasses.get(1) == null) ? "" : subclasses.get(1)));
				
				
				/**
				 * 3 and 4 may not exist for some classes
				 */
				try{
					Launcher.getWisp().Tab3.setText(StringUtils.toCamelCase((subclasses.get(2) == null) ? "" : subclasses.get(2)));
				}
				catch(Exception e){
					Launcher.getWisp().Tab3.setText("");
				}
				
				try{
					Launcher.getWisp().Tab4.setText(StringUtils.toCamelCase((subclasses.get(3) == null) ? "" : subclasses.get(3)));
				}
				catch(Exception e){
					Launcher.getWisp().Tab4.setText("");
				}
				
				Launcher.getWisp().General.setText("General");
				
				//Launcher.getWisp().repaint();
				
			}
		}
		

	}
	
	private String selectionToPath(TreePath selection){
		Object[] path = selection.getPath();
		if(path == null){
			return "";
		}
		
		String ret = "";
		
		//skip the first element, since it is Root
		for(int i = 1; i < path.length; i++){
			
			ret += pathify(path[i].toString()) + "/";
		}
		return ret.substring(0,ret.length()-1);
	}
	
	private String pathify(String input){
		return input.replaceAll(" ", "_").toLowerCase();
	}

}
