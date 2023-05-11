package com.game.model.board;

import java.awt.Point;
import java.awt.event.MouseEvent;

import javax.swing.event.MouseInputListener;

public class TheHand implements MouseInputListener {
	private LogicBoard logicBoard = new LogicBoard();
	private GraphicBoard graphicBoard = new GraphicBoard(this);
	
	private MouseEvent prevClick;

	@Override
	public void mouseClicked(MouseEvent e) {		
		if(e.getButton() == 2) {
			graphicBoard.close();
		}

		
	}
	@Override
	public void mousePressed(MouseEvent e) {
		if(e.getButton() == 3) {
			prevClick = e;
		}
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		if(e.getButton()==3 ) {
			graphicBoard.setBounds(e.getXOnScreen()-prevClick.getX(), e.getYOnScreen()-prevClick.getY(), graphicBoard.MAX_SIZE, graphicBoard.MAX_SIZE);
			//Asi calculo la distancia respecto a la pantalla del punto 00 del frame y puedo desplazarlo consecuentemente

		}
		
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
		
		System.out.println("dragged");
		
	}
	@Override
	public void mouseMoved(MouseEvent e) {
		System.out.println("movido");
		
	}
}
