package skillsplanner.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import skillsplanner.Launcher;
import skillsplanner.utils.jdom.Handler;

/**
 * Listener that handles all of the skill changes and subtree selections. IE The Tab[1-4] buttons, general button, and all the clickable panels that comprise the skill selection
 * @author ryzngard
 *
 */
public class SamWise implements ActionListener{
	@Override
	public void actionPerformed(ActionEvent e) {
		String selection = e.getActionCommand();
		
		switch(selection){
			case Constants.CLASS_SELECTION:
				Object source = e.getSource();
				String name = ((JButton)source).getText();
				System.out.println("Selecting skills for "+name);
				break;
		
			case Constants.LEVEL_DOWN_OPERATION:
				
				break;
		
			case Constants.LEVEL_UP_OPERATION:
				break;
		}
		

	}

}
