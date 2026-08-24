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

	/**
	 * Launch the application.
	 */
	private static int[][] matriz;
	public void TableroJuego() {
        this.matriz = new int[4][4];}
	
	private static int siguienteNumero() {
		Random aleatorio = new Random();
		int n = aleatorio.nextInt(1, 4);
		return n;
	}
	private boolean espacioVacio (int fila , int columna ) {
		return matriz[fila][columna]==0;
	}
	private static List<Cordenada> ubicacionesInicialesNumeros(int [][] matriz){
		List<Cordenada> ubicaciones= new ArrayList<>();
		for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                ubicaciones.add(new Cordenada(i, j));
            }
        }
		Collections.shuffle(ubicaciones);
		return ubicaciones.subList(0, 9);
	}
	
	
	private void inicializarMatriz(int [][] matriz) {
		
		List<Cordenada> iniciales = ubicacionesInicialesNumeros(matriz);
		
        for (Cordenada c : iniciales) {
        	matriz[c.getFila()][c.getColumna()]=siguienteNumero();
        }
	}
	
	
	private boolean revisarAlgunaColLlena(int [][] matriz) {
		boolean esValido = true;
		for (int i = 0; i < 4; i++) {
			boolean colLlena= true;
		
			for(int j = 0; j < 4; j++) {
				if (matriz[j][i] == 0) {
	                colLlena = false;
	                break;	
			}}
			if (colLlena) {
	            return true;}}
		return false;
	
		}
	
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
		});
		System.out.println("00 00 00 00 \n00 00 00 00");
		List<Cordenada> iniciales = ubicacionesInicialesNumeros(matriz);
        for (Cordenada c : iniciales) {
            System.out.println("Fila: " + c.getFila() + ", Columna: " + c.getColumna());
        }
		System.out.println( ubicacionesInicialesNumeros(matriz));
		System.out.println( siguienteNumero());System.out.println( siguienteNumero());System.out.println( siguienteNumero());System.out.println( siguienteNumero());System.out.println( siguienteNumero());System.out.println( siguienteNumero());
	}

	/**
	 * Create the application.
	 */
	public Interface() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			// TODO Auto-generated catch block
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
		
		JButton btn01 = new JButton("0");
		btn01.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		tablero.add(btn01);
		
		JButton btn02 = new JButton("0");
		tablero.add(btn02);
		
		JButton btn03 = new JButton("0");
		tablero.add(btn03);
		
		JButton btn04 = new JButton("0");
		tablero.add(btn04);
		
		JButton btn05 = new JButton("0");
		tablero.add(btn05);
		
		JButton btn06 = new JButton("0");
		tablero.add(btn06);
		
		JButton btn07 = new JButton("0");
		tablero.add(btn07);
		
		JButton btn08 = new JButton("0");
		tablero.add(btn08);
		
		JButton btn09 = new JButton("0");
		tablero.add(btn09);
		
		JButton btn10 = new JButton("0");
		tablero.add(btn10);
		
		JButton btn11 = new JButton("0");
		tablero.add(btn11);
		
		JButton btn12 = new JButton("0");
		tablero.add(btn12);
		
		JButton btn13 = new JButton("0");
		tablero.add(btn13);
		
		JButton btn14 = new JButton("0");
		tablero.add(btn14);
		
		JButton btn15 = new JButton("0");
		tablero.add(btn15);
		
		JButton btn16 = new JButton("0");
		tablero.add(btn16);
		
		JLabel Titulo = new JLabel("Threes!");
		Titulo.setHorizontalAlignment(SwingConstants.CENTER);
		Titulo.setBounds(169, 11, 63, 23);
		frame.getContentPane().add(Titulo);
		


		

	}
}
