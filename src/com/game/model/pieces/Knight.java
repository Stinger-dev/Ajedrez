package com.game.model.pieces;

public class Knight extends Piece {

	public Knight(int x, int y, Colour c) {
		super(x, y, c);
	}

	@Override
	public boolean canMoveTo(int x2, int y2, Piece[][] board) {
		return (Math.abs(this.xPosition - x2 ) == 2 && Math.abs(this.yPosition-y2) == 1) || (Math.abs(this.xPosition - x2 ) == 1 && Math.abs(this.yPosition-y2) == 2);
	}



}
