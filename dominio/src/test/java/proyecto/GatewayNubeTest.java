package proyecto;

import static org.junit.Assert.*;

import org.junit.Test;

import proyecto.servicios.impl.GatewayNube;


public class GatewayNubeTest {

	@Test (expected=IllegalAccessException.class)
	public void cargarUsuarioSinNubesTest() throws IllegalAccessException {
		new GatewayNube("inexistente");
	}

	@Test 
	public void cargarUsuarioConNubesTest() throws IllegalAccessException {
		GatewayNube gateway = new GatewayNube("nicodibi");
		assertTrue(gateway.getNubes().size() == 3);
	}
	
	@Test 
	public void subirArchivoCompartirTest() throws Exception {
		GatewayNube gateway = new GatewayNube("nicodibi");
		Resultados resultados = gateway.subirArchivosCompartir("src/test/resources/imagen.png", "nicolas.dibiase22@gmail.com");
		assertTrue(resultados.getResultadosOk().size() == 1);
	}
}
