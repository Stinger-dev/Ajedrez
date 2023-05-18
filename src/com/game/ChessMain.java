package com.game;

import java.util.Scanner;

import com.game.model.board.BoardException;
import com.game.model.board.TheHand;

public class ChessMain {
	public static final Scanner teclado = new Scanner(System.in);

	public static void main(String[] args) {

		TheHand ob1 = null;
		try {
			ob1 = new TheHand();
			ob1.hashCode();

		} catch (BoardException e) {
			e.printStackTrace();
		}



	}
}
