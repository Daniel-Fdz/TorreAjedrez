package org.iesalandalus.programacion.torreajedrez;

import javax.naming.OperationNotSupportedException;
import org.iesalandalus.programacion.utilidades.Entrada;

public class MainApp {
	
	// Atributos de la clase
	private static Torre torre;
	private static int salida = 0;

	public static void main(String[] args) {
		// Bucle del juego que se repetirá hasta que salida valga 1
		while(salida == 0) {
			ejecutarOpcion(elegirOpcion());
			System.out.println("\n");
		}
		System.out.println("Fin del juego.");
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
			
			if(columna != 'a' && columna != 'h') {
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

	// Creamos la torre con un color pasado por parámetro
	private static void crearTorreColor() {
		torre = new Torre(elegirColor());
	}

	// Creamos la torre con un color y una columna pasados por parámetros
	private static void crearTorreColorColumna() {
		torre = new Torre(elegirColor(), elegirColumnaInicial());
	}

	// Muestra el menú de direcciones, pregunta por la dirección, cantidad de pasos y mueve la torre
	private static void mover() {
		Direccion direccion;
		int pasos;
		
		if(torre == null) {
			System.out.println("ERROR: La torre no puede ser nula.");
		} else {
			System.out.println("Introduzca los pasos que quiere dar:");
			pasos = Entrada.entero();
			direccion = elegirDireccion();
			
			try {
				torre.mover(direccion, pasos);
			} catch(OperationNotSupportedException e) {
				System.out.println(e.getMessage());
			}
		}
	}

	// Obtiene una opción por parámetro y la ejecuta según sea el caso
	private static void ejecutarOpcion(int opcion) {
		switch(opcion) {
			case 1:
				crearTorreDefecto();
				mostrarTorre();
				break;
			case 2:
				crearTorreColor();
				mostrarTorre();
				break;
			case 3:
				crearTorreColorColumna();
				mostrarTorre();
				break;
			case 4:
				mover();
				mostrarTorre();
				break;
			case 5:
				try {
					torre.enrocar(Direccion.ENROQUE_CORTO);
					mostrarTorre();
				} catch (OperationNotSupportedException e) {
					System.out.println("ERROR: No es posible realizar un enroque corto en esta posición.");
				}
				break;
			case 6:
				try {
					torre.enrocar(Direccion.ENROQUE_LARGO);
					mostrarTorre();
				} catch (OperationNotSupportedException e) {
					System.out.println("ERROR: No es posible realizar un enroque largo en esta posición.");
				}
				break;
			case 7:
				salida = 1;
				break;
			default:
				System.out.println("ERROR: Esta opción no es válida.");
				break;
		}
	}
}
