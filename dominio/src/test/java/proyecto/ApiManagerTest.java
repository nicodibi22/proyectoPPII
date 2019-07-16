package proyecto;

import static org.junit.Assert.*;

import org.junit.Test;

import proyecto.servicios.impl.ApiManager;
import proyecto.servicios.impl.ApiManager.FormatoRespuesta;
import proyecto.servicios.impl.AutenticadorExcepcion;
import proyecto.servicios.impl.CredencialInfo;
import proyecto.servicios.impl.CredencialInfo.CredencialEstado;

public class ApiManagerTest {

	@Test
	public void autenticarTest() throws AutenticadorExcepcion {
		CredencialesApi credenciales = new CredencialesApi("nicodibi", "algo");
		ApiManager api = new ApiManager(credenciales, FormatoRespuesta.XML);
		assertTrue(api.getCredenciales().getUsuario().equals("nicodibi"));
		assertTrue(api.getCredenciales().getEstado().equals(CredencialEstado.VALIDO));
	}

	@Test 
	public void autenticarUsuarioInexistenteTest() throws AutenticadorExcepcion {
		CredencialesApi credenciales = new CredencialesApi("nicodibi2", "algo");
		ApiManager api = new ApiManager(credenciales, FormatoRespuesta.XML);
		
		assertTrue(api.getCredenciales().getEstado().equals(CredencialEstado.INEXISTENTE));
	}
	
	@Test (expected=AutenticadorExcepcion.class)
	public void getFotosSinAutenticarTest() throws AutenticadorExcepcion {
		CredencialesApi credenciales = new CredencialesApi("nicodibi2", "algo");
		ApiManager api = null;
		try {
			api = new ApiManager(credenciales, FormatoRespuesta.XML);
		} catch (AutenticadorExcepcion e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(api.obtenerFotos("error")); ;		
	}
	@Test 
	public void getFotosTest() throws AutenticadorExcepcion {
		CredencialesApi credenciales = new CredencialesApi("nicodibi", "algo");
		ApiManager api = null;
		try {
			api = new ApiManager(credenciales, FormatoRespuesta.XML);
		} catch (AutenticadorExcepcion e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(api.obtenerFotos("PruebaPPII")); ;		
	}
}
