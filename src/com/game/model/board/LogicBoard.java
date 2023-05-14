package com.game.model.board;


import com.game.model.pieces.Piece;

public class LogicBoard implements NotationToPieceArray{
	
	private  Piece[][] board ;
	
	
	public LogicBoard(String notaion, Integer cellNumber) throws BoardException {
		/**
		 * Genera el tablero con los datos pasados por parametro, en caso de que el tablero y la notacion no se correspondan,
		 * lanzara una excepcion
		 */
		board = notationToPieceArray(notaion, cellNumber);
	}
	
	public LogicBoard() throws BoardException {
		/**
		 * Genera el array de piezas sin modificaciones y de 0
		 */
		this(null,null);
	}
	
	public String moveCoordToCoord(int x1, int y1, int x2, int y2) {
		
		if(x2>=0 && y2>=0 && x2<GraphicBoard.CELL_NUMBER && y2<GraphicBoard.CELL_NUMBER && this.board[x1][y1] != null && this.board[x2][y2] == null 
				&& (this.board[x1][y1].move(x2, y2, board))) {
			
				this.board[x2][y2] = this.board[x1][y1];	
				this.board[x1][y1] = null;		
		}
		return this.obtainNotation();
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