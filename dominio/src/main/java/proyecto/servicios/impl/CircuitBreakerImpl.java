package proyecto.servicios.impl;

import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Supplier;

import proyecto.CircuitBreakerOpenException;
import proyecto.servicios.CircuitBreaker;
import proyecto.servicios.CircuitBreakerException;

public class CircuitBreakerImpl implements CircuitBreaker {

	protected enum CircuitBreakerEstado {
        /** An OPEN breaker has tripped and will not allow requests
            through. */
        OPEN,

        /** A HALF_CLOSED breaker has completed its cooldown
            period and will allow one request through as a "test request." */
        HALF_CLOSED,

        /** A CLOSED breaker is operating normally and allowing
            requests through. */
        CLOSED
    }
	
	private final String NAME_DEFAULT = "CircuitBreaker";
	
	private final AtomicLong TIMEOUT_DEFAULT = new AtomicLong(10000L);
	
	private CircuitBreakerEstado estado;

	private String nombre = NAME_DEFAULT;
	
	private int contadorFallos = 0;
	
	private int fallosPermitidos = 5;
	
	private AtomicLong ultimoFallo;
	
	private AtomicLong timeout = TIMEOUT_DEFAULT;
	
	public CircuitBreakerImpl() {
		estado = CircuitBreakerEstado.CLOSED;		
	}
	
	public CircuitBreakerImpl(String nombre, int fallosPermitidos, long timeout) {
		this();
		this.nombre = nombre;		
		this.fallosPermitidos = fallosPermitidos;
		this.timeout = new AtomicLong(timeout);
	}
	
	@Override
	public void setTimeout(long timeout) {
		this.timeout = new AtomicLong(timeout);		
	}

	@Override
	public void setIntentos(int intentos) {
		this.fallosPermitidos = intentos;
	}

	@Override
	public long getTimeout() {
		return timeout.get();
	}

	@Override
	public int getIntentos() {
		return fallosPermitidos;
	}
	
	public String getNombre() {
		return this.nombre;
	}
	
	private void manejarErrores() {
		ultimoFallo = new AtomicLong(System.currentTimeMillis());
		if (estado == CircuitBreakerEstado.HALF_CLOSED) {
			estado = CircuitBreakerEstado.OPEN;
		} else {
			contadorFallos++;
			if (contadorFallos >= fallosPermitidos) {
				estado = CircuitBreakerEstado.OPEN;
			}
		}
			
	}
	
	private void reiniciar() {
		estado = CircuitBreakerEstado.CLOSED;
		contadorFallos = 0;
	}

	private void cerrar() {
		estado = CircuitBreakerEstado.CLOSED;
		contadorFallos = 0;
	}
	
	@Override
	public <T> T invoke(Callable<T> c) throws Exception {
		if (!permiteEjecuciones()) {
            new CircuitBreakerOpenException();
        }

        try {
            
            T result = c.call();
            cerrar();
            return result;
        } catch (Throwable cause) {
            manejarErrores();            
        }
        throw new IllegalStateException("not possible");
	}

	@Override
	public <T> T ejecutar(Supplier<T> func) throws CircuitBreakerException {
		
		if (!permiteEjecuciones()) {
            new CircuitBreakerOpenException();
        }

        try {
            
            T result = func.get();
            cerrar();
            return result;
        } catch (Throwable cause) {
            manejarErrores();
        }
        throw new IllegalStateException("not possible");
	}

	private boolean permiteEjecuciones() {
		
		if (estado == CircuitBreakerEstado.CLOSED)
			return true;
		
		else if (estado == CircuitBreakerEstado.OPEN &&
                System.currentTimeMillis() - ultimoFallo.get() >= timeout.get()) {
            estado = CircuitBreakerEstado.HALF_CLOSED;
            return true;
        }
		
		return false;
	}
}
