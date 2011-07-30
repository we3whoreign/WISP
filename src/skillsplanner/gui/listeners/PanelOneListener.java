package skillsplanner.gui.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import skillsplanner.gui.classselection.ClassSelectPanel;

public class PanelOneListener implements ActionListener {

	ClassSelectPanel panel;

	public PanelOneListener(JPanel panel) {
		if(panel instanceof ClassSelectPanel){
			this.panel = (ClassSelectPanel) panel;
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		panel.removeAll();
		panel.setup(e.getActionCommand());
		panel.invalidate();
		panel.validate();
		panel.repaint();
	}

}
