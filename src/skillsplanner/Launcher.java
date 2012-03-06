package skillsplanner;

import java.io.File;
import java.net.URISyntaxException;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import skillsplanner.resources.ClassManager;
import skillsplanner.resources.SkillManager;
import skillsplanner.resources.StaticResources;

/**
 * a launcher wrapper for the GUI. Contains the sole instance of WISP and DFOCharacter
 * @author ryzngard
 */

public class Launcher {
	
	public Launcher() {
		//Start loading xmls before the GUI
		SkillManager.getInstance();
		ClassManager.getInstance();
		
		//block by trying to get something, which waits until load is finished or until it is found
		ClassManager.getClassByName("something that doesn't exist");
		
		//load the GUI
		createAndShowGUI();
	}

	public static void main(String args[]) throws Exception {
		/*
		 * SkillsLoader sk = new SkillsLoader(); Map<String,SkillsTemplate> m =
		 * sk.getClassObjects();
		 * 
		 * for(String s : m.keySet()){ System.out.println(s + ":"+m.get(s));
		 * System.out.println(m.get(s).getName()); }
		 */
		// Schedule a job for the event-dispatching thread:
		// creating and showing this application's GUI.
		/**
		 * javax.swing.SwingUtilities.invokeLater(new Runnable() { public void
		 * run() { createAndShowGUI(); } });
		 */
		javax.swing.SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				new Launcher();
			}
		});

	}

	private void createAndShowGUI() {
		try {
			UIManager.setLookAndFeel(UIManager
					.getCrossPlatformLookAndFeelClassName());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		StaticResources.getWisp();

	}
		
	

}
