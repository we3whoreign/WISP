package skillsplanner.beans;

import java.util.HashMap;


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
	private String baseClass;
	public String getBaseClass() {
		return baseClass;
	}

	public void setBaseClass(String baseClass) {
		this.baseClass = baseClass;
	}

	private HashMap<String,Integer> skills;

	
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void addSkill(Skill st) {
		if(skills == null){
			skills = new HashMap<String,Integer>();
		}
		
		//don't add the skill if it exists
		if(skills.containsKey(st.getName())){
			return;
		}
		skills.put(st.getName(),st.getMinLevel());
	}
	
	public HashMap<String,Integer> getSkills(){
		return skills;
	}
	
	@Override
	public String toString(){
		return this.name;
	}

}
