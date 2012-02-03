package com.xmleditor.gui.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JList;

import com.xmleditor.gui.dialogs.CreateSkillDialog;

public class Rudra implements ActionListener {

	public static final String EDIT_SKILL = "EDITSKILL";
	public static final String NEW_SKILL = "NEWSKILL";
	
	public Rudra(){
		super();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(NEW_SKILL.equals(e.getActionCommand())){
			new CreateSkillDialog();
		}

	}

}
