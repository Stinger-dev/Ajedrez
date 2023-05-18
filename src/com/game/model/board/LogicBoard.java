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
		
		
		if(x2>=0 && y2>=0 && x2<GraphicBoard.CELL_NUMBER && y2<GraphicBoard.CELL_NUMBER && this.board[x1][y1] != null 
				//&& (this.board[x1][y1].getColour().equals(this.nextMove)) //Para movimiento libre simplemente comentar esto
				) {
			if((this.board[x2][y2] == null || !this.board[x2][y2].getColour().equals(this.board[x1][y1].getColour())) && (this.board[x1][y1].move(x2, y2, board))) {
				this.board[x2][y2] = null;
				this.board[x2][y2] = this.board[x1][y1];
				this.board[x1][y1] = null;
				this.nextMove = (this.nextMove.equals(Colour.WHITE))? Colour.BLACK : Colour.WHITE; //Una vez el movimiento ser ha hecho, se cambia quien juega despues

			}
			/*else if(this.board[x2][y2] instanceof Rook && this.board[x1][y1] instanceof King 
					&& this.board[x1][y1].getColour().equals(this.board[x2][y2].getColour())
					&& ((King)this.board[x1][y1]).canCastling(x2, y2, board)
					) {
				Rook tmp = new Rook((Rook)this.board[x2][y2]);
				
				this.board[x2][y2].setxPosition(this.board[x1][y1].getxPosition());
				this.board[x2][y2].setxPosition(this.board[x1][y1].getyPosition());
				this.board[x1][y1].setxPosition(tmp.getxPosition());
				this.board[x1][y1].setxPosition(tmp.getyPosition());

				this.board[x2][y2] = null;
				this.board[x2][y2] = this.board[x1][y1];
				this.board[x1][y1] = null;
				this.board[x1][y1] = tmp;
			
				
				
				

				System.out.println("askjh");

			}*/
		
			
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