package proyecto.servicios.impl;

import proyecto.servicios.CircuitBreakerException;

public class CircuitBreakerOpenException extends CircuitBreakerException {
	
	private static final long serialVersionUID = 1L;

	public CircuitBreakerOpenException() {		
		super("El circuito se encuentra abierto.");
	}
	
}