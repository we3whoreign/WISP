package com.xmleditor.util.skills;

import java.io.InputStream;
import java.util.HashMap;

import com.xmleditor.beans.Skill;
import com.xmleditor.io.IOHandler;
import com.xmleditor.util.jdom.SkillMapper;

public class SkillManager {

	private static SkillManager sm;
	private HashMap<String,Skill>skillList;
	
	public static SkillManager getInstance(){
		if(sm == null){
			sm = new SkillManager();
		}
		return sm;
	}
	
	public SkillManager(){
		skillList = new HashMap<String,Skill>();
		for(InputStream stream : IOHandler.getAllSkills()){
			Skill sk = SkillMapper.createSkillFromStream(stream);
			if(sk == null){
				System.out.println("Hmmm");
			}
			skillList.put(sk.getName(), sk);
		}
	}
	
	public Skill getSkill(String name){
		return skillList.get(name);
	}
	
	public HashMap<String,Skill> getAllSkills(){
		return skillList;
	}
}
