package proyecto.servicios.impl;

import proyecto.servicios.IAutenticador;

public class Autenticador implements IAutenticador {

	@Override
	public void Autenticar(String user, String password) throws AutenticadorExcepcion {
		// TODO Auto-generated method stub
		
		throw new AutenticadorExcepcion(AutenticadorExcepcion.USUARIO_NO_VALIDO);
	
	}

}
