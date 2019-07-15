package proyecto.servicios.impl;

import proyecto.servicios.CircuitBreakerException;

public class CircuitBreakerExecutionException extends CircuitBreakerException {
	
	private static final long serialVersionUID = 1L;

	public CircuitBreakerExecutionException(String mensaje) {		
		super(mensaje);
	}
	
}
