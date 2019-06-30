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
	public void SubirArchivoTest() throws IOException {
		INube dropBox = new ConectorDropBox();
		assertFalse(dropBox.upload(""));
	}
	
}
