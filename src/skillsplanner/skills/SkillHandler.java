package skillsplanner.skills;

import skillsplanner.DFOCharacter;
import skillsplanner.resources.StaticResources;
import skillsplanner.skills.errors.CurrentRequirementException;
import skillsplanner.skills.errors.MaxLevelException;
import skillsplanner.skills.errors.MinLevelException;
import skillsplanner.skills.errors.RequirementsNotMetException;
import skillsplanner.skills.errors.SPException;
import skillsplanner.utils.jdom.Handler;

/**
 * Singleton based class that handles skill operations such as level up and down, with error checking
 * @author Ryzngard
 *
 */
public class SkillHandler {
	
	private static SkillHandler skillHandler;
	
	/**
	 * Get the instance of this class.
	 * @return
	 */
	public static SkillHandler getInstance(){
		if(skillHandler == null){
			skillHandler = new SkillHandler();
		}
		return skillHandler;
	}
	
	/**
	 * Attempts to level up the passed in skill
	 * @param st skill to be leveled
	 * @throws RequirementsNotMetException 
	 * @throws SPException 
	 * @throws MaxLevelException 
	 */
	public static void levelUp(SkillsTemplate st) throws RequirementsNotMetException, SPException, MaxLevelException{
		
		DFOCharacter character = StaticResources.getCharacter();
		
		//Check the prerequisites are met or throw an error if not
		if(!st.requirementsFulfilled(character.getDFOClass())){
			throw new RequirementsNotMetException(st);
		}
		
		//Check level constraints
		if(character.getDFOClass().getSkills().containsKey(st.getName()) && character.getDFOClass().getSkills().get(st.getName()) == st.getMaxLevel()){
			throw new MaxLevelException(st);
		}
		
		//Check sp availability
		if(character.getRemainingSP() < st.getSpcost()){
			throw new SPException("Not enough sp");
			
		}
		
		//Increase skill level
		System.out.println("BEFORE: "+character.getDFOClass().getSkills().get(st.getName()));
		
		character.getDFOClass().getSkills().put(st.getName(),character.getDFOClass().getSkills().get(st.getName())+1);
		
		System.out.println("AFTER: "+character.getDFOClass().getSkills().get(st.getName()));
		
	}
	
	/**
	 * Attempts to level down the passed skill.
	 * @param st
	 * @throws MinLevelException
	 * @throws CurrentRequirementException
	 */
	public static void levelDown(SkillsTemplate st) throws MinLevelException, CurrentRequirementException{
		
		DFOCharacter character = StaticResources.getCharacter();
				
		//Check level constraints
		if(character.getDFOClass().getSkills().containsKey(st.getName()) && character.getDFOClass().getSkills().get(st.getName()) == st.getMinLevel()){
			throw new MinLevelException(st);
		}
		
		//Check if the skill is a requirement for other skills already added
		if(isCurrentlyRequired(st)){
			throw new CurrentRequirementException(st.getName());
		}
		//Decrease skill level
		System.out.println("BEFORE: "+character.getDFOClass().getSkills().get(st.getName()));
		
		character.getDFOClass().getSkills().put(st.getName(),character.getDFOClass().getSkills().get(st.getName())-1);
		
		System.out.println("AFTER: "+character.getDFOClass().getSkills().get(st.getName()));
		
	}
	
	/**
	 * Checks to see if the skill passed in is currently a requirement
	 * for any skills already added.  So Dirty.
	 * @param st
	 * @return
	 */
	private static boolean isCurrentlyRequired(SkillsTemplate st){
		DFOCharacter character = StaticResources.getCharacter();
		
		//Get all the skills for the class  KAAAA
		for(String s : character.getDFOClass().getSkills().keySet()){		
			
			//Check if the skill has been leveled (is higher than min level)  MEEEE
			if(character.getDFOClass().getSkills().get(s) > Handler.getSkillLoader().getSkill(s).getMinLevel()){				
				
				//Check for null skill requirements HAAA
				if(Handler.getSkillLoader().getSkill(s).getSkillRequirements() != null){
				
					//Loop through its skill requirements MEEEE
					for(int i = 0; i < Handler.getSkillLoader().getSkill(s).getSkillRequirements().size(); i++){					
						
						//Check if they require the current skill, and what level HAAAAAAAAAAAAAAAAAAAAA
						if(Handler.getSkillLoader().getSkill(s).getSkillRequirements().get(i).getName().equalsIgnoreCase(st.getName())
								&& Handler.getSkillLoader().getSkill(s).getSkillRequirements().get(i).getLevel() == character.getDFOClass().getSkills().get(st.getName())){						
							
							//Yup. Don't touch me there. DAMN YOU KAKAROTTTTT
							return true;
						}
					}	
				}
			}
		}
		return false;
		
	}
	
	

}
