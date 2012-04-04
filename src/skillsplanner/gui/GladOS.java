package skillsplanner.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import skillsplanner.resources.BuildManager;
import skillsplanner.resources.StaticResources;

/**
 * Listener for the menu of WISP
 * @author ryzngard
 *
 */
public class GladOS implements ActionListener{
	
	public static final String NEW_BUILD = "NEW BUILD";
	public static final String OPEN_BUILD = "OPEN BUILD";
	public static final String SAVE_BUILD = "SAVE BUILD";

	@Override
	public void actionPerformed(ActionEvent arg0) {
		switch(arg0.getActionCommand()){
		case NEW_BUILD:
			JOptionPane.showMessageDialog(StaticResources.getWisp(), "New Build not implemented yet, please just click another class to start over");
			break;
		case OPEN_BUILD:
			JOptionPane.showMessageDialog(StaticResources.getWisp(), "Open Build not implemented yet");
			break;
		case SAVE_BUILD:
			if(BuildManager.saveToXML(StaticResources.getCharacter().getDFOClass())){
				JOptionPane.showMessageDialog(StaticResources.getWisp(), "Save Successful");
			}
			else{
				JOptionPane.showMessageDialog(StaticResources.getWisp(), "Save Failed");
			}
			break;
		}
		
	}

}
