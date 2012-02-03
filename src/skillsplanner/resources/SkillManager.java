package skillsplanner.resources;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import skillsplanner.beans.Skill;
import skillsplanner.io.IOHandler;
import skillsplanner.utils.jdom.SkillMapper;

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
		Map<InputStream,String> map = IOHandler.getAllSkills();
		for(InputStream stream : map.keySet()){
			Skill sk = SkillMapper.createSkillFromStream(stream,map.get(stream));
			if(sk == null){
				System.out.println("Hmmm");
			}
			skillList.put(sk.getName(), sk);
			try {
				stream.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public Skill getSkill(String name){
		return skillList.get(name);
	}
	
	public HashMap<String,Skill> getAllSkills(){
		return skillList;
	}

	public List<Skill> fetchSubclassSkills(String name) {
		List<Skill> list = new LinkedList<Skill>();
		for(String skill : skillList.keySet()){
			Skill sk = skillList.get(skill);
			if(sk.getTree().equals(name)){
				list.add(sk);
			}
		}
		return list;
	}
}
