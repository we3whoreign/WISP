package skillsplanner.io;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Handler for controlling IO. Specifically the connector from ResourceHandlers to the outside world
 * @author Andrew
 *
 */
public class IOHandler {

	private static String IMAGES_DIR = "libs/images";
	private static String SKILLS_DIR = "libs/skills";
	private static String CLASSES_DIR = "libs/classes";
	
	public static Map<InputStream, String> getAllSkills(){
		return IOHandler.getResourcesWithParents(SKILLS_DIR);
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
			streamList.add(resourceHandler.getResource(s));
		}
		
		return streamList;
	}
	
	/**
	 * Looks for all resources that begin with dirName. Must be full path since it looks for the starting. Adds the parent directory as well
	 * @param dirName directory to look for
	 * @return
	 */
	public static Map<InputStream,String> getResourcesWithParents(String dirName){
		ResourceHandler resourceHandler = (IOHandler.isJar()) ? new JarResource(dirName) : new FileSystemResource(dirName);
		
		Map<InputStream,String> streamList = new HashMap<InputStream,String>();
		
		for(String s : resourceHandler.getAllMatches(".*\\.xml")){
			streamList.put(resourceHandler.getResource(s),resourceHandler.getParent(s));
		}
		
		return streamList;
	}
	
	public static void setSkillsDir(String dir){
		SKILLS_DIR = dir;
	}
	
	public static void setClassDir(String dir){
		CLASSES_DIR = dir;
	}
	
	public static void setImagesDir(String dir){
		IMAGES_DIR = dir;
	}

	public static List<InputStream> getAllClasses() {
		return IOHandler.getResourcesIn(CLASSES_DIR);
	}
	
	public static Map<InputStream,String> getClassesWithParents(){
		return IOHandler.getResourcesWithParents(CLASSES_DIR);
	}
	
	/**
	 * Gets an input stream for all images
	 * @return
	 */
	public static List<InputStream> getAllImages(){
		return IOHandler.getResourcesIn(IMAGES_DIR);
	}

	public static InputStream getImageWithName(String name) {
		ResourceHandler resourceHandler = (IOHandler.isJar()) ? new JarResource(IMAGES_DIR) : new FileSystemResource(IMAGES_DIR);
		return resourceHandler.getResource(resourceHandler.resourceLookup(name));
		
	}
	
	
}
