package org.iesalandalus.programacion.torreajedrez;

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

}
