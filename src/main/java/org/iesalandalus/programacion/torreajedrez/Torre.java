package org.iesalandalus.programacion.torreajedrez;

public class Torre {
	
	// Atributos de la clase
	private Color color;
	private Posicion posicion;

	// Constructor por defecto
	public Torre() {
		setColor(Color.NEGRO);
		setPosicion(new Posicion(8, 'h'));
	}

	// Constructor con parámetro de color
	public Torre(Color color) {
		setColor(color);
		if(color.equals(Color.BLANCO)) {
			setPosicion(new Posicion(1, 'h'));
		} else {
			setPosicion(new Posicion(8, 'h'));
		}
	}

	// Constructor con los parámetros de color y columna
	public Torre(Color color, char columna) {
		setColor(color);
		
		if(columna !='a' && columna != 'h') {
			throw new IllegalArgumentException("ERROR: Columna no válida.");
		} else {
			if(color.equals(Color.BLANCO)) {
				setPosicion(new Posicion(1, columna));
			} else {
				setPosicion(new Posicion(8, columna));
			}
		}
	}

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
