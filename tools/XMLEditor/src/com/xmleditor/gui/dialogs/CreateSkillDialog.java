package com.xmleditor.gui.dialogs;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.Collection;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;

import javax.swing.WindowConstants;
import javax.swing.SwingUtilities;

import net.miginfocom.swing.MigLayout;

import com.xmleditor.io.XMLWriter;

import skillsplanner.beans.DFOClass;
import skillsplanner.beans.Skill;
import skillsplanner.resources.ClassManager;
import skillsplanner.resources.SkillManager;
import skillsplanner.utils.ListUtils;

/**
 * Dialog to create a new skill
 * @author ryzngard
 *
 */
public class CreateSkillDialog extends javax.swing.JFrame implements ActionListener{
	private JPanel inputPanel;
	private JTextField nameField;
	private JLabel spLabel;
	private JTextField levelsText;
	private JButton jButton1;
	private JScrollPane jScrollPane1;
	private JTextPane jTextArea1;
	private JCheckBox startLevelCheck;
	private JTextField initialCostText;
	private JLabel jLabel1;
	private JLabel levelLabel;
	private JTextField spText;
	private JLabel nameLabel;
	private JComboBox<DFOClass> baseClass;
	private JTextField requiredLevelText;
	private DefaultListModel model = new DefaultListModel();
	private JList requiredSkills = new JList(model);
	private AddRequiredDialog addRequiredDialog;

	Skill st = new Skill();
	
	public CreateSkillDialog() {
		super();
		initGUI();
		this.setVisible(true);
	}
	
	private void initGUI() {
		try {
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

			inputPanel = new JPanel();
			inputPanel.setLayout(new MigLayout("wrap 2"));
			getContentPane().add(inputPanel, BorderLayout.CENTER);
			inputPanel.setAlignmentX(0.0f);

			nameLabel = new JLabel();
			inputPanel.add(nameLabel);
			nameLabel.setText("Name");
			nameLabel.setHorizontalAlignment(SwingConstants.RIGHT);

			nameField = new JTextField();
			inputPanel.add(nameField, "span, grow");

			spLabel = new JLabel();
			inputPanel.add(spLabel);
			spLabel.setText("SPCost");
			spLabel.setHorizontalAlignment(SwingConstants.RIGHT);

			spText = new JTextField();
			spText.setText("0");
			inputPanel.add(spText, "span, grow");

			levelLabel = new JLabel();
			inputPanel.add(levelLabel);
			levelLabel.setText("Number of Levels");
			levelLabel.setHorizontalAlignment(SwingConstants.RIGHT);

			levelsText = new JTextField();
			levelsText.setText("0");
			inputPanel.add(levelsText, "span, grow");

			jLabel1 = new JLabel();
			inputPanel.add(jLabel1);
			jLabel1.setText("Initial Cost");
			jLabel1.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel1.setSize(155, 16);
			
			initialCostText = new JTextField();
			inputPanel.add(initialCostText, "span, grow");

			JLabel label = new JLabel();
			inputPanel.add(label);
			label.setText("Minimum Level to Obtain Skill:");
			label.setHorizontalAlignment(SwingConstants.RIGHT);
			label.setSize(155, 16);

			requiredLevelText = new JTextField();
			requiredLevelText.setText("0");
			inputPanel.add(requiredLevelText, "span,grow");

			startLevelCheck = new JCheckBox();
			inputPanel.add(startLevelCheck);
			startLevelCheck.setText("Start at level 1");
			

			ConcurrentHashMap<String, DFOClass> map = ClassManager.getInstance().getAllClasses();
			Collection<DFOClass> collection = ListUtils.sortList(ListUtils.getListFromMap(map));
			baseClass = new JComboBox(collection.toArray());
			inputPanel.add(baseClass);

			jButton1 = new JButton();
			
			inputPanel.add(new JLabel("Required Skills"));
			inputPanel.add(new JLabel(""));
			
			JButton addRequiredSkill = new JButton("Add Requirement");
			addRequiredSkill.setActionCommand("ADD_REQUIRED");
			addRequiredSkill.addActionListener(this);
			
			inputPanel.add(requiredSkills,"wrap");
			inputPanel.add(addRequiredSkill);
			
			inputPanel.add(jButton1);
			jButton1.setText("Generate XML");
			jButton1.addActionListener(this);
			pack();
			this.setSize(500, 500);
		} catch (Exception e) {
		    //add your error handling code here
			e.printStackTrace();
		}
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getActionCommand().equalsIgnoreCase("ADD_REQUIRED")){
			addRequiredDialog = new AddRequiredDialog(this);
		}
		else if(arg0.getActionCommand().equals(AddRequiredDialog.SAVE)){
			String name = addRequiredDialog.getSkillName();
			int level = addRequiredDialog.getLevel();
			model.addElement(name + " - "+level);
			addRequiredDialog.setVisible(false);
			st.addSkillRequirement(name, level);
		}
		else{
			st.setName(nameField.getText());
			st.setSpcost(Integer.parseInt(this.spText.getText()));
			st.setMaxLevel(Integer.parseInt(this.levelsText.getText()));
			st.setRequiredLevel(Integer.parseInt(requiredLevelText.getText()));
			st.setTree(getFolderToPutIn());
			if(this.startLevelCheck.isSelected()){
				st.setEntrycost(-1);
			}
			else{
				st.setEntrycost(Integer.parseInt(this.initialCostText.getText()));
			}
			
			if(XMLWriter.getInstance().skillToXML(st,getFolderToPutIn())){
				JOptionPane.showMessageDialog(this, "File created successfully");
			}
		}
	}
	
	private String getFolderToPutIn(){
		DFOClass selected = (DFOClass) baseClass.getSelectedItem();
		
		return selected.getUniqueName();
	}

}
