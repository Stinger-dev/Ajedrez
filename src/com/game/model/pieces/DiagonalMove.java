package com.game.model.pieces;

import com.game.model.board.Constantes;

public interface DiagonalMove {

	/**
	 * El el movimiento cumple la norma de |x1 - x2| = |y1 - y2| o |x1 - y1| = |x2 - y2|Tambien deben de
	 * ser distintos a la posicion actual y ademas ser mayores que 0
	 */
	public default boolean canDiagonalMove(int x1, int y1,int x2, int y2, Piece[][] board) {
		boolean output = false;
		if (Math.abs(x1 - y1) == Math.abs(x2 - y2)|| Math.abs(x1 - x2) == Math.abs(y1 - y2) ) {

			int addX = (x1 > x2)? -1 : 1;
			int addY = (y1 > y2)? -1 : 1;
			boolean colision = false;

			for(int i = 1;  (x1 + i*addX) < Constantes.CELL_NUMBER  && (y1 + i*addY) < Constantes.CELL_NUMBER  
					&& (x1 + i*addX) != x2 && (y1 + i*addY)!=y2 && !colision; i++) {
				if(board[x1 + i*addX][y1 + i*addY] != null) {
					colision = true;
				}
			}
			output = !colision;
		}
		return output;
	}
}
