package com.game.model.board;

public class BoardException extends Exception {

	private static final long serialVersionUID = 1L;

	public BoardException() {
		super();
	}

	public BoardException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public BoardException(String message, Throwable cause) {
		super(message, cause);
	}

	public BoardException(String message) {
		super(message);
	}

	public BoardException(Throwable cause) {
		super(cause);
	}

}
