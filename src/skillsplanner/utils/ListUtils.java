package skillsplanner.utils;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import skillsplanner.beans.Skill;


public class ListUtils {
	
	public static void sortSkills(List<Skill> list){
		Collections.sort(list, new SkillComparator());
	}

	public static List<String> sortList(Set<String> set) {
		List<String> list = new ArrayList<String>();
		for(String k : set){
			list.add(k);
		}
		
		Collections.sort(list);
		
		return list;
	}

	public static List<Skill> listIntersection(HashMap<String, Integer> hashMap,
			List<Skill> reference) {
		List<Skill> list = new ArrayList<Skill>();
		for(Skill sk : reference){
			if(hashMap.containsKey(sk.getName())){
				list.add(sk);
			}
		}
		
		return list;
	}
}
