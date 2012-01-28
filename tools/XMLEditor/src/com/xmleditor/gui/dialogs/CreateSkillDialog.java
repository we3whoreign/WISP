package com.xmleditor.gui.dialogs;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import com.xmleditor.util.classes.ClassManager;

import skillsplanner.classes.DFOClass;
import skillsplanner.skills.SkillsTemplate;

public class CreateSkillDialog extends JFrame implements ActionListener{
	
	private final String NEXT = "NEXT";
	private final String PREVIOUS = "PREVIOUS";
	private final String FINISH = "FINISH";
	
	private SkillsTemplate st;
	
	private JPanel cards;
	
	public CreateSkillDialog(){
		super();
		initGUI();
		st = new SkillsTemplate();
	}
	
	private void initGUI(){
		this.setLayout(new BorderLayout());
		cards = new JPanel(new CardLayout());
		cards.add(getFirstDialog());
		
		this.add(cards,BorderLayout.CENTER);
	}
	
	/**
	 * First dialog gets the name, class, and subclass of the skill
	 * @return
	 */
	private JPanel getFirstDialog(){
		JPanel firstpanel = new JPanel();
	
		firstpanel.setLayout(new BoxLayout(firstpanel,BoxLayout.Y_AXIS));
		
		JPanel namePanel = new JPanel();
		namePanel.add(new JLabel("Name"));
		final JTextField nameText = new JTextField();
		
		nameText.getDocument().addDocumentListener(new DocumentListener(){
			public void changedUpdate(DocumentEvent e) {
					updateText();
			  }
			  public void removeUpdate(DocumentEvent e) {
				  	updateText();
			  }
			  public void insertUpdate(DocumentEvent e) {
				  	updateText();
			  }
			  
			  public void updateText(){
				  	st.setName(nameText.getText().trim());
			  }

		});
		namePanel.add(nameText);
		
		JPanel classPanel = new JPanel();
		JComboBox<DFOClass> classSelector = new JComboBox<DFOClass>();
		for(DFOClass c : ClassManager.getInstance().getAllClasses()){
			classSelector.addItem(c);
		}
		
		firstpanel.add(namePanel);
		return firstpanel;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		switch(arg0.getActionCommand()){
		case NEXT:
			break;
		case PREVIOUS:
			break;
		case FINISH:
			break;
		default:
			break;
		}
		
	}

}
