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

	// Pide introducir el color de nuestra torre y mientras no sea válido seguirá preguntando
	private static Color elegirColor() {
		Color color = null;
		int numeroColor = 0;
		
		do {
			System.out.println("Introduzca una opción para escoger el color de la torre:");
			System.out.println("1. Blanco");
			System.out.println("2. Negro");
			
			numeroColor = Entrada.entero();
			
			switch(numeroColor) {
				case 1:
					color = Color.BLANCO;
					break;
				case 2:
					color = Color.NEGRO;
					break;
				default:
					System.out.println("ERROR: Ese color no es válido.");
					break;
			}
			
		}while(numeroColor != 1 && numeroColor != 2);
		
		return color;
	}

	// Pide introducir la columna inicial de nuestra torre y mientras no sea válida seguirá preguntando
	private static char elegirColumnaInicial() {
		char columna;
		
		do {
			System.out.println("Introduzca 'a' o 'h' para elegir la columna inicial de la torre:");
			columna = Entrada.caracter();
			
			if(columna != 'a' || columna != 'h') {
				System.out.println("ERROR: Esa columna no es válida.");
			}
			
		} while(columna != 'a' && columna != 'h');
		
		return columna;
	}

	// Mostramos el menú de direcciones
	private static void mostrarMenuDirecciones() {
		System.out.println("Introduzca una opción:");
		System.out.println("1. Arriba.");
		System.out.println("2. Abajo.");
		System.out.println("3. Izquierda.");
		System.out.println("4. Derecha.");
	}

	// Mostramos el menú y pedimos que se elija una opción válida
	private static Direccion elegirDireccion() {
		Direccion direccion = null;
		int opcion;
		
		do {
			mostrarMenuDirecciones();
			opcion = Entrada.entero();
		}while(opcion < 1 || opcion > 6);
			
			switch(opcion) {
				case 1:
					direccion = Direccion.ARRIBA;
					break;
				case 2:
					direccion = Direccion.ABAJO;
					break;
				case 3:
					direccion = Direccion.IZQUIERDA;
					break;
				case 4:
					direccion = Direccion.DERECHA;
					break;
				case 5:
					direccion = Direccion.ENROQUE_CORTO;
					break;
				case 6:
					direccion = Direccion.ENROQUE_LARGO;
					break;
			}
		
		return direccion;
	}

	// Creamos la torre por defecto
	private static void crearTorreDefecto() {
		torre = new Torre();
	}

	// Creamos la torre con un color
	private static void crearTorreColor() {
		torre = new Torre(elegirColor());
	}

}
