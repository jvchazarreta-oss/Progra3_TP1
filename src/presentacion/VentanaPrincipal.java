package presentacion;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import negocio.Juego;
import negocio.Tablero;

public class VentanaPrincipal {

	private JFrame frame;
	private JLabel Instrucciones;

	// armo matriz de celdas
	private JLabel[][] celdas = new JLabel[4][4];

	private Juego juego = new Juego();

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaPrincipal window = new VentanaPrincipal();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public VentanaPrincipal() {
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
		frame.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				switch (e.getKeyCode()) {
				case KeyEvent.VK_DOWN: {
					juego.moverAbajo();
					actualizarTablero();
					if (juego.juegoTerminado()) {
						javax.swing.JOptionPane.showMessageDialog(frame, "¡Juego terminado!");
					}
					break;
				}
				case KeyEvent.VK_UP: {
					juego.moverArriba();
					actualizarTablero();
					if (juego.juegoTerminado()) {
						javax.swing.JOptionPane.showMessageDialog(frame, "¡Juego terminado!");
					}
					break;
				}
				case KeyEvent.VK_LEFT: {
					juego.moverIzquierda();
					actualizarTablero();
					if (juego.juegoTerminado()) {
						javax.swing.JOptionPane.showMessageDialog(frame, "¡Juego terminado!");
					}
					break;
				}
				case KeyEvent.VK_RIGHT: {
					juego.moverDerecha();
					actualizarTablero();
					if (juego.juegoTerminado()) {
						javax.swing.JOptionPane.showMessageDialog(frame, "¡Juego terminado!");
					}
					break;
				}
				}

			}

		});
		frame.setBounds(100, 100, 594, 446);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		Instrucciones = new JLabel("Controles: flechas del teclado");
		Instrucciones.setFont(new Font("Tahoma", Font.PLAIN, 16));
		Instrucciones.setHorizontalAlignment(SwingConstants.CENTER);
		Instrucciones.setBounds(139, 357, 302, 21);
		frame.getContentPane().add(Instrucciones);

		JPanel panelTablero = new JPanel();
		panelTablero.setBorder(new EmptyBorder(10, 10, 10, 10));
		panelTablero.setBackground(new Color(207, 229, 222));
		panelTablero.setBounds(78, 45, 417, 290);
		frame.getContentPane().add(panelTablero);
		panelTablero.setLayout(new GridLayout(4, 4, 8, 8));

		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				JLabel labelCelda = new JLabel("0", SwingConstants.CENTER);
				labelCelda.setOpaque(true);
				labelCelda.setBackground(new Color(187, 216, 216)); // mas oscuro que el panel
				labelCelda.setForeground(new Color(80, 60, 100)); // color del texto
				labelCelda.setFont(new Font("SansSerif", Font.BOLD, 16)); // acá el cambio

				Tablero tablero = juego.getTablero();

				int valor = tablero.obtenerValorDeLaCelda(i, j);

				labelCelda.setText(valor == 0 ? "" : String.valueOf(valor));

				switch (valor) {
				case 1: {
					labelCelda.setBackground(Color.RED);
					labelCelda.setForeground(Color.WHITE);
					break;
				}
				case 2: {
					labelCelda.setBackground(Color.BLUE);
					labelCelda.setForeground(Color.WHITE);
					break;
				}
				default:
					if (valor >= 3) {
						labelCelda.setBackground(Color.WHITE);
						labelCelda.setForeground(Color.BLACK);
					} else {
						labelCelda.setBackground(new Color(187, 216, 216)); // mas oscuro que el panel
						labelCelda.setForeground(new Color(80, 60, 100)); // color del texto
					}
				}

				celdas[i][j] = labelCelda;
				panelTablero.add(labelCelda);
			}
		}

		JLabel Titulo = new JLabel("Threes!");
		Titulo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		Titulo.setHorizontalAlignment(SwingConstants.CENTER);
		Titulo.setBounds(252, 11, 63, 23);
		frame.getContentPane().add(Titulo);

	}

	private void actualizarTablero() {
		System.out.println("actualizando tablero");
		Tablero tablero = juego.getTablero();

		for (int i = 0; i < 4; i++)
			for (int j = 0; j < 4; j++) {
				int valor = tablero.obtenerValorDeLaCelda(i, j);
				celdas[i][j].setText(valor == 0 ? "" : String.valueOf(valor));

				// SETEO COLORES
				// TODO: extraer en un metodo xq se usa en el initialize tamb
				switch (valor) {
				case 1: {
					celdas[i][j].setBackground(Color.RED);
					celdas[i][j].setForeground(Color.WHITE);
					break;
				}
				case 2: {
					celdas[i][j].setBackground(Color.BLUE);
					celdas[i][j].setForeground(Color.WHITE);
					break;
				}
				default:
					if (valor >= 3) {
						celdas[i][j].setBackground(Color.WHITE);
						celdas[i][j].setForeground(Color.BLACK);
					} else {
						celdas[i][j].setBackground(new Color(187, 216, 216)); // mas oscuro que el panel
						celdas[i][j].setForeground(new Color(80, 60, 100)); // color del texto
					}
				}
			}
	}
}
