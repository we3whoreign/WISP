package SkillsPlanner.Classes;

import java.util.HashMap;

import SkillsPlanner.Skills.SkillsTemplate;

public abstract class DFOClass {

	String description;

	abstract HashMap<String, SkillsTemplate> loadSkills();

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
