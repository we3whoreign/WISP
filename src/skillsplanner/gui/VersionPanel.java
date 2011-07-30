package skillsplanner.gui;

import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import skillsplanner.Launcher;

/**
 * Simple panel with a JLabel that displays the version
 */
public class VersionPanel extends JPanel {

	private JLabel versionLabel = new JLabel(Launcher.class.getPackage()
			.getImplementationVersion());

	public VersionPanel() {
		super();

		this.setLayout(new FlowLayout(FlowLayout.TRAILING));
		this.add(versionLabel);

	}

}
