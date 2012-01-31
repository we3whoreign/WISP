package com.xmleditor.util.classes;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.xmleditor.beans.DFOClass;
import com.xmleditor.io.FileSystemResource;
import com.xmleditor.io.JarResource;
import com.xmleditor.io.ResourceHandler;
import com.xmleditor.util.jdom.ClassMapper;


public class ClassManager {
	private final String CLASSES_LOCATION = "libs/classes/";
	private HashMap<String,DFOClass> classes;
	ResourceHandler manager;
	
	public ClassManager(boolean isJar){
		classes = new HashMap<String,DFOClass>();
		if(isJar){
			manager = new JarResource(CLASSES_LOCATION);
		}
		else{
			manager = new FileSystemResource(CLASSES_LOCATION);
		}
		
		for(InputStream is : manager.getObjects()){
			//DFOClass sk = ClassMapper.getSkillFromStream(is);
		}
	}
	
	public DFOClass getDFOClass(String name){
		return null;
	}
	
	public ArrayList<DFOClass> getAllClasses(){
		return Handler.getClassLoader().getClasses();
	}
}
