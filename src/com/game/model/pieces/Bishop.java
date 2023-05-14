package com.game.model.pieces;


public class Bishop extends Piece implements DiagonalMove {

	public Bishop(int x, int y, Colour c) {
		super(x, y, c);
	}


	@Override
	public boolean canMoveTo(int x2, int y2, Piece[][] board ) {
		return canDiagonalMove(this.xPosition, this.yPosition, x2, y2, board);
	}

}
