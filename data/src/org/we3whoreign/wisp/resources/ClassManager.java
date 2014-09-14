package org.we3whoreign.wisp.resources;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.concurrent.ConcurrentHashMap;

import org.we3whoreign.wisp.beans.DFOClass;
import org.we3whoreign.wisp.beans.Skill;
import org.we3whoreign.wisp.io.IOHandler;
import org.we3whoreign.wisp.utils.ListUtils;
import org.we3whoreign.wisp.utils.StringUtils;
import org.we3whoreign.wisp.utils.jdom.ClassMapper;


/**
 * Manager of all the DFOClass files. Uses a concurrent hashmap for thread safety.
 * @author Andrew
 *
 */
public class ClassManager extends Observable{
	private java.util.concurrent.ConcurrentHashMap<String,DFOClass> classes;
	private static ClassManager cm;
	private boolean finishedLoading = false;
	
	public ClassManager(){
		classes = new java.util.concurrent.ConcurrentHashMap<String,DFOClass>();
		new Thread(){
			public void run(){
				Map<InputStream,String> map = IOHandler.getClassesWithParents();
				for(InputStream stream : map.keySet()){
					DFOClass dfoclass = ClassMapper.createClassFromStream(stream,map.get(stream));
					classes.put(StringUtils.toFileName(dfoclass.getUniqueName()), dfoclass);
					try {
						stream.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				System.out.println("Classes loaded");
				finishedLoading = true;
			}
		}.start();
		
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
		name = StringUtils.toFileName(name);
		boolean contains;
		contains = classes.containsKey(name);
		while(!finishedLoading && !contains){
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			contains = classes.containsKey(name);
		}
		return classes.get(name);
	}
	
	public void addClass(DFOClass c){
		if(classes.containsKey(c.getUniqueName())){
			classes.remove(StringUtils.toFileName(c.getUniqueName()));
		}
		classes.put(StringUtils.toFileName(c.getUniqueName()), c);
		setChanged();
		notifyObservers();
	}
	
	public ConcurrentHashMap<String, DFOClass> getAllClasses(){
		
		//block off til done loading
		while(!finishedLoading){}
		return classes;
	}

	public static Hashtable<String, ArrayList<String>> listClassTable() {
		return IOHandler.createClassTable();
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
