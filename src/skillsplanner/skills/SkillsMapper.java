package skillsplanner.skills;

import java.util.List;

public class SkillsMapper {

	private SkillsLoader skillsLoader;
	
	private String className;
	
	public SkillsMapper(String className) throws Exception{
		this.className = className;
		skillsLoader = new SkillsLoader();
	}
	
	public List<SkillsTemplate> GetSkills(){
		if(className == null){
			return null;
		}
		
		return null;
	}
	
	/**
	 * Define a skill set for each 
	 */
}
