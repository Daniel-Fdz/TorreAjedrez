package org.iesalandalus.programacion.torreajedrez;

public class Torre {
	
	// Atributos de la clase
	private Color color;
	private Posicion posicion;

	// Devuelve el color
	public Color getColor() {
		return color;
	}
	
	// Establece el color. No puede ser nulo
	private void setColor(Color color) {
		if(color == null) {
			throw new NullPointerException("ERROR: No se puede asignar un color nulo.");
		} else {
			this.color = color;
		}
	}
	
	// Devuelve la posición
	public Posicion getPosicion() {
		return posicion;
	}
	
	// Establece la posición. No puede ser nula
	private void setPosicion(Posicion posicion) {
		if(posicion == null) {
			throw new NullPointerException("ERROR: La posición no puede ser nula.");
		} else {
			this.posicion = posicion;
		}
	}
}
