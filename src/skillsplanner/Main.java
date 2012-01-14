package skillsplanner;

import java.awt.EventQueue;

import javax.swing.UIManager;

class Main {
	public Main() {
	}

	public static void main(String args[]) throws Exception {

		/**
		 * set the look and feel
		 */
		UIManager.setLookAndFeel(UIManager
				.getCrossPlatformLookAndFeelClassName());
		/**
		 * For the love of god thread safety!!!!!
		 */
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				new Launcher();
			}
		});

	}

}
