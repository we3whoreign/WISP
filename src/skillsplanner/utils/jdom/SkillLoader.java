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
 * @author ryzngard
 *
 */
public class SkillLoader {

	private static HashMap<String,SkillsTemplate> skillList;
	
	public void loadSkills() throws URISyntaxException, Exception{
		for(File f : FileUtils.getSkillFiles()){
			Document doc = Handler.openXMLFile(f);
			
			mapSkill(doc);
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
		
		Element root = doc.getRootElement();
		
		Element skillattr = root.getChild("skill");
		
		SkillsTemplate st = new SkillsTemplate();
		
		st.setAnimationFrameTime(Handler.getDouble(skillattr,"frametime"));
		
		st.setCastTime(Handler.getDouble(skillattr, "casttime"));
		
		st.setName(Handler.getString(skillattr, "name"));
		
		st.setSpcost(Handler.getInteger(skillattr,"spcost"));
		
		Element dynamic = skillattr.getChild("dynamic");
		
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
		
		if(skillList == null){
			skillList = new HashMap<String,SkillsTemplate>();
		}
		
		skillList.put(st.getName(), st);
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
	
	public static String[] getSkillList(){
		Object[] oarray = skillList.keySet().toArray();
		String[] sarray = new String[oarray.length];
		
		for(int i=0; i < oarray.length; i++){
			sarray[i] = (String) oarray[i];
		}
		
		return sarray;
	}
	
}
