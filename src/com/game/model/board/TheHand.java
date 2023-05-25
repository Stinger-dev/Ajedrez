package com.game.model.board;

import java.awt.event.MouseEvent;

import javax.swing.event.MouseInputListener;

import com.game.model.pieces.Colour;

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
public class TheHand implements MouseInputListener {
	
	//TODO: Coronar

	
	private LogicBoard logicBoard; 
	private GraphicBoard graphicBoard ;

	// Asi puedo controlar que solo sea el click derecho el que permita arrastrar,
	// no se puede poner la concicional en el drag ya que siempre devuelve 0, no se
	// muy bien porque
	private MouseEvent dragClick;
	
	private MouseEvent firstClick;

	
	
	
	public TheHand() throws BoardException {
		logicBoard  = new LogicBoard();
		graphicBoard = new GraphicBoard(this);
		this.logicBoard.obtainNotation();
	}
	
	public void changeBoard(String newBoard) throws BoardException  {
		this.graphicBoard.generatePieces(newBoard);
	}
	
	public int toCoord(int num) {
		return (int) (num / (Constantes.MAX_SIZE / (double) Constantes.CELL_NUMBER));
	}
	
	/**
	 * Reset the current board to a standard board
	 */
	public void resetBoard() throws BoardException {
		this.graphicBoard.generatePieces(null);
		this.logicBoard = new LogicBoard();
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (e.getButton() == 3) {
			dragClick = e;
		} else {
			dragClick = null;
		}
		
		if (e.getButton() == 1) {
			firstClick = e;
		} else {
			firstClick = null;
		}
	

	}

	@Override
	public void mouseDragged(MouseEvent e) {
		if (dragClick != null) {
			/*
			 * Siempre da 0 cuando hay un drag, asi que es mejor hacerlo asi si quiero que la unica forma de que se mueva sea con el click derecho 
			 */

			// Asi calculo la distancia respecto a la pantalla del punto 00 del frame y
			// puedo desplazarlo consecuentemente
			graphicBoard.setLocation(e.getXOnScreen() - dragClick.getX(), e.getYOnScreen() - dragClick.getY());
			graphicBoard.setSize(Constantes.MAX_SIZE, Constantes.MAX_SIZE);	
			 //Con esto añado soporte a cambio de pantallas
			}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getButton() == 2) {
			graphicBoard.close(); //Poniendolo aqui si el usuario se arrepiente, puede cancelar
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (e.getButton() == 3) {
			//Con esto soluciono un bug que al mover el juego entre diferentes pantallas cambiaba de tamaño
			graphicBoard.setSize(Constantes.MAX_SIZE, Constantes.MAX_SIZE);	
			
			
			//Esto soluciona un bug en el que si click izq y luego click derecho, aunque soltaras el izq, podias mover el tablero con el derecho
			//Pasaba porque la orden de poner drag a null solo pasaba si se pulsaba algo nuevo, al ponerlo tambien al soltar evito que sea activado con otro
			dragClick = null; 
			

		}
		
		if (e.getButton() == 1 && firstClick != null) {


			try {
				String tmp = this.logicBoard.moveCoordToCoord(toCoord(this.firstClick.getX()), toCoord(this.firstClick.getY()),
						toCoord(e.getX()), toCoord(e.getY()));	
				
				if(!tmp.contains("KI")) { //Si no tiene el rey significa que ha perdido
					this.graphicBoard.endMatch(Colour.WHITE);
				}else if(!tmp.contains("ki")) {
					this.graphicBoard.endMatch(Colour.BLACK);

				}else {
					this.graphicBoard.generatePieces(tmp);					
				}

			} catch (BoardException e1) {
				e1.printStackTrace();
			}

		}
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
