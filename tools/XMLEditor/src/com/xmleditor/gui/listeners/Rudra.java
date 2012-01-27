package com.xmleditor.gui.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JList;

public class Rudra implements ActionListener {

	public static final String EDIT_SKILL = "EDITSKILL";
	public static final String NEW_SKILL = "NEWSKILL";
	private JList sourceList;
	
	public Rudra(JList list){
		super();
		sourceList = list;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

}
