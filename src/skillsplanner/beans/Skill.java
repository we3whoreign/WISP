package skillsplanner.beans;

import java.util.ArrayList;
import java.util.LinkedList;

import skillsplanner.beans.SkillRequirement;

public class Skill implements Comparable<Skill>{
	//Indication of what tree the skill is in
		private String tree;

		// Certified String Name
		private String name;

		// Max Level
		private int maxlevel;

		// Minimum Level
		private int minlevel;
		
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

		private int requiredlevel;

		public Skill(){
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
		
		public ArrayList<String> getDynamic(int level){
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
			
			for(int i = 0; i < maxlevel; i++){
				for(String s : getDynamic(i)){
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
		/**
		 * Takes a DFOClass and returns if the class (character) currently
		 * has the skills required to add *this* one.
		 * @param DFOClass character
		 * @return boolean satisfied
		 */
		public boolean requirementsFulfilled(DFOClass character){
			boolean satisfied = true;

			if(skillRequirements != null){
				for(SkillRequirement req : skillRequirements){
					if(req.getLevel() > (character.getSkills()).get(req.getName())){
						satisfied = false;
					}
					
				}
			}
			return satisfied;
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
			return tree.substring(tree.lastIndexOf("/")+1);
		}
		
		public String getFileTree(){
			return tree;
		}
		
		public int getMinLevel() {
			return minlevel;
		}
		public void setMinLevel(int minlevel) {
			this.minlevel = minlevel;
		}
		
		public String toString(){
			return this.getName();
		}
		public int getRequiredlevel() {
			return this.requiredlevel;
		}
		
		public void setRequiredLevel(int requiredlevel){
			this.requiredlevel = requiredlevel;
		}
		@Override
		/**
		 * Simple comparison of the names of the skills
		 */
		public int compareTo(Skill arg0) {
			return getName().compareToIgnoreCase(arg0.getName());
		}
		
		public String getUniqueName() {
			return this.getTree() + "/" + this.getName();
		}
		public String getTreeGrandfather() {
			return tree.substring(0,tree.indexOf("/"));
		}
		public void removeSkillRequirement(String name) {
			for(SkillRequirement req : skillRequirements){
				if(req.getName().equalsIgnoreCase(name)){
					skillRequirements.remove(req);
					return;
				}
			}
			
		}

}
