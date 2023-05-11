package com.game;

import java.awt.Color;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;

import com.game.model.Bishop;
import com.game.model.Colour;
import com.game.model.King;
import com.game.model.Knight;
import com.game.model.Pawn;
import com.game.model.Piece;
import com.game.model.Queen;
import com.game.model.Rook;

public class GraphicBoard extends JFrame {
	private static final int 		CELL_SIZE = 64;
	public static final int 		CELL_NUMBER = 8;
	private static final int 		MAX_X_SIZE = CELL_SIZE * CELL_NUMBER+14;
	private static final int 		MAX_Y_SIZE = CELL_SIZE * CELL_NUMBER+37; //Hasta que aprenda a hacerlo que se pueda arrastrar
	private static final Color		WHITE_PIECE_COLOR = new Color(235,235,211);//Asi es mas facil hacer cambios esteticos en un futuro
	private static final Color		BLACK_PIECE_COLOR = new Color(116,148,84);
	private static final boolean	RESIZABLE = false;
	private static final String		ORIGINAL_DISPLAY =  "R-0-0,KN-1-0,B-2-0,Q-3-0,K-4-0,B-5-0,KN-6-0,R-7-0,"+//mayusculas es negra, la primera es x y la segunda y
														"P-0-1,P-1-1,P-2-1,P-3-1,P-4-1,P-5-1,P-6-1,P-7-1,"
													   +"p-0-6,p-1-6,p-2-6,p-3-6,p-4-6,p-5-6,p-6-6,p-7-6,"
													   +"r-0-7,kn-1-7,b-2-7,q-3-7,k-4-7,b-5-7,kn-6-7,r-7-7";
	
	private static String 			RUTA = System.getProperty("user.dir")+"\\resources\\img\\";
	
	private static JLayeredPane tablero = new JLayeredPane(); //Solo dios sabe lo que me ha costado encontrar esta cosa y hacerla funcionar, pero tambien lo que me soluciona la vida

	
	public GraphicBoard() {
		settingsFrame();
		generarTablero();
		generatePiecesInitial();
		
		
		this.setVisible(true); //ES MUY IMPORTANTE QUE ESTE AL FINAL, SI NO SOLO SERA VISIBLE PARTE, ME PASE MEDIA H POR ESTA TONTERIA Y NI IDEA DE PQ PASA
	}
	private void settingsFrame() {
		this.setSize(MAX_X_SIZE, MAX_Y_SIZE);
		this.setResizable(RESIZABLE);
		this.setLocationRelativeTo(null); //Poniendolo en null hacemos que aparezca en el centro de la pantalla
		this.setTitle("Ajedrez para nada hecho bajo crunch");//cambiamos el nombre de la ventana
		try {
			this.setIconImage(ImageIO.read(new File(RUTA+"logo.jpg"))); //Asi le ponemos una imagen a la pestaña en la barra
		} catch (IOException e) {
			//Si no es capaz de encotrar el logo, tampoco pasa mucho, simplemente aparece el logo de java
		}	
		this.setDefaultCloseOperation(EXIT_ON_CLOSE); //Al cerra ser termina el programa


		
	}	
	private JLabel generarCasilla(int x, int y, boolean white){ 
		/**
		 * Generamos una casilla en la posicion determinada dada un color y las coordenadas "ajedrecisticas"
		 */
		JLabel etiqueta = new JLabel(); //declaramos la etiqueta
		etiqueta.setBounds(x*CELL_SIZE, y*CELL_SIZE, CELL_SIZE, CELL_SIZE); //damos tamaño y posicion a la etiqueta
		etiqueta.setOpaque(true); //Hacemos que la etiqueta sea solida
		etiqueta.setBackground((white)? WHITE_PIECE_COLOR : BLACK_PIECE_COLOR);
		return etiqueta;
	}
	
	private void generarTablero() {
		tablero.setLayout(null);//Con esto evitamos que las etiqueta se vaya al centro

		boolean blanco = true;
		
		for(int j = 0; j<CELL_NUMBER; j++) {
			for(int i = 0; i<CELL_NUMBER; i++) {			
				tablero.add(generarCasilla(i, j, blanco), 0);
				blanco = !blanco;
			}
			blanco = !blanco;
		}	
		this.getContentPane().add(tablero);
		
	}
	
	
	

	private static Image loadPicture(Piece c) {
		/**
		 * Analiza la pieza pasada por parametro y selecciona la imagen correspondiente 
		 * Para que funcione debe seguir el pormato  COLO/TYPE.png
		 */

		return  new ImageIcon(RUTA+c.getColour()+"\\"+c.getClass().getSimpleName().toLowerCase()+".png").getImage();
	}
	
	public void clearAllPieces() {
		this.tablero.remove(1);
	}	
	
	public void generatePiecesInitial() {
		for(String splitPieza : ORIGINAL_DISPLAY.split(",")) {
			String[] splitDatos = splitPieza.split("-");
			int x = Integer.valueOf(splitDatos[1]);
			int y = Integer.valueOf(splitDatos[2]); 

			Colour color = (splitDatos[0].equals(splitDatos[0].toUpperCase()))? Colour.BLACK : Colour.WHITE;
			JLabel etiqueta = new JLabel(); //TODO cambiar el null
			Piece tmpPiece = null;
			switch (splitDatos[0].toLowerCase()) {
			case "b":
				tmpPiece = new Bishop(x, y, color);
				break;
			case "k":
				tmpPiece = new King(x, y, color);
				break;
			case "kn":
				tmpPiece = new Knight(x, y, color);
				break;
			case "p":
				tmpPiece = new Pawn(x, y, color);
				break;
			case "q":
				tmpPiece = new Queen(x, y, color);
				break;
			case "r":
				tmpPiece = new Rook(x, y, color);
				
				break;
			default:
				tmpPiece = new Bishop(x, y, color);
				break;
			}
			
			
			etiqueta.setIcon(new ImageIcon(loadPicture(tmpPiece).getScaledInstance(CELL_SIZE, CELL_SIZE, CELL_SIZE)));
			System.out.println(tmpPiece.toString());
			etiqueta.setBounds(tmpPiece.getxPosition()*CELL_SIZE, tmpPiece.getyPosition()*CELL_SIZE, CELL_SIZE, CELL_SIZE);
			tablero.add(etiqueta,1);
		}
	
	}
	
	


}
