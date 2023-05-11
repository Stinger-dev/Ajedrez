package com.game.model.board;

import java.awt.Point;
import java.awt.event.MouseEvent;

import javax.swing.event.MouseInputListener;

public class TheHand implements MouseInputListener {
	private LogicBoard logicBoard = new LogicBoard();
	private GraphicBoard graphicBoard = new GraphicBoard(this);
	
	private MouseEvent dragClick;

	@Override
	public void mouseClicked(MouseEvent e) {		

		
	}
	@Override
	public void mousePressed(MouseEvent e) {
		if(e.getButton() == 3) {
			dragClick = e;
		}else {
			dragClick = null;
		}
		
		if(e.getButton() == 2) {
			graphicBoard.close();
		}
	}
	@Override
	public void mouseReleased(MouseEvent e) {

		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}	
	@Override
	public void mouseDragged(MouseEvent e) {
	
		if(dragClick!=null) {
			//Asi calculo la distancia respecto a la pantalla del punto 00 del frame y puedo desplazarlo consecuentemente
			graphicBoard.setLocation(e.getXOnScreen()-dragClick.getX(), e.getYOnScreen()-dragClick.getY());		
		}

	
				
	}
	@Override
	public void mouseMoved(MouseEvent e) {
		
	}
}
