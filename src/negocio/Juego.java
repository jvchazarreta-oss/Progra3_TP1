package negocio;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Juego {

	private Tablero tablero;

	public Juego() {
		this.tablero = new Tablero(4,4);
		cargarTablero();
	}

	public void cargarNumeros(int fila, int col) {
		int nuevoNumero = siguienteNumero();
		this.tablero.establecerValorCelda(fila, col, nuevoNumero);
	}
	public  void mostrar() {
		cargarTablero();
		for (int fila = 0; fila < 4; fila++) {
			for (int col = 0; col < 4; col++) {
					System.out.print(tablero.obtenerValorDeLaCelda(fila, col)+" ");
				}System.out.println("");
			}}
 
	public static int siguienteNumero() {
		Random aleatorio = new Random();
		int n = aleatorio.nextInt(1, 4);
		return n;
	}

	public boolean estaVacio(int fila, int columna) {
		return tablero.obtenerValorDeLaCelda(fila, columna) == 0;
	}

	public void cargarTablero() {
		List<Cordenada> ubicaciones = new ArrayList<>();
		ubicaciones = ubicacionesInicialesNumeros();
		for (Cordenada coord : ubicaciones) {
			int fila = coord.getFila();
			int columna = coord.getColumna();
			cargarNumeros(fila, columna);
		}
	}

	/*
	 * public static List<Cordenada> ubicacionesInicialesNumeros(int[][] matriz) {
	 * List<Cordenada> ubicaciones = new ArrayList<>();
	 * for (int i = 0; i < 4; i++) {
	 * for (int j = 0; j < 4; j++) {
	 * ubicaciones.add(new Cordenada(i, j));
	 * }
	 * }
	 * Collections.shuffle(ubicaciones);
	 * return ubicaciones.subList(0, 9);
	 * }
	 */
	public List<Cordenada> ubicacionesInicialesNumeros() {
		List<Cordenada> ubicaciones = new ArrayList<>();
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				ubicaciones.add(new Cordenada(i, j));
			}
		}
		Collections.shuffle(ubicaciones);
		return ubicaciones.subList(0, 9);
	}

	/*
	 * public void inicializarTablero() { // Tiene el mismo comportamiento que
	 * cargarTablero() pero no se usa
	 * List<Cordenada> ubicaciones = new ArrayList<>();
	 * ubicaciones=ubicacionesInicialesNumeros();
	 * for (Cordenada coord : ubicaciones) {
	 * int fila = coord.getFila();
	 * int columna = coord.getColumna();
	 * cargarNumeros(fila,columna);
	 * 
	 * }}
	 * 
	 */

	public Tablero obtenerTablero() {
		return tablero;
	}

	public boolean revisarAlgunaColLlena(int[][] matriz) {
		boolean esValido = true;
		for (int i = 0; i < 4; i++) {
			boolean colLlena = true;

			for (int j = 0; j < 4; j++) {
				if (matriz[j][i] == 0) {
					colLlena = false;
					break;
				}
			}
			if (colLlena) {
				return true;
			}
		}
		return false;

	}

	public boolean revisarAlgunaFilaLlena(int[][] matriz) {
		for (int i = 0; i < 4; i++) {
			boolean filaLlena = true;

			for (int j = 0; j < 4; j++) {
				if (matriz[i][j] == 0) {
					filaLlena = false;
					break;
				}
			}
			if (filaLlena) {
				return true;
			}
		}
		return false;
	}

}