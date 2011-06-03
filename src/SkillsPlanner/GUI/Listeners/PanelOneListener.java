package SkillsPlanner.GUI.Listeners;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import SkillsPlanner.GUI.ClassSelection.*;
public class PanelOneListener implements ActionListener{
	
		ClassSelectPanel panel;
		
		public PanelOneListener(JPanel panel){
			this.panel = (ClassSelectPanel) panel;
		}
	
		public void actionPerformed(ActionEvent e){
			panel.removeAll();
			panel.setup(e.getActionCommand());
			panel.invalidate();
			panel.validate();
			panel.repaint();
		}

}
