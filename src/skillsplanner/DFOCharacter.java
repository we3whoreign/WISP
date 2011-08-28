package skillsplanner;

import java.util.Observable;

import skillsplanner.classes.DFOClass;

/**
 * Represents the character object that encapsulates what the player would be.
 * Includes a class and stats, as well as skills and sp.
 */
public class DFOCharacter extends Observable {
	private int remainingSP;

	// CHANGE LATER
	private static final int totalSP = 500;

	private DFOClass charclass;

	public DFOCharacter() {
		this.charclass = null;
	}

	public void setDFOClass(DFOClass charclass) {
		this.charclass = charclass;
	}

	public DFOClass getDFOClass() {
		return this.charclass;
	}
}
