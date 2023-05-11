package com.game.model.board;

public class GraphicBoardException extends Exception {

	public GraphicBoardException() {
		super();
	}

	public GraphicBoardException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public GraphicBoardException(String message, Throwable cause) {
		super(message, cause);
	}

	public GraphicBoardException(String message) {
		super(message);
	}

	public GraphicBoardException(Throwable cause) {
		super(cause);
	}

}
