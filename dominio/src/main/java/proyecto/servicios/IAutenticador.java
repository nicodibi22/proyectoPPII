package proyecto.servicios;

import proyecto.servicios.impl.AutenticadorExcepcion;

public interface IAutenticador {

	public void Autenticar(String user, String password) throws AutenticadorExcepcion;
}
