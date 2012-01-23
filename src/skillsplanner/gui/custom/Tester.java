/**
 * 
 */
package skillsplanner.gui.custom;

import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * @author ryzngard
 *
 */
public class Tester extends JPanel{
	
	public Tester(){
		this.setSize(new Dimension(500,500));
		guiSetup();
		this.setVisible(true);
	}

	/**
	 * 
	 */
	private void guiSetup() {
		this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		for(int i = 0; i < 10; i++){
			this.add(new ClickablePanel((String.valueOf(i))));
		}
		
	}
	
	public static void main(String args[]){
		JFrame frame = new JFrame();
		frame.add(new Tester());
		frame.setVisible(true);
	}

}
