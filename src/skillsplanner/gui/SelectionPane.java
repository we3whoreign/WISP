package SkillsPlanner.GUI;

import javax.swing.JPanel;
import javax.swing.JSplitPane;

/**
 * The class selection panel where the user chooses what class they would like
 * to make a build for
 */
public class SelectionPane extends JSplitPane {

	public SelectionPane(int split, JPanel item1, JPanel item2) {
		super(split, item1, item2);
	}
}
