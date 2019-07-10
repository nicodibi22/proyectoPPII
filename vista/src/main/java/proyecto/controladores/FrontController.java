package proyecto.controladores;

import java.util.Locale;


import proyecto.servicios.impl.ManejadorIdioma;
import proyecto.servicios.impl.ManejadorIdioma.Idioma;

public class FrontController {

	private static FrontController frontController;
	
	private Dispatcher dispatch;
	
	private ManejadorIdioma idioma;
	
	public static FrontController getInstance(Idioma idioma) {
		if(frontController == null) {
			frontController = new FrontController();				
		}
		frontController.setLocale(idioma);
		return frontController;
	}
	
	private FrontController() {
		dispatch = new Dispatcher();
	}
	
	private void setLocale(Idioma idioma) {
		
		this.idioma = ManejadorIdioma.getInstance(idioma);		
	}
	
	
	public void manejarRequest(String request) {
		
		dispatch.dispatch(request);
	}
}
