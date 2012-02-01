package com.xmleditor.io;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Handler for controlling IO. Specifically the connector from ResourceHandlers to the outside world
 * @author Andrew
 *
 */
public class IOHandler {

	private static String SKILLS_DIR = "libs/skills";
	private static String CLASSES_DIR = "libs/classes";
	
	public static List<InputStream> getAllSkills(){
		return IOHandler.getResourcesIn(SKILLS_DIR);
	}
	
	/**
	 * Attemtps to get a system resource in order to determine whether the container is jar.
	 * @return true if running a jar
	 */
	private static boolean isJar(){
		return false;
	}
	
	/**
	 * Looks for all resources that begin with dirName. Must be full path since it looks for the starting
	 * @param dirName directory to look for
	 * @return
	 */
	public static List<InputStream> getResourcesIn(String dirName){
		ResourceHandler resourceHandler = (IOHandler.isJar()) ? new JarResource(dirName) : new FileSystemResource(dirName);
		
		List<InputStream> streamList = new ArrayList<InputStream>();
		
		for(String s : resourceHandler.getAllMatches(".*\\.xml")){
			System.out.println("Resource: "+s);
			streamList.add(resourceHandler.getResource(s));
		}
		
		return streamList;
	}
	
	public static void setSkillsDir(String dir){
		SKILLS_DIR = dir;
	}
	
	public static void setClassDir(String dir){
		CLASSES_DIR = dir;
	}

	public static List<InputStream> getAllClasses() {
		return IOHandler.getResourcesIn(CLASSES_DIR);
	}
	
	
}
