package proyecto.servicios.impl;

public class CircuitBreakerException extends Exception {
		
		private static final long serialVersionUID = 1L;

		public CircuitBreakerException(String mensaje) {		
			super(mensaje);
		}
		
	}
