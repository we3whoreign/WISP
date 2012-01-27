package com.xmleditor.util.skills;

import java.util.ArrayList;

import skillsplanner.skills.SkillsTemplate;
import skillsplanner.utils.jdom.Handler;
import skillsplanner.utils.jdom.SkillLoader;

public class SkillManager {

	private static SkillManager sm;
	
	public static SkillManager getInstance(){
		if(sm == null){
			sm = new SkillManager();
		}
		return sm;
	}
	
	public SkillManager(){
		try {
			Handler.getSkillLoader().loadSkills();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public SkillsTemplate getSkill(String name){
		for(String key : Handler.getSkillLoader().getSkillList().keySet()){
			if(key.equals(name)){
				return Handler.getSkillLoader().getSkill(name);
			}
		}
		
		return null;
	}
	
	public ArrayList<SkillsTemplate> getAllSkills(){
		ArrayList<SkillsTemplate> list = new ArrayList<SkillsTemplate>();
		
		for(String key : Handler.getSkillLoader().getSkillList().keySet()){
			list.add(Handler.getSkillLoader().getSkillList().get(key));
		}
		
		return list;
	}
}
