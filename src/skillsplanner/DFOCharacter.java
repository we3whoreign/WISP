package skillsplanner;

import java.util.Observable;

import skillsplanner.classes.DFOClass;

/**
 * Represents the character object that encapsulates what the player would be.
 * Includes a DFOClass that can be reassigned as well as SP management
 */
public class DFOCharacter{
	private int remainingSP;

	// CHANGE LATER
	private static final int totalSP = 500;

	private DFOClass charclass;

	public DFOCharacter() {
		this.charclass = new DFOClass();
		this.remainingSP = totalSP;
	}

	public void setDFOClass(DFOClass charclass) {
		this.charclass = charclass;
	}

	public DFOClass getDFOClass() {
		return this.charclass;
	}
	
	/**
	 * Important to note that this does not check bounds so will go negative. The checking is done with the SkillHandler
	 * @param cost
	 */
	public void spendSp(int cost){
		remainingSP = remainingSP - cost;
	}
	
	public int getRemainingSP(){
		return this.remainingSP;
	}
	
}
