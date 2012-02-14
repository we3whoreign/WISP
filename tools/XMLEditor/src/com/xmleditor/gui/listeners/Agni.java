package com.xmleditor.gui.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JList;

import com.xmleditor.gui.dialogs.EditClassDialog;

import skillsplanner.beans.DFOClass;

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
		
		switch(arg0.getActionCommand()){
		case NEW_CLASS:
			break;
		case EDIT_CLASS:
			editClass();
		}
		
	}
	
	private void editClass(){
		DFOClass dfoclass = (DFOClass) sourceList.getSelectedValue();
		EditClassDialog dialog = new EditClassDialog(dfoclass);
	}

}
