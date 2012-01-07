package skillsplanner.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import skillsplanner.Launcher;
import skillsplanner.utils.jdom.Handler;

public class SamWise implements ActionListener{
	@Override
	public void actionPerformed(ActionEvent e) {
		String selection = e.getActionCommand();
		
		switch(selection){
			case Constants.CLOSE_OPERATION:
				System.exit(0);
				break;
		
			case Constants.CLASS_SELECTION:
				break;
		
			case Constants.LEVEL_DOWN_OPERATION:
				
				break;
		
			case Constants.LEVEL_UP_OPERATION:
				break;
				
			case Constants.SUBCLASS_SKILL_TREE_SELECTION:
				String selected = ((JButton) e.getSource()).getText();
				System.out.println("Getting skills for subclass "+selected);
				break;
		}
		

	}

}
