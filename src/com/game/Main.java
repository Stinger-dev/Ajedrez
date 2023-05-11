package com.game;

import java.util.Scanner;

import com.game.model.board.BoardException;
import com.game.model.board.TheHand;

public class Main {
	public static final Scanner teclado = new Scanner(System.in);
	private static final String NOTATION = "R-0-0,N-6-0,R-7-0,P-0-1,P-1-1,P-7-1,p-0-6,p-6-6,p-7-6,r-0-7,n-1-7";
	// mayusculas es negra, la primera es x y la segunda y

	public static void main(String[] args) {

		TheHand ob1 = null;
		try {
			ob1 = new TheHand();
			teclado.nextLine();
			ob1.resetBoard();
			teclado.nextLine();
			ob1.changeBoard(NOTATION);
			ob1.resetBoard();

		} catch (BoardException e) {
			e.printStackTrace();
		}



	}
}
