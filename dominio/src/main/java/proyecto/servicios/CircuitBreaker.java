package proyecto.servicios;

import java.util.concurrent.Callable;
import java.util.function.Supplier;

public interface CircuitBreaker {

	<T> T invoke(Callable<T> c) throws Exception;
	
	<T> T ejecutar(Supplier<T> func) throws CircuitBreakerException;
	
	void setTimeout(long timeout);
	
	void setIntentos(int intentos);
	
	long getTimeout();
	
	int getIntentos();
}
