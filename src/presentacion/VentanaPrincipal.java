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

	private JFrame frmThrees;
	private JLabel lblInstrucciones;
	private JLabel lblIndicadorSugerencia;
	// armo matriz de celdas
	private JLabel[][] celdas = new JLabel[4][4];

	private Juego juego = new Juego();

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
				int numeroDireccion = juego.obtenerSugerencia();

				// 2. Mapeo del tipo de dato primitivo a su representación visual
				String flecha;
				switch (numeroDireccion) {
					case 1:
						flecha = "↑";
						break;
					case 2:
						flecha = "↓";
						break;
					case 3:
						flecha = "←";
						break;
					case 4:
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
		frmThrees.getContentPane().add(btnReiniciarJuego);

		lblIndicadorSugerencia = new JLabel("");
		lblIndicadorSugerencia.setBackground(new Color(128, 255, 128));
		lblIndicadorSugerencia.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblIndicadorSugerencia.setBounds(420, 10, 75, 27);
		frmThrees.getContentPane().add(lblIndicadorSugerencia);

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

		textFieldProximoNumero.setText(
				String.valueOf(tablero.obtenerProximoNumero()));

		for (int i = 0; i < 4; i++)
			for (int j = 0; j < 4; j++) {
				int valor = tablero.obtenerValorDeLaCelda(i, j);
				celdas[i][j].setText(valor == 0 ? "" : String.valueOf(valor));

				// TODO:
				// poner un map: asi el switch pasa a tener 2 lineas
				// 0, (Color(187, 216, 216), Color(80, 60, 100)) <= definir estos colores via
				// cte y listo
				// 1, (Color.RED,Color.WHITE)
				// 2, (Color.BLUE,Color.WHITE)
				// 3, (Color.WHITE,Color.BLACK)
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
}
