package org.we3whoreign.wisp.resources;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.we3whoreign.wisp.beans.Skill;
import org.we3whoreign.wisp.io.IOHandler;
import org.we3whoreign.wisp.utils.jdom.SkillMapper;

/**
 * Manages the skills. Starts loading them in a separate thread and, when attempting to retrieve, either waits til the skill needed is loaded or skill loading is done. Should be thread safe for all methods
 * @author Andrew
 *
 */
public class SkillManager extends Observable{

	private static SkillManager sm;
	protected ConcurrentHashMap<String,Skill>skillList;
	private boolean loadFinished = false;
	
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
		skillList = new ConcurrentHashMap<String,Skill>();
		new Thread(){
			
			public void run(){
				long time = System.nanoTime();
				ConcurrentMap<InputStream,String> map = IOHandler.getAllSkills();
				List<SkillProducer> threadList = new ArrayList<SkillProducer>();
				for(final InputStream stream : map.keySet()){
						SkillProducer producer = new SkillProducer(stream,map.get(stream));
						threadList.add(producer);
						producer.start();
					
				}
				
				for(SkillProducer producer : threadList){
					try {
						producer.join();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				System.out.println("Skills loaded in: "+(System.nanoTime()-time));
				loadFinished = true;
			}
		}.start();
	}
	
	/**
	 * Gets a skill by name
	 * @param name
	 * @return
	 */
	public Skill getSkill(String name){
		boolean contains;
		contains = skillList.containsKey(name);
		while(!loadFinished && !contains){
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			contains = skillList.containsKey(name);
		}
		return skillList.get(name);
	}
	
	/**
	 * Gets the internal hashmap
	 * @return
	 */
	public ConcurrentHashMap<String,Skill> getAllSkills(){
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
	
	private class SkillProducer extends Thread{
		private List<Skill> skills;
		private InputStream stream;
		private String tree;
		
		public SkillProducer(InputStream stream, String tree){
			this.stream = stream;
			this.tree = tree;
		}
		
		public void run(){
			skills = SkillMapper.createSkillFromStream(stream, tree);
			for(Skill sk : skills){
				skillList.put(sk.getName(), sk);
//				System.out.println(sk.getName() +  " : " + tree);
			}
		}
	}
}
