package skillsplanner.utils;

import java.util.Comparator;

import skillsplanner.beans.Skill;

public class SkillComparator implements Comparator<Skill>{

	@Override
	public int compare(Skill sk0, Skill sk1) {
		
		if(Integer.compare(sk0.getRequiredlevel(), sk1.getRequiredlevel()) == 0){
			return sk0.getName().compareTo(sk1.getName());
		}
		
		return Integer.compare(sk0.getRequiredlevel(), sk1.getRequiredlevel());
	}
	
	public boolean equals(Skill sk0, Skill sk1){
		return sk0.getName().equals(sk1.getName());
	}
	
}