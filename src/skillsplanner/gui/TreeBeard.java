package skillsplanner.gui;

import java.io.IOException;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

import skillsplanner.Launcher;
import skillsplanner.utils.FileUtils;
import skillsplanner.utils.StringUtils;
import skillsplanner.utils.jdom.Handler;

/**
 * Listener for any event sent from the JTRee that holds the class hierarchy.
 * @author ryzngard
 *
 */
public class TreeBeard implements TreeSelectionListener {

	@Override
	public void valueChanged(TreeSelectionEvent arg0) {
		
		TreePath selection = arg0.getPath();
		
		String path = selectionToPath(selection);
		
		//System.out.println(path);
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
				
				//Change the DFOCharacter object to reflect change in class
				try {
					System.out.println("Setting character class to "+path);
					Launcher.getCharacter().setDFOClass(
							Handler.getClassLoader().getClass(FileUtils.getDFOClass(path))
							);
					if(Launcher.getCharacter().getDFOClass() == null){
						System.out.println("FUCK OFF");
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					JOptionPane.showMessageDialog(null, e.getMessage());
				}
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
