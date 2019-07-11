package proyecto.servicios.impl;

import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

import proyecto.loader.LoaderClase;
import proyecto.servicios.CircuitBreaker;
import proyecto.servicios.INube;

public class CircuitBreakerNube implements CircuitBreaker {

	private static CircuitBreakerNube instance = null;
	
	private int intentos = 5;
	
	private int timeout = 300000;
		
	public CircuitBreakerNube(List<String> nubes) {
		
		for (String n : nubes ) {
			this.estadoNubes.put(n, new Monitor(timeout, intentos));
		}
		
	}
	
	private Map<String, Monitor> estadoNubes;
	
	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}
	
	public void setIntentos(int intentos) {
		this.intentos = intentos;
	}
	
	@Override
	public void ejecutar(String nube) {
		
		Monitor mon = estadoNubes.get(nube);
		
		if(!mon.estaAbierto()) {
			ClassLoader loaderGenerico = LoaderClase.class.getClassLoader();
			LoaderClase loader = new LoaderClase(loaderGenerico);
			
			Class oNube;
			try {
				oNube = loader.loadClass(nube);
				INube drive = (INube)oNube.newInstance();
				drive.conectar();
				
				if(!mon.estaCerrado()) {
					mon.reset();
				}
				
			} catch (ClassNotFoundException e) {			
				mon.aumentarIntentoFallido();
			} catch (InstantiationException e) {
				mon.aumentarIntentoFallido();
			} catch (IllegalAccessException e) {
				mon.aumentarIntentoFallido();
			}	
		}
		
		
	}  
}
