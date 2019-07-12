package proyecto.servicios;

import java.util.function.Function;
import java.util.function.Supplier;

import proyecto.servicios.impl.CircuitBreakerException;

public interface CircuitBreaker<T, R> {

	void ejecutar(String nube) throws CircuitBreakerException;

	void ejecutar(Supplier<R> func) throws CircuitBreakerException;
}
