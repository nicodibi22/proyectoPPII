package proyecto.servicios.impl;

import java.util.HashMap;
import java.util.Map;

import proyecto.CircuitBreakerExecutionException;
import proyecto.servicios.CircuitBreaker;
import proyecto.servicios.CircuitBreakerException;

public class CircuitBreakerFactory {

	private static final Map<String, CircuitBreakerImpl> circuitBreakerMap =
            new HashMap<String, CircuitBreakerImpl>();
	
	public static CircuitBreaker crearCircuitBreaker(String nombre, int fallosPermitidos, long timeout) {
		CircuitBreaker circuitBreaker = buscarCircuitBreaker(nombre);
		
		if (circuitBreaker == null) {
			agregarCircuitBreaker(nombre, fallosPermitidos, timeout);
		} else {
			circuitBreaker.setIntentos(fallosPermitidos);
			circuitBreaker.setTimeout(timeout);
		}
		return circuitBreaker;
	}
	
	private static CircuitBreaker buscarCircuitBreaker(String nombre) {
		return circuitBreakerMap.get(nombre);
	}
	
	private static void agregarCircuitBreaker(String nombre, int fallosPermitidos, long timeout) {
		circuitBreakerMap.put(nombre, new CircuitBreakerImpl(nombre, fallosPermitidos, timeout));
	}
	
	public static CircuitBreaker getCircuitBreaker(String nombre) throws CircuitBreakerException {
		CircuitBreakerImpl circuitBreaker = circuitBreakerMap.get(nombre);
		if (circuitBreaker == null) {
			throw new CircuitBreakerExecutionException("");
		}
		return circuitBreaker;
	}
}
