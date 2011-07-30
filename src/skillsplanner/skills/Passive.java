package skillsplanner.skills;


/**
 * Passive skills
 */
public abstract class Passive extends SkillsTemplate {

	/**
	 * Perform actions that the passive does for each level
	 */
	abstract void perLevelEffect();
}
