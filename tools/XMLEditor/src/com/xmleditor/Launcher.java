 package com.xmleditor;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import org.we3whoreign.wisp.io.IOHandler;
import org.we3whoreign.wisp.resources.ClassManager;
import org.we3whoreign.wisp.resources.SkillManager;

import com.xmleditor.gui.XMLE;

public class Launcher {
	
	private final String LIBS_PATH = "../../libs";

	public static void main(String args[]){
		Launcher launcher = new Launcher();
		
	}
	
	public Launcher(){
		
		javax.swing.SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				chooseLibsDir();
				new XMLE();
			}
		});
		
	}
	
	private void chooseLibsDir(){
		//make sure to never consider the library as in a jar
		IOHandler.JAROVERRIDE = false;
		File f = new File(LIBS_PATH);
		if(f.exists()){
			IOHandler.setClassDir(f.getAbsolutePath().replaceAll("\\\\", "/")+"/classes");
			IOHandler.setSkillsDir(f.getAbsolutePath().replaceAll("\\\\", "/")+"/skills");
			
			SkillManager.getInstance().getSkill("Something that doesn't exist");
			ClassManager.getInstance().getClassByName("Something that doesn't exist");
			return;
		}
		JFileChooser fc = new JFileChooser(".");
		fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		JOptionPane.showMessageDialog(null, "Please select location of the libs directory");
		int ret = fc.showOpenDialog(null);
		
		if(ret == fc.APPROVE_OPTION){
			f = fc.getSelectedFile();
			IOHandler.setClassDir(f.getAbsolutePath().replaceAll("\\\\", "/")+"/classes");
			IOHandler.setSkillsDir(f.getAbsolutePath().replaceAll("\\\\", "/")+"/skills");
			
			//Make sure the threads finish beforel oading
			SkillManager.getInstance().getSkill("Something that doesn't exist");
			ClassManager.getInstance().getClassByName("Something that doesn't exist");
		}
		
	}
}
