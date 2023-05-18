package com.game.model.pieces;

public class King extends Piece implements LinealMove, DiagonalMove{
	private boolean moved;

	public King(int x, int y, Colour c) {
		super(x, y, c);
		this.moved = false;
	}

	@Override	
	public boolean canMoveTo(int x2, int y2, Piece[][] board ) {

		boolean output = false;
		if(Math.abs(this.xPosition - x2) <=1 && Math.abs(this.yPosition-y2) <=1) {
			output = canDiagonalMove(this.xPosition, this.yPosition, x2, y2, board) || canLinealMove(this.xPosition, this.yPosition, x2, y2, board);
			this.moved = true;
		}
		return output;
	}
	
	public boolean canCastling(int x2, int y2, Piece[][] board ) {
		boolean output = false;
		if(!((Rook)board[x2][y2]).isMoved() && !this.isMoved()) {
			int addX = 1;
			if(this.xPosition == x2) {
				addX = 0;
			}else if (this.xPosition > x2) {
				addX = -1;
			}
			boolean colision = false;
			
			for(int i = 1; (this.xPosition + i*addX) != x2-addX || (this.yPosition)!=y2 && !colision; i++) {
				if(board[this.xPosition + i*addX][this.yPosition] != null) {
					colision = true;
				}
			}
			output = !colision;
			
			
		}
		
		
		return output;
	}
	
	public boolean isMoved() {
		return moved;
	}
}
