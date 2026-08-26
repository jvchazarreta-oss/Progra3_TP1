package tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.UIManager;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

public class Interface {

	private JFrame frame;
	private JLabel Instrucciones;
 
	// armo matriz de celdas
	private JLabel[][] celdas = new JLabel[4][4];
 
	private Juego juego = new Juego();
 
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Interface window = new Interface();
					window.frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});}
 

 
	/**
	 * Create the application.
	 */
	public Interface() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		initialize();
	}
 
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
 
		Instrucciones = new JLabel("Controles:  ⬅️⬆️⬇️➡️");
		Instrucciones.setHorizontalAlignment(SwingConstants.CENTER);
		Instrucciones.setBounds(129, 229, 172, 21);
		frame.getContentPane().add(Instrucciones);
 
		JPanel tablero = new JPanel();
		tablero.setBorder(new EmptyBorder(10, 10, 10, 10));
		tablero.setBackground(new Color(207, 229, 222));
		tablero.setBounds(78, 45, 270, 165);
		frame.getContentPane().add(tablero);
		tablero.setLayout(new GridLayout(4, 4, 8, 8));
		
		
		for (int i = 0; i < 4; i++) {
		    for (int j = 0; j < 4; j++) {
		    	JLabel celda = new JLabel("0", SwingConstants.CENTER);
		    	celda.setOpaque(true);
		    	celda.setBackground(new Color(187, 216, 216)); // mas oscuro que el panel
		    	celda.setForeground(new Color(80, 60, 100)); // color del texto
		    	
		    	Tablero elTablero = juego.obtenerTablero();

		    	celda.setText(String.valueOf(elTablero.obtenerValorDeLaCelda(i, j)));
		    	
		        celdas[i][j] = celda;
		        tablero.add(celda);
		    }
		}
 
		JLabel Titulo = new JLabel("Threes!");
		Titulo.setHorizontalAlignment(SwingConstants.CENTER);
		Titulo.setBounds(169, 11, 63, 23);
		frame.getContentPane().add(Titulo);
		
		
		
 
	}
}
