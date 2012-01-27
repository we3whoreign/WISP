package skillsplanner.skills.errors;


public class CurrentRequirementException extends Exception {
	private static final long serialVersionUID = 2L;
	String error;
	
	public CurrentRequirementException(){
		super();
		error = "UNKNOWN";
	}
	
	public CurrentRequirementException(String name){
		super(name + "is currently a required skill.");
		error = name + "is currently a required skill.";
	}
	
	public String getError(){
		return error;
	}

}