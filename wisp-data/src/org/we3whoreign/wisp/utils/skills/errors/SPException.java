package org.we3whoreign.wisp.utils.skills.errors;

public class SPException extends Exception{
	private static final long serialVersionUID = 2L;
	
	String error;
	
	public SPException(){
		super();
		error = "";
	}
	
	public SPException(String err){
		super(err);
		error = err;
	}
	
	public String getError(){
		return error;
	}
	
	
	
}
