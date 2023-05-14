package com.game.model.pieces;

public class Rook extends Piece {


	public Rook(int x, int y, Colour c) {
		super(x, y, c);
	}

	@Override
	public boolean canMoveTo(int x2, int y2, Piece[][] board) {
		boolean output = false;
		if ((this.xPosition - x2 == 0) || (this.yPosition-y2 == 0)) {
	
			int addX = 1;
			if(this.xPosition - x2 == 0) {
				addX = 0;
			}else if (this.xPosition > x2) {
				addX = -1;
			}
			
			int addY = 1;
			if(this.yPosition-y2 == 0) {
				addY = 0;
			}else if (this.yPosition > y2) {
				addY = -1;
			}

			boolean colision = false;
			
			
			for(int i = 1; (this.xPosition + i*addX) != x2 || (this.yPosition + i*addY)!=y2 && !colision; i++) {
				if(board[this.xPosition + i*addX][this.yPosition + i*addY] != null) {
					colision = true;
				}
			}
			output = !colision;

		}
		return output;
	}


}
