package org.iesalandalus.programacion.torreajedrez;

public class Posicion {
	
	// Atributos de la clase
	private int fila;
	private char columna;
	
	// Devuelve la fila
	public int getFila() {
		return fila;
	}
	
	// Establece la fila. Tiene que ser entre 1 y 8
	private void setFila(int fila) {
		if (fila < 1 || fila > 8) {
			throw new IllegalArgumentException("ERROR: Fila no válida.");
		} else {
			this.fila = fila;
		}
	}
	
	// Devuelve la columna
	public char getColumna() {
		return columna;
	}
	
	// Establece la columna. Tiene que ser entre 'a' y 'h'
	private void setColumna(char columna) {
		if (columna < 'a' || columna > 'h') {
			throw new IllegalArgumentException("ERROR: Columna no válida.");
		} else {
			this.columna = columna;
		}
	}
}
