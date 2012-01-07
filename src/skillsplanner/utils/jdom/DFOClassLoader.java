package skillsplanner.utils.jdom;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Hashtable;

import org.jdom.Attribute;
import org.jdom.Document;
import org.jdom.Element;

import skillsplanner.classes.DFOClass;
import skillsplanner.skills.SkillsTemplate;
import skillsplanner.utils.FileUtils;
import skillsplanner.utils.JarUtils;

/**
 * Loads the class xml files.
 * Never create an instance of this, only use Handler.getClassLoader()
 * @author ryzngard
 *
 */
public class DFOClassLoader {
	
	private List<Object> classfiles;
	
	public DFOClassLoader(){
		try {
			classfiles = FileUtils.getDFOClassFiles();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Check the classfiles list for this object
	 * @param c
	 * @return
	 */
	public boolean hasClass(String c){
		for(Object o : classfiles){
			if(((String)o).contains(c)){
				return true;
			}
		}
		return false;
	}
	
	public String[] listClasses(){
		String[] ret = new String[classfiles.size()];
		int i = 0;
		if(!FileUtils.isJar){
			for(Object file : classfiles){
				File f = (File) file;
				ret[i] = f.getName();
				i++;
			}
		}
		else{
			for(Object file : classfiles){
				ret[i] = (String) file;
				i++;
			}
		}
		
		return ret;
	}
	
	public Hashtable<String,ArrayList<String>> listClassTable(){
		Hashtable<String,ArrayList<String>> ret = new Hashtable<String,ArrayList<String>>(21);
		String parent; 
		
		if(!FileUtils.isJar){
			for(Object f : classfiles){
				File file = (File) f;
				parent = file.getParent().substring(file.getParent().lastIndexOf(file.separator)+1, file.getParent().length());
				
				 if(ret.containsKey(parent)){
					 ret.get(parent).add(file.getName().replace(".xml", ""));
				 }
				 else{
					 ret.put(parent, new ArrayList<String>());
					 ret.get(parent).add(file.getName().replace(".xml", ""));
				 }
			}
		}
		else{
			for(Object f : classfiles){
				String file = (String) f;
				
				if(file.endsWith(".xml")){
					parent = file.substring(0,file.lastIndexOf(FileUtils.getSeparator()));
					parent = parent.substring(parent.lastIndexOf(FileUtils.getSeparator())+1);
					String child = file.substring(file.lastIndexOf(FileUtils.getSeparator())+1).replaceAll(".xml", "");
					if(ret.containsKey(parent)){
						ret.get(parent).add(child);
					}
					else{
						ret.put(parent, new ArrayList<String>());
						ret.get(parent).add(child);
					}
				}
			}
		}
		
		return ret;
	}
	
	/**
	 * Return the DFOClass object associated with the given class
	 * @param dfoclass
	 * @return
	 * @throws IOException 
	 */
	public DFOClass getClass(String dfoclass) throws IOException{
		if(!FileUtils.isJar){
			for(Object f : classfiles){
				File file = (File) f;
				String name = file.getName();
				if(name.equals(dfoclass)){
					return createClassFromFile(file);
				}
			}
		}
		else{
			for(Object f : classfiles){
				String name = (String) f;
				if(name.equals(dfoclass)){
					return createClassFromStream(JarUtils.getResource(dfoclass));
				}
			}
		}
				
		return null;
	}
	
	/**
	 * Functions like createClassFromFile, it gets the doc object and reads the xml to map it to a dfoclass object.
	 * @param resource
	 * @return
	 */
	private DFOClass createClassFromStream(InputStream resource) {
		DFOClass dfoclass = new DFOClass();
		
		Document doc = Handler.openXMLFile(resource);

		Element root = doc.getRootElement();
		
		dfoclass.setDescription(root.getChildText("description"));
		
		dfoclass.setName(root.getChildText("name"));
		
		for(Object v : root.getChildren("skill")){
			Element e = (Element) v;
			try {
				getSkillFromElement(e);
			} catch (Exception e1){}
 		}
		
		for(String s : SkillLoader.skillList.keySet()){
			dfoclass.addSkill(SkillLoader.getSkill(s));
		}
		
		return dfoclass;
	}

	/**
	 * Creates a DFO Class from File
	 * @param f
	 * @return
	 */
	private DFOClass createClassFromFile(File f){
		DFOClass dfoclass = new DFOClass();
		
		Document doc = Handler.openXMLFile(f.getAbsolutePath());
		
		//Start the magic
		
		//cast summon imp
		Element root = doc.getRootElement();
		//and the imp sings....
		
		// Mama...... oooohhhoooooooooo
		dfoclass.setDescription(root.getChildText("description"));
		
		// I don't wanna diiiiieeeeeee
		dfoclass.setName(root.getChildText("name"));
		
		// if I don't come back this time tomorrow carry onnnnn....
		for(Object v : root.getChildren("skill")){
			// iterate through every skill tag getting the name
			Element e = (Element) v;
			try {
				getSkillFromElement(e);
			} catch (Exception e1){}
 		}
		
		for(String s : SkillLoader.skillList.keySet()){
			dfoclass.addSkill(SkillLoader.getSkill(s));
		}
		
		// carry on...
		/**
		try{
			Element skillchange = root.getChild("skill");
			SkillLoader sl = new SkillLoader();
			sl.addSkill(sl.getSkillFromElement(skillchange));
		}
		catch(Exception e){}*/
		
		
		// 'cuz nothing really matters
		return dfoclass;
	}

	private void getSkillFromElement(Element e) throws URISyntaxException, Exception {
		String name = e.getText();
		Handler.getSkillLoader().loadSkillFile(name);
	}

	private SkillsTemplate getSkillFromAttribute(Attribute attribute) {
		String skillname = attribute.getValue();
		
		return SkillLoader.getSkill(skillname);
	}
	
	
}
