package proyecto.servicios;

import proyecto.Album;

public interface RedSocial {

	public boolean estaAutenticado();
	
	public void autenticar();
	
	public void publicar(String comentario, String pathArchivo) throws Exception;
	
	public Album getFotos(String tag);
}
