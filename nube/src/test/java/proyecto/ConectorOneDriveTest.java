package proyecto;

import static org.junit.Assert.*;

import java.io.IOException;

import org.apache.commons.lang3.NotImplementedException;
import org.junit.Test;

import proyecto.servicios.INube;

public class ConectorOneDriveTest {

	@Test
	public void ConectarErrorTest() {
		INube oneDrive = new ConectorOneDrive();
		assertFalse(oneDrive.conectar());
	}
	
	@Test (expected=NotImplementedException.class)
	public void SubirArchivoTest() throws IOException {
		INube oneDrive = new ConectorOneDrive();
		oneDrive.conectar();
		oneDrive.upload("src/test/resources/test.txt");
	}
	
	@Test (expected=NotImplementedException.class)
	public void CompartirArchivoTest() throws IOException {
		INube oneDrive = new ConectorOneDrive();
		oneDrive.conectar();
		oneDrive.uploadAndShare("src/test/resources/test.txt", "nicolas_dibiase@yahoo.com.ar");
	}
	
	@Test
	public void buscarYEncontrarConectorOneDriveBox() {
		assertTrue(ListaNubes.loadNubes().get("ONEDRIVE").getTipo().equals(NubeEnum.ONEDRIVE));
	}

}
