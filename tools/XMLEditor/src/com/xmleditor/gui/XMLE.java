package com.xmleditor.gui;

import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

public class XMLE extends JFrame {

	public XMLE(){
		super();
		initGUI();
	}
	
	private void initGUI(){
		this.setLayout(new BorderLayout());
		JTabbedPane tabbedPane = new JTabbedPane();
		tabbedPane.addTab("Skills",makeSkillsTab());
		tabbedPane.addTab("Classes", makeClassesTab());
		this.add(tabbedPane,BorderLayout.CENTER);
		
		this.setSize(800,600);
		this.setVisible(true);
	}

	private Component makeClassesTab() {
		return new ClassesTab();
	}

	private Component makeSkillsTab() {
		return new SkillsTab();
	}
}
