package SkillsPlanner;

import SkillsPlanner.Skills.*;
import java.util.Map;
import SkillsPlanner.GUI.*;

/**
* a launcher wrapper for the GUI
*/

public class Launcher{
	/**
	* JFrame for the program
	*/
	public static MainFrame frame;

	public Launcher(){
		createAndShowGUI();
	}

	public static void main(String args[]) throws Exception{
		/**
		SkillsLoader sk = new SkillsLoader();
		Map<String,SkillsTemplate> m = sk.getClassObjects();
		
		for(String s : m.keySet()){
			System.out.println(s + ":"+m.get(s));
			System.out.println(m.get(s).getName());
		}
		*/
		//Schedule a job for the event-dispatching thread:
		//creating and showing this application's GUI.
		/**javax.swing.SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					createAndShowGUI();
                		}
                });*/
		Launcher launcher = new Launcher();
                launcher.createAndShowGUI();
		
	}
	
	private void createAndShowGUI(){
		frame = new MainFrame();
		
	}


}
