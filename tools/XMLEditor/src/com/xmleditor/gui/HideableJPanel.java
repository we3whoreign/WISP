package com.xmleditor.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class HideableJPanel extends JPanel{

	String _selection;
	JPanel _contents;
	ClickableHeader header;
	
	public HideableJPanel(String selection, JPanel contents){
		_selection = selection;
		_contents = contents;
		
		header = new ClickableHeader();
		
		this.setLayout(new BorderLayout());
		
		this.add(header,BorderLayout.NORTH);
		this.add(_contents,BorderLayout.CENTER);
		this.setOpaque(true);
		
		initGUI();
	}
	
	private void initGUI(){
		this.setBorder(BorderFactory.createLineBorder(Color.black));
	}
	
	protected void toggleVisible(){
		this._contents.setVisible(!this._contents.isVisible());
	}
	
//	public Dimension getPreferredSize(){
//		int width = 50;
//		int height = 50;
//		
//		if(this._contents.isVisible()){
//			width = (this._contents.getWidth() > this.header.getWidth()) ? this._contents.getWidth() : this.header.getWidth();
//			height = this._contents.getHeight() + this.header.getHeight();
//		}
//		else{
//			width = this.header.getWidth();
//			height = this.header.getHeight();
//		}
//		
//		return new Dimension(width,height);
//	}
	
	private class ClickableHeader extends JPanel implements MouseListener{
		
		public ClickableHeader(){
			initGUI();
		}
		
		private void initGUI(){
			JLabel label = new JLabel(_selection);
			label.setForeground(Color.WHITE);
			this.add(label);
			this.setBackground(Color.GREEN);
			this.setOpaque(true);
			
			this.addMouseListener(this);
			
		}

		@Override
		public void mouseClicked(MouseEvent arg0) {
			// TODO Auto-generated method stub
			toggleVisible();
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
	}
}
