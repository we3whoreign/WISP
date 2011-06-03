package SkillsPlanner.GUI;

import javax.swing.*;
import java.awt.*;
import SkillsPlanner.GUI.*;
import SkillsPlanner.Skills.*;
import SkillsPlanner.*;
import SkillsPlanner.GUI.ClassSelection.*;

public class MainFrame extends JFrame{
	
	private final int dividerSize = 0;
	public static final int DIVIDER_LOCATION = 100;
	private int page;
	DFOCharacter character;
	public static SelectionPane splitPane;

	public MainFrame(){
		super("DFO Skill Builder");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Set the character object
		this.character = new DFOCharacter();
		
		this.setLocation(300,200);
		this.setSize(400,300);
		
		//set to the first page
		firstPage();
		
		//Display the window.
		this.pack();
		this.setVisible(true);
	}
	
	private void firstPage(){
		page = 1;
		
		JPanel baseClass = new BaseClassPanel();
		JPanel classSelect = new ClassSelectPanel();
		
		splitPane = new SelectionPane(JSplitPane.HORIZONTAL_SPLIT,
			baseClass,classSelect,character);
		
		splitPane.setOneTouchExpandable(true);
		splitPane.setDividerLocation(DIVIDER_LOCATION);
		
		//splitPane.setDividerSize(dividerSize);
		
		getContentPane().add(splitPane,BorderLayout.CENTER);
			
	}
	
	private void secondPage(){
		page = 2;
		
	}

}
