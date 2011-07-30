package skillsplanner.gui.classselection;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JPanel;

import skillsplanner.gui.actions.Commands;
import skillsplanner.gui.listeners.PanelOneListener;

public class BaseClassPanel extends JPanel {

	protected JButton fighter, mage, priest, slayer, gunner;

	private PanelOneListener listener;

	public BaseClassPanel(JPanel panel) {
		// Declare the buttons
		fighter = new JButton("Fighter");
		mage = new JButton("Mage");
		priest = new JButton("Priest");
		slayer = new JButton("Slayer");
		gunner = new JButton("Gunner");

		// Setup the action listener to handle the other panel
		listener = new PanelOneListener(panel);
		// Set the action listener for the buttons
		fighter.setActionCommand(Commands.FIGHTER);
		fighter.addActionListener(listener);
		mage.setActionCommand(Commands.MAGE);
		mage.addActionListener(listener);
		priest.setActionCommand(Commands.PRIEST);
		priest.addActionListener(listener);
		slayer.setActionCommand(Commands.SLAYER);
		slayer.addActionListener(listener);
		gunner.setActionCommand(Commands.GUNNER);
		gunner.addActionListener(listener);

		// SpringLayout layout = new SpringLayout();
		GridBagLayout layout = new GridBagLayout();
		GridBagConstraints constraints = new GridBagConstraints();

		this.setLayout(layout);

		setupConstraints(constraints);
		this.add(slayer, constraints);

		setupConstraints(constraints);
		constraints.gridx = 0;
		constraints.gridy = 1;
		this.add(fighter, constraints);

		setupConstraints(constraints);
		constraints.gridx = 0;
		constraints.gridy = 2;
		this.add(priest, constraints);

		setupConstraints(constraints);
		constraints.gridx = 0;
		constraints.gridy = 3;
		this.add(mage, constraints);

		constraints.gridx = 0;
		constraints.gridy = 4;
		constraints.weighty = 1;
		constraints.weightx = 0;
		constraints.ipady = this.getHeight() - 10 * mage.getHeight();
		this.add(Box.createVerticalBox(), constraints);

	}

	/**
	 * Setup the constraints, since they are reset everytime
	 */
	private void setupConstraints(GridBagConstraints c) {
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 1;
		c.weighty = 0;
		c.anchor = GridBagConstraints.NORTHWEST;
		c.gridwidth = GridBagConstraints.RELATIVE;
	}

}
