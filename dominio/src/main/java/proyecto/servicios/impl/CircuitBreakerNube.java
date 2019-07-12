package proyecto.servicios.impl;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;

import javax.print.attribute.SupportedValuesAttribute;

import proyecto.Resultado;
import proyecto.loader.LoaderClase;
import proyecto.servicios.CircuitBreaker;
import proyecto.servicios.INube;

public class CircuitBreakerNube implements CircuitBreaker<String, Boolean> {
	
	private int intentos = 5;
	
	private int timeout = 300000;
		
	private static CircuitBreakerNube instance = null;
	
	public static CircuitBreakerNube gestInstance() {
		if (instance == null) {
			instance = new CircuitBreakerNube();
		}
		return instance;
	}
	
	private CircuitBreakerNube() {
		
	}
	
	public CircuitBreakerNube agregarNube(String nube) {
		if (!estadoNubes.containsKey(nube))
			this.estadoNubes.put(nube, new Monitor(timeout, intentos));
		return instance;
	}
	
	public CircuitBreakerNube agregarNubes(List<String> nubes) {
		for (String nube : nubes)
			if (!estadoNubes.containsKey(nube))
				this.estadoNubes.put(nube, new Monitor(timeout, intentos));
		return instance;
	}
	
	private Map<String, Monitor> estadoNubes;
	
	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}
	
	public void setIntentos(int intentos) {
		this.intentos = intentos;
	}
	
	@Override
	public void ejecutar(  String nube) throws CircuitBreakerException {
		
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
			
				//ejecutar2(drive.uploadAndShare("", ""));
				
			} catch (ClassNotFoundException e) {			
				mon.aumentarIntentoFallido();
				throw new CircuitBreakerException("No se encontró la clase.");
			} catch (InstantiationException e) {
				mon.aumentarIntentoFallido();
				throw new CircuitBreakerException("Error en instanciación.");
			} catch (IllegalAccessException e) {
				mon.aumentarIntentoFallido();
				throw new CircuitBreakerException("Acceso ilegal.");
			}	
		}
		
		
	}

	@Override
	public void ejecutar(Supplier<Boolean> func) throws CircuitBreakerException {
		
		
		
	}  
	

}
