package proyecto;

import static org.junit.Assert.*;
import org.junit.Test;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import java.io.IOException;
import java.security.GeneralSecurityException;
import com.google.api.client.auth.oauth2.Credential;

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
	public void subirArchivosTest() throws IOException {
		ConectorDrive drive = new ConectorDrive();
		drive.conectar();
		assertTrue(drive.upload(NubePropiedades.getInstance().getPropiedad("IMAGEN_JPG")));
		assertTrue(drive.upload(NubePropiedades.getInstance().getPropiedad("ARCHIVO_TEXTO")));
	}
	
	@Test
	public void subirYCompartirArchivosTest() throws IOException {
		ConectorDrive drive = new ConectorDrive();
		drive.conectar();
		assertTrue(drive.uploadAndShare(NubePropiedades.getInstance().getPropiedad("IMAGEN_PNG"), 
				NubePropiedades.getInstance().getPropiedad("MAIL_DOS")));
		assertTrue(drive.uploadAndShare(NubePropiedades.getInstance().getPropiedad("ARCHIVO_TEXTO"), 
				NubePropiedades.getInstance().getPropiedad("MAIL_UNO")));
	}
	
	@Test
	public void buscarTresNubesEnLista() {
		assertTrue(ListaNubes.getListaNubes().size() == 3);
	}
	
	@Test
	public void buscarYEncontrarConectorDrive() {
		assertTrue(ListaNubes.loadNubes().get("GOOGLEDRIVE").getTipo().equals(NubeEnum.DRIVE));
	}
	
}
