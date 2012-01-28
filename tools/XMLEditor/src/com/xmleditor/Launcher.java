package com.xmleditor;

import java.io.File;
import java.net.MalformedURLException;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import skillsplanner.utils.FileUtils;
import skillsplanner.utils.jdom.Handler;

import com.xmleditor.gui.XMLE;

public class Launcher {

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
		JFileChooser fc = new JFileChooser();
		fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		JOptionPane.showMessageDialog(null, "Please select location of the libs directory");
		int ret = fc.showOpenDialog(null);
		
		if(ret == fc.APPROVE_OPTION){
			File f = fc.getSelectedFile();
			System.out.println("Setting libs path to "+f.getAbsolutePath());
			try {
				Handler.setLibsLocation(f.toPath().toString());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.exit(0);
			}
		}
		
	}
}
