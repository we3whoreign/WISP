package skillsplanner.gui.custom;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

/**
 * A clickable panel with the option of storing a skill name. Used for Leveling up skills. Registers Right click as level down and left click as level up.
 * @author Andrew
 *
 */
public class ClickablePanel extends JPanel implements MouseListener{
	private final int width = 250;
	private final int height = 50;
	Border raised;
	Border lowered;
	Border empty;
	
	public ClickablePanel(String skill){
		this.setName(skill);
		setDimensions();
	}
	
	public ClickablePanel(){
		setDimensions();
		this.setBackground(Color.cyan);
	}
	
	/**
	 * Sets the size for the clickable label
	 */
	private void setDimensions(){
		//this.setMinimumSize(new Dimension(width,height));
		//this.setSize(new Dimension(width,height));
		//this.setPreferredSize(new Dimension(width,height));
		this.setToolTipText("Click to level up. Right click to level down");
		
		raised = BorderFactory.createRaisedBevelBorder();
		lowered = BorderFactory.createLoweredBevelBorder();
		empty = BorderFactory.createEmptyBorder();
		
		//this.setBorder(lowered);
		this.add(new JLabel(this.getName()));
		
		this.setCursor(new Cursor(Cursor.HAND_CURSOR));
		this.addMouseListener(this);
		
	}
	
	public void setSkillName(String skill){
		this.setName(skill);
	}
	
	public String getSkillName(){
		return this.getName();
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		if(arg0.getButton() == MouseEvent.BUTTON1){
			System.out.println("Left Click");
		}
		else if(arg0.getButton() == MouseEvent.BUTTON2){
			System.out.println("Right Click");
		}
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		this.setBorder(raised);
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		this.setBorder(empty);
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		this.setBorder(lowered);
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		this.setBorder(raised);
		
	}

}
