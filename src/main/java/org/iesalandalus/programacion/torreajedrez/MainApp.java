package org.iesalandalus.programacion.torreajedrez;

import org.iesalandalus.programacion.utilidades.Entrada;

public class MainApp {
	
	private static Torre torre;

	public static void main(String[] args) {
		
	}
	
	// Muestra la respresentación de la torre
	private static void mostrarTorre() {
		try {
			System.out.println(torre.toString());
		} catch(NullPointerException e) {
			System.out.println("ERROR: No se ha creado ninguna torre.");
		}
	}

	// Menú con las diferentes opciones para el jugador
	private static void mostrarMenu() {
		System.out.println("Introduzca una opción:");
		System.out.println("*-----------------------------------------------------------------*");
		System.out.println("1. Crear una torre por defecto");
		System.out.println("2. Crear una torre de un color");
		System.out.println("3. Crear una torre de un color en una columna inicial (a o h)");
		System.out.println("4. Mover la torre");
		System.out.println("5. Enroque corto");
		System.out.println("6. Enroque largo");
		System.out.println("7. Salir");
		System.out.println("*-----------------------------------------------------------------*");
	}

	// Muestra el menú para elegir una opción
	private static int elegirOpcion() {
		mostrarMenu();
		int opcion = Entrada.entero();
		return opcion;
	}

}
