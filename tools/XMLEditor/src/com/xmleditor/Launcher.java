 package com.xmleditor;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import skillsplanner.io.IOHandler;

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
		File f = new File(LIBS_PATH);
		if(f.exists()){
			IOHandler.setClassDir(f.getAbsolutePath().replaceAll("\\\\", "/")+"/classes");
			IOHandler.setSkillsDir(f.getAbsolutePath().replaceAll("\\\\", "/")+"/skills");
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
		}
		
	}
}
