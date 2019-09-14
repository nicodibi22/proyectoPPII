package proyecto;

public class AutenticadorExcepcion extends Exception {

	public static final String USUARIO_NO_VALIDO = "No es un usuario válido.";
	public static final String CONTRASENIA_INVALIDA = "La contraseña ingresada no es válida.";
	public static final String USUARIO_NO_AUTENTICADO = "Usuario no autenticado.";
	private static final long serialVersionUID = 1L;

	public AutenticadorExcepcion(String mensaje) {		
		super(mensaje);
	}
	
}
