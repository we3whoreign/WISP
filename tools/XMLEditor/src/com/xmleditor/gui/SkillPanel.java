package com.xmleditor.gui;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.JPanel;

import com.xmleditor.gui.listeners.SkillMenuListener;

import skillsplanner.beans.Skill;

public class SkillPanel extends JPanel {
	Skill _skill;
	
	public SkillPanel(MouseListener skillMenuListener, Skill skill){
		_skill = skill;
		this.addMouseListener(skillMenuListener);
		initGUI();
	}
	
	private void initGUI(){
		this.setBackground(Color.black);
		JLabel label = new JLabel(_skill.getName());
		label.setAlignmentX(JLabel.CENTER);
		this.add(label);
		
	}

}
