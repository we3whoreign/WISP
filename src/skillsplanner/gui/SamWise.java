package skillsplanner.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SamWise implements ActionListener{

	public static ActionEvent ClassSelection;
	public static ActionEvent TabSelection;
	public static ActionEvent SkillUp;
	public static ActionEvent SkillDown;
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String selection = e.getActionCommand();
		
		switch(selection){
			case Constants.CLOSE_OPERATION:
				break;
		
			case Constants.CLASS_SELECTION:
				break;
		
			case Constants.LEVEL_DOWN_OPERATION:
				break;
		
			case Constants.LEVEL_UP_OPERATION:
				break;
		}
		

	}

}
