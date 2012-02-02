package skillsplanner.utils;

import java.util.Comparator;

import skillsplanner.beans.Skill;

public class SkillComparator implements Comparator{

	@Override
	public int compare(Object arg0, Object arg1) {
		Skill sk0 = (Skill) arg0;
		Skill sk1 = (Skill) arg1;
		
		if(Integer.compare(sk0.getRequiredlevel(), sk1.getRequiredlevel()) == 0){
			return sk0.getName().compareTo(sk1.getName());
		}
		
		return Integer.compare(sk0.getRequiredlevel(), sk1.getRequiredlevel());
	}
	
	public boolean equals(Skill sk0, Skill sk1){
		return sk0.getName().equals(sk1.getName());
	}
	
}