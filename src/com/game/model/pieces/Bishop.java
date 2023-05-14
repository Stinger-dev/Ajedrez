package com.game.model.pieces;


public class Bishop extends Piece {

	public Bishop(int x, int y, Colour c) {
		super(x, y, c);
	}


	@Override
	public boolean canMoveTo(int x2, int y2, Piece[][] board ) {
		/**
		 * El el movimiento cumple la norma de |x1 - x2| = |y1 - y2| o |x1 - y1| = |x2 - y2|Tambien deben de
		 * ser distintos a la posicion actual y ademas ser mayores que 0
		 */
		boolean output = false;
		if (this.yPosition != y2 && this.xPosition != x2 && (Math.abs(this.xPosition - this.yPosition) == Math.abs(x2 - y2)
				|| Math.abs(this.xPosition - x2) == Math.abs(this.yPosition - y2) )) {
			int addX = (this.xPosition > x2)? -1 : 1;
			int addY = (this.yPosition > y2)? -1 : 1;
			boolean colision = false;
			for(int i = 1; (this.xPosition + i*addX) != x2 && (this.yPosition + i*addY)!=y2 && !colision; i++) {
				if(board[this.xPosition + i*addX][this.yPosition + i*addY] != null) {
					colision = true;
				}
			}
			output = !colision;
		}
		return output;
	}

}
