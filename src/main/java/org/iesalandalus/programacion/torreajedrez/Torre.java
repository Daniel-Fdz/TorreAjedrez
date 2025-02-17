package org.iesalandalus.programacion.torreajedrez;

import javax.naming.OperationNotSupportedException;

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

	// Método que realiza los enroques
	public void enrocar(Direccion direccion) throws OperationNotSupportedException {
		// Si la dirección es nula, lanzamos excepción
		if(direccion == null) {
			throw new NullPointerException("ERROR: La dirección no puede ser nula.");
		}
		
		// Comprobamos que el color sea el correcto y la posición sea la correcta
		if((color.equals(Color.BLANCO) && posicion.getFila() == 1) || (color.equals(Color.NEGRO) && posicion.getFila() == 8)) {
			// Comprobamos el color y dependiendo del resultado asignamos 1 u 8 a la variable
			int fila = color.equals(Color.BLANCO) ? 1 : 8;
			
			// Realizamos los enroques dependiendo del tipo que sea
			if(direccion.equals(Direccion.ENROQUE_CORTO) && posicion.getColumna() == 'h') {
				setPosicion(new Posicion(fila, 'f'));
			} else if(direccion.equals(Direccion.ENROQUE_LARGO) && posicion.getColumna() == 'a') {
				setPosicion(new Posicion(fila, 'd'));
			} else {
				throw new OperationNotSupportedException("ERROR: Movimiento de enroque no válido.");
			}
		} else {
			throw new OperationNotSupportedException("ERROR: Movimiento de enroque no válido.");
		}
	}
	// Método hashCode
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((color == null) ? 0 : color.hashCode());
		result = prime * result + ((posicion == null) ? 0 : posicion.hashCode());
		return result;
	}

	// Método equals
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Torre other = (Torre) obj;
		if (color != other.color)
			return false;
		if (posicion == null) {
			if (other.posicion != null)
				return false;
		} else if (!posicion.equals(other.posicion))
			return false;
		return true;
	}
	
	// Método toString que devuelve la fila, la columna y el color
	@Override
	public String toString() {
		return "fila=" + posicion.getFila() + ", columna=" + posicion.getColumna() + ", color=" + color;
	}
}
