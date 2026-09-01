package presentacion;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import negocio.Juego;
import negocio.Tablero;

public class VentanaPrincipal {

	private JFrame frmThrees;
	private JLabel lblInstrucciones;

	// armo matriz de celdas
	private JLabel[][] celdas = new JLabel[4][4];

	private Juego juego = new Juego();
	private JTextField textFieldPuntajeActual;
	private JTextField textFieldProximoNumero;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaPrincipal window = new VentanaPrincipal();
					window.frmThrees.setVisible(true);
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
		frmThrees = new JFrame();
		frmThrees.setFocusable(true);
		frmThrees.setResizable(false);
		frmThrees.setTitle("Threes!");
		frmThrees.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				switch (e.getKeyCode()) {
				case KeyEvent.VK_DOWN: {
					juego.moverAbajo();
					actualizarTablero();
					if (juego.juegoTerminado()) {
						mostrarFinDeJuego();
					}
					break;
				}
				case KeyEvent.VK_UP: {
					juego.moverArriba();
					actualizarTablero();
					if (juego.juegoTerminado()) {
						mostrarFinDeJuego();
					}
					break;
				}
				case KeyEvent.VK_LEFT: {
					juego.moverIzquierda();
					actualizarTablero();
					if (juego.juegoTerminado()) {
						mostrarFinDeJuego();
					}
					break;
				}
				case KeyEvent.VK_RIGHT: {
					juego.moverDerecha();
					actualizarTablero();
					if (juego.juegoTerminado()) {
						mostrarFinDeJuego();
					}
					break;
				}
				}

			}

		});
		frmThrees.setBounds(100, 100, 594, 446);
		frmThrees.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmThrees.getContentPane().setLayout(null);

		lblInstrucciones = new JLabel("Controles: flechas del teclado");
		lblInstrucciones.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblInstrucciones.setHorizontalAlignment(SwingConstants.CENTER);
		lblInstrucciones.setBounds(128, 375, 330, 21);
		frmThrees.getContentPane().add(lblInstrucciones);

		JPanel panelTablero = new JPanel();
		panelTablero.setBorder(new EmptyBorder(10, 10, 10, 10));
		panelTablero.setBackground(new Color(207, 229, 222));
		panelTablero.setBounds(75, 74, 423, 290);
		frmThrees.getContentPane().add(panelTablero);
		panelTablero.setLayout(new GridLayout(4, 4, 8, 8));

		JLabel lblPuntaje = new JLabel("Puntaje actual");
		lblPuntaje.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblPuntaje.setBounds(246, 13, 105, 31);
		frmThrees.getContentPane().add(lblPuntaje);

		textFieldPuntajeActual = new JTextField();
		textFieldPuntajeActual.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldPuntajeActual.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textFieldPuntajeActual.setEditable(false);
		textFieldPuntajeActual.setBounds(256, 40, 86, 21);
		textFieldPuntajeActual.setText(String.valueOf(juego.obtenerPuntaje()));
		frmThrees.getContentPane().add(textFieldPuntajeActual);
		textFieldPuntajeActual.setColumns(10);

		JButton btnTablaHistorica = new JButton("Tabla histórica");
		btnTablaHistorica.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnTablaHistorica.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnTablaHistorica.setBounds(75, 40, 135, 23);
		frmThrees.getContentPane().add(btnTablaHistorica);

		JLabel lblProximoNumero = new JLabel("Próximo");
		lblProximoNumero.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblProximoNumero.setBounds(427, 21, 88, 14);
		frmThrees.getContentPane().add(lblProximoNumero);

		textFieldProximoNumero = new JTextField();
		textFieldProximoNumero.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldProximoNumero.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textFieldProximoNumero.setEditable(false);
		textFieldProximoNumero.setColumns(10);
		textFieldProximoNumero.setBounds(412, 40, 86, 21);
		frmThrees.getContentPane().add(textFieldProximoNumero);

		JButton btnSugerenciaProxJugada = new JButton("Sugerencia");
		btnSugerenciaProxJugada.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnSugerenciaProxJugada.setBounds(459, 373, 89, 23);
		frmThrees.getContentPane().add(btnSugerenciaProxJugada);

		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				JLabel labelCelda = new JLabel("0", SwingConstants.CENTER);
				labelCelda.setOpaque(true);
				labelCelda.setBackground(new Color(187, 216, 216)); // mas oscuro que el panel
				labelCelda.setForeground(new Color(80, 60, 100)); // color del texto
				labelCelda.setFont(new Font("SansSerif", Font.BOLD, 16)); // acá el cambio

				celdas[i][j] = labelCelda;
				panelTablero.add(labelCelda);
			}
		}

		// vuelvo a recorrer otra vez la matriz, no importa me parece porq O(n**2) +
		// O(n**2) es O(n**2)
		actualizarTablero();

	}

	private void actualizarTablero() {
		Tablero tablero = juego.getTablero();

		for (int i = 0; i < 4; i++)
			for (int j = 0; j < 4; j++) {
				int valor = tablero.obtenerValorDeLaCelda(i, j);
				celdas[i][j].setText(valor == 0 ? "" : String.valueOf(valor));

				// Seteo colores
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
		textFieldPuntajeActual.setText(String.valueOf(juego.obtenerPuntaje()));
	}

	private void mostrarFinDeJuego() {
		// TODO Auto-generated method stub
		Object[] opciones = { "Juego nuevo", "Salir" };
		int eleccion = javax.swing.JOptionPane.showOptionDialog(frmThrees, "¡Juego terminado!", "Fin del juego",
				javax.swing.JOptionPane.DEFAULT_OPTION, javax.swing.JOptionPane.INFORMATION_MESSAGE, null, opciones,
				opciones[0]);

		if (opciones[eleccion] == "Juego nuevo") {
			// reseteo
			juego.nuevoJuego();
			actualizarTablero();
		} else {
			System.exit(0);
		}
	}
}
