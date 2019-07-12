package proyecto.servicios;

import proyecto.servicios.impl.CircuitBreakerException;

public interface CircuitBreaker {

	void ejecutar(String nube) throws CircuitBreakerException;

}
