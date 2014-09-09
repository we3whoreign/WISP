package org.we3whoreign.wisp.beans;

import javax.swing.JOptionPane;

/**
 * Represents the character object that encapsulates what the player would be.
 * Includes a DFOClass that can be reassigned as well as SP management
 */
public class DFOCharacter{
	private int remainingSP;

	private static final int totalSP = 7069;

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
	
	/**
	 * Adds specified amount back into remaining SP pool.
	 * @param amount
	 */
	public void refundSp(int amount){
		remainingSP = remainingSP + amount;
	}
	
	/**
	 * Resets SP to the original total
	 */
	public void resetSp(){
		remainingSP = totalSP;
	}
	
	public int getRemainingSP(){
		return this.remainingSP;
	}

	public boolean resetOK() {
		if(remainingSP == totalSP){
			return true;
		}
		else{
			return JOptionPane.showConfirmDialog(null, "Choose new class and reset skill points?") == JOptionPane.OK_OPTION;
		}
	}
	
}
