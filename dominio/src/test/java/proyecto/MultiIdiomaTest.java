package proyecto;

import static org.junit.Assert.*;

import java.util.MissingResourceException;

import org.junit.Test;

import proyecto.servicios.impl.ManejadorEtiqueta;
import proyecto.servicios.impl.ManejadorIdioma;
import proyecto.servicios.impl.ManejadorIdioma.Idioma;

public class MultiIdiomaTest {

	ManejadorEtiqueta labels = new ManejadorEtiqueta();
	
	
	
	@Test
	public void EspaniolTest() {
		ManejadorIdioma.getInstance(Idioma.ESPANIOL);
		assertTrue(ManejadorEtiqueta.getInstance().getMensaje("etiquetaIngreso").equals("Ingreso"));
		assertTrue(ManejadorEtiqueta.getInstance().getMensaje("etiquetaEgreso").equals("Egreso"));
		assertTrue(ManejadorEtiqueta.getInstance().getMensaje("etiquetaReporte").equals("Reportes"));
	}
	
	@Test
	public void InglesTest() {
		ManejadorIdioma.getInstance(Idioma.INGLES);
		assertTrue(ManejadorEtiqueta.getInstance().getMensaje("etiquetaIngreso").equals("In"));
		assertTrue(ManejadorEtiqueta.getInstance().getMensaje("etiquetaEgreso").equals("Out"));
		assertTrue(ManejadorEtiqueta.getInstance().getMensaje("etiquetaReporte").equals("Report"));
	}

	@Test (expected=MissingResourceException.class)
	public void InglesEtiquetaInexistenteTest() {

		ManejadorIdioma.getInstance(Idioma.INGLES);
		ManejadorEtiqueta.getInstance().getMensaje("etiquetaInexistente").equals("Inexistent");
	}
	

	@Test (expected=MissingResourceException.class)
	public void EspaniolEtiquetaInexistenteTest() {
		ManejadorIdioma.getInstance(Idioma.ESPANIOL);
		ManejadorEtiqueta.getInstance().getMensaje("etiquetaInexistente").equals("Inexistente");	
	}
}

