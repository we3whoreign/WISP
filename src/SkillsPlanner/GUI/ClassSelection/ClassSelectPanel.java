package SkillsPlanner.GUI.ClassSelection;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.File;
import SkillsPlanner.Utils.*;
import SkillsPlanner.GUI.*;
import SkillsPlanner.GUI.Actions.*;

public class ClassSelectPanel extends JPanel{

	private JSplitPane splitter;

	private final String Default_Picture = FileUtils.makePath("./lib/images/Default.png");
	private final String Slayer_Picture = FileUtils.makePath("./lib/images/slayer.png");
	private final String Mage_Picture = FileUtils.makePath("./lib/images/mage.png");
	private final String Fighter_Picture = FileUtils.makePath("./lib/images/fighter.png");
	private final String Priest_Picture = FileUtils.makePath("./lib/images/priest.png");
	private final String Gunner_Picture = FileUtils.makePath("./lib/images/gunner.png");
	
	private final int default_width = 500;
	private final int default_height = 300;

	/**
	* A final string that holds the description for the slayer class to be displayed on class selection
	*/
	private final String Slayer_Description = "A slayer description";

	/**
	* A final string that holds the description for the mage class to be displayed on class selection
	*/
	private final String Mage_Description = "A mage description";

	/**
	* A final string that holds the description for the priest class to be displayed on class selection
	*/
	private final String Priest_Description = "A priest description";

	/**
	* A final string that holds the description for the fighter class to be displayed on class selection
	*/
	private final String Fighter_Description = "A fighter description";

	/**
	* A final string that holds the description for the gunner class to be displayed on class selection
	*/
	private final String Gunner_Description = "A gunner description";

	public ClassSelectPanel(){
		setup("");
	}

	public ClassSelectPanel(String classString){

		System.out.println(classString);
		setup(classString);
	}

	
	/**
	* setup the GUI with selecting the picture and description
	*/
	public void setup(String selection){
		JLabel description;
		JLabel ImagePanel;
		String picture;
		
		this.setLayout(new BorderLayout());

		if(selection == null){
			return;
		}
		else if(Commands.FIGHTER.equals(selection)){
			description = new JLabel(Fighter_Description);
			picture = Fighter_Picture;
		}
		else if(Commands.SLAYER.equals(selection)){
			description = new JLabel(Slayer_Description);
			picture = Slayer_Picture;
		}
		else if(Commands.MAGE.equals(selection)){
			description = new JLabel(Mage_Description);
			picture = Mage_Picture;
		}
		else if(Commands.GUNNER.equals(selection)){
			description = new JLabel(Gunner_Description);
			picture = Gunner_Picture;
		}
		else if(Commands.PRIEST.equals(selection)){
			description = new JLabel(Priest_Description);
			picture = Priest_Picture;
		}
		else{
			description = new JLabel("DFO Default Summary");
			picture = Default_Picture;
		}
		//Dimension preferred = new Dimension(this.getWidth()/2,this.getHeight()/2);
		
		//description.setPreferredSize(preferred);
		//picture.setPreferredSize(preferred);
		
		//splitter = new JSplitPane(JSplitPane.VERTICAL_SPLIT,description,picture);
		//splitter.setResizeWeight(0.5);
		//splitter.setDividerLocation(0.5);
		//add(splitter,BorderLayout.CENTER);
		int width = (this.getWidth() == 0) ? default_width : this.getWidth();
		int height = (this.getHeight() == 0) ? default_height : this.getHeight();
		
		description.setSize(width,height);
		add(description,BorderLayout.NORTH);
		
		BufferedImage image = null;
		try{
			image = ImageIO.read(new File(picture));
			image = FileUtils.resizeImage(image, width, height);
		}
		catch(IOException e){
			JOptionPane.showMessageDialog(null,"Cannot open image files! Exiting");
			System.exit(0);
		}
		ImageIcon icon = new ImageIcon(image);
		ImagePanel = new JLabel(icon);
		
		add(ImagePanel,BorderLayout.CENTER);
	}

}
