package proyecto;

import static org.junit.Assert.*;

import org.junit.Test;

import proyecto.loader.LoaderClase;
import proyecto.servicios.INube;

public class LoaderTest {

	@Test
	public void cargarClaseDriveTest() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		
		
		ClassLoader loaderGenerico = LoaderClase.class.getClassLoader();
		LoaderClase loader = new LoaderClase(loaderGenerico);
		
		Class nube = loader.loadClass("ConectorDrive");
		
		
		INube drive = (INube)nube.newInstance();
		
		drive.conectar();
		
	}

	@Test
	public void cargarClaseDropBoxTest() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		
		
		ClassLoader loaderGenerico = LoaderClase.class.getClassLoader();
		LoaderClase loader = new LoaderClase(loaderGenerico);
		
		Class nube = loader.loadClass("ConectorDropBox");
		
		
		INube drive = (INube)nube.newInstance();
		
		drive.conectar();
		
	}
}
