package com.game.model.board;

import com.game.model.pieces.Piece;

public class LogicBoard implements NotationToPieceArray{
	private static Piece[][] board ;
	
	
	public LogicBoard(String notaion, Integer cellNumber) throws BoardException {
		board = notationToPieceArray(notaion, cellNumber);
	}
	
	public LogicBoard() throws BoardException {
		this(null,null);
	}
	
	
	
	
	
	

	
	

}
