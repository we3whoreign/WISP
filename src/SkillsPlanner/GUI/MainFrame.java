package SkillsPlanner.GUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import SkillsPlanner.DFOCharacter;
import SkillsPlanner.GUI.Actions.Commands;
import SkillsPlanner.GUI.ClassSelection.BaseClassPanel;
import SkillsPlanner.GUI.ClassSelection.ClassSelectPanel;
import SkillsPlanner.GUI.SkillSelection.*;

/**
 * DFO Skill Builder frame that holds all the yummy GUIness
 */
public class MainFrame extends JFrame implements ActionListener {

	private static final int dividerSize = 0;
	public static final int DIVIDER_LOCATION = 100;
	public static DFOCharacter character;
	private JPanel FirstPanel;
	private SkillSelectionPanel SecondPanel;
	private JPanel PageSelection = new JPanel();
	private JButton NextButton = new JButton("Next Button");
	private JPanel card;

	// We start on page 1
	private int page = 1;

	public MainFrame() {
		super("DFO Skill Builder");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Set the character object
		character = new DFOCharacter();
		character.addObserver(SecondPanel);

		setup();

		// set to the first page
		// firstPage();

		// Display the window.
		this.pack();
		this.setVisible(true);
	}

	/**
	 * Handles the GUI setup
	 */
	private void setup() {
		FirstPanel = new JPanel();

		// Set up the card panel
		card = new JPanel();
		card.setLayout(new BorderLayout());

		// Add them to the card
		card.add(FirstPanel, BorderLayout.CENTER);

		NextButton.setActionCommand(Commands.NEXT);
		NextButton.addActionListener(this);

		// Right allign the page selection panel
		PageSelection.setLayout(new FlowLayout(FlowLayout.TRAILING));

		PageSelection.add(NextButton);

		// Page Selection is a wrapper for the selection buttons
		getContentPane().add(PageSelection, BorderLayout.NORTH);
		getContentPane().add(card, BorderLayout.CENTER);
		getContentPane().add(new VersionPanel(), BorderLayout.SOUTH);

		// Page Setup
		firstPage();
		secondPage();
	}

	/**
	 * Set up the first page panel
	 */
	private void firstPage() {
		JPanel classSelect = new ClassSelectPanel();
		JPanel baseClass = new BaseClassPanel(classSelect);

		FirstPanel.setLayout(new BorderLayout());
		FirstPanel.add(baseClass, BorderLayout.WEST);
		FirstPanel.add(classSelect, BorderLayout.CENTER);
	}

	/**
	 * Set up the second page panel
	 */
	private void secondPage() {
		SecondPanel = new SkillSelectionPanel();
		
	}

	/**
	 * Action listener for the next and previous buttons
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		card.removeAll();

		if (Commands.NEXT.equals(e.getActionCommand())) {
			if (page == 1) {
				card.add(SecondPanel, BorderLayout.CENTER);
				page = 2;
			} else {
				card.add(FirstPanel, BorderLayout.CENTER);
				page = 1;
			}
		} else if (Commands.PREVIOUS.equals(e.getActionCommand())) {
			if (page == 1) {
				card.add(SecondPanel, BorderLayout.CENTER);
				page = 2;
			} else {
				card.add(FirstPanel, BorderLayout.CENTER);
				page = 1;
			}
		}
		this.invalidate();
		this.validate();
		this.repaint();
	}

}
