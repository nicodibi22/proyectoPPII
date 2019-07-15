package proyecto.servicios.impl;

import java.util.function.Supplier;

import proyecto.servicios.CircuitBreaker;
import proyecto.servicios.CircuitBreakerException;

public class CircuitBreakerNube implements CircuitBreaker<Boolean> {
	
	private int intentos = 5;
	
	private int timeout = 300000;
		
	CircuitBreakerManejoEstado estadoActual;
	
	public CircuitBreakerNube() {
		estadoActual = new CircuitBreakerManejoEstado(timeout, intentos);
	}
		
	public void setTimeout(int timeout) {		
		this.estadoActual.setTimeout(timeout);
	}
	
	public void setIntentos(int intentos) {
		this.estadoActual.setIntentos(intentos);
	}
	
	@Override
	public void ejecutar(Supplier<Boolean>...func) throws CircuitBreakerException {
		
		boolean result = true;
		
		if (this.estadoActual.estaCerrado() || this.estadoActual.estaMedioAbierto()) {
			try {
				for (Supplier<Boolean> f : func) {
					result = result && f.get();
				}
			} catch (Exception e) {
				this.estadoActual.aumentarIntentoFallido();
				throw new CircuitBreakerExecutionException("Error al ejecutar el servicio.");
			}
						
			if (!result) {
				this.estadoActual.aumentarIntentoFallido();
				throw new CircuitBreakerExecutionException("No se obtuvo la respuesta esperada.");
			}
			
			if (this.estadoActual.estaMedioAbierto())
				this.estadoActual.reset();
			
		} else {
			throw new CircuitBreakerOpenException();
		}
		
	}  
	

}
