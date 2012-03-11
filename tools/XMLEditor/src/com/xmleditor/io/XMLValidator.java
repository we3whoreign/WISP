package com.xmleditor.io;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import skillsplanner.utils.jdom.Handler;

public class XMLValidator {
	private static String LIBS_PATH = "../../libs";

	
	public static void main(String args[]){
		File f = new File(LIBS_PATH);
		if(f.exists()){
			
		}
		else{
			JFileChooser fc = new JFileChooser(".");
			fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			JOptionPane.showMessageDialog(null, "Please select location of the libs directory");
			int ret = fc.showOpenDialog(null);
			
			if(ret == fc.APPROVE_OPTION){
				f = fc.getSelectedFile();
			}
		}
		
		System.out.println("Validating all xml files in : "+f.getAbsolutePath());
		IterateThrough(f);
		System.out.println("Done validating, errors will be shown if anything went wrong.");
		
	}
	
	public static void IterateThrough(File f){
		if(f.isDirectory()){
			for(File file : f.listFiles()){
				IterateThrough(file);
			}
		}
		else if(f.getName().toLowerCase().endsWith(".xml")){
			Handler.openXMLFile(f);
		}
	
	}

}
