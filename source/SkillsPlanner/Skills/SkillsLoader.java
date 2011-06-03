package SkillsPlanner.Skills;

import java.lang.Class;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Map;
import java.util.HashMap;
import java.lang.reflect.Constructor;

/**
* Dynamically loads all of the class files. Current implementation searches a subdirectory called ./Skills (from where the running program is). 
* Call from the main program in the root project directory in order for correctness. Later dates may include the ability to change the directory,
* but that is not yet implemented
*/
public class SkillsLoader{

	//descriptive variables that all skills should have
	private int level;
	private String description;
	//private DFOClass availableClass; 

	// Where we will store the classes, we don't need duplicates
	HashSet<Class> classList = new HashSet<Class>(); 


	/**
	* The constructor makes a call to getChildren in order to load the class list
	*/
	public SkillsLoader() throws Exception{
		getChildren();
	}

	/**
	* This dynamically loads all the classes located in subdirectories (subpackages) from the current
	*/
	private void getChildren() throws Exception{
		//create a file object to represent the current directory
		File file = new File("./Skills");
		
		String files[] = file.list();
		
		/**
		* Don't travers the base directory, but travers all subdirectories
		*/
		for(int i=0; i < files.length; i++){
			File f = new File(file.getPath() + File.separator + files[i]);
			if(f.isDirectory()){
				traverseDirectory(f);
			}
		}
										
		
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

			String classFile = file.getPath().substring(2,file.getPath().length()-6).replace("/",".");
			classFile = classFile.trim();
			
			//Check for a few classes that might be loaded that aren't needed
			/**
			if(isException(classFile)){
				return;
			}
			*/
			Class cls = classLoader.loadClass(classFile);//file.getPath().substring(2,file.getPath().length()-6).replace("/","."));

			if(!classList.contains(cls)){
				classList.add(cls);
				System.out.println("Added: "+cls.getName());
			}
		}
	}

	/**
 	* Declare an instance of each class and return a hashmap using the class name as the lookup
 	*/
	public Map<String,SkillsTemplate> getClassObjects() throws Exception{
		
		Map<String,SkillsTemplate> ret = new HashMap<String,SkillsTemplate>();
	
		for(Class c : classList){
			Constructor ct = c.getConstructor();
			Object tmpobj = ct.newInstance();
			try{
				SkillsTemplate tmp = (SkillsTemplate) tmpobj;
				ret.put(c.getName(),tmp);
			}
			catch(Exception e){
				System.out.println("Get Class Objects Exception:");
				System.out.println(e.getMessage());
				throw e;
			}

		}

		return ret;

	}

	/**
 	* Checks whether the class should be loaded or not based on a list of exceptions for class that don't need to be loaded
 	* @return true if the class is an exception
 	*/
	private boolean isException(String filename){
		String[] exceptionList = {"Skills.Skills","Skills.SkillsLoader","Skills.SkillsTemplate"};
		for(int i = 0; i < exceptionList.length; i++){
			if(exceptionList[i].equals(filename)){
				return true;
			}
		}

		return false;
	}

		
}
	
