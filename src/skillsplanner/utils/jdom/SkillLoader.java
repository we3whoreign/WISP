package skillsplanner.utils.jdom;

import java.io.File;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;

import org.jdom.*;

import skillsplanner.skills.SkillsTemplate;
import skillsplanner.utils.FileUtils;

/**
 * Handles the skill list including loading all the xml files
 * Never create an instance of this, use the Handler.getSkillLoader()
 * @author ryzngard
 *
 */
public class SkillLoader {

	public static HashMap<String,SkillsTemplate> skillList;
	
	public void loadSkills() throws URISyntaxException, Exception{
		if(!FileUtils.isJar){
			for(Object f : FileUtils.getSkillFiles()){
				File file = (File) f;
				Document doc = Handler.openXMLFile(file);
				
				mapSkill(doc);
			}
		}
	}
	
	public void loadSkillFile(String filename) throws URISyntaxException, Exception{
		
		if(filename == null){
			return;
		}
		else if(!filename.endsWith(".xml")){
			filename=filename+".xml";
		}
		
		for(Object f : FileUtils.getSkillFiles()){
			File file = (File) f;
			if(file.getName().equals(filename)){
				Document doc = Handler.openXMLFile(file);
				mapSkill(doc);
				break;
			}
		}
	}
	
	/**
	 * Sets all attributes for a skill except max level, since that would be based on class selection
	 * @param doc
	 */
	private void mapSkill(Document doc) {
		if(doc == null){
			return;
		}
		
		String baseURI = doc.getBaseURI();
		
		Element root = doc.getRootElement();
		
		Element skillattr = root.getChild("skill");
		
		//Magic to take the base URI and derive the subclass, easy to break 
		//and heavily depends on folder structure
		addSkill(getSkillFromElement(skillattr,FileUtils.getParentDir(baseURI)));
	}
	
	/**
	 * Create a SkillsTemplate object from a jdom Element
	 * @param skillattr
	 * @return
	 */
	public static SkillsTemplate getSkillFromElement(Element skillattr,String basename){
		
		SkillsTemplate st = new SkillsTemplate();
		
		st.setTree(FileUtils.firstLetterCap(basename));
		
		st.setAnimationFrameTime(Handler.getDouble(skillattr,"frametime"));
		
		st.setCastTime(Handler.getDouble(skillattr, "casttime"));
		
		st.setName(Handler.getString(skillattr, "name"));
		
		st.setSpcost(Handler.getInteger(skillattr,"spcost"));
		
		st.setEntrycost(Handler.getInteger(skillattr,"entrycost"));
		
		Element dynamic = skillattr.getChild("dynamic");
		
		//add a level 0 if entry cost is positive (CODYFLAG)
		if(st.getEntrycost() >= 0) {
			st.addDynamic(new ArrayList<String>());
		}
		
		//For each level
		for(Object o : dynamic.getChildren()){
			Element e = (Element) o;
			
			ArrayList<String> dynamiclist = new ArrayList<String>();
			
			//For each attribute for that level
			for(Object attr : e.getChildren()){
				Element eattr = (Element) attr;
				String temp = eattr.getAttributeValue("name")+": "+eattr.getAttributeValue("value");
				dynamiclist.add(temp);
			}
			
			st.addDynamic(dynamiclist);
		}
		
		return st;
	}
	
	/**
	 * add a skill to the list
	 * @param st
	 */
	public static void addSkill(SkillsTemplate st){
		
		if(skillList == null){
			skillList = new HashMap<String,SkillsTemplate>();
		}
			
		try{
			skillList.put(st.getName(), st);
		}
		catch(Exception e){
			// this needs to be reduced to only the duplicate exception
			
			// overwrite old with new
			skillList.remove(st.getName());
			skillList.put(st.getName(), st);
		}
	}

	/**
	 * Return a skill given the skill name
	 * @param skillname
	 * @return
	 */
	public static SkillsTemplate getSkill(String skillname) {
		try{
			return skillList.get(skillname);
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Gets a list of all the skill names in a string array
	 * @return
	 */
	public static String[] getSkillList(){
		if(skillList == null){
			return new String[0];
		}
		
		Object[] oarray = skillList.keySet().toArray();
		String[] sarray = new String[oarray.length];
		
		for(int i=0; i < oarray.length; i++){
			sarray[i] = (String) oarray[i];
		}
		
		return sarray;
	}
	
}
