package proyecto.servicios;

import proyecto.servicios.impl.AutenticadorExcepcion;

public interface Autenticador {

	public void autenticar(String user, String password) throws AutenticadorExcepcion;
}
