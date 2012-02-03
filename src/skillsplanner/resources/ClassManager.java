package skillsplanner.resources;

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
import skillsplanner.utils.ListUtils;
import skillsplanner.utils.jdom.ClassMapper;


public class ClassManager {
	private HashMap<String,DFOClass> classes;
	private static ClassManager cm;
	
	public ClassManager(){
		classes = new HashMap<String,DFOClass>();
		Map<InputStream,String> map = IOHandler.getClassesWithParents();
		for(InputStream stream : map.keySet()){
			DFOClass dfoclass = ClassMapper.createClassFromStream(stream,map.get(stream));
			classes.put(dfoclass.getUniqueName(), dfoclass);
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
	
	/**
	 * Get dfoclass with the key of name. Puts name to lower case in order to reduce possible error of case comparisons
	 * @param name
	 * @return
	 */
	public DFOClass getDFOClass(String name){
		name = name.toLowerCase();
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
			table.get(dfoclass.getBaseClass()).add(dfoclass.getUniqueName());
		}
		
		return table;
	}

	/**
	 * Use the available skills of a class to show the possible subclasses.
	 * @param path
	 * @return
	 */
	public List<String> getSubclasses(String path) {
		List<String> list = new ArrayList<String>();
		DFOClass cl = getDFOClass(path);
		for(String skill : cl.getSkills().keySet()){
			Skill sk = SkillManager.getInstance().getSkill(skill);
			if(!list.contains(sk.getTree())){
				list.add(sk.getTree());
			}
		}
		
		ListUtils.subClassListSort(list);
		return list;
	}

	/**
	 * Static reference to get a class by name. The name must directly be mapped to the keyset in the hashtable
	 * @param path
	 * @return
	 */
	public static DFOClass getClassByName(String path) {
		ClassManager cm = getInstance();
		return cm.getDFOClass(path);
	}
}
