package com.game.model.pieces;

public class Rook extends Piece implements LinealMove{
	private boolean moved;


	public Rook(int x, int y, Colour c) {
		super(x, y, c);
		this.moved = false;

	}
	public Rook(Rook tmp) {
		super(tmp.getxPosition(), tmp.getyPosition(), tmp.getColour());
		this.moved = tmp.isMoved();
	}

	@Override
	public boolean canMoveTo(int x2, int y2, Piece[][] board) {
		boolean output = canLinealMove(this.xPosition, this.yPosition, x2, y2, board);
		if(output) {
			this.moved = true;
		}
		
		return output;
	}
	public boolean isMoved() {
		return moved;
	}
	
	


}
