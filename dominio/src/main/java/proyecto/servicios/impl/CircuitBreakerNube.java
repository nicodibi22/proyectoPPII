package proyecto.servicios.impl;

import java.util.function.Supplier;

import proyecto.Resultado;
import proyecto.Resultado.EstadoResultado;
import proyecto.servicios.CircuitBreaker;
import proyecto.servicios.CircuitBreakerException;

public class CircuitBreakerNube implements CircuitBreaker<Boolean, Resultado> {
	
	private int intentos = 5;
	
	private int timeout = 300000;
		
	CircuitBreakerManejoEstado estadoActual;
	
	public CircuitBreakerNube() {
		estadoActual = new CircuitBreakerManejoEstado(timeout, intentos);
	}
		
	@Override
	public void setTimeout(int timeout) {		
		this.estadoActual.setTimeout(timeout);
	}
	
	@Override
	public void setIntentos(int intentos) {
		this.estadoActual.setIntentos(intentos);
	}
	
	@Override
	public int getTimeout() {		
		return this.estadoActual.getTimeout();
	}
	
	@Override
	public int getIntentos() {
		return this.estadoActual.getIntentos();
	}
	
	@Override
	public Resultado ejecutar(Supplier<Boolean>...func) throws CircuitBreakerException {
		
		boolean result = true;
		Resultado resultado = null;
		if(this.estadoActual.estaAbierto()) {
			throw new CircuitBreakerOpenException();
		}
		
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
			resultado = new Resultado();
			resultado.setEstado(EstadoResultado.OK);
			resultado.setRespuesta("OK - Circuit Breaker Success");
			if (this.estadoActual.estaMedioAbierto())
				this.estadoActual.reset();
			
		} 
		return resultado;
		
	}  
	

}
