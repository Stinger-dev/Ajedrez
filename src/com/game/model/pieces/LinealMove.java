package com.game.model.pieces;

public interface LinealMove {
		
	public default boolean canLinealMove(int x1, int y1,int x2, int y2, Piece[][] board) {
		boolean output = false;
		if ((x1 - x2 == 0) || (y1 - y2 == 0)) {
	
			int addX = 1;
			if(x1 - x2 == 0) {
				addX = 0;
			}else if (x1 > x2) {
				addX = -1;
			}
			
			int addY = 1;
			if(y1 - y2 == 0) {
				addY = 0;
			}else if (y1 > y2) {
				addY = -1;
			}

			boolean colision = false;
			
			for(int i = 1; (x1 + i*addX) != x2 || (y1 + i*addY)!=y2 && !colision; i++) {
				if(board[x1 + i*addX][y1 + i*addY] != null) {
					colision = true;
				}
			}
			output = !colision;
		}
		return output;
	}

}