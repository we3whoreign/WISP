package skillsplanner.skills.errors;

import skillsplanner.skills.SkillsTemplate;

public class MinLevelException extends Exception {
	private static final long serialVersionUID = 3L;
	String error;
	
	public MinLevelException(){
		super();
		error = "UNKNOWN";
	}
	
	public MinLevelException(SkillsTemplate st){
		super(st.getName() + " is already at its minimum level.");
		error = st.getName() + " is already at its minimum level.";
	}
	
	public String getError(){
		return error;
	}

}
