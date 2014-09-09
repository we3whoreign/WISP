package com.xmleditor.gui.dialogs;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import org.we3whoreign.wisp.beans.Skill;
import org.we3whoreign.wisp.resources.SkillManager;

import com.xmleditor.gui.custom.JSuggestField;

import net.miginfocom.swing.MigLayout;

public class AddRequiredDialog extends JFrame{
	public static final String SAVE = "REQUIRED SAVE";
	JSuggestField suggestField;
	JTextField level;

	public AddRequiredDialog(ActionListener listener){
		Container contentPane = this.getContentPane();
		contentPane.setLayout(new MigLayout("wrap 2"));
		
		JButton save = new JButton("Save");
		save.addActionListener(listener);
		save.setActionCommand(this.SAVE);
		
		Vector<String> data = new Vector<String>();
		
		for(Skill sk : SkillManager.getInstance().getAllSkills().values()){
			data.add(sk.getName());
		}
		suggestField = new JSuggestField(this,data);
		suggestField.setPreferredSize(new Dimension(200,20));
		JLabel nameLabel = new JLabel("Name of skill:");
		JLabel levelLabel = new JLabel("Level: ");
		level = new JTextField();
		level.setPreferredSize(new Dimension(200,20));
		contentPane.add(nameLabel);
		contentPane.add(suggestField);
		contentPane.add(levelLabel);
		contentPane.add(level);
		contentPane.add(save);
	
		this.setSize(200,200);
		this.setVisible(true);
	}
	
	public String getSkillName(){
		return suggestField.getText();
	}
	
	public int getLevel(){
		return Integer.parseInt(level.getText());
	}
	
}
