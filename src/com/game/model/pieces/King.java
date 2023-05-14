package com.game.model.pieces;

public class King extends Piece implements LinealMove, DiagonalMove{

	public King(int x, int y, Colour c) {
		super(x, y, c);
	}

	@Override	
	public boolean canMoveTo(int x2, int y2, Piece[][] board ) {

		boolean output = false;
		if(Math.abs(this.xPosition - x2) <=1 && Math.abs(this.yPosition-y2) <=1) {
			output = canDiagonalMove(this.xPosition, this.yPosition, x2, y2, board) || canLinealMove(this.xPosition, this.yPosition, x2, y2, board);
		}
		return output;
	}
}
