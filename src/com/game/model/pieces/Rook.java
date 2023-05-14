package com.game.model.pieces;

public class Rook extends Piece implements LinealMove{


	public Rook(int x, int y, Colour c) {
		super(x, y, c);
	}

	@Override
	public boolean canMoveTo(int x2, int y2, Piece[][] board) {
		return canLinealMove(this.xPosition, this.yPosition, x2, y2, board);
	}


}
