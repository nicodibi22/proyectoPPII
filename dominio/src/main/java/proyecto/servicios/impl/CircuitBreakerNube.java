package proyecto.servicios.impl;

import java.util.concurrent.Callable;
import java.util.function.Supplier;

import proyecto.CircuitBreakerExecutionException;
import proyecto.CircuitBreakerOpenException;
import proyecto.Resultado;
import proyecto.Resultado.EstadoResultado;
import proyecto.servicios.CircuitBreaker;
import proyecto.servicios.CircuitBreakerException;

public class CircuitBreakerNube implements CircuitBreaker {
	
	private int intentos = 5;
	
	private int timeout = 300000;
		
	CircuitBreakerManejoEstado estadoActual;
	
	public CircuitBreakerNube() {
		estadoActual = new CircuitBreakerManejoEstado(timeout, intentos);
	}
		
	@Override
	public void setTimeout(long timeout) {		
		//this.estadoActual.setTimeout(timeout);
	}
	
	@Override
	public void setIntentos(int intentos) {
		this.estadoActual.setIntentos(intentos);
	}
	
	@Override
	public long getTimeout() {		
		return this.estadoActual.getTimeout();
	}
	
	@Override
	public int getIntentos() {
		return this.estadoActual.getIntentos();
	}
	

	@Override
	public <T> T ejecutar(Supplier<T> func) throws CircuitBreakerException {
		
		boolean result = true;
		Resultado resultado = new Resultado();
		if(this.estadoActual.estaAbierto()) {
			throw new CircuitBreakerOpenException();
		}
		
		if (this.estadoActual.estaCerrado() || this.estadoActual.estaMedioAbierto()) {
			try {
				
				func.get();
				
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
		return null;
		
	}  
	
	@Override
	public <Boolean> Boolean invoke(Callable<Boolean> c) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
}
