package negocio;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Tablero {
	private int[][] matriz;
	private int cantidadDeColumnas;
	private int cantidadDeFilas;

	public Tablero(int fila, int col) {
		this.matriz = new int[fila][col];
		this.cantidadDeColumnas = col;
		this.cantidadDeFilas = fila;
		cargarTablero();
	}

	public int[][] obtenerTablero() {
		return this.matriz;
	}

	public int obtenerFilas() {
		return this.cantidadDeFilas;
	}

	public int obtenerCol() {
		return this.cantidadDeColumnas;
	}

	public void establecerValorCelda(int fila, int col, int valor) {
		this.matriz[fila][col] = valor;
	}

	public int obtenerValorDeLaCelda(int fil, int col) {

		return this.matriz[fil][col];
	}

	public void cargarTablero() {
		List<Coordenada> ubicaciones = ubicacionesInicialesNumeros();
		for (Coordenada coord : ubicaciones) {
			int fila = coord.getFila();
			int columna = coord.getColumna();
			cargarNumeros(fila, columna);
		}
	}

	public List<Coordenada> ubicacionesInicialesNumeros() {
		List<Coordenada> ubicaciones = new ArrayList<>();
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				ubicaciones.add(new Coordenada(i, j));
			}
		}
		Collections.shuffle(ubicaciones);
		return ubicaciones.subList(0, 9);
	}

	public void cargarNumeros(int fila, int col) {
		int nuevoNumero = siguienteNumero();
		this.establecerValorCelda(fila, col, nuevoNumero);
	}

	public static int siguienteNumero() {
		Random aleatorio = new Random();
		int n = aleatorio.nextInt(1, 4);
		return n;
	}

	public boolean moverAbajo() {
		boolean huboCambio = false;
		int fil = cantidadDeFilas - 1;
		int col = cantidadDeColumnas - 1;

		for (int j = 0; j <= col; j++) {
			for (int i = fil - 1; i >= 0; i--) {
				int valorInicial = matriz[i][j];
				if (valorInicial != 0) {
					int valorFinal = matriz[i + 1][j];
					if (valorFinal == 0) {
						matriz[i + 1][j] = valorInicial;
						matriz[i][j] = 0;
						huboCambio = true;
					} else if (sePuedenFusionar(valorInicial, valorFinal)) {
						matriz[i + 1][j] = valorInicial + valorFinal;
						matriz[i][j] = 0;
						huboCambio = true;
					}
				}
			}
		}
		if (huboCambio) {
			agregarFichaEnFilaSiHayLugar(0);
		}
		return huboCambio;
	}

	public boolean moverArriba() {
		boolean huboCambio = false;
		int fil = cantidadDeFilas - 1;
		int col = cantidadDeColumnas - 1;

		for (int j = 0; j <= col; j++) {
			for (int i = 1; i <= fil; i++) {
				int valorInicial = matriz[i][j];
				if (valorInicial != 0) {
					int valorFinal = matriz[i - 1][j];
					if (valorFinal == 0) {

						matriz[i - 1][j] = valorInicial;
						matriz[i][j] = 0;
						huboCambio = true;
					} else if (sePuedenFusionar(valorInicial, valorFinal)) {
						matriz[i - 1][j] = valorInicial + valorFinal;
						matriz[i][j] = 0;
						huboCambio = true;
					}
				}
			}
		}
		if (huboCambio) {
			agregarFichaEnFilaSiHayLugar(fil);
		}
		return huboCambio;
	}

	public boolean esBordeSuperior(int i, int j) {
		return i == 1;
	}

	public boolean moverIzquierda() {
		boolean huboCambio = false;
		int fil = cantidadDeFilas - 1;
		int col = cantidadDeColumnas - 1;

		for (int i = 0; i <= fil; i++) {
			for (int j = 1; j <= col; j++) {
				int valorInicial = matriz[i][j];
				if (valorInicial != 0) {
					int valorFinal = matriz[i][j - 1];
					if (valorFinal == 0) {
						matriz[i][j - 1] = valorInicial;
						matriz[i][j] = 0;
						huboCambio = true;
					} else if (sePuedenFusionar(valorInicial, valorFinal)) {
						matriz[i][j - 1] = valorInicial + valorFinal;
						matriz[i][j] = 0;
						huboCambio = true;
					}
				}
			}
		}
		if (huboCambio) {
			agregarFichaEnColumnaSiHayLugar(col);
		}
		return huboCambio;
	}

	public boolean moverDerecha() {
		boolean huboCambio = false;
		int fil = cantidadDeFilas - 1;
		int col = cantidadDeColumnas - 1;

		for (int i = 0; i <= fil; i++) {
			for (int j = col - 1; j >= 0; j--) {
				int valorInicial = matriz[i][j];
				if (valorInicial != 0) {
					int valorFinal = matriz[i][j + 1];
					if (valorFinal == 0) {
						matriz[i][j] = 0;
						matriz[i][j + 1] = valorInicial;
						huboCambio = true;
					} else if (sePuedenFusionar(valorInicial, valorFinal)) {
						matriz[i][j + 1] = valorInicial + valorFinal;
						matriz[i][j] = 0;
						huboCambio = true;
					}
				}
			}
		}
		if (huboCambio) {
			agregarFichaEnColumnaSiHayLugar(0);
		}
		return huboCambio;
	}

	public boolean sePuedenFusionar(int a, int b) {
		if (((a == b) && (a >= 3)) || (a == 1 && b == 2) || (a == 2 && b == 1)) {
			return true;
		} else {
			return false;
		}
	}

	private int elegirAlAzar(List<Integer> opciones) {
		Random aleatorio = new Random();
		int indice = aleatorio.nextInt(opciones.size());
		return opciones.get(indice);
	}

	private void agregarFichaEnFilaSiHayLugar(int fila) {
		List<Integer> columnasLibres = new ArrayList<>();
		for (int j = 0; j < cantidadDeColumnas; j++) {
			if (matriz[fila][j] == 0) {
				columnasLibres.add(j);
			}
		}
		if (!columnasLibres.isEmpty()) {
			int col = elegirAlAzar(columnasLibres);
			cargarNumeros(fila, col);
		}
	}

	private void agregarFichaEnColumnaSiHayLugar(int columna) {
		List<Integer> filasLibres = new ArrayList<>();
		for (int i = 0; i < cantidadDeFilas; i++) {
			if (matriz[i][columna] == 0) {
				filasLibres.add(i);
			}
		}
		if (!filasLibres.isEmpty()) {
			int fila = elegirAlAzar(filasLibres);
			cargarNumeros(fila, columna);
		}
	}

	private boolean hayCeldaVacia() {
		for (int i = 0; i < cantidadDeFilas; i++) {
			for (int j = 0; j < cantidadDeColumnas; j++) {
				if (matriz[i][j] == 0)
					return true;
			}
		}
		return false;
	}

	private boolean hayFusionPosible() {
		for (int i = 0; i < cantidadDeFilas; i++) {
			for (int j = 0; j < cantidadDeColumnas; j++) {
				int actual = matriz[i][j];
				if (actual == 0)
					continue;
				if (j + 1 < cantidadDeColumnas && sePuedenFusionar(actual, matriz[i][j + 1]))
					return true;
				if (i + 1 < cantidadDeFilas && sePuedenFusionar(actual, matriz[i + 1][j]))
					return true;
			}
		}
		return false;
	}

	public boolean juegoTerminado() {
		return !hayCeldaVacia() && !hayFusionPosible();
	}

	public int obtenerPuntaje() {
		int puntajeTotal = 0;
		for (int i = 0; i < cantidadDeFilas; i++)
			for (int j = 0; j < cantidadDeColumnas; j++)
				if (matriz[i][j] >= 3)
					puntajeTotal += matriz[i][j];
		return puntajeTotal;
	}

}
