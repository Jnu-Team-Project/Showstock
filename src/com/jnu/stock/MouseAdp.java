package com.jnu.stock;

import java.awt.CardLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

public class MouseAdp implements MouseListener{

	private CardLayout cardLayout;
	private JPanel jPanel;
	
	public MouseAdp(CardLayout cardLayout, JPanel jPanel){
		this.cardLayout = cardLayout;
		this.jPanel = jPanel;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		cardLayout.show(jPanel, "2");
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
