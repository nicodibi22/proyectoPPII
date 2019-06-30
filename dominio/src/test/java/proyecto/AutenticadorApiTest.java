package proyecto;

import static org.junit.Assert.*;

import org.junit.Test;

import proyecto.servicios.Autenticador;
import proyecto.servicios.impl.AutenticadorApi;
import proyecto.servicios.impl.AutenticadorExcepcion;

public class AutenticadorApiTest {

	private String USER = "nicodibi";
	private String PASS = "algo";
	@Test
	public void autenticarTest() {
		
		Autenticador auten = AutenticadorApi.getInstance();
		try {
			auten.autenticar(USER, PASS);
		} catch (AutenticadorExcepcion e) {
			e.printStackTrace();
		}
		
	}

}
