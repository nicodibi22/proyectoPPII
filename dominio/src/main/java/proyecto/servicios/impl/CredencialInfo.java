package proyecto.servicios.impl;

public class CredencialInfo {

	protected enum CredencialEstado {
		VALIDO,
		INVALIDO
	}
	
	private CredencialEstado estado;
	
	public CredencialInfo(String usuario, CredencialEstado estado) {
		this.usuario = usuario;
		this.estado = estado;
	}

	private String usuario;
	
}
