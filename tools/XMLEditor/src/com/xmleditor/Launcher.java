package com.xmleditor;

import com.xmleditor.gui.XMLE;

public class Launcher {

	public static void main(String args[]){
		javax.swing.SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				new XMLE();
			}
		});
	}
}
