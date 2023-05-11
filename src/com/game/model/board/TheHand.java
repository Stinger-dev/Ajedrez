package com.game.model.board;

import java.awt.event.MouseEvent;

import javax.swing.event.MouseInputListener;

public class TheHand implements MouseInputListener {
	/**
	 * TheHand es la clase encargada de gestionar las salidas de LogicBoard y
	 * enviarlas a GraphicBoard, de ahi el nombre, es "la mano" del jugador que
	 * relaciona el tablero, las piezas y el movimiento
	 * 
	 * En mi deseño la parte logica y la grfica estan completamente separadas, es decir, no tienen ninguna relacion entre ellas, asi puedo
	 * modificar independientemente cada una de ellas y podria optimizarlas en un futuro
	 * 
	 * Se podria decir que he dividido esto entre front-end back-end y una api que es a la que tiene acceso el usuario y que gestiona 
	 * las otras dos partes
	 */
	private static final String NOTATION = "R-0-0,N-6-0,R-7-0," + // mayusculas es negra, la primera es x y la segunda
			// y
			"P-0-1,P-1-1,P-7-1," + "p-0-6,p-6-6,p-7-6," + "r-0-7,n-1-7";
	
	private LogicBoard logicBoard; //TODO
	private GraphicBoard graphicBoard ;

	// Asi puedo controlar que solo sea el click derecho el que permita arrastrar,
	// no se puede poner la concicional en el drag ya que siempre devuelve 0, no se
	// muy bien porque
	private MouseEvent dragClick;
	
	
	
	public TheHand() throws BoardException {
		logicBoard  = new LogicBoard();
		graphicBoard = new GraphicBoard(this);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (e.getButton() == 3) {
			dragClick = e;
		} else {
			dragClick = null;
		}

		if (e.getButton() == 1) {
			//ToDo esto es solo una prueba de concepto para ver si funciona lo de repintar el tablero
		
				try {
					this.changeBoard(NOTATION);
				} catch (BoardException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		}
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		if (dragClick != null) {
			// Asi calculo la distancia respecto a la pantalla del punto 00 del frame y
			// puedo desplazarlo consecuentemente
			graphicBoard.setLocation(e.getXOnScreen() - dragClick.getX(), e.getYOnScreen() - dragClick.getY());
		}
	}

	public void changeBoard(String newBoard) throws BoardException  {
		this.graphicBoard.generatePieces(newBoard);
	}
	public int toCoord(double num) {
		return (int) (num / (GraphicBoard.MAX_SIZE / (double) GraphicBoard.CELL_NUMBER));
	}
	
	public void resetBoard() throws BoardException {
		this.graphicBoard.generatePieces(null);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getButton() == 2) {
			graphicBoard.close(); //Poniendolo aqui si el usuario se arrepiente, puede cancelar
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// No es necesario para este programa
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// No es necesario para este programa
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// No es necesario para este programa
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// No es necesario para este programa
	}

}
