package negocio;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Tablero {
	private int[][] matriz;
	private int cantidadDeColumnas;
	private int cantidadDeFilas;
	private int siguienteNumero;

	public Tablero(int fila, int col) {
		this.matriz = new int[fila][col];
		this.cantidadDeColumnas = col;
		this.cantidadDeFilas = fila;
		this.siguienteNumero = 0;

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
			obtenerSiguienteNumero();
		}
	}

	public int obtenerSiguienteNumero() {
		this.siguienteNumero = siguienteNumero();
		return this.siguienteNumero;
	}

	public int obtenerProximoNumero() {
		return this.siguienteNumero;
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

	public void cargarSiguienteNumero(int fila, int col) {
		this.establecerValorCelda(fila, col, this.siguienteNumero);
		obtenerSiguienteNumero();
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
			cargarSiguienteNumero(fila, col);
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
			cargarSiguienteNumero(fila, columna);
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

	private boolean sePuedeMoverArriba() {
		for (int j = 0; j < cantidadDeColumnas; j++) {
			for (int i = 1; i < cantidadDeFilas; i++) {
				int valorActual = matriz[i][j];
				
				if (valorActual != 0) {
					int valorArriba = matriz[i - 1][j];
					if (valorArriba == 0 || sePuedenFusionar(valorActual, valorArriba)) {
						return true;
					}
				}
			}
		}
		return false;
	}
	public int sugerenciaDeMovimientoConMayorPuntaje() {
		int movimientoSugerido = 0; // 0: No hay movimiento posible, 1: Arriba, 2: Abajo, 3: Izquierda, 4: Derecha
		int puntajeMaximo = -1;

		if (sePuedeMoverArriba()) {
			int puntajeArriba = calcularPuntajeMovimiento(1);
			if (puntajeArriba > puntajeMaximo) {
				puntajeMaximo = puntajeArriba;
				movimientoSugerido = 1; // Arriba
			}
		}
		if (sePuedeMoverAbajo()) {
			int puntajeAbajo = calcularPuntajeMovimiento(2);
			if (puntajeAbajo > puntajeMaximo) {
				puntajeMaximo = puntajeAbajo;
				movimientoSugerido = 2; // Abajo
			}
		}
		if (sePuedeMoverIzquierda()) {
			int puntajeIzquierda = calcularPuntajeMovimiento(3);
			if (puntajeIzquierda > puntajeMaximo) {
				puntajeMaximo = puntajeIzquierda;
				movimientoSugerido = 3; // Izquierda
			}
		}
		if (sePuedeMoverDerecha()) {
			int puntajeDerecha = calcularPuntajeMovimiento(4);
			if (puntajeDerecha > puntajeMaximo) {
				puntajeMaximo = puntajeDerecha;
				movimientoSugerido = 4; // Derecha
			}
		}

		return movimientoSugerido;
	}
	private int calcularPuntajeMovimiento(int direccion) {
		Tablero copiaTablero = new Tablero(cantidadDeFilas, cantidadDeColumnas);
		for (int i = 0; i < cantidadDeFilas; i++) {
			for (int j = 0; j < cantidadDeColumnas; j++) {
				copiaTablero.establecerValorCelda(i, j, matriz[i][j]);
			}
		}

		switch (direccion) {
			case 1:
				copiaTablero.moverArriba();
				break;
			case 2:
				copiaTablero.moverAbajo();
				break;
			case 3:
				copiaTablero.moverIzquierda();
				break;
			case 4:
				copiaTablero.moverDerecha();
				break;
			default:
				return -1; // Dirección inválida
		}

		return copiaTablero.obtenerPuntaje();
	}
	
	private boolean sePuedeMoverAbajo() {
		for (int j = 0; j < cantidadDeColumnas; j++) {
			for (int i = cantidadDeFilas - 2; i >= 0; i--) {
				int valorActual = matriz[i][j];
				if (valorActual != 0) {
					int valorAbajo = matriz[i + 1][j];
					if (valorAbajo == 0 || sePuedenFusionar(valorActual, valorAbajo)) {
						return true;
					}
				}
			}
		}
		return false;
	}
		private boolean sePuedeMoverIzquierda() {
			for (int i = 0; i < cantidadDeFilas; i++) {
				for (int j = 1; j < cantidadDeColumnas; j++) {
					int valorActual = matriz[i][j];
					if (valorActual != 0) {
						int valorIzquierda = matriz[i][j - 1];
						if (valorIzquierda == 0 || sePuedenFusionar(valorActual, valorIzquierda)) {
							return true;
					}
				}
			}
		} return false;
		}
		
		private boolean sePuedeMoverDerecha() {
			for (int i = 0; i < cantidadDeFilas; i++) {
				for (int j = cantidadDeColumnas - 2; j >= 0; j--) {
					int valorActual = matriz[i][j];
					if (valorActual != 0) {
						int valorDerecha = matriz[i][j + 1];
						if (valorDerecha == 0 || sePuedenFusionar(valorActual, valorDerecha)) {
							return true;
						}
					}
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
