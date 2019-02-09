package proyecto.servicios;

import java.io.File;

public interface IRedSocial {

	public boolean estaAutenticado();
	
	public void Autenticar();
	
	public void Publicar(String comentario, String pathArchivo) throws Exception;
}
