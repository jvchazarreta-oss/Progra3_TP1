package negocio;

import java.util.ArrayList;
import java.util.Comparator;

public class Juego {

	private Tablero tablero;
	private ArrayList<Integer> puntajes;

	public Juego() {
		this.tablero = new Tablero(4, 4);
		this.puntajes = new ArrayList<Integer>();
	}

	public int obtenerPuntaje() {
		return tablero.obtenerPuntaje();
	}

	public ArrayList<Integer> obtenerPuntajes() {
		return this.puntajes;
	}

	private void almacenarPuntaje() {
		int puntaje = tablero.obtenerPuntaje();

		if (puntajes.contains(puntaje))
			return;

		puntajes.add(tablero.obtenerPuntaje());
		puntajes.sort(Comparator.reverseOrder());
	}

	public void moverAbajo() {
		// le delego a tablero
		tablero.moverAbajo();
	}

	public void moverArriba() {
		// le delego a tablero
		tablero.moverArriba();

	}

	public boolean estaVacio(int fila, int columna) {
		return tablero.obtenerValorDeLaCelda(fila, columna) == 0;
	}

	public void cargarTablero() {
		tablero.cargarTablero();
	}

	public Tablero getTablero() {
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

	public void mostrarTableroPorConsola() {
		for (int fila = 0; fila < 4; fila++) {
			for (int col = 0; col < 4; col++) {
				System.out.print(tablero.obtenerValorDeLaCelda(fila, col) + " ");
			}
			System.out.println("");
		}
	}

	public void moverIzquierda() {
		tablero.moverIzquierda();

	}

	public void moverDerecha() {

		tablero.moverDerecha();

	}

	public boolean juegoTerminado() {
		if (tablero.juegoTerminado()) {
			almacenarPuntaje();
			return true;
		}
		return false;
	}

	public void nuevoJuego() {
		this.tablero = new Tablero(4, 4);
	}

}