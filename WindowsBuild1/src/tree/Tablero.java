package tree;

public class Tablero {
	private int[][] matriz;
	private int cantidadDeColumnas;
	private int cantidadDeFilas;
 
	public  Tablero(int fila, int col) {
		this.matriz = new int[fila][col];
		this.cantidadDeColumnas=col;
		this.cantidadDeFilas=fila;
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
        int valorCelda=this.matriz[fil][col];
		return valorCelda;
}}
