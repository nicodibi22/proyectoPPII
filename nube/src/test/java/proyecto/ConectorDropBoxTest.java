package proyecto;

import static org.junit.Assert.*;
import java.io.IOException;
import org.junit.Test;
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
	public void SubirArchivoImagenTest() throws IOException {
		INube dropBox = new ConectorDropBox();
		dropBox.conectar();
		assertTrue(dropBox.upload(NubePropiedades.getInstance().getPropiedad("IMAGEN_PNG")));		
	}
	
	@Test
	public void CompartirArchivoTest() throws IOException {
		INube dropBox = new ConectorDropBox();
		dropBox.conectar();
		assertTrue(dropBox.uploadAndShare(NubePropiedades.getInstance().getPropiedad("ARCHIVO_TEXTO"), 
				NubePropiedades.getInstance().getPropiedad("MAIL_UNO")));
		assertTrue(dropBox.uploadAndShare(NubePropiedades.getInstance().getPropiedad("IMAGEN_JPG"), 
				NubePropiedades.getInstance().getPropiedad("MAIL_UNO")));
	}
	
	@Test
	public void buscarYEncontrarConectorDropBox() {
		assertTrue(ListaNubes.loadNubes().get("DROPBOX").getTipo().equals(NubeEnum.DROPBOX));
	}
	
}
