package proyecto.servicios.impl;

import java.util.Iterator;

import java.util.ServiceLoader;
import java.util.concurrent.Callable;
import proyecto.Album;
import proyecto.servicios.CircuitBreaker;
import proyecto.servicios.RedSocial;

public class GatewayRedSocial  {

	public GatewayRedSocial() {
		
			
	}

	public Album getFotos(String tag) throws Exception {
		
		ServiceLoader<RedSocial> loader = ServiceLoader.load(RedSocial.class);
        Iterator<RedSocial> redesSociales = loader.iterator();
        Album album = new Album();        
        
        while (redesSociales.hasNext()) {
        	
        	RedSocial redSocial = redesSociales.next();
        	Callable<Album> con = () -> redSocial.getFotos(tag);
        	CircuitBreaker circuitBreaker = CircuitBreakerFactory.crearCircuitBreaker(redSocial.getNombre(), 3, 5000);        
			album = circuitBreaker.invoke(con);
        	album.agregarFotos(redSocial.getFotos(tag).getFotos());
        }
		
		return album;
	}
}

