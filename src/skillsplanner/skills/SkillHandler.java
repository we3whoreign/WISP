package skillsplanner.skills;

import skillsplanner.DFOCharacter;
import skillsplanner.resources.StaticResources;
import skillsplanner.skills.errors.MaxLevelException;
import skillsplanner.skills.errors.RequirementsNotMetException;
import skillsplanner.skills.errors.SPException;

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
		if(character.getDFOClass().getSkills().get(st.getName()) == st.getMaxLevel()){
			throw new MaxLevelException(st);
		}
		
		//Check sp availability
		if(character.getRemainingSP() < st.getSpcost()){
			throw new SPException("Not enough sp");
			
		}
		
		//Add the skill if not already in there
		//character.getDFOClass().addSkill(st);
		System.out.println("BEFORE: "+character.getDFOClass().getSkills().get(st.getName()));
		
		character.getDFOClass().getSkills().put(st.getName(),character.getDFOClass().getSkills().get(st.getName())+1);
		
		System.out.println("AFTER: "+character.getDFOClass().getSkills().get(st.getName()));
		
	}
	
	

}
