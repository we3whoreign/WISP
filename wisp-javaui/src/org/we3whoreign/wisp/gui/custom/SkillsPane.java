package org.we3whoreign.wisp.gui.custom;

import java.awt.Component;
import java.util.HashMap;
import java.util.LinkedList;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import org.we3whoreign.wisp.beans.Skill;
import org.we3whoreign.wisp.beans.SkillRequirement;

/**
 * Panel that will hold ClickableSkillPanels and provide a reference for each one
 * @author ryzngard
 *
 */
@SuppressWarnings("serial")
public class SkillsPane extends JPanel {
	HashMap<String,ClickableSkillPanel> skillList;
	
	public SkillsPane(){
		super();
		skillList = new HashMap<String,ClickableSkillPanel>();
		this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
	}

	/**
	 * Add a skill in the hashmap and to the panel
	 * @param sk
	 */
	public void addSkill(Skill sk){
		ClickableSkillPanel csp = new ClickableSkillPanel(sk);
		skillList.put(sk.getName(), csp);
		this.add(csp);
	}

	/**
	 * Removes all the components and updates the hashmap to be empty;
	 */
	public void wipeSkills() {
		skillList = new HashMap<String,ClickableSkillPanel>();
		for(Component c : this.getComponents()){
			this.remove(c);
		}
		
	}

	public void notifyRequiredSkills(
			LinkedList<SkillRequirement> skillRequirements) {
		for(SkillRequirement requirement : skillRequirements){
			
			//Fixed so that if the skill is not in same tree no null pointer is thrown
			ClickableSkillPanel panel = this.skillList.get(requirement.getName());
			if(panel == null){
				continue;
			}
			panel.skillIsRequired(requirement.getLevel());
			panel.repaint();
		}
		
	}

	public void notifyRequiredSkillsofLeave(
			LinkedList<SkillRequirement> skillRequirements) {
		for(SkillRequirement requirement : skillRequirements){
			//Fixed so that if the skill is not in same tree no null pointer is thrown
			ClickableSkillPanel panel = this.skillList.get(requirement.getName());
			if(panel == null){
				continue;
			}
			panel.skillIsRequired(0);
			panel.repaint();
		}
		
	}
}
