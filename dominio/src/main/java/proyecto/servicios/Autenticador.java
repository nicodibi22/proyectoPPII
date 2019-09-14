package proyecto.servicios;

import proyecto.AutenticadorExcepcion;
import proyecto.Token;

public interface Autenticador {

	public Token autenticar(String user, String password) throws AutenticadorExcepcion;
}
