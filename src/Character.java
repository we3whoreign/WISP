
import SkillsPlanner.Classes.DFOClass;
/**
* Represents the character object that encapsulates what the player would be. 
* Includes a class and stats, as well as skills and sp.
*/
public class Character{
	private int remainingSP;
	
	//CHANGE LATER
	private final int totalSP = 500;
	
	private DFOClass charclass;
	
	public Character(){
		this.charclass = null;
	}
	
	public void setDFOClass(DFOClass charclass){
		this.charclass = charclass;
	}
	
	public DFOClass getDFOClass(){
		return this.charclass;
	}
}
