package tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class game {
	private static int[][] matriz;
	public void TableroJuego() {
        this.matriz = new int[4][4];}
	
	private static int siguienteNumero() {
		Random aleatorio = new Random();
		int n = aleatorio.nextInt(1, 4);
		return n;
	}
	private boolean espacioVacio (int fila , int columna ) {
		return matriz[fila][columna]==0;
	}
	private static List<Cordenada> ubicacionesInicialesNumeros(int [][] matriz){
		List<Cordenada> ubicaciones= new ArrayList<>();
		for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                ubicaciones.add(new Cordenada(i, j));
            }
        }
		Collections.shuffle(ubicaciones);
		return ubicaciones.subList(0, 9);
	}
	
	
	private void inicializarMatriz(int [][] matriz) {
		
		List<Cordenada> iniciales = ubicacionesInicialesNumeros(matriz);
		
        for (Cordenada c : iniciales) {
        	matriz[c.getFila()][c.getColumna()]=siguienteNumero();
        }
	}
	
	
	private boolean revisarAlgunaColLlena(int [][] matriz) {
		boolean esValido = true;
		for (int i = 0; i < 4; i++) {
			boolean colLlena= true;
		
			for(int j = 0; j < 4; j++) {
				if (matriz[j][i] == 0) {
	                colLlena = false;
	                break;	
			}}
			if (colLlena) {
	            return true;}}
		return false;
	
		}
	
	public static void main(String[] args) {
		System.out.println("00 00 00 00 \n00 00 00 00");
		List<Cordenada> iniciales = ubicacionesInicialesNumeros(matriz);
        for (Cordenada c : iniciales) {
            System.out.println("Fila: " + c.getFila() + ", Columna: " + c.getColumna());
        }
		System.out.println( ubicacionesInicialesNumeros(matriz));
		System.out.println( siguienteNumero());System.out.println( siguienteNumero());System.out.println( siguienteNumero());System.out.println( siguienteNumero());System.out.println( siguienteNumero());System.out.println( siguienteNumero());
	}

}
