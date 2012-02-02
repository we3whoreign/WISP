package skillsplanner.utils.classes;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import skillsplanner.beans.DFOClass;
import skillsplanner.beans.Skill;
import skillsplanner.io.IOHandler;
import skillsplanner.utils.jdom.ClassMapper;
import skillsplanner.utils.skills.SkillManager;


public class ClassManager {
	private HashMap<String,DFOClass> classes;
	private static ClassManager cm;
	
	public ClassManager(){
		classes = new HashMap<String,DFOClass>();
		Map<InputStream,String> map = IOHandler.getClassesWithParents();
		for(InputStream stream : map.keySet()){
			DFOClass dfoclass = ClassMapper.createClassFromStream(stream,map.get(stream));
			classes.put(dfoclass.getName(), dfoclass);
			try {
				stream.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static ClassManager getInstance(){
		if(cm == null){
			cm = new ClassManager();
		}
		
		return cm;
	}
	
	public DFOClass getDFOClass(String name){
		return classes.get(name);
	}
	
	public HashMap<String,DFOClass> getAllClasses(){
		return classes;
	}

	public static Hashtable<String, ArrayList<String>> listClassTable() {
		Hashtable<String, ArrayList<String>> table = new Hashtable<String, ArrayList<String>>();
		
		for(String name : getInstance().getAllClasses().keySet()){
			DFOClass dfoclass = getInstance().getDFOClass(name);
			if(!table.containsKey(dfoclass.getBaseClass())){
				table.put(dfoclass.getBaseClass(), new ArrayList<String>());
				
			}
			table.get(dfoclass.getBaseClass()).add(dfoclass.getName());
		}
		
		return table;
	}

	public List<String> getSubclasses(String path) {
		List<String> list = new ArrayList<String>();
		DFOClass cl = getDFOClass(path);
		for(String skill : cl.getSkills().keySet()){
			Skill sk = SkillManager.getInstance().getSkill(skill);
			if(!list.contains(sk.getTree())){
				list.add(sk.getTree());
			}
		}
		
		return list;
	}
}
