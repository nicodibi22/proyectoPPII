package proyecto;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import proyecto.servicios.impl.GatewayRedSocial;

public class GatewayRedSocialTest {

	@Test 
	public void subirArchivoCompartirTest() throws Exception {
		GatewayRedSocial gateway = new GatewayRedSocial();
		gateway.getFotos("PruebaPPII");
		
	}
}
