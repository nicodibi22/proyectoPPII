package proyecto;

import static org.junit.Assert.*;

import org.junit.Ignore;
import org.junit.Test;


import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.List;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.services.drive.model.File;

public class ConectorDriveTest {
	
	@Test
	public void conectarConGoogleDriveConExito() throws IOException {
		ConectorDrive drive = new ConectorDrive();
		
		//conecto con Drive: true si es exitoso
		boolean resultado = drive.conectar();
		assertTrue("Hay conexión: ", resultado);
	}
	
	@Test
	public void conectarConGoogleYValidarCredencialesEnUbicacionErronea() throws IOException {
		//Intento conseguir credenciales en ubicación inexistente
		try {
			Credential credenciales = ConectorDrive.getCredentials("pathInexistente");
			
			assertTrue("credenciales: ", credenciales != null );
			fail("Encontré las credenciales cuando no debería");
		}catch(java.lang.NullPointerException e) {
			assertTrue("Obtención de credenciales", true);
		} catch (GeneralSecurityException e) {
			assertTrue("Obtención de credenciales", true);
		}
	}
	
	@Test
	public void conectarConGoogleYValidarCredenciales() throws IOException {
		//Intento conseguir credenciales en ubicación inexistente
		try {
			Credential credenciales = ConectorDrive.getCredentials("credentialsDrive.json");
			
			assertTrue("credenciales: ", credenciales != null );
			
		} catch (GeneralSecurityException e) {
			assertTrue("Obtención de credenciales", true);
		}
	}	
	
	@Test
	public void subirArchivoTest() throws IOException {
		ConectorDrive drive = new ConectorDrive();
		drive.conectar();
		assertTrue(drive.upload("src/test/resources/test.txt"));
	}
	
	@Test
	public void subirYCompartirArchivoTest() throws IOException {
		ConectorDrive drive = new ConectorDrive();
		drive.conectar();
		assertTrue(drive.uploadAndShare("src/test/resources/test.txt", "nicolas.dibiase22@gmail.com"));
	}
	
	@Test
	public void buscarTresNubesEnLista() {
		assertTrue(ListaNubes.getListaNubes().size() == 3);
	}
	
	@Test
	public void buscarYEncontrarConectorDrive() {
		assertTrue(ListaNubes.loadNubes().get("GOOGLEDRIVE").getTipo().equals(NubeEnum.DRIVE));
	}
	
	@Test
	public void factoryDeNubesDevolvemeUnaAunqueSea() {
		assertTrue(NubeManagerFactory.getNube("GOOGLEDRIVE").getTipo().equals(NubeEnum.DRIVE));
	}
	
	@Test
	public void factoryDeNubesDevolvemeListaConAlMenosUna() {
		assertTrue(NubeManagerFactory.getNube(Arrays.asList("GOOGLEDRIVE")).size() > 0);
	}
	
	

}
