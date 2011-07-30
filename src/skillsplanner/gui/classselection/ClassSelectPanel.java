package skillsplanner.gui.classselection;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.SwingConstants;

import skillsplanner.Launcher;
import skillsplanner.classes.*;
import skillsplanner.gui.MainFrame;
import skillsplanner.gui.actions.Commands;
import skillsplanner.utils.FileUtils;

public class ClassSelectPanel extends JPanel {

	private JSplitPane splitter;

	private final URL Default_Picture = FileUtils
			.makePath("lib/images/Default.png");
	private final URL Slayer_Picture = FileUtils
			.makePath("lib/images/slayer.jpg");
	private final URL Mage_Picture = FileUtils
			.makePath("lib/images/mage.jpg");
	private final URL Fighter_Picture = FileUtils
			.makePath("lib/images/fighter.jpg");
	private final URL Priest_Picture = FileUtils
			.makePath("lib/images/priest.jpg");
	private final URL Gunner_Picture = FileUtils
			.makePath("lib/images/gunner.jpg");

	private static final int default_width = 200;
	private static final int default_height = 100;

	/**
	 * A final string that holds the description for the slayer class to be
	 * displayed on class selection
	 */
	private static final String Slayer_Description = "A slayer description";

	/**
	 * A final string that holds the description for the mage class to be
	 * displayed on class selection
	 */
	private static final String Mage_Description = "A mage description";

	/**
	 * A final string that holds the description for the priest class to be
	 * displayed on class selection
	 */
	private static final String Priest_Description = "A priest description";

	/**
	 * A final string that holds the description for the fighter class to be
	 * displayed on class selection
	 */
	private static final String Fighter_Description = "A fighter description";

	/**
	 * A final string that holds the description for the gunner class to be
	 * displayed on class selection
	 */
	private static final String Gunner_Description = "A gunner description";

	public ClassSelectPanel() {
		setup("");
	}

	public ClassSelectPanel(String classString) {

		System.out.println(classString);
		setup(classString);
		update();
	}

	/**
	 * setup the GUI with selecting the picture and description
	 */
	public void setup(String selection) {
		JLabel description;
		JLabel ImagePanel;
		URL picture;

		this.setLayout(new GridLayout(0,1));
		
		int alignment = SwingConstants.LEFT;

		if (selection == null) {
			return;
		} else if (Commands.FIGHTER.equals(selection)) {
			description = new JLabel(Fighter_Description, alignment);
			picture = Fighter_Picture;
			MainFrame.character.setDFOClass(new Fighter());
		} else if (Commands.SLAYER.equals(selection)) {
			description = new JLabel(Slayer_Description, alignment);
			picture = Slayer_Picture;
			MainFrame.character.setDFOClass(new Slayer());
		} else if (Commands.MAGE.equals(selection)) {
			description = new JLabel(Mage_Description, alignment);
			picture = Mage_Picture;
			MainFrame.character.setDFOClass(new Mage());
		} else if (Commands.GUNNER.equals(selection)) {
			description = new JLabel(Gunner_Description, alignment);
			picture = Gunner_Picture;
			MainFrame.character.setDFOClass(new Gunner());
		} else if (Commands.PRIEST.equals(selection)) {
			description = new JLabel(Priest_Description, alignment);
			picture = Priest_Picture;
			MainFrame.character.setDFOClass(new Priest());
		} else {
			description = new JLabel("DFO Default Summary", alignment);
			picture = Default_Picture;
		}
		
		description.setVerticalTextPosition(SwingConstants.TOP);

		//description.setMinimumSize(new Dimension(default_width, default_height));
		add(description);

		BufferedImage image = null;
		try {
			image = ImageIO.read(picture);
			image = FileUtils.resizeImage(image, default_width, default_height);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null,
					"Cannot open " + picture);
			
			//Kill the GUI
			Launcher.frame.dispose();
		}
		ImageIcon icon = new ImageIcon(image);
		ImagePanel = new JLabel(icon);

		add(ImagePanel);
	}
	
	/**
	 * Updates the second page to changes made on this page. Calls the
	 * notifyObservers method of the MainFrame.character object
	 */
	private void update(){
		MainFrame.character.notifyObservers();
	}

}
