package skillsplanner.gui.custom;

import java.awt.Component;
import java.awt.Image;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;

import skillsplanner.resources.ImageManager;
import skillsplanner.utils.StringUtils;

@SuppressWarnings("serial")
public class TreeImageCellRender extends DefaultTreeCellRenderer {
	Image img;
	
	public TreeImageCellRender(){
		super();
	}

	public Component getTreeCellRendererComponent(JTree tree,
	        Object value, boolean selected, boolean expanded,
	        boolean leaf, int row, boolean hasFocus){

	        super.getTreeCellRendererComponent(tree, value,
	        selected, expanded, leaf, row, hasFocus);
	       
	        if(!leaf){
	        	try{
	        		String name = ((DefaultMutableTreeNode) value).toString();
	        		img = ImageManager.getImage(name);
	        	}
	        	catch(IOException exc){
	        		//do nothing
	        	}
	        	if(img != null){
	        		setIcon(new ImageIcon(img.getScaledInstance(100, 40, 1)));
	        		this.setText("");
	        	}
	        	else{
	        		System.out.println((String) value +" is a null image");
	        	}
	        }
	        else{
	        	String text = ((DefaultMutableTreeNode) value).toString();
	        	text = text.substring(text.indexOf("/")+1);
	        	text = StringUtils.toCamelCase(text);
	        	this.setText(text);
	        }
	        
	        return this;
	 }

}
