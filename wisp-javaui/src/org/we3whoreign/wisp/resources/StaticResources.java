package org.we3whoreign.wisp.resources;

import org.we3whoreign.wisp.beans.DFOCharacter;
import org.we3whoreign.wisp.gui.WISP;

public class StaticResources {

	private static WISP wisp;
	private static DFOCharacter character;
	
	public static WISP getWisp(){
		if(wisp == null){
			wisp = new WISP();
		}
		return wisp;
	}

	public static DFOCharacter getCharacter(){
		if(character == null){
			character = new DFOCharacter();
		}
		return character;
	}
	
	

}
