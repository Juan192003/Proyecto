package Logicaa;

import java.util.Random;
import java.util.Scanner;

public class Juego {
	public static void main(String[] args) {
		Carta[][] tablero = new Carta[2][4];
		int[] valores = {1, 2, 3, 4, 1, 2, 3, 4};
		Random rand = new Random();
		Scanner user = new Scanner(System.in);
		int puntos = 0;
		int intentos = 0;

		// Mezclar los valores
		for (int i = 0; i < valores.length; i++) {
			int j = rand.nextInt(valores.length);
			int auxiliar = valores[i];
			valores[i] = valores[j];
			valores[j] = auxiliar;
		}

		// Crear las cartas en el tablero
		int indice = 0;
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 4; j++) {
				tablero[i][j] = new Carta(valores[indice]);
				indice++;
			}
		}

		System.out.println("JUEGO DE MEMORIA - ENCUENTRA LAS PAREJAS");

		while (puntos < 40) {
			System.out.println("\nPuntos: " + puntos + " | Intentos: " + intentos);
			mostrarTablero(tablero);

			// Primer carta
			System.out.print("\nPrimera casilla - fila (1-2): ");
			int f1 = user.nextInt() - 1;
			System.out.print("Primera casilla - columna (1-4): ");
			int c1 = user.nextInt() - 1;

			if (!esValida(tablero, f1, c1)) {
				System.out.println("Casilla inválida. Intenta de nuevo.");
				continue;
			}

			tablero[f1][c1].setEstado(1); // Mostrar temporal
			mostrarTablero(tablero);

			// Segunda carta
			System.out.print("\nSegunda casilla - fila (1-2): ");
			int f2 = user.nextInt() - 1;
			System.out.print("Segunda casilla - columna (1-4): ");
			int c2 = user.nextInt() - 1;

			if (!esValida(tablero, f2, c2) || (f1 == f2 && c1 == c2)) {
				System.out.println("Casilla inválida o repetida. Pierdes este intento.");
				tablero[f1][c1].setEstado(0);
				intentos++;
				continue;
			}

			tablero[f2][c2].setEstado(1);
			mostrarTablero(tablero);
			intentos++;

			if (tablero[f1][c1].getValor() == tablero[f2][c2].getValor()) {
				System.out.println("\n¡Pareja encontrada! +10 puntos");
				tablero[f1][c1].setEstado(2);
				tablero[f2][c2].setEstado(2);
				puntos += 10;
			} else {
				System.out.println("\nNo es pareja. Sigue intentando.");
				tablero[f1][c1].setEstado(0);
				tablero[f2][c2].setEstado(0);
			}

			try {
				Thread.sleep(2000);
			} catch (Exception e) {
				System.out.println("Error en la pausa");
			}
		}

		System.out.println("\n¡GANASTE! Puntaje final: " + puntos);
		user.close();
	}

	public static void mostrarTablero(Carta[][] tablero) {
		System.out.println("\n--- TABLERO ---");
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 4; j++) {
				Carta carta = tablero[i][j];
				if (carta.getEstado() == 0) {
					System.out.print("[X]");
				} else {
					System.out.print("[" + carta.getValor() + "]");
				}
			}
			System.out.println();
		}
	}

	public static boolean esValida(Carta[][] tablero, int fila, int col) {
		if (fila < 0 || fila > 1 || col < 0 || col > 3) return false;
		return tablero[fila][col].getEstado() != 2;
	}
}