package skillsplanner.utils.skills;

import skillsplanner.beans.DFOCharacter;
import skillsplanner.beans.Skill;
import skillsplanner.beans.SkillRequirement;
import skillsplanner.resources.SkillManager;
import skillsplanner.resources.StaticResources;
import skillsplanner.utils.skills.errors.CurrentRequirementException;
import skillsplanner.utils.skills.errors.MaxLevelException;
import skillsplanner.utils.skills.errors.MinLevelException;
import skillsplanner.utils.skills.errors.RequirementsNotMetException;
import skillsplanner.utils.skills.errors.SPException;

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
	public static void levelUp(Skill st) throws RequirementsNotMetException, SPException, MaxLevelException{
		
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
		
		
		/* Checks to see if the entry cost should apply, please note
		 * that skills with negative entry cost will never be level 0.
		 */
		if(character.getDFOClass().getSkills().get(st.getName()) == 0){
			character.spendSp(st.getSpcost() + st.getEntrycost());
		}
		else{
			character.spendSp(st.getSpcost());
		}
		
		//Increase level
		character.getDFOClass().getSkills().put(st.getName(),character.getDFOClass().getSkills().get(st.getName())+1);
		
		//Reflect SP change in GUI
		StaticResources.getWisp().updateRemainingSP(character.getRemainingSP());
		
		StaticResources.getWisp().updateSkillOverview();
		
	}
	
	/**
	 * Attempts to level down the passed skill.
	 * @param st
	 * @throws MinLevelException
	 * @throws CurrentRequirementException
	 */
	public static void levelDown(Skill st) throws MinLevelException, CurrentRequirementException{
		
		DFOCharacter character = StaticResources.getCharacter();
				
		//Check level constraints
		if(character.getDFOClass().getSkills().containsKey(st.getName()) && character.getDFOClass().getSkills().get(st.getName()) == st.getMinLevel()){
			throw new MinLevelException(st);
		}
		
		//Check if the skill is a requirement for other skills already added
		if(isCurrentlyRequired(st)){
			throw new CurrentRequirementException(st.getName());
		}
		
		/* Checks if the skill will be going down to level 0 and 
		 * therefore need to refund the entry cost SP for the skill.  Note
		 * that skills with negative entry cost will never be able to hit level 0.
		 */
		if((character.getDFOClass().getSkills().get(st.getName()) -1 ) == 0){
			character.refundSp(st.getSpcost() + st.getEntrycost());
		}
		else{
			character.refundSp(st.getSpcost());
		}
		
		//bug fix, need to decrease the level before updating sp. This way the skills overview update shows correctly
		//Decrease level
		character.getDFOClass().getSkills().put(st.getName(),character.getDFOClass().getSkills().get(st.getName())-1);
		
		//Reflect SP change in GUI
		StaticResources.getWisp().updateRemainingSP(character.getRemainingSP());
		
		
		
	}
	
	/**
	 * Checks to see if the skill passed in is currently a requirement
	 * for any skills already added.  So Dirty.
	 * @param st
	 * @return
	 */
	private static boolean isCurrentlyRequired(Skill st){
		DFOCharacter character = StaticResources.getCharacter();
		
		//Get all the skills for the class  KAAAA
		for(String s : character.getDFOClass().getSkills().keySet()){		
			
			//Check if the skill has been leveled (is higher than min level)  MEEEE
			if(character.getDFOClass().getSkills().get(s) > SkillManager.getInstance().getSkill(s).getMinLevel()){				
				
				//Check for null skill requirements HAAA
				if(SkillManager.getInstance().getSkill(s).getSkillRequirements() != null){
				
					//Loop through its skill requirements MEEEE
					for(int i = 0; i < SkillManager.getInstance().getSkill(s).getSkillRequirements().size(); i++){					
						
						//Check if they require the current skill, and what level HAAAAAAAAAAAAAAAAAAAAA
						if(SkillManager.getInstance().getSkill(s).getSkillRequirements().get(i).getName().equalsIgnoreCase(st.getName())
								&& SkillManager.getInstance().getSkill(s).getSkillRequirements().get(i).getLevel() == character.getDFOClass().getSkills().get(st.getName())){						
							
							//Yup. Don't touch me there. DAMN YOU KAKAROTTTTT
							return true;
						}
					}	
				}
			}
		}
		return false;
		
	}
	
	/**
	 * Handle to check if a skill has it's requirements fulfilled
	 * @param sk
	 * @return
	 */
	public static boolean requirementsMet(Skill sk){
		return sk.requirementsFulfilled(StaticResources.getCharacter().getDFOClass());
	}

	public static String getRequirementsAsString(Skill skill) {
		String ret = "Requires:";
		
		if(skill.getSkillRequirements() == null){
			return "";
		}
		
		for(SkillRequirement requirement : skill.getSkillRequirements()){
			ret += "\n"+requirement.getName()+" - "+requirement.getLevel();
		}
		return ret;
	}

	public static void maxLevelUp(Skill skill) {
		if(requirementsMet(skill)){
			try{
				while(true){
					levelUp(skill);
				}
			}
			catch(RequirementsNotMetException e){
				
			} catch (SPException e) {
				// TODO Auto-generated catch block
//				e.printStackTrace();
			} catch (MaxLevelException e) {
				// TODO Auto-generated catch block
//				e.printStackTrace();
			}
		}
	}

	public static void maxLevelDown(Skill skill) {
		try{
			while(true){
				levelDown(skill);
			}
		}
		catch (MinLevelException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		} catch (CurrentRequirementException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		
	}
	
	/**
	 * This methodology ignores requirements currently in place and just sets the level of a skill.
	 * It's like levelUp(), but without checking of boundaries or requirements
	 * @param skill
	 * @param level
	 */
	public static void setLevel(Skill skill, int level){
		while(StaticResources.getCharacter().getDFOClass().getSkills().get(skill.getName()) < level){
			//Increase level
			StaticResources.getCharacter().getDFOClass().getSkills().put(skill.getName(),
					StaticResources.getCharacter().getDFOClass().getSkills().get(skill.getName())+1);
			
			if(StaticResources.getCharacter().getDFOClass().getSkills().get(skill.getName()) == 0){
				StaticResources.getCharacter().spendSp(skill.getSpcost() + skill.getEntrycost());
			}
			else{
				StaticResources.getCharacter().spendSp(skill.getSpcost());
			}
			
		}
		
		//Reflect SP change in GUI
		StaticResources.getWisp().updateRemainingSP(StaticResources.getCharacter().getRemainingSP());
		
		StaticResources.getWisp().updateSkillOverview();
	}
	
	

}
