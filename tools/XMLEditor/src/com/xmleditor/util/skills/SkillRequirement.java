package com.xmleditor.util.skills;

public class SkillRequirement {

	//Name of skill required
	private String name;
	
	//Level the required skill needs to be
	private int level;
	
	public SkillRequirement(){
		setName("");
		setLevel(0);
	}
	
	public SkillRequirement(String skillName, int skillLevel){
		setName(skillName);
		setLevel(skillLevel);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}
	
}
