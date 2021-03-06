package org.we3whoreign.wisp.utils.skills.errors;

import org.we3whoreign.wisp.beans.Skill;

public class MinLevelException extends Exception {
	private static final long serialVersionUID = 3L;
	String error;
	
	public MinLevelException(){
		super();
		error = "UNKNOWN";
	}
	
	public MinLevelException(Skill st){
		super(st.getName() + " is already at its minimum level.");
		error = st.getName() + " is already at its minimum level.";
	}
	
	public String getError(){
		return error;
	}

}
