package com.xmleditor.gui.listeners;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;

import com.xmleditor.gui.SkillPanel;
import com.xmleditor.gui.SkillsTab;

public class SkillMenuListener implements MouseListener{
	SkillsTab _skillPane;
	Border raised;
	Border lowered;
	Border empty;
	public SkillMenuListener(SkillsTab skillsTab){
		this._skillPane = skillsTab;
		raised = BorderFactory.createRaisedBevelBorder();
		lowered = BorderFactory.createLoweredBevelBorder();
		empty = BorderFactory.createEtchedBorder(Color.white, Color.DARK_GRAY);
		_skillPane.setBorder(empty);
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		_skillPane.selectSkill(((SkillPanel) arg0.getSource()).getSkill());
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		((JPanel) arg0.getSource()).setBorder(empty);
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		((JPanel) arg0.getSource()).setBorder(raised);
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		((JPanel) arg0.getSource()).setBorder(lowered);
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		((JPanel) arg0.getSource()).setBorder(raised);
	}

}
