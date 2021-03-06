package org.we3whoreign.wisp.utils.skills.errors;

import org.we3whoreign.wisp.beans.Skill;

/**
 * Simple exception class to represent when skill requirements are not met.
 * @author Ryzngard
 *
 */
public class RequirementsNotMetException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String error;
	
	public RequirementsNotMetException(){
		super();
		error = "Unknown";
	}
	
	public RequirementsNotMetException(String err){
		super(err);
		error = err;
	}
	
	public RequirementsNotMetException(Skill st){
		error = "Requirements not met for "+st.getName();
	}
	
	public String getError(){
		return error;
	}
}
