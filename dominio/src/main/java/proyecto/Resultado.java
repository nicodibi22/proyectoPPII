package proyecto;

public class Resultado {

	public enum EstadoResultado {
		OK,
		ERROR
	}
	
	public Resultado() {
		
	}
	
	public static Resultado getInstance() {
		return new Resultado();
	}
	
	private EstadoResultado estado;
	
	private String mensajeError;
	
	private String respuesta;
	
	public void setEstado(EstadoResultado estado) {
		this.estado = estado;
	}
	
	public EstadoResultado getEstado() {
		return this.estado;
	}
	
	public void setMensajeError(String mensaje) {
		this.mensajeError = mensaje;
	}
	
	public String getMensajeError() {
		return this.mensajeError;
	}
	
	public void setRespuesta(String respuesta) {
		this.respuesta = respuesta;
	}
	
	public String getResuesta() {
		return this.respuesta;
	}
}
