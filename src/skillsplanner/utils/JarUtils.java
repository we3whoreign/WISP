package skillsplanner.utils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.*;
import java.util.zip.*;

import skillsplanner.utils.jdom.Handler;

/**
 * This class works like FileUtils but is to handle files inside a jar. 
 * @author ryzngard
 *
 */
public class JarUtils {

	public static JarFile jar;
	
	/**
	 * Returns all elements in libs/classes/ that end in xml
	 * @return
	 */
	public static List<Object> getDFOClassFiles() {
		Enumeration<JarEntry> em = getJarFile().entries();
		List<Object> list = new ArrayList<Object>();
		while(em.hasMoreElements()){
			String str = em.nextElement().getName().toLowerCase();
			if(str.contains("libs/classes/") && str.endsWith(".xml")){
				str = str.replaceAll("/",File.separator);
				list.add(str);
			}
		}
		
		return list;
	}
	
	
	public static JarFile getJarFile(){
		if(jar == null){
			try{
				jar = new JarFile(getJarFilePath());
			}
			catch(Exception e){
				System.out.println(e.getMessage());
				System.exit(0);
			}
		}
		return jar;
	}
	
	private static String getJarFilePath() {
	    String name = JarUtils.class.getName().replace('.', '/');
	    String s = JarUtils.class.getClass().getResource("/" + name + ".class").toString();
	    s = s.replace('/', File.separatorChar);
	    s = s.substring(0, s.lastIndexOf(".jar")+4);
	    s = s.substring(s.lastIndexOf(':')+1);
	    return s;
	 }


	/**
	 * Checks whether the path exists in the stored class files
	 * @param path
	 * @return
	 */
	public static boolean isClassFile(String path) {
		//System.out.println("Checking: "+path + ".xml");
		path = path + ".xml";
		return Handler.getClassLoader().hasClass(path);
	}


	/**
	 * Takes in a path used from the classes tree to derive the parent path and list all subclasses within it.
	 * @param path
	 * @return
	 */
	public static List<String> getSubclasses(String path) {
		// Get the parent class
		try{
			path = path.substring(0,path.lastIndexOf(File.separator));
		}
		catch(Exception e){
			return null;
		}
		
		// Define regex patterns to search
		String pattern = ".*" + File.separator + "skills" + File.separator + path + File.separator + "[\\S]+";
		String xmlpattern = ".*\\.[xX][mM][lL]";
		
		
		List<String> list = new ArrayList<String>();
		Enumeration<JarEntry> em = getJarFile().entries();
		
		while(em.hasMoreElements()){
			String str = em.nextElement().getName().toLowerCase();
			if(str.matches(pattern) && !str.matches(xmlpattern)){
				if(str.endsWith(File.separator)){
					str = str.substring(0,str.length()-1);
				}
				list.add(str.substring(str.lastIndexOf(File.separator)+1).replaceAll(".xml", ""));
			}
		}
		
		return list;
	}
	
}
