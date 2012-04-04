package skillsplanner.resources;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;

import skillsplanner.beans.DFOClass;
import skillsplanner.utils.jdom.BuildMapper;

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
