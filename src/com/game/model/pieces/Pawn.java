package com.game.model.pieces;

public class Pawn extends Piece {

	public Pawn(int x, int y, Colour c) {
		super(x, y, c);
	}


	@Override
	public boolean canMoveTo(int x2, int y2, Piece[][] board ) {
		boolean output = false;
		if(this.xPosition == x2) {
			if(this.colour == Colour.BLACK ) {
				int totalMove = y2 - this.yPosition;
				if(totalMove == 1 || (totalMove == 2 && this.yPosition == 1)) {
					output = true;
				}
				
			}else {
				int totalMove = this.yPosition - y2; //asi tambien me aseguro que no vaya en direccion inversa
				if(totalMove == 1 || (totalMove == 2 && this.yPosition == 6)) {
					output = true;
				}
			}
		}
		return output;
	}
}
