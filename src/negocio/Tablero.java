package negocio;

import static constantes.Constantes.CANTIDAD_COLUMNAS;
import static constantes.Constantes.CANTIDAD_FILAS;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Tablero {
	private static final int CANTIDAD_FICHAS_INICIALES = 9;
	private static final int VALOR_CELDA_UMBRAL_SUMA_PUNTAJE = 3;

	private int[][] matriz;
	private int cantidadColumnas;
	private int cantidadFilas;
	private int siguienteNumero;
	private int puntaje;
	
	public Tablero(int fila, int col) {
		this.matriz = new int[fila][col];
		this.cantidadColumnas = col;
		this.cantidadFilas = fila;
		this.siguienteNumero = 0;

		cargarTablero();
	}

	public enum Direccion {
		ARRIBA, ABAJO, IZQUIERDA, DERECHA
	}

	public int[][] obtenerTablero() {
		return this.matriz;
	}

	public int obtenerFilas() {
		return this.cantidadFilas;
	}

	public int obtenerCol() {
		return this.cantidadColumnas;
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
		for (int i = 0; i < CANTIDAD_FILAS; i++) {
			for (int j = 0; j < CANTIDAD_COLUMNAS; j++) {
				ubicaciones.add(new Coordenada(i, j));
			}
		}
		Collections.shuffle(ubicaciones);
		return ubicaciones.subList(0, CANTIDAD_FICHAS_INICIALES);
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
	public boolean mover(Direccion direccion, boolean esSugerencia) {
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

	public boolean moverArriba() {
		return mover(Direccion.ARRIBA,false);
	}

	public boolean moverAbajo() {
		return mover(Direccion.ABAJO,false);
	}

	public boolean moverIzquierda() {
		return mover(Direccion.IZQUIERDA,false);
	}

	public boolean moverDerecha() {
		return mover(Direccion.DERECHA,false);
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
			puntaje = puntaje + matriz[filaDestino][colDestino] ;
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
		Tablero copiaTablero = new Tablero(cantidadFilas, cantidadColumnas);
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
				if (matriz[i][j] >= VALOR_CELDA_UMBRAL_SUMA_PUNTAJE)
					puntajeTotal += matriz[i][j];
		return puntajeTotal;
	}

}
