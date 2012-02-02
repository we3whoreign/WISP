package skillsplanner.resources;

import skillsplanner.beans.DFOCharacter;
import skillsplanner.gui.WISP;

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
