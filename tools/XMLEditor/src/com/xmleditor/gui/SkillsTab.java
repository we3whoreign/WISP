package com.xmleditor.gui;

import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import skillsplanner.classes.DFOClass;
import skillsplanner.skills.SkillsTemplate;

import com.xmleditor.gui.listeners.Agni;
import com.xmleditor.gui.listeners.Rudra;
import com.xmleditor.util.skills.SkillManager;

public class SkillsTab extends JPanel{

	public SkillsTab(){
		super();
		initGUI();
	}
	
	private void initGUI(){
		this.setLayout(new BorderLayout());
		//CENTER
		ArrayList<SkillsTemplate> list = SkillManager.getInstance().getAllSkills();
		SkillsTemplate[] holder = new SkillsTemplate[list.size()];
		JList<SkillsTemplate> listpane = new JList<SkillsTemplate>(list.toArray(holder));
		listpane.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane scroller = new JScrollPane(listpane);
		this.add(scroller,BorderLayout.CENTER);
		
		// BOTTOM PANEL
		JPanel buttonPanel = new JPanel();
		JButton newButton = new JButton("New Skill");
		Rudra rudra = new Rudra(listpane);
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
