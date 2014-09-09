package org.we3whoreign.wisp.gui.custom;

import java.awt.Component;
import java.awt.Image;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;

import org.we3whoreign.wisp.resources.ImageManager;
import org.we3whoreign.wisp.utils.StringUtils;

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
	        	String text = ((DefaultMutableTreeNode) value).toString();
	        	if(text.matches("/$")){
	        		text = text.substring(0,text.length()-1);
	        		text = text.substring(text.lastIndexOf("/")+1);
	        	}
	        	text = StringUtils.toCamelCase(text);
	        	//text = text.substring(text.lastIndexOf("/"));
	        	this.setText(text);
//	        	try{
//	        		String name = ((DefaultMutableTreeNode) value).toString();
//	        		if(name.contains("/")){
//	        			name = name.substring(name.indexOf("/")+1);
//	        		}
//	        		img = ImageManager.getImage(name);
//	        	}
//	        	catch(IOException exc){
//	        		//do nothing
//	        	}
//	        	if(img != null){
//	        		setIcon(new ImageIcon(img.getScaledInstance(150, 40, 1)));
//	        		this.setText("");
//	        	}
//	        	else{
//	        		System.out.println((String) value +" is a null image");
//	        	}
	        }
	        else{
	        	String text = ((DefaultMutableTreeNode) value).toString();
	        	if(text.matches("/$")){
	        		text = text.substring(0,text.length()-1);
	        	}
	        	text = text.substring(text.lastIndexOf("/")+1);
	        	text = StringUtils.toCamelCase(text);
	        	this.setText(text);
	        }
	        
	        return this;
	 }

}
