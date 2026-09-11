package negocio;

import java.util.ArrayList;
import java.util.Comparator;

import negocio.Tablero.Direccion;

public class Juego {

	public static final int CANTIDAD_FILAS = 4;
	public static final int CANTIDAD_COLUMNAS = 4;

	private Tablero tablero;
	private ArrayList<Integer> puntajes;

	public Juego() {
		this.tablero = new Tablero(CANTIDAD_FILAS, CANTIDAD_COLUMNAS);
		this.puntajes = new ArrayList<Integer>();
	}

	public int getCantidadFilas() {
		return CANTIDAD_FILAS;
	}

	public int getCantidadColumnas() {
		return CANTIDAD_COLUMNAS;
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