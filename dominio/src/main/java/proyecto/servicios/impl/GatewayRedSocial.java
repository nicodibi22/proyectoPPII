package proyecto.servicios.impl;

import java.util.Iterator;

import java.util.ServiceLoader;

import proyecto.Album;
import proyecto.servicios.RedSocial;

public class GatewayRedSocial  {

	public GatewayRedSocial() {
		
			
	}

	public Album getFotos(String tag) {
		
		ServiceLoader<RedSocial> loader = ServiceLoader.load(RedSocial.class);
        Iterator<RedSocial> redesSociales = loader.iterator();
        Album album = new Album();
        
        while (redesSociales.hasNext()) {
        	RedSocial redSocial = redesSociales.next();
        	redSocial.autenticar();
        	album.agregarFotos(redSocial.getFotos(tag).getFotos());
        }
		
		return album;
	}
}

