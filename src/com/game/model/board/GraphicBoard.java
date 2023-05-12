package com.game.model.board;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;

import com.game.model.pieces.Piece;



public class GraphicBoard extends JFrame implements NotationToPieceArray{
	//TODO: Externalizar las constantes
	private static final int CELL_SIZE = 64;
	public static final int CELL_NUMBER = 8;
	public static final int MAX_SIZE = CELL_SIZE * CELL_NUMBER;
	private static final Color WHITE_PIECE_COLOR = new Color(235, 235, 211);// This way is easier to change colors in the future
	private static final Color BLACK_PIECE_COLOR = new Color(116, 148, 84);
	private static final boolean RESIZABLE = false;
	private static final boolean UNDECORED = true;

	//TODO: pasar todas las variables y la minima documentacion que hay a Ingles



	private static final String IMG_PATH = System.getProperty("user.dir") + "\\resources\\img\\";

	private static JLayeredPane board = new JLayeredPane();

	public GraphicBoard(TheHand hand) throws BoardException {
		settingsFrame();
		generateBoard();
		generatePieces(ORIGINAL_DISPLAY);
		this.addMouseListener(hand);
		this.addMouseMotionListener(hand);
		this.setVisible(true); // ES MUY IMPORTANTE QUE ESTE AL FINAL, SI NO SOLO SERA VISIBLE PARTE, ME PASE MEDIA H POR ESTA TONTERIA Y NI IDEA DE PQ PASA
	}

	private void settingsFrame() {
		this.setSize(MAX_SIZE, MAX_SIZE);
		this.setResizable(RESIZABLE);
		this.setUndecorated(UNDECORED);
		this.setLocationRelativeTo(null); // Poniendolo en null hacemos que aparezca en el centro de la pantalla
		this.setTitle("Ajedrez para nada hecho bajo crunch");// cambiamos el nombre de la ventana
		try {
			this.setIconImage(ImageIO.read(new File(IMG_PATH + "logo.jpg"))); // Asi le ponemos una imagen a la
																					// pestaña en la barra
		} catch (IOException e) {
			// Si no es capaz de encotrar el logo, tampoco pasa mucho, simplemente aparece
			// el logo de java, el programa es usable igualmente
		}
		this.setDefaultCloseOperation(EXIT_ON_CLOSE); // Al cerrar la pestaña se termina el programa

	}

	private void generateBoard() {
		board.setLayout(null);// Con esto evitamos que las etiqueta se vaya al centro

		boolean white = true;

		for (int j = 0; j < CELL_NUMBER; j++) {
			for (int i = 0; i < CELL_NUMBER; i++) {
				board.add(generateCell(i, j, white), 0);
				white = !white;
			}
			white = !white;
		}
		this.setContentPane(board);

	}

	private JLabel generateCell(int x, int y, boolean white) {
		/**
		 * Return a opaque JLabel with the defined color and position
		 */
		JLabel label = new JLabel(); // declaramos la etiqueta
		label.setBounds(x * CELL_SIZE, y * CELL_SIZE, CELL_SIZE, CELL_SIZE); // damos tamaño y posicion a la etiqueta
		label.setOpaque(true); // Hacemos que la etiqueta sea solida
		label.setBackground((white) ? WHITE_PIECE_COLOR : BLACK_PIECE_COLOR);
		return label;
	}

	private static Image loadPicture(Piece c) {
		/**
		 * Returns the corresponging image to the piece
		 * Para que funcione debe seguir el pormato COLOR\TYPE.png
		 * The image path should use the format "COLOR\type.png"
		 */
		return new ImageIcon(IMG_PATH + c.getPieceImagePath()).getImage();
	}

	public void clearAllPieces() {
		while (board.getComponentCount() > (CELL_NUMBER * CELL_NUMBER)) {
			board.remove(0);
		}
		this.repaint(); //No termino de entender porque sin esto salen bugs, pero bueno, en todos lados pone que es importante y no lo haba 

	}

	public void generatePieces(String notation) throws BoardException  {
		clearAllPieces();

		Piece[][] tmpPieceArray = this.notationToPieceArray(notation, CELL_NUMBER);
		
		for(Piece[] tmpX : tmpPieceArray) {
			for(Piece tmpY : tmpX) {
				if(tmpY!= null) {	
					JLabel etiqueta = new JLabel();
					etiqueta.setIcon(new ImageIcon(loadPicture(tmpY).getScaledInstance(CELL_SIZE, CELL_SIZE, CELL_SIZE)));
					etiqueta.setBounds(tmpY.getxPosition() * CELL_SIZE, tmpY.getyPosition() * CELL_SIZE, CELL_SIZE, CELL_SIZE);
					board.add(etiqueta, 0);
				}
			}
		}	
		this.repaint();
	}

	public void close() {
		this.dispose();
	}
	
	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);
	}

}
