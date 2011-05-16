package Skills;

public abstract class SkillsTemplate{

	// Certified String Name
	private String name;
	
	// Skill Level
	private int level;

	public String getName(){
		return name;
	}

	public void setName(String name){
		this.name = name;
	}

	public int getLevel(){
		return level;
	}

	public void setLevel(int level){
		this.level = level;
	}

}
