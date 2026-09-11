package negocio;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Tablero {

	private int[][] matriz;
	private int cantidadColumnas;
	private int cantidadFilas;
	private int siguienteNumero;
	private int puntaje;
	private int cantidadFichasIniciales;
	private int valorCeldaUmbralQueSumaPuntaje;

	public Tablero(int filas, int columnas, int cantidadFichasIniciales, int valorCeldaUmbralQueSumaPuntaje) {
		this.matriz = new int[filas][columnas];
		this.cantidadColumnas = columnas;
		this.cantidadFilas = filas;
		this.siguienteNumero = 0;
		this.cantidadFichasIniciales = cantidadFichasIniciales;
		this.valorCeldaUmbralQueSumaPuntaje = valorCeldaUmbralQueSumaPuntaje;

		cargarTablero();
	}

	public enum Direccion {
		ARRIBA, ABAJO, IZQUIERDA, DERECHA, NINGUNA
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
		for (int i = 0; i < cantidadFilas; i++) {
			for (int j = 0; j < cantidadColumnas; j++) {
				ubicaciones.add(new Coordenada(i, j));
			}
		}
		Collections.shuffle(ubicaciones);
		return ubicaciones.subList(0, this.cantidadFichasIniciales);
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

	public boolean mover(Direccion direccion) {
		return mover(direccion, false);
	}

	private boolean mover(Direccion direccion, boolean esSugerencia) {
		boolean huboCambio = false;

		switch (direccion) {
		case ARRIBA:
			huboCambio = moverVerticalmente(-1);
			break;
		case ABAJO:
			huboCambio = moverVerticalmente(1);
			break;
		case IZQUIERDA:
			huboCambio = moverHorizontalmente(-1);
			break;
		case DERECHA:
			huboCambio = moverHorizontalmente(1);
			break;
		}

		if (huboCambio && !esSugerencia) {
			agregarFichaSegunDireccion(direccion);
		}

		return huboCambio;
	}

	private void agregarFichaSegunDireccion(Direccion direccion) {
		switch (direccion) {
		case ARRIBA:
			agregarFichaEnFilaSiHayLugar(cantidadFilas - 1);
			break;
		case ABAJO:
			agregarFichaEnFilaSiHayLugar(0);
			break;
		case IZQUIERDA:
			agregarFichaEnColumnaSiHayLugar(cantidadColumnas - 1);
			break;
		case DERECHA:
			agregarFichaEnColumnaSiHayLugar(0);
			break;
		}
	}

	private boolean moverFicha(int filaInicial, int colInicial, int filaDestino, int colDestino) {
		int valorInicial = matriz[filaInicial][colInicial];
		int valorDestino = matriz[filaDestino][colDestino];

		if (valorInicial == 0) {
			return false;
		}

		if (valorDestino == 0) {
			matriz[filaDestino][colDestino] = valorInicial;
			matriz[filaInicial][colInicial] = 0;
			return true;
		}

		if (sePuedenFusionar(valorInicial, valorDestino)) {
			matriz[filaDestino][colDestino] = valorInicial + valorDestino;
			matriz[filaInicial][colInicial] = 0;
			// puntaje = puntaje + matriz[filaDestino][colDestino];
			return true;
		}

		return false;
	}

	private boolean moverVerticalmente(int desplazamiento) {
		boolean huboCambio = false;

		if (desplazamiento < 0) {
			// Arriba
			for (int columna = 0; columna < cantidadColumnas; columna++) {
				for (int fila = 1; fila < cantidadFilas; fila++) {
					if (moverFicha(fila, columna, fila - 1, columna)) {
						huboCambio = true;
					}
				}
			}
		} else {
			// Abajo
			for (int columna = 0; columna < cantidadColumnas; columna++) {
				for (int fila = cantidadFilas - 2; fila >= 0; fila--) {
					if (moverFicha(fila, columna, fila + 1, columna)) {
						huboCambio = true;
					}
				}
			}
		}

		return huboCambio;
	}

	private boolean moverHorizontalmente(int desplazamiento) {
		boolean huboCambio = false;

		for (int fila = 0; fila < cantidadFilas; fila++) {
			if (desplazamiento < 0) {
				// Izquierda
				for (int columna = 1; columna < cantidadColumnas; columna++) {
					if (moverFicha(fila, columna, fila, columna - 1)) {
						huboCambio = true;
					}
				}
			} else {
				// Derecha
				for (int columna = cantidadColumnas - 2; columna >= 0; columna--) {
					if (moverFicha(fila, columna, fila, columna + 1)) {
						huboCambio = true;
					}
				}
			}
		}

		return huboCambio;
	}

	public boolean sePuedenFusionar(int a, int b) {
		boolean sonIgualesYMayoresA3 = (a == b) && (a >= 3);
		boolean sonUnoYDos = (a == 1 && b == 2) || (a == 2 && b == 1);

		return sonIgualesYMayoresA3 || sonUnoYDos;
	}

	private int elegirAlAzar(List<Integer> opciones) {
		Random aleatorio = new Random();
		int indice = aleatorio.nextInt(opciones.size());
		return opciones.get(indice);
	}

	private void agregarFichaEnFilaSiHayLugar(int fila) {
		List<Integer> columnasLibres = new ArrayList<>();
		for (int j = 0; j < cantidadColumnas; j++) {
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
		for (int i = 0; i < cantidadFilas; i++) {
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
		for (int i = 0; i < cantidadFilas; i++) {
			for (int j = 0; j < cantidadColumnas; j++) {
				if (matriz[i][j] == 0)
					return true;
			}
		}
		return false;
	}

	private boolean sePuedeMoverArriba() {
		for (int j = 0; j < cantidadColumnas; j++) {
			for (int i = 1; i < cantidadFilas; i++) {
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

	public Direccion sugerenciaDeMovimientoConMayorPuntaje() {
		Direccion movimientoSugerido = Direccion.NINGUNA;
		int puntajeMaximo = -1;

		if (sePuedeMoverArriba()) {
			int puntajeArriba = calcularPuntajeMovimiento(1);
			if (puntajeArriba > puntajeMaximo) {
				puntajeMaximo = puntajeArriba;
				movimientoSugerido = Direccion.ARRIBA;
			}
		}
		if (sePuedeMoverAbajo()) {
			int puntajeAbajo = calcularPuntajeMovimiento(2);
			if (puntajeAbajo > puntajeMaximo) {
				puntajeMaximo = puntajeAbajo;
				movimientoSugerido = Direccion.ABAJO;
			}
		}
		if (sePuedeMoverIzquierda()) {
			int puntajeIzquierda = calcularPuntajeMovimiento(3);
			if (puntajeIzquierda > puntajeMaximo) {
				puntajeMaximo = puntajeIzquierda;
				movimientoSugerido = Direccion.IZQUIERDA;
			}
		}
		if (sePuedeMoverDerecha()) {
			int puntajeDerecha = calcularPuntajeMovimiento(4);
			if (puntajeDerecha > puntajeMaximo) {
				puntajeMaximo = puntajeDerecha;
				movimientoSugerido = Direccion.DERECHA;
			}
		}

		return movimientoSugerido;
	}

	private int calcularPuntajeMovimiento(int direccion) {
		Tablero copiaTablero = new Tablero(cantidadFilas, cantidadColumnas, cantidadFichasIniciales,
				valorCeldaUmbralQueSumaPuntaje);
		for (int i = 0; i < cantidadFilas; i++) {
			for (int j = 0; j < cantidadColumnas; j++) {
				copiaTablero.establecerValorCelda(i, j, matriz[i][j]);
			}
		}

		switch (direccion) {
		case 1:
			copiaTablero.mover(Direccion.ARRIBA, true);
			break;
		case 2:
			copiaTablero.mover(Direccion.ABAJO, true);
			break;
		case 3:
			copiaTablero.mover(Direccion.IZQUIERDA, true);
			break;
		case 4:
			copiaTablero.mover(Direccion.DERECHA, true);
			break;
		default:
			return -1; // Dirección inválida
		}

		return copiaTablero.obtenerPuntaje();
	}

	private boolean sePuedeMoverAbajo() {
		for (int j = 0; j < cantidadColumnas; j++) {
			for (int i = cantidadFilas - 2; i >= 0; i--) {
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
		for (int i = 0; i < cantidadFilas; i++) {
			for (int j = 1; j < cantidadColumnas; j++) {
				int valorActual = matriz[i][j];
				if (valorActual != 0) {
					int valorIzquierda = matriz[i][j - 1];
					if (valorIzquierda == 0 || sePuedenFusionar(valorActual, valorIzquierda)) {
						return true;
					}
				}
			}
		}
		return false;
	}

	private boolean sePuedeMoverDerecha() {
		for (int i = 0; i < cantidadFilas; i++) {
			for (int j = cantidadColumnas - 2; j >= 0; j--) {
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
		for (int i = 0; i < cantidadFilas; i++) {
			for (int j = 0; j < cantidadColumnas; j++) {
				int actual = matriz[i][j];
				if (actual == 0)
					continue;
				if (j + 1 < cantidadColumnas && sePuedenFusionar(actual, matriz[i][j + 1]))
					return true;
				if (i + 1 < cantidadFilas && sePuedenFusionar(actual, matriz[i + 1][j]))
					return true;
			}
		}
		return false;
	}

	public boolean juegoTerminado() {
		return !hayCeldaVacia() && !hayFusionPosible();
	}

	public int obtenerPuntaje() {
		int puntajeTotal = puntaje;
		for (int i = 0; i < cantidadFilas; i++)
			for (int j = 0; j < cantidadColumnas; j++)
				if (matriz[i][j] >= this.valorCeldaUmbralQueSumaPuntaje)
					puntajeTotal += matriz[i][j];
		return puntajeTotal;
	}

}
