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

	private static ResourceHandler resourceHandler;
	
	/**
	 * Creates the appropriate resource handler based on whether or not we are in the filesystem or a jar
	 */
	private static void defineResourceHandler(){
		if(isJar()){
			resourceHandler = new JarResource();
		}
		else{
			resourceHandler = new FileSystemResource();
		}
		
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
		if(resourceHandler == null){
			defineResourceHandler();
		}
		List<InputStream> streamList = new ArrayList<InputStream>();
		
		for(String s : resourceHandler.getAllMatches("^"+dirName)){
			streamList.add(resourceHandler.getResource(s));
		}
		
		return streamList;
	}
	
	public static InputStream getResource(String resource){
		return resourceHandler.getResource(resource);
	}
	
	
}
