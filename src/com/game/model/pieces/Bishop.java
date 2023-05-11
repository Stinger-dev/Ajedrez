package com.game.model.pieces;

public class Bishop extends Piece {

	public Bishop(int x, int y, Colour c) {
		super(x, y, c);
	}

	@Override
	protected void move(int x, int y) {
		this.xPosition = x;
		this.yPosition = y;
	}

	public boolean canMoveTo(int x, int y) {
		/**
		 * El el movimiento cumple la norma de |x1 - x2| = |y1 - y2| Tambien deben de
		 * ser distintos a la posicion actual y ademas ser mayores que 0
		 */
		return (x > 0 && y > 0 && y != this.yPosition && this.xPosition != x
				&& (Math.abs(this.xPosition - this.yPosition) == Math.abs(this.yPosition - y)));
	}
}
