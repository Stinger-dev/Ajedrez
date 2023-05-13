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
	/*
	public static final String ORIGINAL_DISPLAY =  "RO-0-0,KN-1-0,BI-2-0,QU-3-0,KI-4-0,BI-5-0,KN-6-0,RO-7-0,"+ 
												   "PA-0-1,PA-1-1,PA-2-1,PA-3-1,PA-4-1,PA-5-1,PA-6-1,PA-7-1,"+ 
												   "pa-0-6,pa-1-6,pa-2-6,pa-3-6,pa-4-6,pa-5-6,pa-6-6,pa-7-6,"+ 
												   "ro-0-7,kn-1-7,bi-2-7,qu-3-7,ki-4-7,bi-5-7,kn-6-7,ro-7-7";
	*/
	
	
	public static final String ORIGINAL_DISPLAY =	"PA-3-3,BI-4-4";
	public static final Integer DEFAULT_CELLS_NUMBER = GraphicBoard.CELL_NUMBER;

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
			case "bi":
				tmpPiece = new Bishop(x, y, side);
				break;
			case "ki":
				tmpPiece = new King(x, y, side);
				break;
			case "kn":
				tmpPiece = new Knight(x, y, side);
				break;
			case "pa":
				tmpPiece = new Pawn(x, y, side);
				break;
			case "qu":
				tmpPiece = new Queen(x, y, side);
				break;
			case "ro":
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
