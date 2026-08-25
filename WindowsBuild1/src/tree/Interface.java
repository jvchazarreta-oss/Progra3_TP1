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

public class Interface {

	private JFrame frame;
	private JLabel Instrucciones;
 
	// armo matriz de botones
	private JButton[][] botones = new JButton[4][4];
 
	private Juego juego = new Juego();
 
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
	        public void run() {
	            try {
	                Interface window = new Interface();
	                window.frame.setVisible(true);
	                window.juego.mostrar();
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	        }
	    });
		
			}
		
 
	
		/*public void imprimirPantalla(Juego juego) {
			Tablero tablero = juego.obtenerTablero();	        
	        for (int i = 0; i < tablero.obtenerFilas(); i++) {
	            for (int j = 0; j < tablero.obtenerCol(); j++) {
	                System.out.print(tablero.obtenerValorDeLaCelda(i, j) + " ");
	            }
	            System.out.println("");
	        }}*/
		
			
	
 
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
		tablero.setBackground(new Color(245, 236, 180));
		tablero.setBounds(78, 45, 270, 165);
		frame.getContentPane().add(tablero);
		tablero.setLayout(new GridLayout(4, 4, 5, 5));
 
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				JButton boton = new JButton("0");
				botones[i][j] = boton;
				tablero.add(boton);
			}
		}
 
		JLabel Titulo = new JLabel("Threes!");
		Titulo.setHorizontalAlignment(SwingConstants.CENTER);
		Titulo.setBounds(169, 11, 63, 23);
		frame.getContentPane().add(Titulo);
 
	}
}
