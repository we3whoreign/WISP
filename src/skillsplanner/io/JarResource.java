package skillsplanner.io;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarFile;
import java.util.regex.Pattern;

public class JarResource implements ResourceHandler {
	private JarFile jar;
	private String path = "";
	
//	public JarResource(){
////		try {
////			jar = new JarFile(getJarFilePath());
////		} catch (IOException e) {
////			// TODO Auto-generated catch block
////			e.printStackTrace();
////		}
//	}

	/**
	 * Everytime a resource is looked for, it is restricted to anything starting with path
	 * @param path path to look in
	 */
	public JarResource(String path){
		this.path = path;
		try {
			jar = new JarFile(getJarFilePath());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public List<InputStream> getObjects() {
		List<InputStream> list = new ArrayList<InputStream>();
		for(String resource : getResourceIdentifiers()){
			list.add(this.getClass().getResourceAsStream(resource));
		}
		
		return list;
	}

	@Override
	public InputStream getResource(String resource) {
		return this.getClass().getResourceAsStream(resource);
	}

	@Override
	public List<String> getResourceIdentifiers() {
		List<String> list = new ArrayList<String>();
		Enumeration enumeration = jar.entries();
		while(enumeration.hasMoreElements()){
			String resource = enumeration.nextElement().toString();
			if(resource.startsWith(path) && !resource.endsWith("/")){
				list.add(resource);
			}
		}
		return list;
	}

	@Override
	public String resourceLookup(String search) {
		for(String resource : getResourceIdentifiers()){
			if(resource.contains(search)){
				return resource;
			}
		}
		
		return null;
	}

	/**
	 * Gets the path of the running jar that contains JarResource
	 * @return
	 */
	private String getJarFilePath() {
		String name = JarResource.class.getName().replace('.', '/');
	    String s = JarResource.class.getClass().getResource("/" + name + ".class").toString();
	    s = s.substring(0, s.lastIndexOf(".jar")+4);
	    s = s.substring(s.lastIndexOf(':')+1);
	    return s;
	}

	@Override
	public List<String> getAllMatches(String pattern) {
		System.out.println(pattern);
		List<String> list = new ArrayList<String>();
		for(String resource : getResourceIdentifiers()){
			if(Pattern.matches(pattern, resource)){
				list.add(resource);
				System.out.println(resource);
			}
		}
		return list;
	}
	
	public String getParent(String resource) {
		if(!getResourceIdentifiers().contains(resource)){
			return "";
		}
		String parent = resource.substring(0,resource.lastIndexOf("/")-1);
		parent = parent.substring(parent.lastIndexOf("/"), parent.length());
		
		return parent;
	}

}
