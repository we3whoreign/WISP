import SkillsPlanner.Skills.*;
import java.util.Map;
import SkillsPlanner.GUI.*;

class main{

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
                createAndShowGUI();
		
	}
	
	private static void createAndShowGUI(){
		MainFrame frame = new MainFrame();
		
	}

}
