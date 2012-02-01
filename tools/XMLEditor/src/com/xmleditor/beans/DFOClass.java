package com.xmleditor.beans;

import java.util.HashMap;

public class DFOClass {
	private HashMap<String,Integer> skillList;
	private String description;
	private String name;
	
	public DFOClass(){
		skillList = new HashMap<String,Integer>();
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public String getName(){
		return this.name;
	}
	
	public void setDescription(String description){
		this.description = description;
	}
	
	public String getDescription(){
		return this.description;
	}
	
	public void addSkill(Skill sk){
		skillList.put(sk.getName(), sk.getMinLevel());
	}

	public HashMap<String,Integer> getSkills() {
		return skillList;
	}
	
	@Override
	public String toString(){
		return this.name;
	}

}
