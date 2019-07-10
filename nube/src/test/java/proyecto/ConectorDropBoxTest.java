package proyecto;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import com.dropbox.core.DbxException;

import proyecto.servicios.INube;

public class ConectorDropBoxTest {

	
	@Test
	public void ConectarTest() {
		INube dropBox = new ConectorDropBox();
		assertTrue(dropBox.conectar());
	}

	@Test
	public void SubirArchivoSinConectarTest() throws IOException {
		INube dropBox = new ConectorDropBox();
		assertFalse(dropBox.upload(""));
	}
	
	@Test
	public void SubirArchivoTest() throws IOException {
		INube dropBox = new ConectorDropBox();
		dropBox.conectar();
		assertTrue(dropBox.upload("src/test/resources/test.txt"));
	}
	
	@Test
	public void CompartirArchivoTest() throws IOException {
		INube dropBox = new ConectorDropBox();
		dropBox.conectar();
		assertTrue(dropBox.uploadAndShare("src/test/resources/test.txt", "nicolas_dibiase@yahoo.com.ar"));//("src/test/resources/test.txt"));
	}
	
	@Test
	public void buscarYEncontrarConectorDrive() {
		assertTrue(ListaNubes.loadNubes().get("DROPBOX").getTipo().equals(NubeEnum.DROPBOX));
	}
	
}
