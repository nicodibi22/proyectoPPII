package proyecto;

public class Resultado {

	public enum EstadoResultado {
		OK,
		ERROR,
		OK_ADVERTENCIA
	}
	
	public Resultado() {
		
	}
	
	private EstadoResultado estado;
	
	private String mensajeError;
	
	private String respuesta;
}
