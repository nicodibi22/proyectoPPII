package proyecto.servicios.impl;

public class CredencialInfo {

	public enum CredencialEstado {
		VALIDO,
		INVALIDO,
		INEXISTENTE
	}
	
	private CredencialEstado estado;
	
	public CredencialInfo(String usuario, CredencialEstado estado) {
		this.usuario = usuario;
		this.estado = estado;
	}

	private String usuario;
	
	public String getUsuario() {
		return this.usuario;
	}
	
	public CredencialEstado getEstado() {
		return this.estado;
	}
}
