package skillsplanner.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
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

	public static List<Skill> listIntersection(Map<String, Integer> hashMap,
			List<Skill> reference) {
		List<Skill> list = new ArrayList<Skill>();
		for(Skill sk : reference){
			if(hashMap.containsKey(sk.getName())){
				list.add(sk);
			}
		}
		
		return list;
	}

	public static <E> List<E> getListFromMap(Map<String, E> map) {
		List<E> list = new ArrayList<E>();
		for(E obj : map.values()){
			list.add(obj);
		}
		
		return list;
	}

	public static void subClassListSort(List<String> list) {
		Collections.sort(list, new SubclassComparator());
		
	}

	public static Skill[] splitDuplicateSkills(Skill[] skillList,
			Set<String> keySet) {
		List<Skill> list = new ArrayList<Skill>();
		for(Skill sk : skillList){
			if(keySet.contains(sk.getName())){
				list.add(sk);
			}
		}
		
		Skill[] array = new Skill[list.size()];
		return list.toArray(array);
		
	}
}
