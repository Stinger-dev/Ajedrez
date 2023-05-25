package com.game.model.board;

import java.awt.Color;

public class Constantes {
	
	protected static final int CELL_SIZE = 64;
	public static final int CELL_NUMBER = 8;
	public static final int MAX_SIZE = CELL_SIZE * CELL_NUMBER;
	protected static final Color WHITE_PIECE_COLOR = new Color(235, 235, 211);// This way is easier to change colors in the future
	protected static final Color BLACK_PIECE_COLOR = new Color(116, 148, 84);
	protected static final boolean RESIZABLE = false;
	protected static final boolean UNDECORED = true;
	protected static final boolean FREE_MOVE = false;

	public static final String ORIGINAL_DISPLAY =  "RO-0-0,KN-1-0,BI-2-0,QU-3-0,KI-4-0,BI-5-0,KN-6-0,RO-7-0,"+ 
			   "PA-0-1,PA-1-1,PA-2-1,PA-3-1,PA-4-1,PA-5-1,PA-6-1,PA-7-1,"+ 
			   "pa-0-6,pa-1-6,pa-2-6,pa-3-6,pa-4-6,pa-5-6,pa-6-6,pa-7-6,"+ 
			   "ro-0-7,kn-1-7,bi-2-7,qu-3-7,ki-4-7,bi-5-7,kn-6-7,ro-7-7";

}
