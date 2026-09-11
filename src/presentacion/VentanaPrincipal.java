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
import negocio.Tablero.Direccion;

public class VentanaPrincipal {

	private static final Color COLOR_DE_FONDO_CELDA = new Color(187, 216, 216);

	private Juego juego = new Juego();

	private JFrame frmThrees;
	private JLabel lblInstrucciones;
	private JLabel lblIndicadorSugerencia;
	private JLabel[][] celdas = new JLabel[juego.getCantidadFilas()][juego.getCantidadColumnas()];

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
				moverSegunTecla(e);
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

		JButton btnTablaHistorica = new JButton("Tabla de posiciones");
		btnTablaHistorica.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mostrarTablaDePosiciones();
			}
		});
		btnTablaHistorica.setHorizontalTextPosition(SwingConstants.CENTER);
		btnTablaHistorica.setFont(new Font("Tahoma", Font.PLAIN, 16));

		btnTablaHistorica.setBounds(24, 42, 171, 21);
		btnTablaHistorica.setFocusable(false);
		frmThrees.getContentPane().add(btnTablaHistorica);

		JLabel lblProximoNumero = new JLabel("Próximo");
		lblProximoNumero.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblProximoNumero.setBounds(253, 17, 88, 14);
		frmThrees.getContentPane().add(lblProximoNumero);

		textFieldProximoNumero = new JTextField();
		textFieldProximoNumero.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldProximoNumero.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textFieldProximoNumero.setEditable(false);
		textFieldProximoNumero.setColumns(10);
		textFieldProximoNumero.setBounds(245, 42, 86, 21);
		frmThrees.getContentPane().add(textFieldProximoNumero);

		JButton btnSugerenciaProxJugada = new JButton("Sugerencia...");
		btnSugerenciaProxJugada.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnSugerenciaProxJugada.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Direccion numeroDireccion = juego.obtenerSugerencia();

				// 2. Mapeo del tipo de dato primitivo a su representación visual
				String flecha;
				switch (numeroDireccion) {
				case ARRIBA:
					flecha = "↑";
					break;
				case ABAJO:
					flecha = "↓";
					break;
				case IZQUIERDA:
					flecha = "←";
					break;
				case DERECHA:
					flecha = "→";
					break;
				default:
					flecha = "-"; // Estado inactivo o error
					break;
				}

				// 3. Inyección del resultado en el componente gráfico creado en el Paso 1
				lblIndicadorSugerencia.setText(flecha);

			}
		});

		btnSugerenciaProxJugada.setBounds(393, 40, 145, 23);
		btnSugerenciaProxJugada.setFocusable(false);
		frmThrees.getContentPane().add(btnSugerenciaProxJugada);

		JButton btnReiniciarJuego = new JButton("Reiniciar juego");
		btnReiniciarJuego.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				juego.nuevoJuego();
				actualizarTablero();
			}
		});
		btnReiniciarJuego.setHorizontalTextPosition(SwingConstants.CENTER);
		btnReiniciarJuego.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnReiniciarJuego.setBounds(24, 10, 145, 21);
		btnReiniciarJuego.setFocusable(false);
		frmThrees.getContentPane().add(btnReiniciarJuego);

		lblIndicadorSugerencia = new JLabel("");
		lblIndicadorSugerencia.setBackground(new Color(128, 255, 128));
		lblIndicadorSugerencia.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblIndicadorSugerencia.setBounds(420, 10, 75, 27);
		frmThrees.getContentPane().add(lblIndicadorSugerencia);

		for (int i = 0; i < juego.getCantidadFilas(); i++) {
			for (int j = 0; j < juego.getCantidadColumnas(); j++) {
				JLabel labelCelda = new JLabel("0", SwingConstants.CENTER);
				labelCelda.setOpaque(true);
				labelCelda.setBackground(COLOR_DE_FONDO_CELDA);
				labelCelda.setFont(new Font("SansSerif", Font.BOLD, 16));

				celdas[i][j] = labelCelda;
				panelTablero.add(labelCelda);
			}
		}

		// vuelvo a recorrer otra vez la matriz, no importa porque O(n**2) +
		// O(n**2) es O(n**2)
		actualizarTablero();

	}

	private void actualizarTablero() {
		Tablero tablero = juego.getTablero();

		textFieldProximoNumero.setText(String.valueOf(tablero.obtenerProximoNumero()));

		for (int i = 0; i < juego.getCantidadFilas(); i++)
			for (int j = 0; j < juego.getCantidadColumnas(); j++) {
				int valor = tablero.obtenerValorDeLaCelda(i, j);
				celdas[i][j].setText(valor == 0 ? "" : String.valueOf(valor));

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
						celdas[i][j].setBackground(COLOR_DE_FONDO_CELDA);
					}
				}
			}
	}

	private void mostrarFinDeJuego() {
		String[] listaOpciones = { "Tabla de posiciones", "Juego nuevo", "Salir a Windows" };

		int eleccion = javax.swing.JOptionPane.showOptionDialog(frmThrees,
				"¡Juego terminado! Tu puntaje es " + juego.obtenerPuntaje(), null,
				javax.swing.JOptionPane.DEFAULT_OPTION, javax.swing.JOptionPane.INFORMATION_MESSAGE, null,
				listaOpciones, listaOpciones[0]);

		if (eleccion == javax.swing.JOptionPane.CLOSED_OPTION)
			return;

		accionSegunClickDelUsuario(listaOpciones, eleccion);

	}

	private void accionSegunClickDelUsuario(String[] listaOpciones, int eleccion) {

		switch (listaOpciones[eleccion].toString()) {
		case "Tabla de posiciones": {
			mostrarTablaDePosiciones();
			break;
		}
		case "Juego nuevo": {
			juego.nuevoJuego();
			actualizarTablero();
			break;
		}
		case "Salir a Windows": {
			System.exit(0);
		}

		default:
			throw new IllegalStateException();
		}
	}

	private void mostrarTablaDePosiciones() {
		TablaDePosiciones frmTabla = new TablaDePosiciones(juego);
		frmTabla.frmTablaDePosiciones.setVisible(true);
	}

	private void moverSegunTecla(KeyEvent eventoTecla) {
		switch (eventoTecla.getKeyCode()) {
		case KeyEvent.VK_DOWN: {
			juego.mover(Direccion.ABAJO);
			actualizarTablero();
			if (juego.juegoTerminado()) {
				mostrarFinDeJuego();
			}
			break;
		}
		case KeyEvent.VK_UP: {
			juego.mover(Direccion.ARRIBA);
			actualizarTablero();
			if (juego.juegoTerminado()) {
				mostrarFinDeJuego();
			}
			break;
		}
		case KeyEvent.VK_LEFT: {
			juego.mover(Direccion.IZQUIERDA);
			actualizarTablero();
			if (juego.juegoTerminado()) {
				mostrarFinDeJuego();
			}
			break;
		}
		case KeyEvent.VK_RIGHT: {
			juego.mover(Direccion.DERECHA);
			actualizarTablero();
			if (juego.juegoTerminado()) {
				mostrarFinDeJuego();
			}
			break;
		}
		}
	}
}
