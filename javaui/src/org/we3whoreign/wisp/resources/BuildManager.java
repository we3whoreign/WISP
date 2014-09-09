package org.we3whoreign.wisp.resources;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;

import org.we3whoreign.wisp.beans.DFOClass;
import org.we3whoreign.wisp.utils.jdom.BuildMapper;

/**
 * The build manager handles all the saving and loading of all build files.
 * It saves or loads an instance of DFOCharacter into xml.
 * @author ryzngard
 *
 */
public class BuildManager {
	/**
	 * Saves a specified build from the dfoclass passed in to xml file.
	 * Prompts the user for where to save.
	 * @param dfoclass
	 * @return 
	 */
	public static boolean saveToXML(DFOClass dfoclass){
		//start with a file chooser that is in the users home directory
		JFileChooser fc = new JFileChooser(System.getProperty("user.home"));
		fc.setFileFilter(BuildManager.filter);
		int ret = fc.showOpenDialog(null);
		
		//see if accept was chosen
		if(ret == JFileChooser.APPROVE_OPTION){
			//save the build to the chosen file
			return BuildMapper.createFileFromDFOClass(fc.getSelectedFile(), dfoclass);
		}
		else{
			return false;
		}
	}
	
	/**
	 * Opens a build file and loads it.
	 */
	public static void openBuild() {
		//start with a file chooser that is in the users home directory
		JFileChooser fc = new JFileChooser(System.getProperty("user.home"));
		fc.setFileFilter(BuildManager.filter);
		int ret = fc.showOpenDialog(null);
		
		//see if accept was chosen
		if(ret == JFileChooser.APPROVE_OPTION){
			//save the build to the chosen file
			if(!BuildMapper.openFile(fc.getSelectedFile())){
				JOptionPane.showMessageDialog(StaticResources.getWisp(), "Could not load file");
			}
		}
		
		//correct for the lack of on-selection events changing the subclasses for skills
		StaticResources.getWisp().wipeSkills();
		
		StaticResources.getWisp().updateSkills();
		
	}
	
	/**
	 * Filter to show only directories and xml files
	 */
	private static FileFilter filter = new FileFilter(){

		@Override
		public boolean accept(File arg0) {
			if(arg0.isDirectory()){
				return true;
			}
			else if(arg0.getName().toLowerCase().endsWith(".xml")){
				return true;
			}
			else{
				return false;
			}
		}

		@Override
		public String getDescription() {
			return "xml and directories";
		}
		
	};
}
