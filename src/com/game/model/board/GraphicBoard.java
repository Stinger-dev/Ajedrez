package com.game.model.board;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;

import com.game.model.pieces.Colour;
import com.game.model.pieces.Piece;


public class GraphicBoard extends JFrame implements NotationToPieceArray{
	private static final long serialVersionUID = 1L;





	private static final String IMG_PATH = System.getProperty("user.dir") + "\\resources\\img\\";

	private static JLayeredPane board = new JLayeredPane();

	public GraphicBoard(TheHand hand) throws BoardException {
		settingsFrame();
		generateBoard();
		generatePieces(Constantes.ORIGINAL_DISPLAY);
		this.addMouseListener(hand);
		this.addMouseMotionListener(hand);
		this.setVisible(true); // ES MUY IMPORTANTE QUE ESTE AL FINAL, SI NO SOLO SERA VISIBLE PARTE, ME PASE MEDIA H POR ESTA TONTERIA Y NI IDEA DE PQ PASA
	}

	private void settingsFrame() {
		this.setSize(Constantes.MAX_SIZE, Constantes.MAX_SIZE);
		this.setResizable(Constantes.RESIZABLE);
		this.setUndecorated(Constantes.UNDECORED);
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

		for (int j = 0; j < Constantes.CELL_NUMBER; j++) {
			for (int i = 0; i < Constantes.CELL_NUMBER; i++) {
				board.add(generateCell(i, j, white), 0);
				white = !white;
			}
			white = !white;
		}
		this.setContentPane(board);

	}

	/**
	 * Return a opaque JLabel with the defined color and position
	 */
	private JLabel generateCell(int x, int y, boolean white) {
		JLabel label = new JLabel(); // declaramos la etiqueta
		label.setBounds(x * Constantes.CELL_SIZE, y * Constantes.CELL_SIZE, Constantes.CELL_SIZE, Constantes.CELL_SIZE); // damos tamaño y posicion a la etiqueta
		label.setOpaque(true); // Hacemos que la etiqueta sea solida
		label.setBackground((white) ? Constantes.WHITE_PIECE_COLOR : Constantes.BLACK_PIECE_COLOR);
		return label;
	}

	/**
	 * Returns the corresponging image to the piece
	 * Para que funcione debe seguir el pormato COLOR\TYPE.png
	 * The image path should use the format "COLOR\type.png"
	 */
	private static Image loadPicture(Piece c) {
		return new ImageIcon(IMG_PATH + c.getPieceImagePath()).getImage();
	}

	public void clearAllPieces() {
		while (board.getComponentCount() > (Constantes.CELL_NUMBER * Constantes.CELL_NUMBER)) {
			board.remove(0);
		}
		this.repaint(); 
	}

	public void generatePieces(String notation) throws BoardException  {
		clearAllPieces();

		Piece[][] tmpPieceArray = this.notationToPieceArray(notation, Constantes.CELL_NUMBER);
		
		for(Piece[] tmpX : tmpPieceArray) {
			for(Piece tmpY : tmpX) {
				if(tmpY!= null) {	
					JLabel etiqueta = new JLabel();
					etiqueta.setIcon(new ImageIcon(loadPicture(tmpY).getScaledInstance(Constantes.CELL_SIZE, Constantes.CELL_SIZE, Constantes.CELL_SIZE)));
					etiqueta.setBounds(tmpY.getxPosition() * Constantes.CELL_SIZE, tmpY.getyPosition() * Constantes.CELL_SIZE, Constantes.CELL_SIZE, Constantes.CELL_SIZE);
					board.add(etiqueta, 0);
				}
			}
		}	
		this.repaint();
	}

	public void close() {
		this.dispose();
	}
	
	public void endMatch(Colour gandor) {
		while (board.getComponentCount() > 0) {
			board.remove(0);
		}
		this.repaint(); 
		
		JLabel label = new JLabel(); 
		label.setBounds(0, 0, Constantes.MAX_SIZE, Constantes.MAX_SIZE); 
		label.setOpaque(true); 
		label.setBackground((gandor.equals(Colour.WHITE)) ? Constantes.WHITE_PIECE_COLOR : Constantes.BLACK_PIECE_COLOR);
		this.add(label);
		
		
	}
	

}
