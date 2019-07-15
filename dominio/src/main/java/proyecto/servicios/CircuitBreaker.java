package proyecto.servicios;

import java.util.function.Supplier;

public interface CircuitBreaker<R> {

	void ejecutar(Supplier<R>... func) throws CircuitBreakerException;
	
	
}
