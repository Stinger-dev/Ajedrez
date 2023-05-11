package com.game;

public class Board {
	/**
	 * T , C , A , D , R , A , C , T ,,88
	 * P , P , P , P , P , P , P , P ,,
	 *   ,   ,   ,   ,   ,   ,   ,   ,,
	 *   ,   ,   ,   ,   ,   ,   ,   ,,
	 *   ,   ,   ,   ,   ,   ,   ,   ,,
	 *   ,   ,   ,   ,   ,   ,   ,   ,,
	 * p , p , p , p , p , p , p , p ,, 
	 * t , c , a , d , r , a , c , t ,,
	 * 11
	 */
	private Cell[][] board = new Cell[8][8];
	private static final int 		CELL_SIZE = 64;
	public static final int 		CELL_NUMBER = 8;
	private static final int 		MAX_X_SIZE = CELL_SIZE * CELL_NUMBER;//+14
	private static final int 		MAX_Y_SIZE = CELL_SIZE * CELL_NUMBER;//+37
	private static final int 		START_POSITION = 30; //Lugar absoluto donde empieza a dibujar el marco
	
	
	private void setUpBoard() {
		
	}

}
