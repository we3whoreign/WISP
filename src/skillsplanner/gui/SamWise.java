package skillsplanner.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import skillsplanner.beans.Skill;
import skillsplanner.resources.StaticResources;
import skillsplanner.utils.ListUtils;
import skillsplanner.utils.StringUtils;
import skillsplanner.utils.jdom.Handler;
import skillsplanner.utils.skills.SkillManager;

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
			case Constants.SUBCLASS_SKILL_TREE_SELECTION:
				Object source = e.getSource();
				String name = ((JButton)source).getText();
				name = StringUtils.toFileName(name);
				List<Skill> list = ListUtils.listIntersection(StaticResources.getCharacter().getDFOClass().getSkills(),SkillManager.getInstance().fetchSubclassSkills(name));
			
				//Wipe Skills
				StaticResources.getWisp().wipeSkills();
				
				// sort the skills
				ListUtils.sortSkills(list);
				
				//CREATE JPANELS FOR THE SKILLS
				for(Skill st : list){
					StaticResources.getWisp().addSkill(st);
				}
				
				StaticResources.getWisp().validate();
				StaticResources.getWisp().repaint();
				//Launcher.wisp.update(Launcher.wisp.getGraphics());
				
				break;
		
			case Constants.LEVEL_DOWN_OPERATION:
				
				break;
		
			case Constants.LEVEL_UP_OPERATION:
				break;
		}
		

	}

}
