package org.we3whoreign.wisp.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import org.we3whoreign.wisp.beans.Skill;
import org.we3whoreign.wisp.resources.SkillManager;
import org.we3whoreign.wisp.resources.StaticResources;
import org.we3whoreign.wisp.utils.ListUtils;
import org.we3whoreign.wisp.utils.StringUtils;

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
			case Constants.CLOSE_OPERATION:
				StaticResources.getWisp().setVisible(false);
				StaticResources.getWisp().dispose();
		}
		

	}

}
