package proyecto.servicios.impl;

public class AutenticadorExcepcion extends Exception {

	public static final String USUARIO_NO_VALIDO = "No es un usuario válido.";
	public static final String CONTRASENIA_INVALIDA = "La contraseña ingresada no es válida.";
	
	private static final long serialVersionUID = 1L;

	public AutenticadorExcepcion(String mensaje) {		
		super(mensaje);
	}
	
}
