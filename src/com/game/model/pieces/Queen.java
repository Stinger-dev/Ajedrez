package com.game.model.pieces;

public class Queen extends Piece implements LinealMove, DiagonalMove{

	public Queen(int x, int y, Colour c)  {
		super(x, y, c);
	}

	@Override
	public boolean canMoveTo(int x2, int y2, Piece[][] board) {
		return canDiagonalMove(this.xPosition, this.yPosition, x2, y2, board) || canLinealMove(this.xPosition, this.yPosition, x2, y2, board);
	}



}
