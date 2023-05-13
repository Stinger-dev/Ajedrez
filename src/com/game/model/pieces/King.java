package com.game.model.pieces;

public class King extends Piece {

	public King(int x, int y, Colour c) {
		super(x, y, c);
	}

	@Override
	boolean move(int x, int y, Piece[][] board) {
		return false;
		//ToDo
	}

}
