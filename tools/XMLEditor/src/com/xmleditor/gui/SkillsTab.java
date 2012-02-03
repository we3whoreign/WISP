package com.xmleditor.gui;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import skillsplanner.beans.Skill;
import skillsplanner.resources.SkillManager;
import skillsplanner.utils.ListUtils;

import com.xmleditor.gui.listeners.Rudra;


public class SkillsTab extends JPanel{

	public SkillsTab(){
		super();
		initGUI();
	}
	
	private void initGUI(){
		this.setLayout(new BorderLayout());
		//CENTER
		List<Skill> list = ListUtils.getListFromMap(SkillManager.getInstance().getAllSkills());
		Skill[] holder = new Skill[list.size()];
		JList<Skill> listpane = new JList<Skill>(list.toArray(holder));
		listpane.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane scroller = new JScrollPane(listpane);
		this.add(scroller,BorderLayout.CENTER);
		
		// BOTTOM PANEL
		JPanel buttonPanel = new JPanel();
		JButton newButton = new JButton("New Skill");
		Rudra rudra = new Rudra();
		newButton.addActionListener(rudra);
		newButton.setActionCommand(Rudra.NEW_SKILL);
		JButton editButton = new JButton("Edit Class");
		editButton.setActionCommand(Rudra.EDIT_SKILL);
		editButton.addActionListener(rudra);
		
		buttonPanel.add(newButton);
		buttonPanel.add(editButton);
		
		this.add(buttonPanel,BorderLayout.SOUTH);
	}
}
