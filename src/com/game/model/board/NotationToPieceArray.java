package com.game.model.board;

import com.game.model.pieces.Bishop;
import com.game.model.pieces.Colour;
import com.game.model.pieces.King;
import com.game.model.pieces.Knight;
import com.game.model.pieces.Pawn;
import com.game.model.pieces.Piece;
import com.game.model.pieces.Queen;
import com.game.model.pieces.Rook;

public interface NotationToPieceArray {
	// mayusculas es negra, la primera es x y la segunda y 
	// r = rook, n = knight, b = bishop, q = queen, k = king, p = pawn
	public static final String ORIGINAL_DISPLAY =  "R-0-0,N-1-0,B-2-0,Q-3-0,K-4-0,B-5-0,N-6-0,R-7-0,"+ 
												   "P-0-1,P-1-1,P-2-1,P-3-1,P-4-1,P-5-1,P-6-1,P-7-1,"+ 
												   "p-0-6,p-1-6,p-2-6,p-3-6,p-4-6,p-5-6,p-6-6,p-7-6,"+ 
												   "r-0-7,n-1-7,b-2-7,q-3-7,k-4-7,b-5-7,n-6-7,r-7-7";
	public static final Integer DEFAULT_CELLS_NUMBER = 8;

	public default Piece[][] notationToPieceArray(String notation, Integer cellsNumber) throws BoardException {
		/**
		 * null in both will return the default notation (normal chess) at the default size(8)
		 */
		if(notation == null) {
			notation = ORIGINAL_DISPLAY;
		}
		if(cellsNumber == null) {
			cellsNumber = DEFAULT_CELLS_NUMBER;

		}
		
		
		Piece[][] output = new Piece[cellsNumber][cellsNumber];
		

		for (String splitPiece : notation.split(",")) {
			String[] splitCoord = splitPiece.split("-");
			int x = Integer.parseInt(splitCoord[1]);
			int y = Integer.parseInt(splitCoord[2]);

			Colour side = (splitCoord[0].equals(splitCoord[0].toUpperCase())) ? Colour.BLACK : Colour.WHITE;
			Piece tmpPiece = null;
			switch (splitCoord[0].toLowerCase()) {
			case "b":
				tmpPiece = new Bishop(x, y, side);
				break;
			case "k":
				tmpPiece = new King(x, y, side);
				break;
			case "n":
				tmpPiece = new Knight(x, y, side);
				break;
			case "p":
				tmpPiece = new Pawn(x, y, side);
				break;
			case "q":
				tmpPiece = new Queen(x, y, side);
				break;
			case "r":
				tmpPiece = new Rook(x, y, side);
				break;
			default:
				throw new BoardException("Error interpreting the notation");
			}
			if(x>=0 && x< cellsNumber && y>=0 && y<cellsNumber) {				
				output[x][y] = tmpPiece;
			}else {
				throw new BoardException("Notation not valid for that board size");
			}

			

		}
		return output;

	}
}
