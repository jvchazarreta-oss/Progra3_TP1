package tree;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
 
public class Juego {
 
	private int[][] matriz;
 
	public void TableroJuego() {
		this.matriz = new int[4][4];
	}
 
	public static int siguienteNumero() {
		Random aleatorio = new Random();
		int n = aleatorio.nextInt(1, 4);
		return n;
	}
 
	public boolean espacioVacio(int fila, int columna) {
		return matriz[fila][columna] == 0;
	}
 
	public static List<Cordenada> ubicacionesInicialesNumeros(int[][] matriz) {
		List<Cordenada> ubicaciones = new ArrayList<>();
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				ubicaciones.add(new Cordenada(i, j));
			}
		}
		Collections.shuffle(ubicaciones);
		return ubicaciones.subList(0, 9);
	}
 
	public void inicializarMatriz(int[][] matriz) {
		List<Cordenada> iniciales = ubicacionesInicialesNumeros(matriz);
		for (Cordenada c : iniciales) {
			matriz[c.getFila()][c.getColumna()] = siguienteNumero();
		}
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
 
	public int[][] getMatriz() {
		return matriz;
	}
}