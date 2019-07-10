package proyecto;

import static org.junit.Assert.*;

import org.junit.Test;

import proyecto.servicios.Autenticador;
import proyecto.servicios.impl.AutenticadorApi;
import proyecto.servicios.impl.AutenticadorExcepcion;

public class AutenticadorApiTest {

	private String USER = "nicodibi";
	private String USERINCORRECTO = "nicodibi2";
	private String PASS = "algo";
	private String PASSINCORRECTA = "algo2";
	@Test
	public void autenticarTest() {
		
		Autenticador auten = AutenticadorApi.getInstance();
		try {
			auten.autenticar(USER, PASS);
		} catch (AutenticadorExcepcion e) {
			e.printStackTrace();
		}
		
	}

	@Test (expected=AutenticadorExcepcion.class)
	public void autenticarUsuarioIncorrectoTest() throws AutenticadorExcepcion{
		
		Autenticador auten = AutenticadorApi.getInstance();		
		auten.autenticar(USERINCORRECTO, PASS);
		
	}
	
	@Test (expected=AutenticadorExcepcion.class)
	public void autenticarClaveIncorrectoTest() throws AutenticadorExcepcion {
		
		Autenticador auten = AutenticadorApi.getInstance();
		auten.autenticar(USER, PASSINCORRECTA);		
		
	}
}
