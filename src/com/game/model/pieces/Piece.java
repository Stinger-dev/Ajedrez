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

	public boolean move(int x2, int y2, Piece[][] board) {
		/*	
		 * Return true and change values if the move can be done
		 */
		boolean output = false;
		if((this.yPosition!=y2 || this.xPosition!=x2) && this.canMoveTo(x2, y2, board) ) {
			this.xPosition = x2;
			this.yPosition = y2;
			output = true;
		}
		return output;
	}
	
	public abstract boolean canMoveTo(int x2, int y2, Piece[][] board );
	
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
		String pieceType = 	this.getClass().getSimpleName().substring(0, 2).toLowerCase();
		
		if(this.colour.equals(Colour.BLACK)) {
			pieceType = pieceType.toUpperCase();
		}
		
		return pieceType+ "-" + this.xPosition + "-" + this.yPosition;
		
	}

	public String getPieceImagePath() {
		return this.getColour() + "/" + this.getClass().getSimpleName().toLowerCase() + ".png";
	}

	public void setxPosition(int xPosition) {
		this.xPosition = xPosition;
	}

	public void setyPosition(int yPosition) {
		this.yPosition = yPosition;
	}
	
	public Piece clone() {
		return this;
	}

}
