package skillsplanner.skills;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Framework for making skills
 */
public class SkillsTemplate {
	
	//Indication of what tree the skill is in
	private String tree;

	// Certified String Name
	private String name;

	// Skill Level
	private int level;

	// Max Level
	private int maxlevel;

	// Cast time
	private double casttime;

	// Animation frame time
	private double animationframe;
	
	// SP cost
	private int spcost;
	
	private boolean passive;
	
	//Entry Fee (CODYFLAG)
	private int entrycost;
	
	//Linked list which holds all prerequisite skills as SkillRequirements
	private LinkedList<SkillRequirement> skillRequirements;
	
	// List of level dependent values
	private ArrayList<ArrayList<String>> dynamic;

	public SkillsTemplate(){
		dynamic = new ArrayList<ArrayList<String>>();
		
		passive = false;
	}
	/**
	 * Get the name to be displayed for the skill
	 */
	public String getName() {
		return name;
	}

	/**
	 * Set the name to be displayed for the skill
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Get the current level of the skill
	 */
	public int getLevel() {
		return level;
	}

	/**
	 * Set the current level of the skill
	 */
	public void setLevel(int level) {
		this.level = level;
	}

	/**
	 * Increase the level by 1
	 */
	public void levelUp() {
		this.level++;
	}

	/**
	 * Set the max level for this skill
	 */
	public void setMaxLevel(int maxlevel) {
		this.maxlevel = maxlevel;
	}

	/**
	 * Get the max level
	 */
	public int getMaxLevel() {
		return this.maxlevel;
	}

	public void setCastTime(double casttime) {
		this.casttime = casttime;
	}

	public double getCastTime() {
		return this.casttime;
	}

	public void setAnimationFrameTime(double frametime) {
		this.animationframe = frametime;
	}

	public double getAnimationFrameTime() {
		return this.animationframe;
	}
	
	public ArrayList<String> getDynamic(){
		return dynamic.get(level);
	}
	
	public void addDynamic(ArrayList<String> d){
		dynamic.add(d);
		// increase max level each time a new level is added
		maxlevel++;
	}
	
	/**
	 * This does a print of all information for each level
	 */
	public void dumpLevelInfo() {
		int tmplevel = level;
		
		for(int i = 0; i < maxlevel; i++){
			level = i;
			for(String s : getDynamic()){
				System.out.println(s);
			}
			System.out.println("--------------------------------------");
		}
		
	}
	public int getSpcost() {
		return spcost;
	}
	public LinkedList<SkillRequirement> getSkillRequirements(){
		return skillRequirements;
	}
	/*
	 * This takes in a String an an int, converts them to a SkillRequirement, and
	 * then adds them to the stored LinkedList of SkillRequirements.
	 */
	public boolean addSkillRequirement(String skillName, int skillLevel ){
		if(skillRequirements == null){
			skillRequirements = new LinkedList<SkillRequirement>();
		}
		
		SkillRequirement newReq = new SkillRequirement(skillName,skillLevel);
		
		return(skillRequirements.add(newReq));
	}
	public void setSpcost(int spcost) {
		this.spcost = spcost;
	}
	public boolean isPassive() {
		return passive;
	}
	public void setPassive(boolean passive) {
		this.passive = passive;
	}
	public int getEntrycost() {
		return entrycost;
	}
	public void setEntrycost(int entrycost) {
		this.entrycost = entrycost;
	}
	public void setTree(String tree) {
		this.tree = tree;
	}
	public String getTree() {
		return tree;
	}
	
	public int hashCode(){
		return this.getName().hashCode();
	}
	
}
