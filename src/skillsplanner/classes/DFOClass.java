package skillsplanner.classes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.lang.UnsupportedOperationException;

import org.jdom.Attribute;

import skillsplanner.skills.SkillsTemplate;

/**
 * This defines the rules for any character class. Since all information
 * is loaded from an XML file, this only defines the structure of 
 * what information is being looked for. 
 * @author ryzngard
 *
 */
public class DFOClass {

	private String description;
	private String name; 
	private HashMap<String,SkillsTemplate> skills;

	
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		System.out.println("Setting description to: "+description);
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		System.out.println("Setting name to: "+name);
		this.name = name;
	}

	public void addSkill(SkillsTemplate st) {
		if(skills == null){
			skills = new HashMap<String,SkillsTemplate>();
		}
		System.out.println("Adding "+st.getName()+" to skills");
		skills.put(st.getName(),st);
	}
	
	public HashMap<String,SkillsTemplate> getSkills(){
		return skills;
	}
}
