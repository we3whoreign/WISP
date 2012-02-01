package com.xmleditor.io;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.jar.JarFile;

public class JarResource implements ResourceHandler {
	private JarFile jar;
	private String path = "";
	
	public JarResource(){
		try {
			jar = new JarFile(getJarFilePath());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InputStream getResource(String resource) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getResourceIdentifiers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String resourceLookup(String search) {
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
		return null;
	}

}
