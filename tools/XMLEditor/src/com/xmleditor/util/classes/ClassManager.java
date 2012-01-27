package com.xmleditor.util.classes;

import java.io.IOException;
import java.util.ArrayList;

import skillsplanner.classes.DFOClass;
import skillsplanner.utils.jdom.Handler;

public class ClassManager {

	private static ClassManager cm;
	
	public static ClassManager getInstance(){
		if(cm == null){
			cm = new ClassManager();
		}
		return cm;
	}
	
	public DFOClass getDFOClass(String name){
		try {
			return Handler.getClassLoader().getClass(name);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public ArrayList<DFOClass> getAllClasses(){
		return Handler.getClassLoader().getClasses();
	}
}
