package skillsplanner.skills.errors;

import skillsplanner.beans.Skill;


public class MaxLevelException extends Exception {
	private static final long serialVersionUID = 3L;
	String error;
	
	public MaxLevelException(){
		super();
		error = "UNKNOWN";
	}
	
	public MaxLevelException(Skill st){
		super(st.getName() + " cannot exceed "+st.getMaxLevel());
		error = st.getName() +" cannot exceed "+st.getMaxLevel();
	}
	
	public String getError(){
		return error;
	}

}
