package SkillsPlanner.GUI;

import java.awt.*;
import javax.swing.*;
import SkillsPlanner.Launcher;

/**
 * Simple panel with a JLabel that displays the version
 */
public class VersionPanel extends JPanel{
	
	private JLabel versionLabel = new JLabel(Launcher.class.getPackage().getImplementationVersion());
	
	public VersionPanel(){
		super();
		
		this.setLayout(new FlowLayout(FlowLayout.TRAILING));
		this.add(versionLabel);
		
	}
		

}
