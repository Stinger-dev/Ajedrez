package com.game;

import com.game.model.Piece;

public class Cell {
	
	private int lowerX;
	private int lowerY;
	private int upperX;
	private int upperY;
	
	private Piece piece;

	public Cell(int lowerX, int lowerY, int upperX, int upperY, Piece piece) {
		super();
		this.lowerX = lowerX;
		this.lowerY = lowerY;
		this.upperX = upperX;
		this.upperY = upperY;
		this.piece = piece;
	}
	
	
	

}
