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
		List<Coordenada> ubicaciones = new ArrayList<>();
		ubicaciones = ubicacionesInicialesNumeros();
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

	public void moverAbajo() {
		int[][] copia = new int[matriz.length][];
		for (int i = 0; i < matriz.length; i++) {
			copia[i] = matriz[i].clone();
		}
	}

}
