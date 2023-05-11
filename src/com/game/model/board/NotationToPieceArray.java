package com.game.model.board;

import javax.swing.JLabel;

import com.game.model.pieces.Bishop;
import com.game.model.pieces.Colour;
import com.game.model.pieces.King;
import com.game.model.pieces.Knight;
import com.game.model.pieces.Pawn;
import com.game.model.pieces.Piece;
import com.game.model.pieces.Queen;
import com.game.model.pieces.Rook;

public interface NotationToPieceArray {

	public default Piece[][] notationToPieceArray(String notation, int cellsNumber) {
		Piece[][] resultado = new Piece[cellsNumber][cellsNumber];

		for (String splitPieza : notation.split(",")) {
			String[] splitDatos = splitPieza.split("-");
			int x = Integer.parseInt(splitDatos[1]);
			int y = Integer.parseInt(splitDatos[2]);

			Colour color = (splitDatos[0].equals(splitDatos[0].toUpperCase())) ? Colour.BLACK : Colour.WHITE;
			JLabel etiqueta = new JLabel();
			Piece tmpPiece = null;
			switch (splitDatos[0].toLowerCase()) {
			case "b":
				tmpPiece = new Bishop(x, y, color);
				break;
			case "k":
				tmpPiece = new King(x, y, color);
				break;
			case "n":
				tmpPiece = new Knight(x, y, color);
				break;
			case "p":
				tmpPiece = new Pawn(x, y, color);
				break;
			case "q":
				tmpPiece = new Queen(x, y, color);
				break;
			case "r":
				tmpPiece = new Rook(x, y, color);
				break;
			default:
				System.out.println(splitDatos[0].toLowerCase());
			}
			
			resultado[x][y] = tmpPiece;

		}
		return resultado;

	}
}
