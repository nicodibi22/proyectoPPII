package proyecto.servicios.impl;

import java.util.function.Supplier;

import proyecto.servicios.CircuitBreaker;
import proyecto.servicios.CircuitBreakerException;

public class CircuitBreakerImpl implements CircuitBreaker<Boolean, Boolean> {

	@Override
	public Boolean ejecutar(Supplier<Boolean>... func) throws CircuitBreakerException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setTimeout(int timeout) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setIntentos(int intentos) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getTimeout() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getIntentos() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
	private void reset() {
		
	}

}
