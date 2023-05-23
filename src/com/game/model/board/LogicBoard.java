package com.game.model.board;


import com.game.model.pieces.Colour;
import com.game.model.pieces.King;
import com.game.model.pieces.Piece;
import com.game.model.pieces.Rook;

public class LogicBoard implements NotationToPieceArray{
	
	private  Piece[][] board;
	private Colour nextMove = Colour.WHITE;
	
	
	/**
	 * Genera el tablero con los datos pasados por parametro, en caso de que el tablero y la notacion no se correspondan,
	 * lanzara una excepcion
	 */
	public LogicBoard(String notaion, Integer cellNumber) throws BoardException {
		board = notationToPieceArray(notaion, cellNumber);
	}
	
	/**
	 * Genera el array de piezas sin modificaciones y de 0
	 */
	public LogicBoard() throws BoardException {
		this(null,null);
	}
	
	public String moveCoordToCoord(int x1, int y1, int x2, int y2) {
		
		
		if(x2>=0 && y2>=0 && x2<Constantes.CELL_NUMBER && y2<Constantes.CELL_NUMBER && this.board[x1][y1] != null 
				&& (this.board[x1][y1].getColour().equals(this.nextMove)) //Para movimiento libre simplemente comentar esto
				) {
			
			if((this.board[x2][y2] == null || !this.board[x2][y2].getColour().equals(this.board[x1][y1].getColour())) && (this.board[x1][y1].move(x2, y2, board))) {
				move(x1, y1, x2, y2);
			}
			else if(this.board[x2][y2] instanceof Rook && this.board[x1][y1] instanceof King 
					&& this.board[x1][y1].getColour().equals(this.board[x2][y2].getColour())) {
				castling(x1, y1, x2, y2);
			}		
		}	
		return this.obtainNotation();
	}
	
	private void castling(int x1, int y1, int x2, int y2) {
		int y = this.board[x1][y1].getyPosition();
		int tmpX = this.board[x2][y2].getxPosition();
		if(((King)this.board[x1][y1]).move(x2, y2, board)){
			this.board[x2][y2] = null;
			this.board[x1 + ((tmpX == 0)? -2 : 2)][y2] = this.board[x1][y1].clone();
			this.board[(tmpX == 0)? 3 : 5][y1] = new Rook((tmpX == 0)? 3 : 5, y, this.board[x1][y1].getColour());
			this.board[x1][y1] = null;
			this.nextMove = (this.nextMove.equals(Colour.WHITE))? Colour.BLACK : Colour.WHITE; //Una vez el movimiento ser ha hecho, se cambia quien juega despues
		}
	}
	
	private void move(int x1, int y1, int x2, int y2) {

		this.board[x2][y2] = null;
		this.board[x2][y2] = this.board[x1][y1].clone();
		this.board[x1][y1] = null;
		this.nextMove = (this.nextMove.equals(Colour.WHITE))? Colour.BLACK : Colour.WHITE; //Una vez el movimiento ser ha hecho, se cambia quien juega despues

	}
	

	
	public String obtainNotation() {
		StringBuilder output = new StringBuilder();
		
		for(Piece[] tmpX : board) {
			for(Piece tmpY : tmpX) {
				if(tmpY!= null) {	
					output.append(tmpY.toString()).append(",");
				}
			}
		}
		return output.toString();	
	}

}