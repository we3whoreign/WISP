package skillsplanner.io;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ConcurrentMap;

import skillsplanner.beans.DFOClass;
import skillsplanner.beans.Skill;
import skillsplanner.utils.StringUtils;

/**
 * Handler for controlling IO. Specifically the connector from ResourceHandlers to the outside world
 * @author Andrew
 *
 */
public class IOHandler {

	private static String IMAGES_DIR = "libs/images";
	private static String SKILLS_DIR = "libs/skills";
	private static String CLASSES_DIR = "libs/classes";
	private static ConcurrentLinkedQueue<ResourceHandler> handlers = new ConcurrentLinkedQueue<ResourceHandler>();
	
	/**
	 * Override flag to say whether or not this is a jar. Manually set to false to make sure to never use a jar resource.
	 */
	public static boolean JAROVERRIDE = true;
	
	public static ConcurrentMap<InputStream, String> getAllSkills(){
		return IOHandler.getResourcesWithParents(SKILLS_DIR);
	}
	
	/**
	 * Attemtps to get a system resource in order to determine whether the container is jar.
	 * @return true if running a jar
	 */
	private static boolean isJar(){
		String str = IOHandler.class.getResource("IOHandler.class").toString();
		return str.toLowerCase().startsWith("jar") && JAROVERRIDE;
	}
	
	/**
	 * Looks for all resources that begin with dirName. Must be full path since it looks for the starting
	 * @param dirName directory to look for
	 * @return
	 */
	public static List<InputStream> getResourcesIn(String dirName){
		ResourceHandler resourceHandler = getResourceHandler(dirName);
		
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
	public static ConcurrentMap<InputStream, String> getResourcesWithParents(String dirName){
		ResourceHandler resourceHandler = getResourceHandler(dirName);
		
		ConcurrentMap<InputStream,String> streamList = new ConcurrentHashMap<InputStream,String>();
		
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

	/**
	 * Gets an image matching string name, which is the name without the extension
	 * @param name
	 * @return
	 */
	public static InputStream getImageWithName(String name) {
		ResourceHandler resourceHandler = getResourceHandler(IMAGES_DIR);
		return resourceHandler.getResource(resourceHandler.resourceLookup(name));
		
	}

	/**
	 * Gets an output stream for the xml file related to the dfo class passed in
	 * @param dfoclass
	 * @return
	 */
	public static OutputStream getClassOutputter(DFOClass dfoclass) {
		ResourceHandler resourceHandler = getResourceHandler(CLASSES_DIR);
		return resourceHandler.getOutputResource(dfoclass.getUniqueName());
	}

	public static OutputStream getSkillOutputter(Skill skill, String folder) {
		ResourceHandler resourceHandler = getResourceHandler(SKILLS_DIR);
		System.out.println("Attempting to create: " + folder + "/" +generateFileName(skill.getName()));
		return resourceHandler.getOutputResource(folder + "/" +generateFileName(skill.getName()));
	}
	
	private static String generateFileName(String input){
		input = input.replaceAll("[\\s,',/,\\\\]", "_");
		return input.toLowerCase()+".xml";
	}

	/**
	 * Creates a table representing the class hierarchy. It should be stored as base/class
	 * @return
	 */
	public static Hashtable<String, ArrayList<String>> createClassTable() {
		ResourceHandler resourceHandler = getResourceHandler(CLASSES_DIR);
		Hashtable<String, ArrayList<String>> table = new Hashtable<String, ArrayList<String>>();
		for(String dir : resourceHandler.listDirs()){
			table.put(dir, new ArrayList<String>());
			for(String file : resourceHandler.listFiles(dir)){
				file = file.replaceAll("/", "");
				if(!dir.endsWith("/")){
					file = "/" + file;
				}
				table.get(dir).add(StringUtils.toFileName((dir+file.replaceAll("\\.xml", ""))));
			}
		}
		return table;
	}
	
	private static synchronized ResourceHandler getResourceHandler(String path){
		for(ResourceHandler handler : handlers){
			if(path.equals(handler.getPath())){
				return handler;
			}
		}
		System.out.println("Creating resource handler for "+path);
		ResourceHandler handler = (IOHandler.isJar()) ? new JarResource(path) : new FileSystemResource(path);
		handlers.add(handler);
		
		return handler;
	}
	
	
}
