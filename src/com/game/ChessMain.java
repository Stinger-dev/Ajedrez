package com.game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import com.game.model.pieces.Bishop;
import com.game.model.pieces.Colour;
import com.game.model.pieces.Piece;

public class ChessMain {

	private static final int 		CELL_SIZE = 64;
	public static final int 		CELL_NUMBER = 8;
	private static final int 		MAX_X_SIZE = CELL_SIZE * CELL_NUMBER;//+14
	private static final int 		MAX_Y_SIZE = CELL_SIZE * CELL_NUMBER;//+37
	private static final int 		START_POSITION = 30; //Lugar absoluto donde empieza a dibujar el marco

	
	public static void main(String[] args) {
		
		setUpBoard();

		
	}


	private static void setUpBoard() {
		JFrame frame = new JFrame();
		frame.setBounds(START_POSITION, START_POSITION, MAX_X_SIZE, MAX_Y_SIZE);
		frame.setUndecorated(true); //asi puedo quitar los bordes de la pestaña
		
		List<Cell> cells = new ArrayList<>();
		
		
		JPanel panel = new JPanel() {
			@Override
			public void paint(Graphics g) {
				boolean black = false;
				for(int i=0; i<CELL_NUMBER; i++) {
					for(int j=0; j<CELL_NUMBER; j++) {
						g.setColor(black? Color.green.darker() : Color.white);
						
						g.fillRect(i*CELL_SIZE, j*CELL_SIZE, CELL_SIZE, CELL_SIZE);
						
						cells.add(new Cell(i*CELL_SIZE, j*CELL_SIZE, CELL_SIZE, CELL_SIZE, null));
						black=!black;
					}
					black=!black;

				}
				//Bishop ob1 = new Bishop(0, 0, Colour.WHITE);
				//g.drawImage(loadPicture(ob1), ob1.getxPosition()*64, ob1.getyPosition()*64, this);//Aqui habria que poner la pieza
	
			}
		};
		
		
		frame.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("----");
				System.out.println(e.getButton());
				System.out.println(e.getX());
				System.out.println(e.getY());
				
				if(e.getButton() == 2) {	
					frame.dispose();
				}
				

			}
			
			@Override
			public void mouseReleased(MouseEvent e) {
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
	
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				
				
			}
			
		});
			
		frame.add(panel);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	

	

	
}
