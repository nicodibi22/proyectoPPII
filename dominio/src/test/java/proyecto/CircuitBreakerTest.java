package proyecto;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.function.Supplier;

import org.junit.Test;

import proyecto.Resultado.EstadoResultado;
import proyecto.servicios.CircuitBreaker;
import proyecto.servicios.CircuitBreakerException;
import proyecto.servicios.impl.CircuitBreakerImpl;
import proyecto.servicios.impl.CircuitBreakerNube;

public class CircuitBreakerTest {

	private Boolean trueMethod() {
		return true;
	}
	
	private Boolean falseMethod() {
		return false;
	}
	
	@Test 
	public void setearTimeoutAndIntentosTest() {
		CircuitBreaker circuitBreaker = new CircuitBreakerImpl();
		circuitBreaker.setTimeout(300);
		circuitBreaker.setIntentos(3);
		assertTrue(circuitBreaker.getIntentos() == 3);
		assertTrue(circuitBreaker.getTimeout() == 300);
	}

	@Test (expected=CircuitBreakerExecutionException.class)
	public void executionExceptionTest() throws CircuitBreakerException {
		CircuitBreaker circuitBreaker = new CircuitBreakerNube();
		
		Supplier<Boolean> func = () -> falseMethod();
		circuitBreaker.ejecutar(func);		
	}
	
	@Test (expected=CircuitBreakerOpenException.class)
	public void openExceptionTest() throws CircuitBreakerException {
		CircuitBreaker circuitBreaker = new CircuitBreakerNube();
		
		Supplier<Boolean> func = () -> falseMethod();
		for(int i = 0; i < 6; i++) {
			try {
				circuitBreaker.ejecutar(func);
			} catch (CircuitBreakerExecutionException e) {
				e.printStackTrace();
			}	
		}
			
	}
	
	@Test
	public void circuitBreakerOkMedioAbiertoTest() throws CircuitBreakerException, InterruptedException {
		CircuitBreaker circuitBreaker = new CircuitBreakerNube();
		circuitBreaker.setTimeout(1000);
		Supplier<Boolean> func = () -> falseMethod();
		Resultado resultado = null;
		for(int i = 0; i < 7; i++) {
			try {
				boolean algo = circuitBreaker.ejecutar(func);
			} catch (CircuitBreakerExecutionException e) {
				e.printStackTrace();
			} catch (CircuitBreakerOpenException e) {
				func = () -> trueMethod();
				Thread.sleep(1100);
			}
		}			
		assertTrue(resultado.getEstado().equals(EstadoResultado.OK));
	}
}
