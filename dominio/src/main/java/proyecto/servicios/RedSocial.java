package proyecto.servicios;

public interface RedSocial {

	public boolean estaAutenticado();
	
	public void autenticar();
	
	public void publicar(String comentario, String pathArchivo) throws Exception;
	
	public void getFotos(String tag);
}
