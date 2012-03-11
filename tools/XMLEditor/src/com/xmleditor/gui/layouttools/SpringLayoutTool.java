package com.xmleditor.gui.layouttools;

import java.awt.Component;

import javax.swing.SpringLayout;

public class SpringLayoutTool {
	
	/**
	 * Defines a constraint so that c1 is left of c2
	 * @param layout
	 * @param c1
	 * @param c2
	 */
	public static void leftConstraint(SpringLayout layout, Component c1, Component c2, int pad){
		layout.putConstraint(SpringLayout.EAST, c1, pad, SpringLayout.WEST, c2);
	}
	
	/**
	 * Calls leftConstraint(...) with gap default to 5, putting c1 left of c2
	 * @param layout
	 * @param c1
	 * @param c2
	 */
	public static void leftConstraint(SpringLayout layout, Component c1, Component c2){
		leftConstraint(layout,c1,c2,5);
	}
	
	/**
	 * Puts a constraint on the layout so that c1 is above c2
	 * @param layout
	 * @param c1
	 * @param c2
	 * @param pad
	 */
	public static void topConstraint(SpringLayout layout, Component c1, Component c2, int pad){
		layout.putConstraint(SpringLayout.SOUTH, c1, pad, SpringLayout.NORTH, c2);
	}
	
	/**
	 * Puts a constraint on the layout so that c1 is above c2 with default pad of 5
	 * @param layout
	 * @param c1
	 * @param c2
	 * @param pad
	 */
	public static void topConstraint(SpringLayout layout, Component c1, Component c2){
		topConstraint(layout,c1,c2,5);
	}

	/**
	 * Aligns the left side of c2 with the left side of c1 with default padding of 5
	 * @param leftMenuLayout
	 * @param leftMenu
	 * @param panel
	 */
	public static void alignLeft(SpringLayout layout, Component c1,
			Component c2) {
				alignLeft(layout,c1,c2,5);
	}
	
	/**
	 * Aligns the left side of c2 with the left side of c1
	 * @param leftMenuLayout
	 * @param leftMenu
	 * @param panel
	 */
	public static void alignLeft(SpringLayout layout, Component c1,
			Component c2, int pad) {
				layout.putConstraint(SpringLayout.WEST, c1, pad, SpringLayout.WEST, c2);
	}
	
	/**
	 * Aligns the top of c2 and c1 together with pad of 5
	 * @param layout
	 * @param c1
	 * @param c2
	 */
	public static void alignTop(SpringLayout layout, Component c1, Component c2){
		alignTop(layout,c1,c2,5);
	}
	
	/**
	 * Aligns the top of c2 and c1 together
	 * @param layout
	 * @param c1
	 * @param c2
	 */
	public static void alignTop(SpringLayout layout, Component c1, Component c2, int pad){
		layout.putConstraint(SpringLayout.NORTH, c1, pad, SpringLayout.NORTH, c2);
	}

}
