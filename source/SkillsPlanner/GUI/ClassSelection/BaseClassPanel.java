package SkillsPlanner.GUI.ClassSelection;

import javax.swing.*;
import java.awt.*;
import SkillsPlanner.GUI.*;
import SkillsPlanner.GUI.Layout.*;
public class BaseClassPanel extends JPanel{

	protected JButton fighter, mage, priest, slayer;
	
	private final int height = 20;
	private final int width = 100;
	
	//Action commands
	public final String FIGHTER = "Fighter";
	public final String MAGE = "Mage";
	public final String PRIEST = "Priest";
	public final String SLAYER = "Slayer";
	
	private final String imageDir = "libs/images/buttons/";
	public BaseClassPanel(){
		//Declare the buttons
		fighter = new JButton("Fighter");
		mage = new JButton("Mage");
		priest = new JButton("Priest");
		slayer = new JButton("Slayer");
		
		//Set the action listener for the buttons
		fighter.setActionCommand(FIGHTER);
		fighter.addActionListener(MainFrame.splitPane);
		mage.setActionCommand(MAGE);
		mage.addActionListener(MainFrame.splitPane);
		priest.setActionCommand(PRIEST);
		priest.addActionListener(MainFrame.splitPane);
		slayer.setActionCommand(SLAYER);
		slayer.addActionListener(MainFrame.splitPane);
		
		SpringLayout layout = new SpringLayout();
		
		this.setLayout(layout);
		                                                       
		this.add(slayer);
		this.add(fighter);
		this.add(priest);
		this.add(mage);
		
		SpringUtilities.makeCompactGrid(this,
			4,1, //rows, column
			0,0, //initialX, initialY
			0,0); //XPad, YPad
		
	}
	
}
