package proyecto.servicios;

import java.util.function.Supplier;

public interface CircuitBreaker<T, R> {

	R ejecutar(Supplier<T>... func) throws CircuitBreakerException;
	
	void setTimeout(int timeout);
	
	void setIntentos(int intentos);
	
	int getTimeout();
	
	int getIntentos();
}
