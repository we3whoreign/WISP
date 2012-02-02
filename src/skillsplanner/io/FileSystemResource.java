package skillsplanner.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;

public class FileSystemResource implements ResourceHandler {
	File rootDir;

	public FileSystemResource(String rootDir) {
		// TODO Auto-generated constructor stub
		this.rootDir = new File(rootDir);
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
			if(Pattern.matches(regex, resource)){
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
		parent = parent.substring(parent.lastIndexOf("/")+1, parent.length());
		
		return parent;
	}

}
