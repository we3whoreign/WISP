package com.xmleditor.gui.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JList;

/**
 * Action listener for the ClassesTab
 * @author ryzngard
 *
 */
public class Agni implements ActionListener{

	public static final String NEW_CLASS = "NEWCLASS";
	public static final String EDIT_CLASS = "EDITCLASS";
	private JList sourceList;
	
	public Agni(JList list){
		super();
		sourceList = list;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
