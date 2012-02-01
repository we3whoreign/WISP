package com.xmleditor.util.classes;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.xmleditor.beans.DFOClass;
import com.xmleditor.io.FileSystemResource;
import com.xmleditor.io.IOHandler;
import com.xmleditor.io.JarResource;
import com.xmleditor.io.ResourceHandler;
import com.xmleditor.util.jdom.ClassMapper;


public class ClassManager {
	private HashMap<String,DFOClass> classes;
	private static ClassManager cm;
	
	public ClassManager(){
		classes = new HashMap<String,DFOClass>();
		
		for(InputStream stream : IOHandler.getAllClasses()){
			DFOClass dfoclass = ClassMapper.createClassFromStream(stream);
			classes.put(dfoclass.getName(), dfoclass);
		}
	}
	
	public static ClassManager getInstance(){
		if(cm == null){
			cm = new ClassManager();
		}
		
		return cm;
	}
	
	public DFOClass getDFOClass(String name){
		return null;
	}
	
	public HashMap<String,DFOClass> getAllClasses(){
		return classes;
	}
}
