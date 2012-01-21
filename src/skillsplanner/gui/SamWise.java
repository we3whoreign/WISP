package skillsplanner.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import skillsplanner.Launcher;
import skillsplanner.skills.SkillsTemplate;
import skillsplanner.utils.StringUtils;
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
		System.out.println(selection);
		
		switch(selection){
			case Constants.SUBCLASS_SKILL_TREE_SELECTION:
				Object source = e.getSource();
				String name = ((JButton)source).getText();
				name = StringUtils.toFileName(name);
				System.out.println("Selecting skills for "+name);
				List<SkillsTemplate> list = Handler.getSkillLoader().fetchSubclassSkills(name);
			
				//Wipe Skills
				Launcher.wisp.wipeSkills();
				
				//CREATE JPANELS FOR THE SKILLS
				for(SkillsTemplate st : list){
					Launcher.wisp.addSkill(st);
				}
				
				Launcher.wisp.validate();
				Launcher.wisp.update(Launcher.wisp.getGraphics());
				
				break;
		
			case Constants.LEVEL_DOWN_OPERATION:
				
				break;
		
			case Constants.LEVEL_UP_OPERATION:
				break;
		}
		

	}

}
