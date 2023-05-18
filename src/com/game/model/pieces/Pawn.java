package com.game.model.pieces;

public class Pawn extends Piece {

	public Pawn(int x, int y, Colour c) {
		super(x, y, c);
	}


	@Override
	public boolean canMoveTo(int x2, int y2, Piece[][] board ) {
		boolean output = false;
		if(this.xPosition == x2) {
			int tmp = (this.colour.equals(Colour.BLACK))? 1 : -1;
			
			int totalMove = (y2 - this.yPosition)*tmp;
			if( board[x2][y2] == null &&(totalMove == 1 ) || (totalMove == 2 && (this.yPosition == 1 || this.yPosition == 6)&& board[this.xPosition][this.yPosition+tmp] == null)) {
				output = true;
			}
		}else {
			int tmp = (this.colour.equals(Colour.BLACK))? 1 : -1 ; //Segun el color de la pieza varia la direccion y asi el signo de la resta, asi que asi lo ajusto
			int totalMove = (y2 - this.yPosition)*tmp;
			if(totalMove == 1 && board[x2][y2] != null) {
				output = true;
			}

		}
		
		
		
		return output;
	}
}
