package src.skillsplanner;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;


/**
 * a launcher wrapper for the GUI
 */

public class Launcher{
	public Launcher() {
		createAndShowGUI();
	}

	public static void main(String args[]) throws Exception {
		/**
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
		new Launcher();

	}

	private void createAndShowGUI() {
		try {
			UIManager.setLookAndFeel(
			        UIManager.getCrossPlatformLookAndFeelClassName());
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

		//frame = new MainFrame();

	}

}
