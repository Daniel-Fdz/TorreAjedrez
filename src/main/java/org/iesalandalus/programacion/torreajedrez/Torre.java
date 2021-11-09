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

	// Método para el movimiento de la torre. Los pasos deben ser positivos y la dirección no puede ser nula
	public void mover(Direccion direccion, int pasos) throws OperationNotSupportedException {
		
		if(pasos < 1) {
			throw new IllegalArgumentException("ERROR: El número de pasos debe ser positivo.");
		}
		
		if(direccion == null) {
			throw new NullPointerException("ERROR: La dirección no puede ser nula.");
		}
		
		// Si el color de la torre es negro, invertimos las direcciones
		if(color.equals(Color.NEGRO)) {
			Direccion dirDefinitiva = direccion;
			
			switch(direccion) {
				case ARRIBA:
					dirDefinitiva = Direccion.ABAJO;
					break;
				case ABAJO:
					dirDefinitiva = Direccion.ARRIBA;
					break;
				case IZQUIERDA:
					dirDefinitiva = Direccion.DERECHA;
					break;
				case DERECHA:
					dirDefinitiva = Direccion.IZQUIERDA;
					break;
				default:
					break;
			}
			
			// Guardamos la dirección nueva en "direccion"
			direccion = dirDefinitiva;
		}
		
		// Comprobamos la dirección y movemos la torre controlando los errores
		switch(direccion) {
			case ARRIBA:
				try {
					setPosicion(new Posicion(posicion.getFila() + pasos, (char) (posicion.getColumna())));
				} catch(IllegalArgumentException e) {
					throw new OperationNotSupportedException("ERROR: Movimiento no válido (se sale del tablero).");
				}
				
				break;
				
			case ABAJO:
				try {
					setPosicion(new Posicion(posicion.getFila() - pasos, (char) (posicion.getColumna())));
				} catch(IllegalArgumentException e) {
					throw new OperationNotSupportedException("ERROR: Movimiento no válido (se sale del tablero).");
				}
				
				break;
				
			case IZQUIERDA:
				try {
					setPosicion(new Posicion(posicion.getFila(), (char) (posicion.getColumna() - pasos)));
				} catch(IllegalArgumentException e) {
					throw new OperationNotSupportedException("ERROR: Movimiento no válido (se sale del tablero).");
				}
				
				break;
				
			case DERECHA:
				try {
					setPosicion(new Posicion(posicion.getFila(), (char) (posicion.getColumna() + pasos)));
				} catch(IllegalArgumentException e) {
					throw new OperationNotSupportedException("ERROR: Movimiento no válido (se sale del tablero).");
				}
				break;
				
			case ENROQUE_LARGO:
			case ENROQUE_CORTO:
				enrocar(direccion);
				break;
				
		}
	}
}
