package Skills;

import java.lang.Class;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.net.URL;
import java.net.URLClassLoader;

public class Skills{

	//descriptive variables that all skills should have
	private int level;
	private String description;
	//private DFOClass availableClass; 

	// Where we will store the classes, we don't need duplicates
	HashSet<Class> classList = new HashSet<Class>(); 


	public Skills() throws Exception{
		getChildren();
	}

	/**
	* This dynamically loads all the classes located in subdirectories (subpackages) from the current
	*/
	private void getChildren() throws Exception{
		//create a file object to represent the current directory
		File file = new File("./Skills");

		//start the traversion
		traverseDirectory(file);
										
		
	}

	/**
	* traverse a directory looking for .class files to load
	*/
	private void traverseDirectory(File file) throws Exception{
		if(file == null){
			throw new FileNotFoundException();
		}		
		else if(!file.isDirectory()){
			//we can't traverse this, pass on to the class loader
			loadClass(file);
		}

		String files[] = file.list();

		if(files == null){
			return;
		}

		for(int i=0; i < files.length; i++){
			File f = new File(file.getPath() + File.separator + files[i]);
			traverseDirectory(f);
		}
			
	}

	/**
	* methodology to load classes using the classloader
	*/
	private void loadClass(File file) throws Exception{

		if(file.getName().endsWith(".class")){
			//convert the file to a URL so that we may use the URL class loaded.
			// must convert to URI first, since the file.toURL method is now deprecated	
			URL url = file.toURI().toURL(); 
			
			//convert to an array to pass as an argument
			URL[] urls = new URL[]{url};
			
			// declare the class loader for this directory
			ClassLoader classLoader = new URLClassLoader(urls);

			Class cls = classLoader.loadClass(file.getPath().substring(2,file.getPath().length()-6).replace("/","."));

			if(!classList.contains(cls)){
				System.out.println("Adding a new class...");
				classList.add(cls);
				System.out.println("Added: "+cls.getName());
			}
		}
	}
}
	
