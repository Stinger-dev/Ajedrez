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
	// ro = rook, kn = knight, bi = bishop, qu = queen, ki = king, pa = pawn



	 
	public static final Integer DEFAULT_CELLS_NUMBER = Constantes.CELL_NUMBER;

	/**
	 * null in both will return the default notation (normal chess) at the default size(8)
	 */
	public default Piece[][] notationToPieceArray(String notation, Integer cellsNumber) throws BoardException {
		if(notation == null) {
			notation = Constantes.ORIGINAL_DISPLAY; 
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
