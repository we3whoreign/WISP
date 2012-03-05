package skillsplanner.resources;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Observable;

import skillsplanner.beans.Skill;
import skillsplanner.io.IOHandler;
import skillsplanner.utils.jdom.SkillMapper;

public class SkillManager extends Observable{

	private static SkillManager sm;
	private HashMap<String,Skill>skillList;
	
	/**
	 * Returns the singleton of this class
	 * @return
	 */
	public static SkillManager getInstance(){
		if(sm == null){
			sm = new SkillManager();
		}
		return sm;
	}
	
	/**
	 * Initializes the internal hashmap by reading the appropriate xml files
	 */
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
	
	/**
	 * Gets a skill by name
	 * @param name
	 * @return
	 */
	public Skill getSkill(String name){
		return skillList.get(name);
	}
	
	/**
	 * Gets the internal hashmap
	 * @return
	 */
	public HashMap<String,Skill> getAllSkills(){
		return skillList;
	}

	/**
	 * Fetch all the subclasses where the skill tree is the same as the name passed in
	 * @param name
	 * @return
	 */
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

	/**
	 * Adds a skill to the internal hashmap, or updates it if already contained
	 * @param skill
	 */
	public void addSkill(Skill skill) {
		if(skillList.containsKey(skill.getName())){
			this.skillList.remove(skill.getName());
		}
		this.skillList.put(skill.getName(),skill);
		setChanged();
		notifyObservers();
		
	}
}
