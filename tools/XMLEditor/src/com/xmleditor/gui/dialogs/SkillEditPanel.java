package com.xmleditor.gui.dialogs;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.SpringLayout;

import com.xmleditor.gui.layouttools.SpringLayoutTool;
import com.xmleditor.io.XMLWriter;

import skillsplanner.beans.Skill;
import skillsplanner.resources.SkillManager;

public class SkillEditPanel extends JPanel implements ActionListener{
	
	private Skill _skill;
	private final int TEXT_HEIGHT = 50;
	private final int TEXT_WIDTH = 250;
	final JTextPane name = new JTextPane();
	final JTextPane level = new JTextPane();
	final JTextPane spCost = new JTextPane();
	final JCheckBox levelOne = new JCheckBox("Start at 1");
	final JTextPane requiredLevel = new JTextPane();
	
	public SkillEditPanel(Skill skill){
		if(skill != null){
			this._skill = skill;
			initGUI();
			this.setBackground(Color.black);
		}
		else{
			this.setBackground(Color.BLUE);
		}
	}
	
	private void initGUI(){
		name.setPreferredSize(new Dimension(TEXT_WIDTH, TEXT_HEIGHT));
		level.setPreferredSize(new Dimension(TEXT_WIDTH, TEXT_HEIGHT));
		spCost.setPreferredSize(new Dimension(TEXT_WIDTH, TEXT_HEIGHT));
		requiredLevel.setPreferredSize(new Dimension(TEXT_WIDTH, TEXT_HEIGHT));
			
		name.setText(_skill.getName());
		level.setText(String.valueOf(_skill.getMaxLevel()));
		spCost.setText(String.valueOf(_skill.getSpcost()));
		requiredLevel.setText(String.valueOf(_skill.getRequiredlevel()));
		
		this.setOpaque(true);
		JPanel container = new JPanel();
		JPanel namePanel = new JPanel();
		JPanel levelPanel = new JPanel();
		JPanel spCostPanel = new JPanel();
		JPanel requiredLevelPanel = new JPanel();
		JPanel startAtOnePanel = new JPanel();
	
		container.setLayout(new BoxLayout(container,BoxLayout.Y_AXIS));
		
		addPanelLayout(namePanel, new JLabel("Name"), name);
		addPanelLayout(levelPanel, new JLabel("Level"), level);
		addPanelLayout(spCostPanel, new JLabel("SP Cost"), spCost);
		addPanelLayout(startAtOnePanel,levelOne);
		addPanelLayout(requiredLevelPanel, new JLabel("Minimum Level to Obtain:"),requiredLevel);
		
		container.add(namePanel);
		container.add(levelPanel);
		container.add(spCostPanel);
		container.add(requiredLevelPanel);
		container.add(startAtOnePanel);
		
		this.setLayout(new BorderLayout());
		
		this.add(container,BorderLayout.CENTER);
		this.add(generateButtons(),BorderLayout.SOUTH);
	}
	
	private void addPanelLayout(JPanel panel, JLabel label, Component component){
		FlowLayout layout = new FlowLayout();
		layout.setAlignment(FlowLayout.LEFT);
		panel.setLayout(layout);
		
		panel.add(label);
		panel.add(component);
	}
	
	private void addPanelLayout(JPanel panel, Component component){
		FlowLayout layout = new FlowLayout();
		layout.setAlignment(FlowLayout.LEFT);
		panel.setLayout(layout);
		
		panel.add(component);
	}
	
	private JPanel generateButtons(){
		JPanel panel = new JPanel();
		JButton save = new JButton("Save");
		JButton cancel = new JButton("cancel");
		
		save.addActionListener(this);
		cancel.addActionListener(this);
		
		save.setActionCommand("SAVE");
		cancel.setActionCommand("CANCEL");
		
		panel.add(save);
		panel.add(cancel);
		
		return panel;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
		switch(arg0.getActionCommand()){
		case "SAVE":
			System.out.println("Save");
			if(isValidData()){
				this._skill.setName(name.getText());
				this._skill.setMaxLevel(Integer.parseInt(level.getText()));
				this._skill.setSpcost(Integer.parseInt(spCost.getText()));
				this._skill.setMinLevel((levelOne.isSelected()) ? 1 : 0);
				this._skill.setRequiredLevel(Integer.parseInt(requiredLevel.getText()));
				XMLWriter.getInstance().skillToXML(this._skill,this._skill.getFileTree());
			}
			break;
		case "CANCEL":
			System.out.println("Cancel");
			this.setVisible(false);
			break;
		}
	}
	
	private boolean isValidData(){
		try{
			Integer.parseInt(level.getText());
			Integer.parseInt(spCost.getText());
		}
		catch(Exception e){
			return false;
		}
		return true;
	}

}
