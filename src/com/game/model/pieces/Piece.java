package com.game.model.pieces;

public abstract class Piece {

	protected int xPosition;
	protected int yPosition;
	protected Colour colour;

	protected Piece(int x, int y, Colour c) {
		super();
		this.xPosition = x;
		this.yPosition = y;
		this.colour = c;
	}

	abstract void move(int x, int y);

	public Colour getColour() {
		return colour;
	}

	public int getxPosition() {
		return xPosition;
	}

	public int getyPosition() {
		return yPosition;
	}

	@Override
	public String toString() {
		return this.getClass().getSimpleName() + " x = " + this.xPosition + " y = " + this.yPosition;
	}

	public String getRutaImage() {
		return this.getColour() + "\\" + this.getClass().getSimpleName().toLowerCase() + ".png";
	}
}
