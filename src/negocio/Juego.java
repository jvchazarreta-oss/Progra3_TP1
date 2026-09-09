package negocio;

import static constantes.Constantes.CANTIDAD_COLUMNAS;
import static constantes.Constantes.CANTIDAD_FILAS;

import java.util.ArrayList;
import java.util.Comparator;

import negocio.Tablero.Direccion;

public class Juego {

	private Tablero tablero;
	private ArrayList<Integer> puntajes;

	public Juego() {
		this.tablero = new Tablero(CANTIDAD_FILAS, CANTIDAD_COLUMNAS);
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

	public boolean estaVacio(int fila, int columna) {
		return tablero.obtenerValorDeLaCelda(fila, columna) == 0;
	}

	public void cargarTablero() {
		tablero.cargarTablero();
	}

	public Tablero getTablero() {
		return tablero;
	}

	public void mostrarTableroPorConsola() {
		for (int fila = 0; fila < CANTIDAD_FILAS; fila++) {
			for (int col = 0; col < CANTIDAD_COLUMNAS; col++) {
				System.out.print(tablero.obtenerValorDeLaCelda(fila, col) + " ");
			}
			System.out.println("");
		}
	}

	public boolean mover(Direccion direccion) {
		return tablero.mover(direccion);
	}

	public int obtenerSugerencia() {
		return tablero.sugerenciaDeMovimientoConMayorPuntaje();
	}

	public boolean juegoTerminado() {
		if (tablero.juegoTerminado()) {
			almacenarPuntaje();
			return true;
		}
		return false;
	}

	public void nuevoJuego() {
		this.tablero = new Tablero(CANTIDAD_FILAS, CANTIDAD_COLUMNAS);
	}

}