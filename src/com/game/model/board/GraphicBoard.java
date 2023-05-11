package com.game.model.board;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.event.MouseInputListener;

import com.game.model.pieces.Bishop;
import com.game.model.pieces.Colour;
import com.game.model.pieces.King;
import com.game.model.pieces.Knight;
import com.game.model.pieces.Pawn;
import com.game.model.pieces.Piece;
import com.game.model.pieces.Queen;
import com.game.model.pieces.Rook;

public class GraphicBoard extends JFrame  {
	private static final int 		CELL_SIZE = 64;
	protected static final int 		CELL_NUMBER = 8;
	protected static final int 		MAX_SIZE = CELL_SIZE * CELL_NUMBER;
	private static final Color		WHITE_PIECE_COLOR = new Color(235,235,211);//Asi es mas facil hacer cambios esteticos en un futuro
	private static final Color		BLACK_PIECE_COLOR = new Color(116,148,84);
	private static final boolean	RESIZABLE = false;
	private static final boolean	UNDECORED = true;

	private static final String		ORIGINAL_DISPLAY =  "R-0-0,KN-1-0,B-2-0,Q-3-0,K-4-0,B-5-0,KN-6-0,R-7-0,"+//mayusculas es negra, la primera es x y la segunda y
														"P-0-1,P-1-1,P-2-1,P-3-1,P-4-1,P-5-1,P-6-1,P-7-1,"
													   +"p-0-6,p-1-6,p-2-6,p-3-6,p-4-6,p-5-6,p-6-6,p-7-6,"
													   +"r-0-7,kn-1-7,b-2-7,q-3-7,k-4-7,b-5-7,kn-6-7,r-7-7";
	
	private static final String 	RUTA_IMAGENES = System.getProperty("user.dir")+"\\resources\\img\\";
	
	private static JLayeredPane tablero = new JLayeredPane(); //Solo dios sabe lo que me ha costado encontrar esta cosa y hacerla funcionar, pero tambien lo que me soluciona la vida

	
	public GraphicBoard(TheHand hand) {
		settingsFrame();
		generarTablero();
		generatePieces(ORIGINAL_DISPLAY);
		this.addMouseListener(hand);
		this.addMouseMotionListener(hand);

		
		this.setVisible(true); //ES MUY IMPORTANTE QUE ESTE AL FINAL, SI NO SOLO SERA VISIBLE PARTE, ME PASE MEDIA H POR ESTA TONTERIA Y NI IDEA DE PQ PASA
	}
	private void settingsFrame() {
		this.setSize(MAX_SIZE, MAX_SIZE);
		this.setResizable(RESIZABLE);
		this.setUndecorated(UNDECORED);
		this.setLocationRelativeTo(null); //Poniendolo en null hacemos que aparezca en el centro de la pantalla
		this.setTitle("Ajedrez para nada hecho bajo crunch");//cambiamos el nombre de la ventana
		try {
			this.setIconImage(ImageIO.read(new File(RUTA_IMAGENES+"logo.jpg"))); //Asi le ponemos una imagen a la pestaña en la barra
		} catch (IOException e) {
			//Si no es capaz de encotrar el logo, tampoco pasa mucho, simplemente aparece el logo de java
		}	
		this.setDefaultCloseOperation(EXIT_ON_CLOSE); //Al cerra ser termina el programa

		
		
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
		this.setContentPane(tablero);
		
	}

	
	private JLabel generarCasilla(int x, int y, boolean white){ 
		/**
		 * Generamos una casilla en la posicion determinada dada un color y las coordenadas "ajedrecisticas"
		 */
		JLabel etiqueta = new JLabel(); //declaramos la etiqueta
		etiqueta.setBounds(x*CELL_SIZE, y*CELL_SIZE, CELL_SIZE, CELL_SIZE); //damos tamaño y posicion a la etiqueta
		etiqueta.setOpaque(true); //Hacemos que la etiqueta sea solida
		etiqueta.setBackground((white)? WHITE_PIECE_COLOR : BLACK_PIECE_COLOR);
		etiqueta.setVisible(true);
		return etiqueta;
	}
	
	

	private static Image loadPicture(Piece c) {
		/**
		 * Analiza la pieza pasada por parametro y selecciona la imagen correspondiente 
		 * Para que funcione debe seguir el pormato  COLO/TYPE.png
		 */

		return  new ImageIcon(RUTA_IMAGENES+c.getRutaImage()).getImage();
	}
	
	public void clearAllPieces() {
		while(tablero.getComponentCount()>(CELL_NUMBER*CELL_NUMBER)) {
			tablero.remove(0);
		}

		
	}	
	
	public void generatePieces(String notation) {
		clearAllPieces();
		for(String splitPieza : notation.split(",")) {
			String[] splitDatos = splitPieza.split("-");
			int x = Integer.parseInt(splitDatos[1]);
			int y = Integer.parseInt(splitDatos[2]);

			Colour color = (splitDatos[0].equals(splitDatos[0].toUpperCase()))? Colour.BLACK : Colour.WHITE;
			JLabel etiqueta = new JLabel(); //TODO cambiar el caso default
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
			etiqueta.setBounds(tmpPiece.getxPosition()*CELL_SIZE, tmpPiece.getyPosition()*CELL_SIZE, CELL_SIZE, CELL_SIZE);
			tablero.add(etiqueta,0);
			
		}
		System.out.println(tablero.getComponentCount());
	
	}
	
	public int toCoord(double num) {
		return (int)(num/(MAX_SIZE/(double)CELL_NUMBER));
	}
	
	public void close() {
		this.dispose();
	}
	
	
	
	


}
