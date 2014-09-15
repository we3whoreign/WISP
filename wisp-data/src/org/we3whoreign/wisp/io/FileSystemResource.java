package org.we3whoreign.wisp.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;


/**
 * FileSystemResource handler. Useful for testing and 
 * loading files off of a flat system, instead of out of
 * a jar.
 * 
 * @author ryzngard
 *
 */
public class FileSystemResource implements ResourceHandler {
	File rootDir;
	String path;
	
	static String[] possibleLocations = 
	{
		"data" + File.separatorChar + "libs",
		"libs" 
	};

	public FileSystemResource(String rootDir) {
		this.rootDir = new File(rootDir);
		this.path = rootDir;
		
		if (!this.rootDir.exists())
		{
			// The directory isn't a subdir of the loading area.
			// Often times this is due to debugging unpacking in weird ways.
			// Look for a few known locations up the parent tree
			
			// TODO: Possibly look for more than just libs directory
			File currentDir = new File("");
			while(currentDir != null)
			{
				String fullPath = currentDir.getAbsolutePath();
				File newFile;
				for(String searchPath : possibleLocations)
				{
					String path = fullPath + File.separatorChar + searchPath + File.separatorChar + rootDir;
					newFile = new File(path);
					
					if (newFile.exists())
					{
						this.rootDir = newFile;
						return;
					}
				}
				
				currentDir = new File(getParentPath(fullPath));
			}
		}
	}


	@Override
	public List<InputStream> getObjects() {
		List<InputStream> list = new ArrayList<InputStream>();
		for(String resource : getResourceIdentifiers()){
			list.add(getResource(resource));
		}
		return list;
	}

	@Override
	public InputStream getResource(String resource) {
		//Use resourceLookup to get the exact resource
		//resource = resourceLookup(resource);
		if(resource == null){
			return null;
		}
		InputStream is = null;
		try {
			is = new FileInputStream(resource);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return is;
	}

	@Override
	public List<String> getResourceIdentifiers() {
		// TODO Auto-generated method stub
		List<File> fileList = getAllFiles(rootDir);
		
		List<String> list = new ArrayList<String>();
		
		for(File f : fileList){
			list.add(FileSystemResource.pathify(f.getPath()));
		}
		
		return list;
	}
	

	@Override
	public String resourceLookup(String regex) {
		for(String resource : getResourceIdentifiers()){
			if(resource.toLowerCase().contains(regex.toLowerCase())){
				return resource;
			}
		}
		return null;
	}

	@Override
	public List<String> getAllMatches(String pattern) {
		List<String> list = new ArrayList<String>();
		
		for(String resource : getResourceIdentifiers()){
			if(Pattern.matches(pattern, resource)){
				list.add(resource);
			}
		}
		return list;
	}
	
	public void setPath(File file){
		rootDir = file;
	}
	
	private List<File> getAllFiles(File f){
		ArrayList<File> list = new ArrayList<File>();
		if(!f.isDirectory()){
			list.add(f);
			return list;
		}
		
		File[] files = f.listFiles();
		for(int i = 0; i< files.length; i++){
			list.addAll(getAllFiles(files[i]));
		}
		
		return list;
		
	}
	
	public static String pathify(String path){
		path = path.replaceAll("\\\\", "/");
		path = path.replaceAll("//","/");
		
		return path;
	}


	public String getParent(String resource) {
		if(!getResourceIdentifiers().contains(resource)){
			return "";
		}
		String parent = resource.substring(0,resource.lastIndexOf("/"));
		String grandparent = parent.substring(0,parent.lastIndexOf("/"));
		grandparent = grandparent.substring(grandparent.lastIndexOf("/")+1,grandparent.length());
		parent = parent.substring(parent.lastIndexOf("/")+1, parent.length());
		
		return grandparent+"/"+parent;
	}


	@Override
	public OutputStream getOutputResource(String uniqueName) {
		String resource = resourceLookup(uniqueName);
		try {
			if(resource != null){
				return new FileOutputStream(resource);
			}
			else{
				return new FileOutputStream(rootDir.getAbsolutePath()+"/"+uniqueName);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}


	@Override
	public List<String> listDirs() {
		List<String> list = new ArrayList<String>();
		for(String s : rootDir.list()){
			if(new File(rootDir.getPath()+"/"+s).isDirectory()){
				list.add(s);
			}
		}
		
		return list;
	}


	@Override
	public List<String> listFiles(String dir) {
		List<String> list = new ArrayList<String>();
		File f = new File(rootDir.getPath() + "/"+ dir);
		for(String s : f.list()){
			if(s.toLowerCase().endsWith(".xml")){
				list.add(s);
			}
		}
		return list;
	}


	@Override
	public String getPath() {
		return path;
	}
	
	private String getParentPath(String path) {
		return path.substring(0,path.lastIndexOf(File.separatorChar));
	}

}
