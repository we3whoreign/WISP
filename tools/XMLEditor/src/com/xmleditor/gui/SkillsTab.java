package com.xmleditor.gui;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.SpringLayout;

import skillsplanner.beans.Skill;
import skillsplanner.resources.SkillManager;
import skillsplanner.utils.ListUtils;

import com.xmleditor.gui.listeners.Rudra;
import com.xmleditor.gui.listeners.SkillMenuListener;


public class SkillsTab extends JPanel{

	public SkillsTab(){
		super();
		initGUI();
	}
	
	private void initGUI(){
		this.setLayout(new BorderLayout());
		//CENTER
//		List<Skill> list = ListUtils.getListFromMap(SkillManager.getInstance().getAllSkills());
//		Skill[] holder = new Skill[list.size()];
//		JList<Skill> listpane = new JList<Skill>(list.toArray(holder));
//		listpane.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
//		JScrollPane scroller = new JScrollPane(listpane);
//		this.add(scroller,BorderLayout.CENTER);
		
		//Whole new design of awesomeness
		JPanel centerPanel = new JPanel(new BorderLayout());
		JPanel leftMenu = new JPanel();
		leftMenu.setLayout(new BoxLayout(leftMenu,BoxLayout.Y_AXIS));
		JPanel skillList = new JPanel();
		JScrollPane scrollpanel = new JScrollPane(leftMenu);
		centerPanel.add(scrollpanel,BorderLayout.WEST);
		centerPanel.add(skillList,BorderLayout.CENTER);
		
		SkillMenuListener skillMenuListener = new SkillMenuListener(skillList);
		
		HashMap<String,ArrayList<Skill>> skillMap = new HashMap<String,ArrayList<Skill>>();
		
		//add skills to the skill menu 
		for(Skill sk : SkillManager.getInstance().getAllSkills().values()){
			if(!skillMap.containsKey(sk.getTree())){
				skillMap.put(sk.getTree(), new ArrayList<Skill>());
			}
			skillMap.get(sk.getTree()).add(sk);
		}
		
		//make the skill menu
		for(String header : skillMap.keySet()){
			JPanel contents = new JPanel();
			contents.setLayout(new BoxLayout(contents,BoxLayout.Y_AXIS));
			for(Skill sk : skillMap.get(header)){
				contents.add(new SkillPanel(skillMenuListener,sk));
			}
			HideableJPanel panel = new HideableJPanel(header,contents);
			leftMenu.add(panel);
		}
		
		this.add(centerPanel,BorderLayout.CENTER);
		
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
